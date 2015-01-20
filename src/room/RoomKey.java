package room;



public class RoomKey extends Room {
	
	/**
	 *  Create a RoomKey with the name and the description chosen
	 */
	public RoomKey(String description,String name){
		super(description, name);
		
	}
	
	/**
	 * Create a RoomKey with the name and the description chosen and add badge required to open the RoomPass placed in parameter
	 * 
	 * @param description
	 * @param name
	 * @param room
	 */
	public RoomKey(String description, String name,RoomPass room)
	{
		super(description,name);
		this.addKey(room);
		
	}
	/**
	 * Add in this the Pass required to open the RoomPass placed in parameter
	 * @param room
	 */
	public void addKey(RoomPass room)
	{
		this.addItem(room.getPass1());
	}

}
