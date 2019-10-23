package com.company;
import com.file.ReadFileLogic;
import com.menu.Menu;

public class Main {

    public static void main(String[] args)
    {
        ReadFileLogic reader = new ReadFileLogic();
        reader.readFile("entrepot.txt");
        Menu mainMenu = new Menu();

    }
}