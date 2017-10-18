/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import cs245.v1.pkg0.pkg1.ColorGameEngine.Circle;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class ColorGame extends JPanel implements MouseListener {

    private ColorGameEngine engine;
    private MainFrame main;
    private CirclePanel game = new CirclePanel();
    private Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue, new Color(255, 0, 255)};
    private Random rand = new Random();

    public ColorGame(ColorGameEngine engine) {
        setLayout(new BorderLayout());
        this.engine = engine;
        add(game, BorderLayout.CENTER);

    }

    public void setMain(MainFrame main) {
        this.main = main;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    inner panel that contains the circles. Game engine
    is supposed to calculate the circle positions and this 
    nested class just draws them.
     */
    private class CirclePanel extends JPanel {

        private Shape[] drawCirc = new Shape[5];

        /*
       private void makeShapes() {
           int x, y, radius;

           for (int i = 0; i < circles.length; i++) {
               x = circles[i].getXPos();
               y = circles[i].getYPos();
               radius = circles[i].getRadius();
               
               drawCirc[i] = new Ellipse2D.Double(x, y, radius, radius);
           }
       }
         */
        public void paintComponent(Graphics g) {
            drawCirc[0] = new Ellipse2D.Double(50, 50, 20, 20);

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(new BasicStroke(1));

            g2.drawOval(0, 0, 50, 50);

            //Draws the ovals based on the game engine calculated coordinates.
            //Need to fill in with a color later.
            /* this doesnt work, need to figure out why
            for (int i = 0; i < drawCirc.length; i++) {
                g2.draw(drawCirc[i]);
            }
*/

        }

    }

}
