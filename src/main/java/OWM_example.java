import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

public class OWM_example
{
    private static final String apiKey = "a062d298e14f66a96d308541cf16cf6f";
    public static void main(String[] args) throws APIException
    {

        // declaring object of "OWM" class
        OWM owm = new OWM(apiKey);

        // getting current weather data for the "London" city
        CurrentWeather cwd = owm.currentWeatherByCityName("London");

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());

        // printing the max./min. temperature
        System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                + "/" + cwd.getMainData().getTempMin() + "\'K");
    }
}
