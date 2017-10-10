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
    private MainFrame main;
    private GameEngine engine;
    private PlayGame game;
    
    /*
    Constructor
    */
    public GameOver(GameEngine engine) {
        this.engine = engine;
        
        setLayout(new BorderLayout());
        addDoneButton();
        inputUserScore();
        
        
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
            title.setText("Congratulations! You win!");
        } else {
            title.setText("Aww you didnt win. Try again!");
        }
        
        title.setFont(new Font("Kristen ITC", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        titlePanel.add(title);
        add(titlePanel, BorderLayout.PAGE_START);
        
        
        
    }
    
    
    private void inputUserScore() {
        JPanel inputPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        userPrompt.setText("Enter your name (No Spaces): ");
        
        
        containerPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new FlowLayout());
        
        
        inputPanel.add(userPrompt);
        inputPanel.add(input);
        
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 0 ,0, 0));
        inputPanel.setAlignmentX(CENTER_ALIGNMENT);
        inputPanel.setAlignmentY(GameOver.CENTER_ALIGNMENT);
        
        add(inputPanel, BorderLayout.CENTER);
        
    }
     
    
    
    /*
    adds the done button that lets the user return
    to the main menu.
    */
    private void addDoneButton() {
        JPanel buttonPanel = new JPanel();        
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
