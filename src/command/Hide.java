package command;

import java.util.HashMap;

import item.Item;
import entity.Player;
import enumate.EnumItem;
import main.Simulator;

public class Hide extends Command{

    /**
     * If there is a box in the room where the player is, he hides,
     * otherwise he can't.
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        Player hero = simul.getHero();
        HashMap<String,Item> roomInventory = hero.getCurrentRoom().getListofitem();
        if(roomInventory.containsKey(EnumItem.BOX.toString())){
            hero.hide(simul.getBankMap());
            String end=simul.endTurn();
            return "You are hiding under a box.\n"+end;
        }
        return "You can't hide here, there isn't any box.\n";
    }

}
