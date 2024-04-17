package user_input;
import java.util.Date;

public class UserRequest {

    private Date startDate;
    private String[] requestCity;

    // default constructor; not finished
    private UserRequest() {

    }

    // constructor to initialize date and requested cities
    private UserRequest(Date date, String[] requestCity) {
        this.startDate = date;
        this.requestCity = requestCity;
    }

    // getter and setters for start date
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = date;
    }

    public String[] getRequestCity() {
        return this.requestCity;
    }
    // getter and setters for city
    public void setRequestCity(String[] city) {
        this.requestCity = city;
    }

}
