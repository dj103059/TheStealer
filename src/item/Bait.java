package item;



import enumate.EnumItem;

/**
 * 
 * The item Lure
 *
 */
public class Bait extends Item{
	// constant for the weigth of the lure
		private static final int WEIGTH = 5 ;
		private static final int MAX_TURN=5;
		private boolean isActive;
		private int numbTurn;
		public int getNumbTurn() {
			return numbTurn;
		}

		public void setNumbTurn(int numbTurn) {
			this.numbTurn = numbTurn;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		//constructor
		public Bait (){
			this.setDescription(" Use it to create a distraction !");
			this.setName(EnumItem.BAIT.toString());
			this.setWeight(WEIGTH);
			isActive=false;
			numbTurn=MAX_TURN;
		}
		
		public String use(){
			
			isActive=true;
			return "You put a bait on the floor.";
			
			
		}
			
		public void decrementBait(){
			if(isActive){
					
				if(numbTurn>0){
					
					numbTurn=numbTurn-1;
				}
				else{
					isActive=false;
				}	
			}
		}
		
		public boolean getIsActive()
		{
			return isActive;
		}
		public void rechargeBait()
		{
			numbTurn=MAX_TURN;
		}
		
		public void setIsActive(boolean b)
		{
			isActive=b;
		}
		
		}
		
		
		
