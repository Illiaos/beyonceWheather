package clothes;

public class ClotheElement
{
    private ClotheType type;
    private  String name;
    private String color;
    private  int quantity;

    public  ClotheElement()
    {

    }

    public ClotheElement(ClotheType type, String name, String color, int quantity)
    {
        this.type = type;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
    }

    public  void  setType(ClotheType type)
    {
        this.type  = type;
    }

    public ClotheType getType()
    {
        return this.type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return  this.color;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public String toString()
    {
        return
                "Type: " + type.toString() +
                " Name: " + name +
                " Color: " + color +
                " Quantity: " + quantity;
    }
}
