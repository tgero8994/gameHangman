import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.lang.*;

public class BeachSceneController {
    
    private static final ArrayList<Character> guessedLettersArray = new ArrayList<>(); // list of the letters the user already guessed
    private static char firstLetterInGuess; // the user's inputted guess
    private static String currentWord = "hello";  // randomly picked word
    private static int incorrectGuesses = 0;  // keeps the amount of times an incorrect letter was guessed

    @FXML
    private ImageView beachBody;

    @FXML
    private ImageView beachHead;

    @FXML
    private ImageView beachLeftArm;

    @FXML
    private ImageView beachLeftLeg;

    @FXML
    private ImageView beachPicture;

    @FXML
    private ImageView beachRightArm;

    @FXML
    private ImageView beachRightLeg;

    @FXML
    private Button checkLetter;

    @FXML
    private Button home;

    @FXML
    private TextField letter1;

    @FXML
    private TextField letter2;

    @FXML
    private TextField letter3;

    @FXML
    private TextField letter4;

    @FXML
    private TextField letter5;

    @FXML
    private TextField letterGuessBox;

    @FXML
    private TextArea lettersGuessedList;

    @FXML
    private Button resetWord;
    
    @FXML
    void checkLetterButton(ActionEvent event) {
      // checks if the first letter in the guess letter text box is a valid letter and then places an uppercase value into the guessedLettersArray if that letter isn't already there
      firstLetterInGuess = letterGuessBox.getText().charAt(0); // sets only the first letter in the guess box as a char so it can be passed through isALetter() method
      if(isALetter(firstLetterInGuess))
      {
         isTheLetterGuessCorrect();
         buildHangmanCharcter();
      }
    }

    @FXML
    void resetWordButton(ActionEvent event) {
      
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
   
   public void addLettersToListOfAlreadyGuessed()
   {
      // Adds letters to guess box
         if(!guessedLettersArray.contains(firstLetterInGuess)){
            guessedLettersArray.add(firstLetterInGuess);
         }
         String list = guessedLettersArray.toString().toUpperCase();
         lettersGuessedList.setText(list);
   }
   
   // if word contains letter guessed fill it in aproprate letter box
   // if word doesn't contain the letter guessed +1 incorrect guesses
   // if the array of already guessed contains letter -1 incorrect guesses
   public void isTheLetterGuessCorrect()
   {
      String shc = guessedLettersArray.toString();
      // letter not in word and not in list +1
      if(!currentWord.contains("" + firstLetterInGuess) && !shc.contains("" + firstLetterInGuess))
      {
         incorrectGuesses++;
         System.out.println("Letter Not in word " + incorrectGuesses);
      }
      // letter in word
      else if(currentWord.contains("" + firstLetterInGuess) && !shc.contains("" + firstLetterInGuess)){
         System.out.println("Letter in word " + incorrectGuesses);
      }
      // letter already guessed
      else
         System.out.println("Letter Already Guessed " + incorrectGuesses);
      addLettersToListOfAlreadyGuessed();
   }
   
   public void buildHangmanCharcter()
   {
      if(incorrectGuesses==1)
         beachHead.setVisible(true);
      else if(incorrectGuesses==2)
         beachBody.setVisible(true);
      else if(incorrectGuesses==3)
         beachLeftArm.setVisible(true);
      else if(incorrectGuesses==4)
         beachRightArm.setVisible(true);
      else if(incorrectGuesses==5)
         beachLeftLeg.setVisible(true);
      else if(incorrectGuesses==6)
         beachRightLeg.setVisible(true);
      
   }
}
