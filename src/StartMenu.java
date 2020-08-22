import java.awt.Graphics2D;
import java.awt.Image;			//for setting up images
import javax.swing.ImageIcon;



public class StartMenu{
	private ImageIcon ic;
	
	public StartMenu(){
		ic= new ImageIcon("background2.png");
		Image image = ic.getImage(); // transform it 
		Image newimg = image.getScaledInstance(1750, 975,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ic = new ImageIcon(newimg);
	}

	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getStartMenuImg(),0,0,null);
	}
	
	public Image getStartMenuImg(){
		return ic.getImage();
	}

	

}