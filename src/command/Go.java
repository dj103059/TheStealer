package command;

import room.Room;
import main.Main;

public class Go extends Command{
    
    /**
     * Move the player as he wanted
     * @param secondWord
     *          the second word the user has typed
     * @return a String which describes what happens
     */
    @Override
    public String act(String secondWord, Main main) {
        int[] coordinate = new int[2];
        //get the map and the coordinates
        Room[][] map = main.getBankMap();
        coordinate[0] = main.getCoordinateX();
        coordinate[1] = main.getCoordinateY();
        Room nextRoom;
        //catch the next coordinate if the coordinates are correct
        switch(secondWord){
        case "west":
            if(coordinate[0]-1>=0){
                coordinate[0]=coordinate[0]-1;
            }else{
                return "You can't go west";
            }
            break;
        case "east":
            if(coordinate[0]+1<map[0].length){
                coordinate[0]=coordinate[0]+1;
            }else{
                return "You can't go east";
            }
            break;
        case "north":
            if(coordinate[1]-1>=0){
                coordinate[1]=coordinate[1]-1;
            }else{
                return "You can't go north";
            }
            break;
        case "south":
            if(coordinate[1]+1<map[1].length){
                coordinate[1]=coordinate[1]+1;
            }else{
                return "You can't go south.";
            }
            break;
        default:
            return "Go where?";
        }
        //catch the next room
        nextRoom=map[coordinate[0]][coordinate[1]];
        //if the next room isn't null and if you can enter, does the movement
        if(nextRoom!=null && nextRoom.canEnter(main.getHero().getInventory())){
            main.setCoordinate(coordinate);
            return "You go "+secondWord;
        }
        return "You can't go "+secondWord;
    }
}
