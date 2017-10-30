/** *************************************************************
 * file: Credits.java
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
 *
 *************************************************************** */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
public class Credits extends JPanel {

    private MainFrame main;
    private JButton backButton = new JButton("Back");
    private JLabel[] names = new JLabel[6];
    private JPanel scores = new JPanel();
    private JPanel buttons = new JPanel(new BorderLayout());
    private static Action enterAction;
    private static JFrame frame;

    /*
    Constructor
     */
    public Credits() {

        setLayout(new BorderLayout());
        readCredits();
        showCredits();
        addBackButtons();
        requestFocusInWindow();
       

    }

    /* 
    method: setMain
    puspose: This method is used to set this instance of MainFrame to the current
    main frame so that the swapview method can be accessed to change the
    panel back to the main menu once the back button is pressed.
     */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }


    /*
    method: showCredits
    puspose: Displaying the Credits.
     */
    private void showCredits() {
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));

        for (int i = 0; i < 6; i++) {
            if (names[i] != null) {
                scores.add(names[i]);
            }
        }
        scores.setToolTipText("These are the names/brc ids of the project group memebers");
        add(scores, BorderLayout.CENTER);
    }

    /*
    method: readCredits
    puspose:Reading the Credits from the array and adding
    them to the JLabel array.
     */
    private void readCredits() {

        String[] entries = {"Credits", "Brandon Nguyen 011499566", "Charly Dang 010924537", "Colin Koo 010291242",
            "Felix Zhang 010423283", "Gerianna Geminiano 010662522"};
        String[] input;

        names[0] = new JLabel(entries[0]);
        names[0].setFont(new Font("Papyrus", Font.BOLD, 30));
        names[0].setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 1; i < 6; i++) {
            input = entries[i].split(" ");
            names[i] = new JLabel(input[0] + " " + input[1] + "........." + input[2]);
            names[i].setFont(new Font("Papyrus", Font.BOLD, 24));
            names[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        
    }

    /*
     method: addBackButtons
    puspose: adding the back button.
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
        }
        };
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        InputMap inputMap = backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(escape, ACTION_KEY);
        ActionMap actionMap = backButton.getActionMap();
        actionMap.put(ACTION_KEY, actionListener);
        backButton.setActionMap(actionMap);
        
        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0 , true);//supposed to be KeyEvent.VK_F1, 0 , true);
        inputMap = buttons.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(f1, ACTION_KEY);
        buttons.setActionMap(actionMap);
        
        backButton.setToolTipText("Press the bacl button to go back to the main menu");
        buttons.add(backButton, BorderLayout.LINE_START);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.PAGE_END);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startMenuTimer();
                main.swapView("menu");
            }
        });
    }
     // class EnterAction is an AbstractAction that defines what will occur
    // when the enter key is pressed. 
    
}
