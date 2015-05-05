package Assignment6;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class player {
	public static void main(String args[]) {
		try {
			System.out.print("Waiting... ");
			Socket sock = new Socket("localhost", 1235);
			System.out.println("Connected");
			//NetThread t = new NetThread(sock, "Server");
			//t.start();
			Scanner in = new Scanner(System.in);
			PrintStream out = new PrintStream(sock.getOutputStream());
			Frame main = new Frame(out);
			/*while (true) {
				//String line = in.nextLine();
				out.println(main.getInput());
			}*/
		} catch (Exception e) {
			System.out.println(e );
		}
	}
}
