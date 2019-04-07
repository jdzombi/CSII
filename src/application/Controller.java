package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
	@FXML
	public TextField scoreField;
	
	private boolean tutorial = false;
	private boolean scoreEnable = true;
	private boolean inventoryEnable = true;
	private boolean sword = false;
	private boolean introScene = false;
	private boolean controls = false;
	private boolean quit = false;
	
	Date time = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	Collection<String> inventory = new ArrayList<>();
	final Iterator<String> i = inventory.iterator();
	public String pname = "???";
	
	int score = 0;
	private int sequence = 0;
	private int gameSequence = 0;
	
	 private void game_Exit(){
	        Platform.exit();
	        System.exit(0);
	    }
	
	@FXML
	public void inputTextHandler(ActionEvent event) throws InterruptedException{

		// inputText is for passing the users text into the primary console, unmodified.
		// checkText is converted to lowercase and used for checking for game commands.
		        String inputText = "";
		        String checkText = "";
		        inputText = inputField.getText();
		        checkText = inputField.getText().toLowerCase();

		        
		 //Quit game function
		        
		        if(quit) {
		        	if(checkText.equals("quit")) {
		            	game_Exit();
		            	}
		            	else if(checkText.equals("cancel")) {
		            		dialogue.appendText("\nOh, in that case. Carry on!\n\n");
		            		
		            		quit = false;
		            	}
		        	
		        }
		        
		       

		// Display users input as formatted text in primary console.
		        dialogue.appendText( pname + ": " + inputText + "\n" );


		// Tutorial
		        

		        if(tutorial == true && introScene == false){
		        	scoreEnable = false;
		        	
		            	if(checkText.equals("yes") && quit == false) {
		                dialogue.setText("In that case, let me tell you how to play!\n");
		                controls = true;
		                
		            	}
		            		else if(checkText.equals("no") && quit == false) {
		            			sequence = 5;
		            			introScene = true;
		            			gameSequence = 1;
		            		}
		            	
		            if(controls==true) {
		            	dialogue.appendText("\nIn this text-based adventure game, you will respond to prompts on the screen, but you do have commands you can access at (mostly) any time!");
		            	dialogue.appendText("\n"); dialogue.appendText("\n-INVENTORY: Displays your currently held items.");
		            	dialogue.appendText("\n-CHECK <ITEM>: Allows you to check an item in your inventory or any other interactable item.");
		            	dialogue.appendText("\n-SAVE");
		            	dialogue.appendText("\n-LOAD");
		            	dialogue.appendText("\n-QUIT: Exits you out of the game. Be sure to save first!\n");
		            	dialogue.appendText("\nStart off by trying the -inventory- command!\n");
		            	sequence = 3;
		            	controls = false;
		            }
		        }
		        	
		        if(sequence==3 && checkText.equals("inventory")) {
					sequence = 4;
				}
		        	
		        

		        	if(sequence == 4) {
		        		dialogue.appendText("\nYou can also check things around you that are indicated by special markers. Like this <apple> right here. Give it a shot!\n");
		        		sequence = 5;
		        	}
		        
		 
		        	
		        	
		        	
		        	
		        //Beginning of the game
		        
		        if(introScene == true) {
		        	//""+dateFormat.format(time) real time
		        	
		        	if(gameSequence == 1) {
		        	dialogue.setText("DATE: February 15th, 30XX. TIME: 7:00AM.\n");
		        	scoreEnable = true;
		        	dialogue.appendText("\n...Hm? It seems like your shift doesn't start for another 2 hours.\n");
		        	dialogue.appendText("");
		        	
		         }
		        	
		        	
		        }
		       
		        
		        
		        
		        
		// Clear TextField
		        inputField.clear();

		        
		        beginning();
		
		        

		// Pass the users input text to functions which will check for keywords
		        checkText_General(checkText);
		        scoreField.setText("Score: " + score +"/100");
		        scoreEnable = true;
		        inventoryEnable = true;
		        
		        
		        if(score >= 100) {
		        	scoreField.setText("Score: "+score + "/100 Congrats!");
		        	scoreEnable = false;
		        }
		      
	}
	public void beginning() throws InterruptedException{
		
		if(sequence==0) {
		inventoryEnable = false;
		dialogue.setText("Welcome! Do you wish to do the tutorial? <YES/NO>\n");
		inventory.add("ID Card");
		inventory.add("Bottle of Cola");
		inventory.add("M.E.H.");
		sequence = 1;
		}
		if(sequence==1) {
			tutorial = true;
		}
		if(sequence==2) {
			controls = true;
		}
		
		
		
		
		
		
	}

	

	public void checkText_General(String checkText){

        // Check input for game commands
        if(checkText.equals("quit")){
            quit = true;
        	dialogue.appendText("\nAre you sure you want to quit?\n");
        	dialogue.appendText("Type -QUIT- to confirm, otherwise type -CANCEL-. \n");
        	
        }
        
        // Secrets
        if(checkText.equals("owo") && scoreEnable) {
        	score += 50;
        }
        
        if(checkText.equals("jack?") || checkText.equals("who is jack?")) {
        	dialogue.appendText("\nThat's you!\n");
        	pname = "Jack";
        }
        
        
       
        //showing the inventory
        if(checkText.equals("inventory") && inventoryEnable) {
        	dialogue.appendText("\nINVENTORY:\n");
        	
        	for (String s : inventory) {
        		dialogue.appendText("-" +s +"\n");
        		}
        	dialogue.appendText("\n");
        }
        
        
        
        
        //inventory item checks
        if(checkText.contains("check") && inventoryEnable){
        	
        	if((checkText.contains("meh") || checkText.contains("m.e.h.")) && inventory.contains("M.E.H.")) {
        	dialogue.appendText("\nThe Multipurpose Encyclopedia and Helper (M.E.H. for short) is a standard knowledgebase with additional functionality through the use of Expansion Chips.");
        	}
        	
        	else if(checkText.contains("id") && inventory.contains("ID Card")) {
            	dialogue.appendText("\nYour standard Identification Card for the Federation of Engineers and Laborers Looking At Stars.\nNAME: Jack \nOCC: Janitor.\nNot the flashiest job, but certainly the most noble!\n\n");
            	pname = "Jack";
            	}
        	else if(checkText.contains("cola") && inventory.contains("Bottle of Cola")) {
            	dialogue.appendText("\nA bottle of the finest made Cola in all of the Galaxy... or at least that's what the label says.\n");
            	}
        
        	
        }
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreField.appendText(" " + score +"/100");
		try {
			beginning();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
