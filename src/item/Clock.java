package item;

import main.Simulator;
import enumate.EnumItem;
import timer.Timer;
/**
 * The box item which allow the player to hide in for several turn.
 *
 */
public class Clock extends Item{
	// constant for the weigth of the box
		private static final int weigth = 1 ;
		private Timer time;


		/**
		 * Create a clock which is associate to the Timer given in parameter.
		 * @param time
		 */
		public Clock (Timer time){
			this.setDescription(" you can see the time remaining");
			this.setName(EnumItem.CLOCK.toString());
			this.setWeight(weigth);
			this.time = time;
		}
		
		@Override
		public String use(Simulator simul){
			return "Time Remaining "+this.time.getTimer()+" min \n";
		}
		
		/**
		 * Return the time of the class Timer associated to the clock.
		 * @return
		 */
		public Timer getTime() {
			return time;
		}
		
		/**
		 * Set the time of the clock 
		 * @param time
		 */
		public void setTime(Timer time) {
			this.time = time;
		}
		
		
		
	
}
