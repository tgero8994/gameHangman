import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.lang.*;
public class Hangman extends HangmanMethods
{
   /** --------------------------------------------------------------------CHECKLIST OF HOW TO START-------------------------------------------------------------------
      
     >1. Chooses a random word amongst a list of words in a file
     >2. Print the word in an unknown state using _ _ _ _ _ for each letter before asking for input
     >3. Ask for a user input of only one letter no matter if upper or lower case
     >4. Check if the letter is in the randomly selected word
     >      If yes reprint the word but have _ for each unknown letter and fill in the new known letters **(For words with multiple of the same letter fill in all of that instance)**
     >      If no say "wrong" and add a count to the amount needed to lose (head, body, left arm, right arm, left leg, right leg)
     >5. Eliminate the guessed letter from the 26 possible letters so that it cannot be guessed again.
     >6. Repeat step 2 and 3 until the word is fully guessed and increment onto the unkown word for each new known letter
     >7. If all letters are guessed correct reprint the final word and tell the user "You win"
     >8. If all 6 body parts are built or the count gets to 6 the game ends and prints "You Lose"
      
      
     -------------------------------------------------------------------------------THINGS TO ADD--------------------------------------------------------------------------------------------------
          1. ADD API
            - Change the random word generator by getting a word from an API
            - add a sorter to pick a word with only a certain amount of letters
          2. CHANGE UPPERBOUND TO CHANGE SIZE DEPENDING ON THE AMOUNT OF WORDS / MAKE THE API CONNECT
      
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
      System.out.println("Number of Letters: " + getWordLength());
      setWordBlanks(getCurrentWord());
      
      
      
      Scanner keyboard = new Scanner(System.in);
      
      for (int i = 0; i<30; i++){
         System.out.println("\nGuess " + (i+1));
         setLetterGuess(keyboard.next().charAt(0));
         if(isALetter(getLetterGuess())){
            setLetterGuess(Character.toLowerCase(getLetterGuess())); // sets the letter guessed to lowercase so it works with solver()
            solver(getCurrentWord()); // prints out the word with each newly guessed letter
            incorrectGuessIncrements();   // increases each time the user incorrectly guesses and only increases the first time the letter is guessed
            winOrLoseMessage();  //displays if the user won or lose
            lettersAlreadyGuessedList();  // displays the list of letters already guessed in an array
            System.out.println("Letters already guessed:\n" + getGuessedLetters() + "\n---------------------------------------------------------");
         }
         else{
            System.out.println("---ERROR: ENTER A LETTER---");
         }
      }
   }
}