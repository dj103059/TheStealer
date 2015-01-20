package item;
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
	 */
	public String use(){return "";};
	
	@Override
	public String toString(){
		return "Name of item : " + this.name+ "/n description of this item : "+this.description+"/n";
	}
	@Override
	public boolean equals(Object obj){
		Item tmp=(Item)obj;
		if (this.getName().equals(tmp.getName())){return true;}
		return false;
	}
}
