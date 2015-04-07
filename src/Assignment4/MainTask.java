package Assignment4;

import java.io.File;
import java.util.Scanner;

public class MainTask {

	public static void main(String[] args) {
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
				if (heap.isEmpty()) {
					System.out.println("No more task!");
				} else {
					heap.printHeap();
				}
				break;
			case 2:
				if (heap.isEmpty()) {
					System.out.println("No more task!");
				} else {
					System.out.println(heap.peek().toString());
				}
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
				if (heap.isEmpty()) {
					System.out.println("No more task!");
				} else {
					System.out.println(heap.remove());
					// makeList(heap,list);
					heap.sort();
				}

				break;
			case 5:
				if (heap.isEmpty()) {
					System.out.println("No more task!");
				} else {
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
						Job task = new Job(s.getName(), month, day, year, hr,
								min);
						heap.add(task);
					} catch (Exception e) {
						System.out.println("Invalid Input");
					}

					heap.sort();
				}
				break;
			case 6:
				quit = true;
				break;
			}
		}
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
