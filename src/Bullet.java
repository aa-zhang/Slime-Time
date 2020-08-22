import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images
import javax.swing.ImageIcon;
import java.util.ArrayList;		//because we have an ArrayList of Ufos



public class Bullet{
	private double moveY;
	private int bulletX, bulletY;
	private boolean shot = false;
	private ImageIcon ic;
	private int counter, counterIncrement;
	private String property;
	private boolean laserDone;
	public Bullet(int x, int y){
		this.moveY = 0;
		this.bulletX = x;
		this.bulletY = y;
		this.counter = 0;
		this.counterIncrement = 0;
		this.property = "bullet";
		this.shot = false;
		this.laserDone = true;
		ic=new ImageIcon("bullet.png");
	}
	public void checkCollision(){
		ArrayList<Slime> slimes = GameFrame.getSlimeList();
		for (int i=0; i<slimes.size(); i++){
			Slime utemp = slimes.get(i);		//gets the next ufo for checking
        	if (getBounds().intersects(utemp.getBounds()) && !utemp.getPopped()){		//collision check here
                	//collision!  We therefore need to get rid of the offending Ufo
				if (this.shot){
					if (!this.property.equals("laser")){
		            	GameFrame.breakSlime(utemp, this.property);
		            	GameFrame.createBullet();
		            	break;
					}
					else{
		            	GameFrame.breakSlime(utemp, this.property);
		            	
					}
				}
        	}
		}
		if (this.bulletY <-100 && !this.property.equals("laser")){
			GameFrame.createBullet();
		}
				
	}
	
	public String getProperty(){
		return this.property;
	}
	
	public boolean shot(){
		return this.shot;
	}
	
	public void shoot(String p){
		this.property = p;
		if (this.property.equals("bullet")){
			this.moveY = -7;
			this.shot = true;
			if (this.bulletY == 780){
				GameFrame.Sound("rocket");
			}
		}
		else if (this.property.equals("laser")){
			this.bulletX = GameFrame.getPlayerX()-3;
			this.bulletY = 300;
			this.moveY = 0;
			this.counterIncrement = 1;
			this.shot = true;
			this.laserDone = false;
			this.ic=new ImageIcon("Laser.png");
		}
		
	}
	
	public boolean getLaserDone(){
		return laserDone;
	}
	
	public void update(){
		if (this.property.equals("laser")){
			this.bulletX = GameFrame.getPlayerX()-3;
		}
		
		else if (!shot){
			this.bulletX = GameFrame.getPlayerX()+40;
		}
		if (this.property.equals("laser")){
			if (this.counter > 70){
				this.property = "bullet";
				this.counter = 0;
				this.counterIncrement = 0;
				this.laserDone = true;
				ic=new ImageIcon("bullet.png");
				GameFrame.createBullet();
			}
		}
		if (laserDone && GameFrame.getLaser()){
			this.bulletY = 1000;
		}
		this.bulletY += this.moveY;
		this.counter += this.counterIncrement;
		checkCollision(); 
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getbulletImg(),bulletX,bulletY,null);
	}
	
	public Image getbulletImg(){
		return ic.getImage();
	}
	
	public Rectangle getBounds(){
        //get box around bullet pic for use in detecting collision
        return new Rectangle(bulletX, bulletY,getbulletImg().getWidth(null),getbulletImg().getHeight(null));
	}
	
	

}