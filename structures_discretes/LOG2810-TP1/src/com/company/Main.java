package com.company;
import com.file.ReadFileLogic;
import com.graph.GraphConsole;
import com.graph.GraphImplement;
import com.menu.Menu;
import com.robot.Robot;
import com.robot.RobotX;
import com.robot.RobotY;
import com.robot.RobotZ;
import com.route.RouteAlgorithm;
import com.sections.Section;
import com.transportObject.TransportObject;
import com.transportObject.TransportObjectA;
import com.transportObject.TransportObjectB;
import com.transportObject.TransportObjectC;

import java.io.IOException;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        //testGraph(reader.getSectionsInFile());
        //testRobot();
        //testObjects();

        Menu mainMenu = new Menu();
    }

    public static void testGraph(HashSet<Section> sectionsInFile) throws IOException {
        GraphConsole graph = new GraphConsole(sectionsInFile);
        graph.display();
    }

    public static void testRobot()
    {
        HashSet<Robot> container = new HashSet<>();
        container.add(new RobotX());
        container.add(new RobotY());
        container.add(new RobotZ());

        for(Robot robot : container) {
            robot.addBaggage(new TransportObjectA());
            System.out.println(robot.toString() + " " + robot.getK());
        }

    }

    public static void testObjects()
    {
        System.out.println(TransportObjectA.getName() + " " + TransportObjectA.getWeight());
        System.out.println(TransportObjectB.getName() + " " + TransportObjectB.getWeight());
        System.out.println(TransportObjectC.getName() + " " + TransportObjectC.getWeight());
    }
}