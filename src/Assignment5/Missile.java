package Assignment5;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Missile extends JPanel{
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
		p.translate((int)(unitX * 10), (int)(unitY * 10));
	}
	
	public void remove(){
		remove = true;
	}
}
