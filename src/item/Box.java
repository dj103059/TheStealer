package item;

import main.Simulator;
import enumate.EnumItem;

/**
 * The box item
 **/
@SuppressWarnings("serial")
public class Box extends Item {
	// constant for the weigth of the box
	private static final int WEIGTH = 500 ;
	//constructor
	public Box (){
		this.setDescription(" you can hide you inside...");
		this.setName(EnumItem.BOX.toString());
		this.setWeight(WEIGTH);
	}
	
	@Override
	public String use(Simulator simul){
		String s;
		s = "use the command HIDE if you want use it";
		return s;
	}
}
