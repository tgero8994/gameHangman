// API imports
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

// Scene Builder imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Font; 

// Method imports
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.lang.*;

public class BeachSceneController implements Initializable
{
   // Variables----------------------------------------------------------------------------------------------   

   // Referenced from the FXML file
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
   @FXML
   private Label invalidMessage;
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
   @FXML
   private RadioButton westernThemeRadio; 
   @FXML
   private AnchorPane themeSelection; 
   @FXML
   private Button checkLetter;
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

   // Referenced internally, not from FXML file
   
   // List of the letters the user already guesses
   private static final ArrayList<Character> guessedLettersArray = new ArrayList<>();
   
   // The user's inputted guess
   private static char firstLetterInGuess;
   
   // The current word to be guessed
   private static String currentWord;
   
   // Incorrect guesses
   private static int incorrectGuesses = 0;
   
   // Correct guesses
   private static int correctGuesses = 0;
   
   // Theme to be used for game
   private enum Theme { BEACH, WESTERN };
   private Theme theme;
   
   // Key to persist the theme in preferences
   public static final String SCENE_THEME = "scene_theme_key";
   
   // FXML Action Events------------------------------------------------------------------------------------- 
       
   // Determines and applies the theme & theme preference by which radio button was pressed
   @FXML
   void themeRadioSelection(ActionEvent event)
   { 
      // Assigns the selected radio button to the corresponding theme
      if(event.getSource() == westernThemeRadio)
         this.theme = Theme.WESTERN;
      else if(event.getSource() == sunnyBeachRadio)
         this.theme = Theme.BEACH;
         
      // Saves theme preference to key
      Preferences p = Preferences.userNodeForPackage(BeachSceneController.class);
      p.put(SCENE_THEME, this.theme.toString());
      
      updateTheme();
   }
   
   // checks if the first letter in the guess letter text box is a valid letter
   // and then places an uppercase value into the guessedLettersArray if that letter isn't already there
   @FXML
   void checkLetterButton(ActionEvent event)
   {
      // Hide theme selection box
      themeSelection.setVisible(false);
      
      // Remove any lingering invalid input styles and hide invalid input message
      letterGuessBox.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
      invalidMessage.setVisible(false);
      
      try // catches error when nothing is entered in the letterGuessBox text field
      {
         // Set only the first letter in the guess box as a char so it can be passed through isALetter() method
         firstLetterInGuess = letterGuessBox.getText().charAt(0);
         // Check if the input is a letter and if it's already been guessed
         if(isALetter(firstLetterInGuess) && isLetterAlreadyGuessed() == false)
         {
            isTheLetterGuessCorrect();
            amountOfCorrect(currentWord, firstLetterInGuess);
            buildHangmanCharacter();
         }
         // Show invalidMessage with "Already Guessed" message when a letter already guessed is entered
         else if(isLetterAlreadyGuessed() == true)
         {
            System.out.println("Letter Already Guessed!");
            letterGuessBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            invalidMessage.setText("Aleady Guessed!");
            invalidMessage.setFont(new Font("Regular", 10));
            invalidMessage.setVisible(true);
         }
         // Show invalidMessage with "Invalid input" message when anything but a letter is entered
         else
         {
            System.out.println("Enter a letter!");
            letterGuessBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            invalidMessage.setText("Invalid Input!");
            invalidMessage.setFont(new Font("Regular", 12));
            invalidMessage.setVisible(true);
         }
      } catch(Exception StringIndexOutOfBoundsException) {
         System.out.println("ERROR! Must Enter a Letter Value.");
      }
      
      System.out.println("INCORRECT: " + incorrectGuesses +" CORRECT: " + correctGuesses);
      
      // Check whether the game is completed
      winOrLoseMessage();
      
      // Clear the input textfield
      letterGuessBox.clear();
   }

   // Calls an API to generate a new word and resets the game
   @FXML
   void resetWordButton(ActionEvent event)
   {
      updateWord();
      resetGame();
   }
   
   // Methods------------------------------------------------------------------------------------------------
   
   // Checks the current theme, sets the corresponding background, and hides the other background
   public void updateTheme()
   {
      if(this.theme == Theme.WESTERN)
      {
         westernPicture.setVisible(true);
         sunnyBeachPicture.setVisible(false);
      }
      
      if(this.theme == Theme.BEACH)
      {
         westernPicture.setVisible(false);
         sunnyBeachPicture.setVisible(true);
      }
   }
   
   /**
      Determines if the user's char variable is a letter
      @param c The char being tested
      @return false if the char is not a letter
      @return true if the char is a letter
   */
   public static boolean isALetter(char c)
   {
      // Accept lowercase and uppercase chars
      return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
   }
   
   /**
      Determines if the user entered the same letter more than once
      @return false if the char isn't already guessed
      @return true if the char is already guessed
   */
   public static boolean isLetterAlreadyGuessed()
   {
      if(guessedLettersArray.contains(firstLetterInGuess))
      {
         return true;  
      }
      else
         return false;
   }
   
   /**
      Adds guessed letters to an array if the letter wasn't already guessed
   */
   public void addLetterToListOfAlreadyGuessed()
   {
      // Adds letters to array if user hasn't guessed it yet
      if(!guessedLettersArray.contains(firstLetterInGuess))
      {
         guessedLettersArray.add(firstLetterInGuess);
      }
      
      // Create a string from the array and display on scene
      String list = guessedLettersArray.toString().toUpperCase();
      lettersGuessedList.setText(list);
   }
   
   /**
      Checks if the user's letter guess is contained in the currentWord.
      If it isn't add a score to incorrectGuesses.
      If it is replace the corresponding letter textfield.
      Calls addLetterToListOfAlreadyGuessed()
   */
   public void isTheLetterGuessCorrect()
   {
      // string version of the array list guessedLettersArray
      String shc = guessedLettersArray.toString();
      
      // When the letter is not in the word and not in the already guessed list it will add 1 to incorrectGuesses
      if(!currentWord.contains("" + firstLetterInGuess) && !shc.contains("" + firstLetterInGuess))
      {
         incorrectGuesses++;
      }
      // When the letter is in the word it fills in the textfield where the correct letter is located
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
      addLetterToListOfAlreadyGuessed();
   }
   
   /**
      Checks if the user has completed the game and applies corresponding styles
   */
   public void winOrLoseMessage()
   {
      // When User LOSES The Game
      if(incorrectGuesses == 6)
      {
         // Letters not guessed will have a red background
         letter1.setStyle("-fx-background-color: lightcoral;");
         letter2.setStyle("-fx-background-color: lightcoral;");
         letter3.setStyle("-fx-background-color: lightcoral;");
         letter4.setStyle("-fx-background-color: lightcoral;");
         letter5.setStyle("-fx-background-color: lightcoral;");
      
         // Fills in the correct letters in letter textfields
         // Letters guessed correctly will have a green background
         // Sets the letters not guessed when the user loses to the correct letter
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
         
         // Do not allow user to enter any further guesses until game is reset
         checkLetter.setDisable(true);
         letterGuessBox.setDisable(true);
      }
      
      // When User WINS The Game
      if(correctGuesses == currentWord.length())
      {
         // Letters guessed correctly will have a green background
         letter1.setStyle("-fx-background-color: lightgreen;");
         letter2.setStyle("-fx-background-color: lightgreen;");
         letter3.setStyle("-fx-background-color: lightgreen;");
         letter4.setStyle("-fx-background-color: lightgreen;");
         letter5.setStyle("-fx-background-color: lightgreen;");
         
         // Do not allow user to enter any further guesses until game is reset
         checkLetter.setDisable(true);
         letterGuessBox.setDisable(true);
      }
   }
   
   /**
      Calls the API to receive a new random five letter word to be guessed
   */
   public void updateWord()
   {
      System.out.println("LOADING... Generating New Word Please Wait.");
      
      // Call the API
      try
      {
         HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://random-word-api.herokuapp.com/word?length=5"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
         HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
         
         // Create a String to hold API response
         String data = response.body();
      
         // Parse the data to GSON
         Gson gson = new Gson();
         String[] word = gson.fromJson(data, String[].class);
      
         // Set the parsed data as the current word to be guessed
         RandomWord randomWord = new RandomWord(word);
         currentWord = randomWord.getWord();
      }
      catch(Exception e){}
      
      System.out.println(currentWord);
   }
   
   /**
      Clears out previous game and gives user option to change theme for next game
   */
   public void resetGame()
   {
      // Clear letter textfields
      letter1.clear();
      letter2.clear();
      letter3.clear();
      letter4.clear();
      letter5.clear();
      
      // Clear the list of already guessed letters
      guessedLettersArray.clear();
      lettersGuessedList.clear();
      
      // Hide corresponding hangman bodyparts
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
      
      // Reset background color of textfields to white
      letter1.setStyle("-fx-background-color: white;");
      letter2.setStyle("-fx-background-color: white;");
      letter3.setStyle("-fx-background-color: white;");
      letter4.setStyle("-fx-background-color: white;");
      letter5.setStyle("-fx-background-color: white;");
      
      // reset correct and incorrect variables
      incorrectGuesses = 0;
      correctGuesses = 0;
      
      // Re-enable access to guessing box
      checkLetter.setDisable(false);
      letterGuessBox.setDisable(false);
      
      // Re-enable access to theme selection
      themeSelection.setVisible(true);
      
      // Remove any lingering invalid input styles
      letterGuessBox.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
   }
   
   /**
      Builds the hangman character one body part at a time depending on the amount of incorrectGuesses
   */
   public void buildHangmanCharacter()
   {
      // Uses the surfer body parts when the beach theme is shown
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
      
      // Uses the cowboy body parts when the western theme is shown
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
   
   /**
      Determines the amount of correct guesses the user has made
   */
   public void amountOfCorrect(String s, char c)
   {
      // Increment correct guesses by one for each time the guessed letter appears in the word  
      for (int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == c)
            correctGuesses++;
      }
   }
   
   // Implement Initializable interface----------------------------------------------------------------------
   @Override
   public void initialize(URL location, ResourceBundle resources)
   { 
      // Determines which theme to use from preferences 
      Preferences p = Preferences.userNodeForPackage(BeachSceneController.class);
      this.theme = Theme.valueOf(p.get(SCENE_THEME, Theme.BEACH.toString()));
      
      // Selects the corresponding theme's radio button
      if(this.theme == Theme.WESTERN)
         this.westernThemeRadio.setSelected(true);
      else
         this.sunnyBeachRadio.setSelected(true);
         
      // Sets the first word for the game
      updateWord();
      updateTheme();
   }
}