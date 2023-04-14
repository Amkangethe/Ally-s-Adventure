
package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_5_ParentsArguing extends Entity {
	
	public Dialogue_5_ParentsArguing(GamePanel gp)
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
		up1 = setUp("/tiles/WoodTiles");
		up2 = setUp("/tiles/WoodTiles");
		upStand = setUp("/tiles/WoodTiles");
		down1 = setUp("/tiles/WoodTiles");
		down2 = setUp("/tiles/WoodTiles");
		downStand = setUp("/tiles/WoodTiles");
		left1 = setUp("/tiles/WoodTiles");
		left2 = setUp("/tiles/WoodTiles");
		leftStand = setUp("/tiles/WoodTiles");
		right1 = setUp("/tiles/WoodTiles");
		right2 = setUp("/tiles/WoodTiles");
		rightStand = setUp("/tiles/WoodTiles");

	}
	public void setDialogue()
	{
		dialogues[0] = "Oh nah?? Why are they yelling?";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
