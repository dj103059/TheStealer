package command;

import main.Simulator;

/**
 * Command to wait one turn in the current room
 * @author Tom Dall'Agnol
 *
 */
public class Wait extends Command {

    /**
     * Command to wait in the current room
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        String end = simul.endTurn();
        return "You wait here.\n"+end;
    }

    /**
     * 'wait'
     */
    @Override
    public String help(){
        return "Wait in the current room during one turn. Use : Type 'wait'.";
    }
}
