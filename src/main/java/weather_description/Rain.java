package weather_description;

//object class for rain description
public class Rain
{
    private String duration;
    private double amount;
    private  boolean isRaining;

    //default constructor
    public Rain()
    {
        this.isRaining = false;
        this.duration = null;
        this.amount = 0.0;
    }

    //constructor with duration and amount
    public Rain(String duration, double amount)
    {
        this.duration = duration;
        this.amount = amount;
        this.isRaining = true;
    }

    //getter and setter for private values
    public String getDuration()
    {
        return  this.duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public boolean getIsRaining()
    {
        return this.isRaining;
    }

    public void setIsRaining(boolean isRaining)
    {
        this.isRaining = isRaining;
    }
}
