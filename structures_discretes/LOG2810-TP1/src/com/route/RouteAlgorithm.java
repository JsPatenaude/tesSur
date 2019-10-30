package com.route;

import com.file.ReadFileLogic;
import com.file.ReadPaths;
import com.menu.Order;
import com.sections.Section;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.io.PrintStream;
import java.util.*;

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
        getBestRoute();
    }

    public boolean displayBestRoute()
    {
        System.out.println();
        getBestRoute();

        int time = 0;
        System.out.println("Temps: " + time);
        System.out.println("Robot utilisé: " + findRobotType());
        return true;
    }

    private void getBestRoute()
    {
        HashSet<LinkedHashMap<Integer, Section>> allPaths = findAllPath();
        HashSet<LinkedHashMap<Integer, Section>> modifiedPath = new HashSet<LinkedHashMap<Integer, Section>>();
        for(LinkedHashMap<Integer, Section> aPath : allPaths)
            removePathWithNotEnoughItems(aPath, modifiedPath);
        LinkedHashMap<Integer, Section> bestPath = fromModifiedPathFindShortest(modifiedPath);
        if(!bestPath.isEmpty())
            System.out.println(printPath(bestPath));
        else
            System.out.println("There's no existing path!");
    }

    private String printPath(LinkedHashMap<Integer, Section> bestPath)
    {
        //for(int i = 0; i < bestPath.size(); i++)
        return "";
    }

    private LinkedHashMap<Integer, Section> fromModifiedPathFindShortest(HashSet<LinkedHashMap<Integer, Section>> modifiedPath)
    {
        int minLength = (int)Double.POSITIVE_INFINITY;
        LinkedHashMap<Integer, Section> minPath = new LinkedHashMap<>();
        for(LinkedHashMap<Integer, Section> aPath : modifiedPath)
            if (minLength > getLength(aPath))
                minPath = aPath;
        return minPath;
    }

    private int getLength(LinkedHashMap<Integer, Section> aPath)
    {
        int length = 0;
        for(int i = 0; i < aPath.size() - 1; i++)
            length += aPath.get(i).getDistance(aPath.get(i+1).getSectionNumber_());
        return length;
    }

    private void removePathWithNotEnoughItems(LinkedHashMap<Integer, Section> aPath,
                                              HashSet<LinkedHashMap<Integer, Section>> modifiedPaths)
    {
        int pathNumberOfA = 0, pathNumberOfB = 0, pathNumberOfC = 0;
        Section currentSection;
        for(int i = 0; i < aPath.size(); i++)
        {
            currentSection = aPath.get(i);
            pathNumberOfA += currentSection.getNumberOfObjectsA();
            pathNumberOfB += currentSection.getNumberOfObjectsB();
            pathNumberOfC += currentSection.getNumberOfObjectsC();
        }
        if(pathNumberOfA >= numberA && pathNumberOfB >= numberB && pathNumberOfC >= numberC)
            modifiedPaths.add(aPath);
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

    private HashSet<LinkedHashMap<Integer, Section>> findAllPath()
    {
        PrintStream console = System.out; // Store current stream in console
        AllPath path = new AllPath(sections);
        Section root = findSection(0);
        Iterator it = root.getDistances().entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            it.remove(); // avoids a ConcurrentModificationException
            path.printAllPaths(0, (Integer) pair.getKey());
        }
        System.setOut(console); // Restore current stream
        ReadPaths pathReader = new ReadPaths(sections);
        return pathReader.getAllPath();
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

    public static void main(String[] args) {
        ReadFileLogic readFile = new ReadFileLogic();
        ReadPaths read = new ReadPaths(readFile.getSectionsInFile());
        Order ord = new Order();
        ord.takeOrder("Object A", 2);
        ord.takeOrder("Object B", 2);
        ord.takeOrder("Object C", 5);
        RouteAlgorithm route = new RouteAlgorithm(readFile.getSectionsInFile(), ord.getNumberOfA(),
                ord.getNumberOfB(), ord.getNumberOfC());
    }
}
