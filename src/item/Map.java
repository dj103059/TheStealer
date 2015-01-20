package item;
import room.*;
/**
 * The box item
 **/
public class Map extends Item {
	//constant weight of the map
	private static final int weigth = 5 ;
	private Room [][] tabRoom;
	
	//constructor
	public Map (Room [][] allRoom){
		this.setDescription(" You can see the area.");
		this.setName("Map");
		this.setWeigth(weigth);
		this.update(allRoom);
		
	}
	
	
	public Room[][] getTabRoom() {
		return tabRoom;
	}


	public void setTabRoom(Room[][] tabRoom) {
		this.tabRoom = tabRoom;
	}


	public void update(Room [][] allRoom){
		this.setTabRoom(allRoom);
		
		
	}
	
	@Override
	public String use(){
		String s="";
		for (int i=0;i<tabRoom.length;i++)
		{
			for (int j=0;j<tabRoom[i].length;j++)
			{
				if(tabRoom[i][j]!=null){
					
				
					for (int k=0;k<tabRoom[i][j].getListofentity().size();k++)
					{
						s="The entity "+tabRoom[i][j].getListofentity().get(k)+" is in the room ( "+i+", "+j+")"+"\n"+s;
					}
				}
				
			}
		}

		return s;
		
	}
}
