package enumate;

import java.util.HashMap;

/**
 * This class permits to do some actions on the command words (as find a command word thanks to a String,
 * check if the String refers to a correct command word
 * @author Tom Dall'Agnol
 */

public class CommandWords
{
    // map a String to a CommandWord
    private HashMap<String, CommandWord> correctCommands;

    /**
     * initialize the map.
     */
    public CommandWords(){
        correctCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN){
                correctCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord which matches with the String commandWord
     * @param commandWord
     *          The word to look up.
     * @return The CommandWord corresponding, UNKNOWN if it doesn't exist
     */
    public CommandWord getCommandWord(String commandWord){
        CommandWord command = correctCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check if the String matches a CommandWord 
     * @param aString
     *              a String which can correspond to a CommandWord
     * @return true if it matches, false otherwise.
     */
    public boolean isCommand(String aString)
    {
        return correctCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public String showAll(){
        String cmd="";
        for(String command : correctCommands.keySet()) {
            cmd+=command + "  ";
        }
        return cmd;
    }
}
