/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import javax.swing.*;
import java.awt.*;

/**
 *This class is meant for the ending screen once user has finished the game
 * or has clicked the skip button.
 */
public class GameOver extends JPanel {
    private JButton done = new JButton();
    private MainFrame main;
    
    public GameOver() {
        
    }
    
    public void setMain(MainFrame panel) {
        this.main = panel;
    }
}
