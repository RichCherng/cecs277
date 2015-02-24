package Assignment2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Hero extends Character{
	
	ArrayList<Item> items;
	Point location;
	int MAX_ITEMS = 5;
	int level = 1;
	
	public Hero(String n, String q, Point start) {
		super(n, q, 10 , 1, 0);
		location = start;
		items = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public boolean pickUpItem(Item i){
		if(items.size() < MAX_ITEMS){
			items.add(i);
			return true;
		}
		else
			return false;
	}
	
	public void removeItem(Item i){
		items.remove(i);
	}
	
	public void removeItem(int index){
		items.remove(index);
	}
	
	public Point getLocation(){
		return location;
	}
	
	public void setLocation(Point p){
		location = p;
	}
	
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
