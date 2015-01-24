package room;

import item.Item;

import java.util.HashMap;

/**
 * Specific room witch have the role of a wall.
 * 
 *
 */

@SuppressWarnings("serial")
public class Wall extends Room{
	
	/** 
	 * Constructor witch create a wall
	 */
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
	
	@Override
	public void setNoise(int n)
	{
	
	}
	@Override
	public void addNoise(int noise){}
}
