package manages;

import user_input.UserInput;

public class StartupManager
{
    public static void main(String []args)
    {
        UserInput userInput = new UserInput();
        userInput.manageInput();

        //currently used for debug, would be modified in future
        WeatherManager manager = new WeatherManager();
        manager.requestWeather("London");
    }
}
