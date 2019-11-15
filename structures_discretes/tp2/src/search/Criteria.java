package search;

public class Criteria
{
    private String type_;
    private String code_;
    private String name_;

    /**
     * Constructor of a criteria, entered by user for the search
     * @param name of the object searched for
     * @param code of the object searched for
     * @param type of the object searched for
     */
    public Criteria(String name, String code, String type)
    {
        name_ = name;
        code_ = code;
        type_ = type;
    }

    /**
     * Constructor of a criteria, entered by user for the search
     * @param list where index 0 is the name, index 1 is the code and index 2 is the type
     */
    public Criteria(String[] list)
    {
        name_ = list[0];
        code_ = list[1];
        type_ = list[2];
    }

    /**
     * Getter for the criteria's type
     * @return type of the current criteria
     */
    public String getType() { return type_; }

    /**
     * Getter for the criteria's code
     * @return code of the current criteria
     */
    public String getCode() { return code_; }

    /**
     * Getter for the criteria's name
     * @return name of the current criteria
     */
    public String getName() { return name_; }

    /**
     * Function to know if the criteria's type is set
     * @return true if set, false else
     */
    public boolean hasType() { return type_.equals("A") || type_.equals("B") || type_.equals("C"); }

    /**
     * Function to know if the criteria's name is set
     * @return true if set, false else
     */
    public boolean hasName() { return !name_.equals(""); }

    /**
     * Function to know if the criteria's code is set
     * @return true if set, false else
     */
    public boolean hasCode() { return !code_.equals(""); }

}
