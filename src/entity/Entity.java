package entity;
import room.*;
/**
 * Defines all the entities
 * @author user
 *
 */
public abstract class Entity {
	// Where the entity currently is
	private Room currentRoom;	
	// Name of the entity
	private String name;
	
	// Methods
	/**
	 * Allows the entity to move
	 * @param next	The next area she wants to go 
	 * @return True if she moved
	 */
	
	// Getters
	public Room getCurrentRoom(){return currentRoom;}
	public String getName(){return name;}
	
	// Setters
	public void setCurrentRoom(Room currentRoom) {this.currentRoom = currentRoom;}
	public void setName(String name) {this.name = name;}
	
	// Equals override
	@Override
	public boolean equals(Object obj){
		if (this==obj){return true;}
		else if (obj instanceof Entity){
			Entity tmp=(Entity)obj;
			if (this.getCurrentRoom()==tmp.getCurrentRoom()){return true;}
		}
		return false;
	}
	
	// toString Override
	@Override
	public String toString(){return this.getName();}
}
