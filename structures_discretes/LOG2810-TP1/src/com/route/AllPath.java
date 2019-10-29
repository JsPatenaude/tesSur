package com.route;

// Re adapted from https://www.geeksforgeeks.org/find-paths-given-source-destination/

import com.sections.Section;

import java.util.*;

public class AllPath
{
    private int numberOfVertices;
    private ArrayList<Integer>[] adjacencyList;

    //Constructor
    public AllPath(HashSet<Section> sections)
    {
        numberOfVertices = sections.size();
        initAdjList();

        for(Section element: sections) // For each section add it's edges
        {
            HashMap<Integer, Integer> neighbors = element.getDistances();
            Iterator it = neighbors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                it.remove(); // avoids a ConcurrentModificationException
                addEdge(element.getSectionNumber_(), (Integer) pair.getKey());
            }
        }
    }

    // utility method to initialise
    // adjacency list
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjacencyList = new ArrayList[numberOfVertices];
        for(int i = 0; i < numberOfVertices; i++)
            adjacencyList[i] = new ArrayList<>();
    }

    // add edge from u to v
    public void addEdge(int u, int v) { adjacencyList[u].add(v); }

    // Prints all paths from
    // 's' to 'd'
    public void printAllPaths(int s, int d)
    {
        boolean[] isVisited = new boolean[numberOfVertices];
        ArrayList<Integer> pathList = new ArrayList<>();

        //add source to path[] 
        pathList.add(s);

        //Call recursive utility 
        printAllPathsUtil(s, d, isVisited, pathList);
    }

    // A recursive function to print 
    // all paths from 'u' to 'd'. 
    // isVisited[] keeps track of 
    // vertices in current path. 
    // localPathList<> stores actual 
    // vertices in the current path 
    private void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList) {

        // Mark the current node 
        isVisited[u] = true;

        if (u.equals(d))
        {
            System.out.println(localPathList);
            // if match found then no need to traverse more till depth 
            isVisited[u]= false;
            return ;
        }

        // Recur for all the vertices 
        // adjacent to current vertex 
        for (Integer i : adjacencyList[u])
        {
            if (!isVisited[i])
            {
                // store current node  
                // in path[] 
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node 
                // in path[] 
                localPathList.remove(i);
            }
        }

        // Mark the current node 
        isVisited[u] = false;
    }
} 