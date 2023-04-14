
package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_7_SistersRoom extends Entity {
	
	public Dialogue_7_SistersRoom(GamePanel gp)
	{
		super(gp);
		solidArea = new Rectangle(0, 0, 32,50);
		direction = "left";
		speed = 1;
		getImage();	
		setDialogue();
		
	}
	
	public void getImage()
	{
		up1 = setUp("/tiles/SistersRug2");
		up2 = setUp("/tiles/SistersRug2");
		upStand = setUp("/tiles/SistersRug2");
		down1 = setUp("/tiles/SistersRug2");
		down2 = setUp("/tiles/SistersRug2");
		downStand = setUp("/tiles/SistersRug2");
		left1 = setUp("/tiles/SistersRug2");
		left2 = setUp("/tiles/SistersRug2");
		leftStand = setUp("/tiles/SistersRug2");
		right1 = setUp("/tiles/SistersRug2");
		right2 = setUp("/tiles/SistersRug2");
		rightStand = setUp("/tiles/SistersRug2");
	}
	public void setDialogue()
	{
		dialogues[0] = "My sisters are at school. I shouldn't go in.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
