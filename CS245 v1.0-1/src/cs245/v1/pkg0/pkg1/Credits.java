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
 *Need to display our names and make them unselectable.
 * 
 */
public class Credits extends JPanel {
    private MainFrame main;
    private JButton backButton = new JButton("Back");
    private JLabel credits = new JLabel();
    private JPanel buttons = new JPanel(new BorderLayout());
    
    public Credits() {

        setLayout(new BorderLayout());
        addBackButton();
        credits.setText("Brandon Nguyen\nCharly Dang\nColin Koo\nFelix Zhang\nGerianna Geminiano");
        
        
        
        
        
    }
    
    /*
    adds the back button and its functionality.
    */
    private void addBackButton() {
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttons.add(backButton, BorderLayout.PAGE_END);
        add(buttons, BorderLayout.LINE_START);
        add(credits, BorderLayout.CENTER);
        
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
