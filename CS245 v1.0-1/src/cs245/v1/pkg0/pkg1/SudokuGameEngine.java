/** *************************************************************
 * file: SudokuGameEngine.java
 * author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
 * class: CS 245 – Programming Graphical User Interface
 *
 * assignment: Swing Project v1.2
 * date last modified: 10/30/17
 *
 * purpose: This program is a "Point-and-click" Hangman and Color game. Using Swing,
 * we created a game that is controlled by your mouse and keyboard. The user
 * will be able to play the classic Hangman game with 6 guesses, play a matching
 * color game with 5 rounds, and a game of Sudoku see the top 5 high scores, and
 * the credits. You will also be able to switch back and forth between the
 * displays using the buttons integrated.
 *
 *************************************************************** */
package cs245.v1.pkg0.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dieha
 */
public class SudokuGameEngine {

    private int score = 540;
    private boolean won;
    private int[][] answer = {
        {8, 3, 5, 4, 1, 6, 9, 2, 7},
        {2, 9, 6, 8, 5, 7, 4, 3, 1},
        {4, 1, 7, 2, 9, 3, 6, 5, 8},
        {5, 6, 9, 1, 3, 4, 7, 8, 2},
        {1, 2, 3, 6, 7, 8, 5, 4, 9},
        {7, 4, 8, 5, 2, 9, 1, 6, 3},
        {6, 5, 2, 7, 8, 1, 3, 9, 4},
        {9, 8, 1, 3, 4, 5, 2, 7, 6},
        {3, 7, 4, 9, 6, 2, 8, 1, 5}};

    //initial starting board, -1 means the
    //box should be empty.
    private int[][] board = {
        { 8, 0, 0, 4, 0, 6, 0, 0, 7},
        { 0, 0, 0, 0, 0, 0, 4, 0, 0},
        { 0, 1, 0, 0, 0, 0, 6, 5, 0},
        { 5, 0, 9, 0, 3, 0, 7, 8, 0},
        { 0, 0, 0, 0, 7, 0, 0, 0, 0},
        { 0, 4, 8, 0, 2, 0, 1, 0, 3},
        { 0, 5, 2, 0, 0, 0, 0, 9, 0},
        { 0, 0, 1, 0, 0, 0, 0, 0, 0},
        { 3, 0, 0, 9, 0, 2, 0, 0, 5}};

    // Keeps track of boxes submitted with wrong answers
     private int[][] wrong = {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public SudokuGameEngine() {

    }
/*
 * method: updateBoard
 * purpose: used to update the backend board.
 */
    public void updateBoard(int[][] newBoard) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    /*
     * method: getBoard
     * purpose: used to return the board so the GUI can use it
     * to populate the visual.
    */
    public int[][] getBoard() {
        return board;
    }
/*
 * method getAns
 * purpose: getter for answer
 */
    public int[][] getAns() {
        return answer;
    }
    
    /*
     * method getWrong
     * purpose: getter for wrong
     */
    public int[][] getWrong() {
        return wrong;

    }
    /*
     * method setWrong
     * purpose: setter for wrong
     */
    public void setWrong(int [][] wrongBoxes){
        wrong = wrongBoxes ;
    }


       /*
    method: getFinalScore
    puspose: returns score
     */
    public int getFinalScore() {
        return score;
    }

    /*
    method: setScore
    purpose: setter for the score variable
     */
    public void setScore(int x) {
        score = x;
    }

    /*
    method: isWinner
    puspose: returns a number depeding on if player wins or loses
    1 = loss, 2 = win but not highscore, 3 = win and highscore
     */
    public int isWinner() {
        int winType = 0;
        int lowestScore = 0;

        try {
            File file = new File("HighScores.txt");
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String line = buff.readLine();
            // Gets lowest highscore
            while ((line) != null) {
                String[] splitted = line.split(" ");
                lowestScore = Integer.parseInt(splitted[1]);
                line = buff.readLine();
            }
            buff.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ColorGameEngine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ColorGameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (score <= 40) { // If score is 40(all guesses used) or less then a loss
            winType = 1;
        } else if (score < lowestScore) {// If score is less than lowest highscore
            winType = 2;
        } else if (score >= lowestScore) { // If score is higher than lowest highscore
            winType = 3;
        }
        return winType;
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



}
