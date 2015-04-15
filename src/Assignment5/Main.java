package Assignment5;

import java.awt.Color;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Tank");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel p = new Panel();
		//p.setResizable(false);
		
		frame.add(p);
		frame.setBounds(100, 100, 1000, 1000);
		frame.setVisible(true);
		/*Panel p = new Panel();
		p.setResizable(false);
		p.setVisible(true);
		*/
		
	}
}