package com.transportObject;

public class TransportObjectA extends TransportObject {

    /**
     * Constructor of an object type A
     */
    public TransportObjectA()
    {
        weight = 1;
    }

    @Override
    public String getName() { return "Object A"; }
}