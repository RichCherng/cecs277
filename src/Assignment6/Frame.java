package Assignment6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

/**
 * Frame responsible from display content
 * 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Frame extends JFrame implements ActionListener {
	/**
	 * p_player: player's score p_computer: computer's score
	 */
	int p_player = 0, p_computer = 0;
	/**
	 * leftImage & rightImage: list of the name of the image file
	 */
	String[] leftImage = { "l_rock.jpg", "l_paper.jpg", "l_scissors.jpg" };
	String[] rightImage = { "r_rock.jpg", "r_paper.jpg", "r_scissors.jpg" };
	/**
	 * display: display picture and score on the screen
	 */
	Display display;
	/**
	 * img: variable to store image
	 */
	Image img;
	/**
	 * Frame create JFrame and display all of the component in it.
	 * @param out PrintStream for sending information to server
	 */
	public Frame(PrintStream out) {
		//List of options for playr
		Object[] options = { "Novice", "Expert" };
		//prompt window asking player to choose difficulty
		int option = JOptionPane.showOptionDialog(new JFrame(),
				"Pick Difficulty", "A Silly Question",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[1]);
		out.println("Difficulty");
		//Send difficulty for the servers
		if (option == 1)
			out.println(1);
		else
			out.println(0);
		out.flush();
		this.setLayout(new BorderLayout());
		//Text box displaying Title
		JLabel textBox = new JLabel("Networked Rock Paper Scissors",
				SwingConstants.CENTER);
		textBox.setFont(new Font("Verdana", 1, 16));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(500, 50));
		//Panel containning Text Box 
		JPanel title = new JPanel();
		title.add(textBox);
		//Initialize display
		display = new Display();
		//OptionButtons class display buttons
		OptionButtons control = new OptionButtons(out, leftImage, display);
		//add all of the component to the JFrame
		add(title, BorderLayout.NORTH);
		add(display, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		//Panel Configuration
		setBounds(50, 50, 500, 500);
		setTitle("RPS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	/**
	 * Display result to the text box on JPanel
	 * @param result Result from Rock Paper Scissors game
	 */
	public void display(String result) {
		//Check for type of result
		if (result.equals("win")) {
			display.setText("Win!");
			//Increment player's score
			display.addPlayerScore();
		} else if (result.equals("lose")) {
			display.setText("Lose!");
			//Increment computer's score
			display.addComputerScore();
		} else if (result.equals("tie")) {
			display.setText("Tie!");
		}

	}

	/**
	 * display Picture of Computer's choices
	 * @param result Computer's Prediction
	 */
	public void displayComMove(String result) {
		//Check for which prediction and read the correct image
		if (result.equals("r")) {
			try {
				img = ImageIO.read(new File(rightImage[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.equals("s")) {
			try {
				img = ImageIO.read(new File(rightImage[2]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.equals("p")) {
			try {
				img = ImageIO.read(new File(rightImage[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Display the Computer's prediction to the right side of the screen
		display.RightDisplay(img);
	}

}

/**
 * Display class: display content including both player and computer
 * 					pictures and both scores.
 */
class Display extends JPanel {
	/**
	 * textBox: show the result of the game
	 * left: display player's choice image
	 * right: display computer's prediction image
	 * scorePlayer: display player's score
	 * scoreComputer: display computer's score
	 */
	JLabel textBox, left, right, playerTag, comTag, scorePlayer, scoreComputer;
	/**
	 * p_player: score of player
	 * p_computer: score of computer
	 */
	int p_player = 0;
	int p_computer = 0;;

	/**
	 * Constructor of Display: display image and score in panel
	 */
	public Display() {
		this.setBackground(Color.GRAY);
		//JLabel responsible for displaying score of both player and computer
		scorePlayer = new JLabel(p_player + "", SwingConstants.CENTER);
		scoreComputer = new JLabel(p_computer + "", SwingConstants.CENTER);
		//Display the label representing player
		playerTag = new JLabel("player", SwingConstants.CENTER);
		playerTag.setFont(new Font("Verdana", 1, 16));
		playerTag.setPreferredSize(new Dimension(150, 50));
		//Display the label representing player
		comTag = new JLabel("Computer", SwingConstants.CENTER);
		comTag.setFont(new Font("Verdana", 1, 16));
		comTag.setPreferredSize(new Dimension(150, 50));
		//Display the result of the game
		textBox = new JLabel("", SwingConstants.CENTER);
		textBox.setFont(new Font("Verdana", 1, 20));
		textBox.setBackground(Color.BLACK);
		textBox.setPreferredSize(new Dimension(150, 50));
		//Display image on the left side, representing player
		left = new JLabel();
		left.setPreferredSize(new Dimension(150, 92));
		left.setBackground(Color.BLACK);
		//Display image on the right side, representing computer
		right = new JLabel();
		right.setPreferredSize(new Dimension(150, 92));
		right.setBackground(Color.BLACK);

		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		//add and arrange components into the correct position
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.weighty = .5;
		this.add(left, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(textBox, c);
		c.gridx = 2;
		c.gridy = 1;
		this.add(right, c);
		c.gridx = 0;
		c.gridy = 0;
		this.add(playerTag, c);
		c.gridx = 2;
		c.gridy = 0;
		this.add(comTag, c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(scorePlayer, c);
		c.gridx = 2;
		c.gridy = 2;
		this.add(scoreComputer, c);
	}
	/**
	 * Update the result's display
	 * @param text result of the game
	 */
	public void setText(String text) {
		textBox.setText(text);
	}
	/**
	 * Display the image on the right side of the screen
	 * @param img Image of choice
	 */
	public void RightDisplay(Image img) {
		right.setIcon(new ImageIcon(img));
	}
	/**
	 * Display the image on the left side of the screen
	 * @param img Image of choice
	 */
	public void LeftDisplay(Image img) {
		left.setIcon(new ImageIcon(img));
	}
	/**
	 * Increment player's score
	 */
	public void addPlayerScore() {
		scorePlayer.setText((p_player++) + "");

	}
	/**
	 * Increment computer's score
	 */
	public void addComputerScore() {
		scoreComputer.setText((p_computer++) + "");
	}
}
/**
 * OptionButtons: create and control buttons in JFrame 
 */
class OptionButtons extends JPanel {
	/**
	 * rock: option to pick rock
	 * paper: option to pick paper
	 * scissor: option to pick scissor
	 */
	private JButton rock, paper, scissor;
	/**
	 * optImg: list of image of buttons
	 */
	private String[] optImg = { "h_rock.jpg", "h_paper.jpg", "h_scissors.jpg" };
	/**
	 * Constructor for OptionButtons, creat buttons and set their location
	 * @param out Printstream for sending information to server
	 * @param imgFile List of Images that display on the right side of the screen
	 * @param display	Panel the display the content
	 */
	public OptionButtons(final PrintStream out, final String[] imgFile,
			final Display display) {
		//Construct buttons and add their specific fuction to each buttons
		//Send the player's choice to the server 
		//Display the image of player's choice
		rock = new JButton(new AbstractAction("Rock") {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("r");
				try {
					display.LeftDisplay(ImageIO.read(new File(imgFile[0])));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		paper = new JButton(new AbstractAction("Paper") {
			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("p");
				try {
					display.LeftDisplay(ImageIO.read(new File(imgFile[1])));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		;
		scissor = new JButton(new AbstractAction("Scissor") {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		//Set the dimension of buttons
		rock.setPreferredSize(new Dimension(90, 100));
		paper.setPreferredSize(new Dimension(90, 100));
		scissor.setPreferredSize(new Dimension(90, 100));
		//set buttons to display the image that they're representing
		try {
			rock.setIcon(new ImageIcon(ImageIO.read(new File(optImg[0]))));
			paper.setIcon(new ImageIcon(ImageIO.read(new File(optImg[1]))));
			scissor.setIcon(new ImageIcon(ImageIO.read(new File(optImg[2]))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4, 4, 4, 4);
		c.weightx = .5;
		c.weighty = .5;
		c.gridx = 0;
		c.gridy = 1;

		this.add(rock, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(paper, c);
		c.gridx = 2;
		c.gridy = 1;
		this.add(scissor, c);

	}
}
