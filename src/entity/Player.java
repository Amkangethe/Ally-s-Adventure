package entity;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.MapHandler;
import main.UtilityTool;
import object.SuperObject;

public class Player extends Entity{
	
	
	public GamePanel gp;
	KeyHandler keyH;

	private SuperObject buffer;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		
	super(gp);
		this.gp = gp;
	this.keyH = keyH;
	
	
	screenX = gp.screenWidth/2 - (gp.tileSize/2);
	screenY = gp.screenHeight/2 - (gp.tileSize/2);
	
	solidArea = new Rectangle();
	solidArea.x = 2;
	solidArea.y = 32;
	solidAreaDefaultX = solidArea.x;
	solidAreaDefaultY = solidArea.y;
	solidArea.width = 28;
	solidArea.height = 32;
	
	setDefaultValues();
	getPlayerImage();

	}
	
	public void setDefaultValues()
	{
		worldX = gp.tileSize * 4;
		worldY = gp.tileSize * 3;
		speed = 4;
		direction = "downStand";
	}
	public void getPlayerImage()
	{
		
		
		up1 = setUp("/player/Player_Up_1");
		up2 = setUp("/player/Player_Up_2");
		upStand = setUp("/player/Player_Looking_Up");
		down1 = setUp("/player/Player_Down_1");
		down2 = setUp("/player/Player_Down_2");
		downStand = setUp("/player/Player_Looking_Down");
		left1 = setUp("/player/Player_Left_1");
		left2 = setUp("/player/Player_Left_2");
		leftStand = setUp("/player/Player_Looking_Left");
		right1 = setUp("/player/Player_Right_1");
		right2 = setUp("/player/Player_Right_2");
		rightStand = setUp("/player/Player_Looking_Right");
		
		
		
		WASD = setUp("/player/WASD");
		ESC = setUp("/player/ESC");

		
	}
	
	
	public void update()
	{
		if (keyH.downPressed)
		{
				direction = "down";	
		}
		else if (keyH.upPressed)
		{
			direction = "up";

		}
		else if (keyH.leftPressed)
		{
			direction = "left";
		}
		else if (keyH.rightPressed)
		{
			direction = "right";
		}
		
		
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cpH.Checkpoint(gp);

		
		//CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this, true); //Checks if an object is being touched
		touchObject(objIndex); //Determines which object is being touched
		
		//CHECK NPC COLLISION
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//CHANGES MAP
		int map = MapHandler.mapTransfer(objIndex); //Takes Object number to enter Map Location
		//System.out.println(map); // Prints current map;
		gp.tileM.mapDecider(map);
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false)
		{
			switch(direction)
			{
			case "down":
				worldY += speed;
				keyH.keyReleasedCount = 1;
				break;
			case "up":
				worldY -= speed;	
				keyH.keyReleasedCount = 2;
				break;
			case "left":
				worldX -= speed;
				keyH.keyReleasedCount = 3;
				break;
			case "right":
				worldX += speed;
				keyH.keyReleasedCount = 4;
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
			
		 if (!keyH.downPressed || !keyH.upPressed
					 || !keyH.leftPressed || !keyH.rightPressed)
			 {
				if (keyH.keyReleasedCount == 1)
				{
						direction = "downStand";
				}
				else if (keyH.keyReleasedCount == 2)
				{
					direction = "upStand";
				}
				else if (keyH.keyReleasedCount == 3)
				{
					direction = "leftStand";
				}
				else if (keyH.keyReleasedCount == 4)
				{
					direction = "rightStand";
				}

			 }
			 
			 
		}
		

		

		gp.cpH.Checkpoint(gp);
		
		
	}
	
	
	public void touchObject(int i)
	{
		// If the player is not touching a regular tile
		if (i != 765578)
		{
			
			switch(i)
			{
			case 0: // If player exits bedroom into hallway
				
				checkPointDecider(1); //Checkpoint is 1

				//Sets player's dynamics
				gp.player.worldX = gp.tileSize * 3;
				gp.player.worldY = gp.tileSize * 3;
				gp.player.speed = 4;
				gp.player.direction = "downStand";

				//Sets world screen size
				gp.maxWorldCol = 7;
				gp.maxWorldRow = 12;
				
				
				
				//Removes Bedroom to Hallway door mat
				gp.obj[0].worldX = 300 * gp.tileSize;
				gp.obj[0].worldY = 2 * gp.tileSize;
				

			
				//Door to room 
				gp.obj[1].worldX = 300 * gp.tileSize;
				gp.obj[1].worldY = 2 * gp.tileSize;
				
				//Hallway to living room
				gp.obj[2].worldX = 2 * gp.tileSize;
				gp.obj[2].worldY = 10 * gp.tileSize;
				
				if(checkPointer == 1)
				{
					//Plays Parent's Yelling
					gp.playSELoop(1);
				}
				
					
				//Bed DIALOGUE
				gp.npc[1].worldX = gp.tileSize*300;
				gp.npc[1].worldY = gp.tileSize*2;
					
				//Computer DIALOGUE
				gp.npc[2].worldX = gp.tileSize*300;
				gp.npc[2].worldY = gp.tileSize*2;
					
				//DaBaby Rug DIALOGUE
				if(gp.npc[3] != null)
				{
					gp.npc[3].worldX = gp.tileSize*300;
					gp.npc[3].worldY = gp.tileSize*2;
				}
						
				//Hallway to Bedroom Door DIALOGUE
				gp.npc[4].worldX = gp.tileSize*3;
				gp.npc[4].worldY = gp.tileSize*2;
				
				if(checkPointer != 1) // Once player has reaches certain point, room becomes accessible
				{
					gp.npc[4].worldX = 300 * gp.tileSize;
					gp.npc[4].worldY = 2 * gp.tileSize;
					
					gp.obj[1].worldX = 3 * gp.tileSize;
					gp.obj[1].worldY = 2 * gp.tileSize;
				}
				
			
				//Parent's arguing DIALOGUE
				if(gp.npc[5] != null)
				{
					gp.npc[5].worldX = gp.tileSize*3;
					gp.npc[5].worldY = gp.tileSize*6;
					
				}
				
				//Parent's room DIALOGUE
				gp.npc[6].worldX = gp.tileSize*0;
				gp.npc[6].worldY = gp.tileSize*6;
				
				//Sisters room DIALOGUE
				gp.npc[7].worldX = gp.tileSize*6;
				gp.npc[7].worldY = gp.tileSize*6;
				
				
				
				break;
			case 1: //If Player Enters Bedroom
				
				//Sets player's dynamics
				gp.player.worldX = gp.tileSize * 4;
				gp.player.worldY = gp.tileSize * 6;
				gp.player.speed = 4;
				gp.player.direction = "upStand";

				//Sets world screen size
				gp.maxWorldCol = 8;
				gp.maxWorldRow = 16;
				
				//Set door mat location
				gp.obj[0].worldX = 4 * gp.tileSize;
				gp.obj[0].worldY = 8 * gp.tileSize;
				
				//Set door to bedroom location
				gp.obj[1].worldX = 300 * gp.tileSize;
				gp.obj[1].worldY = 8 * gp.tileSize;
				
				//Set hallway to livingroom location
				gp.obj[2].worldX = 300 * gp.tileSize;
				gp.obj[2].worldY = 8 * gp.tileSize;




				
				//Bed DIALOGUE
				gp.npc[1].worldX = gp.tileSize*5;
				gp.npc[1].worldY = gp.tileSize*3;
				
				//Computer DIALOGUE
				gp.npc[2].worldX = gp.tileSize*1;
				gp.npc[2].worldY = gp.tileSize*3;
				
				//DaBaby Rug DIALOGUE
				if(gp.npc[3] != null)
				{
					gp.npc[3].worldX = gp.tileSize*3;
					gp.npc[3].worldY = gp.tileSize*6;
				}
				
				//Hallway to Bedroom Door DIALOGUE
				gp.npc[4].worldX = gp.tileSize*300;
				gp.npc[4].worldY = gp.tileSize*2;
				
				//Parent's arguing DIALOGUE
				if(gp.npc[5] != null)
				{
					gp.npc[5].worldX = gp.tileSize*300;
					gp.npc[5].worldY = gp.tileSize*5;
					
				}
				
				//Parent's room DIALOGUE
				gp.npc[6].worldX = gp.tileSize*300;
				gp.npc[6].worldY = gp.tileSize*6;
				
				//Sisters room DIALOGUE
				gp.npc[7].worldX = gp.tileSize*300;
				gp.npc[7].worldY = gp.tileSize*6;
			
				

				break;
			case 2: //When play enters living room
			
				checkPointDecider(2); //Checkpoint is 2
				
				if(checkPointer == 2)
				{
					// Stop audio of parent's arguing
					gp.stopSE();
				}
		
				
				
				if(checkPointer != 2) // Once player has reaches certain point, room becomes accessible
				{
					//Living room to Hallway DIALOGUE
					gp.npc[10].worldX = 300 * gp.tileSize;
					gp.npc[10].worldY = 2 * gp.tileSize;
					
					
					
					//Living room to Hallway stairs
					gp.obj[3].worldX = 11 * gp.tileSize;
					gp.obj[3].worldY = 3 * gp.tileSize;
				}
				else if (checkPointer == 2)
				{
					//Living room to Hallway DIALOGUE
					gp.npc[10].worldX = 11 * gp.tileSize;
					gp.npc[10].worldY = 3 * gp.tileSize;
					
					//Living room to Hallway stairs
					gp.obj[3].worldX = 300 * gp.tileSize;
					gp.obj[3].worldY = 3 * gp.tileSize;
				}
				
				//Sets player's dynamics
				gp.player.worldX = gp.tileSize * 10;
				gp.player.worldY = gp.tileSize * 3;
				gp.player.speed = 4;
				gp.player.direction = "leftStand";

			
				//Sets world screen size
				gp.maxWorldCol = 14;
				gp.maxWorldRow = 11;

				
				
				//hallway to living room
				gp.obj[2].worldX = 300 * gp.tileSize;
				gp.obj[2].worldY = 3 * gp.tileSize;
				
				//Living room to neighborhood
				gp.obj[4].worldX = 9 * gp.tileSize;
				gp.obj[4].worldY = 9 * gp.tileSize;
				
				
				
				//Hallway to Bedroom Door DIALOGUE
				gp.npc[4].worldX = gp.tileSize*300;
				gp.npc[4].worldY = gp.tileSize*2;
				
				//Parent's room DIALOGUE
				gp.npc[6].worldX = gp.tileSize*300;
				gp.npc[6].worldY = gp.tileSize*6;
				
				//Sisters room DIALOGUE
				gp.npc[7].worldX = gp.tileSize*300;
				gp.npc[7].worldY = gp.tileSize*6;
				
				//Picture 1 DIALOGUE
				gp.npc[8].worldX = gp.tileSize*7;
				gp.npc[8].worldY = gp.tileSize*2;
				
				//Picture 2 DIALOGUE
				gp.npc[9].worldX = gp.tileSize*8;
				gp.npc[9].worldY = gp.tileSize*2;
				
				
				
				
				break;
			case 3: //If player enters back into hallway from living room
				
				
				gp.player.worldX = gp.tileSize * 3;
				gp.player.worldY = gp.tileSize * 9;
				gp.player.speed = 4;
				gp.player.direction = "rightStand";

				
				gp.maxWorldCol = 7;
				gp.maxWorldRow = 12;
				

				
				//Living room to Hallway stairs
				gp.obj[3].worldX = 300 * gp.tileSize;
				gp.obj[3].worldY = 3 * gp.tileSize;
				
				
				//Living room to neighborhood
				gp.obj[4].worldX = 300 * gp.tileSize;
				gp.obj[4].worldY = 3 * gp.tileSize;
				
				
			
				//Hallway to Bedroom Door DIALOGUE
				gp.npc[4].worldX = gp.tileSize*3;
				gp.npc[4].worldY = gp.tileSize*2;
				
				//Parent's room DIALOGUE
				gp.npc[6].worldX = gp.tileSize*0;
				gp.npc[6].worldY = gp.tileSize*6;
				
				//Sisters room DIALOGUE
				gp.npc[7].worldX = gp.tileSize*6;
				gp.npc[7].worldY = gp.tileSize*6;
				
				//Picture 1 DIALOGUE
				gp.npc[8].worldX = gp.tileSize*300;
				gp.npc[8].worldY = gp.tileSize*2;
				
				//Picture 2 DIALOGUE
				gp.npc[9].worldX = gp.tileSize*300;
				gp.npc[9].worldY = gp.tileSize*2;
				
				//Living room to Hallway DIALOGUE
				gp.npc[10].worldX = 300 * gp.tileSize;
				gp.npc[10].worldY = 2 * gp.tileSize;
				
				break;
			case 4:
				
				gp.player.worldX = gp.tileSize * 3;
				gp.player.worldY = gp.tileSize * 8;
				gp.player.speed = 4;
				gp.player.direction = "rightStand";
				
				
				//World Settings
				gp.maxWorldCol = 11;
				gp.maxWorldRow = 11;
				
				
				//Living room to neighborhood
				gp.obj[4].worldX = 300 * gp.tileSize;
				gp.obj[4].worldY = 3 * gp.tileSize;
				
				
				//Picture 1 DIALOGUE
				gp.npc[8].worldX = gp.tileSize*300;
				gp.npc[8].worldY = gp.tileSize*2;
				
				//Picture 2 DIALOGUE
				gp.npc[9].worldX = gp.tileSize*300;
				gp.npc[9].worldY = gp.tileSize*2;
				
				//Living room to Hallway DIALOGUE
				gp.npc[10].worldX = 300 * gp.tileSize;
				gp.npc[10].worldY = 2 * gp.tileSize;
				
				
				
				
				break;
			}
				
			
			

		}

	}
	public static int checkPointer = 0;
	
	public void checkPointDecider(int decider)
	{
		if(checkPointer < decider)
		{
			checkPointer = decider;
		}
		
		System.out.println(checkPointer);
		
		if(checkPointer == 1)
		{
			
			
			
			
		}

		

	}

	
	public void interactNPC(int i)
	{
		if (i != 999999)
		{

			if(i == 0)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[0].speak();
				gp.npc[0] = null;
			}
			else if (i == 1)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[1].speak();
				
				
			}
			
			else if (i == 2)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[2].speak();
				
				
			}
			else if (i == 3)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[3].speak();
				gp.npc[3] = null;	
			}
			else if (i == 4)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[4].speak();
			}
			else if (i == 5)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[5].speak();
				gp.npc[5] = null;
			}
			
			else if (i == 6)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[6].speak();
			}
			else if (i == 7)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[7].speak();
			}
			else if (i == 8)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[8].speak();
			}
			else if (i == 9)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[9].speak();
			}
			else if (i == 10)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[10].speak();
			}
			
			
			
			
		}
		
	}
	public void draw(Graphics2D g2)
	{
		
		

		//g2.setColor(Color.white);
		//g2.fillRect(x ,y , gp.tileSize ,gp.tileSize);
		
		BufferedImage image = null;
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
		g2.drawImage(image, screenX, screenY, gp.XPlayertileSize, gp.YPlayertileSize, null);

	}

	public void removeKey() {
		// Buffer object to hold the key
		buffer = gp.obj[0];
		// Removes the key
		gp.obj[0] = null;
	}
	
	public void removeDoor() {
		// Buffer object to hold the key
		buffer = gp.obj[1];
		// Removes the key
		gp.obj[1] = null;
	}
	
	public void addKey() {
		// Make sure the buffer is not null
		if (Objects.nonNull(buffer))
		{
			// Add the key back
			gp.obj[0] = buffer;
		}
	}
	
	public void addDoor() {
		// Make sure the buffer is not null
		if (Objects.nonNull(buffer))
		{
			// Add the key back
			gp.obj[1] = buffer;
		}
	}
}