package enumate;

import java.util.HashMap;

public class EnumGolds {
    // map a String to a CommandWord
    private HashMap<String, EnumGold> correctGolds;

    /**
     * initialize the map.
     */
    public EnumGolds(){
        correctGolds = new HashMap<String, EnumGold>();
        for(EnumGold gold : EnumGold.values()) {
            if(gold != EnumGold.UNKNOWN){
                correctGolds.put(gold.toString(), gold);
            }
        }
    }

    /**
     * Find the EnumGold which matches with the String it
     * @param gold
     *          The word to look up.
     * @return The EnumGold corresponding, UNKNOWN if it doesn't exist
     */
    public EnumGold getGold(String gold){
        EnumGold golds = correctGolds.get(gold);
        if(golds != null) {
            return golds;
        }
        else {
            System.out.println("unknown");
            return EnumGold.UNKNOWN;
        }
    }
    
    /**
     * Check if the String matches a EnumGold 
     * @param aString
     *              a String which can correspond to a EnumGold
     * @return true if it matches, false otherwise.
     */
    public boolean isGold(String aString)
    {
        return correctGolds.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public String showAll(){
        String gold="";
        for(String item : correctGolds.keySet()) {
            gold+=item + "  ";
        }
        return gold;
    }
}
