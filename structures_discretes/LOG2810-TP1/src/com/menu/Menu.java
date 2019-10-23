package com.menu;

import java.util.Scanner;

public class Menu {

    private Scanner inputStream = new Scanner(System.in);

    public Menu()
    {
        switch (getUserChoice())
        {
            default:
                System.out.println("Unhandled Error.");
            case 0:
                System.exit(0);
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    /**
     * Displays the available options to the user
     * @return What the user chose, or 0 to quit
     */
    private int printOptions()
    {
        System.out.println("Choose between the following options: ");
        System.out.println("1 - Creer un graphe");
        System.out.println("2 - Afficher le graphe");
        System.out.println("3 - Afficher la commande");
        System.out.println("4 - Trouver le plus court chemin");
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
        while(option == 0)
        {
            System.out.println("\n\n\n\nPlease enter a valid option!");
            option = printOptions();
        }
        return option;
    }
}