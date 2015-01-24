package room;


import item.Pass;

/**
 * Specific room with the  treasure room key
 *
 */
@SuppressWarnings("serial")
public class DirectorOffice extends RoomPass{
	
	/*
	 * constructor
	 */
	public DirectorOffice(String description, String name) {
		super(description, name);
	    this.addItem(new Pass("pass-G"));
	}
	
	/**
	 * Second constructor whitch allow to place a key in a second room
	 * @param room the room with the director office key
	 */
	public DirectorOffice(String description, String name,Room room) {
		super(description, name,room);
	    this.addItem(new Pass("pass-G"));
	}
	

}
