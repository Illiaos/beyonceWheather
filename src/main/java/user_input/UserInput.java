package user_input;

import clothes.ClotheElement;
import clothes.ClotheType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserInput
{
    private Scanner scanner;
    private ArrayList<ClotheElement> elements;

    public UserInput()
    {
        scanner = new Scanner(System.in);
        elements = new ArrayList<ClotheElement>();
    }

    public void manageInput()
    {
        while (true)
        {
            int value = drawMenu();
            switch (value)
            {
                case 1:
                {
                    addElement();
                    break;
                }
                case 2:
                {
                    showElements();
                    break;
                }
                case 3:
                {
                    requestWeather();
                    break;
                }
                case 4:
                {
                    return;
                }
            }
        }
    }

    private int drawMenu()
    {
        while (true)
        {
            System.out.println("===MENU===");
            System.out.println("1: Add clothe element");
            System.out.println("2: Show list of clothes");
            System.out.println("3: Request weather");
            System.out.println("4: Exit");

            System.out.println("Enter value: ");
            int request = scanner.nextInt();
            if(request <= 4 && request >=1) return request;
        }
    }

    private void addElement()
    {
        ClotheElement element = new ClotheElement();

        element.setType(getClotheType());
        element.setName(enterClotheName());
        element.setColor(getClotheColor());
        element.setQuantity(getClotheQuantity());
        elements.add(element);
    }

    private ClotheType getClotheType()
    {
        ClotheType end = ClotheType.None;
        while (true)
        {
            showClotheType();
            System.out.println("Select clothe type");
            int value = scanner.nextInt();
            if(value >= 1 && value <= end.getIndex() + 1)
            {
                return ClotheType.values()[value - 1];
            }
        }
    }

    private void showClotheType()
    {
        ClotheType end = ClotheType.None;
        System.out.println("Clothe Types");
        for(int i = 0; i != end.getIndex(); ++i)
        {
            System.out.println(i + 1 + ": " + ClotheType.values()[i]);
        }
    }

    private String enterClotheName()
    {
        System.out.println("Enter clothe name: ");
        return scanner.next();
    }

    private String getClotheColor()
    {
        System.out.println("Enter clothe color: ");
        return scanner.next();
    }

    private int getClotheQuantity()
    {
        System.out.println("Enter clothe quantity: ");
        return scanner.nextInt();
    }

    private void showElements()
    {
        for(ClotheElement item : elements)
        {
            System.out.println(item.toString());
        }
    }

    private void requestWeather()
    {
        UserRequest request = new UserRequest();
        request.setStartDate(requestDate());
        request.setCity(requestCity());
    }

    private Date requestDate()
    {
        while (true)
        {
            System.out.println("Enter Start Date in a format dd:mm:year");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
            dateFormat.setLenient(false);
            String inputDate = scanner.next();
            try
            {
                return dateFormat.parse(inputDate);
            }
            catch (ParseException e)
            {
                System.out.println("Wrong Input");
            }
        }
    }

}
