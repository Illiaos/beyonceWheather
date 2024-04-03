package manages;

import user_input.UserInput;

public class StartupManager
{
    public static void main(String []args)
    {
        UserInput userInput = new UserInput();
        userInput.manageInput();

        //WeatherManager manager = new WeatherManager();
        //manager.requestWeather("London");
    }
}
