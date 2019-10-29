package com.route;

import com.sections.Section;
import com.transportObject.TransportObject;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class RouteAlgorithm {

    HashSet<Section> sections;
    int numberA = 0;
    int numberB = 0;
    int numberC = 0;

    public RouteAlgorithm(HashSet<Section> sectionsFromFile, int numberOfA, int numberOfB, int numberOfC)
    {
        sections = sectionsFromFile;
        numberA = numberOfA;
        numberB = numberOfB;
        numberC = numberOfC;
    }

    public boolean displayBestRoute()
    {
        System.out.println();


        int time = 0;
        System.out.println("Temps: " + time);
        System.out.println("Robot utilisé: " + findRobotType());
        return true;
    }

    /**
     * Finds the a section in the section HashSet
     * @param sectionNumber section number to be found
     * @return The section requested
     */
    private Section findSection(int sectionNumber)
    {
        for(Section element: sections)
            if(element.getSectionNumber_().equals(sectionNumber))
                return element;
        return null;
    }

    private void findAllPath()
    {
        Section root =  findSection(0);
        if(root != null)
        {
            HashMap<Integer, Integer> neighbors = root.getDistances();
            Iterator it = neighbors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                it.remove(); // avoids a ConcurrentModificationException

                System.out.println(pair.getKey() + " = " + pair.getValue());
            }
        }
    }

    private String findRobotType()
    {
        int totalWeight = numberA * TransportObjectA.getWeight() + numberB * TransportObjectB.getWeight()
                + numberC * TransportObjectC.getWeight();
        if(totalWeight > TransportObjectA.getWeight() && totalWeight > TransportObjectB.getWeight())
        {
            if(totalWeight > TransportObjectC.getWeight())
                return "No robot can carry that much weight: " + totalWeight + "kg.";
            return "Only Robot C can carry " + totalWeight + "kg.";
        }


        return "";
    }
}
