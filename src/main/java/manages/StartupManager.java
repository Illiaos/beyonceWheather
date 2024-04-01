package manages;

public class StartupManager
{
    public void main(String []args)
    {
        WeatherManager manager = new WeatherManager();
        manager.requestWeather("London");
    }
}
