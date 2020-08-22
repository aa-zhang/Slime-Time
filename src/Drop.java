import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images
import javax.swing.ImageIcon;


public class Drop{
	private double moveY;
	private double gravity;
	private int DropX, DropY;
	private ImageIcon ic;
	private String property;
	
	public Drop(int x, int y, String p){
		this.moveY = 0;
		this.DropX = x;
		this.DropY = y;
		this.property = p;
		this.gravity = 0;
		ic=new ImageIcon("Drop.png");
		Image image = ic.getImage(); // transform it 
		Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ic = new ImageIcon(newimg);
	}
	
	public String getProperty(){
		return this.property;
	}
	
	public void checkCollision(){
		if (this.DropY+getDropImg().getHeight(null) >=950){ 	//1360 is the width of the window
            this.gravity = 0;
            this.moveY= 0;
            this.DropY = 950-30;
		}
	}
	
	public void update(){
		this.gravity += 0.001;
		this.moveY += this.gravity;
		this.DropY += this.moveY;
		checkCollision();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getDropImg(),DropX,DropY,null);
	}
	
	public Image getDropImg(){
		return ic.getImage();
	}
	
	public Rectangle getBounds(){
        //get box around Drop pic for use in detecting collision
        return new Rectangle(DropX, DropY,getDropImg().getWidth(null),getDropImg().getHeight(null));
	}
	
	

}