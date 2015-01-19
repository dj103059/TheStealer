package room;

import item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import entity.Entity;

public class Room {
	private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> listofitem;
    private ArrayList<Entity> listofentity;
    
    
    public boolean containsEntity(Entity en){
    	listofentity.contains(en);
    	return true;
    }
    
    public ArrayList<Item> getListofitem() {
		return listofitem;
	}
    
    public ArrayList<Entity> getListofentity() {
		return listofentity;
	}
    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * @param description
     *            The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        listofitem = new ArrayList<Item>();
        listofentity = new ArrayList<Entity>();
    }
    
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
			des += listofitem.get(i).getName() +" weight : "+listofitem.get(i).getWeigth()+ " ,\n ";
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


}
