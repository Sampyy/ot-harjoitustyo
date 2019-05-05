/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class TableTest {
    Table table;
    public TableTest() {
    }
    @Before
    public void setUp(){
        
    }
    @Test
    public void constructorPlacesCorrectAmountOfMines() {
        int xy = 10;
        int minesSet = 10;
        Table table = new Table(xy , xy , minesSet);
        int mines = 0;
        for (int i = 0 ; i < xy ; i++){
            for(int j = 0 ; j < xy ; j++){
                if (table.getNumber(i , j)==9){
                    mines++;
                }
            }
        }
        assertEquals(minesSet , mines);
    }
    @Test
    public void setMineWorks() {
        Table table = new Table(3,3,0);
        table.setMine(1, 0);
        assertEquals(9, table.getNumber(1, 0));
    }
    @Test
    public void setNumbersWorksCorrectly() {
        Table table = new Table(10, 10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table.setCell(0, i, j);
            }
        }
        table.setMine(1, 1);
        table.setNumbers(table.getTable());
        assertEquals(1, table.getNumber(1, 0));
    }
    @Test
    public void testWorks() {
        assertEquals(true, true);
    }
    
    @Test
    public void checkEmptyWorksEmptyField() {
        Table table = new Table(10, 10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table.setCell(0, i, j);
            }
        }
        table.checkEmpty(0, 0);
        int checkeds = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getChecked(i, j)){
                    checkeds++;
                }
            }
        }
        assertEquals(100, checkeds);
    }
    
    
    @Test
    public void setFlagSetsFlags() {
        Table table = new Table(3,3,0);
        table.setFlag(2, 1);
        int flags = 0;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (table.getFlag(i, j) == true){
                    flags++;
                }
            }
        }
        assertEquals(1, flags);
    }
    @Test
    public void chooseCellChecksCell() {
        Table table = new Table(10, 10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table.setCell(0, i, j);
            }
        }
        table.setMine(1, 0);
        table.chooseCell(5, 0);
        assertEquals(true, table.getChecked(5, 0));
    }
    @Test
    public void ChooseCellCanWin() {
        Table table = new Table(10, 10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table.setCell(0, i, j);
            }
        }
        table.setMine(1, 1);
        table.setFlag(1, 1);
        assertEquals(true, table.won());
    }
    @Test
    public void wonWorks(){
        Table table = new Table(10, 10, 10);
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if (table.getNumber(i,j) == 9){
                    table.setFlag(i, j);
                }
            }
        }
        Boolean won = table.won();
        
        assertEquals(true, won);
    }
    
    @Test
    public void loseAddsLosses() {
        Table table = new Table(10, 10, 10);
        table.lose();
        table.lose();
        assertEquals(2, table.getLostGames());
    }
    @Test
    public void winAddsWins() {
        Table table = new Table(10, 10, 10);
        table.win();
        table.win();
        assertEquals(2, table.getWonGames());
    }
}
