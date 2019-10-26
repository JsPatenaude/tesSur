package com.route;

import com.sections.Section;

import java.util.HashSet;

public class RouteAlgorithm {

    HashSet<Section> sections;

    public RouteAlgorithm(HashSet<Section> sectionsFromFile) { sections = sectionsFromFile; }

    public void displayBestRoute()
    {
        
        System.out.println();


        int time = 0, typeRobot =  0;
        System.out.println("temps: " + time);
        System.out.println("robot utilisé: " + typeRobot);
    }
}
