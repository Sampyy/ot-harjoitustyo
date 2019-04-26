/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.ui;
import minesweeper.domain.*;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 * Provides the graphics to be used in the gamefield
 * @author Sami
 */
public class Board extends JPanel {
    private Table table;
    private  Cell[][] cells;
    
    public Board(Table t) {
        table = t;
        cells = t.getTable();
        
        addMouseListener(new Actions(t));
        setPreferredSize(new Dimension(cells.length * 20, cells[0].length * 20));
    }
    /** 
     * Loads the images to be used in the field from src/resources, and figures 
     * which of the images to use in the situation.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        cells = table.getTable();
        Image[] img = new Image[14];
        for (int i = 0; i < 14; i++) {
            String path = "resources/" + i + ".png";
            img[i] = (new ImageIcon(path)).getImage();
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell curr = cells[i][j];
                int imgToDraw = 0;
                
                if (curr.isFlagged()) {
                    if (curr.isMine() && !table.gameIsOn) {
                        imgToDraw = 11;
                    }
                    else if (!table.gameIsOn) {
                        imgToDraw = 13;
                    }
                    else {
                        imgToDraw = 9;
                    }
                }
                else if (!curr.isChecked()) {
                    imgToDraw = 10;
                }
                else if (curr.isMine()) {
                    imgToDraw = 12;
                }
                else {
                    imgToDraw = 0;
                }
                if (curr.isChecked() && !curr.isMine()) {
                    imgToDraw = curr.getContains();
                }
                g.drawImage(img[imgToDraw], (j * 20), (i * 20), this);
            }
        }
    }
}
