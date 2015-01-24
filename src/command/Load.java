package command;

/**
 * Allow to load the game to the last save
 */

import save.Serialization;
import entity.Player;
import item.Clock;
import main.Simulator;
import item.Map;
import room.*;
import entity.*;
public class Load extends Command {

	/**
	 * Load to the last save effectued
	 */
	@Override
	public String act(String secondWord, Simulator simul) {
		
		
		
		simul.getHero().setCurrentRoom(Serialization.loadSimulator().getCurrentRoom());
		simul.setBankMap(Serialization.loadSimulator().getBankMap());
		for (int i=0;i<simul.getBankMap().length;i++)
		{
			for (int j=0;j<simul.getBankMap()[i].length;j++)
			{
				
				for (int k=0;k<simul.getBankMap()[i][j].getListofentity().size();k++)
				{
					
					
					Entity a=simul.getBankMap()[i][j].getListofentity().get(k);
					System.out.println("The entity "+a+" is in the room ( "+i+", "+j+")");
					simul.getBankMap()[i][j].getListofentity().remove(k);
					
					if ((!(a instanceof MovingGuards))&&!(a instanceof Player))
					simul.getBankMap()[i][j].addEntity(a);
				}
			}
		}
		
		
		simul.setWin((Serialization.loadSimulator().getWin()));
		
		simul.setCoordinate((Serialization.loadSimulator().getCoordinate()));
		simul.setCurrentRoom(Serialization.loadSimulator().getCurrentRoom());
		simul.setGameOver(Serialization.loadSimulator().getGameOver());
		simul.setListBaits(Serialization.loadSimulator().getListBaits());
		simul.setListGuards(Serialization.loadSimulator().getListGuards());
		simul.setScore(Serialization.loadSimulator().getScore());
		simul.setTime(Serialization.loadSimulator().getTime());
		simul.getHero().getInventory().remove("clock");
		simul.getHero().add(new Clock(simul.getTime()),0);
		simul.getHero().getInventory().remove("map");
		simul.getHero().add(new Map(simul.getBankMap()),0);
		
		
		
		return "You load your last save, the actual map is described just above.Your map will be update during your next deplacement so don't use it before";
	}
	/**
	 * Return the help of the command Load
	 */
	public String help(){
        return"Load the last save effectued";
    }
}
