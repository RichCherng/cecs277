package Assignment5;

import java.awt.Graphics;
import java.awt.Point;


public class Missile {
	Point p;
	private double unitX;
	private double unitY;
	boolean remove = false;
	public Missile(int x, int y,double ux, double uy){
		// need tank location and direction 
		p = new Point(x,y);
		unitX = ux;
		unitY = uy;
		//draw missile
	}
	
	public void draw(Graphics g){
		  g.fillOval((int)p.getX(),(int)p.getY(),5,5);
	}
	
	public void updateMove(){
		p.translate((int)(unitX * 20), (int)(unitY * 20));
	}
	
	public void remove(){
		remove = true;
	}
}
