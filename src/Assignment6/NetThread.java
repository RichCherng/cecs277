package Assignment6;

import java.net.*;
import java.io.*;

public class NetThread extends Thread {
	private Socket sock;
	private String source;

	public NetThread(Socket s, String sc) {
		sock = s;
		source = sc;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			while (true) {
				String line = in.readLine();
				System.out.println(source + ": " + line);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}