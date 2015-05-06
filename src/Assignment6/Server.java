package Assignment6;

import java.net.*;
import java.io.*;

import Assignment3.Computer;

public class Server {
	public static void main(String args[]) {
		Computer computer = new Computer();
		File file = new File("computer.dat");
		try {
			ServerSocket server = new ServerSocket(1235);
			System.out.print("Waiting... ");
			Socket sock = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			// NetThread t = new NetThread(sock, "Client");
			// t.start();
			System.out.println("Connected");
			PrintStream out = new PrintStream(sock.getOutputStream());

			
			
			String pattern = "";

			while (true) {
				// String line = in.nextLine();
				String read = in.readLine();
				if(read.equals("Difficulty")){
					read = in.readLine();
					if(Integer.parseInt(read) == 1)
						System.out.println("Change difficulty");
						computer = difficulty(computer,file);
				}else{
					char predict = computer.makePrediction(pattern);
					//replace the 4th previous input and insert new input
					if(pattern.length() == 4)
						pattern = pattern.substring(1) + read;
					else
						pattern += read;
					
					computer.storePattern(pattern);
					out.println(predict);
					System.out.println("Computer Predict: " + predict);
					
					switch (computer.compare(read.charAt(0), predict)){
					case 1:
						//System.out.println("\nYou Win!\n");
						//p_player++;
						out.println("win");
						break;
					case 0:
						//System.out.println("\nYou Tie!\n");
						out.println("tie");
						break;
					case -1:
						//System.out.println("\nYou Lose!\n");
						//p_computer++;
						out.println("lose");
						break;
					}
				}
				System.out.println("Player: " + read);
				
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Saving file....");
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(computer);
				out.close();
			} catch(FileNotFoundException e1){
				e1.printStackTrace();
			} catch (IOException e1){
				e1.printStackTrace();
			}
		}
	}

	public static Computer difficulty(Computer c,File file) {
		
		Computer computer = null;
		if (file.exists()) {
			try {
				ObjectInputStream obj = new ObjectInputStream(
						new FileInputStream(file));
				try {
					computer = (Computer) obj.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				obj.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return computer;
	}

}
