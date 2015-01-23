package command;

import enumate.CommandWords;
import main.Simulator;

/**
 * Show all the commands you can use in the game
 * 
 */
public class Help extends Command {

    /**
     * Show all the commands you can use
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        CommandWords correctCommands=new CommandWords();
        return "You can use the commands : "+correctCommands.showAll()+"\n";
    }
}
