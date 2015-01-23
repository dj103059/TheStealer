package command;

import enumate.CommandWords;
import main.Simulator;

/**
 * Show all the commands you can use in the game
 * @author Tom Dall'Agnol
 */
public class Help extends Command {

    /**
     * Show all the commands you can use
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        CommandWords correctCommands=new CommandWords();
        //if there is a second word
        if(secondWord!=null){
            //and if it's a correct command
            if(correctCommands.isCommand(secondWord)){
                //we return the help of this command
                Command cmd = correctCommands.getCommandWord(secondWord).getCommand(); 
                return cmd.help();
            }
        }
        return "You can use the commands : "+correctCommands.showAll()+"\nType 'help nameOfTheCommand' to have help on this command";
    }
    
    /**
     * 'help' or 'help + command'
     */
    @Override
    public String help(){
        return "Type 'help' to show all the commands, 'help nameOfACommand' to have help on a specific command.'";
    }
}
