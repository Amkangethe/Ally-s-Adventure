package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_3_DaBabyRug extends Entity {
	
	public Dialogue_3_DaBabyRug(GamePanel gp)
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
		up1 = setUp("/tiles/DaBabyRug5");
		up2 = setUp("/tiles/DaBabyRug5");
		upStand = setUp("/tiles/DaBabyRug5");
		down1 = setUp("/tiles/DaBabyRug5");
		down2 = setUp("/tiles/DaBabyRug5");
		downStand = setUp("/tiles/DaBabyRug5");
		left1 = setUp("/tiles/DaBabyRug5");
		left2 = setUp("/tiles/DaBabyRug5");
		leftStand = setUp("/tiles/DaBabyRug5");
		right1 = setUp("/tiles/DaBabyRug5");
		right2 = setUp("/tiles/DaBabyRug5");
		rightStand = setUp("/tiles/DaBabyRug5");

	}
	public void setDialogue()
	{
		dialogues[0] = "Keep it a buck...I need a new rug...";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
