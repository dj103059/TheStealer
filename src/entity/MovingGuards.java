package entity;
import room.*;
public class MovingGuards extends Guards{

	public MovingGuards(Room currentRoom, boolean bribed, boolean active) {
		super(currentRoom, bribed, active);
	}
	/**
	 * Generate a random move
	 */
	public void randomMove(){
		/**
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		ArrayList<Room> next;
		Room tmp;
		tmp=get(x+1,y);
		if  (tmp){next.add(tmp));
		tmp=get(x-1,y);
		if  (tmp){next.add(tmp));
		tmp=get(x,y+1);
		if  (tmp){next.add(tmp));
		tmp=get(x,y-1);
		if  (tmp){next.add(tmp));
		int ran=Maths.random()*next.size();
		currentRoom=next.get(ran);
		**/
				
	}
	/**
	 * Allows passage from one room to another	
	 */
	public void change(){
		/**
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		int noiseNorth=get(x,y+1).getNoise()+get(x+1,y+1).getNoise()+get(x-1,y+1).getNoise();
		int noiseSouth=get(x,y-1).getNoise()+get(x+1,y-1).getNoise()+get(x-1,y-1).getNoise();
		int noiseEast=get(x-1,y).getNoise()+get(x-1,y-1).getNoise()+get(x-1,y+1).getNoise();
		int noiseWest=get(x+1,y).getNoise()+get(x+1,y+1).getNoise()+get(x+1,y-1).getNoise();
		int max=maths.max(noiseNorth, noiseSouth, noiseEast, noiseWest);
		if (max==noiseNorth){
			currentRoom=get(x,y+1);
		}
		else if (max==noiseSouth){
			currentRoom=get(x,y-1);
		}
		else if (max==noiseLeft){
			currentRoom=get(x-1,y);
		}
		else if (max==noiseRight){
			currentRoom=get(x+,y);
		}
		else if (max==0){
			randomMove();
		}
		**/
	} 
	/**
	 * Manage the movements of the guards
	 * @return True if the guards see a player
	 */
	public boolean move(){
		change();
		return check();
	}
	// Setters
	public void corrupt(){bribed=true;}
}


