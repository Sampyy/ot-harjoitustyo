/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

/**
 *
 * @author Sami
 */
public class Cell {
    boolean checked;
    int contains;
    boolean flagged;
    //contains 0 = empty, 1-8 amount of mines in the adjacent cells, 9 = mine
    
    public Cell()   {
        this.checked = false;
        this.flagged = false;

    }
    
    public boolean checkCell(){
        this.checked = true;
        if (this.contains == 9){
            return true;
            //lose the game
        }
        return false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    
    
}
