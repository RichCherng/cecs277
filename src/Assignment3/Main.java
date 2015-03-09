package Assignment3;

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
		Scanner scanner = new Scanner(System.in);
		File file = new File("computer.dat");
		Computer computer = new Computer();
		String pattern = "";
		int choice  = 1;
		char[] m = {'r','p','s'};
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
		
		/*while(pattern.length() < 4 && running){
			//play first 5 round first
			System.out.print("Choose your Move:\n1.Rock\n2.Paper\n3.Scissor\n4.Quit\n");
			choice = checkInt(1,4);
			if(choice == 4){
				running = false;
				break;
			}
			pattern += m[choice - 1];
			System.out.println("Computer choose: " + computer.randPick());
		}
		*/
		
		
		//System.out.println(pattern);
		computer.add(new Pattern(pattern));
		while(running){
			System.out.print("Choose your Move:\n1.Rock\n2.Paper\n3.Scissor\n4.Quit\n");
			choice = checkInt(1,4);
			if(choice == 4){
				break;
			}
			char predict = computer.prediction(pattern);
			if(pattern.length() == 4)
				pattern = pattern.substring(1) + m[choice - 1];
			else
				pattern += m[choice - 1];
			System.out.println("Computer choose: " + predict);
			computer.add(new Pattern(pattern));
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(computer);
			out.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			
		}
	}
	
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
