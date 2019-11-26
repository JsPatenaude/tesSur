package file;//package com.file;

import node.Node;
import transportObject.TransportObject;
import transportObject.TransportObjectA;
import transportObject.TransportObjectB;
import transportObject.TransportObjectC;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;

public class ReadFileLogic {

    private HashSet<TransportObject> objectsInFile;
    private String line;

    private Node automat;
    private HashSet<String> childrenName;


    /**
     * Constructor, reads data from the file
     */
    public ReadFileLogic()
    {
        objectsInFile = new HashSet<>();

        var childrenSetAutomat = new HashSet<Node>();
        childrenName = new HashSet<String>();
        automat = new Node("", childrenSetAutomat, false);

        /*
        * il va falloir lire les objets dans le fichier et les mettre chacun dans un node terminal
        * ensuite il faut creer les chemin pour acceder a ces nodes terminal (en regardant si lex lettres existent deja dans l<arbre avant de la creer)
        * par exemple:
        *       on lit avion, ... tous les objets jusqua la fin du fichier txt et on les met dans des nodes
        *       on veut creer l<arbre:
        *           creer node root vide
        *           lire premiere lettre nom premier objet et la mettre comme children du root
        *           lire deuxieme lettre et mettre premiere lettre et deuxieme lettre comme children de la premiere lettre
        *           lire troisieme lettre et mettre 1er, 2e et 3e lettre comme children de la deuxieme lettre
        *           ...
        *           quand on arrive au premier mot complet, inserer ce mot comme le node deja cree precedemment (lors de la lecture du txt)
        *           on recommence avec le 2e objet
        *           si la premiere lettre du nom du 2e objet existe deja, alors partir des children de la premiere lettre et continuer
        *           ...
        *           troisieme objet
        *           ...
        *           quand on finit d<inserer tous les nodes terminaux (lus au depart du txt) alors on a finit de creer l<arbre
        *
        * exemple avec des lettres:
        * 1.                root
        *
        * 2.                root
        *                   a
        *
        * 3.                root
        *                   a
        *                 av
        *
        * 4.                root
        *                   a
        *                 av
        *               avi
        * ...
        * 6.                root
        *                    a
        *                 av
        *               avi
        *             avio
        *           avion
        *
        * les node de avion sont inseres (tous les objets ayant le nom avion)
        *
        * on continue avec ami, amie, ...
        *
        *
        *
        *
        *
        * apres la creation de cet automate, on refait le meme processus mais avec le code au lieu du nom
        *
        * on ne fait pas d<automate pour le type pck cest simple
        *
        * on fait finalement le reste du code comme avant et tout fonctionne
        *
        * */



        readFile();
    }

    /**
     * Function to get the automat
     */
    public Node getAutomat(){
        return  automat;
    }


    /**
     * Function to read data from a file
     */
    private void readFile()
    {
        BufferedReader inputFileBuffer;
        try {
            inputFileBuffer = new BufferedReader(new FileReader("inventaire.txt"));
            line = inputFileBuffer.readLine();
            readInventory(inputFileBuffer);
        } catch (IOException error) {
            error.printStackTrace();
            System.out.println("Error Inventory's file could not be opened.");
        }
    }

    /**
     * Getter for the attribute sectionInFile
     * @return attribute objectsInFile containing the different objects read from the file
     */
    public HashSet<TransportObject> getObjectsInFile() { return objectsInFile; }

    /**
     * Read the sections with their information from a Buffer and store them in a set
     * @param inputFileBuffer BufferReader from where the info should be read
     */
    private void readInventory(BufferedReader inputFileBuffer) throws IOException
    {
        while(line != null && !line.isEmpty())
        {
            String name = getStringAtPosition();
            String code = getStringAtPosition();
            String type = getStringAtPosition();
            switch (type)
            {
                case "A" :
                    objectsInFile.add(new TransportObjectA(name, code));
                    createAutomat(name, new TransportObjectA(name, code));
                    break;
                case "B" :
                    objectsInFile.add(new TransportObjectB(name, code));
                    createAutomat(name, new TransportObjectB(name, code));
                    break;
                case "C" :
                    objectsInFile.add(new TransportObjectC(name, code));
                    createAutomat(name, new TransportObjectC(name, code));
                    break;
            }
            line = inputFileBuffer.readLine();
        }
    }

    /**
     * Function to parse a line following this format "A B C"
     */
    private String getStringAtPosition()
    {
        int spaceIndex = line.indexOf(' ');
        String stringRead = spaceIndex != -1 ? line.substring(0, spaceIndex) : line;
        line = line.substring(spaceIndex + 1);
        return stringRead;
    }

    /**
     * Function to create an automat (tree) from the inventory
     */
    private void createAutomat(String stringRead, TransportObject object){
//        if (automat.getChildrenSet().contains())

        // faut pas automat children contienne deja un node pareil (pas 2x meme nom)
        // si le nom du precedent + sa lettre de + != le nom dun objet (faire un for avant)
        //      ajouter un node avec le nom du precedent + sa lettre de +
        //      createAutomat(nouveau nom)
        // sinon
        //      ajouter node avec lettre de plus et lui assigner l'objet inventaire qui correspond a son nom

        // cette fct dans for loop pour chaque ligne lue du fichier


        String lastString = "";
        Node node = automat;
        Node child = null;

        // foreach letter in the string read
        for (var i = 0; i < stringRead.length(); ++i){
            var nodeString = lastString + stringRead.charAt(i);
            child = new Node(nodeString, new HashSet<Node>(), false);

            lastString = nodeString;

            // if node string is not already in the automat
            if (!node.getAllChildrenName(node, childrenName).contains(nodeString)){
                node.addChild(child);
                node = child;
            }
            else{
                if (node.getNodeByName(node, nodeString) != null){
                    node = node.getNodeByName(node, nodeString);
                }
            }
        }

        // set the node as terminal
        if (child != null){
            child.setTerminal(true);
            child.addObjects(object);
        }

    }

}