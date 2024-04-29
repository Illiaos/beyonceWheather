package manages;

import java.awt.event.ActionListener;
import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

import city.City;

import org.alicebot.ab.*;

public class GUI implements ActionListener {

    public GUI(){}

    private static final boolean TRACE_MODE = false;
    private static final String BOT_NAME = "super";

    private Bot beyonce = null;
    private Chat session = null;
    private JTextField input;
    private JTextArea outputArea;
    private String imagePath;
    private ImageIcon[] icons = new ImageIcon[2];
    private JLabel image;

    protected void run(){

        try {
            //Initialise bot
            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            beyonce = new Bot(BOT_NAME, resourcesPath);
            session = new Chat(beyonce);
            beyonce.brain.nodeStats();
        }catch(Exception e){
            e.printStackTrace();
        }

        //GUI
        JFrame frame = new JFrame("Beyonce Weather");
        GridLayout grid = new GridLayout(2, 1);
        frame.setLayout(grid);

        getImagePath();
        icons[0] = new ImageIcon(imagePath + "/beyonce_mouth_closed.png");
        icons[1] = new ImageIcon(imagePath + "/beyonce_mouth_open.png");

        image = new JLabel(icons[0]);
        image.setBounds(0,0,250,250);

        JPanel outputPanel = new JPanel(null);

        JPanel botPanel = new JPanel(null);
        botPanel.add(image);
        botPanel.setBounds(0, 0, 250, 700);

        outputArea = new JTextArea();
        outputArea.setBounds(250, 0, 750, 700);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Calibri", Font.PLAIN, 16));
        JScrollPane outputAreaScroll = new JScrollPane(outputArea);
        outputAreaScroll.setBounds(250, 0, 695, 250);
        outputAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        outputPanel.add(botPanel);
        outputPanel.add(outputAreaScroll);

        JPanel inputPanel = new JPanel(null);

        JLabel inputLabel = new JLabel("Enter prompt.");
        inputLabel.setBounds(375, 50, 200, 50);
        inputLabel.setFont(new Font("Corbel", Font.PLAIN, 30));

        input = new JTextField();
        input.setFont(new Font("Calibri", Font.PLAIN, 16));
        input.setBounds(250,100, 400, 30);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Corbel", Font.PLAIN, 16));
        submitBtn.setForeground(Color.black);
        submitBtn.setBackground(Color.white);
        Border lb = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 5, 5, 5);
        Border compound = new CompoundBorder(lb, margin);
        submitBtn.setBorder(compound);
        submitBtn.setBounds(650,100, 75, 29);

        //Where the action happens
        submitBtn.addActionListener(this);

        inputPanel.add(inputLabel);
        inputPanel.add(input);
        inputPanel.add(submitBtn);

        frame.getContentPane().add(outputPanel);
        frame.getContentPane().add(inputPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent a) {

        WeatherManager manager = new WeatherManager();

        try{

            String line = input.getText();
            input.setText("");
            outputArea.append(line + "\n");

            //If input is null or 0 length
            if(line == null || line.length() < 1) {
                line = MagicStrings.null_input;
            }

            //quit
            if(line.equals("q")) {
                System.exit(0);
            }
            //write AIML & quit
            else if(line.equals("wq")) {
                beyonce.writeQuit();
                System.exit(0);
            }
            else {

                String request = line;

                String question = "";
                String[] requestArr = request.split(" ");

                //for loop to extract question from request
                for(int i = 0; i < requestArr.length - 1; i++){

                    question += requestArr[i] + " ";

                }

                question = question.strip();

                //location to be queried
                String location = requestArr[requestArr.length - 1];

                //if the question matches the set structure
                if(correctSyntax(question.toUpperCase())){

                    //request the weather
                    City city = manager.requestWeather(location);
                    String recommendedClothes = "";

                    //temps lower than 0
                    if(city.getTemperature() <= 0) {
                        recommendedClothes = "a parka, woolen pants, some warm gloves, and a wooly hat.";
                    }
                    //temps between 0 and 10
                    else if(city.getTemperature() <= 10){
                        recommendedClothes = "a thick jacket or fleece, woolen pants, and a scarf.";
                    }
                    //temps between 10 and 20
                    else if(city.getTemperature() <= 20){
                        recommendedClothes = "a lightweight cardigan, some jeans, and maybe a scarf.";
                    }
                    //temps between 20 and 30
                    else if(city.getTemperature() <= 30){
                        recommendedClothes = "a lightweight top such as a T-shirt, shorts or light pants, a sun cap, sandals, and sunglasses. It might also be beneficial to bring sunscreen and a water bottle.";
                    }
                    //temps between 30 and 40
                    else if(city.getTemperature() <= 40){
                        recommendedClothes = "light, breathable, and loose fitting clothes such as summer dresses or T-shirts, shorts, and sandals. It would be helpful to bring a water bottle and sunscreen as well to keep cool and protect against UV exposure.";
                    }
                    //temps above 40
                    else{
                        recommendedClothes = "light, breathable clothes, a lot of sunscreen, a good amount of water to stay hydrated, and someplace to seek shade until temperatures cool.";
                    }

                    String response = "As the temperature in " + city.name + " is currently " + city.getTemperature() + "° Celsius, I would recommend " + recommendedClothes;

                    outputArea.append(">" + response + "\n\n");
                    botTalk();

                }
                //else run bot code
                else {

                    if (MagicBooleans.trace_mode)
                        System.out.println("STATE = " + request + ":THAT = " + (session.thatHistory.get(0)).get(0) + ":TOPIC = " + session.predicates.get("topic"));

                    String response = session.multisentenceRespond(request);
                    while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");

                    outputArea.append(">" + response + "\n\n");
                    botTalk();

                }

            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    private void botTalk() {

        //TODO: Get that damn bot talking

    }

    private static boolean correctSyntax(String question) {

        String[] questionArr = {"WHAT IS THE WEATHER LIKE IN", "WHAT'S THE WEATHER LIKE IN", "WHATS THE WEATHER LIKE IN",
                "WHAT IS THE WEATHER IN", "WHAT'S THE WEATHER IN", "WHATS THE WEATHER IN",
                "WHAT IS THE TEMPERATURE LIKE IN", "WHAT'S THE TEMPERATURE LIKE IN", "WHATS THE TEMPERATURE LIKE IN",
                "WHAT IS THE TEMPERATURE IN", "WHAT'S THE TEMPERATURE IN", "WHATS THE TEMPERATURE IN",
                "WHAT IS IT LIKE IN", "WHAT'S IT LIKE IN", "WHATS IT LIKE IN",
                "WHAT IS THE FORECAST FOR", "WHAT'S THE FORECAST FOR", "WHATS THE FORECAST FOR"};

        for(int i = 0; i < questionArr.length; i++){
            if(question.equals(questionArr[i]))
                return true;

        }
        return false;
    }

    private static String getResourcesPath() {

        //Get resource location for bot
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "bot";
        return resourcesPath;
    }

    private void getImagePath() {

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        imagePath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "images";

    }

}
