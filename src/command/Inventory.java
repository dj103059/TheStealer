package command;

import java.util.HashMap;
import java.util.Map.Entry;

import item.Item;
import entity.Player;
import main.Simulator;

/**
 * Command to show the inventory of the player
 * @author Tom Dall'Agnol
 *
 */
public class Inventory extends Command {

    /**
     * Show the player's inventory
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        Player hero=simul.getHero();
        HashMap<String,Item> inventory=hero.getInventory();
        //Add what how much kg you can still carry
        String textInventory="You can still carry "+(hero.getMaxWeight()-hero.getWeight())+"kg.\nYou have "+hero.getGold()+" gold on you";
        if(inventory.isEmpty()){
            textInventory+=" Nothing in your inventory.\n";
            return textInventory;
        }
        textInventory+=":  ";
        for(Entry<String,Item> entry : inventory.entrySet()){
            textInventory+=entry.getKey()+"  ";
        }
        return textInventory+"\n";
    }
    
    /**
     * 'inventory' to show the player's inventory
     */
    @Override
    public String help(){
        return"Show the player's inventory. Use : 'inventory'";
    }
}
