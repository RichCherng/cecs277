package Assignment6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Frame extends JFrame implements ActionListener{

	int choice  = 1, p_player = 0,p_computer = 0;
	String[] leftImage = {"l_rock.jpg","l_paper.jpg","l_scissors.jpg"};
	String[] rightImage = {"r_rock.jpg","r_paper.jpg","r_scissors.jpg"};
	Display display;
	Image img;
	
	
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
		
		JLabel textBox = new JLabel("Networked Rock Paper Scissors",SwingConstants.CENTER);
		textBox.setFont(new Font("Verdana",1,16));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(500,50));
		JPanel title = new JPanel();
		title.add(textBox);
		
		display = new Display();
		OptionButtons control = new OptionButtons(out,leftImage,display);
		//OptionButtons right = new OptionButtons();
		
		
		add(title,BorderLayout.NORTH);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*if(e == OptionButtons)
		try {
			img = ImageIO.read(new File(rightImage[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		display.LeftDisplay(img);*/
	}
	
	public int getInput(){
		return 1;
	}
	
	public void display(String result){
		if(result.equals("win")){
			display.setText("Win!");
			p_player++;
		}else if(result.equals("lose")){
			display.setText("Lose!");
			p_computer++;
		} else if(result.equals("tie")){
			display.setText("Tie!");
		}
		
	}
	
	public void displayComMove(String result){
		if(result.equals("r")){
			try {
				img = ImageIO.read(new File(rightImage[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(result.equals("s")){
			try {
				img = ImageIO.read(new File(rightImage[2]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(result.equals("p")){
			try {
				img = ImageIO.read(new File(rightImage[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		display.RightDisplay(img);
	}
	
	

}


class Display extends JPanel{
	
	JLabel textBox;
	JLabel left,right;
	public Display(){
		this.setBackground(Color.GRAY);
		textBox = new JLabel("",SwingConstants.CENTER);
		textBox.setFont(new Font("Verdana",1,20));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(150,50));
		left = new JLabel();
		left.setPreferredSize(new Dimension(150,92));
		left.setBackground(Color.BLACK);
		right = new JLabel();
		right.setPreferredSize(new Dimension(150,92));
		right.setBackground(Color.BLACK);

		this.setLayout(new FlowLayout());
		this.add(left);
		this.add(textBox);
		this.add(right);
		//this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	public void setText(String text){
		textBox.setText(text);
	}
	
	public void RightDisplay(Image img){
		right.setIcon(new ImageIcon(img));
	}
	
	public void LeftDisplay(Image img){
		left.setIcon(new ImageIcon(img));
	}
	/*class Label extends JPanel{
		public Label(){
			this.setBackground(Color.RED);
		}
		JLabel textBox = new JLabel("",SwingConstants.CENTER);
		this.get`
		textBox.setFont(new Font("Verdana",1,20));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(150,50));
	}*/
}

class OptionButtons extends JPanel{
	
	JButton rock, paper, scissor;
	public OptionButtons(final PrintStream out,final String[] imgFile,final Display display){
		//this.setBackground(Color.RED);
		rock = new JButton( new AbstractAction("Rock") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            out.println("r");
	            try {
					display.LeftDisplay(ImageIO.read(new File(imgFile[0])));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        }
	    });
		paper = new JButton( new AbstractAction("Paper") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("p");
	        	 try {
						display.LeftDisplay(ImageIO.read(new File(imgFile[1])));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        }
	    });;
	    scissor = new JButton( new AbstractAction("Scissor") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	out.println("s");
	        	 try {
						display.LeftDisplay(ImageIO.read(new File(imgFile[2])));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
