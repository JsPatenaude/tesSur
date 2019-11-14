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
        if(criteria.multipleObjectsCanMatch())
        {
            if(criteria.getType() != null)
                found_.addAll(inventory_.findByType(criteria.getType()));
            if(criteria.getName() != null)
                found_.addAll(inventory_.findByNameInContainer(found_, criteria.getName()));
        }
        else
            found_.add(inventory_.findByCode(criteria.getCode()));
        return !found_.isEmpty();
    }

    public HashSet<TransportObject> getResults() { return found_; }
}
