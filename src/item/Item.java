package item;

import java.io.Serializable;

import main.Simulator;

/**
 * Every item inherit of Item class
 **/
public abstract class Item implements Serializable{
	//Name of item
	private String name; 
	//weight of the object
	private int weight;
	//Description 
	private String description; 
	
	/**
	 * Return the name of the item.
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the item.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get the description of the item.
	 * @return
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the item.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return the Weight of the item.
	 * @return
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Set the weight of the item
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Each item have a use. Use the item.
	 * @param simulator 
	 */
	public String use(Simulator simulator){return "";};
	
	@Override
	public String toString(){
		return this.name;
	}
	@Override
	public boolean equals(Object obj){
	    if (this == obj){
	        return true;
	    }
	    if(obj instanceof Item){
	        Item tmp=(Item)obj;
	        if (this.getName().equals(tmp.getName()) && this.getWeight()==tmp.getWeight()){return true;}
	    }
		return false;
	}
}
