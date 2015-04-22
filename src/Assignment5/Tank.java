package Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Tank extends JPanel{

	Point p;
	Color c;
	int speed,barrelX,barrelY;
	int health = 5;
	Missile missile;
	Point barrel;
	private final int BarrelRange = 50;
	final int DIM_X = 50;
	final int DIM_Y = 50;
	double unitX;
	double unitY;
	boolean remove = false;
	
	public Tank(Point loc, Color col){
		p = loc;
		barrel = loc;
		c = col;
		
	}
	
	public void draw(Graphics g){
		
		//g.drawRect( 0, 0, 50, 50);
		g.setColor(c);
		//g.fillRect(0, 0, 50, 50);
		g.fillRect( (int)p.getX(), (int)p.getY(), DIM_X, DIM_Y);
		g.setColor(Color.RED);
		drawBarrel(g);
	}
	
	public void drawBarrel(Graphics g){
		unitX =  (barrel.getX() - (p.getX() + DIM_X/2));
		unitY =  (barrel.getY() - (p.getY() + DIM_Y/2));
		double mag = Math.sqrt(Math.pow(unitX, 2) + Math.pow(unitY,2));
		unitX /= mag;
		unitY /= mag;
		int newX = (int) (BarrelRange * unitX);
		int newY = (int) (BarrelRange * unitY);
		barrelX = (int)p.getX() + DIM_X/2 + newX;
		barrelY = (int)p.getY() + DIM_Y/2 + newY;
		g.drawLine((int)p.getX() + DIM_X/2, (int)p.getY() + DIM_Y/2,
					(int)p.getX() + DIM_X/2 + newX, (int)p.getY() + DIM_Y/2 + newY);
		//System.out.println(unitX + " " + unitY + " " + mag);
	}
	
	public void updateBarrel(int x, int y){
		barrel = new Point(x,y);
	}
	
	public void moveTank(int dir,int dimX, int dimY,ArrayList<Rectangle> obs){
		int dx = 0;
		int dy = 0;
		switch(dir){
		case KeyEvent.VK_W:
			//p.setLocation(p.getX(), p.getY()-13);
			//p.translate(0, - dimY/16);
			dy -= dimY/8;
			break;
		case KeyEvent.VK_S:
			//p.setLocation(p.getX(), p.getY()+13);
			//p.translate(0, dimY/8);
			 dy += dimY/8;
			break;
		case KeyEvent.VK_A:
			//p.setLocation(p.getX()-13, p.getY());
			//p.translate(-dimX/8, 0);
			dx -= dimX/8;
			break;
		case KeyEvent.VK_D:
			//p.setLocation(p.getX()+13, p.getY());
			//p.translate(dimX/8, 0);
			dx += dimX/8;
			break;
		}
		boolean intersect = false;
		for(Rectangle r: obs){
			if(new Rectangle( (int)(p.getX()+dx) ,(int)(p.getY() + dy),DIM_X,DIM_Y).intersects(r)){
				intersect = true;
			}
		}
		if(!intersect)
			p.translate(dx,dy);
	}
	
	public void updateMove(int fx,int fy,ArrayList<Rectangle> obs){
		
		boolean intersect = false;
		for(Rectangle r: obs){
			if(new Rectangle( (int)(p.getX()+(unitX*fx/8)) ,(int)(p.getY()),DIM_X,DIM_Y).intersects(r)){
				intersect = true;
			}
		}
		if(!intersect)
			p.translate((int)(unitX*fx/8), 0);
		
		for(Rectangle r: obs){
			if(new Rectangle( (int)(p.getX()) ,(int)(p.getY() + unitY*fy),DIM_X,DIM_Y).intersects(r)){
				intersect = true;
			}
		}
		if(!intersect)
			p.translate(0, (int)(unitY*fy));
		
	}
	
	public void remove(){
		if(health > 1)
			health--;
		else
			remove = true;
	}
	
	public void fire(Point player){
		
	}
}
