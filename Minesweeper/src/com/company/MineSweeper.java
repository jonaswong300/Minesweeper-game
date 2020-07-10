package com.company;

import java.util.Scanner;

class MineSweeper{

    //Declare variables
    private Scanner input = new Scanner(System.in);
    private Board board;

    //Prompt user to ask for difficulty level player wants to play in
    public void prompt(){
        boolean ok = false;
        int level = 0;
        String stage = "";

        do{
            System.out.println("Please enter the difficultly you would like to play in: ");
            System.out.println("1. Beginner");
            System.out.println("2. Intermediate");
            System.out.println("3. Expert");
            System.out.println("Choice: ");

            if(input.hasNextInt()){
                level = input.nextInt();
                ok = true;
            }

            System.out.println("You have entered an invalid input, please try again.");

        }while(!ok);

        switch(level){
            case 1:
                stage = "beginner";
                break;
            case 2:
                stage = "intermediate";
                break;
            case 3:
                stage = "expert";
                break;
        }

        board = new Board(stage);
    }

    //Function to play minesweeper game
    public void play(){
        boolean explode = false;
        int row, col;

        prompt();
        board.initialize();
        board.displayBoard();

        do{
            System.out.println("\nX coordinates (1 - 9), Y coordinates (1 - 9)");
            System.out.println("Enter your X & Y coordinate: ");

            row = input.nextInt();
            col = input.nextInt();

            int row_index = row - 1;
            int col_index = col - 1;

            if(board.getGrid()[row_index][col_index].getValue() == 0){
                if(!continueGame(row_index, col_index)){
                    System.out.println("Game Over, you step on a Mine!");
                    explode = true;
                }
            }

        }while(!explode);
    }

    //Check if user has entered a valid position
    //Check if user has step on a mine
    private boolean continueGame(int row, int col){
        if(!board.getMineBoard()[row][col].isMine()){
            int numberOfMines = countNearByMine(row, col);

            if(numberOfMines == 0){
                revealNearby(row, col);
            }else{
                board.getGrid()[row][col].setValue(numberOfMines);
            }
            board.displayBoard();
            return true;
        }else{
            return false;
        }
    }

    //Return number of mines surrounding index that the user has input
    private int countNearByMine(int row, int col){
        int mineCounter = 0;

        /*
        Count all the mines in the 8 adjacent
        cells

            N.W   N   N.E
              \   |   /
               \  |  /
            W----Cell----E
                 / | \
               /   |  \
            S.W    S   S.E

        Cell-->Current Cell (row, col)
        N -->  North        (row-1, col)
        S -->  South        (row+1, col)
        E -->  East         (row, col+1)
        W -->  West         (row, col-1)
        N.E--> North-East   (row-1, col+1)
        N.W--> North-West   (row-1, col-1)
        S.E--> South-East   (row+1, col+1)
        S.W--> South-West   (row+1, col-1)
    */

        //----------- 1st Neighbour (North) ------------
        if(board.isValid(row - 1, col) && board.isMine(row - 1, col)){
            mineCounter++;
        }

        //----------- 2nd Neighbour (South) ------------
        if(board.isValid(row + 1, col) && board.isMine(row + 1, col)){
            mineCounter++;
        }

        //----------- 3rd Neighbour (East) ------------
        if(board.isValid(row, col + 1) && board.isMine(row, col + 1)){
            mineCounter++;
        }

        //----------- 4th Neighbour (West) ------------
        if(board.isValid(row, col - 1) && board.isMine(row, col - 1)){
            mineCounter++;
        }

        //----------- 5th Neighbour (North-East) ------------
        if(board.isValid(row - 1, col + 1) && board.isMine(row - 1, col + 1)){
            mineCounter++;
        }

        //----------- 6th Neighbour (North-West) ------------
        if(board.isValid(row - 1, col - 1) && board.isMine(row - 1, col - 1)){
            mineCounter++;
        }

        //----------- 7th Neighbour (South-East) ------------
        if(board.isValid(row + 1, col + 1) && board.isMine(row + 1, col + 1)){
            mineCounter++;
        }

        //----------- 8th Neighbour (South-West) ------------
        if(board.isValid(row + 1, col - 1) && board.isMine(row + 1, col - 1)){
            mineCounter++;
        }

        System.out.println("Number of mines nearby: " + mineCounter + "\n\n");
        return mineCounter;
    }

    //If there are 0 mines in the surrounding area of 3 x 3, reveal the area with a number "9"
    private void revealNearby(int row, int col){
        /*
            N.W   N   N.E
              \   |   /
               \  |  /
            W----Cell----E
                 / | \
               /   |  \
            S.W    S   S.E

        Cell-->Current Cell (row, col)
        N -->  North        (row-1, col)
        S -->  South        (row+1, col)
        E -->  East         (row, col+1)
        W -->  West         (row, col-1)
        N.E--> North-East   (row-1, col+1)
        N.W--> North-West   (row-1, col-1)
        S.E--> South-East   (row+1, col+1)
        S.W--> South-West   (row+1, col-1)
    */
        //Index itself is set to 0
        board.getGrid()[row][col].setValue(9);

        //----------- 1st Neighbour (North) ------------
        if(board.isValid(row - 1, col)){
            board.getGrid()[row - 1][col].setValue(9);
        }

        //----------- 2nd Neighbour (South) ------------
        if(board.isValid(row + 1, col)){
            board.getGrid()[row + 1][col].setValue(9);
        }

        //----------- 3rd Neighbour (East) ------------
        if(board.isValid(row, col + 1)){
            board.getGrid()[row][col + 1].setValue(9);
        }

        //----------- 4th Neighbour (West) ------------
        if(board.isValid(row, col - 1) ){
            board.getGrid()[row][col - 1].setValue(9);
        }

        //----------- 5th Neighbour (North-East) ------------
        if(board.isValid(row - 1, col + 1)){
            board.getGrid()[row - 1][col + 1].setValue(9);
        }

        //----------- 6th Neighbour (North-West) ------------
        if(board.isValid(row - 1, col - 1)){
            board.getGrid()[row - 1][col - 1].setValue(9);
        }

        //----------- 7th Neighbour (South-East) ------------
        if(board.isValid(row + 1, col + 1)){
            board.getGrid()[row + 1][col + 1].setValue(9);
        }

        //----------- 8th Neighbour (South-West) ------------
        if(board.isValid(row + 1, col - 1) ){
            board.getGrid()[row + 1][col - 1].setValue(9);
        }
    }

}