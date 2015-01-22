package room;

/**
 * The treasure room
 *
 */
public class RoomGold extends RoomPass{
	//the amont of gold
	
	/**
	 * The gold that the room contains
	 */
	private int gold;

	//constructor
	
	/**
	 * Create a RoomGold with a description, a name and an amount of gold of 0.
	 * 
	 * @param description the description of the room
	 * @param name name of the room
	 */
	public RoomGold(String description, String name) {
		super(description, name);
		this.setGold(0);
	}
	
	/**
	 * Create a RoomGold with the amount of gold given in parameter
	 * 
	 * @param description the description of the room
	 * @param name the name of the room
	 * @param g the gold in the room
	 */
	//the constructor with the amont of gold in the room
	
	/**
	 * 
	 * @param description
	 * @param name
	 * @param g gold in the room
	 */
	public RoomGold(String description, String name, int g)
	{
		super(description, name);
		this.setGold(g);
	}
	
	/**
	 * Change the amount of the gold in the room
	 * @param g
	 */
	
	//getter and setter
	public void setGold(int g)
	{
		this.gold=g;
	}

	/**
	 * 
	 * @return the amount of the gold in the room.
	 */
	public int getGold()
	{
		return gold;
	}
	
	public String printItem()
	{
		String l=super.printItem();
		l+="There is "+this.getGold()+" gold in the room.";
		return l;
	}
	
	
}
