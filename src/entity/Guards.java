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
	public boolean check(Player p, Room[][] tab){
		
		if (active){
			int x=currentRoom.getX();	// Abscissa
			int y=currentRoom.getY();	// Ordered	
			boolean north=tab[x][y+1].isPlayer();
			boolean south=tab[x][y-1].isPlayer();
			boolean east=tab[x-1][y].isPlayer();
			boolean west=tab[x+1][y].isPlayer();
			boolean current=tab[x][y].isPlayer();
			if (north||south||east||west||current){return true&&!bribed&&!p.isHidden();}	// If he see the player
		}
		return false;
	}
	@Override
	public int getType(){return 2;}
	public void switche(){active=!active;}
	public boolean move(Player p, Room[][]tab){return check(p,tab);}
}
