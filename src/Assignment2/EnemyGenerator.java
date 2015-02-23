package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnemyGenerator {
	
	ArrayList<Enemy> enemyList;
	ArrayList<String[]> mobs = new ArrayList<String[]>();
	
	public EnemyGenerator(){
		Scanner reader;
		try {
			reader = new Scanner(new File("EnemyList.txt"));
			while(reader.hasNext()){
				mobs.add(reader.nextLine().split(","));
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Enemy generateEnemy(int level){
		Random rd = new Random();
		ItemGenerator items = new ItemGenerator();
		int index = rd.nextInt(mobs.size());
		System.out.println(index);
		return new Enemy(mobs.get(index)[0]
				,mobs.get(index)[1],
				Integer.parseInt(mobs.get(index)[2]),1
				,rd.nextInt(10 + 1),items.generator());
	}

}
