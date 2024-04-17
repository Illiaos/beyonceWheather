package user_input;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserRequest {

    private Date startDate;
    private String[] requestCity;

    // constructor to initialize date and requested cities
    public UserRequest(Date date, String[] requestCity) {
        this.startDate = date;
        this.requestCity = requestCity;
    }

    public UserRequest() { }

    // method to get start date from user input
    public Date getStartDate()
    {
        return this.startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public String[] getRequestCity()
    {
        return this.requestCity;
    }

    // method to set request city array based on user input
    public void setRequestCity(String[] requestCity)
    {
        this.requestCity = requestCity;
    }

}
