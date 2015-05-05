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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			//NetThread t = new NetThread(sock, "Client");
			//t.start();
			System.out.println("Connected");
			PrintStream out = new PrintStream(sock.getOutputStream());

			while (true) {
				//String line = in.nextLine();
				String read = in.readLine();
				out.println(read);
				System.out.println(read);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
