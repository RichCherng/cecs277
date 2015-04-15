package Assignment5;

import java.awt.Color;
import java.awt.FlowLayout;


import javax.swing.JPanel;

public class Panel extends JPanel {
	
	public Panel(){
		setLayout(new FlowLayout(FlowLayout.CENTER));
		//setBounds(100, 100, 1000, 500);
		setBackground(Color.DARK_GRAY);
	}
}
