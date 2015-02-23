package Assignment2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnemyGenerator {
	
	ArrayList<Enemy> enemyList;
	ArrayList<String[]> mobs = new ArrayList<String[]>();
	
	public EnemyGenerator(){
		Scanner reader = new Scanner("EnemyList.txt");
		
		while(reader.hasNext()){
			mobs.add(reader.nextLine().split(","));
			
		}
	}
	
	public Enemy generateEnemy(int level){
		Random rd = new Random();
		ItemGenerator items = new ItemGenerator();
		int index = rd.nextInt(enemyList.size());
		return new Enemy(mobs.get(index)[0],mobs.get(index)[1],
				Integer.parseInt(mobs.get(index)[2]),1
				,rd.nextInt(10 + 1),items.generator());
	}

}
