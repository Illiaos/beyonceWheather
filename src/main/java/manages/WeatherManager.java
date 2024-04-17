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

    //key for connection
    private final String API_KEY = "a062d298e14f66a96d308541cf16cf6f";

    //default constructor
    public WeatherManager()
    {

    }

    public City requestWeather(String cityName)
    {
        City city = null;
        try {
            //connection string
            String urlString = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s", cityName, API_KEY);

            //connection to the OpenWeather Server and request of data
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //check state connection
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                //read data from server and store in String
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null)
                {
                    response.append(line);
                }
                br.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                city = createNewCity(jsonObject.getJSONObject("city"));
                JSONArray listArray = jsonObject.getJSONArray("list");
                for (int i = 0; i < listArray.length(); i++)
                {
                    JSONObject listElement = listArray.getJSONObject(i);
                    //System.out.println(listElement);
                    WeatherData weatherData = new WeatherData();
                    getWeatherTemperature(listElement.getJSONObject("main"), weatherData);
                    getWeatherMain(listElement, weatherData);
                    city.addWeatherCondition(listElement.getString("dt_txt"), weatherData);
                }
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
        return city;
    }

    //method to get data for a city, where a user will go
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

    //method to extract min, max and general temperature
    private void getWeatherTemperature(JSONObject listItem, WeatherData weatherData)
    {
        weatherData.setGeneralTemperature(listItem.getDouble("temp"));
        weatherData.setMinTemperature(listItem.getDouble("temp_min"));
        weatherData.setMaxTemperature(listItem.getDouble("temp_max"));
    }

    //method to parse passed Json object, extract data for a rain state, time and value
    private void getRainData(JSONObject listItem, WeatherData weatherData)
    {
        Rain rain;
        if(!listItem.has("rain"))
        {
            rain = new Rain();
            weatherData.setRain(rain);
            return;
        }

        JSONObject rainObj = listItem.getJSONObject("rain");
        String parse = rainObj.toString().replace("{", "");
        parse = parse.replace("}", "");
        String[] split = parse.split(":");
        rain = new Rain(split[0], Double.parseDouble(split[1]));
    }

    //method to parse passed json object and extract weather array, that contains information according to a weather condition
    private void getWeatherMain(JSONObject listItem, WeatherData weatherData)
    {
        JSONArray weatherArray = listItem.getJSONArray("weather");
        String weather = weatherArray.toString();
        if(weather.isEmpty()) return;
        JSONObject obj = (JSONObject) weatherArray.get(0);
        weatherData.setWeatherDescription(obj.getString("description"));
        weatherData.setWeatherMain(obj.getString("main"));

        // in case it has a rain state call method to parse data for a rain
        if(!weatherData.getWeatherMain().equals("Rain")) return;
        getRainData(listItem, weatherData);
    }
}
