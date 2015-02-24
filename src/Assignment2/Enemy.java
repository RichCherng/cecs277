package Assignment2;

import java.util.Random;

public class Enemy extends Character{
	
	private Item item;
	public Enemy(String n, String q, int h, int l, int g, Item i) {
		super(n, q, h * l, l, g);
		item = i;
	}

	@Override
	public void attack(Character c) {
		Random rd = new Random();
		int damage = rd.nextInt(10 * getLevel());
		System.out.printf("%s hit a %s for %d damage.%n",getName(),c.getName(),damage);
		c.takeDamage(damage);
	}
	
	public Item getItem(){
		return item;
	}
	
}
