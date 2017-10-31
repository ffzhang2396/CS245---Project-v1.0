/** *************************************************************
 * file: HManGameEngine.java
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

import java.io.*;
import java.util.Random;

public class HManGameEngine {

    private int finalScore;
    private boolean won;
    private String word;
    private String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};

    //Constructor
    public HManGameEngine() {
        pickWord();
    }

    /*
    method: getWord
    purpose: return word
     */
    public String getWord() {
        return word;
    }

    /*
    method: setWin
    puspose: sets the boolean won, if user won
     */
    public void setWin(boolean won) {
        this.won = won;
    }

    /*
    method: isWonnered
    purpose: returns a boolean if player won or not, won = true, lost = false
     */
    public boolean isWonnered() {
        return won;
    }

    /*
    method: pickWord
    purpose: picks a new word for the game and updates the length of the word
     */
    public void pickWord() {
        int rng = new Random().nextInt(wordList.length);
        word = wordList[rng];
    }

    /*
    method: setScore
    purpose: setter for the score variable, sets the final score for the
    end of the game.
     */
    public void setScore(int score) {
        finalScore = score;
    }

    /*/*
    method: getScore
    purpose: getter for the score variable, gets the final score for the
    end of the game.
     */
    public int getScore() {
        return finalScore;
    }

    /*
    method: getLetterPositions
    purpose: Returns the letter position in the secret word string.
     */
    public int[] getLetterPositions(String input) {
        char letter = Character.toLowerCase(input.charAt(0));
        int[] positions = new int[frequency(letter)];
        int counter = 0;
        for (int i = 0; i < getWordLength(); i++) {
            if (letter == word.charAt(i)) {
                positions[counter] = i;
                counter++;
            }
        }
        return positions;
    }

    /*
    method: frequency
    purpose: Returns the number of times a char is found in a string. Then returns
      that as an int.
     */
    public int frequency(char letter) {
        int counter = 0;
        for (int i = 0; i < getWordLength(); i++) {
            if (word.charAt(i) == letter) {
                counter++;
            }
        }
        return counter;
    }

    /*
    method: getWordLength
    puspose: Returns the length of the secret word.
     */
    public int getWordLength() {
        return word.length();
    }

    /*
    method:containsLetter
    purpose: Checks if the letter pressed is contained
    inside the secret word string.
     */
    public boolean containsLetter(String input) {
        boolean contains = false;
        char letter = Character.toLowerCase(input.charAt(0));

        for (int i = 0; i < getWordLength(); i++) {
            if (word.charAt(i) == letter) {
                contains = true;
            }
        }
        return contains;
    }

}