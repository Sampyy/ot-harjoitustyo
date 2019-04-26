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
 *
 * @author Sami
 */
public class Actions implements ActionListener, MouseListener {
    private Table table;
    
    public Actions(Table t) {
        table = t;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        table.reset();
        table.refresh();
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1){
            int x = e.getX() / 20;
            int y = e.getY() / 20;
            
            table.chooseCell(y, x);
            
        }
        if (e.getButton()==3) {
            int x = e.getX() / 20;
            int y = e.getY() / 20;
            
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
