package command;

import item.Item;
import entity.Player;
import main.Main;

public class Drop extends Command{

    /**
     * Drop an object of your inventory in the current Room
     */
    @Override
    public String act(String secondWord, Main main) {
        String itemToDrop=secondWord;
        Player hero=main.getHero();
        Item it = hero.getItem(itemToDrop);
        if(it==null){
            return "There isn't any "+itemToDrop+" to drop.";
        }
        hero.drop(itemToDrop,0);
        hero.getCurrentRoom().addItem(it);
        return itemToDrop+" has been dropped on the floor.";
    }
}
