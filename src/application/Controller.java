package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

	@FXML
	public TextArea dialogue;
	@FXML
	public TextField inputField;
	private boolean isChangeName = false;

	
	
	@FXML
	public void inputTextHandler(ActionEvent event) {

		// inputText is for passing the users text into the primary console, unmodified.
		// checkText is converted to lowercase and used for checking for game commands.
		String inputText = "";
		String checkText = "";
		inputText = inputField.getText();
		checkText = inputField.getText().toLowerCase();

		// Display users input as formatted text in primary console.
		dialogue.appendText(pname + ":  " + inputText + "\n");

		
		beginning();
		

	}

	public void beginning() {
		if(!isChangeName) {
			dialogue.appendText("lmao");
		}
	}

	public String pname = "";

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beginning();
		
	}

}
