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
    from the intersects method.
     */
    private void calcCirclePos(int xHeight, int yHeight) {
        int windowH = yHeight;
        int windowW = xHeight;
        Random rand = new Random();
        int tempX, tempY;
        boolean done = false;

    }

    /*
    object to hold the circle information.
     */
    private static class Circle {

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
