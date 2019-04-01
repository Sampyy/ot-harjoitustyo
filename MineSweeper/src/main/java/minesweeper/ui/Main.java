/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;

import minesweeper.domain.Table;
import minesweeper.domain.Cell;

/**
 *
 * @author Sami
 */
public class Main {

    
    public static void main(String[] args) {
        //Prints the table to verify that it is created correctly. 9 = mine, others have the correct amount of mines in adjacent cells.
        int x = 9;
        int y = 9;
        Table table = new Table(x, y, 15);
        for (int i = 0; i < x; i++){
            System.out.println("");
            for (int j = 0; j < y; j++){
                System.out.print(table.getNumber(i, j));
            }
        }
        System.out.println("");
        Cell cell = new Cell();
        cell.setContains(9);
        System.out.println(cell.getContains());
        System.out.println(cell.checkCell());
    }
    
}
