/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;

import minesweeper.domain.Table;
import minesweeper.domain.Cell;
import java.util.Scanner;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.Application;
/**
 *
 * @author Sami
 */
public class Main {
    private Table table;
    private Scene gameScreen;
    
    
    public static void main(String[] args) {
        //Prints the table to verify that it is created correctly. 9 = mine, others have the correct amount of mines in adjacent cells.
        /*int x = 9;
        int y = 9;
        Table table = new Table(x, y, 1);
        for (int i = 0; i < x; i++){
            System.out.println("");
            for (int j = 0; j < y; j++){
                System.out.print(table.getNumber(i, j));
            }
        }
        */Scanner lukija = new Scanner(System.in);/*
        System.out.println("");
        table.printTable();
        System.out.println("");
        table.setFlag(5, 8);*/
        while (true){
            System.out.println("Type the amount of rows");
            int x = Integer.valueOf(lukija.nextLine());
            System.out.println("Type the amount of columns");
            int y = Integer.valueOf(lukija.nextLine());
            System.out.println("Type the amount of mines");
            int mines = Integer.valueOf(lukija.nextLine());
            Table table = new Table (x, y, mines);
            
        while (table.gameIsOn == true){
            System.out.println("Give the cell you wish to check as x,y,f. f if you wish to flag, anything else if you don't want to.");
            String[] split = lukija.nextLine().split(",");
            int first = Integer.valueOf(split[0]);
            int second = Integer.valueOf(split[1]);
            
            if (first < 0 || first > x-1 || second < 0 || second > y-1){
                System.out.println("Cell is not within the table");
                continue;
            }
            if (split[2].equals("f")){
                table.setFlag(first, second);
                table.printTable();
            }
            else {
                table.chooseCell(first, second);
            }
            }
            System.out.println("type x if you wish to exit, else type anything");
            if (lukija.nextLine().equals("x")){
                break;
            }
        }
    }
    
}
