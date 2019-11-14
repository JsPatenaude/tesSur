package file;//package com.file;

import transportObject.TransportObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;

public class ReadFileLogic {

    private HashSet<TransportObject> objectsInFile;
    private String line;

    /**
     * Constructor, reads data from the file
     */
    public ReadFileLogic()
    {
        objectsInFile = new HashSet<>();
        readFile();
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
     * @return attribute sectionInFile containing the different sections read from the file
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
            objectsInFile.add(new TransportObject(
                    0,
                    getStringAtPosition(),
                    getStringAtPosition(),
                    getStringAtPosition()
            ) {
            });
            line = inputFileBuffer.readLine();
        }
    }

//    /**
//     * Read the distances between sections from a Buffer and store them in a
//     * @param inputFileBuffer BufferReader from where the info should be read
//     */
//    private void readDistances(BufferedReader inputFileBuffer) throws IOException
//    {
//        int wantedNumber, toNodeNumber, distance, currentSectionNumber;
//        line = inputFileBuffer.readLine();
//        while(line != null)
//        {
//            wantedNumber = getIntFromStringAtPosition();
//            toNodeNumber = getIntFromStringAtPosition();
//            distance = getIntFromStringAtPosition();
//            for(Section current : sectionsInFile)
//            {
//                currentSectionNumber = current.getSectionNumber_();
//                if (currentSectionNumber == wantedNumber)
//                    current.addDistance(toNodeNumber, distance);
//                else
//                if(currentSectionNumber == toNodeNumber)
//                    current.addDistance(wantedNumber, distance);
//            }
//            line = inputFileBuffer.readLine();
//        }
//    }

    /**
     * Function to parse a line following this format "A,B,C,D" with A,B,C and D numbers
     */
    private String getStringAtPosition()
    {
        int spaceIndex = line.indexOf(' ');
        String stringRead = spaceIndex != -1 ? line.substring(0, spaceIndex) : line.substring(0);
        line = line.substring(spaceIndex + 1);
        return stringRead;
    }
}