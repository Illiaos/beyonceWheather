package tests;

import clothes.ClotheElement;
import clothes.ClotheType;
import org.junit.jupiter.api.Test;
import user_input.UserInput;

import static org.junit.jupiter.api.Assertions.*;
class UserInputTest
{
    UserInput userInput = new UserInput();

    @Test
    void createClotheElement()
    {
        generateElement(ClotheType.Jeans, 2, "CK", "Red");
        generateElement(ClotheType.Shirt, 10, "LV", "Black");

        //test correctness of size
        assertEquals(2, userInput.getSizeOfElements());
        //test if passed index that is higher than current size will return null
        assertNull(userInput.getByIndex(userInput.getSizeOfElements() + 10));

        //check correctness of adding element
        assertEquals(ClotheType.Shirt, userInput.getByIndex(1).getType());
        assertEquals("Red", userInput.getByIndex(0).getColor());
        assertEquals(10, userInput.getByIndex(1).getQuantity());
        assertEquals(2, userInput.getByIndex(0).getQuantity());
    }

    private void generateElement(ClotheType type, int quantity, String name, String color)
    {
        ClotheElement clotheElement = new ClotheElement();
        clotheElement.setType(type);
        clotheElement.setQuantity(quantity);
        clotheElement.setName(name);
        clotheElement.setColor(color);
        userInput.addElementToCollection(clotheElement);
    }
}