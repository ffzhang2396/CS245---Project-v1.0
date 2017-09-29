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
public class Credits extends JPanel {
    
    public Credits() {
        
        JLabel credits = new JLabel();
        
        setLayout(new BorderLayout());
        credits.setText("Brandon Nguyen\nCharly Dang\nColin Koo\nFelix Zhang\nGerianna Geminiano");
        
        
        add(credits, BorderLayout.CENTER);
        
        
        
        
        
        
    }
}
