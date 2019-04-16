/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;
import java.util.Random;
/**
 *
 * @author Sami
 */
public class Table {
    private Cell[][] table;
    private int mines;
    private Random random = new Random();
    public Boolean gameIsOn = true;
    
    public Table(Cell[][] table){
        this.table = table;
    }
    public Table(int x, int y, int mines){
        this.table = new Cell[x][y];
        for (int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                table[i][j] = new Cell();
            }
        }
        this.mines = 0;
        while (this.mines < mines){
            for (int i = 0; i < x; i++){
                for (int j = 0 ; j < x; j++){
                    if (random.nextInt((x * y / mines))==1 && this.mines < mines && table[i][j].getContains()!=9){
                        table[i][j].setContains(9);
                        this.mines++;
                    }
                }
            }
        }   
    setNumbers(this.table);
    }
    public int getNumber(int x, int y){
        return table[x][y].getContains();
    }
    
    public void chooseCell(int x, int y){
        boolean isChecked = table[x][y].checked;
        if (isChecked == false){
            boolean cell = table[x][y].checkCell();
            if (getNumber(x, y) == 0){
                checkEmpty(x,y);
            }
            if (cell == true){
                //loseGame();
                System.out.println("hävisit pelin");
                gameIsOn = false;
            }
            else{
                printTable();
            }
        }
        else{
            System.out.println("Solu on jo tarkistettu");
        }
        
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
        if (this.table[x][y].flagged==true){
            this.table[x][y].setFlagged(false);
        }
        else{
            this.table[x][y].setFlagged(true);
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
    

