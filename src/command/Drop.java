package command;

import java.util.Scanner;

import room.Room;
import room.RoomGold;
import item.Item;
import entity.Player;
import enumate.EnumGold;
import enumate.EnumGolds;
import enumate.EnumItem;
import enumate.Items;
import main.Main;

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
    public String act(String secondWord, Main main) {
        String itemToDrop=secondWord;
        Items correctItems=new Items();
        //get all the correct items from EnumItem
        EnumItem item = correctItems.getItem(secondWord);
        Player hero=main.getHero();
        Room currentRoom=hero.getCurrentRoom();
        //if he wants to drop the gold
        if(item.equals(EnumItem.GOLD) && currentRoom.equals(new RoomGold("default room gold","roomgold"))){
            RoomGold goldRoom=(RoomGold) currentRoom;
            int gold = getGold(main);
        
            //verify if he can drio this amount of gold
            if(hero.getGold()<gold){
                return "You haven't this amount of gold on you.";
            }
            hero.drop(null,gold);
            goldRoom.setGold(gold+goldRoom.getGold());;
            return "You drop "+gold+" gold";
        }
        Item it = hero.getItem(itemToDrop);
        if(it==null){
            return "There isn't any "+itemToDrop+" to drop.";
        }
        hero.drop(itemToDrop,0);
        hero.getCurrentRoom().addItem(it);
        return itemToDrop+" has been dropped on the floor.";
    }
    
    /**
     * Ask the user how much money he wants to drop
     * @param main
     *          the main
     * @return the number of gold he wants to drop
     */
    private int getGold(Main main){
        EnumGolds correctGold=new EnumGolds();
        @SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
        String inputLine;   // will hold the full input line
        String word1 = null;
        do{
            main.printer("How much gold do you want to drop? ");
            main.printer("You can only drop : "+correctGold.showAll());
            main.printer("And you have on you : "+main.getHero().getGold());
            inputLine = reader.nextLine();
            // Find up the word on the line.
            @SuppressWarnings("resource")
			Scanner tokenizer = new Scanner(inputLine);
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();      // get first word
            }
        }while(!correctGold.isGold(word1));  //does it while the word is incorrect (not 10,20,50,100,etc...)
        EnumGold gold = correctGold.getGold(word1);
        return gold.getNumber();
    }
}
