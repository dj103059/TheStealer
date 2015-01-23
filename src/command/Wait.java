package command;

import main.Simulator;

public class Wait extends Command {

    /**
     * Command to wait in the current room
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        String end = simul.endTurn();
        return "You wait here.\n"+end;
    }

}
