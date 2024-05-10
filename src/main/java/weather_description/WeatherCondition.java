package weather_description;

import helper.DataConversion;
import org.jetbrains.annotations.Nullable;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
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

    //get frequent weather state
    public String getGeneralWeatherState()
    {
        HashMap<String, Integer> states = new HashMap<>();
        for(WeatherData data : weatherDataHashMap.values())
        {
            if(states.containsKey(data.getWeatherMain()))
            {
                states.compute(data.getWeatherMain(), (k, current) -> current + 1);
            }
            else
            {
                states.put(data.getWeatherMain(), 1);
            }
        }

        return getKeyWithHighestValue(states);
    }

    //find most frequent weather state
    private static @Nullable String getKeyWithHighestValue(HashMap<String, Integer> states)
    {
        String keyWithHighestValue = null;
        int highestValue = 0;

        // Iterate through the entries
        for (Map.Entry<String, Integer> entry : states.entrySet())
        {
            // Check if the current value is higher than the highest value found so far
            if (entry.getValue() > highestValue) {

                // Update the highest value and corresponding key
                highestValue = entry.getValue();
                keyWithHighestValue = entry.getKey();
            }
        }
        return keyWithHighestValue;
    }
}
