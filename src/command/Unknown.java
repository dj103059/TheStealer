package command;

import main.Main;

public class Unknown extends Command {

    /**
     * Return a String which says it's an unknown command
     */
    @Override
    public String act(String secondWord, Main main) {
        return "Unknow command";
    }

}
