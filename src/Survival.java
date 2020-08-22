import java.awt.Color;
import java.awt.Graphics2D;

public class Survival{
	private double counter, counterIncrement;
	private long startTime, endTime, duration;

	public Survival(){
		startTime = System.currentTimeMillis();
	}
	
	public void draw(Graphics2D g2d){
		endTime = System.currentTimeMillis();
		
		g2d.drawString("Time Survived: " + ((endTime-startTime)/1000), 500, 100);
	}
	public void update(){
		if (((endTime-startTime)/1000) % 2 == 0){
			int n = (int)(Math.random()*3+1), m = (int)(Math.random()*2+1);
			if (n == 1){
				if (m == 1){
					GameFrame.createSlime(-75, 600, 2, -5, 0.0013, 75);
				}
				else{
					GameFrame.createSlime(1750, 600, -2, -5, 0.0013, 75);
				}
			}
			else if (n == 2){
				if (m == 1){
					GameFrame.createSlime(-150, 600, 2, -6, 0.0013, 150);
				}
				else{
					GameFrame.createSlime(1750, 600, -2, -6, 0.0013, 150);
				}
			}
			else if (n == 3){
				if (m == 1){
					GameFrame.createSlime(-300, 600, 2, -7, 0.0013, 300);
				}
				else{
					GameFrame.createSlime(1750, 600, -2, -7, 0.0013, 300);
				}
			}
		}
		
	}
	
}