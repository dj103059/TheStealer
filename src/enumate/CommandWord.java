package enumate;

import command.*;

/**
 * Representations of all the CommandWord which exists.
 * @author Tom Dall'Agnol
 */
public enum CommandWord {

    GO("go",new Go()),USE("use", new Use()),HIDE("hide",new Hide()),
    TAKE("take",new Take()),DROP("drop",new Drop()),QUIT("quit",new End()),
    INVENTORY("inventory",new Inventory()),WAIT("wait",new Wait()),HELP("help",new Help()),
    UNKNOWN("?",new Unknown());
    
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
