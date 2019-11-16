package menu;
import file.ReadFileLogic;
import search.Criteria;
import search.Search;
import transportObject.ObjectManager;
import transportObject.TransportObject;


import java.util.HashSet;
import java.util.Scanner;

public class Menu {
    private Scanner inputStream = new Scanner(System.in);
    private HashSet<TransportObject> objectsInFile = new HashSet<>();
    private ObjectManager manager = new ObjectManager();

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
                    System.out.println("----------Add To Order----------");
                    addToOrder();
                    break;
                case 4:
                    System.out.println("----------Remove From Order----------");
                    //removeFromOrder();
                    break;
                case 5:
                    System.out.println("----------Empty Order----------");
                    //emptyOrder();
                    break;
                case 6:
                    System.out.println("----------Process Order----------");
                    order();
                    break;
            }
            choice = getUserChoice();
        }
        System.out.println("End of program");
    }

    private void addToOrder()
    {

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
        System.out.println("3 - Order");
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

    private void order()
    {

    }

    /**
     * Displays the graph (if created before) or an error!
     */
    private void displaySearch()
    {
        System.out.println("Enter a type A, B or C (anything else will be considered as an empty field)");
        String type = "";
        String userInput = inputStream.nextLine();
        try {
            type = userInput.substring(0,1).toUpperCase();
            if(!type.equals("A") && !type.equals("B") && !type.equals("C"))
                type = null;
        } catch(StringIndexOutOfBoundsException error) {
            System.out.println("Error in type");
        }

        System.out.println("Enter a type a name: (enter to skip)");
        String name = "";
        userInput = inputStream.nextLine();
        try {
            name = userInput;
        } catch(StringIndexOutOfBoundsException error) {
            System.out.println("Error in name");
        }


        String code = "";
        boolean ok = false;
        while(!ok)
        {
            System.out.println("Enter a type a code: (Enter to skip) ");
            userInput = inputStream.nextLine();
            try {
                code = userInput;
                if (code.length() <= 5)
                    ok = true;
            } catch (StringIndexOutOfBoundsException error) {
                System.out.println("Error in code");
            }
        }

        Criteria userCriteria = new Criteria(name, code, type);
        Search search = new Search(manager);
        if(search.exists(userCriteria))
            search.printResults();
        else
            System.out.println("Sorry there are not results for your search :(");
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
        {
            System.out.println("File read and successfully!");
            for(TransportObject element: objectsInFile)
                manager.add(element);
        }
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
