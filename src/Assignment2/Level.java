package Assignment2;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
/**
 * Level class - representing map 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Level implements Serializable{
	//Map in the form of 2d array
	private char[][] level;
	/**
	 * Constructor - create a map
	 */
	public Level(){
		 level = new char[4][4];
	}
	/**
	 * return the element of that position
	 * @param l position in the map
	 * @return the elemnt of that position, return 'n' is next position is invalid
	 */
	public char getRoom(Point l){
		if(l.getY() < level.length && l.getY() >= 0 && 
				l.getX() < level[0].length && l.getX() >= 0){
			return level[(int) l.getY()][(int) l.getX()];
		}
		else{
			return 'n';
		}
		
	}
	/**
	 * display the map
	 * @param p position of the player
	 */
	public void displayMap(Point p){
		System.out.println("____________");
		for(int i = 0; i < level.length; i++){
			System.out.print("|");
			for(int ii = 0; ii < level[i].length; ii++){
				if(p.getX() == ii && p.getY() == i){
					System.out.print(" *");
				}
				else
					System.out.print(" "+ level[i][ii]);
			}
			System.out.println(" |");
		}
		System.out.println("____________");
	}
	/**
	 * return starting location
	 * @return starting location in the form of Point
	 */
	public Point findStartLocation(){
		if(level[0].length < 1){
			System.out.println("Level not yet genereated!");
			return new Point(0,0);
		}
		for(int i = 0; i < level.length; i++){
			for(int ii = 0; ii < level[i].length; ii++){
				if(level[i][ii] == 's'){
					return new Point(ii,i);
				}
			}
		}
		return new Point(0,0);
	}
	/**
	 * read in the file of that level and create a map
	 * @param levelNum the level of the map
	 * @throws FileNotFoundException 
	 */
	public void generateLevel(int levelNum) throws FileNotFoundException{
		String fileName = "level"+levelNum+ ".txt";
		Scanner readFile = new Scanner(new File(fileName));
		String[] line;
		char[][] map = new char[4][4];
		for(int i = 0; i < 4; i++){
			 line = readFile.nextLine().split("\\s+");
			 for(int ii = 0; ii < line.length; ii++){
				 map[i][ii] = line[ii].charAt(0);
			 }	
			/* for(String e: line){
				 System.out.print(e);
			 }
			 System.out.println();
			 */
		}
		
		level = map;
	}
}
