import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Level{
	private int level;
	private int levelCounter, levelCounterIncrement;
	private int length, extra;
	public Level(int l){
		this.level = l;
		this.levelCounter = 0;
		this.levelCounterIncrement = 0;
		this.length = 5000;
		this.extra = 0;
	}
	
	 
	public void update(){
		if (GameFrame.getSlimeCount() == 0){
			levelCounterIncrement = 1;
			
			this.levelCounter += this.levelCounterIncrement;
			if (levelCounter > 100){
				level++;
				nextLevel(level);
				levelCounter = 0;
				levelCounterIncrement = 0;
			}
		}
		else{
			length--;
			extra++;
		}
		if (length <= 0){
			GameFrame.startOver();
		}
		
		
	}
	public void draw(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
        g2d.fillRect(8, 98+extra/5, 29, 4+length/5);
		g2d.setColor(Color.RED);
        g2d.fillRect(10, 100+extra/5, 25, length/5);
        //g2d.setColor(Color.WHITE);
        //g2d.fillRect(5, 100, 100, 20);
        //g2d.drawString("")
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void nextLevel(int level){
		extra = 0;
    	if (level == 1){
    		GameFrame.createSlime(-400, 400, 2, -7, 0.001, 300);
    		length = 2000;
    		
    	}
    	else if (level == 2){
    		GameFrame.createSlime(1800, 600, -2, -5, 0.0013, 75);
    		GameFrame.createSlime(1900, 600, -2, -5, 0.0013, 75);
    		GameFrame.createSlime(2000, 600, -2, -5, 0.0013, 75);

    		GameFrame.createSlime(-50, 600, 2, -5, 0.0013, 75);
    		GameFrame.createSlime(-150, 600, 2, -5, 0.0013, 75);
    		GameFrame.createSlime(-250, 600, 2, -5, 0.0013, 75);
    		length = 2000;
    	}
    	else if (level == 3){
    		GameFrame.createSlime(2050, 400, -2, -12, 0.006, 300);
    		length = 2000;
    	}
    	else if (level == 4){
    		GameFrame.createSlime(-150, 300, 6, -6, 0.0011, 150);
    		GameFrame.createSlime(1750, 300, -6, -6, 0.0011, 150); 
    		length = 2000;
    	}
    	else if (level == 5){
    		GameFrame.createSlime(375, -1000, 0, -9, 0.0008, 1000);
    		length = 5000;
    	}
    }
    


}