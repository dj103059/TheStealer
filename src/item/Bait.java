package item;



import main.Simulator;
import room.Room;
import enumate.EnumItem;

/**
 * 
 * The item Bait which has the role of a lure.
 *
 */
public class Bait extends Item{
	// constant for the weigth of the lure
	private static final int WEIGTH = 5 ;
	//constant which will says during how many time maximum a bait can stay active when actived.
	private static final int MAX_TURN=5;
	//boolean which say if the bait is active or not.
	private boolean isActive;
	// numer which says during how many time a bait can stay active when actived.
	private int numbTurn;
	//coordinate of the bait.
	private int[] coordinate;
	
	/**
	 * Return the numbTurn of the bait.
	 * @return
	 */
	public int getNumbTurn() {
		return numbTurn;
	}
	
	/**
	 * Set the numbTurn of the bait.
	 * @param numbTurn
	 */

	public void setNumbTurn(int numbTurn) {
		this.numbTurn = numbTurn;
	}


	/**
	 * Create a bait.
	 */
	public Bait (){
		this.setDescription(" Use it to create a distraction !");
		this.setName(EnumItem.BAIT.toString());
		this.setWeight(WEIGTH);
		isActive=false;
		numbTurn=MAX_TURN;
		coordinate=new int[2];
		coordinate[0]=0;
		coordinate[1]=0;
	}
	
	/**
	 * Use a bait
	 * @param simulator
	 *             the simulator where all happens
	 * @return the string which represents what is happening
	 */
	public String use(Simulator simulator){
	    Room currentRoom= simulator.getHero().getCurrentRoom();
        if(this.getIsActive()){
            return "the bait is already active\n";
        }
        this.setIsActive(true);
        this.rechargeBait();
        int[] baitCoordinate=new int[2];
        baitCoordinate[0]=currentRoom.getX();
        baitCoordinate[1]=currentRoom.getY();
        this.setCoordinate(baitCoordinate);
        simulator.getHero().drop(this.toString(), 0);
        //in case this bait is in the room and not in the hero's inventory
        currentRoom.removeItem(this);
        currentRoom.addItem(this);
        simulator.setNoise(simulator.getCoordinateX(),simulator.getCoordinateY());
        simulator.addBait(this);
		return "You put a bait on the floor.";
		
		
	}
	/**
	 * If the bait is active , decrement his number of turn remaining or deactivate it if the number of turn remaining is 0.
	 */
		
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
	
	/**
	 * Return true if the bait is active , false else.
	 * @return
	 */
	public boolean getIsActive()
	{
		return isActive;
	}
	/**
	 * Refill the remaining turn of the lure
	 */
	public void rechargeBait()
	{
		this.numbTurn=MAX_TURN;
		
	}
	
	/**
	 * Set the isActive boolean
	 * @param b
	 */
	public void setIsActive(boolean b)
	{
		isActive=b;
	}
	
	/**
	 * Set the coordinate of the bait.
	 * @param coordinate
	 */
	public void setCoordinate(int[] coordinate){
	    this.coordinate=coordinate;
	}
	
	/**
	 * Return the coordinate of the bait.
	 * @return
	 */
	public int[] getCoordinate(){
	    return coordinate;
	}
	
	@Override
	public boolean equals(Object obj){
	    if(this==obj){
	        return true;
	    }
	    if(obj instanceof Bait){
	        Bait bait = (Bait)obj;
	        if(bait.getCoordinate().equals(this.getCoordinate())){
	            if (bait.getName().equals(this.getName())){
	                if(bait.getDescription().equals(this.getDescription())){
	                    if(bait.getNumbTurn()==this.getNumbTurn()){
	                        return true;
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}
}

		
		
		
