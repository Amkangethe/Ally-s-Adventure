package main;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.time.Clock;

import object.OBJ_BedroomToHallway;
public class UI {
	
	
	GamePanel gp;
	Graphics2D g2;
	BufferedImage keyImage;
	Font maruMonica;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter;
	static double startTimeSeconds = 0;
	static double pauseTimeSeconds = 0;
	static double playTimeSeconds = 0;
	static double playTimeMinutes = 0;
	static double playTimeHours = 0;
	public String currentDialogue = "";
	public String currentGameplayText = "";
	public int commandTitleNum = 0;
	public int commandPauseNum = 0;
	public int titleScreenState = 0;
	public int pauseScreenState = 0;



	DecimalFormat dFormat = new DecimalFormat("#00");
	
	
	//Center Values
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int centerX = screenSize.width/2;
	int centerY = screenSize.height/2;
	
	
	public UI(GamePanel gp)
	{
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//OBJ_BedroomToHallway bedtohall = new OBJ_BedroomToHallway(gp);
		//keyImage = bedtohall.image;

		
	}
	
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
	}
	
	
	
	public void draw(Graphics2D g2) 
	{
		
		
		
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		
		
		//Running game Time
		startTimeSeconds += (double)1/60;

		if(gp.gameState == gp.titleState)
		{
			drawTitleScreen();
			


		}
		if(gp.gameState == gp.playState)
		{
			//gp.music.stop();
			//gp.music.setFile(0);
			//Do playState stuff later
			
			

		}
		if(gp.gameState == gp.pauseState)
		{
			 
			playTimeSeconds = (startTimeSeconds-pauseTimeSeconds);
			
			if(playTimeSeconds > 59.3)
			{
				
				playTimeMinutes += 1;
				playTimeSeconds = 0;
				startTimeSeconds = 0;
				pauseTimeSeconds = 0;
			}
			
			if(playTimeMinutes > 59.3)
			{
				
				playTimeHours += 1;
				playTimeSeconds = 0;
				startTimeSeconds = 0;
				pauseTimeSeconds = 0;
				playTimeMinutes = 0;
			}
			
			g2.setColor(Color.white);
			g2.drawRect(0, 0, gp.screenWidth ,gp.screenHeight);
			g2.setColor(new Color(255, 255, 255, 80));
			g2.fillRect(0, 0, gp.screenWidth ,gp.screenHeight);
			
			pauseTimeSeconds += (double)1/60;
			
			
			
			//Display Paused Screen
			drawPauseScreen();

		}
		
	
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState)
		{
			drawDialogueScreen(dialogueNum);
		   

		}
		
		

		
		
		//MESSAGE
		if(messageOn == true)
		{
			
			
			g2.setFont(g2.getFont().deriveFont(30));
			g2.drawString(message, centerX, centerY);
			messageCounter++;
			
			gp.playSE(5);
			if(messageCounter > 1)
			{
				messageCounter = 0;
				messageOn = false;
				
				
			}
		}

	}
	
	public void drawTitleScreen()
	{
		//g2.setColor(new Color(0, 0, 0));
		//g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		 if(titleScreenState == 0)
		 {
			// TITLE NAME
			    g2.setFont(maruMonica);
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
				String text = "Ally's Adventure";
				int x = getXforCenteredText(text);
				int y = (gp.screenHeight/2) - 280;
				
				//Shadow text
				g2.setColor(Color.GRAY);
				g2.drawString(text,x+4,y+4);
				
				//Main text
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				
				//Chapter One Text
				g2.setFont(maruMonica);
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
				text = "Chapter One";
				x = getXforCenteredText(text);
				y += gp.tileSize*2;
				
				//Shadow text
				g2.setColor(new Color(99, 1, 9));
				g2.drawString(text, x+4,y+3);
				
				//Main text
				g2.setColor(new Color(197, 2, 18));
				g2.drawString(text, x,y);
				
				//Image
				x = (gp.screenWidth/2) - 48;
				y += gp.tileSize;
				g2.drawImage(gp.player.downStand, x ,y, gp.XPlayertileSize*3, gp.YPlayertileSize*3, null );

				
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			    g2.setColor(new Color(255, 255, 255));
				text = "START";
				x = getXforCenteredText(text);
				y += gp.tileSize*6;	
				g2.drawString(text,x,y);
				if(commandTitleNum == 0)
				{
					g2.drawString(">", x - gp.tileSize, y);
				}
				
				
	
				text = "QUIT";
				x = getXforCenteredText(text);
				y += gp.tileSize;	
				g2.drawString(text,x,y);
				if(commandTitleNum == 1)
				{
					g2.drawString(">", x - gp.tileSize, y);
				}
		 }
		 else if(titleScreenState == 1) //Controls screen
		 {
			 g2.setFont(maruMonica);
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD | Font.ITALIC, 96F));
				String text = "CONTROLS";
				int x = getXforCenteredText(text);
				int y = (gp.screenHeight/2) - 280;
				
				//Shadow controls text
				g2.setColor(Color.GRAY);
				g2.drawString(text,x+4,y+4);
				
				//Main controls text
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				
				//Movement Text
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
				g2.setColor(new Color(255, 255, 255));
				text = "MOVEMENT  - ";
				x = getXforCenteredText(text);
				y += gp.tileSize;	
				g2.drawString(text,x- gp.tileSize*3,y+80);
				 
				//WASD Image
				x = ((gp.screenWidth/2) + gp.tileSize);
				g2.drawImage(gp.player.WASD, x ,y - 5, (48)*6,(16)*6, null );
				
				//Pause Text
				text = "PAUSE";
				x = getXforCenteredText(text);
				y += (gp.tileSize) + 20;	
				g2.drawString(text,(x- gp.tileSize*3) - 30,y+110);
				
				//Pause Text
				text = "    -";
				g2.drawString(text,x,y+110);
				
				//WASD Image
				x = ((gp.screenWidth/2) + gp.tileSize);
				g2.drawImage(gp.player.ESC, (x+gp.tileSize)+20 ,y+25, (48)*6,(16)*6, null );
				
				
				
				
				//Continue text
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
				text = "Press the Spacebar to Continue...";
				x = getXforCenteredText(text);
				y = gp.tileSize*14;	
				g2.drawString(text,(x+ gp.tileSize*9),y+30);
				 
				
				
		 }
		 else if(titleScreenState == 2)
		 {
			 g2.setFont(maruMonica);
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD | Font.ITALIC, 96F));
				String text = "GAMEPLAY";
				int x = getXforCenteredText(text);
				int y = (gp.screenHeight/2) - 280;
				
				//Shadow controls text
				g2.setColor(Color.GRAY);
				g2.drawString(text,x+4,y+4);
				
				//Main controls text
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				
			    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
			    text = "EXPLORE AND INTERACT WITH THE WORLD AND CHARACTERS";
			    x = getXforCenteredText(text);
				y += gp.tileSize*2;
				g2.drawString(text,x,y);
				
				text = "PROGRESS THROUGH THE GAME AND UNCOVER SECRETS AND INFORMATION";
			    x = getXforCenteredText(text);
				y += gp.tileSize;
				g2.drawString(text,x,y);
				
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
				g2.setColor(new Color(197, 2, 18));
				text = "ACTIONS YOU MAKE WILL DETERMINE THE STORY OF THE GAME.";
			    x = getXforCenteredText(text);
				y += gp.tileSize;
				g2.drawString(text,x,y);
				
			    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50));
				g2.setColor(new Color(255, 255, 255));
				text = "YOU MAY ENCOUNTER ITEMS THAT MAY HELP YOUR STORY";
			    x = getXforCenteredText(text);
				y += gp.tileSize*2;
				g2.drawString(text,x,y);
				
				text = "OR";
			    x = getXforCenteredText(text);
				y += gp.tileSize;
				g2.drawString(text,x,y);
				
			    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
				g2.setColor(new Color(197, 2, 18));
				text = "SABOTAGE YOUR STORY";
			    x = getXforCenteredText(text);
				y += gp.tileSize;
				g2.drawString(text,x,y);
				
				
				g2.setColor(new Color(218,165,32));
				text = "*PICK THE RIGHT CHOICE*";
			    x = getXforCenteredText(text);
				y += gp.tileSize*2;
				g2.drawString(text,x,y);
				
				//Continue text
				g2.setColor(new Color(255,255,255));
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
				text = "Press the Enter Key to Start...";
				x = getXforCenteredText(text);
				y = gp.tileSize*14;	
				g2.drawString(text,(x+ gp.tileSize*9),y+30);

				
				
				
		 }
		
	}
		public int dialogueNum = 0;

		//Dialogue Window
		public void drawDialogueScreen(int num)
		{
			int x = gp.tileSize*2;
			int y = gp.tileSize/2;
			int width = gp.screenWidth - (gp.tileSize*4);
			int height = gp.tileSize*5;
			
			drawSubWindow(x, y, width, height);
			
			if(num == 0)
			{
				g2.setColor(new Color(255,255,255));
				 g2.setFont(new Font("Times New Roman",Font.PLAIN, 40));
					x += gp.tileSize;
					y += gp.tileSize*1.5;
					
					for (String line: currentDialogue.split("\n"))
					{
						g2.drawString(line,x ,y);
						y+=40;

					}
					
			}
			else if (num == 1)
			{
				g2.setColor(new Color(255,0,255));
				g2.setFont(new Font("Times New Roman",Font.PLAIN, 40));
				x += gp.tileSize;
				y += gp.tileSize*1.5;
				
				for (String line: currentDialogue.split("\n"))
				{
					g2.drawString(line,x ,y);
					y+=40;

				}
			}
		   
		}
		
		public void drawSubWindow(int x, int y, int width, int height)
		{
			Color c = new Color(0, 0,0, 200);
			g2.setColor(c);
			g2.fillRoundRect(x,y,width, height, 35, 35);
			
			c = new Color(255,255,255);
			g2.setColor(c);
			g2.setStroke(new BasicStroke(5));
			g2.drawRoundRect(x+5,y +5,width-10, height-10, 25, 25);

		}
	
		
		public void drawPauseScreen()
		{
		    g2.setFont(new Font("Ariel",Font.PLAIN, 40));

			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
			String text = "PAUSED";
			int x = getXforCenteredText(text);
			int y = (gp.screenHeight/2) - (gp.tileSize*5);
			g2.drawString(text, x-5, y);
			
			//SHADOW
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
			g2.drawString(text, x, y);
			
			if(pauseScreenState == 0)
			{
				
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2) - (gp.tileSize*5);
				
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*10)-gp.tileSize/3, (gp.tileSize*6) + gp.tileSize/3, (gp.tileSize*3)+(gp.tileSize/8),30);
				
				//SHADOW TEXT RESUME
				text = "RESUME";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize*4;	
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT RESUME
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 0)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT RESUME
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*10)-gp.tileSize/3, (gp.tileSize*7) + gp.tileSize/3, (gp.tileSize*3)+(gp.tileSize/5),30);
				
				//SHADOW TEXT OPTIONS
				text = "OPTIONS";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize;	
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT OPTIONS
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 1)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT OPTIONS
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*10)+15, (gp.tileSize*8) + gp.tileSize/3, (gp.tileSize*2)+5,30);
				
				//SHADOW TEXT QUIT
				text = "QUIT";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT QUIT
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 2)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT QUIT
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
			}
			else if(pauseScreenState == 1)
			{
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2) - (gp.tileSize*5);
				
				g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*11), (gp.tileSize*6) + gp.tileSize/3, (gp.tileSize*4)+25,30);
				
				//SHADOW TEXT RESUME
				text = "GAME STATS";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize*4;	
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT RESUME
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 0)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT RESUME
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*11) + 10, (gp.tileSize*7) + gp.tileSize/3, (gp.tileSize*5)+25,30);
				
				//SHADOW TEXT OPTIONS
				text = "GAME SETTINGS";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize;	
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT OPTIONS
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 1)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT OPTIONS
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - gp.tileSize*9)-5, (gp.tileSize*8) + gp.tileSize/3, (gp.tileSize*3)+10,30);
				
				//SHADOW TEXT QUIT
				text = "CREDITS";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				//MAIN TEXT QUIT
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 2)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT QUIT
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
				
				
				
				//SHADOW TEXT QUIT
				text = "GO BACK";
				x = (getXforCenteredText(text)) - gp.tileSize*10;
				y += gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text,x-4,y);
				
				g2.setColor(new Color(10, 24, 64, 100));
				g2.fillRect(((getXforCenteredText(text)) - (gp.tileSize*10)-12), (gp.tileSize*9) + gp.tileSize/3, (gp.tileSize*3)+17,30);
				
				//MAIN TEXT QUIT
				g2.setColor(Color.white);
				g2.drawString(text,x,y);
				if(commandPauseNum == 3)
				{
					g2.setColor(Color.black);
					g2.drawString(">", (x - gp.tileSize)-4, y);
					g2.setColor(Color.white);
					g2.drawString(">", x - gp.tileSize, y);
					
					//MAIN TEXT QUIT
					g2.setColor(new Color(10, 24, 64));
					g2.drawString(text,x,y);
				}
			}
			else if(pauseScreenState == 2)
			{
				g2.setColor(new Color(0, 0, 0, 250));
				g2.fillRect(gp.screenWidth/6, (gp.screenHeight/4) - (gp.tileSize/2), (gp.screenWidth/6)*4, (gp.screenHeight/4)*3);
				
				//Display Total RunTime
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
				
				g2.setColor(new Color(105,105,105));
				g2.drawString("Time Played:" + dFormat.format(playTimeHours) + "h "
										     + dFormat.format(playTimeMinutes) + "m "
											 + dFormat.format(playTimeSeconds)+ "s ", ((gp.screenWidth/2)- (gp.tileSize * 4)) - 18, ((gp.screenHeight/4) - (gp.tileSize/2)) + gp.tileSize);
				
				g2.setColor(Color.white);
				g2.drawString("Time Played:" + dFormat.format(playTimeHours) + "h "
										     + dFormat.format(playTimeMinutes) + "m "
											 + dFormat.format(playTimeSeconds)+ "s ", ((gp.screenWidth/2)- (gp.tileSize * 4)) - 20, ((gp.screenHeight/4) - (gp.tileSize/2)) + gp.tileSize);
				
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));

				//Completed Game Text
				g2.setColor(Color.white);
				text = "Completed Game: " + gp.cpH.gameCompleted +"%";
				y = (gp.screenHeight/4) - (gp.tileSize/2);
				g2.drawString(text, (gp.screenWidth/6) + (gp.tileSize/4),y + (gp.tileSize*2));
				
				
				
				Clock c = Clock.systemDefaultZone();    
				 
				//Current Location Text
				text = ("Current Location: " + c.getZone());
				y += gp.tileSize/2;
				g2.drawString(text, (gp.screenWidth/6) + (gp.tileSize/4),y + (gp.tileSize*2));
				
				//Current FPS Text
				text = ("Current FPS/Hz: " + gp.FPS);
				y += gp.tileSize/2;
				g2.drawString(text, (gp.screenWidth/6) + (gp.tileSize/4),y + (gp.tileSize*2));
				
				//Current Map Text
				text = ("Current Map: " + gp.mapH.currentLocation);
				y += gp.tileSize/2;
				g2.drawString(text, (gp.screenWidth/6) + (gp.tileSize/4),y + (gp.tileSize*2));
				
				//Go Back Text
				g2.setColor(new Color(0, 123, 202));
				text = ("Press the Enter Key to Go Back...");
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2) + (gp.tileSize * 7);
				g2.drawString(text, x, y);
			}
			else if(pauseScreenState == 3) // Game Settings
			{
				g2.setColor(new Color(0, 0, 0, 250));
				g2.fillRect(gp.screenWidth/6, (gp.screenHeight/4) - (gp.tileSize/2), (gp.screenWidth/6)*4, (gp.screenHeight/4)*3);
				
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
				

				//Music Text
				g2.setColor(Color.white);
				text = "Music Volume: ";
				y = ((gp.screenHeight/4) - (gp.tileSize/2)) - 0;
				g2.drawString(text, ((gp.screenWidth/6) + (gp.tileSize/4)) + 30,y + (gp.tileSize*2));
				
				g2.setColor(new Color(0, 117, 229, 50));
				
				
				
				//Music Bars
				g2.fillRect((gp.tileSize*12) , (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
				g2.fillRect((gp.tileSize*12) + 300, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

			if(gp.music.currentMusicVolume <= -80.f && gp.music.currentMusicVolume < -72.6f  )
			{
				//All bars are default
			}
			if(gp.music.currentMusicVolume >= -72.6f && gp.music.currentMusicVolume < -65.2f  )
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
			}
			if(gp.music.currentMusicVolume >= -65.2f && gp.music.currentMusicVolume < -57.799995f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

			}
			if(gp.music.currentMusicVolume >= -57.799995f && gp.music.currentMusicVolume < -50.399994f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

			}
			if(gp.music.currentMusicVolume >= -50.399994f && gp.music.currentMusicVolume < -42.999992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.music.currentMusicVolume >= -42.999992f && gp.music.currentMusicVolume < -35.59999f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.music.currentMusicVolume >= -35.59999f && gp.music.currentMusicVolume < -28.199991f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				
			}
			if(gp.music.currentMusicVolume >= -28.199991f && gp.music.currentMusicVolume < -20.799992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.music.currentMusicVolume >= -20.799992f && gp.music.currentMusicVolume < -13.399992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.music.currentMusicVolume >= -13.399992f && gp.music.currentMusicVolume < -5.999992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			
			if(gp.music.currentMusicVolume >= -5.999992f && gp.music.currentMusicVolume < 1.4000082f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.music.currentMusicVolume >= 1.4000082f && gp.music.currentMusicVolume <= 6.0f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 300, (y + (gp.tileSize)) + 12, (gp.tileSize/2), (gp.tileSize));

				
			}
			
			if(commandPauseNum == 0)
			{
				g2.setColor(new Color(42, 42, 42));
				g2.drawString(">", ((gp.screenWidth/6) + (gp.tileSize/4)) +2,y + (gp.tileSize*2));
				g2.setColor(Color.white);
				g2.drawString(">", ((gp.screenWidth/6) + (gp.tileSize/4)) +5,y + (gp.tileSize*2));
				
				//MAIN TEXT QUIT
				g2.setColor(new Color(10, 24, 64));
				g2.drawString(text, ((gp.screenWidth/6) + (gp.tileSize/4)) + 30,y + (gp.tileSize*2));
			}
			
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
			g2.setColor(Color.white);
			text = "Sound FX Volume: ";
			y = ((gp.screenHeight/4) - (gp.tileSize/2)) - 40;
			g2.drawString(text, ((gp.screenWidth/6) + (gp.tileSize/4)) + 30,(y + (gp.tileSize*3))+60);
			
			g2.setColor(new Color(0, 117, 229, 50));
			
			//Music Bars
			g2.fillRect((gp.tileSize*12) , (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

			g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			g2.fillRect((gp.tileSize*12) + 300, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			
			if(gp.se.currentSoundVolume <= -80.f && gp.se.currentSoundVolume < -72.6f  )
			{
				//All bars are default
			}
			if(gp.se.currentSoundVolume >= -72.6f && gp.se.currentSoundVolume < -65.2f  )
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
			}
			if(gp.se.currentSoundVolume >= -65.2f && gp.se.currentSoundVolume < -57.799995f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

			}
			if(gp.se.currentSoundVolume >= -57.799995f && gp.se.currentSoundVolume < -50.399994f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

			}
			if(gp.se.currentSoundVolume >= -50.399994f && gp.se.currentSoundVolume < -42.999992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.se.currentSoundVolume >= -42.999992f && gp.se.currentSoundVolume < -35.59999f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.se.currentSoundVolume >= -35.59999f && gp.se.currentSoundVolume < -28.199991f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				
			}
			if(gp.se.currentSoundVolume >= -28.199991f && gp.se.currentSoundVolume < -20.799992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.se.currentSoundVolume >= -20.799992f && gp.se.currentSoundVolume < -13.399992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.se.currentSoundVolume >= -13.399992f && gp.se.currentSoundVolume < -5.999992f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			
			if(gp.se.currentSoundVolume >= -5.999992f && gp.se.currentSoundVolume < 1.4000082f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60,(y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(gp.se.currentSoundVolume >= 1.4000082f && gp.se.currentSoundVolume <= 6.0f)
			{
				g2.setColor(new Color(0, 117, 229));
				g2.fillRect((gp.tileSize*12), (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 30, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 60, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 90, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 120, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 150, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 180, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 210, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 240, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 270, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));
				g2.fillRect((gp.tileSize*12) + 300, (y + (gp.tileSize*3)+22), (gp.tileSize/2), (gp.tileSize));

				
			}
			if(commandPauseNum == 1)
			{
				g2.setColor(new Color(42, 42, 42));
				g2.drawString(">", ((gp.screenWidth/6) + (gp.tileSize/4)) +2,(y + (gp.tileSize*3))+60);
				g2.setColor(Color.white);
				g2.drawString(">", ((gp.screenWidth/6) + (gp.tileSize/4)) +5,(y + (gp.tileSize*3))+60);
				
				//MAIN TEXT QUIT
				g2.setColor(new Color(10, 24, 64));
				g2.drawString(text, ((gp.screenWidth/6) + (gp.tileSize/4)) + 30,(y + (gp.tileSize*3))+60);
			}

				
				
				
				
				//Go Back Text
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
				g2.setColor(new Color(0, 123, 202));
				text = ("Press the Enter Key to Go Back...");
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2) + (gp.tileSize * 7);
				g2.drawString(text, x, y);
				
				
			}
			else if (pauseScreenState == 4)
			{
				g2.setColor(new Color(0, 0, 0));
				g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
				
				//Go Back Text
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 100F));
				g2.setColor(new Color(255, 255, 255));
				text = ("Made by Allan Kangethe");
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2);
				g2.drawString(text, x, y);
				
				//Go Back Text
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
				g2.setColor(new Color(255, 255, 255));
				text = ("In minor help from Alex Beck and Michael DeReggi");
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2);
				g2.drawString(text, x, y+gp.tileSize);
				
				
				
				
				//Go Back Text
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
				g2.setColor(new Color(0, 123, 202));
				text = ("Press the Enter Key to Go Back...");
				x = getXforCenteredText(text);
				y = (gp.screenHeight/2) + (gp.tileSize * 7);
				g2.drawString(text, x, y);
				
			}
			
			
			
		}
		
		
		
		public int getXforCenteredText(String text)
		{
			int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int x = gp.screenWidth/2 - length/2;
			
			return x;
		}
	
}
