/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

/**
 *
 * @author dieha
 */
public class SudokuGameEngine {

    private int[][] answer = {
        {8, 3, 5, 4, 1, 6, 9, 2, 7},
        {2, 9, 6, 8, 5, 7, 4, 3, 1},
        {4, 1, 7, 2, 9, 3, 6, 5, 8},
        {5, 6, 9, 1, 3, 4, 7, 8, 2},
        {1, 2, 3, 6, 7, 8, 5, 4, 9},
        {7, 4, 8, 5, 2, 9, 1, 6, 3},
        {6, 5, 2, 7, 8, 1, 3, 9, 4},
        {9, 8, 1, 3, 4, 5, 2, 7, 6},
        {3, 7, 4, 9, 6, 2, 8, 1, 5}};

    //initial starting board, -1 means the 
    //box should be empty.
    private int[][] board = {
        {8, -1, -1, 4, -1, 6, -1, -1, 7},
        {-1, -1, -1, -1, -1, -1, 4, -1, -1},
        {5, -1, 9, -1, -1, 3, -1, 7, 8, -1},
        {-1, -1, -1, -1, 7, -1, -1, -1, -1},
        {-1, 4, 8, -1, 2, -1, 1, -1, 3},
        {-1, 5, 2, -1, -1, -1, -1, 9, -1},
        {-1, -1, 1, -1, -1, -1, -1, -1, -1},
        {3, -1, -1, 9, -1, 2, -1, -1, 5},};

    //used to update the backend board. 
    public void updateBoard(int[][] newBoard) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    /*
    used to return the board so the GUI can use it
    to populate the visual.
    */
    public int[][] getBoard() {
        return board;
    }
    
    /*
    This method is called whenver the user enters a number
    into the text field to check if it is a valid input.
    */
    public boolean checkInput(int row, int col) {
        boolean rowValid = false;
        boolean colValid = false;
        
        for (int i = 0; i < board.length; i++) {
            
        }       
        return false;
    }

}
