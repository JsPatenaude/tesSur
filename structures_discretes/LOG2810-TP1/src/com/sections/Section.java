package com.sections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Section {

    private int numberObjectA_ = 0;
    private int numberObjectB_ = 0;
    private int numberObjectC_ = 0;
    private Integer sectionNumber_ = 0;
    private boolean visited_   = false;
    private HashMap<Integer, Integer> distances_ = new HashMap<Integer, Integer>();

    /**
     * Constructor of a section
     * @param sectionNumber the section's given number in the warehouse
     * @param numberObjectA number of objects A contained in this section
     * @param numberObjectB number of objects B contained in this section
     * @param numberObjectC number of objects C contained in this section
     */
    public Section(int sectionNumber, int numberObjectA, int numberObjectB, int numberObjectC)
    {
        sectionNumber_ = sectionNumber;
        numberObjectA_ = numberObjectA;
        numberObjectB_ = numberObjectB;
        numberObjectC_ = numberObjectC;
    }

    /**
     * Overloading of the equals function to compare 2 Sections, here 2 sections are equal if they have
     *  the same number
     * @param comparable Section to be compared to
     * @return True if they have the same section number, else false
     */
    public boolean equals(Section comparable) { return sectionNumber_.equals(comparable); }

    /**
     * Getter for the section's number
     * @return section's number
     */
    public  Integer getSectionNumber_()  { return sectionNumber_; }

    /**
     * Getter for the section's number of objects A
     * @return section's number of objects A
     */
    public int getNumberOfObjectsA() { return numberObjectA_; }

    /**
     * Getter for the section's number of objects B
     * @return section's number of objects B
     */
    public int getNumberOfObjectsB() { return numberObjectB_; }

    /**
     * Getter for the section's number of objects C
     * @return section's number of objects C
     */
    public int getNumberOfObjectsC() { return numberObjectC_; }

    /**
     * Getter for the section's visited attribute
     * @return true if the section has been visited by a robot, else false
     */
    public boolean hasBeenVisited()  { return visited_; }

    /**
     * Sets the visited attribute to true
     */
    private void visit() { visited_ = true; }

    /**
     * Adding a distance to a node to the distances map
     * @param toNode Number of the node to which this arc is related
     * @param distanceToNode Distance to the first parameter (the node)
     */
    public void addDistance(int toNode, int distanceToNode)
    {
        distances_.put(toNode, distanceToNode);
    }

    /**
     * Getter for the distance from current node to a certain node
     * @param toNode Number of the node to which we want the distance
     * @return int distance, or -1 if there's no relation between this node and the parameter node
     */
    public int getDistance(int toNode)
    {
        Integer distance = distances_.get(toNode);
        return  distance != null ? distance : -1;
    }
}