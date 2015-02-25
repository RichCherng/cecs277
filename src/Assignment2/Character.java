package Assignment2;

import java.io.Serializable;
/**
 * Representation of a generic type of a character
 * @author Pongsathron
 *
 */
public abstract class Character implements Serializable{
	//name and quip of character
	private String name,quip;
	//current level of character
	//current hp of character
	//current gold of character
	//current maximum health of character
	private int level, hp, gold, baseHp;
	/**
	 * Constructor - creating a character
	 * @param n set name
	 * @param q set quip
	 * @param h set health
	 * @param l set level
	 * @param g set gold
	 */
	public Character(String n, String q, int h, int l,int g){
		name = n;
		quip = q;
		level = l;
		hp = h;
		baseHp = h;
		gold = g;
	}
	/**
	 * The generic function for attack other character
	 * @param c character receiving attack
	 */
	public abstract void attack(Character c);
	/**
	 * return name of character
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * Set level of character, also modify hp
	 * @param le the level to be set at
	 */
	public void setLevel(int le){
		baseHp = baseHp*le;
		hp = baseHp;
		level = le;
	}
	/**
	 * return current level of character
	 * @return level of character
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * return quip of character 
	 * @return quip of character
	 */
	public String getQuip(){
		return quip;
	}
	/**
	 * return hp of character 
	 * @return hp of character
	 */
	public int getHp(){
		return hp;
	}
	/**
	 * return gold of character
	 * @return gold of character
	 */
	public int getGold(){
		return gold;
	}
	/**
	 * increase level of character
	 */
	public void increaseLevel(){
		level++;
	}
	/**
	 * heal the character
	 * hp cannot be more than base hp
	 * @param h hp to be added to the heal
	 */
	public void heal(int h){
		if((hp += h) >= baseHp){
			hp = baseHp;
		}
		else
			hp += h;
	}
	/**
	 * reduce hp of character
	 * @param h amount of hp to be remove
	 */
	public void takeDamage(int h){
		if(hp - h < 0){
			hp = 0;
		} else
			hp -= h;
	}
	/**
	 * add gold to current amount
	 * @param g amount of gold
	 */
	public void collectGold(int g){
		gold += g;
	}
	/**
	 * display health of caracter
	 */
	public void display(){
		System.out.printf("%s has %d health.", getName(),getHp());
	}
}
