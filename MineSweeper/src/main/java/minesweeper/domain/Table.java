/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;
import minesweeper.ui.Board;
import minesweeper.ui.Actions;
import java.util.Random;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JSlider;
/**
 * Provides the playing fields functionality, uses Cells as the pieces of the field
 * 
 * 
 * @author Sami
 */
public class Table extends JFrame {
    private Cell[][] table;
    private int width, height, mines, wonGames, lostGames;
    private Random random = new Random();
    public Boolean gameIsOn = true;
    private Actions actions;
    private Board board;
    private JButton reset;
    private JSlider size, difficulty;
    
    public Table(Cell[][] table) {
        this.table = table;
    }
    /**
     * Method sets up a minesweeper game with the given values
     * 
     * @param  x   width of the field
     * @param  y   height of the field
     * @param  mines the amount of mines set on the field
    
    */
    public Table(int x, int y, int mines) {
        this.width = x;
        this.height = y;
        this.mines = mines;
        this.wonGames = 0;
        this.lostGames = 0;
        difficulty = new JSlider(JSlider.HORIZONTAL, 5, 50, 10);
        size = new JSlider(JSlider.VERTICAL, 5, 50, 10);
        
        reset = new JButton("Reset");
         
        add(size, BorderLayout.WEST);
        add(difficulty, BorderLayout.NORTH);
        add(reset, BorderLayout.SOUTH);
        
        reset.addActionListener(new Actions(this));
        reset();
    }
    
    /**
     * 
     * method starts resets the game, rearranging the mines and covering every cell.
     */
    public void reset() {
        gameIsOn = true;
        this.table = new Cell[width][height];
        if (this.size.getValue() != this.width) {
            this.width = this.size.getValue();
            this.height = this.size.getValue();
            this.table = new Cell[width][height];
        }
        if (difficulty.getValue() != mines) {
            this.mines = difficulty.getValue() * size.getValue() * size.getValue() / 100;
        }    
        this.board = new Board(this);
            
        add(board, BorderLayout.CENTER);
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        placeCells();
        placeMines();
        setNumbers(this.table);
    }
    /**
     * Places Cells into the table.
     */
    public void placeCells() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new Cell();
            }
        }
    }
    /**
     * Places mines into the table
     */
    public void placeMines() {
        int minesSet = 0;
        while (minesSet < mines) {
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    if (random.nextInt((this.width * this.height / mines)) == 1 && minesSet < mines && table[i][j].getContains() != 9) {
                        table[i][j].setContains(9);
                        minesSet++;
                    }
                }
            }
        }   
    }
    public void refresh() {
        board.repaint();
    }
    public int getNumber(int x, int y) {
        return table[x][y].getContains();
    }
    public int getwidth() {
        return width;
    }
    public int getheight() {
        return height;
    }
    
    /** 
     * Method handles the event of choosing a cell to check,revealing the chosen cell,
     * if it's a mine losing the game, and if it is empty also revealing every other
     * connected empty cell.
     * 
     * @param x the x coordinate of the chosen cell in the field
     * @param y the y coordinate of the chosen cell in the field
     */
    public void chooseCell(int x, int y) {
        if (table[x][y].isFlagged()) {
            return;
        }
        table[x][y].setChecked(true);
        
        
        if (table[x][y].getContains() == 0) {
            checkEmpty(x , y);
            resetUnneededFlags();
        }
        refresh();
        if (table[x][y].getContains() == 9) {
            lose();
        }
        if (won()) {
            win();
        }
        
    }
    /**
     * sets the gamestate to off, reveals all unchecked cells, adds a game to the lost games and tells you that you lost. Then resets the game
     *
     */
    public void lose() {
        gameIsOn = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!table[i][j].isChecked()) {
                    table[i][j].setChecked(true);
                }
            }
        }
        lostGames++;
        refresh();
        JOptionPane.showMessageDialog(null, "You hit a mine and lost.\n Won Games: " + wonGames + " Lost Games: " + lostGames);
        reset();
    }
    /**
     * sets the gamestate to off, reveals all unchecked cells, adds a game to the won games and tells you that you won. Then resets the game.
     */
    public void win() {
        gameIsOn = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!table[i][j].isChecked()) {
                    table[i][j].setChecked(true);
                }
            }
        }
        wonGames++;
        refresh();
        JOptionPane.showMessageDialog(null, "Congratulations, you won.\n Won Games: " + wonGames + " Lost Games: " + lostGames);
        reset();
    }
    /**
     * Checks if the game is in a state where you have won (all mines marked, no unnecessary flags)
     * @return true if won, false otherwise
     */
    public boolean won() {
        int flags = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (table[i][j].getContains() == 9 && table[i][j].isFlagged()) {
                    flags++;
                }
            }
        }
        if (flags == this.mines) {
            return true;
        }
        return false;
    }
    /**
     * Handles checking for connected empty cells
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     */
    public void checkEmpty(int x , int y) {
        table[x][y].setChecked(true);
        if (x > 0) {
            if (table[x - 1][y].getContains() == 0 && table[x - 1][y].isChecked() == false) {
                checkEmpty(x - 1 , y);
            }
        }
        if (x < table[0].length - 1) {
            if (table[x + 1][y].getContains() == 0 && table[x + 1][y].isChecked() == false) {
                checkEmpty(x + 1 , y);
            }
        }
        if (y > 0) {
            if (table[x][y - 1].getContains() == 0 && table[x][y - 1].isChecked() == false) {
                checkEmpty(x , y - 1);
            }
        }
        if (y < table[0].length - 1) {
            if (table[x][y + 1].getContains() == 0 && table[x][y + 1].isChecked() == false) {
                checkEmpty(x , y + 1);
            }
        }   
    }
    /** 
     * Handles setting numbers with the help of setNumber, which finds out the 
     * number of a specific cell
     * 
     * @see   minesweeper.domain.Table#setNumber(minesweeper.domain.Cell[][], int, int) 
     * @param table the field currently in use
     */
    public void setNumbers(Cell[][] table) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (table[i][j].getContains() == 9) {
                    continue;
                }
                this.table[i][j].setContains(setNumber(table , i , j));
            }
        }
    }
    /**
     * Finds out which number the given cell should get.
     * @param table table in use
     * @param i the coordinate of the cell
     * @param j the coordinate of the cell
     * @return returns the amount of mines nearby
     */
    public int setNumber(Cell[][] table, int i, int j) {
        int minesNearby = 0;
        for (int h = -1; h <= 1; h++) {
            for (int w = -1; w <= 1; w++) {
                if (i + h < 0 || i + h >= table.length || j + w < 0 || j + w >= table.length) {
                    continue;
                }
                else if (table[h + i][w + j].getContains() == 9) {
                    minesNearby++;
                }
            }
        }
        return minesNearby;
    }
    
    /**
     * Sets a flag in the (x,y) cell, or removes it if already flagged
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     */
    public void setFlag(int x, int y) {
        if (this.table[x][y].isFlagged()) {
            this.table[x][y].setFlagged(false);
        }
        else {
            this.table[x][y].setFlagged(true);
        }
        resetUnneededFlags();
        refresh();
    }
    /**
     * Removes flags from cells that are checked, for example if an empty cells it checked
     * but it gets revealed
     */
    private void resetUnneededFlags() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (table[i][j].isChecked()) {
                    table[i][j].setFlagged(false);
                }
            }
        }
    }
    /**
     * returns whether a cell is flagged
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return boolean flagged
     */
    public boolean getFlag(int x, int y) {
        return this.table[x][y].isFlagged();
    }
    
    public boolean getChecked(int x, int y) {
        return this.table[x][y].isChecked();
    }
    
    public void setMine(int x, int y) {
        this.table[x][y].setContains(9);
    }
    
    public Cell[][] getTable() {
        return this.table;
    }
    /**
     * used for tests
     * @param number number to be set to the cell
     * @param x coordinates of the cell
     * @param y coordinates of the cell
     */
    public void setCell(int number, int x, int y) {
        this.table[x][y].setContains(number);
    }
    
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println("");
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j].toString());
            }
        }
    }
}
    

