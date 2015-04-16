package Assignment5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;

public class Panel extends JPanel {

	int Dim_X,Dim_Y;
	char[][] map;
	
	public Panel() {
		
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
		for(int x = 0; x < Dim_X; x++){
			input = read.nextLine();
			dim = input.split("\\s+");
			for(int y = 0; y < Dim_Y; y++){
				map[x][y] =	(char) Integer.parseInt(dim[y]);
			}
		}
		
		
		//setLayout(new GridBagLayout());
		//setLayout(new BorderLayout());
		setBounds(0, 0, getXDim()*80, getYDim()*80);
		//setSize(getXDim()*80,getYDim()*80);
		//setBackground(Color.DARK_GRAY);
	}
	
	public int getXDim(){
		return Dim_X;
	}
	
	public int getYDim(){
		return Dim_Y;
	}
	
	public void paintComponent(Graphics g){
		//g.drawRect( 0, 0, 50, 50);
		g.setColor(Color.YELLOW);
		//g.fillRect(0, 0, 50, 50);
		//g.drawRect( 50, 50, 50, 50);
		for(int row = 0; row < Dim_X; row++){
			for(int col = 0; col < Dim_Y; col++){
				if(map[row][col] == 1)
					g.fillRect(row *(getXDim()*80/Dim_X), col*(getYDim()*80/Dim_Y), 80, 80);
			}
		}
	}
}
