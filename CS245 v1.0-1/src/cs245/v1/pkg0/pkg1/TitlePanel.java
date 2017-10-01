
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
    
    JLabel groupName = new JLabel();
    JLabel projName = new JLabel();
    
    public TitlePanel() {
        setLayout(new BorderLayout());
        
        groupName.setText("title");
        
        add(groupName, BorderLayout.CENTER);
    }
    
}
