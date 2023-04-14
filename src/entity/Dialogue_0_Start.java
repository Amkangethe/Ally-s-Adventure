package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_0_Start extends Entity {
	
	public Dialogue_0_Start(GamePanel gp)
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
		up1 = setUp("/tiles/DarkWoodTiles");
		up2 = setUp("/tiles/DarkWoodTiles");
		upStand = setUp("/tiles/DarkWoodTiles");
		down1 = setUp("/tiles/DarkWoodTiles");
		down2 = setUp("/tiles/DarkWoodTiles");
		downStand = setUp("/tiles/DarkWoodTiles");
		left1 = setUp("/tiles/DarkWoodTiles");
		left2 = setUp("/tiles/DarkWoodTiles");
		leftStand = setUp("/tiles/DarkWoodTiles");
		right1 = setUp("/tiles/DarkWoodTiles");
		right2 = setUp("/tiles/DarkWoodTiles");
		rightStand = setUp("/tiles/DarkWoodTiles");

	}
	public void setDialogue()
	{
		dialogues[0] = "That's crazy... I really turn 18 years old today...";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
