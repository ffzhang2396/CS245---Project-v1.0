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
    private CardLayout layout = new CardLayout();
    private JPanel holdPanel = new JPanel();
    private JPanel thunk1 = new JPanel();
    private JPanel thunk2 = new JPanel();
    
    public HighScore() {
        JButton backButton = new JButton("Back");
        JPanel buttons = new JPanel(new BorderLayout());
        

        
        setLayout(new BorderLayout());
        holdPanel.setLayout(layout);
        

        
        
        
        buttons.add(backButton, BorderLayout.PAGE_END);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.LINE_START);
        add(holdPanel, BorderLayout.CENTER);
        
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startMenuTimer();
                main.swapView("menu");
            }
            
        });
        
        
        
    }
    
    
    
    
    
    
    
    public void setMain(MainFrame panel) {
        this.main = panel;
    }
}
