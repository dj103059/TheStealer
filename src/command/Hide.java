package command;

import java.util.HashMap;

import item.Item;
import entity.Player;
import enumate.EnumItem;
import main.Simulator;

/**
 * Command to hide yourself under a box if there is one in the room
 * @author Tom Dall'Agnol
 *
 */
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

    /**
     * 'hide' to hide under a box
     */
    @Override
    public String help(){
        return "Hide yourself in a room WITH A BOX. Use : Type 'hide' to hide yourself under it during 4 turns (Be carefull, guards keeps moving!)";
    }
}
