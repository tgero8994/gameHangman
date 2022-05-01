import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
// Scene Builder
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.lang.*;

public class BeachSceneController {
    
   private static final ArrayList<Character> guessedLettersArray = new ArrayList<>(); // list of the letters the user already guessed
   private static char firstLetterInGuess; // the user's inputted guess
   private static String currentWord = "hello";  // randomly picked word
   private static int incorrectGuesses = 0;  // keeps the amount of times an incorrect letter was guessed
   private static int correctGuesses = 0;
   
   @FXML
    private ToggleGroup backgroundGroup;
     
   @FXML
    private ImageView beachBody;

   @FXML
    private ImageView beachHead;

   @FXML
    private ImageView beachLeftArm;

   @FXML
    private ImageView beachLeftLeg;

   @FXML
    private ImageView beachRightArm;

   @FXML
    private ImageView beachRightLeg;
    
   @FXML
    private ImageView sunnyBeachPicture;
   
   //------------------------------------CHANGED TO WESTERNPICTURE 
   @FXML
    private ImageView westernPicture;
    
    @FXML
    private ImageView cowboyBody;

    @FXML
    private ImageView cowboyHead;

    @FXML
    private ImageView cowboyLeftArm;

    @FXML
    private ImageView cowboyLeftLeg;

    @FXML
    private ImageView cowboyRightArm;

    @FXML
    private ImageView cowboyRightLeg;
    
   @FXML
    private RadioButton sunnyBeachRadio;
   
   //------------------------------------CHANGED TO WESTERNTHEMERADIO 
   @FXML
    private RadioButton westernThemeRadio;
    
   @FXML
    private AnchorPane themeSelection;
    
   @FXML
    private Button checkLetter;
   //------------------------------------REMOVED HOME BUTTON
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
    
    // Changes the theme determined by what radio button was pressed
   @FXML
    void changeBackground(ActionEvent event) {
      //----------------------------------------------CHANGED TO WESTERN   
      if(event.getSource() == westernThemeRadio){
         westernPicture.setVisible(true);
         sunnyBeachPicture.setVisible(false);
      }
      if(event.getSource() == sunnyBeachRadio){
      //---------------------------------------------CHANGED TO WESTERN
         westernPicture.setVisible(false);
         sunnyBeachPicture.setVisible(true);
      }
    }
    
   @FXML
    void checkLetterButton(ActionEvent event) {
      // checks if the first letter in the guess letter text box is a valid letter
      // and then places an uppercase value into the guessedLettersArray if that letter isn't already there
//        while (letterGuessBox.getText().length() != 1)
//        {
//            letterGuessBox.setStyle("-fx-border-color: red; -fx-border-width: 3;");
//            letterGuessBox.setPromptText("Enter single letters only");
//        }



      firstLetterInGuess = letterGuessBox.getText().charAt(0); // sets only the first letter in the guess box as a char so it can be passed through isALetter() method
      // hides radio buttons to keep preference on
      themeSelection.setVisible(false);
      
      if(isALetter(firstLetterInGuess) && isLetterAlreadyGuessed() == true)   // is the letter guessed a letter and is it a letter not already guessed
      {
         isTheLetterGuessCorrect();
         amountOfCorrect(currentWord, firstLetterInGuess);
         buildHangmanCharacter();
      }
      else
         System.out.println("Enter a letter!");

      System.out.println("INCORRECT: " + incorrectGuesses +" CORRECT: " + correctGuesses);
      winOrLoseMessage();
      letterGuessBox.clear();
   }

   @FXML
    void resetWordButton(ActionEvent event) {
      updateWord();
      resetGame();
   }
   
    /**
      Determines if the user's char variable is a letter.
      @param c is the char being tested.
      @return false if the char is not a letter
      @return true if only letters
   */
   public static boolean isALetter(char c)
   {
      // char guess = c.toUpperCase();
      return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
   }
   
   /**
      Determines if the user entered the same letter more than once.
      @return false if the char is not a letter already guessed.
      @return true if the char is a letter already guessed.
   */
   public static boolean isLetterAlreadyGuessed(){
      if(guessedLettersArray.contains(firstLetterInGuess)){
         System.out.println("Letter Already Guessed, Try Again!");
         return false;  
      }
      else
         return true;
   }
   
   public void addLetterToListOfAlreadyGuessed()
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
      // letter not in word and not in already guessed list so add 1 to incorrectGuesses
      if(!currentWord.contains("" + firstLetterInGuess) && !shc.contains("" + firstLetterInGuess))
      {
         incorrectGuesses++;
      }
      // letter in word, fills in textfield where the correct letter is located in the word
      else if(currentWord.contains("" + firstLetterInGuess) && !shc.contains("" + firstLetterInGuess))
      {
         String myLetter = Character.toString(firstLetterInGuess);
         
         if(currentWord.charAt(0) == firstLetterInGuess)
            letter1.setText(myLetter.toUpperCase());
         if(currentWord.charAt(1) == firstLetterInGuess)
            letter2.setText(myLetter.toUpperCase());
         if(currentWord.charAt(2) == firstLetterInGuess)
            letter3.setText(myLetter.toUpperCase());
         if(currentWord.charAt(3) == firstLetterInGuess)
            letter4.setText(myLetter.toUpperCase());
         if(currentWord.charAt(4) == firstLetterInGuess)
            letter5.setText(myLetter.toUpperCase());
      }
      // letter already guessed
      else
         System.out.println("Letter Already Guessed Try Again");
         
      addLetterToListOfAlreadyGuessed();
   }
   
   /**
      Prints out a win statement when all parts in the theWord arraylist don't equal '_' meaning all letters have been guessed.
      Prints out a loss statement if the user guesses incorrectly six or more times (equal to TRIESGIVEN)
   */
   public void winOrLoseMessage()
   {
      if(incorrectGuesses == 6)
      {
         letter1.setStyle("-fx-background-color: lightcoral;");
         letter2.setStyle("-fx-background-color: lightcoral;");
         letter3.setStyle("-fx-background-color: lightcoral;");
         letter4.setStyle("-fx-background-color: lightcoral;");
         letter5.setStyle("-fx-background-color: lightcoral;");
         //---------------------------------------------------------ADDED
         if (letter1.getText().isEmpty() == false)
            letter1.setStyle("-fx-background-color: lightgreen;");
         else
            letter1.setText(currentWord.substring(0, 1).toUpperCase());
            
         if (letter2.getText().isEmpty() ==  false)
            letter2.setStyle("-fx-background-color: lightgreen;");
         else
            letter2.setText(currentWord.substring(1, 2).toUpperCase());
            
         if (letter3.getText().isEmpty() == false)
            letter3.setStyle("-fx-background-color: lightgreen;");
         else
            letter3.setText(currentWord.substring(2, 3).toUpperCase());
            
         if (letter4.getText().isEmpty() == false)
            letter4.setStyle("-fx-background-color: lightgreen;");
         else
            letter4.setText(currentWord.substring(3, 4).toUpperCase());
            
         if (letter5.getText().isEmpty() == false)
            letter5.setStyle("-fx-background-color: lightgreen;");
         else
            letter5.setText(currentWord.substring(4).toUpperCase());
            
         //---------------------------------------------------------ADDED
         checkLetter.setDisable(true);
         letterGuessBox.setDisable(true);
      }
      if(correctGuesses == currentWord.length())
      {
         letter1.setStyle("-fx-background-color: lightgreen;");
         letter2.setStyle("-fx-background-color: lightgreen;");
         letter3.setStyle("-fx-background-color: lightgreen;");
         letter4.setStyle("-fx-background-color: lightgreen;");
         letter5.setStyle("-fx-background-color: lightgreen;");
         checkLetter.setDisable(true);
         letterGuessBox.setDisable(true);
      }
   }
   
   public void updateWord(){
      System.out.println("LOADING... Please Wait.");
      try{
         HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://random-word-api.herokuapp.com/word?length=5"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
         HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
         String data = response.body();

         Gson gson = new Gson();
         String[] word = gson.fromJson(data, String[].class);
      
         RandomWord randomWord = new RandomWord(word);
         currentWord = randomWord.getWord();
      }catch(Exception e){}
      System.out.println(currentWord);
   }
   
   public void resetGame(){
      // reset letter boxes
      letter1.clear();
      letter2.clear();
      letter3.clear();
      letter4.clear();
      letter5.clear();
      // reset list of already guessed letters
      guessedLettersArray.clear();
      lettersGuessedList.clear();
      // reset person
      if(sunnyBeachPicture.isVisible())
      {
         beachHead.setVisible(false);
         beachBody.setVisible(false);
         beachLeftArm.setVisible(false);
         beachRightArm.setVisible(false);
         beachLeftLeg.setVisible(false);
         beachRightLeg.setVisible(false);
      }
      else if(westernPicture.isVisible())
      {
         cowboyHead.setVisible(false);
         cowboyBody.setVisible(false);
         cowboyLeftArm.setVisible(false);
         cowboyRightArm.setVisible(false);
         cowboyLeftLeg.setVisible(false);
         cowboyRightLeg.setVisible(false);
      }
      // reset background colors to white
      letter1.setStyle("-fx-background-color: white;");
      letter2.setStyle("-fx-background-color: white;");
      letter3.setStyle("-fx-background-color: white;");
      letter4.setStyle("-fx-background-color: white;");
      letter5.setStyle("-fx-background-color: white;");
      // reset correct and incorrect variables
      incorrectGuesses = 0;
      correctGuesses = 0;
      // re-enable access to guessing box
      checkLetter.setDisable(false);
      letterGuessBox.setDisable(false);
      // re-enable access to theme
      themeSelection.setVisible(true);
   }
   
   public void buildHangmanCharacter()
   {
      if(sunnyBeachPicture.isVisible())
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
      if(westernPicture.isVisible())
      {
         if(incorrectGuesses==1)
            cowboyHead.setVisible(true);
         else if(incorrectGuesses==2)
            cowboyBody.setVisible(true);
         else if(incorrectGuesses==3)
            cowboyLeftArm.setVisible(true);
         else if(incorrectGuesses==4)
            cowboyRightArm.setVisible(true);
         else if(incorrectGuesses==5)
            cowboyLeftLeg.setVisible(true);
         else if(incorrectGuesses==6)
            cowboyRightLeg.setVisible(true);
      }
   }
   // does this method before the main method is started 
   public void initialize() { 
      //updateWord();
      // CODE SHOULD NOT ALLOW THE TEXTFIELD TO BE ENTERED IF NOTHING INSIDE
   //       if(!letterGuessBox.getText().isEmpty()) 
   //       {
   //          // disable button to be pressed
   //          checkLetter.setDisable(true);
   //       }
   //       else // enable button
   //          checkLetter.setDisable(false);
   }
   
   public void amountOfCorrect(String s, char c) {
      //initialize count array index  
      for (int i = 0; i < s.length(); i++){
         if(s.charAt(i) == c)
            correctGuesses++;
      }
   }
   
   
}
