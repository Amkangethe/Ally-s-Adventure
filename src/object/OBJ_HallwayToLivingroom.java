package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_HallwayToLivingroom extends SuperObject {
	GamePanel gp;
	public OBJ_HallwayToLivingroom(GamePanel gp)
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
	}
}