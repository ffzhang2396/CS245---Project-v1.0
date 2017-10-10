/***************************************************************
* file: GameEngine.java
* author: Brandon Nguyen, Charly Dang, Colin Koo, Felix Zhang, Gerianna Geminiano
* class: CS 245 – Programming Graphical User Interface
*
* assignment: Swing Project v1.0
* date last modified: 10/10/17 
* 
* purpose: This program is a "Point-and-click" Hangman game. Using Swing, 
* we created a game that is controlled by your mouse and keyboard. The user
* will be able to play the classic Hangman game with 6 guesses, see the top 5
* high scores, and the credits. You will also be able to switch back and forth 
* between the displays using the buttons integrated. 
*
****************************************************************/ 
package cs245.v1.pkg0.pkg1;
import java.io.*;
import java.util.Random;

public class GameEngine {
    private int finalScore;
    private int wordLength;
    private MainFrame main;
    private boolean won;
    private String word;
    private String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};

    //Constructor
    public GameEngine() {
        pickWord();
    }
    
    /*
    method: getWord
    purpose: return word
    */
    
    public String getWord(){
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
    method: getLetterPositions
    purpose: Returns the letter position in the secret word string.
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
    /*
    method: frequency
    purpose: Returns the number of times a char is found in a string. Then returns
      that as an int.
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
    	char letter = Character.toLowerCase(input.charAt(0)) ;
        
        
    	for(int i = 0; i < getWordLength(); i++){
    		if(word.charAt(i) == letter){
    			contains = true;
    		}
    	}
        return contains;
    }


}
