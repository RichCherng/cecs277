package Assignment6;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{

	public Frame(){
		this.setLayout(new BorderLayout());
		OptionButtons left = new OptionButtons();
		//OptionButtons right = new OptionButtons();
		add(left,BorderLayout.LINE_START);
		//add(right,BorderLayout.LINE_END);
		setBounds(50, 50, 800, 800);
		setTitle("RPS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

class OptionButtons extends JPanel{
	
	Button rock, paper, scissor;
	public OptionButtons(){
		
		rock = new Button("Rock");
		paper = new Button("Paper");
		scissor = new Button("Scissor");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(rock);
		this.add(paper);
		this.add(scissor);
		
	}
}
