package room;

import item.Bait;
import item.Pass;

/**
 * specific room with the  treasure room key
 *
 */
public class DirectorOffice extends RoomPass{
	
	/*
	 * constructor
	 */
	public DirectorOffice(String description, String name) {
		super(description, name);
	    this.addItem(new Pass("pass-G"));
	}
	
	/**
	 * constructor particular
	 * @param room the room with the director office key
	 */
	public DirectorOffice(String description, String name,Room room) {
		super(description, name,room);
	    this.addItem(new Pass("pass-G"));
	}
	

}
