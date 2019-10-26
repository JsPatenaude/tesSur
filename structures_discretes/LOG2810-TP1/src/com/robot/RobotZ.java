package com.robot;

public class RobotZ extends Robot
{
    /**
     * Constructor of a robot type Z
     */
    public RobotZ()
    {
        maxWeight = 25;
        k = 2.5;
    }

    /**
     * Function to update the speed of robot A with the current load's weight
     * @return void
     */
    @Override
    protected void updateK()
    {
        k = 2.5 + 0.2 * currentWeight;
    }
}