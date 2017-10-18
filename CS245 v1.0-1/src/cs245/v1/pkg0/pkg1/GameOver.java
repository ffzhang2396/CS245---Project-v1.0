/** *************************************************************
 * file: GameOver.java
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *This class is meant for the ending screen once user has finished the game
 * or has clicked the skip button.
 */
public class GameOver extends JPanel {
    private JButton done = new JButton();
    private JTextField input = new JTextField(10);
    private JLabel userPrompt = new JLabel();
    private JPanel titlePanel = new JPanel();
    private JPanel containerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private MainFrame main;
    private ColorGameEngine engine;
    private HManGame game;

    /*
    Constructor
    */
    public GameOver(ColorGameEngine engine) {
        this.engine = engine;

        setLayout(new BorderLayout());
        //addDoneButton();
        //inputUserScore();


    }

    /*
    accesor method to allow panel switching from
    within this panel.
    */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }

    public void addTitle() {

        JLabel title = new JLabel();

        titlePanel.removeAll();

        if (engine.isWonnered()) {
            // need to check if the user is in the high score or not
            title.setText("Congratulations! You win!");
            buttonPanel.removeAll();
            inputUserScore();
        } else {
            title.setText("Aww you didnt win. Try again!");
            containerPanel.removeAll();
            addDoneButton();
        }

        title.setFont(new Font("Kristen ITC", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        titlePanel.add(title);
        add(titlePanel, BorderLayout.PAGE_START);



    }


    private void inputUserScore() {
        containerPanel.removeAll();
        userPrompt = new JLabel();
        input = new JTextField(10);
        String scoreText;
        scoreText = "Woo! You made the High Score!"+ Integer.toString(engine.getScore());
        JPanel inputPanel = new JPanel();

        JButton confirm = new JButton("OK");
        JLabel notice = new JLabel(scoreText, SwingConstants.CENTER);
        userPrompt.setText("Enter your name! (No Spaces): ");
        notice.setFont(new Font("Kristen ITC", Font.BOLD, 20));


        containerPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new FlowLayout());


        inputPanel.add(userPrompt);
        inputPanel.add(input);
        inputPanel.add(confirm);

        notice.setAlignmentX(CENTER_ALIGNMENT);
        notice.setAlignmentY(GameOver.CENTER_ALIGNMENT);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0 ,0, 0));
        inputPanel.setAlignmentX(CENTER_ALIGNMENT);
        inputPanel.setAlignmentY(GameOver.CENTER_ALIGNMENT);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String inputValue = input.getText();
               if(inputValue.length() == 0){
                   inputValue = "NONAME";
               }
//               System.out.println(inputValue + " score:"+ engine.getScore());
               engine.updateHighScore(inputValue, engine.getScore());
               main.swapView("menu");
               main.updateScore();

            }

        });



        containerPanel.add(notice, BorderLayout.PAGE_START);
        containerPanel.add(inputPanel, BorderLayout.CENTER);
        add(containerPanel, BorderLayout.CENTER);





    }



    /*
    adds the done button that lets the user return
    to the main menu.
    */
    private void addDoneButton() {

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(done);

        done.setText("End");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.swapView("menu");
            }
        });
        add(buttonPanel, BorderLayout.PAGE_END);
    }
}
