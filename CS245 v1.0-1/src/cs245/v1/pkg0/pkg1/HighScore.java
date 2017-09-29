/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class HighScore extends JPanel {
    
    public HighScore() {
        ImageIcon img = new ImageIcon(new ImageIcon("thinking.jpg").getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));
        JLabel label = new JLabel("", img, JLabel.CENTER);
        
        
        add(label);
    }
}
