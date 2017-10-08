
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;

/**
 *This will the title panel that holds the
 * group name as well as the project name.
 * will display for 3 seconds and then switch
 * to the main menu panel.
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
    Creates the title and sets the font as well as the
    positioning of the Label within the window.
    */
    private void drawTitle() {
        
        projName.setText("ThinkMan's Hangman");
        projName.setFont(new Font("Papyrus", Font.ITALIC, 50 ));
        projName.setHorizontalAlignment(SwingConstants.CENTER);
        projName.setForeground(Color.white);
        
        add(projName, BorderLayout.PAGE_START);
    }
    
    /*
    Creates the group Name and sets the font as well as the positioning
    of the label within the window.
    */
    private void teamName() {
        groupName.setText("Team NoF.lame");
        groupName.setFont(new Font("Papyrus", Font.BOLD, 20));
        groupName.setHorizontalAlignment(SwingConstants.CENTER);
        groupName.setForeground(Color.white);
        groupName.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
        
        add(groupName, BorderLayout.PAGE_END);
    }
}
