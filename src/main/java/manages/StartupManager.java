package manages;

import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import city.City;

import org.alicebot.ab.*;
import org.alicebot.ab.utils.IOUtils;

public class StartupManager
{
    private static final boolean TRACE_MODE = false;
    private static final String BOT_NAME = "super";
    public static void main(String []args)
    {
        WeatherManager manager = new WeatherManager();
        manager.requestWeather("London");
        //manager.requestWeather("Warszawa");
        //manager.requestWeather("Brasilia");

        try{

            //Initialise bot
            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot beyonce = new Bot(BOT_NAME, resourcesPath);
            Chat session = new Chat(beyonce);
            beyonce.brain.nodeStats();

            String line;

            while(true) {

                //Await input from user
                System.out.print(">");
                line = IOUtils.readInputTextLine();

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

                        System.out.println(">" + response);

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

                        System.out.println(response);

                    }

                }

            }

        } catch(Exception e){
            e.printStackTrace();
        }
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
}
