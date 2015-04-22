package Assignment5;


import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Frame extends JFrame {
	
	public Frame  (int diff){
		this.setTitle("Tank");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int fX = 80;
		int fY = 80;
		Panel p = new Panel(fX,fY,diff);
		//p.setResizable(false);
		//frame.setLayout(new GridBagLayout());
		p.addKeyListener(p);
		this.add(p);
		
		this.setBounds(100, 100, p.Dim_X*fX, p.Dim_Y*fY);
		//p.adjustBounds(this.getWidth(),this.getHeight());
		//frame.setBackground(Color.GRAY);
		//this.add(new Tank(new Point(10,10),Color.GREEN,1));
		this.setResizable(false);
		this.setVisible(true);
		
		/*Panel p = new Panel();
		p.setResizable(false);
		p.setVisible(true);
		*/	
	}
	
	public static void main(String[] args){
		JFrame diff = new JFrame();
		String difficulty = "0";
		difficulty = JOptionPane.showInputDialog(diff, "Pick Difficulty?");
		new Frame(Integer.parseInt(difficulty));
		
	}
}