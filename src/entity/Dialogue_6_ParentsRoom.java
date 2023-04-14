package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_6_ParentsRoom extends Entity {
	
	public Dialogue_6_ParentsRoom(GamePanel gp)
	{
		super(gp);
		solidArea = new Rectangle(0, 0, 50,50);
		direction = "left";
		speed = 1;
		getImage();	
		setDialogue();
		
	}
	
	public void getImage()
	{
		up1 = setUp("/tiles/ParentRug1");
		up2 = setUp("/tiles/ParentRug1");
		upStand = setUp("/tiles/ParentRug1");
		down1 = setUp("/tiles/ParentRug1");
		down2 = setUp("/tiles/ParentRug1");
		downStand = setUp("/tiles/ParentRug1");
		left1 = setUp("/tiles/ParentRug1");
		left2 = setUp("/tiles/ParentRug1");
		leftStand = setUp("/tiles/ParentRug1");
		right1 = setUp("/tiles/ParentRug1");
		right2 = setUp("/tiles/ParentRug1");
		rightStand = setUp("/tiles/ParentRug1");

	}
	public void setDialogue()
	{
		dialogues[0] = "Don't think I should talk to them right now... maybe after work.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
