package Assignment6;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Assignment3.Computer;

public class Frame extends JFrame implements ActionListener{

	int choice  = 1, p_player = 0,p_computer = 0;
	
	public Frame(PrintStream out,BufferedReader in){
		
		Object[] options = {"Novice",
                "Expert"};
		int option = JOptionPane.showOptionDialog(new JFrame(),
			    "Would you like some green eggs to go "
			    + "with that ham?",
			    "A Silly Question",
			    JOptionPane.YES_NO_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[1]);
		out.println("Difficulty");
		if(option == 1)
			out.println(1);
		else
			out.println(0);
		out.flush();
		
		this.setLayout(new BorderLayout());
		OptionButtons left = new OptionButtons(out,this,in);
		//OptionButtons right = new OptionButtons();
		add(left,BorderLayout.PAGE_END);
		//add(right,BorderLayout.LINE_END);
		
		setBounds(50, 50, 500, 500);
		setTitle("RPS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int getInput(){
		return 1;
	}
	
	public void display(BufferedReader in){
		
	}

}

class OptionButtons extends JPanel{
	
	JButton rock, paper, scissor;
	public OptionButtons(final PrintStream out,final Frame f,final BufferedReader in){
		//this.setBackground(Color.RED);
		rock = new JButton( new AbstractAction("Rock") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            out.println("r");
	            f.display(in);
	        }
	    });
		paper = new JButton( new AbstractAction("Paper") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("p");
	        	f.display(in);
	        }
	    });;
	    scissor = new JButton( new AbstractAction("Scissor") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("s");
	        	f.display(in);
	        }
	    });
		this.setLayout(new GridBagLayout());
		//rock.setPreferredSize(new Dimension(50,50));
		//paper.setPreferredSize(new Dimension(50,50));
		//scissor.setPreferredSize(new Dimension(50,50));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4,4,4,4);
		c.weightx = .5;
		c.weighty = .5;
		c.gridx = 0 ;
		c.gridy = 1;
	
		this.add(rock,c);
		c.gridx = 1 ;
		c.gridy = 1;
		this.add(paper,c);
		c.gridx = 2 ;
		c.gridy = 1;
		this.add(scissor,c);
		
	}
}
