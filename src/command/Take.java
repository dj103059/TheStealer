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

public class Take extends Command{

    /**
     * The player takes an object in his current room
     */
    @Override
    public String act(String secondWord, Main main) {
        String itemToTake=secondWord;
        Items correctItems=new Items();
        //get all the correct items from EnumItem
        EnumItem item = correctItems.getItem(secondWord);
        Player hero = main.getHero();
        Room currentRoom=hero.getCurrentRoom();
        //if he wants to take the gold
        if(item.equals(EnumItem.GOLD) && currentRoom.equals(new RoomGold("default room gold","roomgold"))){
            RoomGold goldRoom=(RoomGold) currentRoom;
            int gold = getGold(main);
            //verify if he can take this amount of gold
            if(goldRoom.getGold()<gold){
                return "The gold room hasn't this much gold in it.";
            }
            System.out.println("gold : "+gold);
            if(!hero.add(null,gold)){
                return "You are too heavy, you can't take this amount of gold";
            }
            goldRoom.setGold(goldRoom.getGold()-gold);
            return "You take "+gold+" gold";
        }
        //otherwise he wants to take an item
        Item it = currentRoom.getItem(itemToTake);
        if(it==null){
            return "There isn't any "+itemToTake+" to take.";
        }
        //we add it in his inventory
        hero.add(it,0);
        //we remove it from the current room
        currentRoom.removeItem(it);
        return itemToTake+" has been took on the floor.";
    }
    
    /**
     * Ask the user how much money he wants
     * @param main
     *          the main
     * @return the number of gold he wants
     */
    private int getGold(Main main){
        EnumGolds correctGold=new EnumGolds();
        @SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
        String inputLine;   // will hold the full input line
        String word1 = null;
        do{
            main.printer("How much gold do you want to take? ");
            main.printer("You can only take : "+correctGold.showAll());
            main.printer("> ");     // print prompt
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
