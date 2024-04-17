package weather_description;

public class Rain
{
    private String rainState;
    private String duration;
    private double amount;
    private  boolean isRaining;

    public Rain()
    {
        this.isRaining = false;
        this.duration = null;
        this.rainState = null;
    }

    public Rain(String rainState, String duration, double amount)
    {
        this.rainState = rainState;
        this.duration = duration;
        this.amount = amount;
        this.isRaining = true;
    }
}
