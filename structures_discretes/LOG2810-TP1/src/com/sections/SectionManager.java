package com.sections;

import java.util.HashSet;
import java.util.Set;


public class SectionManager {

    private HashSet<Section> listOfSections_ = new HashSet<Section>();
    private int numberOfElements_ = 0;

    public int getNumberOfElements_() { return  numberOfElements_; }

    public Section contains(Section aSection)
    {
        for(Section element: listOfSections_)
            if(element.equals(aSection))
                return element;
        return null;
    }

    public boolean hasBeenVisited(Section aSection)
    {
        Section foundSection = contains(aSection);
        return foundSection != null && foundSection.hasBeenVisited();
    }
}
