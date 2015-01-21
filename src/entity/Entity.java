package entity;
import room.*;
/**
 * Defines all the entities
 * @author user
 *
 */
public abstract class Entity {
	// Where the entity currently is
	protected Room currentRoom;	
	// Name of the entity
	protected String name;
	
	// Methods
	/**
	 * Allows the entity to move
	 * @param next	The next area she wants to go 
	 * @return True if she moved
	 */
	public boolean move(Room next){
		if (next!=null){currentRoom=next;return true;}
		else{return false;}
	}
	
	// Getters
	public Room getCurrentRoom(){return currentRoom;}
	public String getName(){return name;}
	
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
	public String toString(){return this.name;}
}
