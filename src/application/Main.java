package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/JavaFX.fxml"));
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
