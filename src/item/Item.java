package item;
/**
 * Every item inherit of Item class
 **/
public abstract class Item {
	//Name of item
	private String name; 
	//weigth of the object
	private int weigth;
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

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}

	/**
	 * Each item have a use
	 */
	public String use(){return "";};
	
	@Override
	public String toString(){
		return "Name of item : " + this.name+ "/n description of this item : "+this.description+"/n";
	}
}
