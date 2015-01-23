package command;

import room.Room;
import room.RoomGold;
import item.Item;
import entity.Player;
import enumate.EnumItem;
import enumate.Items;
import main.Main;
import main.Simulator;

public class Take extends Command{

    /**
     * The player takes an object in his current room
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        String itemToTake=secondWord;
        Items correctItems=new Items();
        //get all the correct items from EnumItem
        EnumItem item = correctItems.getItem(secondWord);
        Player hero = simul.getHero();
        Room currentRoom=hero.getCurrentRoom();
        //if he wants to take the gold
        if(item.equals(EnumItem.GOLD) && currentRoom.equals(new RoomGold("default room gold","roomgold"))){
            RoomGold goldRoom=(RoomGold) currentRoom;
            int gold = Main.parseGold("take");
            //verify if he can take this amount of gold
            if(goldRoom.getGold()<gold){
                return "The gold room hasn't this much gold in it.\n";
            }
            System.out.println("gold : "+gold);
            if(!hero.add(null,gold)){
                return "You are too heavy, you can't take this amount of gold\n";
            }
            goldRoom.setGold(goldRoom.getGold()-gold);
            String end = simul.endTurn();
            return "You take "+gold+" gold\n"+end;
        }
        //otherwise he wants to take an item
        Item it = currentRoom.getItem(itemToTake);
        if(it==null){
            return "There isn't any "+itemToTake+" to take.\n";
        }
        //we add it in his inventory
        hero.add(it,0);
        //we remove it from the current room
        currentRoom.removeItem(it);
        String end = simul.endTurn();
        return itemToTake+" has been took on the floor.\n"+end;
    }
    
}
