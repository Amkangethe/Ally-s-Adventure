package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue1_0_LivingroomToHallway extends Entity {
	
	public Dialogue1_0_LivingroomToHallway(GamePanel gp)
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
		up1 = setUp("/tiles/LivingroomStairs3");
		up2 = setUp("/tiles/LivingroomStairs3");
		upStand = setUp("/tiles/LivingroomStairs3");
		down1 = setUp("/tiles/LivingroomStairs3");
		down2 = setUp("/tiles/LivingroomStairs3");
		downStand = setUp("/tiles/LivingroomStairs3");
		left1 = setUp("/tiles/LivingroomStairs3");
		left2 = setUp("/tiles/LivingroomStairs3");
		leftStand = setUp("/tiles/LivingroomStairs3");
		right1 = setUp("/tiles/LivingroomStairs3");
		right2 = setUp("/tiles/LivingroomStairs3");
		rightStand = setUp("/tiles/LivingroomStairs3");
	}
	public void setDialogue()
	{
		dialogues[0] = "I don't need to go upstairs. I need to go to work.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
