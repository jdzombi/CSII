package application;
	
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextArea;

public class Main extends Application {
	@FXML
	protected TextArea txtArea;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game");
			primaryStage.show();
			
			ResourceBundle resources;
			
			txtArea.appendText("gey");

			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	public static void main(String[] args) {
		
		launch(args);
		
		
		
	}
}
