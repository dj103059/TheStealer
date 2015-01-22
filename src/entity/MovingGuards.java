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
		int x=getCurrentRoom().getX();	// Abscissa
		int y=getCurrentRoom().getY();	// Ordered
		ArrayList<Room> next=new ArrayList<Room>();	// List of rooms where he can go
		Room tmp;
		tmp=tab[x+1][y];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x-1][y];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x][y+1];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		tmp=tab[x][y-1];
		if  (!tmp.equals(new Wall())){next.add(tmp);}
		getCurrentRoom().removeEntity(this);
		int ran= new Random().nextInt(next.size());
		setCurrentRoom(next.get(ran));
		getCurrentRoom().addEntity(this);
	}
	/**
	 * 
	 * @param p The player
	 * @return	True if the guard is bribed
	 */
	private boolean corrupt(Player p){
		double ratio=p.getGold()/100;
		double ran=Math.random();
		if (ran<=ratio){
			p.setGold(p.getGold()/2);
			setBribed(true);
			System.out.println("You bribed the guard and you lost "+p.getGold()+" gold\n");
			return true;
		}else{
			System.out.println("You are not able to bribed the guard\n");
			return false;	
		}
	}
	/**
	 * Allows passage from one room to another, and manages noise
	 */
	private void change(Room[][] tab){
		int x=getCurrentRoom().getX();	// Abscissa
		int y=getCurrentRoom().getY();	// Ordered
		int noiseNorth=tab[x][y+1].getNoise()+tab[x+1][y+1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseSouth=tab[x][y-1].getNoise()+tab[x+1][y-1].getNoise()+tab[x-1][y-1].getNoise();
		int noiseWest=tab[x-1][y].getNoise()+tab[x-1][y-1].getNoise()+tab[x-1][y+1].getNoise();
		int noiseEast=tab[x+1][y].getNoise()+tab[x+1][y+1].getNoise()+tab[x+1][y-1].getNoise();
		if ((x+2<tab.length-2)&&!(tab[x+1][y].equals(new Wall()))){noiseEast+=tab[x+2][y].getNoise();}		// He can't hear through walls
		if ((x-2>0)&&!(tab[x-1][y].equals(new Wall()))){noiseWest+=tab[x-2][y].getNoise();}					// He can't hear through walls
		if ((y+2<tab.length-2)&&!(tab[x][y+1].equals(new Wall()))){noiseNorth+=tab[x][y+2].getNoise();}		// He can't hear through walls
		if ((y-2>0)&&!(tab[x][y-1].equals(new Wall()))){noiseSouth+=tab[x][y-2].getNoise();}				// He can't hear through walls
		int max=max(noiseNorth, noiseSouth, noiseEast, noiseWest);											// Which side is the loudest
		if ((max==noiseNorth)&&(max!=0)){
			if(!tab[x][y+1].equals(new Wall())){getCurrentRoom().removeEntity(this); setCurrentRoom(tab[x][y+1]); getCurrentRoom().addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseSouth)&&(max!=0)){
			if(!tab[x][y-1].equals(new Wall())){getCurrentRoom().removeEntity(this); setCurrentRoom(tab[x][y-1]); getCurrentRoom().addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseEast)&&(max!=0)){
			if(!tab[x-1][y].equals(new Wall())){getCurrentRoom().removeEntity(this); setCurrentRoom(tab[x+1][y]); getCurrentRoom().addEntity(this);}
			else{randomMove(tab);}
		}
		else if ((max==noiseWest)&&(max!=0)){
			if(!tab[x+1][y].equals(new Wall())){getCurrentRoom().removeEntity(this); setCurrentRoom(tab[x-1][y]); getCurrentRoom().addEntity(this);}
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
	 * This time, move the guard and check if he sees the player, and manages the corruption
	 */
	public boolean act(Player p, Room[][]tab){
		if (this.check(p, tab)){if(!corrupt(p)){return true;}}
		else{
			this.change(tab);
			if (this.check(p, tab)){
				if (corrupt(p)){return false;}
				else{return true;}
			}
		}
		return false;
	}
}


