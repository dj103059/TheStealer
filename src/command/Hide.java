package command;

import item.Box;
import entity.Player;
import main.Main;

public class Hide extends Command{

    @Override
    public String act(String secondWord, Main main) {
        Player hero = main.getHero();
        if(hero.getCurrentRoom().getItem("box")){
            hero.hide();
            return "You are hiding under a box.";
        }
        return "You can't hide here, there isn't any box.";
    }

}
