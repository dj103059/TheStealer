package command;

import item.Item;
import entity.Player;
import main.Simulator;

/**
 * Command to use an item from the current room or from the player's inventory
 * @author Tom Dall'Agnol
 *
 */
public class Use extends Command {

    /**
     * Use an object which is in the current room or in the player's inventory
     */
    @Override
    public String act(String secondWord, Simulator simulator) {
        String itemToUse=secondWord;
        Player hero=simulator.getHero();
        Item it = hero.getItem(itemToUse);
        if(it==null){
            it= hero.getCurrentRoom().getItem(itemToUse);
        }
        if(it==null){
            return "There isn't any "+itemToUse+".\n";
        }
        return it.use(simulator)+"\n";
    }
    
    /**
     * use + name of an object in the room or on you
     */
    @Override
    public String help(){
        return "Use an object from the current room or from your inventory. Use : Type 'use + nameOfTheItem'";
    }
}
