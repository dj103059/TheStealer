package room;

import item.Pass;

public class RoomPass extends Room {
	private Pass pass1;
	private Pass pass2;

	public RoomPass(String description, String name) {
		super(description, name);
		this.pass1 = new Pass(name);
		this.pass2 = new Pass("ALLROOM");
	}

	public Pass getPass1() {
		return pass1;
	}
	
	public Pass getPass2() {
		return pass2;
	}

	public void setPass1(Pass pass1) {
		this.pass1 = pass1;
	}

	public void setPass2(Pass pass2) {
		this.pass2 = pass2;
	}
	
}
