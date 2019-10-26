package com.file;

import com.sections.Section;
import it.unimi.dsi.fastutil.Hash;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;

public class ReadFileLogic {

    private HashSet<Section> sectionsInFile = new HashSet<Section>();
    private String line;

    public boolean readFile(String filePath)
    {
        BufferedReader inputFileBuffer;
        try {
            inputFileBuffer = new BufferedReader(new FileReader(filePath));
            line = inputFileBuffer.readLine();
            readSections(inputFileBuffer);
            readDistances(inputFileBuffer);
            return true;
        } catch (IOException error) {
            error.printStackTrace();
            System.out.println("Error Warehouse's file could not be opened.");
            return false;
        }
    }

    /**
     * Getter for the attribute sectionInFile
     * @return attribute sectionInFile containing the different sections read from the file
     */
    public HashSet<Section> getSectionsInFile() { return sectionsInFile; }

    /**
     * Read the sections with their information from a Buffer and store them in a set
     * @param inputFileBuffer BufferReader from where the info should be read
     * @return void
     */
    private void readSections(BufferedReader inputFileBuffer) throws IOException
    {
        while(!line.isEmpty())
        {
            sectionsInFile.add(new Section(
                    getIntFromStringAtPosition(),
                    getIntFromStringAtPosition(),
                    getIntFromStringAtPosition(),
                    getIntFromStringAtPosition()
            ));
            line = inputFileBuffer.readLine();
        }
    }

    /**
     * Read the distances between sections from a Buffer and store them in a
     * @param inputFileBuffer BufferReader from where the info should be read
     * @return void
     */
    private void readDistances(BufferedReader inputFileBuffer) throws IOException
    {
        int wantedNumber, toNodeNumber, distance, currentSectionNumber;
        line = inputFileBuffer.readLine();
        while(line != null)
        {
            wantedNumber = getIntFromStringAtPosition();
            toNodeNumber = getIntFromStringAtPosition();
            distance = getIntFromStringAtPosition();
            for(Section current : sectionsInFile)
            {
                currentSectionNumber = current.getSectionNumber_();
                if (currentSectionNumber == wantedNumber)
                    current.addDistance(toNodeNumber, distance);
                else
                    if(currentSectionNumber == toNodeNumber)
                        current.addDistance(wantedNumber, distance);
            }
            line = inputFileBuffer.readLine();
        }
    }

    /**
     * Function to parse a line following this format "A,B,C,D" with A,B,C and D numbers
     */
    private int getIntFromStringAtPosition()
    {
        int commaIndex = line.indexOf(',');
        Integer number = commaIndex != -1 ? Integer.parseInt(line.substring(0, commaIndex)) : Integer.parseInt(line.substring(0));
        line = line.substring(commaIndex + 1);
        return number.intValue();
    }
}