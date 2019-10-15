package com.robot;

public class RobotY extends Robot {

    /**
     * Function to update the speed of robot A with the current load's weight
     * @return void
     */
    @Override
    protected void updateSpeed()
    {
        speed = 1.5 + 0.6 * currentWeight;
    }
}
