/** *************************************************************
 * file: HighScore.java
 * author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
 * class: CS 245 – Programming Graphical User Interface
 *
 * assignment: Swing Project v1.0
 * date last modified: 10/10/17
 *
 * purpose: This program is a "Point-and-click" Hangman game. Using Swing,
 * we created a game that is controlled by your mouse and keyboard. The user
 * will be able to play the classic Hangman game with 6 guesses, see the top 5
 * high scores, and the credits. You will also be able to switch back and forth
 * between the displays using the buttons integrated.
 *
 *************************************************************** */
 package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 * Need to figure out how to display high scores. needs to have unselectable
 * displays
 *
 */
public class HighScore extends JPanel {

    private MainFrame main;
    private JButton backButton = new JButton("Back");
    private JLabel[] names = new JLabel[5];
    private JPanel scores = new JPanel();
    private JPanel buttons = new JPanel(new BorderLayout());

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
    This method is used to set this instance of MainFrame to the current
    main frame so that the swapview method can be accessed to change the
    panel back to the main menu once the back button is pressed.
     */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }


    private void addTitle() {
        JLabel title = new JLabel("High Scores");
        title.setFont(new Font("Papyrus", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.PAGE_START);
    }

    /*
    Displaying the High Scores.
     */
    private void showHighScores() {
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));
        scores.setBorder(BorderFactory.createEmptyBorder(20, 0, 0 ,0));

        for (int i = 0; i < 5; i++) {

            if (names[i] != null) {
                scores.add(names[i]);
            }
        }
        add(scores, BorderLayout.CENTER);
    }

   /*
    Updates the High Scores
    */
     public void updateHS(){
        scores.removeAll();
        readHighScore();
        showHighScores();
    }

    /*
    Reading the High scores from the text file and adding
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
    adding the back button.
     */
    private void addBackButtons() {
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
