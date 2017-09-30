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
 * 
 */
public class HighScore extends JPanel {
    private MainFrame main;
    
    public HighScore() {
        JButton backButton = new JButton("Back");
        JPanel buttons = new JPanel(new BorderLayout());
        
        ImageIcon img = new ImageIcon(new ImageIcon("thinking.jpg").getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        JLabel label = new JLabel("", img, JLabel.CENTER);
        
        setLayout(new BorderLayout());
        
        
        buttons.add(backButton, BorderLayout.PAGE_END);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.LINE_START);
        
        add(label, BorderLayout.CENTER);
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.swapView("menu");
            }
            
        });
        
    }
    
    public void setMain(MainFrame panel) {
        this.main = panel;
    }
}
