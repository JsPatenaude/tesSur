package com.menu;

import com.transportObject.TransportObject;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order
{
    private int list [ ];

    public Order()
    {
        list = new int [3];
        list[0] = -1;
    }

    private int getIndex(String objectType)
    {
        if(objectType.equals("Object A"))
            return 0;
        if(objectType.equals("Object B"))
            return 1;
        if(objectType.equals("Object C"))
            return 2;
        throw new IllegalArgumentException();
    }

    public void takeOrder(String objectType, Integer amount) { list[getIndex(objectType)] = amount; }


    public void display()
    {
        System.out.println("Number of object A is " + list[0]);
        System.out.println("Number of object B is " + list[1]);
        System.out.println("Number of object C is " + list[2]);
    }

    public int getNumberOfA() { return list[0]; }

    public int getNumberOfB() { return list[1]; }

    public int getNumberOfC() { return list[2]; }
}
