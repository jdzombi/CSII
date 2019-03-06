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
	public TextField Uinput;

	private boolean isChangeName = false;

	@FXML
	public void inputTextHandler(ActionEvent event) {

// inputText is for passing the users text into the primary console, unmodified.
// checkText is converted to lowercase and used for checking for game commands.
		String inputText = "";
		String checkText = "";
		inputText = Uinput.getText();
		checkText = Uinput.getText().toLowerCase();
// Display users input as formatted text in primary console.
		dialogue.appendText(pname + ":  " + inputText + "\n");

		if (isChangeName == true) {
			if (pname.equals("") && Uinput.getText().equals("")) {
				dialogue.appendText("Error:  Please Re-enter Name\n");
			} else {
				pname = dialogue.getText();
			}
		}

		beginning();

	}

	public void beginning() {

	}

	public String pname = "";

	// ----------------------------------------------------- beginning
	// ------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beginning();

	}

}
