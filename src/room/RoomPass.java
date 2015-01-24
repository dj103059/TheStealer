package room;



import java.util.HashMap;

import item.*;

/**
 * Class for room witch required a pass to go in
 * 
 */

public class RoomPass extends Room {
	//pass 1 particular for the room
	private Pass pass1;
	// pass for all room
	private Pass pass2;

	/**
	 * Create a room with the description and the name given in parameter
	 * @param description
	 * @param name
	 */
	public RoomPass(String description, String name) {
		super(description, name);
		this.pass1 = new Pass(name);
		this.pass2 = new Pass("multipass");
	}
	
	/**
	 * Create a room with the description and the name given in parameter and the pass of the room in the room
	 * given in parameter
	 * @param description
	 * @param name
	 * @param room
	 */
	public RoomPass(String description, String name,Room room) {
		super(description, name);
		this.pass1 = new Pass(name);
		this.pass2 = new Pass("multipass");
		room.addItem(this.pass1);
	}

	/**
	 * Get the pass required to open the room
	 * @return pass1
	 */
	public Pass getPass1() {
		return pass1;
	}
	
	/**
	 * Get the second pass whitch can open the room (common to all room)
	 * @return
	 */
	public Pass getPass2() {
		return pass2;
	}

	/**
	 * Set the pass required to open the room
	 */
	public void setPass1(Pass pass1) {
		this.pass1 = pass1;
	}

	
	/**
     * Return true if the item contains in the inventory allow the player to go in the room, false else.
     * @param inventory
     * @return boolean
     */
	@Override
	public boolean canEnter(HashMap<String, Item> inventory)
	{
		
		if(this.getName().equals("Wall"))
		{return false;}
		
		if (inventory.containsValue(this.getPass1()))
				{
					return true;
				}
		return false;
	}
}
