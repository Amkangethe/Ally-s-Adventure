package main;

public class MapHandler {
	
	GamePanel gp;
	
	public MapHandler(GamePanel gp)
	{
		this.gp = gp;
	}
	
public static int currentMap = 0;
public static String currentLocation = "Allan's Bedroom";

	public static int mapTransfer(int objIndex)
	{
		if(objIndex == 0)
		{
			currentMap = 1; //Hallway
			currentLocation = "Hallway";

		}
		else if(objIndex == 1)
		{
			currentMap = 0; //Bedroom
			currentLocation = "Allan's Bedroom";

		}
		else if(objIndex == 2)
		{
			currentMap = 2; //LivingRoom
			currentLocation = "Allan's Living Room";

		}
		else if(objIndex == 3)
		{
			currentMap = 1; // Hallway
			currentLocation = "Hallway";

		}
		else if(objIndex == 4)
		{
			currentMap = 3; // Allan's Neighborhood
			currentLocation = "Allan's Neighborhood";

		}

		
		return currentMap;
	}

}
