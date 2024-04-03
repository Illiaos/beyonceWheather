package tests;

import manages.WeatherManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherManagerTest
{
    WeatherManager wm = new WeatherManager();

    @Test
    public  void getTemperature()
    {
        assertNull(wm.getCity());
        wm.requestWeather("London");
        assertEquals("London", wm.getCity().getCityName());
        assertEquals("GB", wm.getCity().getCountry());
        assertEquals(285.73, wm.getCity().getTemperature());
        assertEquals(284.68, wm.getCity().getMinTemperature());
    }
}