package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.MapHandler;
import main.UtilityTool;
import java.awt.Graphics2D;

public class Entity{
	
	public GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, upStand, down1, down2, downStand, left1, left2, leftStand, right1, right2, rightStand, WASD, ESC;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
    public String dialogues[] = new String[20];
	public static int dialogueIndex = 0;
	public static String directionnn = "";
	public Entity(GamePanel gp)
	{
		this.gp= gp;
	}
	public void setAction()
	{
		
	}
	public void speak()
	{
		if(dialogues[dialogueIndex] == null)
		{
			dialogueIndex = 0;
			gp.ui.currentDialogue = "monkey";

			//gp.gameState = gp.playState;
		}
		
			//System.out.println(dialogueIndex);
			gp.ui.currentDialogue = dialogues[dialogueIndex];
			//System.out.println(dialogueIndex);
		

		switch(gp.player.direction)
		{
		case "up":
			direction = "downStand";
			gp.player.direction = "upStand";
			break;
		case "down":
			direction = "upStand";
			gp.player.direction = "downStand";
			break;
		case "left":
			direction = "rightStand";
			gp.player.direction = "leftStand";

			break;
		case "right":
			direction = "leftStand";
			gp.player.direction = "rightStand";
			break;
		}
	}
	
	
	public void update()
	{
		
		
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		//CHECK TILE COLLISION
				collisionOn = false;
				gp.cChecker.checkTile(this);

				//IF COLLISION IS FALSE, PLAYER CAN MOVE
				if(collisionOn == false)
				{
					switch(direction)
					{
					case "down":
						worldY += speed;
						break;
					case "up":
						worldY -= speed;	
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
					}
				
				}
				
				
				spriteCounter++;
				if(spriteCounter > 10)
				{
					if(spriteNum == 1)
					{
						spriteNum = 2;
					}
					else if(spriteNum == 2)
					{
						spriteNum = 3;
					}
					else if(spriteNum == 3)
					{
						spriteNum = 4;
					}
					else if(spriteNum == 4)
					{
						spriteNum = 1;
					}
					spriteCounter = 0;
					
				}
		
	}
	private void NPC_Mother_Home() {
		// TODO Auto-generated method stub
		
	}
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
		{
			switch(direction) {
			case "up":
				if(spriteNum == 1)
				{
					image = upStand;
				}
				if(spriteNum == 2)
				{
					image = up2;
				}
				if(spriteNum == 3)
				{
					image = upStand;
				}
				if(spriteNum == 4)
				{
					image = up1;
				}
				break;
			case "upStand":
				if(spriteNum == 1)
				{
					image = upStand;
				}
				if(spriteNum == 2)
				{
					image = upStand;
				}	
				if(spriteNum == 3)
				{
					image = upStand;
				}
				if(spriteNum == 4)
				{
					image = upStand;
				}	
				break;
			case "down":
				if(spriteNum == 1)
				{
					image = downStand;
				}
				if(spriteNum == 2)
				{
					image = down2;
				}
				if(spriteNum == 3)
				{
					image = downStand;
				}
				if(spriteNum == 4)
				{
					image = down1;
				}
				
				break;
			case "downStand":
				if(spriteNum == 1)
				{
					image = downStand;
				}
				if(spriteNum == 2)
				{
					image = downStand;
				}	
				if(spriteNum == 3)
				{
					image = downStand;
				}
				if(spriteNum == 4)
				{
					image = downStand;
				}	
				break;
			case "left":
				if(spriteNum == 1)
				{
					image = leftStand;
				}
				if(spriteNum == 2)
				{
					image = left2;
				}	
				if(spriteNum == 3)
				{
					image = leftStand;
				}
				if(spriteNum == 4)
				{
					image = left1;
				}	
				break;
			case "leftStand":
				if(spriteNum == 1)
				{
					image = leftStand;
				}
				if(spriteNum == 2)
				{
					image = leftStand;
				}	
				if(spriteNum == 3)
				{
					image = leftStand;
				}
				if(spriteNum == 4)
				{
					image = leftStand;
				}	
				break;
			case "right":
				if(spriteNum == 1)
				{
					image = right2;
				}
				if(spriteNum == 2)
				{
					image = rightStand;
				}	
				if(spriteNum == 3)
				{
					image = right1;
				}
				if(spriteNum == 4)
				{
					image = rightStand;
				}	
				break;
			case "rightStand":
				if(spriteNum == 1)
				{
					image = rightStand;
				}
				if(spriteNum == 2)
				{
					image = rightStand;
				}	
				if(spriteNum == 3)
				{
					image = rightStand;
				}
				if(spriteNum == 4)
				{
					image = rightStand;
				}	
				break;
			}
			
			g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);

		}
			
	}
	
	public BufferedImage setUp(String imagePath)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}

}
