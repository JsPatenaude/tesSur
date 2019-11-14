package transportObject;

public class TransportObjectA extends TransportObject
{
    public static final int weight = 1;

    public TransportObjectA(String name, String code, String type)
    {
        super(weight, name, code, type);
    }

    public TransportObjectA(TransportObject object)
    {
        super(weight, object.getName(), object.getHashCode(), object.getType());
    }
}