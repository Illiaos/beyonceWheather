package manages;

import city.City;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;


import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import weather_description.Rain;
import weather_description.WeatherData;

public class WeatherManager {
    private final String apiKey = "a062d298e14f66a96d308541cf16cf6f";

    public WeatherManager()
    {

    }

    public void requestWeather(String cityName)
    {
        try {
            String urlString = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s", cityName, apiKey);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null)
                {
                    response.append(line);
                }
                br.close();
                JSONObject jsonObject = new JSONObject(response.toString());

                City city = createNewCity(jsonObject.getJSONObject("city"));

                JSONArray listArray = jsonObject.getJSONArray("list");
                //System.out.println(listArray.getJSONObject(0).getString("dt_txt"));
                //System.out.println(listArray.getJSONObject(0));
                for (int i = 0; i < listArray.length(); i++)
                {
                    JSONObject listElement = listArray.getJSONObject(i);
                    System.out.println(listElement);
                    WeatherData weatherData = new WeatherData();
                    getWeatherTemperature(listElement.getJSONObject("main"), weatherData);
                    getRainData(listElement, weatherData);
                    city.addWeatherCondition(listElement.getString("dt_txt"), weatherData);
                }
                //city.Debug();
                //System.out.println("Temperature: " + city.getTemperature() + "F");
            }
            else
            {
                System.out.println("Failed to fetch data. HTTP Error Code: " + conn.getResponseCode());
            }
            conn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private City createNewCity(JSONObject listItem)
    {
        City returnValue = new City();
        returnValue.id = listItem.getDouble("id");
        returnValue.name = listItem.getString("name");
        returnValue.country = listItem.getString("country");
        returnValue.population = listItem.getDouble("population");
        returnValue.timeZone = listItem.getDouble("timezone");
        returnValue.sunrise = listItem.getDouble("sunrise");
        returnValue.sunset = listItem.getDouble("sunset");
        return  returnValue;
    }

    private void getWeatherTemperature(JSONObject listItem, WeatherData weatherData)
    {
        //System.out.println(listItem);
        weatherData.generalTemperature = listItem.getDouble("temp");
        weatherData.minTemperature = listItem.getDouble("temp_min");
        weatherData.maxTemperature = listItem.getDouble("temp_max");
        //WeatherTemperature weatherTemperature = new WeatherTemperature();
        //weatherTemperature.temperature = listItem.getDouble("temp");
        //weatherTemperature.temperatureMax = listItem.getDouble("temp_max");
        //weatherTemperature.temperatureMin = listItem.getDouble("temp_min");
        //weatherTemperature.temperatureFeelsLike = listItem.getDouble("feels_like");
        //weatherTemperature.humidity = listItem.getDouble("humidity");
        //return  weatherTemperature;
    }

    private void getRainData(JSONObject listItem, WeatherData weatherData)
    {
        Rain rain;
        if(!listItem.has("rain"))
        {
            rain = new Rain();
            weatherData.setRain(rain);
        }

        JSONObject rainObj = listItem.getJSONObject("rain");
        JSONArray weatherArray = listItem.getJSONArray("weather");
        String weather = weatherArray.toString();
        String[] split = weather.split(",");
        split = split[1].split(":");
        split[1] = split[1].replace("\"", "");
        String parse = rainObj.toString().replace("{", "");
        parse = parse.replace("}", "");
        split = parse.split(":");
        rain = new Rain(split[1], split[0], Double.valueOf(split[1]));
    }
}
