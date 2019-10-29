package com.route;

import com.sections.Section;

import java.util.*;

// Source : https://stackoverflow.com/questions/17480022/java-find-shortest-path-between-2-points-in-a-distance-weighted-map
// Modified for our use case

public class Dijkstra
{
    private Vertex vertices[];
    private HashSet<Section> sections;

    public Dijkstra(HashSet<Section> sectionsFromFile)
    {
        vertices = new Vertex[sectionsFromFile.size()];
        sections = sectionsFromFile;
        convertHashSet(sectionsFromFile);
    }

    public void convertHashSet(HashSet<Section> sectionsFromFile)
    {
        for(Section element: sections) // Adding all section as vertices
            vertices[element.getSectionNumber_()] = new Vertex(element.getSectionNumber_());

        for(Section element: sections) // For each section add it's edges
        {
            HashMap<Integer, Integer> neighbors = element.getDistances();
            int i = 0;
            vertices[element.getSectionNumber_()].adjacency = new Edge[neighbors.size()];
            Iterator it = neighbors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                it.remove(); // avoids a ConcurrentModificationException
                vertices[element.getSectionNumber_()].adjacency[i++] = new Edge(vertices[(Integer) pair.getKey()], (Integer) pair.getValue());
            }
        }
    }

    public void runDijkstra(int source, int destination)
    {
        computePaths(vertices[source]);
        System.out.println("Distance to " + vertices[destination] + ": " + vertices[destination].minDistance);
        List<Vertex> path = getShortestPathTo(vertices[destination]);
        System.out.println("Path: " + path);
    }

    private Section findSection(int number)
    {
        for(Section element : sections)
            if(element.getSectionNumber_() == number)
                return element;
        return null;
    }

    private static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty())
        {
            Vertex u = vertexQueue.poll();
            for (Edge e : u.adjacency)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance)
                {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    static class Vertex implements Comparable<Vertex>
    {
        final int name;
        Edge[] adjacency;
        double minDistance = Double.POSITIVE_INFINITY;
        Vertex previous;
        Vertex(int argName) { name = argName; }
        public String toString() { return String.valueOf(name); }
        public int toInt() { return name; }
        public int compareTo(Vertex other) { return Double.compare(minDistance, other.minDistance); }
    }


    static class Edge
    {
        final Vertex target;
        final double weight;
        Edge(Vertex argTarget, double argWeight)
        {
            target = argTarget;
            weight = argWeight;
        }
    }

}