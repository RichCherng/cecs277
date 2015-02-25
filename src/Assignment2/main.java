package Assignment2;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		int MAX_LEVEL = 3;
		Hero hero = null;
		Level map = new Level();
		File f = new File("hero.dat");
		boolean running = true;
		
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
			System.out.print("What is your name, traveler? ");
			hero = new Hero(reader.nextLine(),"Woo hoo",new Point(0,0));
		}
		int lev = hero.getLevel();
		EnemyGenerator enemyGen = new EnemyGenerator();
		try {
			map.generateLevel(lev);
			hero.setLocation(map.findStartLocation());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(running){
			char map_Element = nextPosition(reader, hero, map);
			switch(map_Element){
			case 's':
				// sell item
				System.out.println("Sell Item : ");
				sellItems(hero);
				break;
			case 'i':
				//recieve item
				foundItem(hero);
				break;
			case 'm':
				running = fight(hero,enemyGen.generateEnemy(lev),map);
				break;
			case 'f':
				
				lev++;
				if(lev > MAX_LEVEL)
					running = false;
				try {
					map.generateLevel(lev);
					hero.setLocation(map.findStartLocation());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hero.setLevel(lev);
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
		
		/*
		 * Check move
		 * if hero.goNorth(map) return 'n' = invalid move
		 * 
		 */
		
		//System.out.println("What is your name, traveler ? ");
		//reader.nextLine();
	}
	
	public static void foundItem(Hero hero){
		ItemGenerator itemGen = new ItemGenerator();
		Item found = itemGen.generator();
		System.out.printf("You found a %s%nWhat do you do?"
				+ "%n1. Keep it%n2. Sell it%n",found.getName());
		int choice = checkInt(1,2);
		if(choice == 2){
			System.out.printf("You sell your %s for %d gold.%n",found.getName(),found.getValue());
			hero.collectGold(found.getValue());
		}
		else if(hero.pickUpItem(found)){
			System.out.printf("Cannot Pick up Item, Bag is full\n"
					+ "Item sold for %d%n",found.getValue());
		}
	}
	
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

		
		/*int choice;
		while(hero.getItems().size() > 0 )
		for(int i = 0; i < hero.getItems().size(); i++){
			System.out.printf("%d. hero.getItems().get(i)",(+1));
		}
		choice = checkInt(1,hero.getItems().size()+1);
		if(choice == hero.getItems().size()+1)
			break Enter;
		else
		{
			
		}*/
		/*System.out.println("Sell all items?\n1. Yes\n2. No.");
		int choice = checkInt(1,2);
		if(choice == 1){
			for(int i = 0; i < hero.getItems().size(); i++){
				System.out.printf("Sell %s for %d golds.%n",hero.getItems().get(i).getName(),hero.getItems().get(i).getValue());
				hero.collectGold(hero.getItems().get(i).getValue());
				hero.removeItem(i);
			}
		}*/
	}
	
	public static boolean fight(Hero hero, Enemy enemy,Level map){
		int action = 0;
		while(hero.getHp() > 0 && enemy.getHp() > 0){
			hero.display();
			System.out.printf("%n%s has encountered %s%nIt has %d health.%n",hero.getName(),enemy.getName(),enemy.getHp());
			if(hero.checkForHealthPotion()){
				System.out.println("What do you do?\n1. Run Away\n2. Attack\n3. Use Health Potion ");
				action = checkInt(1,3);
			}
			else{
				System.out.println("What do you do?\n1. Run Away\n2. Attack\n");
				action = checkInt(1,2);
			}
			switch(action){
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
			case 2:
				hero.attack(enemy);
				if(enemy.getHp() <= 0){
					System.out.printf("%s killed a %s%n",hero.getName(),enemy.getName());
					System.out.printf("%s say \"%s\"%n",hero.getName(),hero.getQuip());
					System.out.printf("%s receive %d gold.%n",hero.getName(),enemy.getGold());
					System.out.printf("%s receive a %s%n",hero.getName(),enemy.getItem().getName());
					
					if(!hero.pickUpItem(enemy.getItem())){
						System.out.printf("Cannot Pick up Item, Bag is full\n"
								+ "Item sold for %d%n",enemy.getItem().getValue());
						hero.removeItem(enemy.getItem());
					}
				}
				else
				{
					enemy.attack(hero);
					if(hero.getHp() <= 0){
						System.out.printf("%s killed a %s",enemy.getName(),hero.getName());
						System.out.printf("%s say \"%s\"%n",enemy.getName(),enemy.getQuip());
						System.out.println("Game Over");
						return false;
					}
				}
				break;
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
			map_Element = 'n';
		}
		if(map_Element == 'n'){
			System.out.println("Cannot move that direction");
			return nextPosition(reader,hero,map);
		}
		else
			return map_Element;
	}
	
	public static int getDirection(Scanner reader){
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
