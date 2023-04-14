package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_LivingroomToHallway extends SuperObject {
	GamePanel gp;
	public OBJ_LivingroomToHallway(GamePanel gp)
	{
		super(gp);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/LivingroomStairs3.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
		//collision = true;

	}
}
