package Assignment6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Frame extends JFrame implements ActionListener{

	int choice  = 1, p_player = 0,p_computer = 0;
	
	
	public Frame(PrintStream out){
		
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
		OptionButtons control = new OptionButtons(out);
		//OptionButtons right = new OptionButtons();
		Display display = new Display();
		
		
		add(display,BorderLayout.CENTER);
		add(control,BorderLayout.SOUTH);
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
	
	public void display(String result){
		if(result.equals("win")){
			p_player++;
		}else if(result.equals("lose")){
			p_computer++;
		}
		
	}
	
	public void displayMove(String result){
		/*if(result.equals("win")){
			p_player++;
		}else if(result.equals("lose")){
			p_computer++;
		}*/     
		
	}

}


class Display extends JPanel{
	
	public Display(){
		this.setBackground(Color.GRAY);
		JLabel textBox = new JLabel("",SwingConstants.CENTER);
		textBox.setFont(new Font("Verdana",1,20));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(150,50));
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(150,150));
		left.setBackground(Color.BLACK);
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(150,150));
		right.setBackground(Color.BLACK);

		this.setLayout(new FlowLayout());
		this.add(left);
		this.add(textBox);
		this.add(right);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
