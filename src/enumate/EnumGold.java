package enumate;

public enum EnumGold {
    TEN("10",10),TWENTY("20",20),FIFTY("50",50),HUNDRED("100",100),
    TWOHUNDRED("200",200),UNKNOWN("?",42);
    
    // The command string.
    private String stringNumber;
    private int number;
    /**
     * Initialize with the corresponding command string.
     * 
     * @param stringNumber
     *            The String which corresponds to the number.
     * @param number
     *            The number
     */
    EnumGold(String stringNumber, int number){
        this.stringNumber = stringNumber;
        this.number = number;
    }

    /**
     * return the number as an int
     * @return 
     */
    public int getNumber(){
        return number;
    }
    
    /**
     * return the String number
     * @return The number as a string.
     */
    public String toString() {
        return stringNumber;
    }
}
