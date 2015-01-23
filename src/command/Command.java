package command;

import main.Simulator;

public abstract class Command {
    /**
     * Do the associated action to the Command
     * @param secondWord
     *          the second word of what the user types
     * @return a String which describes what happens
     */
    public abstract String act(String secondWord, Simulator simul);
}
