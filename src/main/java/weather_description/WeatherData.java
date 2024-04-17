package weather_description;

import helper.DataConversion;

//description of a weather for a city
public class WeatherData
{
    //const value to convert temperature to C
    private final double TEMP_CONVERSION = 273.15;
    private double minTemperature;
    private double maxTemperature;
    private   double generalTemperature;
    private Rain rain;
    private String weatherDescription;
    private String weatherMain;

    //default constructor
    public WeatherData() {}

    //getter and setters for private values
    public void setMinTemperature(double value)
    {
        this.minTemperature = value;
    }

    public  double getMinTemperature()
    {
        //conversion temperature to C and convert to double value of format 0.00
        return  DataConversion.round(minTemperature - TEMP_CONVERSION, 2);
    }

    public void setMaxTemperature(double value)
    {
        this.maxTemperature = value;
    }

    public double getMaxTemperature()
    {
        //conversion temperature to C and convert to double value of format 0.00
        return  DataConversion.round(maxTemperature - TEMP_CONVERSION, 2);
    }

    public void setGeneralTemperature(double value)
    {
        this.generalTemperature = value;
    }

    public double getGeneralTemperature()
    {
        //conversion temperature to C and convert to double value of format 0.00
        return DataConversion.round(generalTemperature - TEMP_CONVERSION, 1);
    }

    public void setRain(Rain rain)
    {
        this.rain = rain;
    }

    public Rain getRain()
    {
        return  this.rain;
    }

    public void setWeatherDescription(String description)
    {
        this.weatherDescription = description;
    }

    public String getWeatherDescription()
    {
        return this.weatherDescription;
    }

    public void setWeatherMain(String weatherMain)
    {
        this.weatherMain = weatherMain;
    }

    public String getWeatherMain()
    {
        return this.weatherMain;
    }
}
