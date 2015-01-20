package command;

import room.Room;
import item.Item;
import entity.Player;
import main.Main;

public class Take extends Command{

    /**
     * The player takes an object in his current room
     */
    @Override
    public String act(String secondWord, Main main) {
        String itemToTake=secondWord;
        Player hero = main.getHero();
        Room currentRoom=hero.getCurrentRoom();
        Item it = currentRoom.getItem(itemToTake);
        if(it==null){
            return "There isn't any "+itemToTake+" to take.";
        }
        hero.add(it,0);
        currentRoom.removeItem(it);
        return itemToTake+" has been dropped on the floor.";
    }

}
