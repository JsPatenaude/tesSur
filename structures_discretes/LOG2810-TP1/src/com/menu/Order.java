package com.menu;

import com.transportObject.TransportObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order
{
    private HashMap<TransportObject, Integer> list;

    public Order(){
        list = new HashMap<>();
    }

    public void takeOrder(TransportObject objectType, Integer amount){
        list.put(objectType, amount);
    };

    // TODO
    public void display()
    {
        HashMap<TransportObject, Integer> listCopy = list;
        Iterator it = listCopy.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            TransportObject current = (TransportObject) pair.getKey();
            System.out.println( current.getName() + ": " + pair.getValue());
            it.remove(); // To Avoid a current modification exception
        }
    }

    public HashMap<TransportObject, Integer> getList(){ return list; }
}
