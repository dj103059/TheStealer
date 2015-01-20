package enumate;

import command.*;

/**
 * Representations of all the CommandWord which exists.
 * @author Tom Dall'Agnol
 */
public enum CommandWord {

    GO("go",new Go()),USE("use", new Use()),UNKNOWN("?",new Unknown());
    
    // The command string.
    private String commandString;
    private Command commandWord;
    /**
     * Initialize with the corresponding command string.
     * 
     * @param commandString
     *            The command String.
     * @param command
     *            The object Command
     */
    CommandWord(String commandString, Command command){
        this.commandString = commandString;
        this.commandWord = command;
    }

    public Command getCommand(){
        return commandWord;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString() {
        return commandString;
    }
}
