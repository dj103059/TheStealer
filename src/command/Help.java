package command;

import enumate.CommandWords;
import main.Main;

/**
 * Show all the commands you can use in the game
 * 
 */
public class Help extends Command {

    /**
     * Show all the commands you can use
     */
    @Override
    public String act(String secondWord, Main main) {
        CommandWords correctCommands=new CommandWords();
        return "You can use the commands : "+correctCommands.showAll();
    }
}
