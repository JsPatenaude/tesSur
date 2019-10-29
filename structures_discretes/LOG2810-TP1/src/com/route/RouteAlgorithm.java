package com.route;

import com.file.ReadFileLogic;
import com.sections.Section;
import com.transportObject.TransportObject;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class RouteAlgorithm
{
    private HashSet<Section> sections;
    private int numberA;
    private int numberB;
    private int numberC;

    public RouteAlgorithm(HashSet<Section> sectionsFromFile, int numberOfA, int numberOfB, int numberOfC)
    {
        sections = sectionsFromFile;
        numberA = numberOfA;
        numberB = numberOfB;
        numberC = numberOfC;
        AllPath g = new AllPath(sections);
        System.out.println("Following are all different paths from 2 to 3");
        g.printAllPaths(2,3);
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
        Dijkstra algorithm = new Dijkstra(sections);
        //for()
    }

    private void removeSection(int toRemove)
    {
        for(Section element : sections)
        {
            if(element.getSectionNumber_() == toRemove)
                sections.remove(element);
            if(element.getDistances().containsKey(toRemove))
                element.getDistances().remove(toRemove);
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
