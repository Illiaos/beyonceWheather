package weather_description;

import java.util.HashMap;
import java.util.Map;

public class WeatherCondition
{
    private HashMap<String, WeatherData> weatherDataHashMap;

    public WeatherCondition()
    {
        weatherDataHashMap = new HashMap<>();
    }

    public boolean addData(String date, WeatherData weatherData)
    {
        if(weatherDataHashMap.containsKey(date)) return false;
        weatherDataHashMap.put(date, weatherData);
        return true;
    }

    public WeatherData GetWeatherData(String key)
    {
        return weatherDataHashMap.get(key);
    }

    public void Debug()
    {
        for ( Map.Entry<String, WeatherData> entry : weatherDataHashMap.entrySet())
        {
            String key = entry.getKey();
            WeatherData data = entry.getValue();
            //System.out.println("DATE: " + key + " TEMP: " + data.getGeneralTemperature() + " RAIN: " + data.isRaining + " RAIN DURATION: " + data.rainDuration);
        }
    }
}
