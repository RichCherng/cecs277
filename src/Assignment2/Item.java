package Assignment2;

public class Item {
	private String name;
	private int goldValue;
	
	public Item(String n, int v){
		name = n;
		goldValue = v;
	}
	
	public int getValue(){
		return goldValue;
	}
}
