package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class MainFrame extends JFrame {

    private CardLayout layout = new CardLayout();
    private JPanel mainP = new JPanel(layout);
    private MainMenu mainMenu = new MainMenu();
    private HighScore hScore = new HighScore();
    private Credits credit = new Credits();
    private TitlePanel title = new TitlePanel();
    private PlayGame play = new PlayGame();
    private GameOver over = new GameOver();
    private Timer timer;

    /*
    Constructor for main frame.
     */
    public MainFrame() {
        initUI();
    }
    
    public void reload() {
        play.startNewGame();
    }

    /*
    accessor method for sub classes to access mainMenu class
    to stop the timer for the main menu animation once the 
    user leaves the main menu.
     */
    public void stopMenuTimer() {
        mainMenu.stopTimer();
    }

    /*
    This is used by the sub panels inside the layout to be able to change to
    other panels. Not sure if this is the correct way to do this but it works.
     */
    public void swapView(String key) {
        layout.show(mainP, key);
    }

    /*
    accessor method for sub classes to access mainMenu class 
    method to start the timer for the main menu animation once 
    the user returns to the main menu.
     */
    public void startMenuTimer() {
        mainMenu.startTimer();
    }

    /*
    initializer method to draw the frame and add elements to it.
     */
    private void initUI() {
        ImageIcon icon = new ImageIcon("thinking.jpg");

        startLayout();
        introTimer();

        setTitle("Thinkman's Hangman");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(icon.getImage());

        add(mainP);
        pack();
    }

    /*
    I moved the panel initializations into another method
    to clean up the initUI() method.
     */
    private void startLayout() {
        mainP.add(title, "title");
        mainP.add(mainMenu, "menu");
        mainP.add(hScore, "High Score");
        mainP.add(credit, "credits");
        mainP.add(play, "play");
        mainP.add(over, "over");

        mainMenu.setMain(this);
        credit.setMain(this);
        hScore.setMain(this);
        play.setMain(this);
        over.setMain(this);
    }

    /*
    Timer to set the 3 second delay between the title panel
    and the main menu.
     */
    private void introTimer() {
        int delay = 3000;

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainP, "menu");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Main method that uses EventQueue.invokeLater in order to have the GUI
     * execute in the Event Dispatch Thread so backend calculations do not cause
     * the GUI to freeze or lag.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame menu = new MainFrame();
            menu.setVisible(true);
        });
    }
}
