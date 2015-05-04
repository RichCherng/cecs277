package Assignment6;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(1235);
			System.out.print("Waiting... ");
			Socket sock = server.accept();
			NetThread t = new NetThread(sock, "Client");
			t.start();
			System.out.println("Connected");
			Scanner in = new Scanner(System.in);
			PrintStream out = new PrintStream(sock.getOutputStream());

			while (true) {
				String line = in.nextLine();
				out.println(line);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
