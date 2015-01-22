package room;

import item.Item;
import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * a room 
 *
 */
public class Room {
	//name of the room
	private String name;
	//description of the room
	private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    //list of item in the room
    private HashMap<String,Item> listofitem;
    //list of entity in the room (guard or player)
    private ArrayList<Entity> listofentity;
    //position of the room in the map
    private int x;
    private int y;
    private int noise = 0;
    
    
    
    /**
     * if the room contains the entity en , return true
     * @param en
     * @return
     */
    public boolean containsEntity(Entity en){
    	return listofentity.contains(en);
    	
    }
    
    public boolean containsItem(Item en){
    	return listofitem.containsValue(en);
    	
    }
    
    /**
     * Create a room described "description". Initially, it has no exits.
     * 
     * @param description
     *            The room's description.
     */
    public Room(String description,String name) {
        this.description = description;
        this.name = name;
        exits = new HashMap<>();
        listofitem = new HashMap<String,Item>();
        listofentity = new ArrayList<Entity>();
    }
    
    /**remove and add in the list of item and list of entity
     */
    public boolean removeItem(Item item){
    	if(this.listofitem.remove(""+item) != null){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Remove the entity in parameter of the listofentity
     * 
     * @param entity
     * @return
     */
    
    public boolean removeEntity(Entity entity){
    	return this.listofentity.remove(entity);
    }
    
    /**
     * 
     * Add to the listofitem of the item in parameter
     * @param item
     */
    public void addItem(Item item){
    	if(this.listofitem.put(""+item,item) != null){
    		System.out.println("succes");
    	}
    			
    }
    
    /**
     * Add the entity to the listofentity of the room
     * @param entity
     */
    public void addEntity(Entity entity){
    	this.listofentity.add(entity);
    }
    /**
     * Define an exit from this room.
     * 
     * @param direction
     *            The direction of the exit.
     * @param neighbor
     *            The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room (the one that was defined in
     *         the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form: You are in the kitchen.
     * Exits: north west
     * 
     * @return A long description of this room
     */
    public String getLongDescription() {
    	String des = "You are " + description ;
    	des += descriptionofitem();
    	des +=  "\n" +  getExitString();
        return des ;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    protected String descriptionofitem(){
    	 String des = ", there are " + listofitem.size() + " item in the room : \n" ;
		
			des += this.printItem();
	return des;
    	
    }
    

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction
     *            The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /*
     * getter and setter
     */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
    public HashMap<String,Item> getListofitem() {
		return listofitem;
	}
    
    public ArrayList<Entity> getListofentity() {
		return listofentity;
	}
    public boolean isPlayer(){
    	ArrayList<Entity> tmp=this.getListofentity();
    	for (Entity t : tmp){
    		if (t.getName().equals("hero")){return true;}
    	}
    	return false;
    }

	public int getNoise() {
		
			return noise;
		}
	

	public void setNoise(int noise) {
		this.noise = noise;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description){
		this.description=description;
	
	}
	
	public boolean canEnter(HashMap<String, Item> inventory)
	{
		return true;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this==obj){return true;}
		else if (obj instanceof Room){
			Room tmp=(Room) obj;
			if (this.getListofitem().equals(tmp.getListofitem())){
				if (this.getName().equals(tmp.getName())){
					if (this.getShortDescription().equals(tmp.getShortDescription())){
						if(this.getListofentity().equals(tmp.getListofentity())){
							if(this.getExitString().equals(tmp.getExitString())){
								if(this.getX()==(tmp.getX())){
									if(this.getY()==(tmp.getY())){
										if(this.getNoise()==(tmp.getNoise())){
											return true;
										}
										
									}
									
								}
								}
							}
						}
				}
			}
		}
		return false;
	}
	
	public Item getItem(String name)
	{
		
		return listofitem.get(name);
	
	}
	
	public String printItem()
	{
		String l="";
		
		
		for (String mapKey : listofitem.keySet()) {
			 l +=listofitem.get(mapKey)+", "+listofitem.get(mapKey).getDescription()+" weigth: "+listofitem.get(mapKey).getWeight()+"\n";
		
		}
		
		return l+"\n";
	}
}
