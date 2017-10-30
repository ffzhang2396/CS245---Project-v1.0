/** *************************************************************
 * file: HManGame.java
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.text.DateFormat;
import java.util.Date;

/**
 * This will the the actual GUI for the game. I implemented action listener so
 * we can just use one action listener instead of creating a separate listener
 * for every JButton for each letter of the alphabet. This class will also be
 * the controller for the backend, so game update methods should be written in
 * here for backend communication.
 *
 */
public class HManGame extends JPanel implements ActionListener {

    private int score = 100;
    private MainFrame main;
    private GamePanel game = new GamePanel();
    private HManGameEngine engine;
    private JPanel btnPanel = new JPanel(new GridLayout(2, 13, 5, 5));
    private JPanel titleBar = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel skipPanel = new JPanel();
    private JButton skip = new JButton("Skip");
    private JLabel points = new JLabel();
    private JLabel[] guessWord;
    private JButton[] buttons = new JButton[26];
    private String[] letters = {"A", "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private JFrame frame;
    

    /*Constructor
    for Game UI panel.
     */
    public HManGame(HManGameEngine engine) {

        this.engine = engine;
        setLayout(new BorderLayout());
        loadUI();
        requestFocusInWindow();
       

    }

    /*
    method: loadUI
    purpose: adds all of the panels in this panel to the screen
    to display them.
     */
    private void loadUI() {

        addButtons();
        add(titleBar, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.PAGE_END);
        add(skipPanel, BorderLayout.LINE_END);
        drawTitle();
        drawGame();
        skipButton();
        
    }

    /*
    method: startNewGame()
    purpose: resets variables and display for new game
     */
    public void startNewGame() {
        game.resetCount(); //reset # of wrong tries
        score = 100; // reset the score back to 100
        engine.pickWord(); // pick a secret word
        centerPanel.removeAll();
        titleBar.removeAll();
        drawTitle();
        drawGame();

        //enable all of the buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(true);
        }
    }

    /*
    method: skipButton
    purpose: skip button to end game functionality needs
    to be implemented here.
     */
    private void skipButton() {
        String ACTION_KEY = "The Action";
        Action actionListener = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {
        String source = actionEvent.getActionCommand();
        System.out.println("The command:" + source);
        if(source.equals(" ")){
            JOptionPane.showMessageDialog(frame, "Winter Quarter\nCharly Dang 010924537"
                             + "\nBrandon Nguyen 011499566\nColin Koo 010291241\nFelix Zhang 01042383"
                             + "\nGerianna Geminiano 010662522");
        } else {
            System.exit(0);
        }
        }
        };
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        InputMap inputMap = skip.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(escape, ACTION_KEY);
        ActionMap actionMap = skip.getActionMap();
        actionMap.put(ACTION_KEY, actionListener);
        skip.setActionMap(actionMap);
        
        
        skip.setToolTipText("Press this button to skip the Hangman Game and go directly to the Color Game");
        
        KeyStroke space = KeyStroke.getKeyStroke(' ');//supposed to be KeyEvent.VK_F1, 0 , true);
        inputMap = skipPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(space, ACTION_KEY);
        skipPanel.setActionMap(actionMap);
        
        skipPanel.add(skip);

        skip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.setScore(0);
                main.gameOverMessage();
                main.swapView("play2");
            }
        });
    }
    /*
    method: drawGame
    purpose: Draws the visual representation of the game, which includes
    the hangman as well as the places for the word.
     */
    private void drawGame() {
        JPanel word = new JPanel();
        int length = engine.getWordLength();
        guessWord = new JLabel[length];

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(game, BorderLayout.CENTER);
        word.setLayout(new FlowLayout());

        for (int i = 0; i < length; i++) {
            guessWord[i] = new JLabel(" ", SwingConstants.CENTER);
            guessWord[i].setPreferredSize(new Dimension(30, 30));
            guessWord[i].setFont(new Font("Arial", Font.PLAIN, 24));
            word.add(guessWord[i]);
            guessWord[i].setOpaque(true);
            guessWord[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
            guessWord[i].setToolTipText("Guess this word!!");
        }
        centerPanel.add(word, BorderLayout.PAGE_END);
    }

    /*
    method: drawTitle
    purpose: Draws the title bar for the hangman game where it includes the stylized drawing
    of the hangman word as well as the current date and time.
     */
    private void drawTitle() {
        JLabel hangman = new JLabel("HANGMAN");
        JLabel time = new JLabel();
        hangman.setToolTipText("Welcome to the Hangman Game!");
        // adding the time
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
        time.setToolTipText("The current time!");
        titleBar.add(time, BorderLayout.LINE_END);

        //adding the stylized HANGMAN
        hangman.setFont(new Font("Papyrus", Font.BOLD, 18));
        titleBar.add(hangman, BorderLayout.LINE_START);

        //adding the points
        points.setText("Points: " + score);
        points.setFont(new Font("Arial", Font.ITALIC, 14));
        points.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));
        points.setToolTipText("Your current total of points: " + score);
        titleBar.add(points, BorderLayout.CENTER);
    }

    /*
    method: addButtons
    purpose: adds an array of JButtons that make up the alphabet buttons for the
    user to click on when playing the game.
     */
    private void addButtons() {
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));
        for (int i = 0; i < letters.length; i++) {
            buttons[i] = new JButton(letters[i]);
            buttons[i].addActionListener(this);
            // buttons[i].setFont(new Font("Calibri", Font.PLAIN, 11));
            buttons[i].setMargin(new Insets(0, 0, 0, 0));
            buttons[i].setToolTipText("Press " + letters[i]);
            btnPanel.add(buttons[i]);
        }

    }

    /*
    method:actionPerformed
    purpose: checks the button presed against
    the secret word as well as disable the button once the letter is added to
    the word.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //if the user chooses the correct letter
        // disable the letter and add the letter
        // to the screen.
        JButton button = (JButton) e.getSource();
        if (engine.containsLetter(e.getActionCommand())) {
            int[] positions = engine.getLetterPositions(button.getText());
            for (int i = 0; i < positions.length; i++) {
                guessWord[positions[i]].setText(button.getText());
            }

            button.setEnabled(false);
            game.repaint();

            if (wordFound()) {
                engine.setScore(score);
                gameFinished(true);
            }
        } else {
            // if the user chooses the incorrect letter
            //increment the wrong tries count and subtract 10 from score.
            //update the count.
            score -= 10;
            points.setText("Points: " + score);
            game.addCount();
            button.setEnabled(false);

            if (game.getTries() == 6) { //Game Over                
                gameFinished(false);
                engine.setScore(score);
            }
        }
      
    }

    /*
    method: wordFound
    purpose: checks if the word has been filled out, if it has then end the game.
     */
    private boolean wordFound() {
        boolean found = true;
        for (int i = 0; i < guessWord.length; i++) {
            if (guessWord[i].getText() == " ") {
                found = false;
            }
        }
        return found;
    }

    /*
    method: gameFinished
    purpose: This method is going to be used to transition into the
    game over panel.
     */
    private void gameFinished(boolean won) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }

        // Timer delay for the game over screen.
        // Probably need to change this to go to the next game
        // After user has finished the game.
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.setWin(won);
                engine.setScore(score);
                main.gameOverMessage();
                main.swapView("play2");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /*
    method: setMain
    purpose: setting the current mainFrame to the reference in this class
    to allow for panel switching.
     */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }

    /*
    Inner class to utilize the paint() method in order to draw the
    hangman as well as to draw limbs onto the screen once the user
    has reached a certain number of incorrect tries.disc
     */
    private static class GamePanel extends JPanel {

        private int wrongTries;

        public GamePanel() {

        }

        /*
        method: getTries()
        purpose: returns the number of wrong tries the user 
        has attempted.
         */
        public int getTries() {
            return wrongTries;
        }

        /*
        method: addCount
        purpose: increments the number of wrong tries.
         */
        public void addCount() {
            wrongTries++;
        }

        /*
        method: resetCount
        purpose: resets the number of wrong tries.
         */
        public void resetCount() {
            wrongTries = 0;
        }

        /*
        method: paintComponent
        purpose: Draws the hangman graphic and updates it based
        on the number of wrong tries the user has.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            super.setToolTipText("The gallows demand a body! Number of wrong guesses: " + getTries() + "!");
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
