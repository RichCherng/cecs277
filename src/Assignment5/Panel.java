package Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener,Runnable {

	int Dim_X, Dim_Y;
	/* dimension of JFrame */
	int fx, fy;
	char[][] map;
	Tank player;
	private Thread t;

	public Panel(int jx, int jy) {
		super();
		fx = jx;
		fy = jy;
		Scanner read = null;

		try {
			read = new Scanner(new File("map1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String input = read.nextLine();
		String[] dim = input.split("\\s+");
		read.nextLine();
		Dim_X = Integer.parseInt(dim[0]);
		Dim_Y = Integer.parseInt(dim[1]);
		map = new char[Dim_X][Dim_Y];
		for (int x = 0; x < Dim_X; x++) {
			input = read.nextLine();
			dim = input.split("\\s+");
			for (int y = 0; y < Dim_Y; y++) {
				map[x][y] = (char) Integer.parseInt(dim[y]);
			}
		}
		t = new Thread(this);
		player = new Tank(RandomSpawn(), Color.GREEN, 1);
		// setLayout(new GridBagLayout());
		// setLayout(new BorderLayout());
		this.addKeyListener(this);
		setFocusable(true);
		setBounds(0, 0, Dim_X * 80, Dim_Y * 80);
		t.start();
		// setSize(getXDim()*80,getYDim()*80);
		// setBackground(Color.DARK_GRAY);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawRect( 0, 0, 50, 50);
		g.setColor(Color.YELLOW);
		// g.fillRect(0, 0, 50, 50);
		// g.drawRect( 50, 50, 50, 50);
		for (int row = 0; row < Dim_X; row++) {
			for (int col = 0; col < Dim_Y; col++) {
				if (map[row][col] == 1)
					g.fillRect(col * (Dim_X * fx / Dim_X),
								row * (Dim_Y * fy / Dim_Y), fx, fy);
				
				
				System.out.print(map[row][col] + 0);
			}
			System.out.println();
			
		}
		System.out.println();
		player.draw(g);
	}

	public Point RandomSpawn() {

		Random rand = new Random();
		int randomX;
		int randomY;
		do {
			randomX = rand.nextInt(Dim_X);
			randomY = rand.nextInt(Dim_Y);
			
		} while (map[randomX][randomY] == 1);
		
		return new Point(randomX * (Dim_X * fy / Dim_X),randomY * (Dim_Y * fx / Dim_Y));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// System.out.println("move");
		player.moveTank(key,fx,fy);
		//repaint();
		System.out.println(player.p);
		// this.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while(true){
			repaint();
			try{
				Thread.sleep(16);//~60 fps
			} catch (InterruptedException e){
				
			}
		}
		
	}

	/*
	 * public void adjustBounds(int width, int height){ fx = width; fy = height;
	 * }
	 */
}
