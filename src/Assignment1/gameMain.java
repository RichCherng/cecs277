package Assignment1;
/**
 * Main class - game that has player as the Jedi with his forces fighting agaist Sithlord
 * @author Pongsathorn Cherngchaosil 012124071
 */


import java.util.Random;
import java.util.Scanner;

public class gameMain {
	
	public static void main( String args[]){
		
		//Initialize variable for holding the name of the player
		String jediName;
		//Arrays of strings containing the name of all the missions
		String[] missionsName = {"Hunt the Sith Lord", "Capture the Imperial Base"};
		//Arrays of Entity that contain each team character.
		Entity[] rebelTeam = new Entity[8];
		Entity[] imperialTeam = new Entity[6];
		
		//Asking player to input a name for the Jedi then create Jedi as the first character in the team.
		System.out.println("Welcome to the Star Wars Galaxy\nPlease choose a name for your Jedi:");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		jediName = reader.nextLine();
		rebelTeam[0] = new Jedi(jediName,"For the Republic","blue");
		
		//Initialize the rest of the Jedi team
		for(int i = 1; i < 6; i++){
			rebelTeam[i] = new Rebel("Rebel Trooper " + i, "Pew pew pew....");
		}
		rebelTeam[6] = new Medical("2-1B");
		rebelTeam[7] = new Astromech("R2-D2");
		
		//Initialize the imperial team.
		imperialTeam[0] = new SithLord("Darth Vadar","Force is Strong with this one.","red");
		for(int i = 1; i < 6; i++){
			imperialTeam[i] = new Stormtrooper("Stromtrooper  " + i, "Bam bam bam");
		}
		
		//Ask the player to choose a mission
		System.out.println("\nChoose a Mission:");
		int missionNumber;
		for( int i = 0; i < missionsName.length; i++ ){
			System.out.println((i+1) + ". " + missionsName[i] );
		}
		missionNumber = checkInt(1,missionsName.length);
		
		/*
		 * Scenario 1 - Run across the Sith Lord with troops
		 * Scenario 2 - Run across door
		 * Scenario 3 - Run across Computer 
		 */
		
		//After the player selected the mission, run through each scenario of the mission
		int[][] scenario = {{1,1,1},
							{1,2,3}};
		for(int i = 0; i < scenario[missionNumber-1].length; i++){
			playScenario(scenario[missionNumber-1][i],rebelTeam,imperialTeam);
			//Special event where player will fight Imperial Team more than once
			if(missionNumber == 1){
				if(i<=1){
					System.out.println("Enemies escaped!!!");
					reset(imperialTeam);
				}
			}
			//If the player died during the mission, mission is ended.
			if(!rebelTeam[0].getActive())
				break;
		}
		
		//Upon ending the mission, output the appropriate announcement.
		if(rebelTeam[0].getActive()){
			System.out.println("\nMISSION COMPLETE!\n");
		}else{
			System.out.println("\nYour Hero has fallen");
		}
		
	}
	
	/**
	 * Play each scenario and responds according to it.
	 * @param scenario The type of scenario that will be run
	 * @param rebel The Rebel Team
	 * @param imperial The Imperial Team
	 */
	public static void playScenario(int scenario,Entity[] rebel, Entity[] imperial){
		
		switch(scenario){
		case 1:  //Rebel Team fight Imperial Team
			 fight(rebel, imperial);
			break;
		case 2:  //The player encounter a door
			System.out.print("You ran across a gaint door\nHave droid open the door? 1(Yes) or 0(No):");
			int DECISION_DOOR =  checkInt(0,1);
				if(DECISION_DOOR < 1)
				{
					System.out.print("You ran across a gaint door\nHave droid open the door? 1(Yes) or 0(No):");
					DECISION_DOOR = checkInt(0,1);
				}
			rebel[7].doTask(new Door("Door",1));
			break;
		case 3:  //The player encounter a computer
			System.out.print("You ran across a computer room.\nHave droid hack the computer? 1(Yes) or 0(No):");
			int DECISION_COMPUTER =  checkInt(0,1);
				while(DECISION_COMPUTER < 1)
				{
					System.out.print("You ran across a computer room.\nHave droid hack the computer? 1(Yes) or 0(No):");
					DECISION_COMPUTER = checkInt(0,1);
				}
			rebel[7].doTask(new Computer("Computer",1));
			break;
		}
	}
	
	/**
	 * This method take care of the fighting simulation.Printing out detail of the fight and ask 
	 * player for actions.
	 * @param rebel Rebel Team
	 * @param imperial Imperial Team
	 */
	public static void fight(Entity[] rebel, Entity[] imperial){
		System.out.println("You run across the Sith Lord, he has troops with him. Attack!");
		
		//While the Jedi still alive and one or more of the imperial team is still active, keep playing.
		while(rebel[0].getActive() && !win(imperial)){
			System.out.println("\nGood Guys\n_________");
			//Print details of the Rebel team
			printEntity(rebel);
			System.out.println("\nBad Guys\n________");
			//Print details of the Imperial Team
			printEntity(imperial);
			
			//Give player choice of actions to choose
			System.out.println("\nWhat do you want to do?");
			System.out.println("1. Attack.\n2. Have the droid to heal someone");
			int PLAYER_DECISION = checkInt(1,3);
			
			//response according to the action the player chose.
			if(PLAYER_DECISION != 2){ //attack action
				//tell Jedi to do it task. chooseEntity selected the target that will be hit by Jedi attack
				rebel[0].doTask(chooseEntity(imperial));
			}else{ // heal action
				System.out.println("\nChoose someone to heal:");
				
				//Given the options for healing.
				for(int i =0; i < rebel.length-2; i++){
					System.out.println((i+1)+". "+rebel[i].getName());
				}
				int CHOOSE_HEAL = checkInt(1,rebel.length-2)-1;
				System.out.print('\n'+rebel[6].getName()+" heal "+rebel[CHOOSE_HEAL].getName());
				
				//Tell the Medical Droid to heal.
				rebel[6].doTask(rebel[CHOOSE_HEAL]);		
			}
			
			//Running through the Rebel team and telling them to do their task.
			for(int i = 1; i < rebel.length; i++){
				
				//if all the imperial characters are down then end the loop.
				if(win(imperial)){
					break;
				}
				
				//check if the character still active to do their task.
				if(rebel[i].getActive()){
					
					rebel[i].doTask(imperial[pickTarget(imperial)]);
				}else{
					System.out.println(rebel[i].getName()+ " is down.");
				}
			}
			System.out.println();
			
			//Running through the imperial team and telling them to do their task.
			for(Entity e:imperial){
				
				//if the player's character is down then the game will end.
				if(!rebel[0].getActive()){
					break;
				}
				
				//check if the character still active to do their task.
				if(e.getActive()){
					e.doTask(rebel[pickTarget(rebel)]);
				}else{
					System.out.println(e.getName()+ " is down.");
				}
			}
		}
		
		//Output the appropriate announcement according to the situation.
		if(win(imperial)){
			System.out.println("\nThe enemies are defeated!!!!\n");
		}
		if(!rebel[0].getActive()){
			System.out.println("\nGAME OVER!!!\n");
		}
	}
	
	/**
	 * This method is use by all the character except the player to choose the valid target to attack.
	 * @param e The array of entity that will be selected.
	 * @return the location of that entity in the array.
	 */
	public static int pickTarget(Entity[] e){
		
		//generate a random location within the array
		Random random = new Random();
		int PICK_TARGET = random.nextInt(e.length);
		
		//if the target is inactive or it is a droid then redo.
		while((!e[PICK_TARGET].getActive()|| (e[PICK_TARGET] instanceof Droid))){
			PICK_TARGET = random.nextInt(e.length);
		}
		return PICK_TARGET;
	}
	
	/**
	 * This method check if all the imperial team members are down
	 * @param imperial Array of imperial team.
	 * @return if all imperial team member are inactive, return true.
	 */
	public static boolean win(Entity[] imperial){
		
		boolean win = true;
		for(Entity e:imperial){
			if(e.getActive())
				win = false;
		}
			
		return win;
	}
	
	/**
	 * Reset the imperial team 
	 * @param e The array of the imperial team.
	 */
	public static void reset(Entity[] e){
		e[0].modifyHp(100);
		for(int i = 1; i < e.length; i++){
			e[i].modifyHp(50);
		}
	}
	
	/**
	 * This method is use by Jedi character to ask player to select an Entity to attack.
	 * @param entity Imperial Team.
	 * @return The entity that has been selected.
	 */
	public static Entity chooseEntity(Entity[] entity){
		System.out.println("\nChoose someone to attack:");
		
		//print out the list of imperial team.
		for(int i =0; i < entity.length; i++){
			System.out.println((i+1)+". "+entity[i].getName());
		}
		
		//selected the entity.
		int CHOOSE_ATTACK = checkInt(1,entity.length);
		return entity[CHOOSE_ATTACK-1];
	}
	
	/**
	 * Print the list of Entity with their Hp.
	 * @param e The array of Entity.
	 */
	public static void printEntity(Entity[] e){
		final int SPACE_INDENT = 20;
		
		//Loop through each entity and print out its detail.
		for( Entity i: e){
			System.out.print(i.getName());
			for ( int ii = 0; ii < ( SPACE_INDENT - i.getName().length() ); ii++ ){
				System.out.print(" ");
			}
			System.out.println(i.getHp());
		}
	}
	
	/**
	 * Check for invalid input.
	 * @param low The the lowest value possible.
	 * @param high The highest value possible
	 * @return The valid value.
	 */
	@SuppressWarnings("resource")
	public static int checkInt( int low, int high){
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while(!valid){
			
			if(in.hasNextInt()){
				validNum = in.nextInt();
				if( validNum >= low && validNum <= high){
					valid = true;
				}else{
					System.out.print("Invalid- Retry: ");
				}
			}else{
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		
		return validNum;
	}

}
