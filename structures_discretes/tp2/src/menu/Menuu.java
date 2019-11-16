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
import java.util.HashSet;

public class Menuu
{
    private JFrame mainWindow;
    private JList suggested;
    private DefaultListModel suggestedModel;
    private JList available;
    private DefaultListModel availableModel;
    private JList cart;
    private DefaultListModel cartModel;

    private HashSet<TransportObject> objectsInFile = new HashSet<>();
    private ObjectManager manager = new ObjectManager();
    private ObjectManager order = new ObjectManager();

    public  Menuu()
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
        objectsInFile = file.getObjectsInFile();
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

    private void switchElements(JList listFrom, DefaultListModel modelFrom, DefaultListModel modelTo, ObjectManager from, ObjectManager to)
    {
        if(listFrom.getSelectedValue() != null)
        {
            String selected = listFrom.getSelectedValue().toString();
            System.out.println(selected);
            Criteria searchSelected = new Criteria(selected);
            TransportObject result = from.findByCode(searchSelected.getCode());
            if (result != null)
            {
                to.add(result);
                modelTo.add(modelTo.size(), result.getString());
                from.remove(result);
                modelFrom.removeElement(result.getString());
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
            public void actionPerformed(ActionEvent e) { readFile(); }
        });

        JButton addToOrder = new JButton("Add To Order");
        addToOrder.setBounds(POSITIONX,60, BUTTONWIDTH, BUTTONHEIGHT);
        addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { switchElements(available, availableModel, cartModel, manager, order); }
        });

        JButton removeFromOrder = new JButton("Remove From Order");
        removeFromOrder.setBounds(POSITIONX,110, BUTTONWIDTH, BUTTONHEIGHT);
        removeFromOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { switchElements(cart, cartModel, availableModel, order, manager); }
        });

        JButton emptyOrder = new JButton("Empty Order");
        emptyOrder.setBounds(POSITIONX,160, BUTTONWIDTH, BUTTONHEIGHT);
        emptyOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cartModel.removeAllElements();
                availableModel.removeAllElements();
                manager = new ObjectManager();
                for(TransportObject toAdd : objectsInFile)
                    availableModel.add(availableModel.size(), toAdd.getString());
            }
        });

        JButton order = new JButton("Order");
        order.setBounds(POSITIONX,210, BUTTONWIDTH, BUTTONHEIGHT);

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
        suggestedModel = new DefaultListModel();
        suggested = new JList(suggestedModel);

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

    private void addCriteria()
    {
        int POSITIONX = 350;
        int LABELWIDTH = 150;
        int LABELHEIGHT = 20;

        JLabel criteriaLabel = new JLabel("Criteria To Get Suggestions");
        criteriaLabel.setBounds(POSITIONX - 50,272, LABELWIDTH+100, LABELHEIGHT);
        mainWindow.add(criteriaLabel);

        JComboBox type = new JComboBox();
        type.addItem(" ");
        type.addItem("A");
        type.addItem("B");
        type.addItem("C");
        type.setBounds(POSITIONX,302, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(type);

        JTextArea name = new JTextArea();
        name.setBounds(POSITIONX,352, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(name);


        JTextArea code = new JTextArea();
        code.setBounds(POSITIONX,402, LABELWIDTH, LABELHEIGHT);
        mainWindow.add(code);
    }

    private void addAvailableItems()
    {
        JLabel availableLabel = new JLabel("Available items to add");
        availableLabel.setBounds(620 ,22, 175, 40);
        mainWindow.add(availableLabel);

        availableModel = new DefaultListModel();
        available = new JList(availableModel);

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
        cartModel = new DefaultListModel();
        cart = new JList(cartModel);

        scrollPane.setViewportView(cart);
        scrollPane.setBounds(850,52, 200, 400);
        mainWindow.add(scrollPane);
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
