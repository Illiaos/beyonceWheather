package clothes;

public enum ClotheType
{
    ClassicTrousers(0),
    Jeans(1),
    TShirt(2),
    Shirt(3),
    Jacket(4),
    Socks(5),
    Sweater(6),
    Hoodie(7),
    None(8);

    private final int index;
    private ClotheType(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }
}
