import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.*;


public class Player {
	private int moveX;
	private int playerX, playerY;
	private int speed = 4;
	private boolean right = false, left = false;
	private ImageIcon ic;
	private boolean laser;
	private int counter, counterIncrement;
	
	public Player(int x){
		this.playerX = x;
		this.playerY = 805;
		this.moveX = 0;
		this.ic=new ImageIcon("images/player.png");
		this.laser = false;
		this.counter = 0;
		this.counterIncrement = 0;
	}
	
	public int getPlayerX() {
		return playerX;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_1){  //a key, so move left
			GameFrame.setStart("adventure");	

		}
		if (key == KeyEvent.VK_2){
			GameFrame.setStart("survival");
		}
		if (key == KeyEvent.VK_A|| key == KeyEvent.VK_LEFT){  //a key, so move left
			this.moveX = -speed;
			left = true;

		}
		if (key == KeyEvent.VK_D|| key == KeyEvent.VK_RIGHT){  //d key, so move right
			this.moveX = speed;
			right = true;

		}
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE){  //a key, so move left
			if (laser){
				GameFrame.shootLaser();
				this.laser = false; 
				Sound("lShoot");
				//GameFrame.shootBullet();
			}
			else if (GameFrame.getLaserDone()){
				GameFrame.shootBullet();
				
			}

		}
		
		/*
		if (key == KeyEvent.VK_R){  //a key, so move left
			GameFrame.createSlime(300);

		}
		if (key == KeyEvent.VK_T){  //a key, so move left
			GameFrame.createSlime(150);
		}
		if (key == KeyEvent.VK_Y){  //a key, so move left
			GameFrame.createSlime(75);

		}
		if (key == KeyEvent.VK_E){  //a key, so move left
			GameFrame.giantSlime();

		}
		*/
	
	}
	public void setLaser(){
		this.laser = false;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
	    	if (!right){
	    		this.moveX = 0;
	    	}
	    	else{
	    		this.moveX = speed;
	    	}
	    	left = false;
	    }
	    else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
	    	if (!left){
	    		this.moveX = 0;
	    	}
	    	else{
	    		this.moveX = -speed;
	    	}
	    	right = false;
	    }
	   
	  }
	
	public void checkCollision(){
		ArrayList<Slime> slimes = GameFrame.getSlimeList();
		ArrayList<Drop> drops = GameFrame.getDropList();
		if (playerX<=0){
			playerX = 0;
		}
		else if (playerX + 100 >=1742){
			playerX = 1742-100;
		}
		for (int i=0; i<slimes.size(); i++){
			Slime utemp = slimes.get(i) ;		//gets the next ufo for checking
	    	if (getBounds().intersects(utemp.getBounds())){ // checks if the slime makes contact with the player
	    		Sound("dead");
	    		GameFrame.startOver();
	    	}
		}
		for (int i=0; i<drops.size(); i++){
			Drop utemp = drops.get(i);
			if (getBounds().intersects(utemp.getBounds())){
				drops.remove(utemp);
				pickUp(GameFrame.property(utemp));
			}
		}
	}
	
	public void Sound(String p){
		if (p.equals("laser")){
			try{
				File file = new File("soundFX/lPickUp.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("shield")){
			try{
				File file = new File("soundFX/fPickUp.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("lShoot")){
			try{
				File file = new File("soundFX/lShoot.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("pop")){
			try{
				File file = new File("soundFX/ouch.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("dead")){
			try{
				File file = new File("soundFX/dead.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("rocket")){
			try{
				File file = new File("soundFX/rocket.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
		else if (p.equals("music")){
			try{
				File file = new File("soundFX/bgMusic.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.start();
			}
			catch(Exception e){
				System.out.println("ERROR" + e.getMessage());
			}
		}
	}
	
    public void pickUp(String s){
    	if (s.equals("laser")){
    		laser = true;
    		this.ic=new ImageIcon("images/pLaser1.png");
    		this.counterIncrement = 1;
			this.playerY = 780;
			Sound("laser");
    	}
    	else if (s.equals("shield")){
    		GameFrame.makeShield();
    		Sound("shield");
    	}
    }
	
	public void update(){
		playerX += this.moveX;
		if (GameFrame.getLaserDone() && !laser){
			this.ic=new ImageIcon("images/player.png");
			this.playerY = 800;
			this.counter = 0;
			this.counterIncrement = 0;
		}
		else{
			if (counter%50<5){
				this.ic = new ImageIcon("images/pl1.png");
			}
			else if (counter%50 <10){
				this.ic = new ImageIcon("images/pl2.png");
			}
			else if (counter%50 <15){
				this.ic = new ImageIcon("images/pl3.png");
			}
			else if (counter%50 <20){
				this.ic = new ImageIcon("images/pl4.png");
			}
			else if (counter%50 <25){
				this.ic = new ImageIcon("images/pl5.png");
			}
			else if (counter%50 <30){
				this.ic = new ImageIcon("images/pl6.png");
			}
			else if (counter%50 <35){
				this.ic = new ImageIcon("images/pl7.png");
			}
			else if (counter%50 <40){
				this.ic = new ImageIcon("images/pl8.png");
			}
			else if (counter%50 <45){
				this.ic = new ImageIcon("images/pl9.png");
			}
			else{
				this.ic = new ImageIcon("images/pl10.png");
			}
			counter+= counterIncrement;
		}
		checkCollision();
	}
	
	public boolean getLaser(){
		return this.laser;
	}
	
	public Image getplayerImg(){
		return ic.getImage();
	}
	public void draw(Graphics2D g2d){
		g2d.drawImage(getplayerImg(),playerX,playerY,null);
	}
	public Rectangle getBounds(){
	    return new Rectangle(playerX, playerY,100, 200);
	}
	
	
}
