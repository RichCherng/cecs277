package Assignment2;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		Hero hero;
		Level map = new Level();
		try {
			map.generateLevel(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("What is your name, traveler? ");
		hero = new Hero(reader.nextLine(),"Woo hoo",map.findStartLocation());
		while(true){
			char map_Element = nextPosition(reader, hero, map);
		}
		
		/*
		 * Check move
		 * if hero.goNorth(map) return 'n' = invalid move
		 * 
		 */
		
		//System.out.println("What is your name, traveler ? ");
		//reader.nextLine();
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
	
}
