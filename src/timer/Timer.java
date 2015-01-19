package timer;
/**
 * Class for the game's timer
 */
public class Timer {
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
}
