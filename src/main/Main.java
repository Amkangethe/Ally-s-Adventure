package main;




import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {
	
	

	
	public static void main(String [] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setResizable(false);
		window.setTitle("Ally's Adventure");

		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		gamePanel.setupGame();
		gamePanel.startGameThread();
		ImageIcon image = new ImageIcon("res/MeetTheAuthor.jpg");
		window.setIconImage(image.getImage());
		
	}

	

}
