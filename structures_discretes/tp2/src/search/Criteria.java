package search;

import java.util.ArrayList;

public class Criteria
{
    private String type_;
    private String code_;
    private String name_;

    public Criteria(String type, String code, String name)
    {
        type_ = type;
        code_ = code;
        name_ = name;
    }

    public Criteria(String[] list)
    {
        type_ = list[0];
        code_ = list[1];
        name_ = list[2];
    }

    public String getType() { return type_; }

    public String getCode() { return code_; }

    public String getName() { return name_; }

}
