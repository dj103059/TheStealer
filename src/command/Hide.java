package command;

import item.Box;
import entity.Player;
import main.Main;

public class Hide extends Command{

    /**
     * If there is a box in the room where the player is, he hides,
     * otherwise he can't.
     */
    @Override
    public String act(String secondWord, Main main) {
        Player hero = main.getHero();
        if(hero.getCurrentRoom().equals(new Box())){
            hero.hide();
            return "You are hiding under a box.";
        }
        return "You can't hide here, there isn't any box.";
    }

}
