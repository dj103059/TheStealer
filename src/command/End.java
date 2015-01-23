package command;
import main.Simulator;

/**
 * Permits to quit the game
 *
 */
public class End extends Command {

    /**
     * End do nothing
     */
    @Override
    public String act(String secondWord, Simulator simul) {
        simul.setGameOver(true);
        return "You quit\n";
    }

}
