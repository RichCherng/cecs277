package Assignment3;
/**
 * Main Class - Game Rock, Paper, Scissor
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		//Scanner to read in file
		Scanner scanner = new Scanner(System.in);
		//Initialize File
		File file = new File("computer.dat");
		//Initialize Computer
		Computer computer = new Computer();
		//Initialize String that will hold your move
		String pattern = "";
		//Initialize Counter for player/computer points and choice for user input
		int choice  = 1, p_player = 0,p_computer = 0;
		//Initialize array of all possible move
		char[] m = {'r','p','s'};
		//Initialize Game State
		boolean running = true;
		//Game choices for Veteran or Beginner
		System.out.println("Choose game mode\n1.Beginner\n2.Veteran\n");
		choice = checkInt(1,2);
		//if choice is veteran then load file, else do nothing.
		if(choice == 2){
			//Process of checking and reading file for existing data.
			if(file.exists()){
				try{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
					try{
						computer = (Computer) in.readObject();
					} catch(ClassNotFoundException e){
						e.printStackTrace();
					}
					in.close();
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		} 
		//Run the game until user quit
		while(running){
			System.out.print("Choose your Move:\n1.Rock\n2.Paper\n3.Scissor\n4.Quit\n");
			//Ask and check user's input
			choice = checkInt(1,4);
			//User choose quit, stop the game
			if(choice == 4){
				break;
			}
			//prediction from computer
			char predict = computer.makePrediction(pattern);
			//replace the 4th previous input and insert new input
			if(pattern.length() == 4)
				pattern = pattern.substring(1) + m[choice - 1];
			else
				pattern += m[choice - 1];
			System.out.println("You choose: " + m[choice - 1]);
			System.out.println("Computer choose: " + predict);
			//Check for result of the game
			switch (computer.compare(m[choice - 1], predict)){
			case 1:
				System.out.println("\nYou Win!\n");
				p_player++;
				break;
			case 0:
				System.out.println("\nYou Tie!\n");
				break;
			case -1:
				System.out.println("\nYou Lose!\n");
				p_computer++;
				break;
			}
			System.out.printf("Player Points: %d%nComputer Points: %d%n%n",p_player,p_computer);
			computer.storePattern(pattern);
		}
		
		//Write object Computer to file.
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(computer);
			out.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		//Print out the winner
		if(p_player > p_computer){
			System.out.println("You Won!");
		}else if (p_computer > p_player)
			System.out.println("You Lost!");
		else
			System.out.println("Tie!!");
	}
	/**
	 * Ask user for input and check if user is valid.
	 * @param low lowest possible input
	 * @param high highest possible input
	 * @return the valid input
	 */
	public static int checkInt(int low, int high){
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while(!valid){
			
			if(in.hasNextInt()){
				validNum = in.nextInt();
				if(validNum >= low && validNum <= high){
					valid = true;
				}else{
					System.out.print("Invalid- Retry: ");
				}
			}else {
				in.next();
				System.out.println("Invalid input- Retry: ");
			}
		}
		return validNum;
	}
	
}
