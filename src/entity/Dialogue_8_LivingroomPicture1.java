package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_8_LivingroomPicture1 extends Entity {
	
	public Dialogue_8_LivingroomPicture1(GamePanel gp)
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
		up1 = setUp("/tiles/Picture3");
		up2 = setUp("/tiles/Picture3");
		upStand = setUp("/tiles/Picture3");
		down1 = setUp("/tiles/Picture3");
		down2 = setUp("/tiles/Picture3");
		downStand = setUp("/tiles/Picture3");
		left1 = setUp("/tiles/Picture3");
		left2 = setUp("/tiles/Picture3");
		leftStand = setUp("/tiles/Picture3");
		right1 = setUp("/tiles/Picture3");
		right2 = setUp("/tiles/Picture3");
		rightStand = setUp("/tiles/Picture3");
	}
	public void setDialogue()
	{
		dialogues[0] = "God I'm handsome...God took his MIGHTY time with me!";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
