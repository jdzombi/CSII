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
	private boolean cc = false;
	private boolean introScene = false;
	private boolean controls = false;
	private boolean quit = false;
	private boolean map = false;
	private boolean roomCheck = false;
	
	Date time = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	Collection<String> inventory = new ArrayList<>();
	final Iterator<String> i = inventory.iterator();
	public String pname = "???";
	public String CTime = "7:00";
	public String breakln = "------------------------------------\n";
	
	int score = 0;
	private int sequence = 0;
	private int gameSequence = 0;
	private int conv = 0;
	
	
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
		       if(!checkText.equals("")) {
		        dialogue.appendText( pname + ": " + inputText + "\n" );
		       }


		// Tutorial
		        

		        if(tutorial == true && introScene == false){
		        	scoreEnable = false;
		        	
		            	if(checkText.equals("yes") && quit == false) {
		                dialogue.setText("In that case, let me tell you how to play!\n");
		                controls = true;
		                
		            	}
		            		else if(checkText.equals("no") && quit == false) {
		            			sequence = 7;
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
		        
		        	if(sequence == 5 && checkText.equals("check apple")) {
		        		sequence = 6;
		        		dialogue.appendText("\nWoah! An apple!\n\n");
		        		dialogue.appendText("Keep out for items indicated on the overworld like <this>. Sometimes the clue you were looking for is right under your nose! But with that, I don't think I have anything else to teach you... Remember to be brave, and good luck!\n\n");
		        		dialogue.appendText("Type -GO- whenever you are ready to start.\n\n");
		        	}
		        	if(sequence == 6 && checkText.equals("go")) {
		        		sequence = 7; 
		        		introScene = true;
            			gameSequence = 1;
		        		
		        	}
		        	
		        	
		        	
		        	
		        //Beginning of the game
		        
		        if(introScene == true) {
		        	//""+dateFormat.format(time) real time
		        	
		        	if(gameSequence == 1) {
		        		bedroom();
		        		gameSequence = 2;
		        		cc = false;
		        	}
		        	
		        	
		        	
		        	//checktext 1
		        	if(checkText.equals("1") && gameSequence == 2) {
		        		bedroom();
			        	gameSequence = 3;	
		        	}
		        	else if(checkText.equals("1") && (gameSequence == 3 || gameSequence == 4)) {
		        		gameSequence = 5;
		        	}
		        	else if(checkText.equals("1") && gameSequence == 6 && !cc) {
		        		dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
		        		dialogue.appendText(breakln);
		        		dialogue.appendText("\"Well hello there!\" Zeke says, cheerfully. \"Look who's up early.\" He mops in his usual counter-clockwise motion, in his usual spot, as usual.\n");
		        		dialogue.appendText(breakln);
		        		dialogue.appendText("1.[Enter Supply Closet]\n");
		        		gameSequence = 7;
		        		cc=true;
		        	}
		        	else if(checkText.equals("1") && gameSequence ==7) {
		        		sCloset();
		        		gameSequence = 8;
		        	}
		        	else if(checkText.equals("1") && gameSequence == 8) {
		        		if(inventory.contains("Screwdriver")) {
		        			gameSequence = 10;
		        			hallway();
		        		}
		        		else if(!inventory.contains("Screwdriver")) {
		        			gameSequence = 9;
		        			hallway();
		        		}
		        	}
		        	else if(checkText.equals("1") && gameSequence == 12) {
		        		gameSequence = 13;
		        		
		        	}
		        	
		        	
		        	
		        	
		        	
		        	
		        	if(gameSequence == 3 && checkText.equals("check bedroom")) {
		        		gameSequence = 4;
		        		roomCheck = true;
		        		CTime = "7:22";
		        		bedroom();
		        		
		        		
		        	}
		        	if(gameSequence == 4) {
		        		if(checkText.equals("check window")) {
		        			CTime = "7:34";
		        			dialogue.appendText("\nThe vast, inky void of space never ceases to amaze you. Every star, every planet, so far away but all containing infinite possibilities. I wonder if anyone out there is staring back?\n");
		        		}
		        		
		        		if(checkText.equals("check nightstand") && !map) {
		        			dialogue.appendText("\nInside your nightstand you find a Map Expansion Chip for your M.E.H., how trivial. Why would you need a map when the ship is so easy to naviate? Either way, you insert it into your M.E.H..\n");
		        			map = true;
		        			score += 7;
		        		}
		        		else if(checkText.equals("check nightstand") && map) {
		        			dialogue.appendText("\nThe nightstand is empty.\n");
		        		}
		        	}
		        	
		        	if(gameSequence == 5) {
		        		hallway();
		        		gameSequence = 6;
		        	}
		        	
		        	if(gameSequence == 8) {
		        		if(checkText.equals("check toolbox")) {
		        			
		        			if(inventory.contains("Screwdriver")){
				        		dialogue.appendText("The toolbox is empty.\n\n");
				        		}
		        			if(!inventory.contains("Screwdriver")){
		        			dialogue.appendText("It's not commmon to see a toolbox around these parts. Perhaps a maintenance worker forgot it? Inside you find a screwdriver.\n\n");
		        			inventory.add("Screwdriver");
		        			score+=15;
		        			}
		        			
		        		}
		        	}
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        	if(checkText.equals("2") && gameSequence == 2) {
		        		gameSequence = 13;
		        	}
		        	else if (checkText.equals("2") && gameSequence == 6) {
		        		gameSequence = 7;
		        		sCloset();
		        		gameSequence = 8;
		        		
		        	}
		        	
		        	if(gameSequence==130) {
		        		dialogue.setText("DATE: February 15th, 30XX. TIME: 8:43AM.\n");
			        	dialogue.appendText(breakln);
			        	dialogue.appendText("RED ALERT. RED ALERT. INTRUDERS DETECTED IN SOUTHERN BAY... DAMAGE TO HULL: CRITICAL... EVACUATE IMMEDIATELY.\n");
			        	dialogue.appendText("You shoot out of bed immediately. Zeke is nowhere to be seen.\n");
			        	dialogue.appendText(breakln);
			        	dialogue.appendText("1.[Run]\n");
			        	
			        	if(gameSequence == 130 && checkText.equals("1")) {
			        		dialogue.setText("DATE: February 15th, 30XX. TIME: 8:45AM.\n");
				        	dialogue.appendText(breakln);
				        	dialogue.appendText("You rush out of your bedroom door... the hallway is empty. The warning siren lights paint the pristine hallway an eerie dark red.\n");
				        	dialogue.appendText("You hear footsteps coming from the left.\n");
				        	dialogue.appendText(breakln);
				        	gameSequence = 131;
			        	}
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

	//rooms
	public void bedroom() {
		
		if(gameSequence == 1) {
			dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
        	scoreEnable = true;
        	dialogue.appendText(breakln);
        	dialogue.appendText("[BZZT]... [BZZT]... [BZZT]... You awake to the drone of your roommates alarm clock.");
        	dialogue.appendText("\n...Hm? It seems like your shift doesn't start for another 2 hours...\n");
        	dialogue.appendText(breakln);
        	dialogue.appendText("1.[Get out of bed]\n");
        	dialogue.appendText("2.[Sleep in]\n\n");
			
		}
		
		if(gameSequence == 2) {
			CTime = "7:17";
    		dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
    		scoreEnable = true;
        	dialogue.appendText(breakln);
        	dialogue.appendText("After working up the courage to leave bed a bit early today, you finally stand up.\n\n");
        	dialogue.appendText("You stand in your 2-person <bedroom> shared by you and your roommate XygZykyel (or Zeke for short). Zeke seems to have left for his shift in the time it took you to wake up.\n");
        	dialogue.appendText(breakln);
        	dialogue.appendText("1.[Leave room]\n\n");
		}
		
		if(gameSequence == 4) {
			dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
			dialogue.appendText(breakln);
			dialogue.appendText("A pristine, plain white square room with one bed and one <nightstand> for you and Zeke alike. On the wall is a <window> showing the vast, empty abyss of space.\n");
			dialogue.appendText("There is a single door that leads to the hallway.\n");
			dialogue.appendText(breakln);
			dialogue.appendText("1.[Leave room]\n");
		}
	}
	
	public void hallway() {
		if(gameSequence == 5) {
		dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
		dialogue.appendText(breakln);
		dialogue.appendText("You walk out into the hallway you know so fondly. A large window stretches along the outer wall showing the vast beauty of space. Zeke is mopping the floor as usual. ");
		dialogue.appendText("At the end of each hallway is a door leading to the cafeteria and restroom areas, but they are locked until 10AM. There is also one supply closet full of everything you'd ever need.\n");
		dialogue.appendText(breakln);
		dialogue.appendText("1.[Talk to Zeke]\n");
		dialogue.appendText("2.[Enter Supply Closet]\n\n");
		}
		
		if(gameSequence == 9 || gameSequence == 10) {
			dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
			dialogue.appendText(breakln);
			dialogue.appendText("You step back into the hallway, but... Zeke isn't there. How very unusual. Maybe he is back in the room?");
			dialogue.appendText("\n");
			dialogue.appendText(breakln);
			dialogue.appendText("1.[Enter Bedroom]\n\n");
			gameSequence = 11;
			
		}
	}

	
	public void sCloset() {
		if(gameSequence == 7) {
			dialogue.setText("DATE: February 15th, 30XX. TIME: "+CTime+"AM.\n");
			dialogue.appendText(breakln);
			dialogue.appendText("The supply closet is full of janitorial tools and supplies. Metal shelves lined with cleaning chemicals, spare brooms, mops, and buckets all contained within a small, windowless room. ");
			dialogue.appendText("Oddly enough, there is an unfamiliar red <toolbox> on the ground.\n");
			dialogue.appendText(breakln);
			dialogue.appendText("1.[Leave Closet]\n\n");
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
        	
        	if((checkText.contains("meh") || checkText.contains("m.e.h.")) && inventory.contains("M.E.H.") && !map) {
        	dialogue.appendText("\nThe Multipurpose Encyclopedia and Helper (M.E.H. for short) is a standard knowledgebase with additional functionality through the use of Expansion Chips.\n");
        	}
        	if((checkText.contains("meh") || checkText.contains("m.e.h.")) && inventory.contains("M.E.H.") && map) {
            	dialogue.appendText("\nThe Multipurpose Encyclopedia and Helper (M.E.H. for short) is a standard knowledgebase with additional functionality through the use of Expansion Chips. Now with additional navigation capabilities.\n");
            	}
        	
        	else if(checkText.contains("id") && inventory.contains("ID Card")) {
            	dialogue.appendText("\nYour standard Identification Card for the Federation of Engineers and Laborers Looking At Stars.\nNAME: Jack \nOCC: Janitor.\nNot the flashiest job, but certainly the most noble!\n\n");
            	pname = "Jack";
            	}
        	else if(checkText.contains("cola") && inventory.contains("Bottle of Cola")) {
            	dialogue.appendText("\nA bottle of the finest made Cola in all of the Galaxy... or at least that's what the label says.\n");
            	}
        	else if(checkText.contains("screwdriver") && inventory.contains("Screwdriver")) {
        		dialogue.appendText("\nA tool for tightening and loosening screws. A simple, yet crucial tool.\n");
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
