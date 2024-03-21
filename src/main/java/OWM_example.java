import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;

public class OWM_example
{
    private static final String apiKey = "a062d298e14f66a96d308541cf16cf6f";
    public static void main(String[] args) throws APIException
    {
        WeatherManager manager = new WeatherManager();
        manager.requestWeather("London");
    }
}
