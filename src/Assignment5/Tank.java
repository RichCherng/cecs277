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
	
	public Tank(Point loc, Color col, int dir){
		p = loc;
		c = col;
		direction = dir;
	}
	
	public void draw(Graphics g){
		
		//g.drawRect( 0, 0, 50, 50);
		g.setColor(c);
		//g.fillRect(0, 0, 50, 50);
		g.fillRect( (int)p.getX(), (int)p.getY(), 25, 25);
	}
	
	public void moveTank(int dir,int dimX, int dimY){
		switch(dir){
		case KeyEvent.VK_W:
			//p.setLocation(p.getX(), p.getY()-13);
			p.translate(0, - dimY/2);
			break;
		case KeyEvent.VK_S:
			p.setLocation(p.getX(), p.getY()+13);
			break;
		case KeyEvent.VK_A:
			p.setLocation(p.getX()-13, p.getY());
			break;
		case KeyEvent.VK_D:
			p.setLocation(p.getX()+13, p.getY());
			break;
		}
	}
}
