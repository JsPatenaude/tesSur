package transportObject;

public abstract class TransportObject
{
    protected int weight_;
    protected String code_;
    protected String name_;
    protected String type_;

    public TransportObject(int weight, String name, String code, String type)
    {
        weight_ = weight;
        name_ = name;
        code_ = code;
        type_ = type;
    }

    /**
     * Getter for the object's weight
     * @return the object's weight
     */
    public int getWeight() { return weight_; }

    /**
     * Getter for the object's unique code
     * @return the object's unique code
     */
    public String getHashCode() { return code_; }


    /**
     * Getter for the object's type
     * @return the object's type
     */
    public String getType() { return type_; }

    /**
     * Getter for the object's name
     * @return the object's name
     */
    public String getName() { return name_; }
}
