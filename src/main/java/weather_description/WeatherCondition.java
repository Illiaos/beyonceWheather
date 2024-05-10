package weather_description;

import helper.DataConversion;

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

    //method to get min temperature
    public  double getMinWeather()
    {
        return  calculateMinTemperature();
    }

    //method to calculate min day temperature
    private double calculateMinTemperature()
    {
        double temp = 0.0f;
        for(WeatherData value : weatherDataHashMap.values())
        {
            temp += value.getMaxTemperature();
        }
        return  temp / weatherDataHashMap.size();
    }

    //method to get max temperature
    public double getMaxWeather()
    {
        return  calculateMaxTemperature();
    }

    //method to calculate min day temperature
    private double calculateMaxTemperature()
    {
        double temp = 0.0f;
        for(WeatherData value : weatherDataHashMap.values())
        {
            temp += value.getMaxTemperature();
        }
        return  temp / weatherDataHashMap.size();
    }

    //method to get general temperature
    public  double getGeneralTemperature()
    {
        return  calculateGeneralTemperature();
    }

    //method to calculate general day temperature
    private double calculateGeneralTemperature()
    {
        double temp = 0.0f;
        for(WeatherData value : weatherDataHashMap.values())
        {
            temp += value.getGeneralTemperature();
        }
        return DataConversion.round(temp / weatherDataHashMap.size(), 2);
    }

    public String rainState()
    {
        for(Map.Entry<String, WeatherData> item : weatherDataHashMap.entrySet())
        {
            if(item.getValue().getRain() == null) continue;
            if(!item.getValue().getRain().getIsRaining()) continue;

            String returnValue = "Raining on " + item.getKey().split(" ")[0];
            returnValue += " At: " + item.getKey().split(" ")[1];
            returnValue += " Duration: " + item.getValue().getRain().getDuration().replaceAll("\"", "");
            return returnValue;
        }
        return null;
    }
}
