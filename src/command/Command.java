package command;

import main.Simulator;

/**
 * An abstract representation of a command
 * @author Tom Dall'Agnol
 *
 */
public abstract class Command {
    /**
     * Do the associated action to the Command
     * @param secondWord
     *          the second word of what the user types
     * @return a String which describes what happens
     */
    public abstract String act(String secondWord, Simulator simul);
    
    /**
     * return a String which say how to use this command
     * @return a String
     */
    public abstract String help();
}
