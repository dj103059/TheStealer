package item;
/**
 * the Pass (key) item
 *
 */
public class Pass extends Item {
	// constant for the weigth of a pass
		private static final int weigth = 1 ;
		//constructor
		public Pass (String name){
			this.setDescription(" the pass for a specific door.");
			this.setName(name);
			this.setWeigth(weigth);
		}
		
		public Pass(){
			this.setDescription("A pass for all door");
			this.setName("AllRoom");
			this.setWeigth(weigth);
		}
}
