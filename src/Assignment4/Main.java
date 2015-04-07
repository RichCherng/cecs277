package Assignment4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		boolean quit = false;
		System.out.println("Choose function");
		System.out.println("1.TaskList\n2.Jukebox");
		int numFunc = checkInt(1, 2);
		switch (numFunc) {
		case 1:
			TaskList();
			break;
		case 2:
			Jukebox();
			break;
		}
	}

	public static void TaskList() {
		Scanner reader = null;
		Heap<Job> heap = new Heap<Job>();
		try {
			reader = new Scanner(new File("taskList.txt"));
		} catch (Exception e) {
			System.out.println("File doesn't exist!");
		}
		while (reader.hasNext()) {
			String[] job = reader.nextLine().split(",");
			String[] dueDate = job[1].split("\\s");
			String[] date = dueDate[1].split("/");
			int month = Integer.parseInt(date[0]);
			int day = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			String[] time = dueDate[2].split(":");
			int hr = Integer.parseInt(time[0]);
			int min = Integer.parseInt(time[1]);
			Job task = new Job(job[0], month, day, year, hr, min);
			// System.out.println(newSong.getTitle());
			heap.add(task);
		}
		// heap.printHeap();
		// 2
		heap.sort();

		boolean quit = false;
		while (!quit) {
			System.out.println("1. Display the list of tasks\n"
					+ "2. Display current task.\n"
					+ "3. Add a new item to the task list\n"
					+ "4. Mark complete\n" + "5. Postpone next task\n"
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
				System.out.println(heap.peek().toString());
				break;
			case 3:
				try {
					String[] job = read.nextLine().split(",");
					String[] dueDate = job[1].split("\\s");
					String[] date = dueDate[1].split("/");
					int month = Integer.parseInt(date[0]);
					int day = Integer.parseInt(date[1]);
					int year = Integer.parseInt(date[2]);
					String[] time = dueDate[2].split(":");
					int hr = Integer.parseInt(time[0]);
					int min = Integer.parseInt(time[1]);
					Job task = new Job(job[0], month, day, year, hr, min);
					// System.out.println(newSong.getTitle());
					heap.add(task);
					heap.sort();
				} catch (Exception e) {
					System.out.println("Invalid input");
				}
				break;
			case 4:
				System.out.println(heap.remove());
				// makeList(heap,list);
				heap.sort();
				break;
			case 5:
				System.out.print("New date for\n");
				Job s = heap.remove();
				System.out.println(s);
				try {
					String[] dueDate = read.nextLine().split("\\s");
					String[] date = dueDate[0].split("/");
					int month = Integer.parseInt(date[0]);
					int day = Integer.parseInt(date[1]);
					int year = Integer.parseInt(date[2]);
					String[] time = dueDate[1].split(":");
					int hr = Integer.parseInt(time[0]);
					int min = Integer.parseInt(time[1]);
					Job task = new Job(s.getName(), month, day, year, hr, min);
					heap.add(task);
				} catch (Exception e) {
					System.out.println("Invalid Input");
				}

				heap.sort();
				break;
			case 6:
				quit = true;
				break;
			}
		}
	}

	public static void Jukebox() {
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
				System.out.println(heap.peek().toString());
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
				System.out.println(heap.remove());
				// makeList(heap,list);
				heap.sort();
				break;
			case 5:
				System.out.print("New Rating for\n");
				Song s = heap.remove();
				System.out.println(s);
				int newRating = checkInt(1, 5);
				heap.add(new Song(s.getTitle(), s.getArtist(), s.getAlbum(),
						newRating));
				heap.sort();
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
