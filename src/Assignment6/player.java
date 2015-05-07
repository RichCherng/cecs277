package Assignment6;

import java.net.*;
import java.io.*;

/**
 * Player Class handle the communication with serer
 * 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Player {
	/**
	 * Main that run the game and communicate with server from client side
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			System.out.print("Waiting... ");
			Socket sock = new Socket("localhost", 1235);
			System.out.println("Connected");
			PrintStream out = new PrintStream(sock.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			Frame frame = new Frame(out);
			// Continuously read in from Buffer
			while (true) {
				String read = in.readLine();
				// If input received is result, display result
				if (read.length() >= 3) {
					frame.display(read);
				} else if (read.length() == 1) {
					// If input received is prediction, display the correct
					// picture
					frame.displayComMove(read);
				}
				System.out.println("Computer: " + read);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
