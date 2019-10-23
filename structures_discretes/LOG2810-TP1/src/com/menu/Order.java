package com.menu;

import com.transportObject.TransportObject;

import java.util.HashMap;

public class Order {
    public Order(){
        list = new HashMap<TransportObject, Integer>();
    }

    public void takeOrder(TransportObject objectType, Integer amount){
        list.put(objectType, amount);
    };

    // TODO
    public void displayOrder(){

    }

    public HashMap<TransportObject, Integer> getList(){ return list; }

    private HashMap<TransportObject, Integer> list;
}
