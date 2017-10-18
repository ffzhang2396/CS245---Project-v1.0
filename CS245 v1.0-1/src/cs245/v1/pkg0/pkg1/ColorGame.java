/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import cs245.v1.pkg0.pkg1.ColorGameEngine.Circle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class ColorGame extends JPanel implements MouseListener {

    
    private ColorGameEngine engine;
    private HManGameEngine hEngine;
    private MainFrame main;
    private CirclePanel game = new CirclePanel();
    private Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue, new Color(255, 0, 255)};
    private Random rand = new Random();
    private JPanel titleBar = new JPanel(new BorderLayout());
    private JPanel skipPanel = new JPanel();
    private JButton skip = new JButton("Skip");
    private JLabel points = new JLabel();
    
    public ColorGame(ColorGameEngine engine,HManGameEngine hEngine) {
        this.engine = engine;
        this.hEngine = hEngine;
        setLayout(new BorderLayout());
        loadUI();
        

    }
    
    private void loadUI(){
        add(titleBar, BorderLayout.PAGE_START);
        add(skipPanel, BorderLayout.LINE_END);
        add(game, BorderLayout.CENTER);
        drawTitle();
        skipButton();
       
    }
/*
    Draws the title bar for the hangman game where it includes the stylized drawing
    of the hangman word as well as the current date and time.
     */
    private void drawTitle() {
        JLabel time = new JLabel();

        // adding the time
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(UIManager.getFont("Label.font").deriveFont(Font.BOLD, 12));
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
        titleBar.add(time, BorderLayout.LINE_END);

       

        //adding the points
       /* points.setText("Points: ");
        points.setFont(new Font("Arial", Font.ITALIC, 14));
        points.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));
        titleBar.add(points, BorderLayout.CENTER);
        */
        
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
    skip button to end game functionality needs
    to be implemented here.
     */
    private void skipButton() {
        skipPanel.add(skip);

        skip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hangman" + hEngine.getScore());
                engine.setScore(hEngine.getScore());
                engine.setWin(true);
                main.gameOverMessage();
                main.swapView("over");
            }
        });
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
            /*
            for (int i = 0; i < drawCirc.length; i++) {
                g2.draw(drawCirc[i]);
            }
*/

        }

    }

}
