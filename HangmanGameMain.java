import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.*;

public class HangmanGameMain extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception
   {
      try{
      // Load the GUI from FXML built in Scene Builder
      Parent root = FXMLLoader.load(getClass().getResource("BeachScene.fxml"));    
      Scene scene = new Scene(root);      
      stage.setTitle("Hangman Game!");
      
      // The stylesheet is currently set in Scene Builder
      // Note you can also load this programmatically
      // scene.getStylesheets().add("styles.css");

      stage.setScene(scene);
      stage.show();
      } catch (Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace();
      }
      // All of the code that interacts with the API and the Scene 
      //   is found in FXMLTempAppController.java
      
   }

}