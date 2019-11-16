package search;

import transportObject.ObjectManager;
import transportObject.TransportObject;
import java.util.HashSet;

public class Search
{
    private ObjectManager inventory_;
    private HashSet<TransportObject> found_;

    /**
     * Constructor of a search
     * @param inventory manager of the objects, to find objects
     */
    public Search(ObjectManager inventory)
    {
        inventory_ = inventory;
        found_ = new HashSet<>();
    }

    /**
     * Function to change a search's ObjectManager
     * @param newManager new manager of the objects to replace the old one
     */
    public void changeManager(ObjectManager newManager) { inventory_ = newManager; }

    /**
     * Function returns if an object exists depending on the criteria
     * @param criteria for the object's search
     * @return true if the object exists, else false
     */
    public boolean exists(Criteria criteria)
    {
        found_ = new HashSet<>();
        if(criteria.getCode().length() < 5)
        {
            if(criteria.hasCode())
            {
                found_.addAll(inventory_.findByCodeInContainer(inventory_.getElements(), criteria.getCode()));
                if (criteria.hasName())
                    found_ = inventory_.findByNameInContainer(found_, criteria.getName());
                if(criteria.hasType())
                    found_ = inventory_.findByTypeInContainer(found_, criteria.getType());
            }
            else
            {
                if(criteria.hasName())
                {
                    found_.addAll(inventory_.findByNameInContainer(inventory_.getElements(), criteria.getName()));
                    if(criteria.hasType())
                        found_ = inventory_.findByTypeInContainer(found_, criteria.getType());
                }
                else
                    found_.addAll(inventory_.findByTypeInContainer(inventory_.getElements(), criteria.getType()));
            }
        }
        else
            found_.add(inventory_.findByCode(criteria.getCode()));
        return !found_.isEmpty();
    }

    /**
     * Function to get the result of the previous search (using exists)
     * @return HashSet containing all the objects that fit the criteria of the previous exists
     */
    public HashSet<TransportObject> getResults() { return found_; }

    /**
     * Function to print the results in the console
     */
    public void printResults()
    {
        System.out.println(found_.size() + " object" + ((found_.size() > 1) ? "s ":" ") + "fit the requirements:");
        for(TransportObject element: found_)
            System.out.println("   " + element.getName() + " " + element.getHashCode() + " " + element.getType());
    }
}
