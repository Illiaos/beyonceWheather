package tests;

import city.City;
import manages.WeatherManager;
import org.junit.jupiter.api.Test;
import weather_description.WeatherData;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherMangerTest
{
    @Test
    void weatherManagerTest()
    {
        WeatherManager manager = new WeatherManager();
        City city = manager.requestWeather("London");
        //test city name parse, from the previous milestone
        assertEquals("London", city.getCityName());

        //get number of WeatherData elements, 8 elements per day
        assertEquals(40, city.getWeatherCondition().getSize());

        //get element by data
        assertNotNull(city.getWeatherCondition().getWeatherData("2024-04-22 18:00:00"));

        WeatherData data = city.getWeatherCondition().getWeatherData("2024-04-22 18:00:00");
        assertEquals(8.9, data.getGeneralTemperature());
        assertEquals("Clouds", data.getWeatherMain());
        assertEquals("broken clouds", data.getWeatherDescription());
    }
}
