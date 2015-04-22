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

import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener, Runnable,
		MouseMotionListener, MouseListener {

	int Dim_X, Dim_Y;
	/* dimension of JFrame */
	int fx, fy;
	char[][] map;
	Tank player;
	private Thread t;
	ArrayList<Rectangle> obs = new ArrayList<Rectangle>();
	ArrayList<Missile> mis = new ArrayList<Missile>();
	ArrayList<Tank> tank = new ArrayList<Tank>();

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
				if (map[x][y] == 1)
					obs.add(new Rectangle(y * fx, x * (Dim_Y * fy / Dim_Y), fx,
							fy));
			}
		}
		t = new Thread(this);
		player = new Tank(new Point(80, 80), Color.GREEN);
		tank.add(new Tank(new Point(910, 80), Color.PINK));
		tank.add(new Tank(new Point(80, 910), Color.BLUE));
		tank.add(new Tank(new Point(910, 910), Color.GRAY));
		// setLayout(new GridBagLayout());
		// setLayout(new BorderLayout());
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
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
					g.fillRect(col * fx, row * (Dim_Y * fy / Dim_Y), fx, fy);

				// System.out.print(map[row][col] + 0);
			}
			// System.out.println();

		}
		// System.out.println();
		player.draw(g);
		// System.out.println(player.p);
		for (int i = 0; i < tank.size(); i++) {
			tank.get(i).draw(g);
		}
		Random rd = new Random();
		for (int i = 0; i < tank.size(); i++) {
			tank.get(i).updateBarrel((int) player.p.getX(),
					(int) player.p.getY());
			if (rd.nextInt(30) == 5) {
				mis.add(new Missile((int) tank.get(i).barrelX, (int) tank
						.get(i).barrelY, tank.get(i).unitX, tank.get(i).unitY));
			}
			
			if(rd.nextInt(30) == 5)
				tank.get(i).updateMove(fx,fy,obs);
		}

		if (mis.size() > 0) {
			for (Missile missile : mis) {
				missile.updateMove();
				boolean intersect = false;
				for (Rectangle r : obs) {
					if (r.contains(missile.p)) {
						intersect = true;
						missile.remove();
					}
				}

				for (int i = 0; i < tank.size(); i++) {
					if (new Rectangle((int) tank.get(i).p.getX(),
							(int) tank.get(i).p.getY(), tank.get(i).DIM_X,
							tank.get(i).DIM_Y).contains(missile.p)) {
						missile.remove();
						intersect = true;
						tank.get(i).remove();
					}
				}

				missile.draw(g);
			}
		}
		for (Iterator<Missile> iterator = mis.iterator(); iterator.hasNext();) {
			Missile miss = iterator.next();
			if (miss.remove) {
				iterator.remove();
			}
		}

		for (Iterator<Tank> iterator = tank.iterator(); iterator.hasNext();) {
			Tank t = iterator.next();
			if (t.remove) {
				iterator.remove();
			}
		}
	}

	public void CreateMap(char[][] map) {
		System.out.println("Creating a map....");
		char[][] temp = new char[map.length * 3][map[0].length * 3];
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				for (int mRow = 0; mRow < 3; mRow++) {
					for (int mCol = 0; mCol < 3; mCol++) {
						temp[row + mRow][mCol * col] = map[row][col];
					}
				}
			}
		}
		System.out.println(obs.size());
	}

	public Point RandomSpawn() {

		Random rand = new Random();
		int randomX = 0;
		int randomY = 0;
		/*
		 * do { randomX = rand.nextInt(Dim_X); randomY = rand.nextInt(Dim_Y);
		 * 
		 * } while (map[randomX][randomY] == 1);
		 */

		boolean intersect = true;
		while (intersect) {
			randomX = rand.nextInt(Dim_X);
			randomY = rand.nextInt(Dim_Y);
			intersect = false;
			for (Rectangle r : obs) {
				if (new Rectangle(randomY * fx, randomX * fy, 50, 50)
						.intersects(r)) {
					intersect = true;
					break;
				}
			}

		}

		return new Point(randomX * fx, randomY * fy);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// System.out.println("move");
		player.moveTank(key, fx, fy, obs);
		// repaint();
		// System.out.println(player.p);
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
		while (true) {
			repaint();
			try {
				Thread.sleep(16);// ~60 fps
			} catch (InterruptedException e) {

			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		player.updateBarrel(x, y);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Missile shot = new Missile(player.p,player.unitX,player.unitY);
		int x = player.barrelX;// (int) (player.p.getX() + (player.DIM_Y / 2));
		int y = player.barrelY;// (int) (player.p.getY() + (player.DIM_Y / 2));
		mis.add(new Missile(x, y, player.unitX, player.unitY));

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

	/*
	 * public void adjustBounds(int width, int height){ fx = width; fy = height;
	 * }
	 */
}
