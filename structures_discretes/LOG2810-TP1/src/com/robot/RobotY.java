package com.robot;

public class RobotY extends Robot
{
    /**
     * Constructor of a robot type Y
     */
    public RobotY()
    {
        maxWeight = 10;
        k = 1.5;
    }

    /**
     * Function to update the speed of robot A with the current load's weight
     * @return void
     */
    @Override
    protected void updateK()
    {
        k = 1.5 + 0.6 * currentWeight;
    }
}