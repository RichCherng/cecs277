package Assignment2;

import java.io.Serializable;

public class Item implements Serializable {
	private String name;
	private int goldValue;
	
	public Item(String n, int v){
		name = n;
		goldValue = v;
	}
	
	public int getValue(){
		return goldValue;
	}
	
	public String getName(){
		return name;
	}
}
