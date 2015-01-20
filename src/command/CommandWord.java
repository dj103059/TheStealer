package command;

/**
 * Representations of all the CommandWord which exists.
 * @author Tom Dall'Agnol
 */
public enum CommandWord {

    GO("go",new Go()),UNKNOWN("?",null);
    
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

    /**
     * Get the Command from an element of the enum
     */
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
