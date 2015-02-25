package Assignment2;
/**
 * Main Class - game dungeon crawler.
 * @author Pongsathorn Cherngchaosil
 */
import java.awt.Point;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		//Initialize Scanner to read user input
		Scanner reader = new Scanner(System.in);
		//Max Level available
		int MAX_LEVEL = 3;
		//Initialize Hero
		Hero hero = null;
		//Initialize level/ map
		Level map = new Level();
		//Initialize file reader
		File f = new File("hero.dat");
		//Initialize state of the game
		boolean running = true;
		
		//Process of checking and reading file for Hero
		if(f.exists()){
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				try {
					hero = (Hero) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in.close();	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			//If the file doesn't exist then create a hero from user input
			System.out.print("What is your name, traveler? ");
			hero = new Hero(reader.nextLine(),"Woo hoo",new Point(0,0));
		}
		//Initialize current level
		int lev = hero.getLevel();
		//Generator for creating enemy
		EnemyGenerator enemyGen = new EnemyGenerator();
		//Try to generate map of that level
		try {
			map.generateLevel(lev);
			//Set the locaiton of hero at the starting point
			hero.setLocation(map.findStartLocation());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(running){
			//Ask playing to choose direction then check room of the next move.
			char map_Element = nextPosition(reader, hero, map);
			switch(map_Element){
			case 's':
				// sell item
				System.out.println("Sell Item : ");
				sellItems(hero);
				break;
			case 'i':
				//receive item
				foundItem(hero);
				break;
			case 'm':
				//Found enemy, procede to fight them
				running = fight(hero,enemyGen.generateEnemy(lev),map);
				break;
			case 'f':
				//Hero reached the end of the level, goes to the next one
				lev++;
				//if heor finish max level then the state of the game will be false
				//game ended
				if(lev > MAX_LEVEL)
					running = false;
				//generate next map level
				try {
					map.generateLevel(lev);
					hero.setLocation(map.findStartLocation());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//change level of the hero
				hero.setLevel(lev);
				//Save hero's progress
				//Save heor into file
				try {
					ObjectOutputStream out = new ObjectOutputStream(
											new FileOutputStream(f));
					out.writeObject(hero);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	/**
	 * If hero found item then they have option to keep or sellit
	 * If bag is full then it will automatically sell the item
	 * @param hero hero, giving access to hero's methods
	 */
	public static void foundItem(Hero hero){
		//Generate item
		ItemGenerator itemGen = new ItemGenerator();
		Item found = itemGen.generator();
		System.out.printf("You found a %s%nWhat do you do?"
				+ "%n1. Keep it%n2. Sell it%n",found.getName());
		//ask user for action
		int choice = checkInt(1,2);
		//sell item
		if(choice == 2){
			System.out.printf("You sell your %s for %d gold.%n",found.getName(),found.getValue());
			hero.collectGold(found.getValue());
		}
		//keep item if bag isn't full otherwise sell it
		else if(hero.pickUpItem(found)){
			System.out.printf("Cannot Pick up Item, Bag is full\n"
					+ "Item sold for %d%n",found.getValue());
		}
	}
	/**
	 * Sell Item in inventory
	 * @param hero user control character
	 */
	public static void sellItems(Hero hero){
		
		System.out.println("0. quit");
		for(int i = 0; i < hero.getItems().size(); i++){
			System.out.printf("%d. %s%n",(i+1),hero.getItems().get(i).getName());
		}
		int choice = checkInt(0,hero.getItems().size());
		if(choice != 0){
			System.out.printf("Sell %s for %d golds.%n",hero.getItems().get(choice-1).getName(),hero.getItems().get(choice-1).getValue());
			hero.collectGold(hero.getItems().get(choice-1).getValue());
			hero.removeItem(choice-1);
			sellItems(hero);
		}
	}
	/**
	 * Process of fighting if found enemy
	 * @param hero Character of user
	 * @param enemy Random generated enemy
	 * @param map map of the current level
	 * @return true is hero is alive, false if hero died
	 */
	public static boolean fight(Hero hero, Enemy enemy,Level map){
		int action = 0;
		while(hero.getHp() > 0 && enemy.getHp() > 0){
			hero.display();
			System.out.printf("%n%s has encountered %s%nIt has %d health.%n",hero.getName(),enemy.getName(),enemy.getHp());
			//check for health potions
			if(hero.checkForHealthPotion()){
				System.out.println("What do you do?\n1. Run Away\n2. Attack\n3. Use Health Potion ");
				action = checkInt(1,3);
			}
			else{
				System.out.println("What do you do?\n1. Run Away\n2. Attack\n");
				action = checkInt(1,2);
			}
			//Action of the user
			switch(action){
			//user choose to run away, hero will random run to the next available room
			case 1:
				Random rd = new Random();
				char map_Element;
				do{
					int temp = rd.nextInt(4)+1;
					switch(temp){
					case 1:
						map_Element = hero.goNorth(map);
						break;
					case 2:
						map_Element = hero.goSouth(map);
						break;
					case 3:
						map_Element = hero.gotEast(map);
						break;
					case 4:
						map_Element = hero.goWest(map);
						break;
					default:
						map_Element = 'n';
					}
				}while(map_Element == 'n');
				break;
			// Hero decided to fight enemy	
			case 2:
				hero.attack(enemy);
				if(enemy.getHp() <= 0){
					System.out.printf("%s killed a %s%n",hero.getName(),enemy.getName());
					System.out.printf("%s say \"%s\"%n",hero.getName(),hero.getQuip());
					System.out.printf("%s receive %d gold.%n",hero.getName(),enemy.getGold());
					System.out.printf("%s receive a %s%n",hero.getName(),enemy.getItem().getName());
					//check if the enemy is killed
					if(!hero.pickUpItem(enemy.getItem())){
						System.out.printf("Cannot Pick up Item, Bag is full\n"
								+ "Item sold for %d%n",enemy.getItem().getValue());
						hero.removeItem(enemy.getItem());
					}
				}
				else
				{
					//If enemy is alive then they will attack
					enemy.attack(hero);
					if(hero.getHp() <= 0){
						System.out.printf("%s killed a %s",enemy.getName(),hero.getName());
						System.out.printf("%s say \"%s\"%n",enemy.getName(),enemy.getQuip());
						System.out.println("Game Over");
						return false;
					}
				}
				break;
			//Only available when there's a health potion if inventory
			case 3:
					hero.heal(15);
					for(Item i:hero.getItems()){
						if(i.getName().equals("Health Potion"))
							hero.removeItem(i);
						break;
					}
				break;
			}
		}
		return true;
	}
	/**
	 * Ask User for next direction to move to
	 * @param reader Reader for reading user input
	 * @param hero User Character
	 * @param map Current map
	 * @return the element in the next room
	 */
	public static char nextPosition(Scanner reader, Hero hero, Level map){
		char map_Element;
		
		System.out.println("Dungeon Map:\n");
		map.displayMap(hero.getLocation());
		
		switch(getDirection(reader)){
		case 1:
			map_Element = hero.goNorth(map);
			break;
		case 2:
			map_Element = hero.goSouth(map);
			break;
		case 3:
			map_Element = hero.gotEast(map);
			break;
		case 4:
			map_Element = hero.goWest(map);
			break;
		default:
			//There is an error
			map_Element = 'n';
		}
		// if element is invalid
		if(map_Element == 'n'){
			System.out.println("Cannot move that direction");
			return nextPosition(reader,hero,map);
		}
		else
			return map_Element;
	}
	/**
	 * Helper method that ask user for input for next direction
	 * Also check for invalid input
	 * @param reader reader to read user input
	 * @return the value to determined the direction
	 */
	private static int getDirection(Scanner reader){
		int read = 0;
		boolean valid = false;
		while(!valid){
			try{
				System.out.println("Choose a Direction:\n"
						+ "1. North\n"
						+ "2. South\n"
						+ "3. East\n"
						+ "4. West\n");
				read = Integer.parseInt(reader.nextLine());
				if(read  >= 1 && read <= 4)
					valid = true;
				else
					System.out.println("Invalid Input");
				
			} 
			catch(NumberFormatException nfe){
				
				System.out.println("Invalid Input");
			}
		}
		return read;
	}
	/**
	 * check for valid input
	 * @param low minimum number
	 * @param high maximum number
	 * @return the user's action represented as integer
	 */
	public static int checkInt(int low, int high){
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while(!valid){
			
			if(in.hasNextInt()){
				validNum = in.nextInt();
				if(validNum >= low && validNum <= high){
					valid = true;
				}else{
					System.out.print("Invalid- Retry: ");
				}
			}else {
				in.next();
				System.out.println("Invalid input- Retry: ");
			}
		}
		return validNum;
	}
	
}
