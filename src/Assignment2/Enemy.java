package Assignment2;

public class Enemy extends Character{
	
	private Item item;
	public Enemy(String n, String q, int h, int l, int g, Item i) {
		super(n, q, h, l, g);
		item = i;
	}

	@Override
	public void attack(Character c) {
		// TODO Auto-generated method stub
		
	}
	
	public Item getItem(){
		return item;
	}
	
}
