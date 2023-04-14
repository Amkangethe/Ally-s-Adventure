package entity;


import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Mother_Home extends Entity {
	
	
	


	
	public NPC_Mother_Home(GamePanel gp)
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
		
		
		up1 = setUp("/player/Player_Up_1");
		up2 = setUp("/player/Player_Up_2");
		upStand = setUp("/player/Player_Looking_Up");
		down1 = setUp("/player/Player_Down_1");
		down2 = setUp("/player/Player_Down_2");
		downStand = setUp("/player/Player_Looking_Down");
		left1 = setUp("/player/Player_Left_1");
		left2 = setUp("/player/Player_Left_1");
		leftStand = setUp("/player/Player_Looking_Left");
		right1 = setUp("/player/Player_Right_1");
		right2 = setUp("/player/Player_Right_2");
		rightStand = setUp("/player/Player_Looking_Right");

	}
	
	
	public void setDialogue()
	{
		dialogues[0] = "I should go to my work";
		dialogues[1] = "But i dont know what to do";


	}
	
	public void speak()
	{		
		super.speak();
	}
	public void setAction()
	{
		actionLockCounter++;
		
		if(actionLockCounter == 120)
		{
			Random random = new Random();
			int i = random.nextInt(100)+1; //pick up a number
			
			if(i <= 25)
			{
				direction = "up";
			}
			if(i > 25 && i <= 50)
			{
				direction = "down";
			}
			if(i > 50 && i <= 75)
			{
				direction = "left";
			}
			if(i > 75 && i <= 100)
			{
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
		
	}
	
}
	
	
	
	
	


