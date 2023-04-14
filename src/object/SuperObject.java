package object;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics2D;


import main.GamePanel;
import main.UtilityTool;
public class SuperObject {
	
	GamePanel gp;
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int SolidAreaDefaultX = 0;
	public int SolidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	public String dialogues[] = new String[20];
	public static int dialogueIndex = 0;
	
	public SuperObject(GamePanel gp)
	{
		this.gp = gp;
		
	}
	public void speak()
	{
		if(dialogues[dialogueIndex] == null)
		{
			dialogueIndex = 0;
			gp.gameState = gp.playState;
		}

	
		gp.ui.currentDialogue = dialogues[dialogueIndex];


		
			


		switch(gp.player.direction)
		{
		case "up":
			gp.player.direction = "upStand";

			break;
		case "down":
			gp.player.direction = "downStand";
			break;
		case "left":
			gp.player.direction = "leftStand";

			break;
		case "right":
			gp.player.direction = "rightStand";
			break;
		}
	}
	
	public void draw(Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
		{
			g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);

		}
			
			


	}
}