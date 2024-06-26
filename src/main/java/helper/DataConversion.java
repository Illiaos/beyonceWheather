package helper;

//class helper
public class DataConversion
{
    //convert double values to values, with passed values after dot
    public static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
