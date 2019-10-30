package com.robot;

public class RobotX extends Robot
{
    /**
     * Constructor of a robot type X
     */
    public RobotX()
    {
        maxWeight = 5;
        k = 1;
    }

    static public int getMaxWeight() { return 5; }

    /**
     * Function to update the speed of robot A with the current load's weight
     */
    @Override
    protected void updateK() { k = 1 + currentWeight; }

    /**
     * Getter function of the robot's name
     */
    @Override
    public String getName() { return "Robot X"; }
}