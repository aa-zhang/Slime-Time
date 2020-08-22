import javax.swing.*;
public class SlimeTime{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Slime Time");	//sets up the frame
		frame.setSize(1750,1000);				//sizes it
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //activates the close icon
		frame.setResizable(false);				//prevents resizing by users
		frame.add(new GameFrame());			//creates the game window itself
		frame.setVisible(true);	
	}
}

// POWER UP IDEA LIST
// - slow down balls

//OTHER IDEAS
// Menu and retry
// Survival
// Score (maybe store)
