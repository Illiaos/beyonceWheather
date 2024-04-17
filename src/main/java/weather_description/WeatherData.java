package weather_description;

import helper.DataConversion;

public class WeatherData
{
    private final double TEMP_CONVERSION = 273.15;
    public double minTemperature;
    public double maxTemperature;
    public  double generalTemperature;
    private Rain rain;

    public  double getMinTemperature()
    {
        return  (minTemperature - TEMP_CONVERSION);
    }

    public double getMaxTemperature()
    {
        return  (maxTemperature - TEMP_CONVERSION);
    }

    public double getGeneralTemperature()
    {
        return DataConversion.round(generalTemperature - TEMP_CONVERSION, 2);
    }

    public void setRain(Rain rain)
    {
        this.rain = rain;
    }

    public Rain getRain()
    {
        return  this.rain;
    }
}
