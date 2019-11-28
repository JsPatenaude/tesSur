package search;

import file.Automate;
import transportObject.ObjectManager;
import transportObject.TransportObject;
import java.util.HashSet;

public class Search
{
    private ObjectManager inventory_;
    private HashSet<TransportObject> found_;
    private Automate automateNames_;
    private Automate automateCodes_;

    /**
     * Constructor of a search
     * @param inventory manager of the objects, to find objects
     */
    public Search(ObjectManager inventory, Automate names, Automate codes)
    {
        inventory_ = inventory;
        found_ = new HashSet<>();
        automateNames_ = names;
        automateCodes_ = codes;
    }

    /**
     * Function returns if an object exists depending on the criteria
     * @param criteria for the object's search
     * @return true if the object exists, else false
     */
    public boolean exists(Criteria criteria)
    {
        found_ = new HashSet<>();
        Automate foundAuto;
        if(criteria.getCode().length() < 5)
        {
            if(criteria.hasCode())
            {
                foundAuto = automateCodes_.getNodeByName(criteria.getCode());
                found_ = foundAuto.getObjects();
                if (criteria.hasName()) {
                    foundAuto = automateNames_.getNodeByName(foundAuto, criteria.getName());
                    found_ = foundAuto.getObjects();
                }
                    //found_ = inventory_.findByNameInContainer(found_, criteria.getName());
                if(criteria.hasType())
                    found_ = inventory_.findByTypeInContainer(found_, criteria.getType());
            }
            else
            {
                if(criteria.hasName())
                {
                    //found_.addAll(inventory_.findByNameInContainer(inventory_.getElements(), criteria.getName()));
                    found_.addAll(automateNames_.getNodeByName(criteria.getName()).getObjects());
                    if(criteria.hasType())
                        found_ = inventory_.findByTypeInContainer(found_, criteria.getType());
                }
                else
                    found_.addAll(inventory_.findByTypeInContainer(inventory_.getElements(), criteria.getType()));
            }
        }
        else {
            found_.add(automateCodes_.getNodeByName(automateCodes_, criteria.getCode()).getObjects().iterator().next());
            System.out.println("I am here im working1!!!");
        }
        return !found_.isEmpty();
    }

    /**
     * Function to get the result of the previous search (using exists)
     * @return HashSet containing all the objects that fit the criteria of the previous exists
     */
    public HashSet<TransportObject> getResults() { return found_; }
}
