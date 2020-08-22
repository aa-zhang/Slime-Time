import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images

import javax.swing.ImageIcon;


public class Shield{
	private int ShieldX, ShieldY;
	private ImageIcon ic;
	private boolean hasShield;
	
	public Shield(int x){
		this.ShieldX = x-30;
		this.ShieldY = 765;
		this.hasShield = true;
		ic=new ImageIcon("Shield.png");
	}
	
	public void breakShield(){
		hasShield = false;
	}
	
	 
	public void update(){
		if (this.hasShield){
			this.ShieldX = GameFrame.getPlayerX()-30;
		}
		else{
			this.ShieldX = -200;
		}
		
	}
	
	public void draw(Graphics2D g2d){
		if (hasShield){
			g2d.drawImage(getShieldImg(),ShieldX,ShieldY,null);
		}
	}
	
	public Image getShieldImg(){
		return ic.getImage();
	}
	
	public Rectangle getBounds(){
        //get box around Shield pic for use in detecting collision
        return new Rectangle(ShieldX, ShieldY,getShieldImg().getWidth(null),getShieldImg().getHeight(null));
	}
	
	

}