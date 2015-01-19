package item;

/**
 * The box item
 **/
public class Map extends Item {
	//constant weight of the map
	private static final int weigth = 5 ;
	//constructor
	public Map (){
		this.setDescription(" you can see the area.");
		this.setName("Map");
		this.setWeigth(weigth);
	}
	
	@Override
	public void use(){
		/* TODO  : during three turns , player's noise equal 0*/
	}
}
