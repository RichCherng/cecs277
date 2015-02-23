package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ItemGenerator {
	ArrayList<Item> itemList;
	
	public ItemGenerator(){
		
		itemList = new ArrayList<Item>();
		Scanner reader;
		try {
			reader = new Scanner(new File("ItemList.txt"));
			while(reader.hasNext()){
				String[] item = reader.nextLine().split(",");
				itemList.add(new Item(item[0],Integer.parseInt(item[1])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Item generator(){
		Random rd = new Random();
		int ran = rd.nextInt(itemList.size());
		return itemList.get(ran);
	}
}
