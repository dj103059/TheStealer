package room;

import entity.Player;

public class Wall extends Room{
	
	public Wall(){
		super("This room is a Wall","Wall");
	}
	
	@Override
	public boolean equals(Object obj){
		if (this==obj){return true;}
		else if (obj instanceof Wall){
			return true;
			
		}
		return false;
	}
	
	@Override
	public boolean isPlayer(){
		return false;
	}
}
