import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.net.URL;
import java.util.ArrayList;
import java.lang.*;
public class Hangman
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
   */
   private static ArrayList<Character> theWord = new ArrayList<>();
   private static char letterGuess;
   private static char letterCycle;
   private static String currentWord;
   private static int incorrectGuesses = 0;
   private static final int TRIESGIVEN = 6;  // number of tries allowed before user loses
   private static final int ALLOWEDGUESSES = 30;   // number of guesses allowed before the program just ends
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
      
      
      
      /** -------------------------------------------------------------------------------THINGS TO ADD--------------------------------------------------------------------------------------------------
      
        // 1. IF THE WORD IS COMPLETLY SOLVED -> STOP THE LOOP AND PRINT THE AMOUNT OF GUESSES USED AND THEN FINAL WORD (LOOK AT NUMBER 6)
           2. ADD A LETTER BANK OF THE LETTERS ALREADY GUESSED AND THE LETTERS STILL AVAILABLE TO GUESS (MAYBE USE AN ARRAY WITH ALL LETTERS AND REMOVE AFTER SELECTING)
               ONLY ALLOW THE USER TO PICK FROM THAT ARRAY WHICH INCLUDES THE LETTERS LEFT TO GUESS
           3. DONT ALLOW TO ENTER A VALUE ALREADY GUESSED
        // 4. CONVERT THE USER INPUT TO LOWER CASE
        // 5. STOP AND SAY YOU LOSE AFTER GETTING 6 INCORRECT LETTERS (HANGMAN CHARCTER PARTS)
        // 6. WRITE IF STATEMENT THAT SAYS IF theWord INCLUDES NO '_' VALUES THEN THE WORD IS SOLVED
           7. CHANGE UPPERBOUND TO CHANGE SIZE DEPENDING ON THE AMOUNT OF WORDS
      
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
      currentWord = wordList.get(int_rand);
      System.out.println("Your Word is: " + currentWord);
      System.out.println("Letter Count: " + currentWord.length());
      setWordBlanks(currentWord);
      
      
      
      Scanner keyboard = new Scanner(System.in);
      
      for (int i = 0; i<ALLOWEDGUESSES; i++)
      {
         System.out.println("\nGuess " + (i+1));
         letterGuess = keyboard.next().charAt(0);
         if(isALetter(letterGuess) == true)
         {
            letterGuess = Character.toLowerCase(letterGuess);
            letterPositionOf(currentWord);
            incorrectGuesses();
            win();
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
   
   // -------------------------------------------------------------------------------METHODS--------------------------------------------------------------------------------------------------
   /**
      Determines if the char variable is a letter.
      @param c is the char being tested.
      @return false if the char is not a letter and true if only letters.
   */
   public static boolean isALetter(char c)
   {
      // char guess = c.toUpperCase();
      if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z'))
         return false;
      else
         return true;
   }
   /**
      Makes a character array list with '_' for the length of the s
      @param s is a string value.
   */
   public static void setWordBlanks(String s)
   {      
      for(int i = 0; i < s.length(); i++)
      {
         theWord.add('_');
      }System.out.println(theWord);
   }
   
   /**
      Loops through each letter position of a word (that was passed as a char array) and determines if the enetered value is equal to that letter of the word,
      If equal it then sets the setWordBlanks or '_' with that letter.
      @param s is a string that changes depending on if the letters guessed are correct.
   */
   public static void letterPositionOf(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i) == letterGuess && letterGuess == 'a' && s.charAt(i) == 'a')
            theWord.set(i, 'a');
         else if (s.charAt(i) == letterGuess && letterGuess == 'b' && s.charAt(i) == 'b')
            theWord.set(i, 'b');
         else if (s.charAt(i) == letterGuess && letterGuess == 'c' && s.charAt(i) == 'c')
            theWord.set(i, 'c');
         else if (s.charAt(i) == letterGuess && letterGuess == 'd' && s.charAt(i) == 'd')
            theWord.set(i, 'd');
         else if (s.charAt(i) == letterGuess && letterGuess == 'e' && s.charAt(i) == 'e')
            theWord.set(i, 'e');
         else if (s.charAt(i) == letterGuess && letterGuess == 'f' && s.charAt(i) == 'f')
            theWord.set(i, 'f');
         else if (s.charAt(i) == letterGuess && letterGuess == 'g' && s.charAt(i) == 'g')
            theWord.set(i, 'g');
         else if (s.charAt(i) == letterGuess && letterGuess == 'h' && s.charAt(i) == 'h')
            theWord.set(i, 'h');
         else if (s.charAt(i) == letterGuess && letterGuess == 'i' && s.charAt(i) == 'i')
            theWord.set(i, 'i');
         else if (s.charAt(i) == letterGuess && letterGuess == 'j' && s.charAt(i) == 'j')
            theWord.set(i, 'j');
         else if (s.charAt(i) == letterGuess && letterGuess == 'k' && s.charAt(i) == 'k')
            theWord.set(i, 'k');
         else if (s.charAt(i) == letterGuess && letterGuess == 'l' && s.charAt(i) == 'l')
            theWord.set(i, 'l');
         else if (s.charAt(i) == letterGuess && letterGuess == 'm' && s.charAt(i) == 'm')
            theWord.set(i, 'm');
         else if (s.charAt(i) == letterGuess && letterGuess == 'n' && s.charAt(i) == 'n')
            theWord.set(i, 'n');
         else if (s.charAt(i) == letterGuess && letterGuess == 'o' && s.charAt(i) == 'o')
            theWord.set(i, 'o');
         else if (s.charAt(i) == letterGuess && letterGuess == 'p' && s.charAt(i) == 'p')
            theWord.set(i, 'p');
         else if (s.charAt(i) == letterGuess && letterGuess == 'q' && s.charAt(i) == 'q')
            theWord.set(i, 'q');
         else if (s.charAt(i) == letterGuess && letterGuess == 'r' && s.charAt(i) == 'r')
            theWord.set(i, 'r');
         else if (s.charAt(i) == letterGuess && letterGuess == 's' && s.charAt(i) == 's')
            theWord.set(i, 's');
         else if (s.charAt(i) == letterGuess && letterGuess == 't' && s.charAt(i) == 't')
            theWord.set(i, 't');
         else if (s.charAt(i) == letterGuess && letterGuess == 'u' && s.charAt(i) == 'u')
            theWord.set(i, 'u');
         else if (s.charAt(i) == letterGuess && letterGuess == 'v' && s.charAt(i) == 'v')
            theWord.set(i, 'v');
         else if (s.charAt(i) == letterGuess && letterGuess == 'w' && s.charAt(i) == 'w')
            theWord.set(i, 'w');
         else if (s.charAt(i) == letterGuess && letterGuess == 'x' && s.charAt(i) == 'x')
            theWord.set(i, 'x');
         else if (s.charAt(i) == letterGuess && letterGuess == 'y' && s.charAt(i) == 'y')
            theWord.set(i, 'y');
         else if (s.charAt(i) == letterGuess && letterGuess == 'z' && s.charAt(i) == 'z')
            theWord.set(i, 'z');
      }
       System.out.println(theWord);
   }
   /**
      If the currentWord doesn't include a letter that was guessed the incorrectGuesses counter increases.
      Prints out the amount of incorrect guesses used out of 6.
      The program then ends when the user has entered 6 incorrect guesses.
   */
   public static void incorrectGuesses()
   {
      
      CharSequence seq = new StringBuilder(1).append(letterGuess);
      if (currentWord.contains(seq) == false)
         incorrectGuesses++;
      if (incorrectGuesses == TRIESGIVEN)
      {
         System.out.println("Incorrect Guesses: " + incorrectGuesses + "/" + TRIESGIVEN);
         System.out.println("---------------------------------------------\nSorry you guessed incorrectly too mant times.\nThe correct word was: -------- " + currentWord.toUpperCase() + " --------");
         System.exit(0);
      }
      System.out.println("Incorrect Guesses: " + incorrectGuesses + "/" + TRIESGIVEN);
   }
   /**
      Prints out a you win statement when all parts in the theWord arraylist don't equal '_' meaning all letters have been guessed.
   */
   public static void win()
   {
         if (currentWord.length() == 5 && theWord.get(0) != '_' && theWord.get(1) != '_' && theWord.get(2) != '_' && theWord.get(3) != '_' && theWord.get(4) != '_') // NEEDS TO BE CHANGED TO READ WORDS WITH ANY AMOUNT OF LETTERS
         {
            System.out.println("YOU WIN");
            System.exit(0);
         }
   }
}