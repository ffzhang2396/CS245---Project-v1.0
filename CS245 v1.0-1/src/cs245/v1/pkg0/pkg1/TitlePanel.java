/** *************************************************************
 * file: TitlePanel.java
 * author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
 * class: CS 245 – Programming Graphical User Interface
 *
 * assignment: Swing Project v1.2
 * date last modified: 10/31/17
 *
 * purpose: This program is a "Point-and-click" Hangman and Color game. Using Swing,
 * we created a game that is controlled by your mouse and keyboard. The user
 * will be able to play the classic Hangman game with 6 guesses, play a matching
 * color game with 5 rounds, and a game of Sudoku see the top 5 high scores, and
 * the credits. You will also be able to switch back and forth between the
 * displays using the buttons integrated.
 *
 *************************************************************** */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;

/**
 * This will the title panel that holds the group name as well as the project
 * name. will display for 3 seconds and then switch to the main menu panel.
 *
 */
public class TitlePanel extends JPanel {

    private JLabel groupName = new JLabel();
    private JLabel projName = new JLabel();

    /*
    Constructor
     */
    public TitlePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.black);

        drawTitle();
        teamName();
    }

    /*
    method: drawTitle
    purpose: Creates the title and sets the font as well as the
    positioning of the Label within the window.
     */
    private void drawTitle() {
        projName.setText("ThinkMan's Hangman");
        projName.setFont(new Font("Papyrus", Font.ITALIC, 50));
        projName.setHorizontalAlignment(SwingConstants.CENTER);
        projName.setForeground(Color.white);
        projName.setToolTipText("Thinking....Thikning....Thinking...Eureka!");
        add(projName, BorderLayout.PAGE_START);
    }

    /*
    method: teamName
    purpose: Creates the group Name and sets the font as well as the positioning
    of the label within the window.
     */
    private void teamName() {
        groupName.setText("Team NoF.lame");
        groupName.setFont(new Font("Papyrus", Font.BOLD, 20));
        groupName.setHorizontalAlignment(SwingConstants.CENTER);
        groupName.setForeground(Color.white);
        groupName.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        groupName.setToolTipText("This game was built by Team NoF.lame");
        add(groupName, BorderLayout.PAGE_END);
    }
}
