/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 *
 */
public class SudokuGame extends JPanel {

    private SudokuGameEngine engine;
    private MainFrame main;
    private JTextField[][] boxes = new JTextField[9][9];
    private JPanel board = new JPanel();

    public SudokuGame(SudokuGameEngine engine) {
        this.engine = engine;
        setLayout(new BorderLayout());
    }

    public void setMain(MainFrame main) {
        this.main = main;
    }

    /*
    For the sudoku board we are going to use a
    9x9 gridLayout for the inner JPanel thats going to be in the center of the 
    outer JPanel which is an instance of this class.
     */
    private void createBoard() {

    }

    /*
    This method draws the title bar of the game panel.
    Title bar should include the time as well as a stylized
    version of the game name. 
     */
    private void titleBar() {
        JPanel title = new JPanel(new BorderLayout());

    }

    /*
    adds in the submit button that goes on the left
    side of the game board.
     */
    private void submitButton() {
    }

    /*
    This method adds in the submit button that goes on the right
    side of the game board. 
     */
    private void quitButton() {

    }
    
    /*
    initializes the board to a new state.
    Probably can use this to restart the game.
    */
    private void initBoard() {
        int[][] board = engine.getBoard();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boxes[i][j] = new JTextField();
                if (board[i][j] != -1) {
                    boxes[i][j].setText(Integer.toString(board[i][j]));
                    boxes[i][j].setEditable(false);                   
                } else {
                    boxes[i][j].setText("");
                    boxes[i][j].setEditable(true);
                }
            }
        }
    }
    
    /*
    need to add a separate action listener that listens to 
    when the JtextFields are modified with user input.
    This listener needs to be different so that it does not 
    mess with the action listeners for the buttons.
    */
    private class gridListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField src = (JTextField) e.getSource();
            
            
            
        }
        
        /*
        this method is used for finding the x and y values
        for the engine to check if the value is a valid input.
        */
        private void findSrc() {
            
        }
        
    }

}
