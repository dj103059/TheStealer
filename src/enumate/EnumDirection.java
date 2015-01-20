package enumate;

public enum EnumDirection {
	SOUTH("south"), NORTH("north"), EAST("east"), WEST("west"), UNKNOWN("?");
	private String direction;
	
	EnumDirection(String name){
		this.direction= name;
	}
	
	@Override
	public String toString(){
		String s = direction;
		return s;
	}
}
