package room;

import item.Pass;

public class RoomGold extends RoomPass{
	private int gold;

	public RoomGold(String description, String name) {
		super(description, name);
		this.setPass1(new Pass("Gold Room"));
		this.setGold(0);
	}
	
	public RoomGold(String description, String name, int g)
	{
		super(description, name);
		this.setPass1(new Pass("Gold Room"));
		this.setGold(g);
	}
	
	public void setGold(int g)
	{
		this.gold=g;
	}

	public int getGold()
	{
		return gold;
	}
	
	
}
