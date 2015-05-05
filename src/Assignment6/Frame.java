package Assignment6;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{

	public Frame(PrintStream out){
		this.setLayout(new BorderLayout());
		OptionButtons left = new OptionButtons(out);
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

}

class OptionButtons extends JPanel{
	
	JButton rock, paper, scissor;
	public OptionButtons(final PrintStream out){
		//this.setBackground(Color.RED);
		rock = new JButton( new AbstractAction("Rock") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            out.println("r");
	        }
	    });
		paper = new JButton( new AbstractAction("Paper") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("p");
	        }
	    });;
	    scissor = new JButton( new AbstractAction("Scissor") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("s");
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
