/** *************************************************************
 * file: HighScore.java
 * author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
 * class: CS 245 � Programming Graphical User Interface
 *
 * assignment: Swing Project v1.1
 * date last modified: 10/19/17
 *
 * purpose: This program is a "Point-and-click" Hangman and Color game. Using Swing,
 * we created a game that is controlled by your mouse and keyboard. The user
 * will be able to play the classic Hangman game with 6 guesses, play a matching
 * color game with 5 rounds, see the top 5 high scores, and the credits. You will
 * also be able to switch back and forth between the displays using the buttons
 * integrated.
 *************************************************************** */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 * HighScore class
 */
public class HighScore extends JPanel {

    private MainFrame main;
    private JButton backButton = new JButton("Back");
    private JLabel[] names = new JLabel[5];
    private JPanel scores = new JPanel();
    private JPanel buttons = new JPanel(new BorderLayout());
    private JFrame frame;

    /*
    Constructor
     */
    public HighScore() {
        setLayout(new BorderLayout());
        addTitle();
        readHighScore();
        showHighScores();
        addBackButtons();
      
    }

    
    /*
    method: setMain
    purpose: This method is used to set this instance of MainFrame to the current
    main frame so that the swapview method can be accessed to change the
    panel back to the main menu once the back button is pressed.
     */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }

    /*
    method: addTitle
    purpose: adds title to highscore screen
     */
    private void addTitle() {
        JLabel title = new JLabel("High Scores");
        title.setFont(new Font("Papyrus", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setToolTipText("Welcome to the HighScore Panel!");
        add(title, BorderLayout.PAGE_START);
    }

    /*
    method: showHighScores
    purpose: Displaying the High Scores.
     */
    private void showHighScores() {
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));
        scores.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        for (int i = 0; i < 5; i++) {
            if (names[i] != null) {
                scores.add(names[i]);
            }
        }
        scores.setToolTipText("These are the High Scores recoreded");
        
        
        add(scores, BorderLayout.CENTER);
    }

    /*
    method: updateHS
    purpose: Updates the High Scores
     */
    public void updateHS() {
        scores.removeAll();
        readHighScore();
        showHighScores();
    }

    /*
     method: readHighScore
     purpose: Reading the High scores from the text file and adding
    them to the JLabel array. Since There are only ever going
    to be the top 5 high scores, we can just run the loop 5 times.
     */
    private void readHighScore() {
        File file = new File("HighScores.txt");
        String[] entry;
        try {
            Scanner reader = new Scanner(file);
            for (int i = 0; i < 5; i++) {

                if (reader.hasNext()) {
                    entry = reader.nextLine().split(" ");
                    names[i] = new JLabel(entry[0] + "...................." + entry[1]);
                    names[i].setFont(new Font("Papyrus", Font.BOLD, 25));
                    names[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    method: addBackButtons
    purpose: adding the back button.
     */
    private void addBackButtons() {
        
        String ACTION_KEY = "The Action";
      
        Action actionListener = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {
        String source = actionEvent.getActionCommand();
        if(source==null){
            JOptionPane.showMessageDialog(frame, "Winter Quarter\nCharly Dang 010924537"
                             + "\nBrandon Nguyen 011499566\nColin Koo 010291241\nFelix Zhang 01042383"
                             + "\nGerianna Geminiano 010662522");
        } else {
            System.exit(0);
        }
        
        
        //System.out.println("Activated: " + source.getText());
        }
        };
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        InputMap inputMap = backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(escape, ACTION_KEY);
        ActionMap actionMap = backButton.getActionMap();
        actionMap.put(ACTION_KEY, actionListener);
        backButton.setActionMap(actionMap);
        
        backButton.setToolTipText("Press this button to go back to the main menu!");
        
        KeyStroke space = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0 , true);
        inputMap = buttons.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(space, ACTION_KEY);
        buttons.setActionMap(actionMap);
        
        buttons.add(backButton, BorderLayout.LINE_START);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.PAGE_END);
       
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHS();
                main.startMenuTimer();
                main.swapView("menu");
            }
        });
        
    }
   
}
