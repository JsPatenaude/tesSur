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

    /**
     * Function to update the speed of robot A with the current load's weight
     * @return void
     */
    @Override
    protected void updateK() { k = 1 + currentWeight; }
}