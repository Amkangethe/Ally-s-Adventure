package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import entity.Entity;
public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, rightKeyPressed;
	public int keyReleasedCount, dialogueCount;
	boolean checkDrawTime;
	static int  dialogue = 1;
	
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public int num = 0;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			
			if(gp.ui.titleScreenState == 0)
			{
				if(code == KeyEvent.VK_UP)
				{
					gp.ui.commandTitleNum--;
					if(gp.ui.commandTitleNum < 0)
					{
						gp.ui.commandTitleNum = 1;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_DOWN)
				{
					gp.ui.commandTitleNum++;
					if(gp.ui.commandTitleNum > 1)
					{
						gp.ui.commandTitleNum = 0;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_ENTER)
				{
					if(gp.ui.commandTitleNum == 0)
					{
						gp.playSE(4);
						gp.ui.titleScreenState = 1;
					}
					if(gp.ui.commandTitleNum == 1)
					{
						System.exit(0);
					}
					
					
								
				}
			}
			else if (gp.ui.titleScreenState == 1)
			{
				if(code == KeyEvent.VK_SPACE)
				{
					gp.playSE(4);
					gp.ui.titleScreenState = 2;

					
				}
			}
			else if (gp.ui.titleScreenState == 2)
			{
				if(code == KeyEvent.VK_ENTER)
				{
					gp.playSE(4);
					JOptionPane.showMessageDialog(null,
						    "Note: The game does not automatically save.\n"
						    +"I am still developing this.\n"
						    +"Currently, you must play the entire game in one run.\n"
						    +"Sorry :(",
						    "WARNING",
						    JOptionPane.WARNING_MESSAGE);
			
					gp.gameState = gp.playState;
				}
			}
		}
		
		//GAME STATE
		if(gp.gameState == gp.playState)
		{
			if(code == KeyEvent.VK_W)
			{
				upPressed = true;
			}
			if(code == KeyEvent.VK_A)
			{
				leftPressed = true;

			}
			if(code == KeyEvent.VK_S)
			{
				downPressed = true;

			}
			if(code == KeyEvent.VK_D)
			{
				rightPressed = true;
			}
			if(code == KeyEvent.VK_ESCAPE)
			{
				gp.gameState = gp.pauseState;

			}
			
			if(code == KeyEvent.VK_SHIFT)
			{
				gp.player.speed = 7;

			}
			
			
			//DEBUG
			if(code == KeyEvent.VK_T)
			{
				checkDrawTime = true;
				if(checkDrawTime == false)
				{
				

				}
				else if(checkDrawTime == true)
				{
					checkDrawTime = false;

				}
			}

		}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState)
		{
			
			if(gp.ui.pauseScreenState == 0)
			{
				if(code == KeyEvent.VK_ESCAPE)
				{
					gp.gameState = gp.playState;
				}
				if(code == KeyEvent.VK_UP)
				{
					gp.ui.commandPauseNum--;
					if(gp.ui.commandPauseNum < 0)
					{
						gp.ui.commandPauseNum = 2;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_DOWN)
				{
					gp.ui.commandPauseNum++;
					if(gp.ui.commandPauseNum > 2)
					{
						gp.ui.commandPauseNum = 0;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_ENTER)
				{
					if(gp.ui.commandPauseNum == 0)
					{
						gp.gameState = gp.playState;
						//gp.stopMusic();
						//gp.playMusic(0);
					


					}
					if(gp.ui.commandPauseNum == 1)
					{
						gp.ui.pauseScreenState = 1;
						gp.ui.commandPauseNum = 0;
					}
					if(gp.ui.commandPauseNum == 2)
					{
						System.exit(0);
					}

				}
			}
			else if(gp.ui.pauseScreenState == 1)
			{
				if(code == KeyEvent.VK_ESCAPE)
				{
					gp.gameState = gp.playState;
					gp.ui.pauseScreenState = 0;
					gp.ui.commandPauseNum = 0;
					
				}
				if(code == KeyEvent.VK_UP)
				{
					gp.ui.commandPauseNum--;
					if(gp.ui.commandPauseNum < 0)
					{
						gp.ui.commandPauseNum = 3;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_DOWN)
				{
					gp.ui.commandPauseNum++;
					if(gp.ui.commandPauseNum > 3)
					{
						gp.ui.commandPauseNum = 0;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_ENTER)
				{
					if(gp.ui.commandPauseNum == 0)
					{
						
						gp.ui.pauseScreenState = 2;



					}
					if(gp.ui.commandPauseNum == 1)
					{
						gp.ui.pauseScreenState = 3;
						gp.ui.commandPauseNum = 0;

					}
					if(gp.ui.commandPauseNum == 2)
					{
						gp.ui.pauseScreenState = 4;
						gp.ui.commandPauseNum = 0;

					}
					if(gp.ui.commandPauseNum == 3)
					{
						gp.ui.pauseScreenState = 0;
						gp.ui.commandPauseNum = 1;
					}

				}
			}
			else if (gp.ui.pauseScreenState == 2)
			{
				if(code == KeyEvent.VK_ENTER)
				{
					gp.ui.pauseScreenState = 1;
					gp.ui.commandPauseNum = 0;

				}
				if(code == KeyEvent.VK_ESCAPE)
				{
					gp.ui.pauseScreenState = 0;
					gp.gameState = gp.playState;
				}
				
				
			}
			else if (gp.ui.pauseScreenState == 3)
			{
				if(code == KeyEvent.VK_ENTER)
				{
					gp.ui.pauseScreenState = 1;
					gp.ui.commandPauseNum = 1;

				}
				if(code == KeyEvent.VK_ESCAPE)
				{
					gp.ui.pauseScreenState = 0;
					gp.gameState = gp.playState;
				}
				
				
				if(code == KeyEvent.VK_UP)
				{
					gp.ui.commandPauseNum--;
					if(gp.ui.commandPauseNum < 0)
					{
						gp.ui.commandPauseNum = 1;
					}
					gp.playSE(2);

				}
				if(code == KeyEvent.VK_DOWN)
				{
					gp.ui.commandPauseNum++;
					if(gp.ui.commandPauseNum > 1)
					{
						
						gp.ui.commandPauseNum = 0;
					}
					gp.playSE(2);

				}
				
				if(gp.ui.commandPauseNum == 0)
				{
					if(code == KeyEvent.VK_LEFT)
					{
						gp.musicVolumeDown();
					}
					if(code == KeyEvent.VK_RIGHT)
					{
						gp.musicVolumeUp();
						System.out.println(gp.music.currentMusicVolume);

					}
					
				}
				else if(gp.ui.commandPauseNum == 1)
				{
					if(code == KeyEvent.VK_LEFT)
					{
						gp.soundVolumeDown();
						System.out.println(gp.se.currentSoundVolume);
					}
					if(code == KeyEvent.VK_RIGHT)
					{
						gp.soundVolumeUp();
						System.out.println(gp.se.currentSoundVolume);


					}
				}
				
				
			}
			else if (gp.ui.pauseScreenState == 4)
			{
				if(code == KeyEvent.VK_ENTER)
				{
					gp.ui.pauseScreenState = 1;
					gp.ui.commandPauseNum = 2;

				}
				if(code == KeyEvent.VK_ESCAPE)
				{
					gp.ui.pauseScreenState = 0;
					gp.gameState = gp.playState;
				}
			}
			
			
		}

		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState)
		{
			if(code == KeyEvent.VK_ENTER)
			{
				
				entity.Entity.dialogueIndex++;
				//object.SuperObject.dialogueIndex++;
				gp.gameState = gp.playState;

				
				
				//entity.Entity.dialogueIndex++;
				//gp.ui.currentDialogue = entity.Entity.dialogues[dialogue];

				
				/*
				gp.ui.currentDialogue = entity.Entity.dialogues[dialogue];
				dialogue++;
				System.out.println(dialogue);

				if( entity.Entity.dialogues[dialogue] == null)
				{
					dialogue = 1;
					gp.gameState = gp.playState;
				}
			
			*/
	
			}
		
	}

		
	

	}
	
	public static String checkPointer = "";

	
	@Override
	public void keyReleased(KeyEvent e) {


		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_A)
		{
			leftPressed = false;

		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;


		}
		if(code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		
		if(code == KeyEvent.VK_SHIFT)
		{
			gp.player.speed = 4;

		}
	

	} 

}
