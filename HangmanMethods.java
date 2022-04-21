import java.util.ArrayList;
import java.lang.*;
public class HangmanMethods
{
   private static final ArrayList<Character> theWord = new ArrayList<>();
   private static final ArrayList<Character> guessedLetters = new ArrayList<>(); // list of the letters the user already guessed
   private static char letterGuess; // the user's inputted guess
   private static String currentWord;  // randomly picked word
   private static int incorrectGuesses = 0;  // keeps the amount of times an incorrect letter was guessed
   private static final int TRIESGIVEN = 6;  // number of tries allowed before user loses
   
   // -------------------------------------------------------------------------------METHODS--------------------------------------------------------------------------------------------------
   public static ArrayList<Character> getGuessedLetters(){
      return guessedLetters;
   }
   public static void setLetterGuess(char a){
      letterGuess = a;
   }
   public static char getLetterGuess(){
      return letterGuess;
   }
   public static void setCurrentWord(String i){
      currentWord = i;
   }
   public static String getCurrentWord(){
      return currentWord;
   }
   public static int getWordLength(){
      return getCurrentWord().length();
   }
   
    /**
      Determines if the user's char variable is a letter.
      @param c is the char being tested.
      @return false if the char is not a letter and true if only letters.
   */
   public static boolean isALetter(char c)
   {
      // char guess = c.toUpperCase();
      return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
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
      }
   }
   
   /**
      Loops through each letter position of a word (that was passed as a char array) and determines if the entered value is equal to that letter of the word,
      If equal it then sets the setWordBlanks or '_' with that letter.
      @param s is a string that changes depending on if the letters guessed are correct.
   */
   public static void solver(String s)
   {
      for (int i = 0; i < s.length(); i++)
      {
         if (s.charAt(i) == letterGuess && letterGuess == 'a')
            theWord.set(i, 'a');
         else if (s.charAt(i) == letterGuess && letterGuess == 'b')
            theWord.set(i, 'b');
         else if (s.charAt(i) == letterGuess && letterGuess == 'c')
            theWord.set(i, 'c');
         else if (s.charAt(i) == letterGuess && letterGuess == 'd')
            theWord.set(i, 'd');
         else if (s.charAt(i) == letterGuess && letterGuess == 'e')
            theWord.set(i, 'e');
         else if (s.charAt(i) == letterGuess && letterGuess == 'f')
            theWord.set(i, 'f');
         else if (s.charAt(i) == letterGuess && letterGuess == 'g')
            theWord.set(i, 'g');
         else if (s.charAt(i) == letterGuess && letterGuess == 'h')
            theWord.set(i, 'h');
         else if (s.charAt(i) == letterGuess && letterGuess == 'i')
            theWord.set(i, 'i');
         else if (s.charAt(i) == letterGuess && letterGuess == 'j')
            theWord.set(i, 'j');
         else if (s.charAt(i) == letterGuess && letterGuess == 'k')
            theWord.set(i, 'k');
         else if (s.charAt(i) == letterGuess && letterGuess == 'l')
            theWord.set(i, 'l');
         else if (s.charAt(i) == letterGuess && letterGuess == 'm')
            theWord.set(i, 'm');
         else if (s.charAt(i) == letterGuess && letterGuess == 'n')
            theWord.set(i, 'n');
         else if (s.charAt(i) == letterGuess && letterGuess == 'o')
            theWord.set(i, 'o');
         else if (s.charAt(i) == letterGuess && letterGuess == 'p')
            theWord.set(i, 'p');
         else if (s.charAt(i) == letterGuess && letterGuess == 'q')
            theWord.set(i, 'q');
         else if (s.charAt(i) == letterGuess && letterGuess == 'r')
            theWord.set(i, 'r');
         else if (s.charAt(i) == letterGuess && letterGuess == 's')
            theWord.set(i, 's');
         else if (s.charAt(i) == letterGuess && letterGuess == 't')
            theWord.set(i, 't');
         else if (s.charAt(i) == letterGuess && letterGuess == 'u')
            theWord.set(i, 'u');
         else if (s.charAt(i) == letterGuess && letterGuess == 'v')
            theWord.set(i, 'v');
         else if (s.charAt(i) == letterGuess && letterGuess == 'w')
            theWord.set(i, 'w');
         else if (s.charAt(i) == letterGuess && letterGuess == 'x')
            theWord.set(i, 'x');
         else if (s.charAt(i) == letterGuess && letterGuess == 'y')
            theWord.set(i, 'y');
         else if (s.charAt(i) == letterGuess && letterGuess == 'z')
            theWord.set(i, 'z');
      }
       System.out.println(theWord);
   }
   /**
      Prints out a win statement when all parts in the theWord arraylist don't equal '_' meaning all letters have been guessed.
      Prints out a loss statement if the user guesses incorrectly six or more times (equal to TRIESGIVEN)
   */
   public static void winOrLoseMessage()
   {
         if (getWordLength() == 5 && theWord.get(0) != '_' && theWord.get(1) != '_' && theWord.get(2) != '_' && theWord.get(3) != '_' && theWord.get(4) != '_') // NEEDS TO BE CHANGED TO READ WORDS WITH ANY AMOUNT OF LETTERS
         {
            System.out.println("YOU WIN");
            System.exit(0);
         }
         else if(incorrectGuesses == TRIESGIVEN)
         {
            System.out.println("Incorrect Guesses: " + incorrectGuesses + "/" + TRIESGIVEN);
            System.out.println("---------------------------------------------\nSorry you guessed incorrectly too many times.\nThe correct word was: -------- " + getCurrentWord().toUpperCase() + " --------");
            System.exit(0);
         }
         System.out.println("Incorrect Guesses: " + incorrectGuesses + "/" + TRIESGIVEN);

   }
   /**
      Adds the guessedLetter to a list to display all already guessed letters
   */
   public static void lettersAlreadyGuessedList()
   {
      if(!guessedLetters.contains(getLetterGuess())){
         guessedLetters.add(getLetterGuess());
      }
      else{
         System.out.println("LETTER ALREADY GUESSED: TRY AGAIN!");
      }
   }
   /**
      Adds an incorrectGuess for only the first time an incorrect letter is guessed
   */
   public static void incorrectGuessIncrements()
   {
      CharSequence seq = new StringBuilder(1).append(letterGuess);
      if(!getCurrentWord().contains(seq)){
         incorrectGuesses++;
         if(guessedLetters.contains(getLetterGuess()))
            incorrectGuesses--;
      }
   }
}