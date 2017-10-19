/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class ColorGame extends JPanel {

    private ColorGameEngine engine;
    private HManGameEngine hEngine;
    private MainFrame main;
    private CirclePanel game;
    private Random rand = new Random();
    private JPanel titleBar = new JPanel(new BorderLayout());
    private JPanel skipPanel = new JPanel();
    private JButton skip = new JButton("Skip");
    private JLabel points = new JLabel();
    private JTextField color = new JTextField(10);
    private JLabel target = new JLabel();
    

    public ColorGame(ColorGameEngine engine, HManGameEngine hEngine) {
        this.engine = engine;
        this.hEngine = hEngine;
        game = new CirclePanel();
        setLayout(new BorderLayout());
        loadUI();

    }

    private void loadUI() {
    	
    	target.setText("Color: " + engine.getTarget());
        target.setFont(new Font("Papyrus", Font.BOLD, 18));
        target.setForeground(Color.red);
        titleBar.add(target, BorderLayout.LINE_START);
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
       

    }
    
    public Color chooseRandomColor(){
    	Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue, new Color(255, 0, 255)};
    	int rnd = new Random().nextInt(colors.length);
        Color choice = colors[rnd];
    	return choice;
    }

    public void setMain(MainFrame main) {
        this.main = main;
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
                game.repaint();
                /*
                System.out.println("Hangman" + hEngine.getScore());
                engine.setScore(hEngine.getScore());
                engine.setWin(true);
                main.gameOverMessage();
                main.swapView("over");
                 */
            }
        });
    }

    /*
    inner panel that contains the circles. Game engine
    is supposed to calculate the circle positions and this 
    nested class just draws them.
     */
    private class CirclePanel extends JPanel implements MouseListener, MouseMotionListener {

        private Shape[] drawCirc = new Shape[5];
        private Color[] colors = {Color.red, Color.yellow, Color.green, Color.blue, new Color(255, 0, 255)};
        private Point mouse = null;
        private String color;
        
        public CirclePanel() {

            drawCirc = engine.getCircles(431, 284);
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        
        
        
        public void reDraw() {
        	 //titleBar.remove(target);
        	 //titleBar.validate();
        	 //titleBar.repaint();
        	 target.setText("Color: " + engine.getTarget());
             target.setFont(new Font("Papyrus", Font.BOLD, 18));
            // target.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));
             target.setForeground(chooseRandomColor());
             titleBar.add(target, BorderLayout.LINE_START);
        	 
             points.setText("Points: " + Integer.toString(engine.getScore()));
             points.setFont(new Font("Arial", Font.ITALIC, 14));
             points.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 10));
             titleBar.add(points, BorderLayout.CENTER);
             
             drawCirc = engine.getCircles(431, 284);
        }

        public void paintComponent(Graphics g) {
            Rectangle r = this.getBounds();
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(1));

            //Draws the ovals based on the game engine calculated coordinates.
            //Need to fill in with a color later.
            //drawCirc = engine.getCircles(width, height);
            for (int i = 0; i < drawCirc.length; ++i) {
               if (mouse != null && drawCirc[i].contains(mouse)){//mouseX, mouseY)){
                    //System.out.println("inside");
                    g2.setColor(colors[i].darker());
                    thisColor(i);
                } else {
                    g2.setColor(colors[i]);
                }
                g2.fill(drawCirc[i]);
            }
            


        }
        
        public void thisColor(int i){
        	switch(i){
        	case 0:
        		//System.out.println("red");
        		color = "red";
        		break;
        	case 1:
        		//System.out.println("yellow");
        		color = "yellow";
        		break;
        	case 2:
        		//System.out.println("green");
        		color = "green";
        		break;
        	case 3:
        		//System.out.println("blue");
        		color = "blue";
        		break;
        	case 4:
        		//System.out.println("pink");
        		color = "pink";
        		break;
        	
        	} 
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        	if(engine.getRounds() <= 3){
            for (Shape shape : drawCirc) {
                if (shape.contains(e.getPoint())) {
                    //System.out.println("click click");
                    System.out.println(color);
                    engine.matches(color);
                    reDraw();
                }
            }
           } else if(engine.getRounds() == 4){
        	   for (Shape shape : drawCirc) {
                	if (shape.contains(e.getPoint())) {
                    //System.out.println("click click");
                    System.out.println(color);
                    engine.matches(color);
                    System.out.println("Game is over");
                    engine.isWonnered();
                    //reDraw();
                } }
           }else {
        		System.out.println("Game is over");
        	}
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent e) {
            mouse = e.getPoint();
            repaint();
            /*for (Shape shape : drawCirc) {
                if (shape.contains(e.getPoint())) {
                    mouse = e.getPoint();
                } else {
                    mouse = e.getPoint();
                }
                repaint();
            }*/
        }

    }

}
