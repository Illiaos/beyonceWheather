package manages;

import java.io.File;

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
        manager.requestWeather("Warszawa");
        manager.requestWeather("Brasilia");

        /*try{

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

                    if(MagicBooleans.trace_mode)
                        System.out.println("STATE = " + request + ":THAT = " + ((History) session.thatHistory.get(0)).get(0) + ":TOPIC = " + session.predicates.get("topic"));

                    String response = session.multisentenceRespond(request);
                    while(response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while(response.contains("&gt;"))
                        response = response.replace("&gt;", ">");

                    System.out.println(response);

                }

            }

        } catch(Exception e){
            e.printStackTrace();
        }*/
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
