package com.route;

import com.file.ReadFileLogic;
import com.file.ReadPaths;
import com.menu.Order;
import com.robot.Robot;
import com.robot.RobotX;
import com.robot.RobotY;
import com.robot.RobotZ;
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
        displayBestRoute();
    }

    public boolean displayBestRoute()
    {
        System.out.println();
        try {
            LinkedHashMap<Integer, Section> bestPath = getBestRoute();
            LinkedHashMap<Integer, Order> pickUps = findHowToPickObjects(bestPath);
            System.out.println(bestPath);
            System.out.println("Robot utilisé: ");
            int time = findRobotType(pickUps, bestPath);
            System.out.println("Temps: " + time);
            return true;
        } catch (NoSuchElementException error) { return false; }
    }

    private LinkedHashMap<Integer, Order> findHowToPickObjects(LinkedHashMap<Integer, Section> path)
    {
        int pathNumberOfA = 0, pathNumberOfB = 0, pathNumberOfC = 0;
        Section currentSection;
        Order currentOrder;
        String result = "";
        int howMuch;
        LinkedHashMap<Integer, Order> fromEachSection = new LinkedHashMap<>();
        for(int pick = path.size() - 1; pick >= 0; pick--)
        {
            currentSection = path.get(pick);
            currentOrder = new Order();
            result += "From " + currentSection.getSectionNumber_();
            if(pathNumberOfA < numberA && !currentSection.hasBeenVisited())
            {
                howMuch = findHowMuchFrom(currentSection.getNumberOfObjectsA(), pathNumberOfA, numberA);
                pathNumberOfA += howMuch;
                currentOrder.takeOrder("Object A", howMuch);
                result += " A: " + howMuch;
            }
            else result += " A: 0";
            if(pathNumberOfB < numberB && !currentSection.hasBeenVisited())
            {
                howMuch = findHowMuchFrom(currentSection.getNumberOfObjectsB(), pathNumberOfB, numberB);
                pathNumberOfB += howMuch;
                currentOrder.takeOrder("Object B", howMuch);
                result += ", B: " + howMuch;
            }
            else result += ", B: 0";
            if(pathNumberOfC < numberC && !currentSection.hasBeenVisited())
            {
                howMuch = findHowMuchFrom(currentSection.getNumberOfObjectsC(), pathNumberOfC, numberC);
                pathNumberOfC += howMuch;
                currentOrder.takeOrder("Object C", howMuch);
                result += ", C: " + howMuch;
            }
            else result += ", C: 0";
            result += "\n";
            fromEachSection.put(pick, currentOrder);
            currentSection.visit();
        }
        System.out.println(result);
        return fromEachSection;
    }

    private int findHowMuchFrom(int contains, int current, int limit)
    {
        if(contains + current <= limit)
            return contains;
        else
            return limit - current;
    }

    private boolean pathIsSymmetric(LinkedHashMap<Integer, Section> path)
    {
        String path1 = "", path2 = "";
        for(int i = 0; i < path.size(); i++)
        {
            if(i <= path.size() / 2)
                path1 += path.get(i).getSectionNumber_();
            else
                path2 += path.get(i).getSectionNumber_();
        }
        return path1.equals(path2);
    }

    private LinkedHashMap<Integer, Section> getBestRoute()
    {
        HashSet<LinkedHashMap<Integer, Section>> allPaths = findAllPath();
        HashSet<LinkedHashMap<Integer, Section>> modifiedPath = new HashSet<LinkedHashMap<Integer, Section>>();
        for(LinkedHashMap<Integer, Section> aPath : allPaths)
            removePathWithNotEnoughItems(aPath, modifiedPath); // Not working
        LinkedHashMap<Integer, Section> bestPath = fromModifiedPathFindShortest(modifiedPath);
        if(!bestPath.isEmpty())
            System.out.println("Best path is: "+ printPath(bestPath));
        else
        {
            System.out.println("There's no existing path!");
            throw new NoSuchElementException();
        }
        return bestPath;
    }

    private String printPath(LinkedHashMap<Integer, Section> bestPath)
    {
        String path = "[";
        for(int i = 0; i < bestPath.size(); i++)
            path += bestPath.get(i).getSectionNumber_() + ", ";
        StringBuilder builder = new StringBuilder(path);
        builder.setCharAt(path.length() - 2, ']');
        path = builder.toString();
        return path;
    }

    private LinkedHashMap<Integer, Section> fromModifiedPathFindShortest(HashSet<LinkedHashMap<Integer, Section>> modifiedPath)
    {
        int minLength = (int)Double.POSITIVE_INFINITY, currentPathLength = (int)Double.POSITIVE_INFINITY;
        LinkedHashMap<Integer, Section> minPath = new LinkedHashMap<>();
        for(LinkedHashMap<Integer, Section> aPath : modifiedPath)
        {
            currentPathLength = getLength(aPath, minLength);
            if (minLength > currentPathLength)
            {
                minPath = aPath;
                minLength = currentPathLength;
            }
        }
        return minPath;
    }

    private int getLength(LinkedHashMap<Integer, Section> aPath, int minLength)
    {
        int length = 0;
        for(int i = 0; i < aPath.size() - 1; i++)
        {
            length += aPath.get(i).getDistance(aPath.get(i + 1).getSectionNumber_());
            if(length > minLength)
                break;
        }
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
            if(currentSection.hasBeenVisited())
            {
                pathNumberOfA += currentSection.getNumberOfObjectsA();
                pathNumberOfB += currentSection.getNumberOfObjectsB();
                pathNumberOfC += currentSection.getNumberOfObjectsC();
            }
            currentSection.visit();
        }
        resetVisited();
        if(pathNumberOfA >= numberA && pathNumberOfB >= numberB && pathNumberOfC >= numberC)
            modifiedPaths.add(aPath);
    }

    private void resetVisited()
    {
        for(Section element : sections)
            element.unVisit();
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

    private int findRobotType(LinkedHashMap<Integer, Order> pickUps, LinkedHashMap<Integer, Section> bestPath)
    {
        int totalWeight = numberA * TransportObjectA.weight + numberB * TransportObjectB.weight
                + numberC * TransportObjectC.weight;
        if(totalWeight > RobotX.getMaxWeight() && totalWeight > RobotY.getMaxWeight())
        {
            if(totalWeight > RobotZ.getMaxWeight())
                System.out.println("No robot can carry that much weight: " + totalWeight + "kg.");
            else
            {
                System.out.println("Only Robot C can carry " + totalWeight + "kg.");

            }
        }
        List<Robot> robots = new ArrayList<>();
        robots.add(new RobotX());
        robots.add(new RobotY());
        robots.add(new RobotZ());
        Robot bestRobot = robots.get(0);
        int bestTime = (int)Double.POSITIVE_INFINITY;
        int currentTime = 0;
        Order order = new Order();
        order.takeOrder("Object A", numberA);
        order.takeOrder("Object B", numberB);
        order.takeOrder("Object C", numberC);
        for(Robot aRobot : robots)
        {
            currentTime = aRobot.findPathTime(pickUps, bestPath, order);
            if(currentTime < bestTime)
            {
                bestTime = currentTime;
                bestRobot = aRobot;
            }
        }
        System.out.println(bestRobot.getName());
        return bestTime;
    }

    public static void main(String[] args) {
        ReadFileLogic readFile = new ReadFileLogic();
        ReadPaths read = new ReadPaths(readFile.getSectionsInFile());
        Order ord = new Order();
        ord.takeOrder("Object A", 1);
        ord.takeOrder("Object B", 1);
        ord.takeOrder("Object C", 1);
        RouteAlgorithm route = new RouteAlgorithm(readFile.getSectionsInFile(), ord.getNumberOfA(),
                ord.getNumberOfB(), ord.getNumberOfC());
    }
}
