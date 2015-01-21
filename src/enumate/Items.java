package enumate;

import item.Item;

import java.util.HashMap;

public class Items {
        // map a String to a CommandWord
        private HashMap<String, EnumItem> correctItems;

        /**
         * initialize the map.
         */
        public Items(){
            correctItems = new HashMap<String, EnumItem>();
            for(EnumItem item : EnumItem.values()) {
                if(item != EnumItem.UNKNOWN){
                    correctItems.put(item.toString(), item);
                }
            }
        }

        /**
         * Find the EnumItem which matches with the String it
         * @param it
         *          The word to look up.
         * @return The EnumItem corresponding, UNKNOWN if it doesn't exist
         */
        public EnumItem getCommandWord(String it){
            EnumItem item = correctItems.get(it);
            if(item != null) {
                return item;
            }
            else {
                return EnumItem.UNKNOWN;
            }
        }
        
        /**
         * Check if the String matches a ItemName 
         * @param aString
         *              a String which can correspond to a CommandWord
         * @return true if it matches, false otherwise.
         */
        public boolean isItem(String aString)
        {
            return correctItems.containsKey(aString);
        }

        /**
         * Print all valid commands to System.out.
         */
        public String showAll(){
            String it="";
            for(String item : correctItems.keySet()) {
                it+=item + "  ";
            }
            return it;
        }
    }

