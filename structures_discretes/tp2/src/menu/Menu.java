package menu;
import file.ReadFileLogic;
import transportObject.ObjectManager;
import transportObject.TransportObject;

import java.util.HashSet;
import java.util.Scanner;

public class Menu {
    private Scanner inputStream = new Scanner(System.in);
    private HashSet<TransportObject> objectsInFile = new HashSet<>();

    /**
     * Constructor, asks user for a menu choice until the user quits.
     */
    private Menu()
    {
        int choice = getUserChoice();
        while(choice != 0)
        {
            switch (choice)
            {
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
                case 1:
                    System.out.println("----------Initiate----------");
                    initiate();
                    break;
                case 2:
                    System.out.println("----------Research Object----------");
                    displaySearch();
                    break;
                case 3:
                    System.out.println("----------Add to cart----------");
                    //takeOrder();
                    break;
                case 4:
                    System.out.println("----------Remove from cart----------");
                    //displayOrder();
                    break;
                case 5:
                    System.out.println("----------Remove all from cart----------");
                    //findShortestPath();
                    break;
                case 6:
                    System.out.println("----------Execute order----------");
                    //findShortestPath();
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
        System.out.println("\n");
        System.out.println("Choose between the following options: ");
        System.out.println("1 - Initiate");
        System.out.println("2 - Research Object");
        System.out.println("3 - Add to cart");
        System.out.println("4 - Remove from cart");
        System.out.println("5 - Remove all from cart");
        System.out.println("6 - Execute order");
        System.out.println("To quit enter 'Q'");

        String userInput = inputStream.nextLine();
        int input = -1;
        try {
            input = Character.getNumericValue(userInput.charAt(0));
        } catch(StringIndexOutOfBoundsException error) {
            System.out.println("Enter a valid choice!");
            input = printOptions();
        }
        return userInput.equals("Q") ?  0 : input;
    }

    /**
     * Get the user's menu choice, and treat errors
     * @return What the user chooses
     */
    private int getUserChoice() { return printOptions(); }

    /**
     * Created an order from user's input
     */
//    private void takeOrder()
////    {
////        boolean validChoice = false;
////        while(!validChoice)
////        {
////            try {
////                System.out.println("Object A amount : ");
////                Scanner amountA = new Scanner(System.in);
////                order.takeOrder(TransportObjectA.getName(), amountA.nextInt());
////                validChoice = true;
////            } catch (InputMismatchException error) {
////                System.out.println("Enter a valid number!");
////                validChoice = false;
////            }
////        }
////
////        validChoice = false;
////        while(!validChoice)
////        {
////            try {
////                System.out.println("Object B amount : ");
////                Scanner amountB = new Scanner(System.in);
////                order.takeOrder(TransportObjectB.getName(), amountB.nextInt());
////                validChoice = true;
////            } catch (InputMismatchException error) {
////                System.out.println("Enter a valid number!");
////                validChoice = false;
////            }
////        }
////        validChoice = false;
////        while(!validChoice)
////        {
////            try {
////                System.out.println("Object C amount : ");
////                Scanner amountC = new Scanner(System.in);
////                order.takeOrder(TransportObjectC.getName(), amountC.nextInt());
////                validChoice = true;
////            } catch (InputMismatchException error) {
////                System.out.println("Enter a valid number!");
////                validChoice = false;
////            }
////        }
////    }

    /**
     * Displays the order (if created before) or an error!
     */
//    private void displayOrder()
//    {
//        if(order.getNumberOfA() == 0 && order.getNumberOfB() == 0 && order.getNumberOfC() == 0)
//            System.out.println("Your order is empty!");
//        else
//            order.display();
//    }

    /**
     * Displays the graph (if created before) or an error!
     */
    private void displaySearch()
    {
//        if(sectionsInFile.isEmpty())
//            System.out.println("Please initiate the program first");
//        else
//        {
//            //display max 10 first objects
//
////            GraphConsole graphOutput = new GraphConsole(sectionsInFile);
////            graphOutput.display();
//        }
    }

    /**
     * Creates the graph from the available information in the text file
     */
    private void initiate()
    {

        //create ReadFilLogic
        //store inventaire dans une liste, map, etc
        //construire automate minimal???????????????????

        ReadFileLogic file = new ReadFileLogic();
        objectsInFile = file.getObjectsInFile();
        if(objectsInFile.isEmpty())
            System.out.println("Sorry!! There was a problem with the initiation of the program :(");
        else
            System.out.println("File read and successfully!");
    }

    /**
     * Finds the shortest path to complete the user's order
     */
//    private void findShortestPath()
//    {
//        if(sectionsInFile.isEmpty())
//            System.out.println("Please Create the graph first");
//        else
//        {
//            RouteAlgorithm route = new RouteAlgorithm(sectionsInFile, order.getNumberOfA(),
//                    order.getNumberOfB(), order.getNumberOfC());
//            if (order.getNumberOfA() != 0 || order.getNumberOfB() != 0 || order.getNumberOfC() == 0)
//                route.displayBestRoute();
//        }
//    }

    public static void main(String[] args)
    {
        Menu mainMenu = new Menu();

    }
}
