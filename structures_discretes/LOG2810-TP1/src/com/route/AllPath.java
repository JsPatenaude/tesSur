package com.route;

// Re adapted from https://www.geeksforgeeks.org/find-paths-given-source-destination/

import com.sections.Section;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class AllPath
{
    private int numberOfVertices;
    private ArrayList<Integer>[] adjacencyList;

    /**
     * Constructor, converts a section to it's own definition of graph
     * @param sections sections container to be written in the graph
     */
    public AllPath(HashSet<Section> sections)
    {
        numberOfVertices = sections.size();
        initAdjList();
        HashSet<Section> copy = new HashSet<>(sections);
        for(Section element: copy) // For each section add it's edges
        {
            HashMap<Integer, Integer> neighbors = element.getDistances();
            Iterator it = neighbors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                it.remove(); // avoids a ConcurrentModificationException
                addEdge(element.getSectionNumber_(), (Integer) pair.getKey());
            }
        }
        try {
            PrintStream o = new PrintStream(new File("AllPath.txt"));
            System.setOut(o);
        } catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Function that initializez the adjacency list.
     */
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjacencyList = new ArrayList[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++)
            adjacencyList[i] = new ArrayList<>();
    }

    /**
     * Function to add an edge, to the adjacency list (from a vertex u to a vertex v)
     * @param origin origin vertex
     * @param destination destination vertex
     */
    public void addEdge(int origin, int destination) { adjacencyList[origin].add(destination); }

    /**
     * Function that prints all paths between 2 vertices
     * @param origin origin vertex
     * @param destination destination vertex
     */
    public void printAllPaths(int origin, int destination)
    {
        boolean[] isVisited = new boolean[numberOfVertices];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(origin);
        printAllPathsUtil(origin, destination, isVisited, pathList);
    }

    /**
     * Function that prints all paths between 2 vertices
     * @param origin origin vertex
     * @param destination destination vertex
     * @param isVisited boolean indicating if a vertex has been visited or not
     * @param localPathList the current path list being used
     */
    private void printAllPathsUtil(Integer origin, Integer destination, boolean[] isVisited, List<Integer> localPathList)
    {
        isVisited[origin] = true;
        if (origin.equals(destination))
        {
            System.out.println(localPathList);
            isVisited[origin]= false;
            return ;
        }
        for (Integer i : adjacencyList[origin])
        {
            if (!isVisited[i])
            {
                localPathList.add(i);
                printAllPathsUtil(i, destination, isVisited, localPathList);
                localPathList.remove(i);
            }
        }
        isVisited[origin] = false;
    }
} 