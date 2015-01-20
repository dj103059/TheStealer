package room;

import java.util.ArrayList;


import item.*;
/*
 * room who need a pass to enter
 */

public class RoomPass extends Room {
	//pass 1 particular for the room
	private Pass pass1;
	// pass all room
	private Pass pass2;

	//constructor
	public RoomPass(String description, String name) {
		super(description, name);
		this.pass1 = new Pass(name);
		this.pass2 = new Pass("ALLROOM");
	}
	
	//constructor with the room who you drop the key
	public RoomPass(String description, String name,Room room) {
		super(description, name);
		this.pass1 = new Pass(name);
		this.pass2 = new Pass("ALLROOM");
		room.addItem(this.pass1);
	}

	//getter an setter
	public Pass getPass1() {
		return pass1;
	}
	
	public Pass getPass2() {
		return pass2;
	}

	public void setPass1(Pass pass1) {
		this.pass1 = pass1;
	}

	public void setPass2(Pass pass2) {
		this.pass2 = pass2;
	}
	
	public boolean canEnter(ArrayList<Item> inventory)
	{
		if (inventory.contains(this.getPass1()))
				{return true;}
		return false;
	}
}
