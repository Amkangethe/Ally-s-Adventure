package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject{
	GamePanel gp;
	public OBJ_Boots(GamePanel gp)
	{
		super(gp);

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Stairs4.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		setDialogue();
	}
	
	public void setDialogue()
	{
		dialogues[0] = "I should go to my work";
		dialogues[1] = "So you've come to \neat me?";
		dialogues[2] = "Well";
		dialogues[3] = "So be it";

	}
	
	public void speak()
	{
		super.speak();
		
	}
	
}

