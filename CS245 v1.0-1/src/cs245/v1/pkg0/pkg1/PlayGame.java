/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
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
    private GamePanel game = new GamePanel();
    private GameEngine engine = new GameEngine();
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
        add(game, BorderLayout.CENTER);
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
       if(!engine.containsLetter(e.getActionCommand())) {
           JButton button = (JButton) e.getSource();
           game.addCount();
           game.repaint();
           button.setEnabled(false);
       } else {
           //increment the wrong tries count
           if (game.getTries() == 6) {
               //Game Over
           }
       }
    }
    
   

   private static class GamePanel extends JPanel {
       private int wrongTries;
       
       public GamePanel() {
           
       }
       
       public int getTries() {
           return wrongTries;
       }
       
       public void addCount() {
           wrongTries++;
       }
       
       
       public void paintComponent(Graphics g) {
           super.paintComponent(g);
           Graphics2D g2 = (Graphics2D) g;
           
           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           
           g2.setStroke(new BasicStroke(10));
           Line2D base = new Line2D.Double(500, 250, 80, 250); // base
           Line2D vLine = new Line2D.Double(400, 250, 400, 20); // vertical line for pole
           Line2D hLine = new Line2D.Double(400, 20, 300, 20); // horizontal line for beam
           Line2D hangRope = new Line2D.Double(300, 20, 300, 80); // rope
           
           
           g2.draw(base);
           g2.draw(vLine); 
           g2.draw(hLine);
           g2.setStroke(new BasicStroke(1));
           g2.draw(hangRope);
           
           g2.setStroke(new BasicStroke(4));
           
           if (wrongTries >= 1) {
               g2.drawOval(279, 75, 20, 20);
           }
           
           if (wrongTries >= 2) {
               g2.drawLine(299, 90, 299, 150);
           }
           
           g2.setStroke(new BasicStroke(3));
           
           if (wrongTries >= 3) {
               g2.drawLine(299, 100, 325, 125);
           }
           
           if (wrongTries >= 4) {
               g2.drawLine(299, 100, 273, 110);
           }
           
           if (wrongTries >= 5) {
               g2.drawLine(299, 150, 326, 170);
           }
           
           if (wrongTries >= 6) {
               g2.drawLine(299, 150, 283, 180);
           }
           
           

           
           repaint();
       }
    
} 
   

   
    
}
