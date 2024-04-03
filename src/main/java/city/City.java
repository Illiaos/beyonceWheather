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
    public ArrayList<WeatherClouds> weatherClouds;
    public ArrayList<WeatherCondition> weatherCondition;
    public ArrayList<WeatherRain> weatherRain;
    public ArrayList<WeatherTemperature> weatherTemperature;
    public ArrayList<WeatherWind> weatherWind;

    public City()
    {
        weatherClouds = new ArrayList<WeatherClouds>();
        weatherCondition = new ArrayList<WeatherCondition>();
        weatherRain = new ArrayList<WeatherRain>();
        weatherTemperature = new ArrayList<WeatherTemperature>();
        weatherWind = new ArrayList<WeatherWind>();
    }

    public double getTemperature()
    {
        if(weatherTemperature.isEmpty() == true) return  0.0f;
        return weatherTemperature.get(0).temperature;
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
