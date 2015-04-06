package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		boolean quit = false;
		System.out.println("Choose function");
		System.out.println("1.TaskList\n2.Jukebox\n");
		int numFunc = checkInt(1,2);
		switch(numFunc){
		case 1:
			break;
		case 2:
			Jukebox();
			break;
		}
	}
	
	public static void Jukebox(){
		Scanner reader = null;
		Heap<Song> heap = new Heap<Song>();
		try{
			reader = new Scanner(new File("playlist.txt"));
		} catch(Exception e){
			System.out.println("File doesn't exist!");
		}
		while(reader.hasNext()){
			String[] song = reader.nextLine().split(",");
			Song newSong = new Song(song[0],song[1],song[2],Integer.parseInt(song[3]));
			//System.out.println(newSong.getTitle());
			heap.add(newSong);
		}
		heap.printHeap();
	}

	public static int checkInt(int low, int high) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid- Retry: ");
				}
			} else {
				// clear buffer of junk input
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		return validNum;
	}
}
