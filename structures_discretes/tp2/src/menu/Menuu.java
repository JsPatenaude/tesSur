package menu;
import file.ReadFileLogic;
import search.Criteria;
import search.Search;
import transportObject.ObjectManager;
import transportObject.TransportObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Menuu
{
    private JFrame mainWindow;
    private DefaultListModel<String> suggestedModel;
    private JList<String> available;
    private DefaultListModel<String> availableModel;
    private JList<String> cart;
    private DefaultListModel<String> cartModel;
    private JLabel weight;
    private int weightInt;

    private ObjectManager manager = new ObjectManager();
    private ObjectManager orderManager = new ObjectManager();
    private ObjectManager suggestedItems = new ObjectManager();
    private Criteria currentCriteria = new Criteria();

    private  Menuu()
    {
        mainWindow = new JFrame();
        mainWindow.setTitle("LOG2810 - TP2");
        addButtons();
        addSuggestedItems();
        addInputFields();
        addCriteria();
        addAvailableItems();
        addCart();
        mainWindow.setSize(1100,500);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        centerWindow();
    }

    private void readFile()
    {
        ReadFileLogic file = new ReadFileLogic();
        HashSet<TransportObject> objectsInFile = file.getObjectsInFile();
        if(objectsInFile.isEmpty())
            System.out.println("Sorry!! There was a problem with the initiation of the program :(");
        else
        {
            System.out.println("File read and successfully!");
            for(TransportObject element: objectsInFile)
                manager.add(element);
            availableModel.addAll( manager.getElementsString() );
        }
    }

    private void switchElements(JList<String> listFrom, DefaultListModel<String> modelFrom, DefaultListModel<String> modelTo,
                                ObjectManager from, ObjectManager to, boolean isFromCart)
    {
        if(listFrom.getSelectedValue() != null)
        {
            String selected = listFrom.getSelectedValue();
            System.out.println(selected);
            Criteria searchSelected = new Criteria(selected);
            TransportObject result = from.findByCode(searchSelected.getCode());
            if (result != null)
            {
                if(orderManager.findByCode(result.getHashCode()) != null && listFrom.equals(available)) {
                    JOptionPane.showMessageDialog(mainWindow, "This item is already in cart!");
                    return ;
                }
                if(listFrom.equals(available))
                    weightInt += result.getWeight();
                else
                    weightInt -= result.getWeight();
                weight.setText(weightInt + " kg");
                if(weightInt > 25)
                    JOptionPane.showMessageDialog(mainWindow, "Warning: Critical weight!!\n Current Weight" +
                            " is " + weight.getText() + " > allowed weight of 25 kg.");

                if(isFromCart)
                {
                    from.remove(result);
                    modelFrom.removeElement(result.getString());
                }
                else
                {
                    to.add(result);
                    modelTo.add(modelTo.size(), result.getString());
                }
            }
        }
        else
            JOptionPane.showMessageDialog(mainWindow, "Error please select an element!!");
    }

    private void addButtons()
    {
        int BUTTONWIDTH = 200;
        int BUTTONHEIGHT = 40;
        int POSITIONX = 10;

        JButton initiate = new JButton("Initiate");
        initiate.setBounds(POSITIONX,10, BUTTONWIDTH, BUTTONHEIGHT);
        initiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(manager.isEmpty())
                    readFile();
                else
                    JOptionPane.showMessageDialog(mainWindow, "File already read.");
            }
        });

        JButton addToOrder = new JButton("Add To Order");
        addToOrder.setBounds(POSITIONX,60, BUTTONWIDTH, BUTTONHEIGHT);
        addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { switchElements(available, availableModel, cartModel, manager, orderManager, false); }
        });

        JButton removeFromOrder = new JButton("Remove From Order");
        removeFromOrder.setBounds(POSITIONX,110, BUTTONWIDTH, BUTTONHEIGHT);
        removeFromOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { switchElements(cart, cartModel, availableModel, orderManager, manager, true); }
        });

        JButton emptyOrder = new JButton("Empty Order");
        emptyOrder.setBounds(POSITIONX,160, BUTTONWIDTH, BUTTONHEIGHT);
        emptyOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cartModel.removeAllElements();
                availableModel.removeAllElements();
                orderManager = new ObjectManager();
                manager = new ObjectManager();
                readFile();
            }
        });

        JButton order = new JButton("Order");
        order.setBounds(POSITIONX,210, BUTTONWIDTH, BUTTONHEIGHT);
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(weightInt > 25)
                    JOptionPane.showMessageDialog(mainWindow, "Sorry the cart's weight should be less than 25 kg to order\n" +
                            "Remove some items and try again later :)");
                else
                {
                    for(TransportObject element: orderManager.getElements())
                    {
                        manager.remove(element);
                        availableModel.removeElement(element.getString());
                    }
                    cartModel.removeAllElements();
                    orderManager = new ObjectManager();
                    weightInt = 0;
                    weight.setText("0 kg");
                }
            }
        });

        JButton quit = new JButton("Quit");
        quit.setBounds(POSITIONX,260, BUTTONWIDTH, BUTTONHEIGHT);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });

        mainWindow.add(initiate);
        mainWindow.add(addToOrder);
        mainWindow.add(removeFromOrder);
        mainWindow.add(emptyOrder);
        mainWindow.add(order);
        mainWindow.add(quit);
    }

    private void addSuggestedItems()
    {
        JLabel suggestedLabel = new JLabel("Suggested Items using inputted criteria");
        suggestedLabel.setBounds(250 ,22, 275, 40);
        mainWindow.add(suggestedLabel);

        JScrollPane scrollPane = new JScrollPane();
        suggestedModel = new DefaultListModel<>();
        JList<String> suggested = new JList<String>(suggestedModel);

        scrollPane.setViewportView(suggested);
        scrollPane.setBounds(300,52, 200, 200);
        mainWindow.add(scrollPane);
    }

    private void addInputFields()
    {
        int POSITIONX = 300;
        int LABELWIDTH = 75;
        int LABELHEIGHT = 40;

        JLabel type = new JLabel("Type");
        type.setBounds(POSITIONX,290, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(type);

        JLabel name = new JLabel("Name");
        name.setBounds(POSITIONX,340, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(name);


        JLabel code = new JLabel("Code");
        code.setBounds(POSITIONX,390, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(code);
    }

    void addToSuggested(Search search)
    {
        suggestedModel.removeAllElements();
        suggestedItems = new ObjectManager();
        if(search.exists(currentCriteria))
        {
            for(TransportObject element: search.getResults())
                suggestedItems.add(element);
            suggestedModel.addAll(suggestedItems.getElementsString());
        }
    }

    private void addCriteria()
    {
        int POSITIONX = 350;
        int LABELWIDTH = 150;
        int LABELHEIGHT = 20;
        Search search = new Search(manager);

        JLabel criteriaLabel = new JLabel("Criteria To Get Suggestions");
        criteriaLabel.setBounds(POSITIONX - 50,272, LABELWIDTH+100, LABELHEIGHT);
        mainWindow.add(criteriaLabel);

        JComboBox<String> type = new JComboBox<>();
        type.addItem(" ");
        type.addItem("A");
        type.addItem("B");
        type.addItem("C");
        type.setBounds(POSITIONX,302, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(type);
        type.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String input = type.getSelectedItem().toString();
                currentCriteria.setType(input);
                suggestedItems = new ObjectManager();
                suggestedModel.removeAllElements();
                if(search.exists(currentCriteria))
                {
                    for(TransportObject element: search.getResults())
                        suggestedItems.add(element);
                    suggestedModel.addAll(suggestedItems.getElementsString());
                }
            }
        });

        JTextArea name = new JTextArea();
        name.setBounds(POSITIONX,352, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(name);
        name.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { }

            @Override
            public void keyPressed(KeyEvent keyEvent) { }

            @Override
            public void keyReleased(KeyEvent keyEvent)
            {
                currentCriteria.setName(name.getText());
                addToSuggested(search);
            }
        });

        JTextArea code = new JTextArea();
        code.setBounds(POSITIONX,402, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(code);
        code.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { }

            @Override
            public void keyPressed(KeyEvent keyEvent) { }

            @Override
            public void keyReleased(KeyEvent keyEvent)
            {
                currentCriteria.setCode(code.getText());
                addToSuggested(search);
            }
        });
    }

    private void addAvailableItems()
    {
        JLabel availableLabel = new JLabel("Available items to add");
        availableLabel.setBounds(620 ,22, 175, 40);
        mainWindow.add(availableLabel);

        availableModel = new DefaultListModel<>();
        available = new JList<>(availableModel);

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(available);
        scrollPane.setBounds(600,52, 200, 400);
        mainWindow.add(scrollPane);
    }

    private void addCart()
    {
        JLabel cartLabel = new JLabel("Items Currently in cart");
        cartLabel.setBounds(870 ,22, 175, 40);
        mainWindow.add(cartLabel);

        JScrollPane scrollPane = new JScrollPane();
        cartModel = new DefaultListModel<>();
        cart = new JList<String>(cartModel);

        scrollPane.setViewportView(cart);
        scrollPane.setBounds(850,52, 200, 300);
        mainWindow.add(scrollPane);

        JLabel weightLabel = new JLabel("Cart's Weight");
        weightLabel.setBounds(850 ,372, 100, 20);
        mainWindow.add(weightLabel);

        weight = new JLabel("0 kg");
        weight.setBounds(950, 372, 100, 20);
        mainWindow.add(weight);
    }

    private void centerWindow()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - mainWindow.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - mainWindow.getHeight()) / 2);
        mainWindow.setLocation(x, y);
    }

    public static void main(String[] args)
    {
        Menuu mainMenu = new Menuu();

    }
}
