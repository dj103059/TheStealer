package command;

import main.Main;

/**
 * Permits to quit the game
 *
 */
public class End extends Command {

    /**
     * End do nothing
     */
    @Override
    public String act(String secondWord, Main main) {
        main.gameOver();
        return "You quit";
    }
    
    /**
     * obj is equals to this if it's an End object or if it's == to this
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj instanceof End){
            return true;
        }
        return false;
    }

}
