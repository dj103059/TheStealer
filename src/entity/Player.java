package entity;

import item.*;

import java.util.ArrayList;

public class Player extends Entity{
	private boolean hidden;
	private ArrayList<Item> inventory;
	private int weight=0;
	private int maxWeight;
	public Player(int maxWeight){
		inventory.add(new Map());
		inventory.add(new Clock());
		this.maxWeight=maxWeight;
		hidden=false;
	}
	
	public boolean isHidden(){return hidden;}
	// Getteurs
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
}
