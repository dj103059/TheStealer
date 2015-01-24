package item;

import main.Simulator;

/**
 * The Pass item wich has the role of a key.
 *
 */
@SuppressWarnings("serial")
public class Pass extends Item {
	// constant for the weigth of a pass
		private static final int WEIGHT = 1 ;
		
		/**
		 * Create a pass with a specific name, it will allow to open room's which have same name than it.
		 * @param name
		 */
		public Pass (String name){
			this.setDescription(" the pass for a specific door.");
			this.setName(name);
			this.setWeight(WEIGHT);
		}
		
		@Override
		public String use(Simulator simul){
		    return "It's a "+this.getName();
		}
		
		/**
		 * Create a pass which allow go in all the room
		 */
		public Pass(){
			this.setDescription("A pass for all room");
			this.setName("multipass");
			this.setWeight(WEIGHT);
		}
}
