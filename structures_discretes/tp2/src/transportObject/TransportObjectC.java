package transportObject;

public class TransportObjectC extends TransportObject
{
    public static final int weight = 5;
    public static final String type = "C";

    public TransportObjectC(String name, String code)
    {
        super(weight, name, code, type);
    }

    /**
     * Constructor of a transport object type B from a normal Transport Object
     * @param object to construct from
     */
    public TransportObjectC(TransportObject object)
    {
        super(weight, object.getName(), object.getHashCode(), object.getType());
    }
}