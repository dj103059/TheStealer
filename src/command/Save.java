package command;

/**
 * Allow the player to save the current state of the game
 */

import save.Serialization;
import main.Simulator;

public class Save extends Command {

	/**
	 * Save the current state of the game
	 */
	@Override
	public String act(String secondWord, Simulator simul) {
		Serialization.saveSimulator(simul);
		return "Save effectued.";
	}
	/**
	 * Return the help for the command save
	 */
	public String help(){
        return"Save the current state of the game. Be carreful, the precedent save you have done will be deleted'";
    }
}
