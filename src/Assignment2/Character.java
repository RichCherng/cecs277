package Assignment2;

import java.io.Serializable;

public abstract class Character implements Serializable{
	private String name,quip;
	private int level, hp, gold, baseHp;
	
	public Character(String n, String q, int h, int l,int g){
		name = n;
		quip = q;
		level = l;
		hp = h;
		baseHp = h;
		gold = g;
	}
	
	public abstract void attack(Character c);
	
	public String getName(){
		return name;
	}
	
	public void setLevel(int le){
		level = le;
	}
	
	public int getLevel(){
		return level;
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
		if((hp += h) >= baseHp){
			hp = baseHp;
		}
		else
			hp += h;
	}
	
	public void takeDamage(int h){
		if(hp - h < 0){
			hp = 0;
		} else
			hp -= h;
	}
	
	public void collectGold(int g){
		gold += g;
	}
	
	public void display(){
		System.out.printf("%s has %d health.", getName(),getHp());
	}
}
