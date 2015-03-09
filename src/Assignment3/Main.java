package Assignment3;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Choose R - Rock\nChoose P - Paper\nChoose S - Scissor\n");
		Computer computer = new Computer();
		String pattern = "";
		while(pattern.length() < 4){
			//play first 5 round first
			System.out.print("Choose your Move:");
			pattern += scanner.nextLine();
		}
		System.out.println(pattern);
		computer.add(new Pattern(pattern));
		while(true){
			System.out.print("Choose your Move:");
			char predict = computer.prediction(pattern);
			pattern = pattern.substring(1) + scanner.nextLine();
			System.out.println("Computer choose: " + predict);
			computer.add(new Pattern(pattern));
		}
	}
}
