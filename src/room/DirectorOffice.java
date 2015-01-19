package room;

import item.Pass;

public class DirectorOffice extends RoomPass{

	public DirectorOffice(String description, String name) {
		super(description, name);
		this.setPass1(new Pass("Director Office"));
	    this.addItem(new Pass("Gold Room"));
	}
	
	

}
