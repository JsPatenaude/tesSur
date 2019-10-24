package com.company;
import com.file.ReadFileLogic;
import com.graph.GraphImplement;
import com.menu.Menu;
import com.sections.Section;

import java.io.IOException;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFileLogic reader = new ReadFileLogic();
        reader.readFile("entrepot.txt");

        testGraph(reader.getSectionsInFile());


        Menu mainMenu = new Menu();
    }

    public static void testGraph(HashSet<Section> sectionsInFile) throws IOException {
        GraphImplement graph = new GraphImplement(sectionsInFile);
        graph.generate();
        System.out.println(graph.toString());
    }
}