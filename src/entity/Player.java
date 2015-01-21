package entity;
import item.*;
import room.*;

import java.util.HashMap;
/**
 * This class defines the Player
 * @author user
 *
 */
public class Player extends Entity{
	// If the player can act
	private boolean act=true;
	// If the player is hidden
	private boolean hidden=false;
	// Inventory of the player
	private HashMap<String, Item> inventory=new HashMap<String, Item>();
	// His current gold
	private int gold=0;
	// Weight of the gold weight
	private int goldWeight=4;
	// Weight of what he is carrying
	private int weight=0;
	// Maximum weight he can carry
	private int maxWeight;
	
	// Constructor
	public Player(Room currentRoom, int maxWeight, String name){this.maxWeight=maxWeight;this.name=name;this.currentRoom=currentRoom;}
	
	// Methods
	/**
	 * Calculate the weight if we add/remove item/gold
	 * @param i		Item
	 * @param gold	Amount of gold
	 * @param add	True if you add, false if you remove
	 * @return		The new weight 
	 */
	private int calculateWeight(Item i, int gold,  boolean add){
		int signe=1;;
		if (!add){signe=-1;}
		int weightTmp=weight;
		if (i!=null){weightTmp+=signe*i.getWeight();}
		if (gold !=0){weightTmp+=signe*gold*goldWeight;}
		return weightTmp;
	}
	/**
	 * Reinitialize the noise 
	 * @param tab Rooms
	 */
	private void resetNoise(Room[][] tab){
		int x=currentRoom.getX();
		int y= currentRoom.getY();
		tab[x][y+1].setNoise(0);
		tab[x+1][y+1].setNoise(0);
		tab[x-1][y+1].setNoise(0);
		tab[x][y-1].setNoise(0);
		tab[x+1][y-1].setNoise(0);
		tab[x-1][y-1].setNoise(0);
		tab[x-1][y].setNoise(0);
		tab[x-1][y-1].setNoise(0);
		tab[x-1][y+1].setNoise(0);
		tab[x+1][y].setNoise(0);
		tab[x+1][y+1].setNoise(0);
		tab[x+1][y-1].setNoise(0);
		tab[x][y].setNoise(0);
		if (x+2<tab.length-2){tab[x+2][y].setNoise(0);}
		if (x-2>0){tab[x-2][y].setNoise(1);}
		if (y+2<tab.length-2){tab[x][y+2].setNoise(1);}
		if (y-2>0){tab[x][y-2].setNoise(1);}
	}
	/**
	 * Add noise to the rooms
	 * @param tab Rooms
	 */
	public void addNoise(Room[][] tab){
		int x=currentRoom.getX();
		int y= currentRoom.getY();
		tab[x][y].setNoise(3);
		if (!tab[x+1][y].equals(new Wall())){
			tab[x+1][y].setNoise(2);
			tab[x+1][y+1].setNoise(1);
			tab[x+1][y-1].setNoise(1);
			if (x+2<tab.length-2){tab[x+2][y].setNoise(1);}
		}
		if (!tab[x-1][y].equals(new Wall())){
			tab[x-1][y].setNoise(2);
			tab[x-1][y+1].setNoise(1);
			tab[x-1][y-1].setNoise(1);
			if (x-2>0){tab[x-2][y].setNoise(1);}
		}
		if (!tab[x][y+1].equals(new Wall())){
			tab[x+1][y].setNoise(2);
			tab[x+1][y+1].setNoise(1);
			tab[x-1][y+1].setNoise(1);
			if (y+2<tab.length-2){tab[x][y+2].setNoise(1);}
		}
		if (!tab[x][y-1].equals(new Wall())){
			tab[x+1][y].setNoise(2);
			tab[x+1][y-1].setNoise(1);
			tab[x-1][y-1].setNoise(1);
			if (y-2>0){tab[x][y-2].setNoise(1);}
		}
		
	}
	/**
	 * Add a item/gold ti the player inventory
	 * 
	 * @param i		Item you want to add in the player inventory
	 * @param gold	Amount of gold you want to add to the player
	 * @return		False if he can't carry this weight
	 */
	public boolean add(Item i, int gold){
		if (!act){return false;}
		int tmp=calculateWeight(i, gold, true);
		if (tmp<=maxWeight){
			if (i!=null){inventory.put(i+"",i);}
			this.gold+=gold;
			weight=tmp;
			return true;
		}else{return false;}
	}
	/**
	 * Only to initiate the player inventory
	 * @param i Item you want to add to the player inventory
	 */
	public void init(Item i){inventory.put(i+"",i);calculateWeight(i, 0, true);}
	/**
	 * Allows the player to drop an item
	 * @param i		Item the player want to drop
	 * @param gold	Amount of gold the player want to drop;
	 * @return 		True if he did it
	 */
	public boolean drop(String name, int gold){
		if (!act){return false;}
		Item delete=inventory.get(name);
		if (delete==null){return false;}
		this.gold-=gold;
		weight=calculateWeight(delete,gold, false);
		inventory.remove(name);
		return true;
	}
	/**
	 * Update the room and the noise
	 * @param next Next Room
	 * @param tab	Rooms
	 */
	public void move(Room next, Room[][] tab){
		resetNoise(tab);
		currentRoom=next;
		addNoise(tab);
	}
	
	// Getters
	public boolean isHidden(){return hidden;}
	public int getWeight(){return weight;}
	public int getMaxWeight(){return maxWeight;}
	public HashMap<String, Item> getInventory(){return inventory;}
	public Item getItem(String name){return inventory.get(name);}
	
	// Setters
	public void hide(){hidden=true;}
	public void show(){hidden=false;}
	public void freeze(){act=false;}
	public void unFreeze(){act=true;}
	
	// Equals override
	@Override
	public boolean equals(Object obj){
		if (this==obj){return true;}
		else if (obj instanceof Player){
			Player tmp=(Player) obj;
			if (this.getInventory()==tmp.getInventory()){
				if (this.getWeight()==tmp.getWeight()){
					if (this.getMaxWeight()==tmp.getMaxWeight()){return true;}
				}
			}
		}
		return false;
	}
}
