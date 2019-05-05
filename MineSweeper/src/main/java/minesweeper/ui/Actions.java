/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;
import minesweeper.domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Handles the interactivity of the minefield
 * @author Sami
 */
public class Actions implements ActionListener, MouseListener {
    private Table table;
    private final int cellSize = 20;
    
    public Actions(Table t) {
        table = t;
    }
    /**
     * provids the functionality for reset button
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        table.reset();
        table.refresh();   
    }
    /**
     * If left clicked, checks the cell. If rightclicked, will flag or unflag depending on if the cell is flagged.
     * @param e mouseevent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;
            
            table.chooseCell(y, x);
            
        }
        if (e.getButton() == 3) {
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;
            
            table.setFlag(y, x);
        }
        table.refresh();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
 
    @Override
    public void mouseExited(MouseEvent e) {
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
