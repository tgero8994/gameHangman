# Hangman Game
   Basic concept user enters in a letter guess and if the letter is in the word it replaces the blank space(s) where the letter(s) appear.
   If guessed incorrect the hangman character starts to be built beginning with the head with a total of 6 parts.


# Important Files

   * HangmanGameMain.java - Contains the main method and code to load the FXML file for the initial scene.

   * BeachSceneController.java - The bulk of the program code is found here. This is the main Java file that is "tied" to the FXML document. 
                                 This contains the methods that are run when buttons are pushed as well as the code to access the API.
                                 
   * BeachScene.fxml - This is the file generated by Scene Builder that contains all of the information for displaying the UI and for linking the UI components to the BeachSceneController.java file.
   
   * RandomWord.java - Used for JSON returned by the random word generator API that is being parsed by GSON.

   * Images - File including all images need for the BeachScene.fxml to work properly. Images must be in this location to appear in Scene Builder.
  
   
# Notes

   * This project is configured to work with the JGrasp IDE.
   
   * There is no API key needed as of now.