package item;

import main.Simulator;

/**
 * the Pass (key) item
 *
 */
public class Pass extends Item {
	// constant for the weigth of a pass
		private static final int WEIGHT = 1 ;
		//constructor
		public Pass (String name){
			this.setDescription(" the pass for a specific door.");
			this.setName(name);
			this.setWeight(WEIGHT);
		}
		
		@Override
		public String use(Simulator simul){
		    return "It's a "+this.getName();
		}
		
		public Pass(){
			this.setDescription("A pass for all door");
			this.setName("multipass");
			this.setWeight(WEIGHT);
		}
}
