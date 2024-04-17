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
    // Method to set request city array based on user input
    public void setRequestCity() {
        Scanner scanner = new Scanner(System.in);
        String[] cityCollection = new String[3];

        System.out.println("Enter city names");
        for (int i = 0; i < 3; ++i) {
            while (true) {
                String input = scanner.next();
                if (input.matches("[a-zA-Z]+")) {
                    cityCollection[i] = input;
                    break;
                }
            }
        }
        this.requestCity = cityCollection;
    }

}
