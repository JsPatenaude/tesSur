package transportObject;

public class TransportObjectC extends TransportObject
{
    public static final int weight = 5;

    public TransportObjectC(String name, String code, String type)
    {
        super(weight, name, code, type);
    }

    public TransportObjectC(TransportObject object)
    {
        super(weight, object.getName(), object.getHashCode(), object.getType());
    }
}