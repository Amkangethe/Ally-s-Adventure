package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_BedroomToHallway extends SuperObject {
	GamePanel gp;
	public OBJ_BedroomToHallway(GamePanel gp)
	{
		super(gp);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Rug2.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
}
