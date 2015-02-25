package Assignment2;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Hero class - Represent a character the user play in game
 * @author Pongsathorn Cherngchaosil 012124071
 *
 */
public class Hero extends Character implements Serializable{
	//List of item, variable to store items
	ArrayList<Item> items;
	//point to represent location
	Point location;
	//Maximum items in inventory allowed
	int MAX_ITEMS = 5;
	//current level
	int level = 1;
	/**
	 * Constructor - create a Hero
	 * @param n name of hero
	 * @param q quip of hero
	 * @param start starting position of hero
	 */
	public Hero(String n, String q, Point start) {
		super(n, q, 25 , 1, 0);
		location = start;
		items = new ArrayList<Item>();
	}
	/**
	 * check for health potion in inventory
	 * @return true if there atleast one health potion
	 */
	public boolean checkForHealthPotion(){
		for(Item i:items){
			if(i.getName().equals("Health Potion"))
				return true;
		}
		return false;
	}
	/**
	 * return list of item in inventory
	 * @return arraylist of item
	 */
	public ArrayList<Item> getItems(){
		return items;
	}
	/**
	 * store item in list
	 * Also check if the inventory is full
	 * @param i item that want to be stored
	 * @return false if item cannot be stored, true if successfully stored the item
	 */
	public boolean pickUpItem(Item i){
		if(items.size() <= MAX_ITEMS){
			items.add(i);
			return true;
		}
		else
			return false;
	}
	/**
	 * Remove specific item from inventory
	 * @param i the item to be remove
	 */
	public void removeItem(Item i){
		items.remove(i);
	}
	/**
	 * Remove item by index
	 * @param index index of item in array
	 */
	public void removeItem(int index){
		items.remove(index);
	}
	/**
	 * return the current location of player
	 * @return location of player as Point
	 */
	public Point getLocation(){
		return location;
	}
	/**
	 * set location of the player
	 * @param p location to be set
	 */
	public void setLocation(Point p){
		location = p;
	}
	/**
	 * method to move north direction
	 * @param l current map
	 * @return the element of the next room, 'n' if invalid move
	 */
	public char goNorth(Level l){
		char temp;
		Point loc =  new Point((int)location.getX(),(int)location.getY()-1);
		if( (temp = l.getRoom(loc)) == 'n'){
			return temp;
		}
		else
		{
			setLocation(loc);
			return temp;
		}
	}
	/**
	 * method to move south direction
	 * @param l current map
	 * @return the element of the next room, 'n' if invalid move
	 */
	public char goSouth(Level l){
		char temp;
		Point loc =  new Point((int)location.getX(),(int)location.getY()+1);
		if( (temp = l.getRoom(loc)) == 'n'){
			return temp;
		}
		else
		{
			setLocation(loc);
			return temp;
		}
	}
	/**
	 * method to move east direction
	 * @param l current map
	 * @return the element of the next room, 'n' if invalid move
	 */
	public char gotEast(Level l){
		char temp;
		Point loc =  new Point((int)location.getX()+1,(int)location.getY());
		if( (temp = l.getRoom(loc)) == 'n'){
			return temp;
		}
		else
		{
			setLocation(loc);
			return temp;
		}
	}
	/**
	 * method to move west direction
	 * @param l current map
	 * @return the element of the next room, 'n' if invalid move
	 */
	public char goWest(Level l){
		char temp;
		Point loc =  new Point((int)location.getX()-1,(int)location.getY());
		if( (temp = l.getRoom(loc)) == 'n'){
			return temp;
		}
		else
		{
			setLocation(loc);
			return temp;
		}
	}
	
	@Override
	public void attack(Character c) {
		Random rd = new Random();
		int damage = rd.nextInt(15 * getLevel());
		System.out.printf("%s hit a %s for %d damage.%n",getName(),c.getName(),damage);
		c.takeDamage(damage);
	}
	
}
