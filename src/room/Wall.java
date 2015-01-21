package room;

import item.Item;

import java.util.HashMap;

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
	
	@Override
	public boolean canEnter(HashMap<String, Item> inventory)
	{
		return false;
	}
}
