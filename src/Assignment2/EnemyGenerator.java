package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * EnemyGenerator class - representing a class that create and store enemy
 * @author Pongsathornn Cherngchaosil
 *
 */
public class EnemyGenerator {
	//list of enemy
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	/**
	 * Constructor - read in file and create a list of enemy
	 */
	public EnemyGenerator(){
		Scanner reader;
		//read in and create enemy at level one from file
		try {
			reader = new Scanner(new File("EnemyList.txt"));
			while(reader.hasNext()){
				String[] mob = reader.nextLine().split(",");
				enemyList.add(new Enemy(mob[0],
										mob[1],
										Integer.parseInt(mob[2]),
										1,
										10,
										null));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * pick random type of enemy and create one according to level
	 * @param level level of enemy
	 * @return enemy
	 */
	public Enemy generateEnemy(int level){
		Random rd = new Random();
		ItemGenerator items = new ItemGenerator();
		int index = rd.nextInt(enemyList.size());
		return new Enemy(enemyList.get(index).getName()
				,enemyList.get(index).getQuip(),
				10*level,level,rd.nextInt(10 + 1),items.generator());
	}

}
