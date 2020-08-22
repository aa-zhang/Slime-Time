import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;	
import java.awt.event.ActionEvent;      //for timer
import java.awt.event.ActionListener;   //for timer
import java.util.ArrayList;		// To contain the slimes and bullets


public class GameFrame extends JPanel implements ActionListener{
	Timer mainTimer;
    static Player player;
    static Shield shield;
    static StartMenu startmenu;
    static Background background;
    static Level level;
    static ArrayList<Slime> slimes = new ArrayList<Slime>();
    static Bullet bullet;
    static ArrayList<Drop> drops = new ArrayList<Drop>();
    static boolean start;
    static Survival survival;
    static boolean adventure;
    
    public GameFrame(){
        setFocusable(true);	
        startmenu = new StartMenu();
        start = false;
        background = new Background();
        player = new Player(825);
        shield = new Shield(-200);
        level = new Level(0);
        survival = new Survival();
        adventure = false;
        addKeyListener(new KeyAdapt(player));
        breakShield();
        createBullet();
        player.Sound("music");
        mainTimer = new Timer(9,this);     //sets Timer object to 10 milliseconds
        mainTimer.start();                  //to start the timer
    }
    
    public static void startOver(){
    	slimes.clear();
    	drops.clear();
    	player.setLaser();
    	start = false;
    	level = new Level(0);
    }
 // x-coordinate, y-coordinate, horizontal velocity, vertical velocity, 
    //number of times popped, strength of gravity, size of slime
    
    
    public static String property(Drop d){
    	return d.getProperty();
    	
    }
    
    public static void setStart(String s){
    	start = true;
    	if (s.equals("adventure")){
    		adventure = true;
    	}
    	else {
    		adventure = false;
    	}
    }
    
    public static boolean getLaserDone(){
    	return bullet.getLaserDone();
    }
    
    public static void createSlime(int x, int y, double hV, double vV, double g, int s){
    	slimes.add(new Slime(x, y, hV, vV, g, s));
    }

    public static boolean getLaser(){
    	return player.getLaser();
    }
    
    
    public static void createBullet(){	
    	bullet = new Bullet(player.getPlayerX()+40, 780);
    }
    public static void shootBullet(){
    	bullet.shoot("bullet");
    }
    public static void shootLaser(){
    	bullet.shoot("laser");
    }
    public void actionPerformed(ActionEvent arg0){  //this method nec'y for timers
    	if (start){
    		for (int i=0; i<slimes.size(); i++){
            	Slime tempSlime = slimes.get(i);
            	tempSlime.update();
            }
            bullet.update();
            for (int i=0; i<drops.size(); i++){
            	Drop tempDrop = drops.get(i);
            	tempDrop.update();
            }
            player.update();
            shield.update();
            if (adventure){
            	level.update();
            }
            else{
            	survival.update();
            }
            repaint();
            //System.out.println(slimes.size());
            
    	}
    	
    }
    
    public static int getSlimeCount(){
    	return slimes.size();
    }
     
    public void paint(Graphics g){	//this method paints everything onto the screen
        super.paint(g);
        Graphics2D g2d  = (Graphics2D) g; 
        if (!start){
        	startmenu.draw(g2d);
        	g.setFont(new Font("Arial", Font.BOLD, 70));
        	g.drawString("PRESS 1 FOR ADVENTURE", 425, 400);
        	g.drawString("PRESS 2 FOR SURVIVAL MODE", 350, 500);
        }
        else{
        	background.draw(g2d);
            for (int i=0;i<slimes.size();i++){    
                Slime tempSlime = slimes.get(i);   
                tempSlime.draw(g2d);
            }
            bullet.draw(g2d);
            for (int i=0; i<drops.size(); i++){
            	Drop tempDrop = drops.get(i);
            	tempDrop.draw(g2d);
            }
            player.draw(g2d);
            shield.draw(g2d);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            if (level.getLevel()>0 && adventure){
            	g.drawString("Level " + level.getLevel(), 780, 150);
            	level.draw(g2d);
            }
            else if (!adventure){
            	survival.draw(g2d);
            }
        }
        

    }
 
    public static ArrayList<Drop> getDropList(){	
        return drops;				
    }
    
    public static Shield getShield(){
    	return shield;
    }
    
    public static ArrayList<Slime> getSlimeList(){	
        return slimes;			
	}
    
	public static void breakSlime(Slime s, String prop){	
		int x = s.getSlimeX();
		int y = s.getSlimeY();
		double b = s.getSpeed();
		double hV = s.gethV();
		double height = s.getHeight();
		int si = s.getSize();
		String property = prop;
		int middle = (s.getslimeImg().getWidth(null)/2)+x - (s.getslimeImg().getWidth(null)/4);
		
		if (hV == 0){
			hV = 2;
		}
		
		if (si >= 125 && !property.equals("laser")){ // the number of pops until the slime disappears
			slimes.add(new Slime(middle, y, -hV, height+1, b+0.0001, si/2));
	    	slimes.add(new Slime(middle, y, hV, height+1, b+0.0001, si/2));
		}
		else if (si <= 75){
			int chance = (int)(Math.random()*5+1);
			if (chance == 1){
				drops.add(new Drop(middle, y, "laser"));
			}
			else if (chance == 2){
				drops.add(new Drop(middle, y, "shield"));
			}
			
		}
		s.pop(si);
		player.Sound("pop");
    	
		 
	}
	
	public static void Sound(String p){
		player.Sound(p);
	}
	
	
	public static void removeSlime(Slime s){
		slimes.remove(s);
	}
	
	public static void makeShield(){
		shield = new Shield(getPlayerX());
	}
	
	public static void breakShield(){
		shield.breakShield();
	}
	public static int getPlayerX(){
		return player.getPlayerX();
	}

}
