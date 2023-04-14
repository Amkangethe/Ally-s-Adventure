package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	final int originalPlayerXTileSize = 16; // 16x16 tile
	final int originalPlayerYTileSize = 32; // 16x16 tile
	
	final int originalTileSize = 16; // 16x16 tile

	final int playerScale = 2;
	final int tileScale = 3;
	
	public final int XPlayertileSize = originalPlayerXTileSize * playerScale; //48x48
	public final int YPlayertileSize = originalPlayerYTileSize * playerScale; //48x48
	public final int tileSize = 16 * 3; //48x48

	
	public final int maxScreenCol = 26;
	public final int maxScreenRow = 14;
	public final int screenWidth = 54 * maxScreenCol;  
	public final int screenHeight = 54 * maxScreenRow; 
	
	public int maxWorldCol = 500;
	public int maxWorldRow = 500;
	//public final int worldWidth = tileSize * maxWorldCol;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 60;
		
	
	
	//SYSTEM
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Sound music = new Sound(this);
	public Sound se = new Sound(this);

	public MapHandler mapH = new MapHandler(this);
	public Checkpoint cpH = new Checkpoint();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	//public Dialogue SuperDialogue = new Dialogue(this);
	public UI ui = new UI(this);
	Thread gameThread;

	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[1000];
	public Entity npc[] = new Entity[100];
	
	
	
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int controlsState = 4;
	public final int gameInfoState = 5;
	public final int otherState = 6;

	
	public GamePanel()
	{
		

		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

		
	}
	
	public void setupGame()
	{
		maxWorldCol = 50;
		maxWorldRow = 50;
		
		//gameState = playState;
		gameState = titleState;
		
		aSetter.setObject();
		aSetter.setNPC();
		
		
		
		//playMusic(3);
		//playMusic(0);
		
		
		

	}
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
		

	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null)
		{		

			currentTime = System.nanoTime();
			
			timer += (currentTime - lastTime);
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				
				repaint();
				
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000)
			{
				//System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
			
		}
		
	}
	
	public void update()
	{
		
		if(gameState == playState)
		{
			
			player.update();
			
			for(int i = 0; i < npc.length;i++)
			{
				if(npc[i] != null)
				{
					npc[i].update();
				}
				
				//here is update diologue
			}
			
			
			
		}
		if(gameState == pauseState)
		{
			
		}
		if(gameState == dialogueState)
		{
				
		}
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true)
		{
			drawStart = System.nanoTime();

		}
		
		if((gameState == titleState) || 
				(gameState == controlsState) ||
				(gameState == gameInfoState) ||
				(gameState == otherState))
		{
			ui.draw(g2);
		}
		else
		{
			//TILE
			tileM.draw(g2);
			
			for(int i = 0; i < obj.length; i++)
			{
				if(obj[i] != null)
				{
					obj[i].draw(g2,this);
				}
			}
			
			//NPC
			for(int j = 0; j < npc.length; j++)
				{
					if(npc[j] != null)
					{
						npc[j].draw(g2);
					}
				}
			
			player.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		
		if(keyH.checkDrawTime == true)
		{
			//DEBUG
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed + " Nanoseconds", 10, 400);


		}
		
		
		
		g2.dispose();
		
		
	}
	public void playMusic(int i)
	{
		music.setFile(i);
		music.musicPlay();
		music.loop();
	}
	public void stopMusic()
	{
		music.stop();
	}
	public void musicVolumeUp()
	{
		music.musicVolumeUp();
	}
	public void musicVolumeDown()
	{
		music.musicVolumeDown();
	}
	public void soundVolumeUp()
	{
		se.soundVolumeUp();
	}
	public void soundVolumeDown()
	{
		se.soundVolumeDown();
	}
	public void playSE(int i)
	{
		se.setFile(i);
		se.soundPlay();
	}
	
	public void playSELoop(int i)
	{
		se.setFile(i);
		se.soundPlay();
		se.loop();
	}
	public void stopSE()
	{
		
		se.stop();
	}

}
