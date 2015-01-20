package room;


/**
 * the treasure room
 *
 */
public class RoomGold extends RoomPass{
	//the amont of gold
	private int gold;

	//constructor
	public RoomGold(String description, String name) {
		super(description, name);
		this.setGold(0);
	}
	
	//the constructor with the amont of gold in the room
	public RoomGold(String description, String name, int g)
	{
		super(description, name);
		this.setGold(g);
	}
	
	//getter and setter
	public void setGold(int g)
	{
		this.gold=g;
	}

	public int getGold()
	{
		return gold;
	}
	
	
}
