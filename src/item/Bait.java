package item;

import enumate.EnumItem;

/**
 * 
 * The item Lure
 *
 */
public class Bait extends Item {
	// constant for the weigth of the lure
		private static final int WEIGTH = 5 ;
		//constructor
		public Bait (){
			this.setDescription(" use it for make distraction!");
			this.setName(EnumItem.BAIT.toString());
			this.setWeight(WEIGTH);
		}
		
		@Override
		public String use(){
			
			return "";
			/* TODO  : during three turns , player's noise equal 0*/
		}
}
