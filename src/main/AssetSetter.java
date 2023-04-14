package main;
import entity.Dialogue1_0_LivingroomToHallway;
import entity.Dialogue_0_Start;
import entity.Dialogue_1_Bed;
import entity.Dialogue_2_Computer;
import entity.Dialogue_3_DaBabyRug;
import entity.Dialogue_4_HallwayToBedroom;
import entity.Dialogue_5_ParentsArguing;
import entity.Dialogue_6_ParentsRoom;
import entity.Dialogue_7_SistersRoom;
import entity.Dialogue_8_LivingroomPicture1;
import entity.Dialogue_9_LivingroomPicture2;
import entity.NPC_Mother_Home;
import object.OBJ_BedroomToHallway;
import object.OBJ_Boots;
import object.OBJ_HallwayToBedroom;
import object.OBJ_HallwayToLivingroom;
import object.OBJ_LivingroomToHallway;
import object.OBJ_LivingroomToNeighborhood;

public class AssetSetter {
	
	GamePanel gp;
	

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject()
	{
		gp.obj[0] = new OBJ_BedroomToHallway(gp);
		gp.obj[0].worldX = 4 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;

		gp.obj[1] = new OBJ_HallwayToBedroom(gp);
		gp.obj[1].worldX = 300 * gp.tileSize;
		gp.obj[1].worldY = 0 * gp.tileSize;
		
		gp.obj[2] = new OBJ_HallwayToLivingroom(gp);
		gp.obj[2].worldX = 300 * gp.tileSize;
		gp.obj[2].worldY = 4 * gp.tileSize;
		
		gp.obj[3] = new OBJ_LivingroomToHallway(gp);
		gp.obj[3].worldX = 300 * gp.tileSize;
		gp.obj[3].worldY = 4 * gp.tileSize;
		
		gp.obj[4] = new OBJ_LivingroomToNeighborhood(gp);
		gp.obj[4].worldX = 300 * gp.tileSize;       //Coin type object
		gp.obj[4].worldY = 7 * gp.tileSize;	
		
			
	}
	
	public void setNPC()
	{
		
		
		gp.npc[0] = new Dialogue_0_Start(gp);
		gp.npc[0].worldX = gp.tileSize*4;
		gp.npc[0].worldY = gp.tileSize*3;
		gp.npc[0].direction = "leftStand";
		
		gp.npc[1] = new Dialogue_1_Bed(gp);
		gp.npc[1].worldX = gp.tileSize*5;
		gp.npc[1].worldY = gp.tileSize*3;
		gp.npc[1].direction = "leftStand";
		
		gp.npc[2] = new Dialogue_2_Computer(gp);
		gp.npc[2].worldX = gp.tileSize*1;
		gp.npc[2].worldY = gp.tileSize*3;
		gp.npc[2].direction = "leftStand";
		
		gp.npc[3] = new Dialogue_3_DaBabyRug(gp);
		gp.npc[3].worldX = gp.tileSize*3;
		gp.npc[3].worldY = gp.tileSize*6;
		gp.npc[3].direction = "leftStand";
		
		gp.npc[4] = new Dialogue_4_HallwayToBedroom(gp);
		gp.npc[4].worldX = gp.tileSize*300;
		gp.npc[4].worldY = gp.tileSize*6;
		gp.npc[4].direction = "leftStand";
		
		gp.npc[5] = new Dialogue_5_ParentsArguing(gp);
		gp.npc[5].worldX = gp.tileSize*300;
		gp.npc[5].worldY = gp.tileSize*6;
		gp.npc[5].direction = "leftStand";
		
		gp.npc[6] = new Dialogue_6_ParentsRoom(gp);
		gp.npc[6].worldX = gp.tileSize*300;
		gp.npc[6].worldY = gp.tileSize*6;
		gp.npc[6].direction = "leftStand";
		
		gp.npc[7] = new Dialogue_7_SistersRoom(gp);
		gp.npc[7].worldX = gp.tileSize*300;
		gp.npc[7].worldY = gp.tileSize*6;
		gp.npc[7].direction = "leftStand";
		
		gp.npc[8] = new Dialogue_8_LivingroomPicture1(gp);
		gp.npc[8].worldX = gp.tileSize*300;
		gp.npc[8].worldY = gp.tileSize*3;
		gp.npc[8].direction = "leftStand";
		
		gp.npc[9] = new Dialogue_9_LivingroomPicture2(gp);
		gp.npc[9].worldX = gp.tileSize*300;
		gp.npc[9].worldY = gp.tileSize*3;
		gp.npc[9].direction = "leftStand";
		
		gp.npc[10] = new Dialogue1_0_LivingroomToHallway(gp);
		gp.npc[10].worldX = gp.tileSize*300;
		gp.npc[10].worldY = gp.tileSize*3;
		gp.npc[10].direction = "leftStand";
		
		
		

	}
	

	
	



}
