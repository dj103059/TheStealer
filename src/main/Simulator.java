package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import command.*;
import entity.*;
import room.DirectorOffice;
import room.Room;
import room.RoomGold;
import room.RoomPass;
import room.Wall;
import timer.Timer;
import item.*;

/**
 * Simulator of the game
 *
 */
@SuppressWarnings("serial")
public class Simulator implements Serializable{
    //the map
    private Room[][] bankMap;
    
    //the player
    private Player hero;
    //the current coordinate of the character : table with x and y
    private int[] coordinate;
    //the current room where the character is
    private Room currentRoom;
    
    private int[] finishRoom;
    private Timer time;
    //the list of guards
    private List<Guards> listGuards;
    //the list of the baits
    private List<Bait> listBaits;
    //the score
    private int score;
    //the win boolean : true if you win the game
    private boolean win;
    //the game over boolean : true if you have a game over
    private boolean gameOver;
    //======================================================================
    //initialize a simulator
    
    /**
     * Constructor - creates a Main object with a specified length and width
     * @param length
     * @param width
     */
   
    public Simulator(int length, int width){
        //initialize time and score and coordinate
        time = new Timer(900);
        score=0;
        coordinate=new int[2];
        //the map has the same size that the user want
        bankMap=new Room[length][width];
        Map map = new Map(bankMap);
        Clock clock = new Clock(time);
        //initialize the list of guards and baits
        listGuards=new ArrayList<Guards>();
        listBaits=new ArrayList<Bait>();
        //add the rooms from the map.txt file
        iniMap();
        loadMap();
        //check if all the bankMap is complete (without any void room)
        for(int i=0;i<bankMap.length;i++){
            for(int j=0;j<bankMap[0].length;j++){
                if(bankMap[i][j]==null){
                    throw new RuntimeException("The map has some void room in it. The Map.txt isn't correct.");
                }
            }
        }
        //initialize the exits of each rooms
        initExit();
        //he begins in (x,y)
        hero = new Player(currentRoom,100,"hero");
        currentRoom.addEntity(hero);
        hero.init(map);
        hero.init(clock);
        
    }
    
    /**
     * Initialize the map
     * It adds rooms to each box of the table
     */
    private void iniMap(){
        int maxColumn = bankMap.length;
        int maxLine = bankMap[0].length;
        //the borders are walls
        //borders north and south are walls
        for(int i=0;i<maxColumn;i++){    
            bankMap[i][0]=new Wall();
            bankMap[i][0].setX(i);
            bankMap[i][0].setY(0);
            bankMap[i][maxLine-1]=new Wall();
            bankMap[i][maxLine-1].setX(i);
            bankMap[i][maxLine-1].setY(maxColumn-1);
        }
        //borders east and west are walls
        for(int j=0;j<maxLine;j++){
            bankMap[0][j]=new Wall();
            bankMap[0][j].setX(0);
            bankMap[0][j].setY(j);
            bankMap[maxColumn-1][j]=new Wall();
            bankMap[maxColumn-1][j].setX(maxLine-1);
            bankMap[maxColumn-1][j].setY(j);
        }
    }
    
    /**
     * Load the Map.txt file in order to create a 30x30 map
     * @throws RuntimeException
     *              if there isn't enough correct characters in the Map.txt file to create a 30x30 map
     */
    public void loadMap(){
        int maxColumn = bankMap.length;
        int maxLine = bankMap[0].length;
        //the corrects characters to do a correct
        List<Character> correctChara = addCorrectCharacters();
        BufferedReader buff;
        int x=1;
        int y=1;
        try{
            //we open the text file in reading mode
            String path = System.getProperty("user.dir");
            buff = new BufferedReader(new FileReader(path+"/Map.txt"));
            try{
                char charac;
                int counter;
                while(true){
                    counter=0;
                    //charac must be a correct character, otherwise we take the next charac
                    do{
                      //if we have done more than 30 tries, the Map.txt isn't correct
                        if (counter>30){
                            throw new RuntimeException("The Map.txt isn't correct! There isn't enough" +
                                    " correct characters.");
                        }
                        charac=(char)buff.read();
                        counter++;
                    }while(!correctChara.contains(charac));
                    //we add a room to the map in function of the character
                    addRoomToMap(charac,x,y,buff);
                    x++;
                    //if we reach the end of the line, we have to increment y and set x to 1
                    if(x>maxLine-2){
                        x=1;
                        y++;
                        //if we have done all the lines, we finish to load the map
                        if(y>maxColumn-2){
                            break;
                        }
                    }
                }
            }finally{
                //we close the text file after using it
                buff.close();
            }
        }catch(IOException e){
            //in case of an I/O error, we exit
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Initialize the correctCharac list, putting in it the characters to create a map
     * @return correctChara
     *              the list with the corrects characters
     */
    private List<Character> addCorrectCharacters() {
        List<Character> correctChara=new ArrayList<Character>();
        correctChara.add('P');//a room which need a pass to enter
        correctChara.add('K');//a room with a key/pass
        correctChara.add('D');//the director's room
        correctChara.add('I');//a static guard
        correctChara.add('M');//a mobile guard
        correctChara.add('R');//a room
        correctChara.add('W');//a wall
        correctChara.add('G');//a goldroom
        correctChara.add('B');//a room with a box
        correctChara.add('E');//the entry room where the player begins
        correctChara.add('L');//a room with a bait
        return correctChara;
    }

    /**
     * Add a room to the map thanks to a character
     * @param charac
     *          a char which represents a room
     * @param x
     *          x of the map
     * @param y
     *          y of the map
     * @param buff
     *          the buffer of the file
     */
    private void addRoomToMap(char charac,int x,int y,BufferedReader buff) throws IOException{
        boolean originalRoom=false;
        //we search if the character represents an original room
        switch(charac){
        case 'W':
            //wall
            bankMap[x][y]=new Wall();
            originalRoom=true;
            break;
        case 'G':
            //gold room
            bankMap[x][y]=new RoomGold("RoomGold"+x+"-"+y,"pass-G",300);
            originalRoom=true;
            break;
        case 'D':
            //director office
            bankMap[x][y]=new DirectorOffice("directoroffice"+x+"-"+y,"pass-D");
            originalRoom=true;
            break;
        case 'P':
            //room which need a pass to enter in it, the pass is determined by the next char
            bankMap[x][y]=new RoomPass("awesome room which needs a pass to enter","pass-"+(char)buff.read());
            originalRoom=true;
            break;
        default:
            break;
        }
        if(!originalRoom){
            //if not one of the previous case, we have a normal room with something in it
            bankMap[x][y]=new Room("room"+x+"-"+y,"Awesome room");
        }
        //we add the x and the y of the room
        bankMap[x][y].setX(x);
        bankMap[x][y].setY(y);
        if(originalRoom){
            //if it was an original room, we can quit here
            return;
        }
        //here we determined what is in it
        switch(charac){
        case 'L':
            //room with a bait
            bankMap[x][y].addItem(new Bait());
            break;
        case 'K':
            //room with a key, the key is determined by the next char
            bankMap[x][y].addItem(new Pass("pass-"+(char)buff.read()));
            break;
        case 'M':
            //room with a mobile guard
            Guards guardMobile=new MovingGuards(bankMap[x][y], false, true, "Guard "+x+"-"+y);
            listGuards.add(guardMobile);
            bankMap[x][y].addEntity(guardMobile);
            break;
        case 'I':
            //room with a stationary guard
            Guards guard=new Guards(bankMap[x][y], false, true, "Guard "+x+"-"+y);
            listGuards.add(guard);
            bankMap[x][y].addEntity(guard);
            break;
        case 'B':
            //room with a box
            bankMap[x][y].addItem(new Box());
            break;
        case 'E':
            //the hero will be here
            finishRoom=new int[2];
            //we put the right coordinate for thhe beginning
            finishRoom[0]=x;
            finishRoom[1]=y;
            coordinate[0]=x;
            coordinate[1]=y;
            currentRoom=bankMap[x][y];
            break;
        case 'R':
            //a room
            break;
        default:
            break;
        }
    }
    
    /**
     * initialize the exits of each room of the map
     */
    public void initExit(){
        Room wall=new Wall();
        for(int i=1;i<bankMap.length-1;i++){
            for(int j=1;j<bankMap[0].length-1;j++){
                //south exit
                if(!bankMap[i][j+1].equals(wall)){
                    bankMap[i][j].setExit("south", bankMap[i][j+1]);
                }
                //east exit
                if(!bankMap[i+1][j].equals(wall)){
                    bankMap[i][j].setExit("east", bankMap[i+1][j]);
                }
                //west exit
                if(!bankMap[i-1][j].equals(wall)){
                    bankMap[i][j].setExit("west", bankMap[i-1][j]);
                }
                //north exit
                if(!bankMap[i][j-1].equals(wall)){
                    bankMap[i][j].setExit("north", bankMap[i][j-1]);
                }
            }
        }
    }
    
    //=====================================================================
    //The game
    
    /**
     * Launch the game
     * @return what happened during the turn
     */
    public String play(CommandLine cmd){
        String state="";
        score=time.getTimer();
        state+=processCommand(cmd);
        //while the hero is hidden, the hero can't do anything
        while(hero.getHidden()>0){
            hero.decrementHide();
            state+=endTurn();
        }
        return state;
    }
    
    /**
     * Process the command entered by the user
     * 
     * @param cmd
     *              a CommandLine which contains all the line that the user has entered
     * @return the action which was performed with this command
     */
    private String processCommand(CommandLine cmd){
        //get the command from cmd
        Command command = cmd.getCommandWord().getCommand();
        //execute the command
        String action=command.act(cmd.getSecondWord(),this);
        return action;
    }
    
    /**
     * All the guards acts when we call this method
     * @return a String which say game over if you have been saw
     */
    public String actGuards(){
        //each guard will act
        for(int i = 0;i<listGuards.size();i++){
            //if the hero has been seen
            if(listGuards.get(i).act(hero, bankMap)){
                setGameOver(true);
                return "You have been saw by a guard!";
            }
        }
        return "";
    }
    
    /**
     * Process the end of a turn
     * @return what happens during the end of the turn
     */
    public String endTurn(){
        String end = "End turn\n";
        //the guards acts
        end+=actGuards();
        List<Bait> baitToRemove=new ArrayList<Bait>();
        //the baits acts
        for(Bait bait : listBaits){
            if(actBaits(bait)){
                //if they can't act anymore, we say we want to remove it
                baitToRemove.add(bait);
            }
        }
        //we remove all the baits that can't act anymore
        for(Bait bait : baitToRemove){
            System.out.println(listBaits.remove(bait));
        }
        //decrement the time
        if (time.decrementTime(5)==false)
        {
            //if we reach 0, game over
            setGameOver(true);
            end+="\nNo more time! Try again!";
        }
        //if we win
        if (win())
        {
            //we set win at true
            setWin(true);
        }
        return end;
    }
    
    /**
     * The baits acts as they have to
     * @param bait
     *          the bait we want to do the action
     */
    private boolean actBaits(Bait bait){
        if(bait.getIsActive() && bait.getNumbTurn()<=0){
            bait.setIsActive(false);
            removeNoise(bait.getCoordinate());
            return true;
        }
        bait.decrementBait();
        return false;
    }
    
    /**
     * Remove the noise of a room and all the room in the area
     * @param coordinate
     */
    private void removeNoise(int[] coordinate) {
        int x=coordinate[0];
        int y=coordinate[1];
        bankMap[x][y].addNoise(-3);
        bankMap[x+1][y].addNoise(-2);
        bankMap[x-1][y].addNoise(-2);
        bankMap[x][y+1].addNoise(-2);
        bankMap[x][y-1].addNoise(-2);
        bankMap[x+1][y-1].addNoise(-1);
        bankMap[x+1][y+1].addNoise(-1);
        bankMap[x-1][y-1].addNoise(-1);
        bankMap[x-1][y+1].addNoise(-1);
        if (!bankMap[x+1][y].equals(new Wall())&&(x+2<=bankMap.length-2)){bankMap [x+2][y].addNoise(-1);}
        if (!bankMap[x-1][y].equals(new Wall())&&(x-2>0)){bankMap [x-2][y].addNoise(-1);}
        if (!bankMap[x][y+1].equals(new Wall())&&(y+2<=bankMap.length-2)){bankMap [x][y+2].addNoise(-1);}
        if (!bankMap[x][y-1].equals(new Wall())&&(y-2>0)){bankMap [x][y-2].addNoise(-1);}
    }
    
    /**
     * Set the noise in a room and the surrounding room
     * @param coordinateX
     * @param coordinateY
     */
    public void setNoise(int x, int y) {
        bankMap[x][y].addNoise(3);
        bankMap[x+1][y].addNoise(2);
        bankMap[x-1][y].addNoise(2);
        bankMap[x][y+1].addNoise(2);
        bankMap[x][y-1].addNoise(2);
        bankMap[x+1][y-1].addNoise(1);
        bankMap[x+1][y+1].addNoise(1);
        bankMap[x-1][y-1].addNoise(1);
        bankMap[x-1][y+1].addNoise(1);
        if (!bankMap[x+1][y].equals(new Wall())){bankMap [x+2][y].addNoise(1);}
        if (!bankMap[x-1][y].equals(new Wall())){bankMap [x-2][y].addNoise(1);}
        if (!bankMap[x][y+1].equals(new Wall())){bankMap [x][y+2].addNoise(1);}
        if (!bankMap[x][y-1].equals(new Wall())){bankMap [x][y-2].addNoise(1);}
    }
        
    /**
     * the Win method
     * @return true if you win, false otherwise
     */
       public boolean win()
       {
           if ((bankMap[finishRoom[0]][finishRoom[1]].containsEntity(hero))&&(hero.getGold()>0))
               return true;
           return false;
       }
       

    
    //=================================================================
    //get and set
       
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
     * @return coordinate X
     */
    public int getCoordinateX() {
        return coordinate[0];
    }
    
    /**
     * get the Y coordinate of the hero
     * @return coordinate y
     */
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
    
    /**
     * add baits to the current room
     * @param bait
     *          a Bait we want to add
     */
    public void addBait(Bait bait){
        listBaits.add(bait);
    }
    
    /**
     * remove a bait from the list
     * @param bait
     *          the bait we want to remove
     * @return true if there is a bait in it and we remove it, false otherwise
     */
    public boolean removeBait(Bait bait){
        return listBaits.remove(bait);
    }
    
    /**
     * Return the current score of the game
     * @return the score
     */
    public int getScore(){
        return score;
    }
    
    /**
     * Set the win boolean
     * @param b
     *              a boolean to know at which state we want the win boolean
     */
    public void setWin(boolean b){
        this.win=b;
    }
    
    /**
     * Get the win boolean
     * @return win
     */
    public boolean getWin(){
        return win;
    }
    
    /**
     * Set the gameOver boolean
     * @param b
     *              a boolean to know at which state we want the gameOver boolean
     */
    public void setGameOver(boolean b){
        this.gameOver=b;
    }
    
    /**
     * Get the gameOver boolean
     * @return gameOver
     */
    public boolean getGameOver(){
        return gameOver;
    }
    /**
     * Get the time remaining
     * @return time
     */
   
	public Timer getTime() {
		return time;
	}
	

	/**
	 * Set the time.
	 * @param time
	 */
	public void setTime(Timer time) {
		this.time = time;
	}
	
	/**
	 * Get the listGuards
	 * @return listGuards
	 */
	public List<Guards> getListGuards() {
		return listGuards;
	}
	
	/**
	 * Set the listGuards
	 * @param listGuards
	 */
	public void setListGuards(List<Guards> listGuards) {
		this.listGuards = listGuards;
	}
	/**
	 * get the listBaits
	 * @returnlistBaits
	 */
	public List<Bait> getListBaits() {
		return listBaits;
	}
	
	/**
	 * Set the listBaits
	 * @param listBaits
	 */
	public void setListBaits(List<Bait> listBaits) {
		this.listBaits = listBaits;
	}
	
	/**
	 * set the currentRoom
	 * @param currentRoom
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	/**
	 * set the score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * get the current Coordinate of the player
	 * @return
	 */
    public int[] getCoordinate(){
    	return coordinate;
    }
}
