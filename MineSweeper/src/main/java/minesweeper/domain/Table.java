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
/**
 *
 * @author Sami
 */
public class Table extends JFrame{
    private Cell[][] table;
    private int width, height, mines;
    private Random random = new Random();
    public Boolean gameIsOn = true;
    private Board board;
    private JButton reset;
    
    public Table(Cell[][] table){
        this.table = table;
    }
    public Table(int x, int y, int mines){
        this.width = x;
        this.height = y;
        this.mines = mines;
        this.table = new Cell[x][y];
        for (int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                table[i][j] = new Cell();
            }
        }
        reset();
        board = new Board(this);
        reset = new JButton("Reset");
        
        add(board, BorderLayout.CENTER);
        add(reset,BorderLayout.SOUTH);
        
        reset.addActionListener(new Actions(this));
        /*for ( int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                table[i][j].setChecked(true);
            }
        }*/
        
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    public void reset(){
        gameIsOn = true;
        int minesSet = 0;
        while (minesSet < mines){
            for (int i = 0; i < this.width; i++){
                for (int j = 0 ; j < this.height; j++){
                    if (random.nextInt((this.width * this.height / mines))==1 && minesSet < mines && table[i][j].getContains()!=9){
                        table[i][j].setContains(9);
                        minesSet++;
                    }
                }
            }
        }   
        setNumbers(this.table);
    }
    public void refresh(){
        board.repaint();
    }
    public int getNumber(int x, int y){
        return table[x][y].getContains();
    }
    public int getx(){
        return width;
    }
    public int gety(){
        return height;
    }
    
    public void chooseCell(int x, int y){
        if (table[x][y].isFlagged()){
            return;
        }
        table[x][y].setChecked(true);
        resetUnneededFlags();
        refresh();
        if (table[x][y].getContains()==0){
            checkEmpty(x,y);
        }
        if (table[x][y].getContains()==9){
            lose();
        }
        if (won()){
            win();
        }
    }
    public void lose(){
        gameIsOn=false;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (!table[i][j].isChecked()){
                    table[i][j].setChecked(true);
                }
            }
        }
        refresh();
        JOptionPane.showMessageDialog(null, "You hit a mine and lost.");
        reset();
    }
    public void win(){
        gameIsOn=false;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (!table[i][j].isChecked()){
                    table[i][j].setChecked(true);
                }
            }
        }
        refresh();
        JOptionPane.showMessageDialog(null, "Congratulations, you won.");
        reset();
    }
    public boolean won(){
        int flags = 0;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (table[i][j].getContains()==9 && table[i][j].isFlagged()){
                    flags++;
                }
            }
        }
        if (flags == this.mines){
            return true;
        }
            return false;
    }
    public void checkEmpty(int x, int y){
        table[x][y].setChecked(true);
        if (x > 0){
            if (table[x-1][y].getContains()==0 && table[x-1][y].checked==false){
                checkEmpty(x-1, y);
            }
        }
        if (x < table[0].length-1){
            if (table[x+1][y].getContains() == 0 && table[x+1][y].checked==false){
                checkEmpty(x+1,y);
            }
        }
        if (y > 0){
            if (table[x][y-1].getContains()==0 && table[x][y-1].checked==false){
                checkEmpty(x, y-1);
            }
        }
        if (y < table[0].length-1){
            if (table[x][y+1].getContains() == 0 && table[x][y+1].checked==false){
                checkEmpty(x,y+1);
            }
        }   
    }
    public void setNumbers(Cell[][] table){
        for (int i = 0; i < table.length; i++){
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].getContains()== 9){
                    continue;
                }
                this.table[i][j].setContains(setNumber(table, i, j));
            }
        }
    }
    
    public int setNumber (Cell[][] table, int i, int j){
        int minesNearby=0;
        for (int h = -1; h <= 1; h++){
            for (int w = -1; w <=1; w++){
                if (i+h < 0||i+h >= table.length||j+w<0||j+w>=table.length){
                    continue;
                }
                else if(table[h+i][w+j].getContains()==9){
                    minesNearby++;
                }
            }
        }
        return minesNearby;
    }
    
    public void setFlag(int x, int y){
        if (this.table[x][y].isFlagged()){
            this.table[x][y].setFlagged(false);
        }
        else{
            this.table[x][y].setFlagged(true);
        }
        resetUnneededFlags();
        refresh();
    }
    
    private void resetUnneededFlags(){
        for (int i = 0; i < width ; i++){
            for (int j = 0; j < height; j++){
                if (table[i][j].isChecked()){
                    table[i][j].setFlagged(false);
                }
            }
        }
    }
    public boolean getFlag(int x, int y){
        return this.table[x][y].flagged;
    }
    
    
    public void setMine(int x, int y){
        this.table[x][y].setContains(9);
    }
    public Cell[][] getTable(){
        return this.table;
    }
    
    public void printTable(){
        for (int i = 0; i < table.length; i++){
            System.out.println("");
            for (int j = 0; j < table[0].length;j++){
                System.out.print(table[i][j].toString());
            }
        }
    }
}
    

