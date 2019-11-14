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
     * Function returns if an object exists depending on the criteria
     * @param criteria for the object's search
     * @return true if the object exists, else false
     */
    public boolean exists(Criteria criteria)
    {
        if(criteria.getCode().length() < 5)
        {
            if(criteria.getCode() != null)
            {
                found_.addAll(inventory_.findByCodeInContainer(inventory_.getElements(), criteria.getCode()));
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
        System.out.println("There are " + found_.size() + " objects that fit the requirements:");
        for(TransportObject element: found_)
            System.out.println("   " );
    }
}
