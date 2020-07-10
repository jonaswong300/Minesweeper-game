package com.company;

//Class the describes the individual cell/index of a 2d array [][].
class Cell{
    private int x;          //X coordinate
    private int y;          //Y coordinate
    private boolean isMine; //Boolean to check if cell is mine
    private int value;      //Value to stored and record how many nearby mines or if it is an empty cell

    Cell(int x, int y, boolean isMine){
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.value = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "" + getValue();
    }
}
