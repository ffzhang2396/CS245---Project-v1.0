/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

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
    private int score;
    private boolean won;
    public void drawCircles() {

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
    
    public int getScore(){
        return score;
    }
    
    
    /*
    method: setScore
    purpose: setter for the score variable, sets the final score for the
    end of the game.
    */
    public void setScore(int score) {
         this.score = score;
    }
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
            if (line.length() == 0 ) {  
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
    public Circle[] calcCirclePos(int xHeight, int yHeight) {
        int windowH = yHeight;
        int windowW = xHeight;
        Random rand = new Random();
        int tempX, tempY;
        boolean done = false;

        //Randomly generate values for tempX and tempY within the bounds of the panel
        //Then check if the values for the randomly generated circle intersect with any
        //circle in circle array. if so, keep getting new values until a non intersecting
        //triangle is formed.
        while (!done) {
            tempX = rand.nextInt(windowW - 20); //width - 20 because we dont want to generate circles that go off screen.
            tempY = rand.nextInt(windowH - 20); //same reason here.

            //now we have to check if the circles at these coordinates intersect with any circle we already have.
        }

        circles[0] = new Circle( 50, 50);
        return circles;
    }


    /*
    object to hold the circle information.
     */
    public static class Circle {

        private int xPos, yPos;
        private int xCenter, yCenter;
        private final int radius = 20;

        public Circle(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
            xCenter = xPos + 10;
            yCenter = yPos + 10;
        }

        /*
    checks if two circles are intersecting given the two
    circle objects. NEEDS TO BE CHECKED IF ACTUALLY WORKS.
         */
        public boolean intersects(Circle comp) {
            int r0 = 0;
            int r1 = (int) Math.pow(this.getRadius(), 2);
            int x0x1 = (int) Math.pow(this.xCenter - comp.getXCenter(), 2);
            int y0y1 = (int) Math.pow(this.yCenter - comp.getYCenter(), 2);
            int sum = x0x1 + y0y1;

            if ((r0 <= sum) && (sum <= r1)) {
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
        }

        public void setYPos(int yPos) {
            this.yPos = yPos;
        }

    }
}
