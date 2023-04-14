package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_LivingroomToNeighborhood extends SuperObject {
	GamePanel gp;
	public OBJ_LivingroomToNeighborhood(GamePanel gp)
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
		//collision = true;

	}
}
