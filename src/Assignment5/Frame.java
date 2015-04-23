package Assignment5;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Frame class responsible for main and JFrame
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Frame extends JFrame {
	/**
	 * Creating JFrame
	 * @param diff - level of difficulty
	 */
	public Frame  (int diff){
		this.setTitle("Tank");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Dimension of the frame
		int fX = 80;
		int fY = 80;
		Panel p = new Panel(fX,fY,diff);
		p.addKeyListener(p);
		this.add(p);
		
		this.setBounds(100, 100, p.Dim_X*fX, p.Dim_Y*fY);
		this.setResizable(false);
		this.setVisible(true);
	
	}
	/**
	 * Main that run the game
	 * @param args
	 */
	public static void main(String[] args){
		JFrame diff = new JFrame();
		String difficulty = "0";
		//Dialog box asking for level of difficulty
		difficulty = JOptionPane.showInputDialog(diff, "Pick Difficulty?");
		new Frame(Integer.parseInt(difficulty));
		
	}
}