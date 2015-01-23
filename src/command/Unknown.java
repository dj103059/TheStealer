package command;

import main.Simulator;
/**
 * Unknown command, we use it when the user hasn't entered a correct command
 * @author Tom Dall'Agnol
 *
 */
public class Unknown extends Command {

    /**
     * Return a String which says it's an unknown command
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        return "Unknow command\n";
    }
    
    /**
     * can't be called
     */
    @Override
    public String help(){
        return "How can you call this method?!";
    }

}
