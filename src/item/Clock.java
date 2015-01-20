package item;

import timer.Timer;

public class Clock extends Item{
	// constant for the weigth of the box
		private static final int weigth = 1 ;
		private Timer time;


		//constructor
		public Clock (){
			this.setDescription(" you can see the time remaining");
			this.setName("wristwatch");
			this.setWeigth(weigth);
		}
		
		@Override
		public String use(){
			return "Time Remaining "+this.time.getTimer()+" min /n";
		}
		public Timer getTime() {
			return time;
		}

		public void setTime(Timer time) {
			this.time = time;
		}
		
		public String update(int n){
			this.time.setTimer(this.time.getTimer()-n);
			if(n <=0){
				return "Game over, elapsed time";
				//TODO
			}
			else return "";
		}
}
