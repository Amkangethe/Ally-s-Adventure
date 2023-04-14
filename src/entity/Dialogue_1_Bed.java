package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_1_Bed extends Entity {
	
	public Dialogue_1_Bed(GamePanel gp)
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
		
		
		up1 = setUp("/tiles/Bed3");
		up2 = setUp("/tiles/Bed3");
		upStand = setUp("/tiles/Bed3");
		down1 = setUp("/tiles/Bed3");
		down2 = setUp("/tiles/Bed3");
		downStand = setUp("/tiles/Bed3");
		left1 = setUp("/tiles/Bed3");
		left2 = setUp("/tiles/Bed3");
		leftStand = setUp("/tiles/Bed3");
		right1 = setUp("/tiles/Bed3");
		right2 = setUp("/tiles/Bed3");
		rightStand = setUp("/tiles/Bed3");

	}
	public void setDialogue()
	{
		dialogues[0] = "I don't need to sleep.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
