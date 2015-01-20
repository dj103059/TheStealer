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
    private ArrayList<Item> listofitem;
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
        listofitem = new ArrayList<Item>();
        listofentity = new ArrayList<Entity>();
    }
    
    /**remove and add in the list of item and list of entity
     */
    public boolean removeItem(Item item){
    	return this.listofitem.remove(item);
    }
    
    public boolean removeEntity(Entity entity){
    	return this.listofentity.remove(entity);
    }
    
    public void addItem(Item item){
    	this.listofitem.add(item);
    }
    
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
    	 String des = "there are " + listofitem.size() + " item in the room : \n" ;
		for(int i =0; i< listofitem.size();i++){
			des += listofitem.get(i).getName() +" weight : "+listofitem.get(i).getWeight()+ " ,\n ";
		}
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
	
    public ArrayList<Item> getListofitem() {
		return listofitem;
	}
    
    public ArrayList<Entity> getListofentity() {
		return listofentity;
	}
    public boolean isPlayer(){
    	ArrayList<Entity> tmp=this.getListofentity();
    	for (Entity t : tmp){
    		if (t.getType()==3){return true;}
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
}
