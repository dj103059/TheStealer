package item;

/**
 * 
 * The item Lure
 *
 */
public class Lure extends Item {
	// constant for the weigth of the lure
		private static final int weigth = 5 ;
		//constructor
		public Lure (){
			this.setDescription(" use it for make distraction!");
			this.setName("Lure");
			this.setWeigth(weigth);
		}
		
		@Override
		public String use(){
			
			return "";
			/* TODO  : during three turns , player's noise equal 0*/
		}
}
