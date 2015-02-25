package Assignment2;

import java.util.Random;
/**
 * Enemy Class - representing item 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Enemy extends Character{
	//Item store with enemy
	private Item item;
	/**
	 * Constructor - create an enemy
	 * @param n name
	 * @param q quip
	 * @param h hp
	 * @param l level
	 * @param g gold 
	 * @param i item
	 */
	public Enemy(String n, String q, int h, int l, int g, Item i) {
		super(n, q, h * l, l, g);
		item = i;
	}

	@Override
	public void attack(Character c) {
		Random rd = new Random();
		int damage = rd.nextInt(10 * getLevel());
		System.out.printf("%s hit %s for %d damage.%n",getName(),c.getName(),damage);
		c.takeDamage(damage);
	}
	/**
	 * return item of enemy 
	 * @return item of enemy 
	 */
	public Item getItem(){
		return item;
	}
	
}
