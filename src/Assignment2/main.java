package Assignment2;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		
		Level map = new Level();
		try {
			map.generateLevel(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hero hero = new Hero("Link","Woo hoo",map.findStartLocation());
		map.displayMap(hero.getLocation());
		
		
		/*
		 * Check move
		 * if hero.goNorth(map) return 'n' = invalid move
		 * 
		 */
		
		//System.out.println("What is your name, traveler ? ");
		//reader.nextLine();
	}
}
