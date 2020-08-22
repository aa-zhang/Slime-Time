import java.awt.event.KeyAdapter;  
import java.awt.event.KeyEvent;


public class KeyAdapt extends KeyAdapter {
	Player p;   //this Class has a variable which will be the Ball so all keypress
                     //handling happens in Ball Class because of the variable’s Ball type
  
	public KeyAdapt(Player player){ //the ball is received from GameFrame after Ball is created
		p=player;   //this sets the sent ball as the one being key controlled
	}
	
	public void keyPressed(KeyEvent e){
		p.keyPressed(e);    //method from Ball Class dictates reaction to keystrokes

	}
	public void keyReleased(KeyEvent e){
		p.keyReleased(e);    //method from Ball Class dictates reaction to keystrokes

	}
	 
}