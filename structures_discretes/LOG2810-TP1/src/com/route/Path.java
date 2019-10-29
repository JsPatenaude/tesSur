package com.route;

import com.sections.Section;

import java.util.LinkedHashMap;

public class Path {
    public LinkedHashMap<Integer, Section> path;
    public LinkedHashMap<Integer, Integer> distances;
    public int containsA = 0;
    public int containsB = 0;
    public int containsC = 0;
    private int numberOfSection = 0;

    public Path()
    {
        path = new LinkedHashMap<>();
        distances = new LinkedHashMap<>();
    }

    public void addSection(Section toAdd, int distance)
    {
        path.put(numberOfSection, toAdd);
        distances.put(numberOfSection++, distance);
        containsA += toAdd.getNumberOfObjectsA();
        containsB += toAdd.getNumberOfObjectsB();
        containsC += toAdd.getNumberOfObjectsC();
    }
}
