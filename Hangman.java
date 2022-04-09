import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.net.URL;
import java.util.ArrayList;
import java.lang.*;
public class Hangman extends HangmanMethods
{
   /** --------------------------------------------------------------------CHECKLIST OF HOW TO START-------------------------------------------------------------------

     DONE 1. Chooses a random word amongst a list of words in a file (then change to an online file)
      2. Print the word in an unknown state using _ _ _ _ _ for each letter before asking for input
      3. Ask for a user input of only one letter no matter if upper or lower case
      4. Check if the letter is in the randomly selected word
            If yes reprint the word but have _ for each unknown letter and fill in the new known letters **(For words with multiple of the same letter fill in all of that instance)**
            If no say "wrong" and add a count to the amount needed to lose (head, body, left arm, right arm, left leg, right leg)
      5. Eliminate the guessed letter from the 26 possible letters so that it cannot be guessed again.
      6. Repeat step 2 and 3 until the word is fully guessed and increment onto the unkown word for each new known letter
      7. If all letters are guessed correct reprint the final word and tell the user "You win"
      8. If all 6 body parts are built or the count gets to 6 the game ends and prints "You Lose"
      
      
     -------------------------------------------------------------------------------THINGS TO ADD--------------------------------------------------------------------------------------------------
      
        > 1. IF THE WORD IS COMPLETLY SOLVED -> STOP THE LOOP AND PRINT THE AMOUNT OF GUESSES USED AND THEN FINAL WORD (LOOK AT NUMBER 6)
          2. ADD A LETTER BANK OF THE LETTERS ALREADY GUESSED AND THE LETTERS STILL AVAILABLE TO GUESS (MAYBE USE AN ARRAY WITH ALL LETTERS AND REMOVE AFTER SELECTING)
               ONLY ALLOW THE USER TO PICK FROM THAT ARRAY WHICH INCLUDES THE LETTERS LEFT TO GUESS
          3. DONT ALLOW TO ENTER A VALUE ALREADY GUESSED
        > 4. CONVERT THE USER INPUT TO LOWER CASE
        > 5. STOP AND SAY YOU LOSE AFTER GETTING 6 INCORRECT LETTERS (HANGMAN CHARCTER PARTS)
        > 6. WRITE IF STATEMENT THAT SAYS IF theWord INCLUDES NO '_' VALUES THEN THE WORD IS SOLVED
          7. CHANGE UPPERBOUND TO CHANGE SIZE DEPENDING ON THE AMOUNT OF WORDS
      
   */
   public static void main(String[] args)throws Exception
   {
      // generate a random number
      Random rand = new Random();
      final int UPPERBOUND = 2315; // number of lines (words) in the file
      int int_rand = rand.nextInt(UPPERBOUND);
      
      
      
      /** --------------------------------------------------------FOR GETTING WORDS FROM A WEBSITE (NOT COMPLETE)-------------------------------------------------------------------
      // read the list of words from online website
      System.out.println("LOADING... Please Wait.\n");
      URL url = new URL("https://raw.githubusercontent.com/3b1b/videos/master/_2022/wordle/data/possible_words.txt");
      Scanner website = new Scanner(url.openStream());
      String word;
      
      
      // reads every line in the website (still needs to add to an array so you can select one with a random num) or (randomly select a random line which is equal to a random word and use that)
      // do the loop x amount of times and this will list all the words up to that point then determine the last word listed and this should be equal to the random number generated 
      try 
      {
         while((word = website.nextLine()) != null)
         {
            //System.out.println(word);
            wordList.add(word);
         }website.close();
      }catch(Exception ex)
      {
         return;
      }
      */
      
      // -------------------------------------------------------------------------------MAIN-----------------------------------------------------------------------------------------------
      
      // READS WORDS FROM A TXT FILE
      BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tgero\\OneDrive\\Documents\\Computer Science\\Computer Science 2\\Hangman Project\\gameHangman\\five-letter-words.txt"));
      ArrayList<String> wordList = new ArrayList<>();
      String line = br.readLine();
      while (line != null)
      {
         wordList.add(line);
         line = br.readLine();
      }br.close();
      
      // uses the random int generator to select a word from the list
      setCurrentWord(wordList.get(int_rand));
      System.out.println("Your Word is: " + getCurrentWord());
      System.out.println("Letter Count: " + getCurrentWord().length());
      setWordBlanks(getCurrentWord());
      
      
      
      Scanner keyboard = new Scanner(System.in);
      
      for (int i = 0; i<30; i++)
      {
         System.out.println("\nGuess " + (i+1));
         setLetterGuess(keyboard.next().charAt(0));
         if(isALetter(getLetterGuess()) == true)
         {
            setLetterGuess(Character.toLowerCase(getLetterGuess()));
            letterPositionOf(getCurrentWord());
            checkIfLetterIsNotInWord();
            winDisplayMessage();
            System.out.println("Letters already guessed:\n");
            System.out.println("Letters left to guess:\n");
         }
         else
         {
            System.out.println("---ERROR: ENTER A LETTER---");
            System.out.println("Letters already guessed:\n");
            System.out.println("Letters left to guess:\n");
         }
       
      }
      
   }
   
   
}