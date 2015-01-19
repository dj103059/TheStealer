package room;

import item.Pass;

public class RoomGold extends RoomPass{

	public RoomGold(String description, String name) {
		super(description, name);
		this.setPass1(new Pass("Gold Room"));
	}

}
