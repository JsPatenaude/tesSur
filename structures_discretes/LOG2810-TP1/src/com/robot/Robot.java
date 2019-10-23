package com.robot;
import com.transportObject.TransportObject;
import java.util.Vector;

public abstract class Robot {

    protected int maxWeight = 0;
    protected int currentWeight = 0;
    protected double speed = 0;
    Vector<TransportObject> baggage = new Vector<TransportObject>();

    /**
     * Checks if an object can be added to a robot
     * @param weightToAdd weight of the object to be added
     * @return if the object can be added without exceeding the limit
     */
    private boolean canAddObject(int weightToAdd) { return (currentWeight + weightToAdd) <= maxWeight; }

    /**
     * Adds an object to a robot's transportation list
     * @param toAdd object to be added to the list
     */
    public void addBaggage(TransportObject toAdd)
    {
        if(toAdd != null)
            if(canAddObject(toAdd.getWeight()))
            {
                baggage.add(toAdd);
                updateSpeed();
            }
            else
                System.out.println("Sorry Robot is full.\nCurrent Weight: " + currentWeight
                        + " kg\nMaxWeight: " + maxWeight + " kg");
    }

    /**
     * Find the estimated time the robot will take to travel a certain distance
     * @param distance distance for which the time will be evaluated
     * @return The time that will be taken
     */
    public double findETA(double distance) { return distance * speed; }

    /**
     * Getter for the robot's current speed
     * @return Current speed of the robot with the current load
     */
    public double getSpeed() { return speed; }

    /**
     * Abstract function (should be defined for each child class), to calculate the speed depending
     *      on the current load's weight
     */
    protected abstract void updateSpeed();
}