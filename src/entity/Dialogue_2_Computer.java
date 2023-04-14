package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_2_Computer extends Entity {
	
	public Dialogue_2_Computer(GamePanel gp)
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
		
		
		up1 = setUp("/tiles/SeatTile");
		up2 = setUp("/tiles/SeatTile");
		upStand = setUp("/tiles/SeatTile");
		down1 = setUp("/tiles/SeatTile");
		down2 = setUp("/tiles/SeatTile");
		downStand = setUp("/tiles/SeatTile");
		left1 = setUp("/tiles/SeatTile");
		left2 = setUp("/tiles/SeatTile");
		leftStand = setUp("/tiles/SeatTile");
		right1 = setUp("/tiles/SeatTile");
		right2 = setUp("/tiles/SeatTile");
		rightStand = setUp("/tiles/SeatTile");

	}
	public void setDialogue()
	{
		dialogues[0] = "I don't need to use my computer.";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
