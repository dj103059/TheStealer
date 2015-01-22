package command;

import main.Main;

public class Wait extends Command {

    /**
     * Command to wait in the current room
     */
    @Override
    public String act(String secondWord, Main main) {
        return "You wait here.";
    }

}
