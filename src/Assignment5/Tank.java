package Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Tank extends JPanel{

	Point p;
	Color c;
	int direction,speed;
	Missile missile;
	Point barrel;
	private final int BarrelRange = 50;
	private final int DIM_X = 50;
	private final int DIM_Y = 50;
	
	public Tank(Point loc, Color col, int dir){
		p = loc;
		barrel = loc;
		c = col;
		direction = dir;
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
		double unitX =  (barrel.getX() - (p.getX() + DIM_X/2));
		double unitY =  (barrel.getY() - (p.getY() + DIM_Y/2));
		double mag = Math.sqrt(Math.pow(unitX, 2) + Math.pow(unitY,2));
		unitX /= mag;
		unitY /= mag;
		int newX = (int) (BarrelRange * unitX);
		int newY = (int) (BarrelRange * unitY);
		g.drawLine((int)p.getX() + DIM_X/2, (int)p.getY() + DIM_Y/2, (int)p.getX() + DIM_X/2 + newX, (int)p.getY() + DIM_Y/2 + newY);
		System.out.println(unitX + " " + unitY + " " + mag);
	}
	
	public void updateBarrel(int x, int y){
		barrel = new Point(x,y);
	}
	
	public void moveTank(int dir,int dimX, int dimY){
		switch(dir){
		case KeyEvent.VK_W:
			//p.setLocation(p.getX(), p.getY()-13);
			p.translate(0, - dimY/16);
			break;
		case KeyEvent.VK_S:
			//p.setLocation(p.getX(), p.getY()+13);
			p.translate(0, dimY/8);
			break;
		case KeyEvent.VK_A:
			//p.setLocation(p.getX()-13, p.getY());
			p.translate(-dimX/8, 0);
			break;
		case KeyEvent.VK_D:
			//p.setLocation(p.getX()+13, p.getY());
			p.translate(dimX/8, 0);
			break;
		}
	}
}
