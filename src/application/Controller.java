package application;

import java.net.URL;
import java.util.ArrayList;
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
	public TextField scoreField;
	private boolean isChangeName = false;
	private boolean scoreEnable = true;
	private boolean inventoryEnable = true;
	private boolean sword = false;
	
	ArrayList<String> inventory = new ArrayList<String>();
	public String pname = "";
	
	int score = 0;
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
		        dialogue.appendText( pname + ": " + inputText + "\n" );



		// Beginning Sequence
		        if( isChangeName == true )
		        {
		        	scoreEnable = false;
		        	inventoryEnable = false;
		        	
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
		    			sequence = 3;
		    			
		        	}
		        	
		        	
		        
		        if(sequence == 3) {
		        	dialogue.appendText("\nThere is a sword in front of you. Type -pick up sword- to add it to your inventory.\n");
		        	sword = true;
		        	sequence = 4;
		        }
		        	
		        if(sequence == 5 && checkText.equals("inventory")) {
		        	dialogue.appendText("\nTUTORIAL COMPLETE\n");
		        	
		        	sequence = 6;
		        }
		        
		        }
		// Clear TextField
		        inputField.clear();

		
		        

		// Pass the users input text to functions which will check for keywords
		        checkText_General( checkText );
		        scoreField.setText("Score: " + score +"/100");
		        scoreEnable = true;
		        inventoryEnable = true;
		        
		        
		        if(score >= 100) {
		        	scoreField.setText("Score: "+score + "/100 Congrats!");
		        	scoreEnable = false;
		        }
		      
	}
	public void beginning() {
		dialogue.setText("What is your name?\n");
		inventory.add("apple");
		isChangeName = true;
		
		
		
		
	}

	

	public void checkText_General(String checkText){

        // Check input for game commands
        if(checkText.equals("quit")){
            game_Exit();
        }
        
        if(checkText.equals("owo") && scoreEnable) {
        	score += 50;
        }
        
        if(checkText.equals("inventory") && inventoryEnable) {
        	//showing the inventory
        	dialogue.appendText("\nINVENTORY:\n");
        	for(int i = 0; i < inventory.size(); i++)
        	dialogue.appendText(inventory.get(i) + "\n");
        	
        }
        if(checkText.equals("pick up sword") && sword) {
        	inventory.add("sword");
        	sword = false;
        	dialogue.appendText("\nYou got a sword! View it in your items by typing -inventory-. \n");
        	sequence = 5;
        	
        }
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreField.appendText(" " + score +"/100");
		beginning();
		
	}

}
