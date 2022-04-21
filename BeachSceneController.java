import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.lang.*;

public class BeachSceneController {
    
    private static final ArrayList<Character> theWord = new ArrayList<>();
    private static final ArrayList<Character> guessedLetters = new ArrayList<>(); // list of the letters the user already guessed
    private static char letterGuess; // the user's inputted guess
    private static String currentWord;  // randomly picked word
    private static int incorrectGuesses = 0;  // keeps the amount of times an incorrect letter was guessed
    private static final int TRIESGIVEN = 6;  // number of tries allowed before user loses

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
      // places uppercase letter guess into an array and then prints the array as a String into the letters already guessed box
      if(isALetter(letterGuessBox.getText().charAt(0))){
         char gds = letterGuessBox.getText().charAt(0);
         String cnd = Character.toString(gds).toUpperCase();
         if(!guessedLetters.contains(gds)){
            guessedLetters.add(gds);
         }
         String snc = guessedLetters.toString();
         
         lettersGuessedList.setText(snc);
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

}
