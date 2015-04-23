package Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Panel Class - representing panel in the frame and all the drawing and animation
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Panel extends JPanel implements KeyListener, Runnable,
		MouseMotionListener, MouseListener {
	/**
	 * Dim_X - Dimension of the map
	 * Dim_Y - Dimension of the map
	 * fx - Dimension of the frame
	 * fy - Dimension of the frame
	 */
	int Dim_X, Dim_Y,fx,fy;
	/** map of the game*/
	char[][] map;
	/** Tank that represent player*/
	Tank player;
	private Thread t;
	/**
	 * obs - list of obstacle, mainly walls
	 * tank - list of enemy tank
	 */
	ArrayList<Rectangle> obs = new ArrayList<Rectangle>();
	ArrayList<Tank> tank = new ArrayList<Tank>();
	/**
	 * Constructor for Panel
	 * 	Initialize all tank
	 * 	Read in map from file
	 * @param jx - Dimension of JFrame
	 * @param jy - Dimension of JFrame
	 * @param diff - level of difficulty
	 */
	public Panel(int jx, int jy, int diff) {
		super();
		fx = jx;
		fy = jy;
		// Scanner for reading in file
		Scanner read = null;
		try {
			read = new Scanner(new File("map1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input = read.nextLine();
		//Dimension of the map
		String[] dim = input.split("\\s+");
		read.nextLine();
		Dim_X = Integer.parseInt(dim[0]);
		Dim_Y = Integer.parseInt(dim[1]);
		map = new char[Dim_X][Dim_Y];
		//Initialize map and all obstacles and walls
		for (int x = 0; x < Dim_X; x++) {
			input = read.nextLine();
			dim = input.split("\\s+");
			for (int y = 0; y < Dim_Y; y++) {
				map[x][y] = (char) Integer.parseInt(dim[y]);
				if (map[x][y] == 1)
					obs.add(new Rectangle(y * fx, x * (Dim_Y * fy / Dim_Y), fx,
							fy));
			}
		}
		//Initialize thread
		t = new Thread(this);
		//Initialzie all the tank in the game
		player = new Tank(new Point(80, 80), Color.GREEN);
		tank.add(new Tank(new Point(910, 80), Color.PINK));
		tank.add(new Tank(new Point(80, 910), Color.BLUE));
		tank.add(new Tank(new Point(910, 910), Color.GRAY));
		//Depend on the level of difficulty, remove the unnesscary tank
		for (int i = 3 - diff; i > 0; i--) {
			if (!tank.isEmpty()) {
				tank.remove(0);
			}
		}
		
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		setFocusable(true);
		setBounds(0, 0, Dim_X * 80, Dim_Y * 80);
		//Start thread
		t.start();
	}
	/**
	 * Method responsible for drawing all the component on the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Draw the map
		g.setColor(Color.YELLOW);
		for (int row = 0; row < Dim_X; row++) {
			for (int col = 0; col < Dim_Y; col++) {
				if (map[row][col] == 1)
					g.fillRect(col * fx, row * (Dim_Y * fy / Dim_Y), fx, fy);
			}
		}
		//If player is alive then draw player's tank
		if (player.isAlive())
			player.draw(g);
		//Draw existing enemy tank
		for (int i = 0; i < tank.size(); i++) {
			tank.get(i).draw(g);
		}
	}

	/**
	 * move the plaer's tank according to the 
	 * keypressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		player.moveTank(key, fx, fy, obs);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Thread responsible for updating all the component 
	 * and animation
	 */
	@Override
	public void run() {
		Random rd = new Random();
		while (true) {
			//update all AI 
			for (int i = 0; i < tank.size(); i++) {
				//update Ai barrel
				tank.get(i).updateBarrel((int) player.p.getX(),
						(int) player.p.getY());
				int rand = rd.nextInt(15);
				//AI fire missile based on random chance
				if (rand == 5) {
					tank.get(i).fireMissile(tank.get(i).barrelX, tank.get(i).barrelY);
				}
				//AI move based on random chance
				if (rd.nextInt(15) == 10)
					tank.get(i).updateMove(fx, fy, obs);
				//check for Ai collision
				tank.get(i).testHit(player,tank,obs);
			}
			//check for player's collision
			player.testHit(player, tank,obs);
			//remove destroyed AI
			for (Iterator<Tank> iterator = tank.iterator(); iterator.hasNext();) {
				Tank t = iterator.next();
				if (t.remove) {
					iterator.remove();
				}
			}
			repaint();
			//stop when player is dead
			if (!player.inGame())
				break;
			try {
				Thread.sleep(50);// ~60 fps
			} catch (InterruptedException e) {

			}
		}
		JOptionPane.showMessageDialog(null, "You Lost");
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * constanly update barrel of the player 
	 * sdepend on the location of the player's mouse
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		player.updateBarrel(x, y);

	}
	
	/**
	 * Tell the player tank to fire in the direction of the mouse
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Missile shot = new Missile(player.p,player.unitX,player.unitY);
		int x = player.barrelX;// (int) (player.p.getX() + (player.DIM_Y / 2));
		int y = player.barrelY;// (int) (player.p.getY() + (player.DIM_Y / 2));
		//mis.add(new Missile(x, y, player.unitX, player.unitY));
		player.fireMissile(x,y);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
