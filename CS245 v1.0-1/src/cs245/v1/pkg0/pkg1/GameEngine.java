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
        System.out.println("setWin " + this.won);
    }

   public boolean isWonnered() {
         try {
            File file = new File("HighScores.txt");
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String readLine = "", lastLine = "";
            //Reads last line of file (the lowest score)
            while ((readLine = buff.readLine()) != null) {
                lastLine = readLine;
            }
            //Splits the string by name and score
            String[] splitted = lastLine.split(" ");
            //If old score is less than new player score 
            if ((Integer.parseInt(splitted[1]) < (finalScore))) {
                won = true;
            } else {
                won = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    gets the final score for the
    end of the game.
    */
    public int getScore() {
        return finalScore;
    }
    
    /*
    Gets players score and possibly adds it to the highscore list
     */
    public void updateHighScore(String name, int score) {

        try {
            File f = new File("HighScores.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            BufferedWriter bw = new BufferedWriter(new FileWriter("TempFile.txt"));
            String readLine = "";
            boolean replaced = false;
            int lines = 0;
            while ((readLine = br.readLine()) != null) {
                //System.out.println(readLine);
                String[] splitted = readLine.split(" ");
                if ((Integer.parseInt(splitted[1]) < score) && replaced == false) {
                    if (lines < 5) {
                        bw.write(name + " " + Integer.toString(score));
                        bw.flush();
                        System.out.print(name + " " + Integer.toString(score));
                        ++lines;
                    }
                    if (lines < 5) {
                        bw.write(readLine);
                        bw.flush();
                        System.out.print(readLine);
                        ++lines;
                    }
                    replaced = true;
                } else {
                    if (lines < 5) {
                        bw.write(readLine);
                        bw.flush();
                        System.out.print(readLine);
                        ++lines;
                    }
                }
            }
            bw.close();
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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
