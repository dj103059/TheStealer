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
		s = "use the command HIDE if you want use it";
		return s;
	}
}
