/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;


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
    
    private JPanel btnPanel = new JPanel(new GridLayout(2, 13, 5, 5));
    private JPanel titleBar = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JButton skip = new JButton("Skip");
    private JButton[] buttons = new JButton[26];
    private String[] letters = {"A", "B", "C", "D", "E", "F",
    "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
    "S", "T", "U", "V", "W", "X", "Y", "Z"};
    

    public PlayGame() {
        setLayout(new BorderLayout());
        // might need to remove the top border to make room for the actual game
        btnPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        loadUI();
        
        
        
  
    }
    

    
    
    
    private void loadUI() {
        addButtons();
        
        add(btnPanel, BorderLayout.PAGE_END);
        add(titleBar, BorderLayout.PAGE_START);
        drawTitle();
      
    }
    
    
    /*
    Draws the title bar for the hangman game where it includes the stylized drawing
    of the hangman word as well as the current date and time.
    */
    private void drawTitle() {
        JLabel time = new JLabel();
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 12));
        //time.setText(DateFormat.getDateTimeInstance().format(new Date()));
        
        
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }            
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
        titleBar.add(time, BorderLayout.LINE_END);
    }

    
    /*
    adds an array of JButtons that make up the alphabet buttons for the 
    user to click on when playing the game.
    */
    private void addButtons() {
        for (int i = 0; i < letters.length; i++) {
            buttons[i] = new JButton(letters[i]);
            buttons[i].addActionListener(this);
           // buttons[i].setFont(new Font("Calibri", Font.PLAIN, 11));
            buttons[i].setMargin(new Insets(0, 0, 0, 0));
            
            btnPanel.add(buttons[i]);
        }
        
        
    }
    
    /*
    Need to implement a solution later that checks the button presed against
    the secret word as well as disable the button once the letter is added to
    the word.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
       System.out.println(e.getActionCommand());
    }
    
}
