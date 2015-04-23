package Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tank Class represent both player and enemy
 * @author Pongsathorn Cherngchoasil
 *
 */
public class Tank  {
	/**Location of the tank*/
	Point p;
	/**Color of the tank*/
	Color c;
	/**
	 * barrelX - Location of the end of the barrel
	 * barrelY - Location of the end of the barrel
	 * health - represent lives
	 */
	int speed, barrelX, barrelY, health = 5;
	/**Location the barrel is pointing to*/
	Point barrel;
	/**
	 * BarrelRange - Length of the barrel
	 * DIM_X - Dimension of the tank
	 * DIM_Y - DImension of the tank
	 */
	private final int BarrelRange = 50
			,DIM_X = 50, DIM_Y = 50;
	/**
	 * unitX - Unit Vector where the barrel pointing to
	 * unitY - Unit Vector where the barrel pointing to
	 */
	double unitX,unitY;
	/** State of the tank, true if tank is destroyed*/
	boolean remove = false;
	/** Rectangle representing the tank*/
	Rectangle mRec;
	/** List of the missile fired by this tank*/
	ArrayList<Missile> missile;
	/**
	 * Constructor - construct tank class
	 * @param loc - location of spawn
	 * @param col - color of the tank
	 */
	public Tank(Point loc, Color col) {
		p = loc;
		barrel = loc;
		c = col;
		// Initialize Dimension of the tank
		mRec = new Rectangle((int) loc.getX(), (int) loc.getY(), DIM_X, DIM_Y);
		//Initialize list of tank's missile
		missile = new ArrayList<Missile>();

	}
	/**
	 * function to draw tank in the JPanel
	 * @param g - graphic
	 */
	public void draw(Graphics g) {
		//Tank is alive if health is > 0
		if (health > 0) {
			g.setColor(c);
			g.fillRect((int) p.getX(), (int) p.getY(), DIM_X, DIM_Y);
			g.setColor(Color.RED);
			drawBarrel(g);
		}
		drawMissile(g);
	}
	/**
	 * Draw missile that created by this tank until they destroyed
	 * @param g - graphics
	 */
	public void drawMissile(Graphics g) {
		for (Missile m : missile) {
			//update the location of the missile
			m.updateMove();
			m.draw(g);
		}
	}
	/**
	 * Check for any collision between this tank and every other missile in the game.
	 * @param player - Tank beloging to the player
	 * @param tank - List of enemies
	 * @param obs - obstacle in the game.
	 */
	public void testHit(Tank player, ArrayList<Tank> tank,
			ArrayList<Rectangle> obs) {
		for (Tank t : tank) {
			for (Missile mis : t.missile) {
				//check if missile hit the player
				if (mRec.contains(mis.p)) {
					//remove the missile from the game
					mis.remove();
					//reset player location to spawn and decrement his lives
					reset();
				}
			}
		}
		for(Missile mis: player.missile){
			for(Tank t: tank){
				//check if this tank missile hit any tank
				if (t.getRec().contains(mis.p)) {
					//remove missile from the game
					mis.remove();
					//Decrement the tank's lives or remove it from the game
					t.remove();
				}
			}
			
		}
		for (Missile m : missile) {
			for (Rectangle r : obs) {
				//check if the missile hit the game
				if (r.contains(m.p)) {
					//remove missile
					m.remove();
				}
			}
		}
		//remove missile that is destroyed from the list
		for (Iterator<Missile> iterator = missile.iterator(); iterator
				.hasNext();) {
			Missile miss = iterator.next();
			if (miss.remove) {
				iterator.remove();
			}
		}
	}
	/**
	 * Accessory method 
	 * @return rectangle representing tank 
	 */
	public Rectangle getRec(){
		return mRec;
	}
	/**
	 * create a missile every time it called
	 * @param x location of the barrel
	 * @param y location of the barrel
	 */
	public void fireMissile(int x, int y) {
		missile.add(new Missile(x, y, unitX, unitY));
	}
	/**
	 * Draw barrel of the tank
	 * @param g - graphics
	 */
	public void drawBarrel(Graphics g) {
		unitX = (barrel.getX() - (p.getX() + DIM_X / 2));
		unitY = (barrel.getY() - (p.getY() + DIM_Y / 2));
		// Magnitude of from the center of the tank to location the mouse pointing to
		double mag = Math.sqrt(Math.pow(unitX, 2) + Math.pow(unitY, 2));
		// Unit Vector
		unitX /= mag;
		unitY /= mag;
		// Location of the end of the barrel
		int newX = (int) (BarrelRange * unitX);
		int newY = (int) (BarrelRange * unitY);
		barrelX = (int) p.getX() + DIM_X / 2 + newX;
		barrelY = (int) p.getY() + DIM_Y / 2 + newY;
		g.drawLine((int) p.getX() + DIM_X / 2, (int) p.getY() + DIM_Y / 2,
				(int) p.getX() + DIM_X / 2 + newX, (int) p.getY() + DIM_Y / 2
						+ newY);
	}
	/**
	 * update the barrel orientation according to the location of the mouse
	 * @param x location the mouse is pointing to
	 * @param y location the mouse is pointing to
	 */
	public void updateBarrel(int x, int y) {
		barrel = new Point(x, y);
	}
	/**
	 * Called when player move
	 * @param dir Key-event, direction being called
	 * @param dimX dimension of the JPanel
	 * @param dimY dimension of the JPanel
	 * @param obs List of obstacle in the game.
	 */
	public void moveTank(int dir, int dimX, int dimY, ArrayList<Rectangle> obs) {
		int dx = 0;
		int dy = 0;
		//check for direction
		switch (dir) {
		case KeyEvent.VK_W:
			dy -= 3;
			break;
		case KeyEvent.VK_S:
			dy += 3;
			break;
		case KeyEvent.VK_A:
			dx -= 3;
			break;
		case KeyEvent.VK_D:
			dx += 3;
			break;
		}
		//Cehck for collision with obstacle
		boolean intersect = false;
		p.translate(dx, dy);
		mRec.setLocation(p);
		for (Rectangle r : obs) {
			if (mRec.intersects(r)) {
				intersect = true;
			}
		}
		if (intersect) {
			p.translate(-dx, -dy);
			mRec.setLocation(p);
		}
	}
	/**
	 * Move function for AI
	 * AI will try to move closer to player location.
	 *  If hit the wall, move only in the direction that allowed
	 * @param fx x-coordinate of the player
	 * @param fy y-coordinate of the player
	 * @param obs list of obstacle in the game (Wall)
	 */
	public void updateMove(int fx, int fy, ArrayList<Rectangle> obs) {
		boolean intersect = false;
		//update move closer to player in X direction
		p.translate((int) (unitX * fx / 8), 0);
		mRec.setLocation(p);
		//Check for collision
		for (Rectangle r : obs) {
			if (mRec.intersects(r)) {
				intersect = true;
			}
		}
		if (intersect) {
			p.translate(-(int) (unitX * fx / 8), 0);
			mRec.setLocation(p);
		}
		//update move closer to player in Y direction
		p.translate(0, (int) (unitY * fy / 8));
		mRec.setLocation(p);
		//check for collision
		for (Rectangle r : obs) {
			if (mRec.intersects(r)) {
				intersect = true;
			}
		}
		if (intersect) {
			p.translate(0, -(int) (unitY * fy / 8));
			mRec.setLocation(p);
		}

	}
	/**
	 * Function call when tank get hit
	 * remove one health every time
	 * if no more live exist then the tank is destroyed
	 */
	public void remove() {
		if (health > 1)
			health--;
		else
			remove = true;
	}
	/**
	 * Check state of the player
	 * if health is 0 then the 
	 * player will no longer be in game
	 * @return true if player is still in the game
	 */
	public boolean inGame() {
		return health > 0;
	}
	/**
	 * Reset player location every time player get hit by missile 
	 */
	public void reset() {
		health--;
		p.setLocation(80, 80);
	}
	/**
	 * Check state of the tank (used by AI)
	 * @return true is AI is still alive
	 */
	public boolean isAlive() {
		return health > 0;
	}
	
}
