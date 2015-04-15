package Assignment4;

import java.io.File;
import java.util.Scanner;

/**
 * MainTask - main of TaskList
 * 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class MainTask {

	public static void main(String[] args) {
		// Scanner to read in file.
		Scanner reader = null;
		// Construct heap to store Job class
		Heap<Job> heap = new Heap<Job>();
		// Try to read taskList.txt file
		try {
			reader = new Scanner(new File("taskList.txt"));
		} catch (Exception e) {
			System.out.println("File doesn't exist!");
		}
		// Read in input from the file
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
			heap.add(task);
		}
		// Sort heap after modification
		heap.sort();
		// check if user quit
		boolean quit = false;
		while (!quit) {
			// List options
			System.out.println("1. Display the list of tasks\n"
					+ "2. Display current task.\n"
					+ "3. Add a new item to the task list\n"
					+ "4. Mark complete\n" + "5. Postpone next task\n"
					+ "6. Quit");
			// check for valid input
			int choice = checkInt(1, 6);
			// Scanner to read console user's input
			Scanner read = new Scanner(System.in);
			switch (choice) {
			case 1:
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
				// read in new task, if fail do nothing
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

	/**
	 * Check for invalid input
	 * 
	 * @param low
	 *            minimum value
	 * @param high
	 *            maximum value
	 * @return the user input
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
