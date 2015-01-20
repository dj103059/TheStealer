package item;
import room.*;


public class Map extends Item {
	//constant weight of the map
	private static final int WEIGTH = 5 ;
	private Room [][] tabRoom;
	
	/**
	 * Create a map associated at the area
	 * 
	 * @param allRoom
	 */
	public Map (Room [][] allRoom){
		this.setDescription("You can see the position of the various entities in the area.");
		this.setName("Map");
		this.setWeight(WEIGTH);
		this.setTabRoom(allRoom);
		
	}
	
	/**
	 * Return the TabRoom (witch is representing the area) associated with the map
	 * 
	 * @return
	 */
	public Room[][] getTabRoom() {
		return tabRoom;
	}

	/**
	 * Set the TabRoom (witch is representing the area) associated with the map. Allow to update the information of the map.
	 * 
	 * @param tabRoom
	 */
	public void setTabRoom(Room[][] tabRoom) {
		this.tabRoom = tabRoom;
	}

	
	
	/**
	 * Return a String whitch contains the position of the different entity in the area.
	 */
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
