package manages;

import city.City;
import net.aksingh.owmjapis.core.OWM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;


import org.json.JSONArray;
import org.json.JSONObject;
import weather_description.WeatherTemperature;

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
                for (int i = 0; i < listArray.length(); i++)
                {
                    JSONObject listElement = listArray.getJSONObject(i);

                    city.weatherTemperature.add(createNewTemperature(listElement.getJSONObject("main")));
                }
                System.out.println("Temperature: " + city.getTemperature() + "F");
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
        System.out.println(listItem);
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

    private WeatherTemperature createNewTemperature(JSONObject listItem)
    {
        WeatherTemperature weatherTemperature = new WeatherTemperature();
        weatherTemperature.temperature = listItem.getDouble("temp");
        weatherTemperature.temperatureMax = listItem.getDouble("temp_max");
        weatherTemperature.temperatureMin = listItem.getDouble("temp_min");
        weatherTemperature.temperatureFeelsLike = listItem.getDouble("feels_like");
        weatherTemperature.humidity = listItem.getDouble("humidity");
        return  weatherTemperature;
    }
}
