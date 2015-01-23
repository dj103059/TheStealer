package item;



import main.Simulator;
import room.Room;
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
	private int[] coordinate;
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
		
	public void decrementBait(){
		System.out.println("numb turn : "+numbTurn);
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
		this.numbTurn=MAX_TURN;
		System.out.println("numb turn : " +numbTurn);
	}
	
	public void setIsActive(boolean b)
	{
		isActive=b;
	}
	
	public void setCoordinate(int[] coordinate){
	    this.coordinate=coordinate;
	}
	
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

		
		
		
