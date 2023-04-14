package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_HallwayToBedroom extends SuperObject{
	GamePanel gp;
	public OBJ_HallwayToBedroom(GamePanel gp)
	{
		super(gp);

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/BottomDoor.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		collision = true;
	}

}
