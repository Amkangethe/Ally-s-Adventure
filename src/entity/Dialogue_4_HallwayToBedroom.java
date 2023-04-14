package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_4_HallwayToBedroom extends Entity {
	
	public Dialogue_4_HallwayToBedroom(GamePanel gp)
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
		
		
		up1 = setUp("/tiles/BottomDoor");
		up2 = setUp("/tiles/BottomDoor");
		upStand = setUp("/tiles/BottomDoor");
		down1 = setUp("/tiles/BottomDoor");
		down2 = setUp("/tiles/BottomDoor");
		downStand = setUp("/tiles/BottomDoor");
		left1 = setUp("/tiles/BottomDoor");
		left2 = setUp("/tiles/BottomDoor");
		leftStand = setUp("/tiles/BottomDoor");
		right1 = setUp("/tiles/BottomDoor");
		right2 = setUp("/tiles/BottomDoor");
		rightStand = setUp("/tiles/BottomDoor");

	}
	public void setDialogue()
	{
		dialogues[0] = "I don't need to go to my room. I need to go to work.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
