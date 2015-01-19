package entity;
import room.*;

public abstract class Entity {
	protected Room currentRoom;
	public boolean move(Room next){
		if (next!=null){currentRoom=next;return true;}
		else{return false;}
	}
	// Getteurs
	public int getType(){return 1;}
	public Room getCurrentRoom(){return currentRoom;}
	@Override
	public boolean equals(Object obj){
		if (this==obj){return true;}
		else if (obj instanceof Entity){
			Entity tmp=(Entity)obj;
			if (this.getCurrentRoom()==tmp.getCurrentRoom()){return true;}
		}
		return false;
	}
}
