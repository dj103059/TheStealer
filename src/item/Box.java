package item;
/**
 * The box item
 **/
public class Box extends Item {
	// constant for the weigth of the box
	private static final int WEIGTH = 500 ;
	//constructor
	public Box (){
		this.setDescription(" you can hide you inside...");
		this.setName("Box");
		this.setWeight(WEIGTH);
	}
	
	@Override
	public String use(){
		String s;
		s = "you are hiden";
		return s;
		/* TODO  : during three turns , player's noise equal 0*/
	}
}
