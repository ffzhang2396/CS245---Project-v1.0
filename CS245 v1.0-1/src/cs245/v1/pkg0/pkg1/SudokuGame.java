/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * 
 */
public class SudokuGame extends JPanel {
    private SudokuGameEngine engine;
    private MainFrame main;
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
    
    
    
}
