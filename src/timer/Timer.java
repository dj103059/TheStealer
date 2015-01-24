package timer;

import java.io.Serializable;

/**
 * Class for the game's timer
 */
@SuppressWarnings("serial")
public class Timer implements Serializable{
	//the time for the game 
	private int timer ;
	/**
	 * constructor
	 * @param time
	 */
	public Timer(int time){
		this.timer = time;
	}
	/**
	 * getter and setter
	 */
	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	
	}
	
	/**
	 * Decrement the time and return true or false to say if there is still some time remaining or not.
	 * @param n
	 * @return
	 */
	public boolean decrementTime(int n)
	{
		if (timer-n>0)
		{
			timer=timer-n;
			return true;
		}
		else return false;
	}
}
