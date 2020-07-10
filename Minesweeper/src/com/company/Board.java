package com.company;

//Board class to describe the combination of cell to make up a 2d array [][]
class Board{

    private int boardSize, mineSize;    //Variables to store size of board and number of mines
    private Cell [][] grid;             //Playing board, changes and plays are made here
    private Cell [][] mineBoard;        //Board to keep track of mines
    private String level;               //Store difficult level of player

    //Constructor
    public Board(String level){
        this.level = level;
    }

    //To initialise board for playing
    //Create board and generate mines
    public void initialize(){
        createBoard();
        createMine();
    }

    //Create the board object by creating a board based on difficulty level
    //Initialize board and individual cell
    private void createBoard(){
        switch (this.level) {
            case "beginner":
                boardSize = 9;
                break;
            case "intermediate":
                boardSize = 16;
                break;
            case "expert":
                boardSize = 24;
                break;
        }

        grid = new Cell [boardSize][boardSize];
        mineBoard = new Cell [boardSize][boardSize];

        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                grid[x][y] = new Cell(x, y, false);
                mineBoard[x][y] = new Cell(x, y, false);
            }
        }
    }

    //Create and generate mines into random positions. Number of mines is determined by level.
    private void createMine(){
        switch (this.level) {
            case "beginner":
                mineSize = 10;
                break;
            case "intermediate":
                mineSize = 40;
                break;
            case "expert":
                mineSize = 99;
                break;
        }
        for(int i = 0; i <= mineSize; i++){
            int x_position = (int) (Math.random() * boardSize);
            int y_position = (int) (Math.random() * boardSize);

            if(mineBoard[x_position][y_position].getValue() == 0){
                mineBoard[x_position][y_position].setValue(1);
                mineBoard[x_position][y_position].setMine(true);
            }
        }
    }

    //Check if row and col is within the board limit
    public boolean isValid(int row, int col){
        return (row >= 0 && row < boardSize && col >= 0 && col < boardSize);
    }

    //Check to determine if current index is a mine
    public boolean isMine(int row, int col){
        return mineBoard[row][col].isMine();
    }

    //Function to print board. can be edited to print only one.
    public void displayBoard(){
        System.out.println("Playing grid (9 represent empty cell)");
        for(Cell [] i : grid){
            for(Cell j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }

        System.out.println("\n");
        System.out.println("Display of Mines");
        for(Cell [] i : mineBoard){
            for(Cell j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }

    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell[][] getMineBoard() {
        return mineBoard;
    }
}
