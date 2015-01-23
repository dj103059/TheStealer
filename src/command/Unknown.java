package command;

import main.Simulator;
public class Unknown extends Command {

    /**
     * Return a String which says it's an unknown command
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        return "Unknow command\n";
    }

}
