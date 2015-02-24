package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnemyGenerator {
	
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ItemGenerator items = new ItemGenerator();
	
	public EnemyGenerator(){
		Scanner reader;
		try {
			reader = new Scanner(new File("EnemyList.txt"));
			while(reader.hasNext()){
				String[] mob = reader.nextLine().split(",");
				enemyList.add(new Enemy(mob[0],
										mob[1],
										Integer.parseInt(mob[2]),
										1,
										10,
										items.generator()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Enemy generateEnemy(int level){
		Random rd = new Random();
		int index = rd.nextInt(enemyList.size());
		return new Enemy(enemyList.get(index).getName()
				,enemyList.get(index).getQuip(),
				10*level,level,rd.nextInt(10 + 1),items.generator());
	}

}
