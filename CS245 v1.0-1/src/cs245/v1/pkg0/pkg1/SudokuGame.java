/** *************************************************************
 * file: SudokuGame.java
 * author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
 * class: CS 245 – Programming Graphical User Interface
 *
 * assignment: Swing Project v1.2
 * date last modified: 10/30/17
 *
 * purpose: This program is a "Point-and-click" Hangman and Color game. Using Swing,
 * we created a game that is controlled by your mouse and keyboard. The user
 * will be able to play the classic Hangman game with 6 guesses, play a matching
 * color game with 5 rounds, and a game of Sudoku see the top 5 high scores, and the credits. You will
 * also be able to switch back and forth between the displays using the buttons
 * integrated.
 *
 *************************************************************** */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.*;

/**
 *
 *
 */
public class SudokuGame extends JPanel {

    private ColorGameEngine cEngine;
    private SudokuGameEngine engine;
    private MainFrame main;
    private JTextField[][] boxes = new JTextField[9][9];
    private JPanel board = new JPanel();

    /**
     * constructor
     * @param engine
     * @param cEngine
     */
    public SudokuGame(SudokuGameEngine engine, ColorGameEngine cEngine) {
        this.engine = engine;
        this.cEngine = cEngine;
        setLayout(new BorderLayout());

        initBoard();
        createBoard();
        titleBar();
        submitButton();
        quitButton();
    }

    public void setMain(MainFrame main) {
        this.main = main;

    }

    /*
    method: startNewGame
    purpose: Resets the game.
     */
    public void startNewGame() {
        engine.setScore(540); // reset the score back to 540
        // NEED TO ADD MORE CODE TO RESET EVERYTHINE ELSE
        boxes = new JTextField[9][9];
        board.removeAll();
        initBoard();
        createBoard();
    }

    /*
     *  method: startNewGame
     *  purpose: Resets the game.
    For the sudoku board we are going to use a
    9x9 gridLayout for the inner JPanel thats going to be in the center of the
    outer JPanel which is an instance of this class.
     */
    private void createBoard() {
        board.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes.length; j++) {

                board.add(boxes[i][j]);
                board.setToolTipText("Sudoku Board! Come play!");
                boxes[i][j].setToolTipText("The current coordiantes are: ["+i+"]["+j+"]" );
            }
        }

        board.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(board, BorderLayout.CENTER);
    }

    /*
     * method: titleBar
     * purpose: This method draws the title bar of the game panel.
     * Title bar should include the time as well as a stylized
     * version of the game name.
     */
    private void titleBar() {
        JPanel title = new JPanel(new BorderLayout());
        JLabel sudoku = new JLabel("SUDOKU");
        sudoku.setToolTipText("Time to commit SUDOKU!!");
        JLabel time = new JLabel();

        //adding the time
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 12));
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
        time.setToolTipText("The current Date!");
        title.add(time, BorderLayout.LINE_END);
        title.setToolTipText("Welcome to the Sudoku Game!");
        //adding stylized sudoku name
        sudoku.setFont(new Font("Papyrus", Font.BOLD, 18));
        title.add(sudoku, BorderLayout.LINE_START);
        add(title, BorderLayout.PAGE_START);

    }

    /*
     * method: submitButton
     * purpose: adds in the submit button that goes on the left 
     * side of the game board.
     */
    private void submitButton() {
        JButton submit = new JButton("Submit");
        submit.setToolTipText("Submit all of your entries");
        JPanel buttons = new JPanel(new BorderLayout());

        //formatting
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));
        buttons.add(submit, BorderLayout.PAGE_END);
        buttons.setToolTipText("Please do something.");
        add(buttons, BorderLayout.LINE_START);
        
        //adding actionListener
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

             
            if(checkInput()){
               System.out.println("Sudoku Score: " + engine.getFinalScore());
               engine.setScore(cEngine.getScore()+engine.getFinalScore());
               System.out.println("Final Score: " + engine.getFinalScore());
               main.gameOverMessage();
               main.swapView("over");
               startNewGame();
            }
            }
        });
    }

    /*
     * method: checkInput
     * purpose: checks the boxes of sudoku puzzle 
     * and compares with the answer board. will highlight the boxes 
     * red if the answer does not match, green if it is correct. need to 
     * ol choose a less bright color.
     */
    private boolean checkInput() {
        int[][] answers = engine.getAns();
        int[][] wrongAns = engine.getWrong(); // Array that tells which box had wrong answer
        String ans = "";
        JFrame pop = new JFrame();
        boolean finish = true;

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes.length; j++) {

                try {
                    ans = boxes[i][j].getText().replaceAll("\\s+", "");

                    if (Integer.parseInt(ans) == answers[i][j]) {
                        boxes[i][j].setBackground(Color.green);
                    } else {
                        if (wrongAns[i][j] == 0) {
                            engine.setScore(engine.getFinalScore() - 10);
                            wrongAns[i][j] = 1;
                        }
                        finish = false;
                        boxes[i][j].setBackground(Color.red);
                    }
                } catch (NumberFormatException n) {
                    if (wrongAns[i][j] == 0) {
                        engine.setScore(engine.getFinalScore() - 10);
                        wrongAns[i][j] = 1;
                    }
                    finish = false;
                    boxes[i][j].setBackground(Color.red);
                    continue;
                }

            }
        }

        // Update wrong boxes array
        //Meant so that can't be dedcuted more than once for each box
        engine.setWrong(wrongAns);
        System.out.println("Score: " + engine.getFinalScore());
        return finish;
    }

    /*
     * method: quitButton
     * purpose: This method adds in the submit button that goes on the right
     * side of the game board.
     */
    private void quitButton() {
        JButton quit = new JButton("Quit");
        quit.setToolTipText("Press this button to quit the game.");
        JPanel btns = new JPanel(new BorderLayout());
        btns.setToolTipText("Please do something.");
        //formatting
        btns.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));
        btns.add(quit, BorderLayout.PAGE_END);
        add(btns, BorderLayout.LINE_END);

        //adding actionListener
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If choose to quit then Socuku score is 0
                // So Ending score is previous games Score
                engine.setScore(cEngine.getScore());
                System.out.println("Final Score: " + engine.getFinalScore());
                main.gameOverMessage();
                main.swapView("over");
                
                startNewGame();
            }

        });
    }

    /*
     * method: initBoard
     * purpose: initializes the board to a new state.
     * Probably can use this to restart the game.
     */
    private void initBoard() {
        int[][] board = engine.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boxes[i][j] = new JTextField(1);
                boxes[i][j].setToolTipText("The current coordiantes are: ["+i+"]["+j+"]" );
                if (board[i][j] != 0) {
                    boxes[i][j].setText(Integer.toString(board[i][j]));
                    boxes[i][j].setEditable(false);
                } else {
                    boxes[i][j].setText("");
                    boxes[i][j].setEditable(true);
                    boxes[i][j].setInputVerifier(new Verifier()); //used for check input
                }
            }
        }
        beautifyBorders();
    }

    /*
     * method: beautifyBorders
     * purpose: method to draw the black bars for sudoku
     * to make the board more appealling to the eye.
     */
    private void beautifyBorders() {

        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes.length; j++) {
                if (j == 2 || j == 5) {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
                } else if (i == 2 || i == 5) {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
                } else {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
                }
                boxes[2][5].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
                boxes[2][2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
                boxes[5][2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
                boxes[5][5].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));

                boxes[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }
    
    /*
     * class to verify input
     */
    private class Verifier extends InputVerifier {

        /*
         * method: shouldYieldFocus
         * purpose: doesn't allow focus to change if input is not valid
         * pops a warning dialog box informing user of the invalid input
         */
        @Override
        public boolean shouldYieldFocus(JComponent input) {
            if (verify(input)) {
                return true;
            }
            JOptionPane.showMessageDialog(input, "Only numbers beetween 1 and 9 are allowed", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        /*
         * method; verify
         * purpose: verifies if input is between 1 and 9. also if contains any non number characters
         * a null character is accepted as valid input to allow the user to change previous answers
         */
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            try {

                int value = Integer.parseInt(text);
                if (1 <= value && value <= 9) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                if (text.isEmpty()) {
                 //   System.out.println(text);
                    return true;
                }


                return false;
            }

        }
    }
}
