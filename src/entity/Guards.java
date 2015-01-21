package entity;
import room.*;
/**
 * Defines the guard
 * @author user
 *
 */
public class Guards extends Entity{
	// If he's active
	protected boolean active;	
	// If the player have bribed him, if yes, he don't see him
	protected boolean bribed;
	
	// Constructor
	public Guards(Room currentRoom, boolean bribed, boolean active, String name){
		this.currentRoom=currentRoom;
		this.bribed=bribed;
		this.active=active;	
		this.name=name;
	}
	
	// Methods	
	/**
	 * The guard will check the adjacent Rooms
	 * @return True if he can see the player
	 */
	public boolean check(Player p, Room[][] tab){
		if (active){
			int x=currentRoom.getX();	// Abscissa
			int y=currentRoom.getY();	// Ordered	
			System.out.println("x : "+ x+"y : "+y);
			boolean north=tab[x][y+1].isPlayer();
			boolean south=tab[x][y-1].isPlayer();
			boolean east=tab[x-1][y].isPlayer();
			boolean west=tab[x+1][y].isPlayer();
			boolean current=tab[x][y].isPlayer();
			if (north||south||east||west||current){return true&&!bribed&&!p.isHidden();}	// If he see the player
		}
		return false;
	}
	/**
	 * Only check if he sees the player
	 * @param p		The player
	 * @param tab	The map
	 * @return		True if he sees the player
	 */
	public boolean act(Player p, Room[][]tab){return check(p,tab);}

	
	// Setters
	public void switche(){active=!active;}

}
