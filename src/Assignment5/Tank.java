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
	Rectangle mRec;
	
	public Tank(Point loc, Color col){
		p = loc;
		barrel = loc;
		c = col;
		mRec = new Rectangle((int)loc.getX(),(int)loc.getY(),DIM_X,DIM_Y);
		
	}
	
	public void draw(Graphics g){
		g.setColor(c);
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
			dy -= 3;
			break;
		case KeyEvent.VK_S:
			//p.setLocation(p.getX(), p.getY()+13);
			//p.translate(0, dimY/8);
			 dy += 3;
			break;
		case KeyEvent.VK_A:
			//p.setLocation(p.getX()-13, p.getY());
			//p.translate(-dimX/8, 0);
			dx -= 3;
			break;
		case KeyEvent.VK_D:
			//p.setLocation(p.getX()+13, p.getY());
			//p.translate(dimX/8, 0);
			dx += 3;
			break;
		}
		boolean intersect = false;
		p.translate(dx,dy);
		mRec.setLocation(p);
		for(Rectangle r: obs){
			if(mRec.intersects(r)){
				intersect = true;
			}
		}
		if(intersect){
			p.translate(-dx,-dy);
			mRec.setLocation(p);
		}
	}
	
	public Rectangle getRec(){
		return mRec;
	}
	
	public void updateMove(int fx,int fy,ArrayList<Rectangle> obs){
		
		boolean intersect = false;
		p.translate((int)(unitX*fx/8), 0);
		System.out.print(unitX*fx/8);
		mRec.setLocation(p);
		for(Rectangle r: obs){
			if(mRec.intersects(r)){
				intersect = true;
			}
		}
		if(intersect){
			p.translate(-(int)(unitX*fx/8), 0);
			mRec.setLocation(p);
		}
		
		p.translate(0,(int)(unitY*fy));
		mRec.setLocation(p);
		for(Rectangle r: obs){
			if(mRec.intersects(r)){
				intersect = true;
			}
		}
		if(intersect){
			p.translate(0,-(int)(unitY*fy));
			mRec.setLocation(p);
		}
		
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
