package Assignment4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainJuke {

	public static void main(String[] args) {
		Scanner reader = null;
		Heap<Song> heap = new Heap<Song>();
		try {
			reader = new Scanner(new File("playlist.txt"));
		} catch (Exception e) {
			System.out.println("File doesn't exist!");
		}
		while (reader.hasNext()) {
			String[] song = reader.nextLine().split(",");
			Song newSong = new Song(song[0], song[1], song[2],
					Integer.parseInt(song[3]));
			// System.out.println(newSong.getTitle());
			heap.add(newSong);
		}
		// heap.printHeap();
		// 2
		heap.sort();

		boolean quit = false;
		while (!quit) {
			System.out
					.println("1. Display the list of songs\n"
							+ "2. Display current song.\n"
							+ "3. Add a new song to the list\n"
							+ "4. Play next song – removes song from list, displays new current song\n"
							+ "5. Re-rate next song – prompts user for new rating, remove and re-add to list\n"
							+ "6. Quit");
			int choice = checkInt(1, 6);
			Scanner read = new Scanner(System.in);
			// ArrayList<Song> list = new ArrayList<Song>();
			// makeList(heap,list);
			switch (choice) {
			case 1:
				/*
				 * for(Song i:list){ System.out.println(i); }
				 */
				// printList(heap,list);
				heap.printHeap();
				break;
			case 2:
				if (heap.isEmpty()) {
					System.out.println("No more song!");
				} else {
					System.out.println(heap.peek().toString());
				}
				break;
			case 3:
				try {
					String[] song = read.nextLine().split(",");
					Song newSong = new Song(song[0], song[1], song[2],
							Integer.parseInt(song[3]));
					heap.add(newSong);
					// makeList(heap,list);
					heap.sort();
				} catch (Exception e) {
					System.out.println("Invalid Input");
				}
				break;
			case 4:
				if (heap.isEmpty()) {
					System.out.println("No more song!");
				} else {
					System.out.println(heap.remove());
					// makeList(heap,list);
					heap.sort();

				}

				break;
			case 5:
				if (heap.isEmpty()) {
					System.out.println("No more song!");
				} else {

					System.out.print("New Rating for\n");
					Song s = heap.remove();
					System.out.println(s);
					int newRating = checkInt(1, 5);
					heap.add(new Song(s.getTitle(), s.getArtist(),
							s.getAlbum(), newRating));
					heap.sort();
				}
				// makeList(heap,list);
				break;
			case 6:
				quit = true;
				break;
			}
		}
	}

	/*
	 * public static <T> void makeList(Heap<?> heap,ArrayList<T> list){ Heap t =
	 * new Heap(); list.clear(); for(int i=0; i < heap.getSize(); i++){ T temp =
	 * (T)heap.remove(); list.add(temp); t.add((Comparable) temp); } heap = t; }
	 */

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
