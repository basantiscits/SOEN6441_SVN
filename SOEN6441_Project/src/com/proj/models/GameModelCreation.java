package com.proj.models;

import java.util.Observable;

/**
 * Game model creation class
 * @author Ofreish
 * @since 8 Feb 2019
 * @version 1.0
 */
public class GameModelCreation extends Observable{
	private Player[] player;
	private Player currPlayer;
	private Map mapDetails;
	private int turn;

	/**
	 * constructor of Game Model Creation
	 * @param exisitingMapDetails existing map details
	 * @param player array object of Player class
	 */
	public GameModelCreation(Map exisitingMapDetails, Player[] player) {
		this.player = player;
		this.mapDetails = exisitingMapDetails;
		this.currPlayer = player[0];
		updateChanges();
	}
	
	
	public int armiesAllocated(Player p) {
		int size = 0;
		for(Country c : p.getCountriesOwned()) {
			size = size + c.getNoOfArmiesPresent();
		}
		return size;
	}
	
	
	
	public int getTurn() {
		return turn;
	}


	public void setTurn(int turn) {
		this.turn = turn;
	}


	public void incrementTurn() {
		if(turn == player.length) {
			turn = 0;
		}
		turn++;
		updateChanges();
	}
	
	
	public void changePlayer(){
		if(currPlayer == player[player.length-1]) {
			currPlayer = player[0];
		}
		else {
			currPlayer = player[turn-1];
		}
		
		if(currPlayer.getCountriesOwned().size()==0) {
			incrementTurn();
			changePlayer();
		}
		updateChanges();
	}
	
	public Player[] getPlayer() {
		return player;
	}






	public void setPlayer(Player[] player) {
		this.player = player;
		updateChanges();
	}






	public Player getCurrPlayer() {
		return currPlayer;
	}






	public void setCurrPlayer(Player currPlayer) {
		this.currPlayer = currPlayer;
		updateChanges();
	}






	public Map getMapDetails() {
		return mapDetails;
	}






	public void setMapDetails(Map mapDetails) {
		this.mapDetails = mapDetails;
		updateChanges();
	}






	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
