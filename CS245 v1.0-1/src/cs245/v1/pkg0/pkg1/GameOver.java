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
        
        
    }
    
    /*
    accesor method to allow panel switching from
    within this panel.
    */
    public void setMain(MainFrame panel) {
        this.main = panel;
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
