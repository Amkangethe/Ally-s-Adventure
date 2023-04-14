package entity;


import java.awt.Rectangle;

import main.GamePanel;

public class Dialogue_9_LivingroomPicture2 extends Entity {
	
	public Dialogue_9_LivingroomPicture2(GamePanel gp)
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
		up1 = setUp("/tiles/Picture4");
		up2 = setUp("/tiles/Picture4");
		upStand = setUp("/tiles/Picture4");
		down1 = setUp("/tiles/Picture4");
		down2 = setUp("/tiles/Picture4");
		downStand = setUp("/tiles/Picture4");
		left1 = setUp("/tiles/Picture4");
		left2 = setUp("/tiles/Picture4");
		leftStand = setUp("/tiles/Picture4");
		right1 = setUp("/tiles/Picture4");
		right2 = setUp("/tiles/Picture4");
		rightStand = setUp("/tiles/Picture4");
	}
	public void setDialogue()
	{
		dialogues[0] = "If I could kiss myself...I would!";


	}
	
	public void speak()
	{
		super.speak();
		
	}
	
	
	
	
	

}
