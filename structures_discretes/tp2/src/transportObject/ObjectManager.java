package transportObject;

import java.util.ArrayList;
import java.util.HashSet;

public class ObjectManager
{
    HashSet<TransportObjectA> containerA_;
    HashSet<TransportObjectB> containerB_;
    HashSet<TransportObjectC> containerC_;

    public ObjectManager()
    {
        containerA_ = new HashSet<>();
        containerB_ = new HashSet<>();
        containerC_ = new HashSet<>();
    }

    public void add(TransportObject toAdd)
    {
        switch (toAdd.type_)
        {
            case "A" :
                containerA_.add((TransportObjectA) toAdd);
                break;
            case "B" :
                containerB_.add((TransportObjectB) toAdd);
                break;
            case "C" :
                containerC_.add((TransportObjectC) toAdd);
                break;
        }
    }

    public<T extends TransportObject> HashSet<T> findByType(String type)
    {
        switch (type)
        {
            case "A" : return (HashSet<T>) containerA_;
            case "B" : return (HashSet<T>) containerB_;
            case "C" : return (HashSet<T>) containerC_;
            default  : return null;
        }
    }

    private static <T extends TransportObject> TransportObject searchInContainer(HashSet<T> container, String code)
    {
        for(T element: container)
            if(element.code_.equals(code))
                return element;
        return null;
    }

    public TransportObject findByCode(String code)
    {
        TransportObject found = searchInContainer(containerA_, code);
        if(found == null)
        {
            found = searchInContainer(containerB_, code);
            if(found == null)
                found = searchInContainer(containerC_, code);
        }
        return found;
    }



}
