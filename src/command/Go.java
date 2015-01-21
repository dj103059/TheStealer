package command;

import java.util.HashMap;

import entity.Player;
import enumate.EnumDirection;
import room.Room;
import room.Wall;
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
        //the useful rooms
        Room currentRoom = main.getCurrentRoom(); 
        Room nextRoom;
        //check if you can move
        boolean canMove=false;
        //find the direction you want :
        EnumDirection direct = getEnumDirection(secondWord);
        //catch the next coordinate if the coordinates are correct
        switch(direct){
        case WEST:
            if(coordinate[0]-1>=0){
                coordinate[0]=coordinate[0]-1;
                canMove=true;
            }
            break;
        case EAST:
            if(coordinate[0]+1<map[0].length){
                coordinate[0]=coordinate[0]+1;
                canMove=true;
            }
            break;
        case NORTH:
            if(coordinate[1]-1>=0){
                coordinate[1]=coordinate[1]-1;
                canMove=true;
            }
            break;
        case SOUTH:
            if(coordinate[1]+1<map[1].length){
                coordinate[1]=coordinate[1]+1;
                canMove=true;
            }
            break;
        default:
            return "Go where?";
        }
        //catch the next room
        nextRoom=map[coordinate[0]][coordinate[1]];
        //if the next room isn't null and if you can enter, do the movement
        if(canMove && nextRoom.canEnter(main.getHero().getInventory())){
            Player hero=main.getHero();
            main.setCoordinate(coordinate);
            nextRoom.addEntity(hero);
            currentRoom.removeEntity(hero);
            hero.move(nextRoom);
            return "You go "+secondWord+" and now "+nextRoom.getLongDescription();
        }
        return "You can't go "+secondWord;
    }
    
    /**
     * Return the EnumDirection object associated to the String in parameter
     * @param direct
     *              the String which is a direction
     * @return the EnumDirection associated to the String, UNKNOWN otherwise
     */
    private EnumDirection getEnumDirection(String direct){
        HashMap<String, EnumDirection>correctDirect = new HashMap<String, EnumDirection>();
        for(EnumDirection direction : EnumDirection.values()) {
            if(direction != EnumDirection.UNKNOWN){
                correctDirect.put(direction.toString(), direction);
            }
        }
        EnumDirection direction = correctDirect.get(direct);
        if(direction != null){
            return direction;
        }else{
            return EnumDirection.UNKNOWN;
        }
    }
}
