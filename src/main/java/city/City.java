package city;

import weather_description.*;

import java.util.ArrayList;

public class City
{
    public double id;
    public String name;
    public String country;
    public double population;
    public double timeZone;
    public double sunrise;
    public double sunset;

    private WeatherCondition weatherCondition;
    public City()
    {
        weatherCondition = new WeatherCondition();
    }

    public double getTemperature()
    {
        return 0.0f;
    }

    public boolean addWeatherCondition(String date, WeatherData weatherData)
    {
        weatherCondition.addData(date, weatherData);
        return true;
    }

    public double getMinTemperature()
    {
        if(weatherTemperature.isEmpty() == true) return  0.0f;
        return weatherTemperature.get(0).temperatureMin;
    }

    public double getMaxTemperature()
    {
        if(weatherTemperature.isEmpty() == true) return  0.0f;
        return weatherTemperature.get(0).temperatureMax;
    }

    public String getCityName()
    {
        return this.name;
    }

    public String getCountry()
    {
        return this.country;
    }

    public String toString()
    {
        return  "ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Country: " + country + "\n"
                + "Population: " + population + "\n"
                + "timeZone: " + timeZone + "\n"
                + "Rise: " + sunrise + "\n"
                + "Set: " + sunset;
    }

}
