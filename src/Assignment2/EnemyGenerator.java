package Assignment2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnemyGenerator {
	
	ArrayList<Enemy> enemyList;
	ArrayList<String> mobs = new ArrayList<String>();
	
	public EnemyGenerator(){
		Scanner reader = new Scanner("EnemyList.txt");
		Random rd = new Random();
		ItemGenerator items = new ItemGenerator();
		while(reader.hasNext()){
			String mob[] = reader.nextLine().split(",");
			enemyList.add(new Enemy(mob[0],mob[1],Integer.parseInt(mob[2]),1,rd.nextInt(10 + 1),items.generator()));
		}
	}
	
	public Enemy generateEnemy(int level){
		Random rd = new Random();
		return enemyList.get(rd.nextInt(enemyList.size()));
	}

}
