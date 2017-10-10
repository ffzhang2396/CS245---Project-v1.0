/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;
import java.util.Random;
import java.util.Scanner;



public class GameEngine {
    private int finalScore;
    private int wordLength;
    private MainFrame main;
    private boolean won;
    //private 
            String word;
    private String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};

    public GameEngine() {
        pickWord();


    }


    public void setWin(boolean won) {
        this.won = won;
    }

    public boolean isWonnered() {
        return won;
    }



    /*
    picks a new word for the game.
    also updates the length of the word
    */
    public void pickWord() {
        int rng = new Random().nextInt(wordList.length);       
        word = wordList[rng];
    }

    /*
    sets the final score for the
    end of the game.
    */
    public void setScore(int score) {
        finalScore = score;
    }

    /*
    Returns the letter position in the
    secret word string.
    */
    public int[] getLetterPositions(String input) {
    	char letter = Character.toLowerCase(input.charAt(0));
        int[] positions = new int[frequency(letter)];
        int counter = 0;
        for(int i = 0; i < getWordLength(); i++){
        	if(letter == word.charAt(i)){
        		positions[counter] = i;
        		counter++;
        	}
        }
        return positions;
    }
    /**
     * Returns the number of times a char
     * is found in a string. Then returns
     * that as an int.
     */
    public int frequency(char letter){
    	int counter = 0;
    	for( int i = 0; i < getWordLength(); i++ ) {
    	    if( word.charAt(i) == letter ) {
    	        counter++;
    	    }
    	}
    	return counter;
    }

    /*
    returns the length of the secret word.
    */
    public int getWordLength() {
        return word.length();
    }

    /*
    Checks if the letter pressed is contained
    inside the secret word string.
    */
    public boolean containsLetter(String input) {
    	boolean contains = false;
    	char letter = Character.toLowerCase(input.charAt(0)) ;
        
        
    	for(int i = 0; i < getWordLength(); i++){
    		if(word.charAt(i) == letter){
    			contains = true;
    		}
    	}
        return contains;
    }


}
