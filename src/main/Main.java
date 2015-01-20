package main;

import parser.Parser;
import command.*;
import entity.*;
import room.Room;
import timer.Timer;
import item.*;
import item.Map;

public class Main {
    //the map
    private Room[][] bankMap;
    
    //the player
    private Player hero;
    private MovingGuards movingguard;
    //the current coordinate of the character : table with x and y
    private int[] coordinate;
    //the current room where the character is
    private Room currentRoom;
    
    //the parser
    private Parser parser;
    
    //======================================================================
    
    /**
     * Constructor - creates a Main object with a specified length and width
     * @param length
     * @param width
     */
    public Main(int length, int width){
    	Timer time = new Timer(300);
        //initialize the parser
        parser=new Parser();
        //the map has the same size that the user want
        bankMap=new Room[length][width];
        Map map = new Map(bankMap);
        Clock clock = new Clock(time);
        
        
        coordinate=new int[2];
        //the hero has a weight limit of 100
        iniMap();
        //he begins in (0,0)
        currentRoom=bankMap[1][1];
        coordinate[0]=1;
        coordinate[1]=1;
        hero = new Player(currentRoom,100,"hero");
        bankMap[1][1].addEntity(hero);
        movingguard = new MovingGuards(bankMap[3][3],false,true,"Guard");
        bankMap[3][3].addEntity(movingguard);
        hero.init(map);
        hero.init(clock);
        
        
    }
    
    /**
     * Initialize the map
     * It adds rooms to each box of the table
     */
    private void iniMap(){
        for(int i=1;i<bankMap.length-1;i++){
            for(int j=1;j<bankMap[0].length-1;j++){
                bankMap[i][j]=new Room("room"+i+"-"+j,"best room ever");
            }
        }
        //add the left room
        for(int i=2;i<bankMap.length-1;i++){
            for(int j=1;j<bankMap[0].length-1;j++){
                bankMap[i][j].setExit("left", bankMap[i-1][j]);
            }
        }
        for(int i=1;i<bankMap.length-2;i++){
            for(int j=1;j<bankMap[0].length-1;j++){
                bankMap[i][j].setExit("right", bankMap[i+1][j]);
            }
        }
        for(int i=1;i<bankMap.length-1;i++){
            for(int j=2;j<bankMap[0].length-1;j++){
                bankMap[i][j].setExit("north",bankMap[i][j-1]);
            }
            for(int j=1;j<bankMap[0].length-2;j++){
                bankMap[i][j].setExit("south", bankMap[i][j+1]);
            }
        }
    }
    
    //=====================================================================
    
    /**
     * Launch the game
     */
    public void play(){
        //while finished is false, the game continues
        //if it's the end, finished is true
        boolean finished=false;
        
        while(!finished){
            CommandLine cmd=parser.getCommand();
            finished=processCommand(cmd);
            movingguard.act(hero,bankMap);
            endTurn();
        }
    }
    
    /**
     * Process the command entered by the user
     * 
     * @param cmd
     *              a CommandLine which contains all the line that the user has entered
     */
    private boolean processCommand(CommandLine cmd){
        Command command = cmd.getCommandWord().getCommand();
        if(command.equals(new End())){
            return true;
        }
        String action=command.act(cmd.getSecondWord(),this);
        printer(action);
        return false;
    }
    
    /**
     * Print the String which is pass in parameter
     * @param str
     *              the String we want to print
     */
    private void printer(String str){
        System.out.println(str);
    }
    
    /**
     * Process the end of a turn
     */
    private void endTurn(){
        System.out.println("x : "+coordinate[0] + "\ny : "+coordinate[1]);
    }
    
    //=================================================================
    /**
     * Get the bankMap
     * @return the bankMap
     */
    public Room[][] getBankMap() {
        return bankMap;
    }

    /**
     * Set the bankMap as we want
     * @param bankMap
     * @throws IllegalArgumentException
     *              if the new map hasn't the same size than the actual map
     */
    public void setBankMap(Room[][] bankMap) {
        if(bankMap[0].length!= this.bankMap[0].length || bankMap.length!= this.bankMap.length){
            throw new IllegalArgumentException("bankMap passed in setBankMap is incorrect. You can't modify the size of the map");
        }
        this.bankMap = bankMap;
    }

    /**
     * Get the hero
     * @return the Player hero
     */
    public Player getHero() {
        return hero;
    }

    /**
     * Set the hero as we want
     * @param hero
     */
    public void setHero(Player hero) {
        this.hero = hero;
    }

    /**
     * get the X coordinate of the hero
     * @return coordinate
     */
    public int getCoordinateX() {
        return coordinate[0];
    }
    
    public int getCoordinateY(){
        return coordinate[1];
    }

    /**
     * set the coordinates as we want
     * @param coordinate
     * @throws IllegalArgumentException
     *              if coordinate isn't a table of 2 elements
     *              if the new coordinates are not correct
     */
    public void setCoordinate(int[] coordinate) {
        int COORDINATELENGTH=2;
        //if coordinate is not a table of 2 elements
        if(! (coordinate.length==COORDINATELENGTH)){
            throw new IllegalArgumentException("coordinate passed in setCoordinate is incorrect. Correct format : int[2] which contains the x and the y");
        }
        //if the x and the y of the new coordinates are not correct (they are too high or too low)
        if(coordinate[0]>bankMap.length || coordinate[1]>bankMap[0].length || coordinate[0]<0 || coordinate[1]<0){
            throw new IllegalArgumentException("coordinate passed in setCoordinate is incorrect. The coordinates are too high or too low.");
        }
        this.coordinate = coordinate;
        currentRoom=bankMap[coordinate[0]][coordinate[1]];
    }
    
    /**
     * get the current room
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    //=================================================================
    
    public static void main(String[] args) {
		Main main = new Main(5,5);
		main.play();
	}

}
