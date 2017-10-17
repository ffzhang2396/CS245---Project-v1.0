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
public class ColorGame extends JPanel {
    CirclePanel game = new CirclePanel();
    ColorGameEngine engine = new ColorGameEngine();

    public ColorGame() {
        setLayout(new BorderLayout());
        

    }
    
    
    
    
    /*
    inner panel that contains the circles. Game engine
    is supposed to calculate the circle positions and this 
    nested class just draws them.
    */
    private static class CirclePanel extends JPanel {
        private boolean done = false;
        
        
        
        
        
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          
          g2.setStroke(new BasicStroke(1));
          
          g2.drawOval(0, 0, 50, 50);
          
          
          while (!done) {
              
          }
            
        }
        
        
        
        
        
        
        
    }
    

}
