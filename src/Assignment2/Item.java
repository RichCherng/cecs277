package Assignment2;

import java.io.Serializable;
/**
 * Item Class - representing item 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Item implements Serializable {
	//name of item
	private String name;
	//value of that item
	private int goldValue;
	/**
	 * Constructor - create an item
	 * @param n name of the item
	 * @param v value of the item
	 */
	public Item(String n, int v){
		name = n;
		goldValue = v;
	}
	/**
	 * return value of that item
	 * @return value of that item
	 */
	public int getValue(){
		return goldValue;
	}
	/**
	 * return name of the item 
	 * @return name of the item
	 */
	public String getName(){
		return name;
	}
}
