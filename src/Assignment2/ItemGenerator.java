package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * ItemGenerator class - representing a class that create and store item
 * @author Pongsathornn Cherngchaosil
 *
 */
public class ItemGenerator {
	//List of item from the file
	ArrayList<Item> itemList;
	/**
	 * Constructor create an Item generator
	 */
	public ItemGenerator(){
		itemList = new ArrayList<Item>();
		Scanner reader;
		//Read in items from the file
		try {
			reader = new Scanner(new File("ItemList.txt"));
			while(reader.hasNext()){
				String[] item = reader.nextLine().split(",");
				itemList.add(new Item(item[0].trim(),Integer.parseInt(item[1])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * pick a random item 
	 * @return return that item
	 */
	public Item generator(){
		Random rd = new Random();
		int ran = rd.nextInt(itemList.size());
		return itemList.get(ran);
	}
}
