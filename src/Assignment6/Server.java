package Assignment6;

import java.net.*;
import java.io.*;

import Assignment3.Computer;

/**
 * Represent Server where the prediction happen
 * 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Server {
	/**
	 * Main that run Server
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		/**
		 * computer - store the patterns and calculate the prediction
		 */
		Computer computer = new Computer();
		/**
		 * File - read computer's binary from
		 */
		File file = new File("computer.dat");
		try {
			ServerSocket server = new ServerSocket(1235);
			System.out.print("Waiting... ");
			Socket sock = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			System.out.println("Connected");
			PrintStream out = new PrintStream(sock.getOutputStream());
			String pattern = "";
			// while loop continuously wait for input from BufferedReader and
			// make prediction
			while (true) {
				// Read input from buffer
				String read = in.readLine();
				// check for tpye of command
				if (read.equals("Difficulty")) {
					read = in.readLine();
					// Change the difficulty
					if (Integer.parseInt(read) == 1)
						System.out.println("Change difficulty");
					computer = difficulty(computer, file);
				} else {
					// computer make prediction
					char predict = computer.makePrediction(pattern);
					// replace the 4th previous input and insert new input
					if (pattern.length() == 4)
						pattern = pattern.substring(1) + read;
					else
						pattern += read;
					// Store Pattern in computer
					computer.storePattern(pattern);
					// Send prediction to client
					out.println(predict);
					System.out.println("Computer Predict: " + predict);
					// Check for result
					switch (computer.compare(read.charAt(0), predict)) {
					case 1:
						out.println("win");
						break;
					case 0:
						out.println("tie");
						break;
					case -1:
						out.println("lose");
						break;
					}
				}
				System.out.println("Player: " + read);

			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Saving file....");
			// Attempt to save the file
			try {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(file));
				out.writeObject(computer);
				out.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * If expert is selected, computer read in existing date from file
	 * 
	 * @param c
	 *            Computer Class
	 * @param file
	 *            file containing data
	 * @return the existing computer class from the file
	 */
	public static Computer difficulty(Computer c, File file) {

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
