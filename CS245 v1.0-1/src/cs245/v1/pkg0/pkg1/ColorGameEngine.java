/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.util.Random;

/**
 *
 * @author FelixZhang
 */
public class ColorGameEngine {

    Circle[] circles = new Circle[5];

    public void drawCircles() {

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
