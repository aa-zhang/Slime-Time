import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images
import javax.swing.ImageIcon;


public class Slime{
	private double moveX;
	private double moveY;
	private int slimeX, slimeY;
	private double gravity;
	private ImageIcon ic;
	private double height;
	private double speed;
	private int size;
	private boolean popped = false;
	private int counter, counterIncrement;
	private boolean offScreen;
	
	public Slime(int x, int y, double mX, double mY, double s, int si){ // x-coordinate, y-coordinate, horizontal velocity, vertical velocity, number of times popped, strength of gravity, size of slime
		this.moveX = mX;
		this.moveY = mY/1.5;
		this.slimeX = x;
		this.slimeY = y;
		this.gravity = 0; // variable added to the moveY
		this.height = mY;
		this.speed = s; // acceleration
		this.size = si;
		this.counter = 0;
		this.counterIncrement = 0;
		this.offScreen = true;
		if (this.moveX == 0){
			this.moveY = 0;
		}
		ic=new ImageIcon("slime.png");
		Image image = ic.getImage(); // transform it 
		Image newimg = image.getScaledInstance((this.size), (this.size),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ic = new ImageIcon(newimg);
	}
	
	public boolean getPopped(){
		return this.popped;
	}
	
	public double gethV(){
		return this.moveX;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	public void checkCollision(){
		Shield s = GameFrame.getShield();
		if (slimeX+getslimeImg().getWidth(null) > 1750 && !offScreen){ 	//1360 is the width of the window
            this.moveX*=-1;				//changes moveX from 3 to -3
		}
		if (slimeX < 0 && !offScreen){ 	//1360 is the width of the window
            this.moveX*=-1;				//changes moveX from 3 to -3;
		}
		if (getBounds().intersects(s.getBounds())){
			this.moveY = this.height;
			this.gravity = 0;
			this.moveX*=-1;
			GameFrame.breakShield();
		}
		if (slimeY+getslimeImg().getHeight(null) >950){ 	//1360 is the width of the window
            this.gravity = 0;
            this.moveY= this.height;
		}
		if (this.counter > 5){
			GameFrame.removeSlime(this);
		}
		if (this.offScreen){
			if (slimeX+getslimeImg().getWidth(null) < 1750 && slimeX > 0){
				this.offScreen = false;
			}
		}
		
		
	}
	
	public int getSlimeX(){
		return this.slimeX;
	}
	
	public int getSlimeY(){
		return this.slimeY;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void update(){
		this.slimeX += this.moveX;
		this.gravity += this.speed;
		this.moveY += this.gravity;
		this.slimeY += this.moveY;
		this.counter += this.counterIncrement;
		checkCollision(); 
	}
	
	public void pop(int s){
		ic=new ImageIcon("Pop.png");
		Image image = ic.getImage(); // transform it 
		Image newimg = image.getScaledInstance(s, s,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ic = new ImageIcon(newimg);
		this.moveX = 0;
		this.moveY = 0;
		this.slimeY +=10;
		this.speed = 0;
		this.gravity = 0;
		this.popped = true;
		this.counterIncrement = 1;
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getslimeImg(),slimeX,slimeY,null);
	}
	

	public Image getslimeImg(){
		return ic.getImage();
	
	 }
	public Rectangle getBounds(){
        //get box around slime pic for use in detecting collision
        return new Rectangle(slimeX, slimeY,getslimeImg().getWidth(null),getslimeImg().getHeight(null));
	}
	
	

}