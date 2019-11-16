package transportObject;
import java.util.ArrayList;
import java.util.HashSet;

public class ObjectManager
{
    private HashSet<TransportObjectB> containerB_;
    private HashSet<TransportObjectC> containerC_;
    private HashSet<TransportObjectA> containerA_;

    /**
     * Constructor of an ObjectManager
     */
    public ObjectManager()
    {
        containerA_ = new HashSet<>();
        containerB_ = new HashSet<>();
        containerC_ = new HashSet<>();
    }

    /**
     * Function to get all the objects from all types
     * @return HashSet containing all the objects
     */
    public HashSet<TransportObject> getElements()
    {
        HashSet<TransportObject> contains = new HashSet<>();
        contains.addAll(containerA_);
        contains.addAll(containerB_);
        contains.addAll(containerC_);
        return contains;
    }

    /**
     * Function to know if the manager contains any element
     * @return true if all the containers are empty, else false
     */
    public boolean isEmpty() { return containerA_.isEmpty() && containerB_.isEmpty() && containerC_.isEmpty(); }

    /**
     * Function to get all the objects from all types in a list
     * @return List containing all the objects
     */
    public ArrayList<String> getElementsString()
    {
        HashSet<TransportObject> contains = getElements();
        ArrayList<String> list = new ArrayList<>();
        for(TransportObject element: contains)
            list.add(element.getName() + " " + element.getHashCode() + " " + element.getType());
        return list;
    }

    /**
     * Function to add an object to it's container
     * @param  toAdd object that should be added
     */
    public void add(TransportObject toAdd)
    {
        switch (toAdd.type_)
        {
            case "A" :
                containerA_.add(new TransportObjectA(toAdd));
                break;
            case "B" :
                containerB_.add(new TransportObjectB(toAdd));
                break;
            case "C" :
                containerC_.add(new TransportObjectC(toAdd));
                break;
        }
    }

    /**
     * Function to remove an object to it's container
     * @param  toRemove object that should be added
     */
    public void remove(TransportObject toRemove)
    {
        switch (toRemove.type_)
        {
            case "A" :
                for(TransportObjectA element: containerA_)
                    if(element.code_.equals(toRemove.code_)) {
                        containerA_.remove(element);
                        break;
                    }
                break;
            case "B" :
                for(TransportObjectB element: containerB_)
                    if(element.code_.equals(toRemove.code_)) {
                        containerA_.remove(element);
                        break;
                    }
                break;
            case "C" :
                for(TransportObjectC element: containerC_)
                    if(element.code_.equals(toRemove.code_)) {
                        containerA_.remove(element);
                        break;
                    }
                break;
        }
    }

    /**
     * Function to find objects using their type
     * @param  type that we are searching for
     * @return the container of the type we are searching for
     */
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

    /**
     * Function to find objects using their type inside a container
     * @param container to search inside
     * @param  type that we are searching for
     * @return the container of the type we are searching for
     */
    public HashSet<TransportObject> findByTypeInContainer(HashSet<TransportObject> container, String type)
    {
        HashSet<TransportObject> found = new HashSet<>();
        for(TransportObject element: container)
            if(element.type_.equals(type))
                found.add(element);
        return found;
    }

    /**
     * Function to search for an object with a certain code inside a container
     * @param container containerA, B or C to search inside
     * @param code unique hexadecimal code of the object
     * @return TransportObjectA, B or C
     */
    private static <T extends TransportObject> TransportObject findByCodeInContainerPrivate(HashSet<T> container, String code)
    {
        for(T element: container)
            if(element.code_.equals(code))
                return element;
        return null;
    }

    /**
     * Function to search a certain container and find all objects which code starts with a certain sequence
     * @param code beginning of the code's sequence
     * @return List containing all the found objects
     */
    public HashSet<TransportObject> findByCodeInContainer(HashSet<TransportObject> container, String code)
    {
        HashSet<TransportObject> found = new HashSet<>();
        for(TransportObject element: container)
            if(element.code_.substring(0, code.length()).equals(code))
                found.add(element);
        return found;
    }

    /**
     * Function to search all containers and find the object with a certain code
     * @param code unique hexadecimal code of the object we are searching for
     * @return object if found, else null
     */
    public TransportObject findByCode(String code)
    {
        TransportObject found = findByCodeInContainerPrivate(containerA_, code);
        if(found == null)
        {
            found = findByCodeInContainerPrivate(containerB_, code);
            if(found == null)
                found = findByCodeInContainerPrivate(containerC_, code);
        }
        return found;
    }

    /**
     * Function to search for objects with a name inside a container
     * @param container contains the objects to be searched
     * @param name name of the objects we are searching for
     * @return HashSet containing all the object with the required name
     */
    public static <T extends TransportObject> HashSet<TransportObject> findByNameInContainer(HashSet<T> container, String name)
    {
        HashSet<TransportObject> found = new HashSet<>();
        for(TransportObject element: container)
            if(element.name_.substring(0, name.length()).equals(name))
                found.add(element);
        return found;
    }

    /**
     * Function to search all containers and find the objects with a certain name
     * @param name name of the objects we are searching for
     * @return HashSet containing all the object with the required name
     */
    public HashSet<TransportObject> findByName(String name)
    {
        HashSet<TransportObject> found = new HashSet<>();
        found.addAll(findByNameInContainer(containerA_, name));
        found.addAll(findByNameInContainer(containerB_, name));
        found.addAll(findByNameInContainer(containerC_, name));
        return found;
    }

}
