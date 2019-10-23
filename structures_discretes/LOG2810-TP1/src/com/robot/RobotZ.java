package com.robot;

public class RobotZ extends Robot {

    /**
     * Function to update the speed of robot A with the current load's weight
     * @return void
     */
    @Override
    protected void updateSpeed()
    {
        speed = 2.5 + 0.2 * currentWeight;
    }
}