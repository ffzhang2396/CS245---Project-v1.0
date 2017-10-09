/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;
import java.util.Scanner;



public class GameEngine {
    private int finalScore;
    private static String word;
    private static String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};

    public GameEngine() {

    }
  
    
    /*
    picks a new word for the game.
    */
    public static void pickWord() {
        
        String rand = wordList[(int) (Math.random()*wordList.length)];
        word = rand;
        System.out.println(word);
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
    	char letter = input.charAt(0);
        int[] positions = new int[frequency(letter)];
        int counter = 0;
        for(int i = 0; i < getWordLength(); i++){
        	if(letter == word.charAt(i)){
        		positions[0] = i;
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
    public static int getWordLength() {    
        return word.length();
    }
    
    /*
    Checks if the letter pressed is contained
    inside the secret word string.
    */
    public static boolean containsLetter(String input) {
    	boolean contains = false;
    	char letter = input.charAt(0);
    	for(int i = 0; i < getWordLength(); i++){
    		if(letter == word.charAt(0)){
    			contains = true;
    		}
    	}
        return contains;
    }
    
   
}
