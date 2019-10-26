package com.menu;

import com.transportObject.TransportObject;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.util.Scanner;

public class Menu {

    private Scanner inputStream = new Scanner(System.in);
    private Order order = new Order();

    public Menu()
    {
        int choice = getUserChoice();
        while(choice != 0)
        {
            switch (choice)
            {
                default:
                    System.out.println("Unhandled Error.");
                    break;
                case 1:
                    System.out.println("----------Create graph----------");
                    break;
                case 2:
                    System.out.println("----------Display graph----------");
                    break;
                case 3:
                    System.out.println("----------Take order----------");
                    takeOrder();
                    break;
                case 4:
                    System.out.println("----------Display order----------");
                    displayOrder();
                    break;
                case 5:
                    System.out.println("----------Find the shortest way----------");
                    break;
            }
            choice = getUserChoice();
        }
        System.out.println("End of program");

    }

    /**
     * Displays the available options to the user
     * @return What the user chose, or 0 to quit
     */
    private int printOptions()
    {
        System.out.println("Choose between the following options: ");
        System.out.println("1 - Create graph");
        System.out.println("2 - Display graph");
        System.out.println("3 - Take order");
        System.out.println("4 - Display order");
        System.out.println("5 - Find the shortest way");
        System.out.println("To quit enter 'Q'");

        String userInput = inputStream.nextLine();
        return userInput.equals("Q") ?  0 : Character.getNumericValue(userInput.charAt(0));
    }

    /**
     * Get the user's menu choice, and treat errors
     * @return What the user chooses
     */
    public int getUserChoice()
    {
        int option = printOptions();
        return option;
    }

    public void takeOrder()
    {
        System.out.println("Object A amount : ");
        TransportObjectA objectA = new TransportObjectA();
        Scanner amountA = new Scanner(System.in);
        order.takeOrder(objectA, amountA.nextInt());

        System.out.println("Object B amount : ");
        TransportObjectB objectB = new TransportObjectB();
        Scanner amountB = new Scanner(System.in);
        order.takeOrder(objectB, amountB.nextInt());

        System.out.println("Object C amount : ");
        TransportObjectC objectC = new TransportObjectC();
        Scanner amountC = new Scanner(System.in);
        order.takeOrder(objectC, amountC.nextInt());
    }

    public void displayOrder(){
        for (TransportObject object : order.getList().keySet()) {

            //System.out.println("Object " + object.getName() + " amount : " + order.getList().get(object));
        }
    }

}