package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
			try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.setTitle("Adventures of Jack Danger");
			//primaryStage.setResizable(false);
			primaryStage.show();
			
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
		
		
	}
}
