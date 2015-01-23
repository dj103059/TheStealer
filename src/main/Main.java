package main;


import java.util.Scanner;

import command.CommandLine;
import enumate.EnumGold;
import enumate.EnumGolds;
import parser.Parser;

public class Main {
    //the parser
    private Parser parser;
    //the simulator
    private Simulator simul;
    
    /**
     * Constructor - initialize simul and the parser
     * @param length
     * @param width
     */
    public Main(int length,int width){
        this.simul=new Simulator(length,width);
        this.parser=new Parser();
    }
    
    /**
     * Constructor - initialize simul with the simul in parameter
     * @param simul
     *              a simulator
     */
    public Main(Simulator simul){
        this.simul=simul;
        this.parser=new Parser();
    }
    
    /**
     * Launch the game
     */
    public void play(){
        introduction();
        while(true){
            CommandLine cmd=parser.getCommand();
            printer(simul.play(cmd));
            if(simul.getGameOver()){
                gameOver();
            }
            if(simul.getWin()){
                winGame();
            }
        }
    }
    
    /**
     * Parse the amount of golds the user wants
     * @param action
     *              what we want to do with golds
     * @return the int of gold we want
     */
    public static int parseGold(String action){
        EnumGolds correctGold=new EnumGolds();
        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        String inputLine;   // will hold the full input line
        String word1 = null;
        do{
            printer("How much gold do you want to"+ action+"? ");
            printer("You can only"+ action+" : "+correctGold.showAll());
            printer("> ");     // print prompt
            inputLine = reader.nextLine();
            // Find up the word on the line.
            @SuppressWarnings("resource")
            Scanner tokenizer = new Scanner(inputLine);
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();      // get first word
            }
        }while(!correctGold.isGold(word1));  //does it while the word is incorrect (not 10,20,50,100,etc...)
        EnumGold gold = correctGold.getGold(word1);
        return gold.getNumber();
    }
    
    /**
     * introduction
     */
    private void introduction(){
        System.out.println("You are a thief in a bank");
        System.out.println("You have some time to go to the treasure room and return to the starting point.");
        System.out.println("Be careful, there are guards, if you are seen, it's game over.");
        System.out.println("You will also need keys to move, find them.");
        System.out.println("Let's go!");
    }
    
    /**
     * Print the String which is pass in parameter
     * @param str
     *              the String we want to print
     */
    public static void printer(String str){
        System.out.println(str);
    }
    
    /**
     * method to call when you win the game
     */
    public void gameOver(){
        int score = simul.getScore();
        score+=simul.getHero().getGold();
        score=score/10;
        printer("Game over : \nScore : "+score);
        System.exit(0);
    }
    
    /**
     * Method to call when you win the game
     */
    public void winGame(){
        int score = simul.getScore();
        score +=simul.getHero().getGold();
        printer("You win the game. \nScore : "+score);
        System.exit(0);
    }

    /**
     * permits to launch the game
     * @param args
     */
    public static void main(String[] args) {
		Main main = new Main(32,32);
		main.play();
	}

}
