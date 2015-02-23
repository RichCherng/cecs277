package Assignment2;

public abstract class Character {
	private String name,quip;
	private int level, hp, gold;
	
	public Character(String n, String q, int h, int l,int g){
		name = n;
		quip = q;
		level = l;
		hp = h;
		gold = g;
	}
	
	public abstract void attack(Character c);
	
	public String getName(){
		return name;
	}
	
	public String getQuip(){
		return quip;
	}
	
	public int getHp(){
		return hp;
	}
	
	public int getGold(){
		return gold;
	}
	
	public void increaseLevel(){
		level++;
	}
	
	public void heal(int h){
		hp += h;
	}
	
	public void takeDamage(int h){
		hp -= h;
	}
	
	public void collectGold(int g){
		gold += g;
	}
	
	public void display(){
		
	}
}
