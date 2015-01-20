package command;

/**
 * Representations of all the CommandWord which exists.
 * @author Tom Dall'Agnol
 */
public enum CommandWord {

    GO("go",new Go());
    
    // The command string.
    private String commandString;
    private Command commandWord;
    /**
     * Initialize with the corresponding command string.
     * 
     * @param commandString
     *            The command string.
     */
    CommandWord(String commandString, Command command){
        this.commandString = commandString;
        this.commandWord = command;
    }

    /**
     * @return The command word as a string.
     */
    public String toString() {
        return commandString;
    }
}
