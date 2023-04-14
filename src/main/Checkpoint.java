package main;

public class Checkpoint {
	GamePanel gp;
	
	public int gameCompleted = 0;


	public void Checkpoint(GamePanel gp)
	{
		this.gp = gp;
		
		if(gp.player.checkPointer == 0) 
		{
			gameCompleted = 0;

		}
		if(gp.player.checkPointer == 1) // When the player leaves the bedroom
		{
			
			gameCompleted = 4;

		//	System.out.println("you are in hallway");
			
		}
		if(gp.player.checkPointer == 2)
		{
			
			gameCompleted = 4;

		//	System.out.println("you are in hallway");
		}

	}
	
	
	
	

}
