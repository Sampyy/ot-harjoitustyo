/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

/**
 * Cells contain whether the different squares in the game are checked, mines, or flagged.
 * @author Sami
 */
public class Cell {
    private boolean checked;
    private int contains;
    private boolean flagged;
    //contains 0 = empty, 1-8 amount of mines in the adjacent cells, 9 = mine
    /**
     * Cells are not checked or flagged by default
     */
    public Cell()   {
        this.checked = false;
        this.flagged = false;

    }
    /**
     * 
     * @return whether the cell has been checked or not
     */
    public boolean isChecked() {
        return checked;
    }
    /**
     * 
     * @return returns whether the cell is a mine or not
     */
    public boolean isMine() {
        if (this.contains == 9) {
            return true;
        }
        return false;
    }
    /**
     * 
     * @param checked sets whether the cell is checked or not
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public int getContains() {
        return contains;
    }
    /**
     * sets contains
     * @param contains 0 = empty, 1-8 amount of mines in the adjacent cells, 9 = mine
     */
    public void setContains(int contains) {
        this.contains = contains;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    /**
     * not in use anymore
     * @return returns F if the field is flagged, - if it hasn't been checked yet
     * and the value otherwise
     */
    @Override
    public String toString() {
        
        if (this.flagged == true) {
            return "F";
        }
        else if (this.checked == false) {
            return "-";
        }
        else {
            return Integer.toString(this.contains);
        }
    }
    
}
