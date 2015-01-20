package entity;
import item.*;

import java.util.ArrayList;

public class Player extends Entity{
	private boolean hidden;
	private ArrayList<Item> inventory;
	private int gold=0;
	private int goldWeight=4;
	private int weight=0;
	private int maxWeight;
	
	// Constructor
	public Player(int maxWeight){
		this.maxWeight=maxWeight;
		hidden=false;
	}
	
	// Methods
	/**
	 * 
	 * @param i		Item
	 * @param gold	Amount of gold
	 * @param add	True if you add, false if you remove
	 * @return		The new weight 
	 */
	private int calculateWeight(Item i, int gold,  boolean add){
		int signe=1;;
		if (!add){signe=-1;}
		int weight=gold*goldWeight;
		if (i!=null){weight+=signe*i.getWeight();}
		if (gold !=0){weight+=signe*gold*goldWeight;}
		return weight;
	}
	/**
	 * 
	 * @param i		Item you want to add in the player inventory
	 * @param gold	Amount of gold you want to add to the player
	 * @return		False if he can't carry this weight
	 */
	public boolean add(Item i, int gold){
		int tmp=calculateWeight(i, gold, true);
		if (tmp<=maxWeight){
			if (i!=null){
				inventory.add(i);
			}
			this.gold+=gold;
			weight=tmp;
			return true;
		}else{return false;}
	}
	/**
	 * 
	 * @param i		Item the player want to drop
	 * @param gold	Amount of gold the player want to drop;
	 */
	public void drop(String name, int gold){
		Item delete=null;
		for (Item j : inventory){if (j.getName().equals(name)){inventory.remove(j);delete=j;break;}}
		this.gold-=gold;
		weight=calculateWeight(delete,gold, false);
	}
	
	// Getters
	public boolean isHidden(){return hidden;}
	public int getWeight(){return weight;}
	public int getMaxWeight(){return maxWeight;}
	public ArrayList<Item> getInventory(){return inventory;}
	
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
	@Override
	public int getType(){return 3;}
}
