package Assignment2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ItemGenerator {
	ArrayList<Item> itemList;
	
	public ItemGenerator(){
		
		itemList = new ArrayList<Item>();
		Scanner reader = new Scanner("ItemList.txt");
		while(reader.hasNext()){
			String[] item = reader.nextLine().split(",");
			itemList.add(new Item(item[0],Integer.parseInt(item[1])));
		}
	}
	
	public Item generator(){
		Random rd = new Random();
		int ran = rd.nextInt(itemList.size());
		return itemList.get(ran);
	}
}
