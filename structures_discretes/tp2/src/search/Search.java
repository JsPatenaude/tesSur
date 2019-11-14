package search;

import transportObject.ObjectManager;
import transportObject.TransportObject;

import java.util.HashSet;

public class Search
{
    private ObjectManager inventory_;
    private HashSet<TransportObject> found_;

    public Search(ObjectManager inventory)
    {
        inventory_ = inventory;
        found_ = new HashSet<>();
    }

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

    public HashSet<TransportObject> getResults() { return found_; }

    public void printResults()
    {
        System.out.println("There are " + found_.size() + " objects that fit the requirements:");
        for(TransportObject element: found_)
            System.out.println("   " );
    }
}
