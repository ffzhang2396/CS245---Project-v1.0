/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author FelixZhang
 */
public class ColorGameEngine {

    Circle[] circles = new Circle[5];
    private int score = 0;
    private boolean won;
    private String input;
    private String[] colors = {"red", "yellow", "green", "pink", "green"};
    private String target;
    private int rounds = 0;
    

    public ColorGameEngine() {
    	selectTarget();
    }
    
    
    public String getTarget(){
    	return target;
    }
    
    public void selectTarget(){
    	int rnd = new Random().nextInt(colors.length);
         target = colors[rnd];
         System.out.println(target);
    }
    public int getScore(){
    	return score;
    }
    public void setScore(int x){
    	score = x;
    }
    
    public void matches(String input){
    	if(rounds <= 3){
    	if(target.equals(input)){
    		score += 100;
    		System.out.println("Score Goes up, score: " + score);
    		setScore(score);
    		rounds++;
    		selectTarget();
    	} else {
    		System.out.println("WRONG, score: " + score);
    		selectTarget();
    		rounds++;
    	}
    	} else if(rounds == 4) {
    		if(target.equals(input)){
        		score += 100;
        		System.out.println("Score Goes up, score: " + score);
        		rounds++;
        	} else {
        		System.out.println("WRONG, score: " + score);
        		rounds++;
        	}
    	} else {
    		System.out.println("Game is over");
    	}
    }
    public int getRounds(){
    	return rounds;
    }
 

    public void drawCircles() {
        for (int i = 0; i < circles.length; i++) {
            System.out.println(circles[i].getXCenter() + " " + circles[i].getYCenter());
        }

    }
    
    public Shape drawCircleAt(int x, int y){
    	Shape circle = new Ellipse2D.Double(x,y, 50.0f, 50.0f);
    	return circle;
    }
    
    
    public Shape[] getCircles(int width, int height) {
        Shape[] shapes = new Shape[5];
        int x, y, radius;
        
        calcCirclePos(width, height);
        
        for (int i = 0; i < circles.length; i++) {
            x = circles[i].getXPos();
            y = circles[i].getYPos();
            radius = circles[i].getRadius();
            
            shapes[i] = new Ellipse2D.Double(x, y, radius, radius);
            
        }
        return shapes;
    }

    /*
    method: setWin
    puspose: sets the boolean won, if user won
     */
    public void setWin(boolean won) {
        this.won = won;
    }

    /*
    /*
    method: isWonnered
    purpose: returns a boolean if player won or not, won = true, lost = false
     */
    public boolean isWonnered() {
        return won;
    }


    /*
    method: setScore
    purpose: setter for the score variable, sets the final score for the
    end of the game.
     */


    /*
    method: updateHighScore
    purpose: Gets players score and adds it to the highscore list if its in the top 5
    First it reads the highscores file, adds it to an array with the new score, and 
    then overrites old file.
     */
    public void updateHighScore(String name, int score) {

        String scoreArr[] = new String[5];
        try {
            File f = new File("HighScores.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));

            boolean replaced = false;
            int i = 0;
            // if File is empty
            String line = br.readLine();
            if (line.length() == 0) {
                scoreArr[i] = name + " " + Integer.toString(score);
                ++i;
            } else {
                while ((line) != null) {
                    String[] splitted = line.split(" ");
                    if ((Integer.parseInt(splitted[1]) <= score) && replaced == false) {
                        if (i < 5) {
                            scoreArr[i] = name + " " + Integer.toString(score);
                            ++i;
                        }
                        if (i < 5) {
                            scoreArr[i] = line;
                            ++i;
                        }
                        replaced = true;
                    } else {
                        if (i < 5) {
                            scoreArr[i] = line;
                            ++i;
                        }
                    }
                    line = br.readLine();
                }
            }
            if (i < 5) {
                while (i < 5) {
//                    System.out.println(i);
                    scoreArr[i] = "AAA 0";
                    ++i;
                }
            }
            i = 0;

            br.close();
            f.delete();
            BufferedWriter bw = new BufferedWriter(new FileWriter("HighScores.txt"));

            for (int n = 0; n < scoreArr.length; n++) {

                bw.write(scoreArr[n]);
                bw.newLine();
                bw.flush();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    This is used to calculate the positions of the circles with help
    from the intersects method. Takes in the width and the height of the 
    JPanel and draws circles within the bounds of the JPanel.
    @returns a circle array to the circle panel class.
     */
    public void calcCirclePos(int xHeight, int yHeight) {
        
        //System.out.println("running");
        
        circles = new Circle[5];
        int windowH = yHeight;
        int windowW = xHeight;
        int counter = 0;
        Random rand = new Random();
        Circle tempCirc;
        int tempX, tempY;
        boolean done = false;
        
        /*
        while the circle array is not filled, keep picking random numbers for
        coordinates of the circle. Then check if the random circle overlaps with 
        any circle thats already in the list. if so, repick new random coordinates.
        */
        while (!done) {
            tempX = rand.nextInt(windowW);
            tempY = rand.nextInt(windowH);
            tempCirc = new Circle(tempX, tempY);
            
            if(!containsInter(tempCirc)) {
                circles[counter] = tempCirc;
                counter++;
            }
            
            if (counter == 5) {
                done = true;
            }
            
        }
        
    }     
        /*
        //Randomly generate values for tempX and tempY within the bounds of the panel
        //Then check if the values for the randomly generated circle intersect with any
        //circle in circle array. if so, keep getting new values until a non intersecting
        //triangle is formed.
        while (!done) {
            tempX = rand.nextInt(windowW); //width - 20 because we dont want to generate circles that go off screen.
            tempY = rand.nextInt(windowH); //same reason here.
            tempCirc = new Circle(tempX, tempY);
            //now we have to check if the circles at these coordinates intersect with any circle we already have.
            for (int i = 0; i < circles.length; i++) {
                //check to make sure were not comparing a null element thatll throw us nullpointer
                if (circles[i] != null) {
                    //check to see if tempCirc does intersect with circles[i]
                    //if does intersect, then pick new values for center and reset loop back to beginning.
                    if (tempCirc.intersects(circles[i])) {
                        System.out.println("--------------------------------");
                        System.out.println("tempCirc: " + tempCirc);
                        System.out.println("circles[i]: " + circles[i]);
                        tempCirc = new Circle(rand.nextInt(windowW), rand.nextInt(windowH));
                        System.out.println("---------------------------------i = " + i);
                        i = 0;                     
                    }
                }
                /* problem is that sometimes there are overlapping circles and i think its in
                this method but im not sure where the actualy problem is. i think its in the double
                if statements inside the for loops.
                
            }
            // if the list is full, terminate while loop
            if (counter == 5) {
                done = true;
            } else {
                circles[counter] = tempCirc;
                counter++;
            }
        }
        System.out.println("====================================");
        drawCircles();
        System.out.println("====================================");
    } */
    
    /*
    method to check if target circle has an intersection
    within the circle array.
    */
    private boolean containsInter(Circle target) {
        
        for (int i = 0; i < circles.length; i++) {
            if(circles[i] != null) {
                if (target.intersects(circles[i])) {
                    return true;
                }
            }
        }
        return false;
    }

     

    /*
    object to hold the circle information.
     */
    public static class Circle {

        private int xPos, yPos;
        private int xCenter, yCenter;
        private final int radius = 70;

        public Circle(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
            xCenter = xPos + 35;
            yCenter = yPos + 35;
        }

        /*
    checks if two circles are intersecting given the two
    circle objects. NEEDS TO BE CHECKED IF ACTUALLY WORKS.
         */
        public boolean intersects(Circle comp) {
            double radiSum = (radius * 2) + 10;
            double x0x1 = Math.pow((comp.getXCenter() - this.getXCenter()), 2);
            double y0y1 = Math.pow((comp.getYCenter() - this.getYCenter()), 2);
            double sum = x0x1 + y0y1;
            double distance = Math.sqrt(sum);
            
            if (radiSum >= distance) {
                return true;
            }
            return false;
        }

        public int getXCenter() {
            return xCenter;
        }

        public int getYCenter() {
            return yCenter;

        }

        public int getXPos() {
            return xPos;
        }

        public int getYPos() {
            return yPos;
        }

        public int getRadius() {
            return radius;
        }

        public void setXPos(int xPos) {
            this.xPos = xPos;
            xCenter = xPos + 25;
        }

        public void setYPos(int yPos) {
            this.yPos = yPos;
            yCenter = yPos + 25;
        }
        
        public String toString() {
            String retString = this.xCenter + " " + this.yCenter;
            return retString;
        }

    }
}
