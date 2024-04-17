package tests;

import helper.DataConversion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversionTest
{
    @Test
    void convertionDouble()
    {
        assertEquals(10.05, DataConversion.round(10.05345, 2));
        assertEquals(10.5546, DataConversion.round(10.554645, 4));
        assertEquals(15.1, DataConversion.round(15.05345, 1));
        assertEquals(13.123, DataConversion.round(13.123456, 3));
    }
}
