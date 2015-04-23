package Assignment5;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Missile Class - representing missile 
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Missile {
	/** Location of the missile*/
	Point p;
	/** Direction that the missile is moving towrad*/
	private double unitX,unitY;
	/** State of the missile*/
	boolean remove = false;
	/**
	 * Constructor - construct missile 
	 * @param x - Origin point
	 * @param y - Origin point
	 * @param ux - unit vector for moving direction
	 * @param uy - unit vector for moving direction
	 */
	public Missile(int x, int y,double ux, double uy){
		p = new Point(x,y);
		unitX = ux;
		unitY = uy;
	}
	/**
	 * Draw the Missile in the JPanel
	 * @param g - Graphics
	 */
	public void draw(Graphics g){
		  g.fillOval((int)p.getX(),(int)p.getY(),5,5);
	}
	/**
	 * Update location of the missile using unit vector
	 */
	public void updateMove(){
		p.translate((int)(unitX * 20), (int)(unitY * 20));
	}
	/**
	 * Function is called when missile is destroyed
	 */
	public void remove(){
		remove = true;
	}
}
