package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

	private int sequence = 1;
	
	
	 private void game_Exit(  )
	    {
	        Platform.exit();
	        System.exit(0);
	    }
	
	@FXML
	public void inputTextHandler(ActionEvent event){

		// inputText is for passing the users text into the primary console, unmodified.
		// checkText is converted to lowercase and used for checking for game commands.
		        String inputText = "";
		        String checkText = "";
		        inputText = inputField.getText();
		        checkText = inputField.getText().toLowerCase();



		// Display users input as formatted text in primary console.
		        dialogue.appendText( pname + ":  " + inputText + "\n" );



		// Beginning Sequence
		        if( isChangeName == true )
		        {
		            if( pname.equals( "" ) && inputField.getText().equals( "" ) )
		            {
		                dialogue.appendText( "Error:  Please Re-enter Name\n" );
		            }
		            else
		            {
		                pname = inputField.getText();
		                isChangeName = false;
		                sequence = 2;
		            }
		        }

		        
		        if(pname != "") {
		        	if(sequence == 2) {
		    			dialogue.appendText("Ah, " + pname + ". Nice to finally meet you. \n");
		    			sequence+=1;
		        	}
		    		
		        }
		// Clear TextField
		        inputField.clear();

		// Check if it is the beginning sequence
		        

		// Pass the users input text to functions which will check for keywords
		        checkText_General( checkText );
		      
	}
	public void beginning() {
		dialogue.setText("What is your name?");
		isChangeName = true;
		
		
		
	}

	public String pname = "";

	public void checkText_General( String checkText ){

        // Check input for game commands
        if( checkText.equals("quit")){
            game_Exit();
        }
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		beginning();
		
	}

}
