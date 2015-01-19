package entity;
import room.*;

public class Guards extends Entity{
	protected boolean active;
	protected boolean bribed;
	// Constructor
	public Guards(Room currentRoom, boolean bribed, boolean active){
		this.currentRoom=currentRoom;
		this.bribed=bribed;
		this.active=active;
	}
	// Méthods	
	/**
	 *  The guard will check the adjacent Room
	 * @return True if he can see the player
	 */
	public boolean check(Player p){
		
		if (active){
			int x=currentRoom.getX();	// Abscissa
			int y=currentRoom.getY();	// Ordered	
			Entity north=get(x,y+1).getPlayer();
			Entity south=get(x,y-1).getPlayer();
			Entity east=get(x-1,y).getPlayer();
			Entity west=get(x+1,y).getPlayer();
			Entity current=get(x,y).getPlayer();
			if (north||south||east||west||current){return true&&!blibed&&!p.isHidden();}	// If he see the player
		}
		return false;
	}
	@Override
	public int getType(){return 2;}
	public void switche(){active=!active;}
	public boolean move(Player p){return check(p);}
}
