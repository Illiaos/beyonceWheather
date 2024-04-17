package weather_description;

import java.util.HashMap;
import java.util.Map;

public class WeatherCondition
{
    //hash map used to store weather data according to a passed dates
    private HashMap<String, WeatherData> weatherDataHashMap;

    //default constructor
    public WeatherCondition()
    {
        weatherDataHashMap = new HashMap<>();
    }

    //method to add data to the hashmap
    public boolean addData(String date, WeatherData weatherData)
    {
        if(weatherDataHashMap.containsKey(date)) return false;
        weatherDataHashMap.put(date, weatherData);
        return true;
    }

    //method to get weather condition by date
    public WeatherData getWeatherData(String key)
    {
        return weatherDataHashMap.get(key);
    }

    public int getSize()
    {
        return weatherDataHashMap.size();
    }
}
