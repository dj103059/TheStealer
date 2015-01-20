package command;

import main.Main;

public class End extends Command {

    /**
     * End do nothing
     */
    @Override
    public String act(String secondWord, Main main) {
        //DO NOTHING
        return null;
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
