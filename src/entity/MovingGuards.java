package entity;
import java.util.ArrayList;

import room.*;
public class MovingGuards extends Guards{

	public MovingGuards(Room currentRoom, boolean bribed, boolean active) {
		super(currentRoom, bribed, active);
	}
	/**
	 * Generate a random move
	 */
	public void randomMove(Room[][] tab){
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		ArrayList<Room> next=new ArrayList<Room>();
		Room tmp;
		tmp=tab[x+1][y];
		if  (tmp!=null){next.add(tmp);}
		tmp=tab[x-1][y];
		if  (tmp!=null){next.add(tmp);}
		tmp=tab[x][y+1];
		if  (tmp!=null){next.add(tmp);}
		tmp=tab[x][y-1];
		if  (tmp!=null){next.add(tmp);}
		int ran=(int)Math.random()*next.size();
		currentRoom=next.get(ran);
		}
	/**
	 * Allows passage from one room to another	
	 */
	public void change(Room[][] tab){
		int x=currentRoom.getX();	// Abscissa
		int y=currentRoom.getY();	// Ordered
		int noiseNorth=tab[x][y+1].getNoise()+tab[x+1][y+1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseSouth=tab[x][y-1].getNoise()+tab[x+1][y-1].getNoise()+tab[x-1][y-1].getNoise();
		int noiseEast=tab[x-1][y].getNoise()+tab[x-1][y-1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseWest=tab[x+1][y].getNoise()+tab[x+1][y+1].getNoise()+tab[x+1][y-1].getNoise();
		int max=max(noiseNorth, noiseSouth, noiseEast, noiseWest);
		if (max==noiseNorth){
			currentRoom=tab[x][y+1];
		}
		else if (max==noiseSouth){
			currentRoom=tab[x][y-1];
		}
		else if (max==noiseEast){
			currentRoom=tab[x-1][y];
		}
		else if (max==noiseWest){
			currentRoom=tab[x+1][y];
		}
		else if (max==0){
			randomMove(tab);
		}
	} 
	/**
	 * Manage the movements of the guards
	 * @return True if the guards see a player
	 * 
	 */
	@Override
	public boolean move(Player p, Room[][] tab){
		change(tab);
		return check(p,tab);
	}
	public int max(int val1, int val2, int val3, int val4){
		if ((val1>val2)&&(val1>val3)&&(val1>val4)){return val1;}
		else if ((val2>=val1)&&(val2>=val3)&&(val2>=val4)){return val2;}
		else if ((val3>=val1)&&(val3>=val2)&&(val3>=val4)){return val3;}
		else if ((val4>=val1)&&(val4>=val3)&&(val4>=val2)){return val4;}
		return 0;
	}
	// Setters
	public void corrupt(){bribed=true;}
}


