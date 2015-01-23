package command;

import room.Room;
import room.RoomGold;
import item.Item;
import entity.Player;
import enumate.EnumItem;
import enumate.Items;
import main.Main;
import main.Simulator;

/**
 * The command to Drop an item or gold in the current room
 * We use it like that : "drop item" or "drop gold" and the amount of gold
 *
 */
public class Drop extends Command{

    /**
     * Drop an object of your inventory in the current Room
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        String itemToDrop=secondWord;
        Items correctItems=new Items();
        //get all the correct items from EnumItem
        EnumItem item = correctItems.getItem(secondWord);
        Player hero=simul.getHero();
        Room currentRoom=hero.getCurrentRoom();
        //if he wants to drop the gold
        if(item.equals(EnumItem.GOLD) && currentRoom.equals(new RoomGold("default room gold","roomgold"))){
            RoomGold goldRoom=(RoomGold) currentRoom;
            int gold = Main.parseGold("drop");
        
            //verify if he can drop this amount of gold
            if(hero.getGold()<gold){
                return "You haven't this amount of gold on you.\n";
            }
            hero.drop(null,gold);
            goldRoom.setGold(gold+goldRoom.getGold());
            simul.actGuards();
            return "You drop "+gold+" gold\n";
        }
        Item it = hero.getItem(itemToDrop);
        if(it==null){
            return "There isn't any "+itemToDrop+" to drop.\n";
        }
        hero.drop(itemToDrop,0);
        hero.getCurrentRoom().addItem(it);
        String end = simul.endTurn();
        return itemToDrop+" has been dropped on the floor.\n"+end;
    }
}
