package Assignment2;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
	private char[][] level;
	
	public Level(){
		
	}
	
	public char getRoom(Point l){
		if(l.getY() < level.length && l.getX() < level[0].length){
			return level[(int) l.getY()][(int) l.getX()];
		}
		else{
			return 'n';
		}
		
	}
	
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
