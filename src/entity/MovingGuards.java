package entity;
import java.util.ArrayList;
import java.util.Random;

import room.*;
/**
 * Defines the moving guards
 * @author user
 *
 */
public class MovingGuards extends Guards{
	// Constructor
	public MovingGuards(Room currentRoom, boolean bribed, boolean active, String name) {super(currentRoom, bribed, active,name);}
	
	// Methods
	/**
	 * Generate a random move
	 */
	private void randomMove(Room[][] tab){
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		ArrayList<Room> next=new ArrayList<Room>();
		Room tmp;
		tmp=tab[x+1][y];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x-1][y];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x][y+1];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x][y-1];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		currentRoom.removeEntity(this);
		int ran= new Random().nextInt(next.size());
		currentRoom=next.get(ran);
		currentRoom.addEntity(this);
	}
	
	/**
	 * Allows passage from one room to another, and manages noise
	 */
	private void change(Room[][] tab){
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		int noiseNorth=tab[x][y+1].getNoise()+tab[x+1][y+1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseSouth=tab[x][y-1].getNoise()+tab[x+1][y-1].getNoise()+tab[x-1][y-1].getNoise();
		int noiseWest=tab[x-1][y].getNoise()+tab[x-1][y-1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseEast=tab[x+1][y].getNoise()+tab[x+1][y+1].getNoise()+tab[x+1][y-1].getNoise();
		int max=max(noiseNorth, noiseSouth, noiseEast, noiseWest);
		if ((max==noiseNorth)&&(max!=0)){
			if(!tab[x][y+1].equals(new Wall())){currentRoom.removeEntity(this); currentRoom=tab[x][y+1]; currentRoom.addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseSouth)&&(max!=0)){
			if(!tab[x][y-1].equals(new Wall())){currentRoom.removeEntity(this); currentRoom=tab[x][y-1]; currentRoom.addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseEast)&&(max!=0)){
			if(!tab[x-1][y].equals(new Wall())){currentRoom.removeEntity(this); currentRoom=tab[x-1][y]; currentRoom.addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseWest)&&(max!=0)){
			if(!tab[x+1][y].equals(new Wall())){currentRoom.removeEntity(this); currentRoom=tab[x+1][y]; currentRoom.addEntity(this);}
			else{randomMove(tab);}
		}
		else if (max==0){randomMove(tab);}
	} 

	/**
	 * Return the max value of the four
	 * @param val1 first value
	 * @param val2 second value
	 * @param val3 third value
	 * @param val4 fourth value
	 * @return return the max value
	 */
	private int max(int val1, int val2, int val3, int val4){
		if ((val1>=val2)&&(val1>=val3)&&(val1>=val4)){return val1;}
		else if ((val2>=val1)&&(val2>=val3)&&(val2>=val4)){return val2;}
		else if ((val3>=val1)&&(val3>=val2)&&(val3>=val4)){return val3;}
		else if ((val4>=val1)&&(val4>=val3)&&(val4>=val2)){return val4;}
		return 0;
	}
	
	@Override
	/**
	 * This time, move the guard and check if he sees the player
	 */
	public boolean act(Player p, Room[][]tab){this.change(tab);return this.check(p, tab);}
	
	// Setters
	public void corrupt(){bribed=true;}
}


