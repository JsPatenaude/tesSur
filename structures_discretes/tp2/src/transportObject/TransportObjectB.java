package transportObject;

public class TransportObjectB extends TransportObject
{
    public static final int weight = 3;

    public TransportObjectB(String name, String code, String type)
    {
        super(weight, name, code, type);
    }

    public TransportObjectB(TransportObject object)
    {
        super(weight, object.getName(), object.getHashCode(), object.getType());
    }
}