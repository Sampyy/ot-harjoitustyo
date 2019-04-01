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
    public void constructorPlacesCorrectAmountOfMines(){
        Table table = new Table(3, 3, 3);
        int mines = 0;
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (table.getNumber(i, j)==9){
                    mines++;
                }
            }
        }
        assertEquals(3, mines);
    }
    @Test
    public void setMineWorks(){
        Table table = new Table(3,3,0);
        table.setMine(1, 0);
        assertEquals(9, table.getNumber(1, 0));
    }
    @Test
    public void setNumbersWorksCorrectly(){
        Table table = new Table(3,3,0);
        table.setMine(1, 1);
        table.setNumbers(table.getTable());
        assertEquals(1, table.getNumber(1, 0));
    }
    @Test
    public void testWorks(){
        assertEquals(true, true);
    }
    
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     //@Test
     //public void hello() {}
}
