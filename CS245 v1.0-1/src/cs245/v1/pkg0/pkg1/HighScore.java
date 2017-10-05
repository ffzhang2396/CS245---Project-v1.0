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
 *Need to figure out how to display high scores.
 * needs to have unselectable displays
 * 
 */
public class HighScore extends JPanel {
    private MainFrame main;

    
    public HighScore() {
        JButton backButton = new JButton("Back");
        JPanel buttons = new JPanel(new BorderLayout());
        

        
        setLayout(new BorderLayout());

 
        buttons.add(backButton, BorderLayout.PAGE_END);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.LINE_START);

        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startMenuTimer();
                main.swapView("menu");
            }
            
        });
    }
    
   
    /*
    This method is used to set this instance of MainFrame to the current
    main frame so that the swapview method can be accessed to change the
    panel back to the main menu once the back button is pressed.
    */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }
}
