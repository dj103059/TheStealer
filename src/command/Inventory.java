package command;

import java.util.HashMap;
import java.util.Map.Entry;

import item.Item;
import entity.Player;
import main.Main;

public class Inventory extends Command {

    /**
     * Show the player's inventory
     */
    public String act(String secondWord, Main main) {
        Player hero=main.getHero();
        HashMap<String,Item> inventory=hero.getInventory();
        String textInventory="You have ";
        if(inventory.isEmpty()){
            textInventory+=" nothing in your inventory.";
            return textInventory;
        }
        textInventory+=":  ";
        for(Entry<String,Item> entry : inventory.entrySet()){
            textInventory+=entry.getKey()+"  ";
        }
        return textInventory;
    }

}
