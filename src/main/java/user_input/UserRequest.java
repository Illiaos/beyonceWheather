package user_input;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserRequest {

    private Date startDate;
    private String[] requestCity;

    // constructor to initialize date and requested cities
    private UserRequest(Date date, String[] requestCity) {
        this.startDate = date;
        this.requestCity = requestCity;
    }

    // method to get start date from user input
    public Date getStartDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd: MM: yyyy"); //SimpleDateFormat object to parse dates
        dateFormat.setLenient(false);

        // loop until user gives a valid date
        while (true) {
            System.out.println("Enter Start Date in the format dd: mm: yyyy");
            String inputDate = scanner.nextLine();

            try {
                return dateFormat.parse(inputDate);
            } catch (ParseException e) {
                System.out.println("Wrong Input"); // handle parsing errors if the input date is invalid
            }
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = date;
    }

    public String[] getRequestCity() {
        return this.requestCity;
    }
    // method to set request city array based on user input
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
