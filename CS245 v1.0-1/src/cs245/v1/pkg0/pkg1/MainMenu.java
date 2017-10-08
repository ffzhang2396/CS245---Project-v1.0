package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 *Main Controller class for all of the FrontEnd.
 * includes a card layout to easily switch between different
 * JPanels.
 */
public class MainMenu extends JPanel {
    
    private JButton play = new JButton("Play");
    private JButton hScore = new JButton("High Score");
    private JButton credit = new JButton("Credits");
    private JPanel menuButtons = new JPanel();
    private JPanel buttons = new JPanel();
    private CardLayout slideShow = new CardLayout();
    private JPanel pictures = new JPanel(slideShow);
    private boolean show = false;
    private Timer timer;
    private MainFrame main;
    
    /*
    Constructor for the mainMenu class
    */
    public MainMenu() {
        setBackground(Color.black);
        pictures.setBackground(Color.black);
        setLayout(new BorderLayout());
        
        add(menuBtns(), BorderLayout.LINE_END);
        add(pictures, BorderLayout.LINE_START);
        
        displayLogo();  
    }
    
    /*
    accessor method for subclasses to be able to set the
    current instance of the MainMenu to be able to access
    certain methods from a subclass.
    */
    public void setMain(MainFrame panel) {
        this.main = panel;
    
    }
    
    /*
    accessor method for subclasses to be able to stop the timer
    once the user leaves the main menu so the animation does not keep playing.
    */
    public void stopTimer() {
        timer.stop();
    }
    
    /*
    accesssor method for sublcasses to be able to stop the timer once the user leaves
    the main menu so the animation does not keep going.
    */
    public void startTimer() {
        timer.start();
    }
    
    
    /*
    I used a java swing timer to slideshow the two 
    logo pictures.
    */
    //NEED TO CHANGE TO USE BUFFERED IMAGE FOR AUTOMATIC IMAGE SCALING.
    private void displayLogo() {
        ImageIcon img = new ImageIcon(new ImageIcon("thunking.png").getImage().getScaledInstance(375, 375, Image.SCALE_SMOOTH));
        JLabel label = new JLabel("", img, JLabel.CENTER);
        ImageIcon img2 = new ImageIcon(new ImageIcon("thunking2.png").getImage().getScaledInstance(375, 375, Image.SCALE_SMOOTH));
        JLabel label2 = new JLabel("", img2, JLabel.CENTER);
        
        pictures.add(label, "one");
        pictures.add(label2, "two");
        
        timer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show) {
                    slideShow.show(pictures, "one");
                    show = false;
                } else {
                    slideShow.show(pictures, "two");
                    show = true;
                }
            }
            
        });
        timer.start();
    }
    
    /*
    Method that sets the main menu buttons and adds functions
    to them.
    */
    private JComponent menuBtns() {
        
        //setting layout of inner panel
        menuButtons.setLayout(new BorderLayout());

        //setting up layout of panel that goes inside menuButtons
        buttons.setPreferredSize(new Dimension(200, 150));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttons.add(play);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(hScore);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(credit);

        //resizing the buttons so it looks like the example
        //also setting all of the buttons to be the same size
        hScore.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        play.setMaximumSize(hScore.getMaximumSize());
        credit.setMaximumSize(hScore.getMaximumSize());

        //right aligningt the buttons
        play.setAlignmentX(Component.RIGHT_ALIGNMENT);
        hScore.setAlignmentX(Component.RIGHT_ALIGNMENT);
        credit.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //finally add the inner panel to menu panel and return.
        menuButtons.add(buttons, BorderLayout.PAGE_END);
        
        
        // Action Listeners for the 3 main buttons.
        hScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("High Score");
            }
        });
        
        credit.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("credits");
            }
        });
        
        
        play.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("play");
            }
        });
        
        menuButtons.setBackground(Color.black);
        buttons.setBackground(Color.black);
        menuButtons.setOpaque(false);
        buttons.setOpaque(false);
        
        return menuButtons;
    } 
}
