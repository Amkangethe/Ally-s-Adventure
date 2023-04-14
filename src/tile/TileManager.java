package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Boots;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[10000];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		
		
	}
	
	public void getTileImage()
	{
		setUp(0, "WoodTiles", false);
		setUp(1, "DarkWoodTiles", false);
		setUp(2, "BottomWall", true);
		setUp(3, "TopWall", false);
		setUp(4, "SeatTile", true);
		setUp(5, "Computer", false);
		setUp(6, "Computer2", true);
		setUp(7, "SeatTile2", false);
		setUp(8, "Table", true);
		setUp(9, "Drawer", false);
		setUp(10, "Drawer2", true);
		setUp(11, "Rug1", false);
		setUp(12, "Rug2", false);
		setUp(13, "Rug3", false);
		setUp(14, "Rug4", true);
		setUp(15, "Rug5", true);
		setUp(16, "Rug6", true);
		setUp(17, "BlackSpace", true);
		setUp(18, "Bed1", true);
		setUp(19, "Bed2", true);
		setUp(20, "Bed3", true);
		setUp(21, "Bed4", true);
		setUp(22, "DaBabyRug1", false);
		setUp(23, "DaBabyRug2", false);
		setUp(24, "DaBabyRug3", false);
		setUp(25, "DaBabyRug4", false);
		setUp(26, "DaBabyRug5", false);
		setUp(27, "DaBabyRug6", false);
		setUp(28, "DaBabyRug7", false);
		setUp(29, "DaBabyRug8", false);
		setUp(30, "DaBabyRug9", false);
		setUp(31, "HallwayTopWall", true);
		setUp(32, "HallwayBottomWall", true);
		setUp(33, "TopDoor", true);
		setUp(34, "Stairs1", true);
		setUp(35, "Stairs2", true);
		setUp(36, "Stairs3", true);
		setUp(37, "Stairs4", true);
		setUp(38, "LivingroomStairs1", true);
		setUp(39, "LivingroomStairs2", true);
		setUp(40, "LivingroomStairs3", true);
		setUp(41, "LivingroomStairs4", true);
		setUp(42, "LivingroomStairs5", true);
		setUp(43, "LivingroomStairs6", true);
		setUp(44, "LivingroomRug1", false);
		setUp(45, "LivingroomRug2", false);
		setUp(46, "LivingroomRug3", true);
		setUp(47, "LivingroomRug4", true);
		setUp(48, "LivingroomRug5", false);
		setUp(49, "LivingroomRug6", false);
		setUp(50, "LivingroomRug7", false);
		setUp(51, "LivingroomRug8", false);
		setUp(52, "LivingroomRug9", true);
		setUp(53, "LivingroomRug10", true);
		setUp(54, "LivingroomRug11", false);
		setUp(55, "LivingroomRug12", false);
		setUp(56, "LivingroomRug13", false);
		setUp(57, "LivingroomRug14", false);
		setUp(58, "LivingroomRug15", true);
		setUp(59, "LivingroomRug16", true);
		setUp(60, "LivingroomRug17", false);
		setUp(61, "LivingroomRug18", false);
		setUp(62, "LivingroomRug19", false);
		setUp(63, "LivingroomRug20", false);
		setUp(64, "LivingroomRug21", false);
		setUp(65, "LivingroomRug22", false);
		setUp(66, "LivingroomRug23", false);
		setUp(67, "LivingroomRug24", false);
		setUp(68, "Tree1", true);
		setUp(69, "Tree2", true);
		setUp(70, "DarkTree1", true);
		setUp(71, "DarkTree2", true);
		setUp(72, "Sink1", true);
		setUp(73, "Sink2", true);
		setUp(74, "Sink3", false);
		setUp(75, "Sink4", false);
		setUp(76, "Cabinet1", true);
		setUp(77, "Cabinet2", true);
		setUp(78, "Cabinet3", false);
		setUp(79, "Cabinet4", false);
		setUp(80, "Picture1", false);
		setUp(81, "Picture2", false);
		setUp(82, "Picture3", true);
		setUp(83, "Picture4", true);
		setUp(84, "BottomDoor", true);
		setUp(85, "ParentRug1", false);
		setUp(86, "ParentRug2", false);
		setUp(87, "SistersRug1", false);
		setUp(88, "SistersRug2", false);
		setUp(89, "Grass1", false);
		setUp(90, "Grass2", false);
		setUp(91, "Grass3", false);
		setUp(92, "Grass4", false);
		setUp(93, "BackFence1", true);
		setUp(94, "BackFence2", true);
		setUp(95, "BackFence3", true);
		setUp(96, "BackFence4", true);
		setUp(97, "BackFence5", true);
		setUp(98, "BackFence6", true);
		setUp(99, "BackFence7", true);
		setUp(100, "BackGrass1", true);
		setUp(101, "BackGrass2", true);
		setUp(102, "BackGrass3", true);
		setUp(103, "BackGrass4", true);
		setUp(104, "BackGrass5", true);
		setUp(105, "BackGrass6", true);
		setUp(106, "BackGrass7", true);
		setUp(107, "BackGrass8", true);
		setUp(108, "BackGrass9", true);
		setUp(109, "BackFence8", true);
		setUp(110, "BackFence9", true);
		setUp(111, "Roof1", true);
		setUp(112, "Roof2", true);
		setUp(113, "Roof3", true);
		setUp(114, "Roof4", true);
		setUp(115, "Roof5", true);
		setUp(116, "BackFence1.0", true);
		setUp(117, "BackFence1.1", true);
		setUp(118, "Roof6", true);
		setUp(119, "Roof7", true);
		setUp(120, "Roof8", true);
		setUp(121, "Roof9", true);
		setUp(122, "Roof1.0", true);
		setUp(123, "BackFence1.2", true);
		setUp(124, "BackFence1.3", true);
		setUp(125, "Roof1.1", true);
		setUp(126, "Roof1.2", true);
		setUp(127, "Roof1.3", true);
		setUp(128, "Roof1.4", true);
		setUp(129, "Roof1.5", true);
		setUp(130, "Roof1.6", true);
		setUp(131, "Roof1.7", true);
		setUp(132, "Roof1.8", true);
		setUp(133, "Roof1.9", true);





	}
	
	public void setUp(int index, String imagePath, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void mapDecider(int map)
	{
		
		if(map == 0)
		{
			loadMap("/maps/BedroomMap.txt");
		}
		else if(map == 1) 
		{
			loadMap("/maps/HallwayMap.txt");
		}
		else if(map == 2) 
		{
			loadMap("/maps/LivingroomMap.txt");
			
		}
		else if(map == 3) 
		{
			
			loadMap("/maps/NeighborhoodMap.txt");

			
		}
		
	}
	
	public void loadMap(String filePath)
	{
		
		
		
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				String line = br.readLine();
				
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			
			br.close();
		}
		catch(Exception e)
		{
			
		}
		
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
			{
				g2.drawImage(tile[tileNum].image, screenX , screenY , null);

			}
			worldCol++;
			
		
			if(worldCol == gp.maxWorldCol)
			{
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
