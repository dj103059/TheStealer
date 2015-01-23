package item;

import main.Simulator;

/**
 * Every item inherit of Item class
 **/
public abstract class Item {
	//Name of item
	private String name; 
	//weight of the object
	private int weight;
	//Description 
	private String description; 
	
	/** getter and setter
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Each item have a use
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
