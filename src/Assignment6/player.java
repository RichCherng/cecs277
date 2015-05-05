package Assignment6;

import java.net.*;
import java.io.*;

public class player {
	public static void main(String args[]) {
		try {
			System.out.print("Waiting... ");
			Socket sock = new Socket("localhost", 1235);
			System.out.println("Connected");
			//NetThread t = new NetThread(sock, "Server");
			//t.start();
			//Scanner in = new Scanner(System.in);
			PrintStream out = new PrintStream(sock.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			Frame frame = new Frame(out);
			
			while (true) {
				String read = in.readLine();
				//out.println(read);
				/*if(read.equals("win")){
					
				} else if(read.equals("tie")){
					
				} else if(read.equals("lose")){
					
				}*/
				
				if(read.length() >= 3){
					frame.display(read);
				} else if (read.length() == 1){
					frame.displayMove(read);
				}
				System.out.println("Computer: " + read);
			}
		} catch (Exception e) {
			System.out.println(e );
		}
	}
}
