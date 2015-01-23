package command;

import item.Item;
import entity.Player;
import main.Simulator;

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

}
