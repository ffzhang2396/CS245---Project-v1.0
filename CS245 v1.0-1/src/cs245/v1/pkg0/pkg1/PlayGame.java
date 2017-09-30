/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *This will the the actual GUI for the game. I implemented
 * action listener so we can just use one action listener
 * instead of creating a separate listener for every JButton
 * for each letter of the alphabet. This class will also be the
 * controller for the backend, so game update methods should be
 * written in here for backend communication.
 * 
 */
public class PlayGame extends JPanel implements ActionListener {

    public PlayGame() {
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
}
