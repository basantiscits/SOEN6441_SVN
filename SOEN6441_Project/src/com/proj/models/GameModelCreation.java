package com.proj.models;

import java.io.Serializable;
import java.util.Observable;

import com.proj.views.GameWindowScreen;

/**
 * Game model creation class
 * @author Ofreish
 * @since 8 Feb 2019
 * @version 1.0
 */
public class GameModelCreation extends Observable implements Serializable{
	private Player[] player;
	private Player currPlayer;
	private Map mapDetails;
	private int turn;
	private int gameState = 0;
	private GameWindowScreen gameScreen;

	
	public GameWindowScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameWindowScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
		updateChanges();
	}

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
	
	/**
	 * Armies allocated to player
	 * @param p Object of Player class
	 * @return number of armies allocated to player
	 */
	public int armiesAllocated(Player p) {
		int size = 0;
		for(Country c : p.getCountriesOwned()) {
			size = size + c.getNoOfArmiesPresent();
		}
		return size;
	}
	
	
	/**
	 * getter for turn
	 * @return turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * setter for turn
	 * @param turn number of turn
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * increments turn
	 * 
	 */
	public void incrementTurn() {
		if(turn == player.length) {
			turn = 0;
		}
		turn++;
		//updateChanges();
	}
	
	/**
	 * change player
	 * 
	 */
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
	
	/**
	 * getter for player
	 * @return player
	 */
	public Player[] getPlayer() {
		return player;
	}

	/**
	 * setter for player
	 * @param player Array object of Player class 
	 */
	public void setPlayer(Player[] player) {
		this.player = player;
		updateChanges();
	}

	/**
	 * getter of current player
	 * @return current player
	 */
	public Player getCurrPlayer() {
		return currPlayer;
	}

	/**
	 * setter of current player
	 * @param currPlayer Object of Player class
	 * 
	 */
	public void setCurrPlayer(Player currPlayer) {
		this.currPlayer = currPlayer;
		updateChanges();
	}

	/**
	 * getter for map details
	 * @return map details
	 */
	public Map getMapDetails() {
		return mapDetails;
	}

	/**
	 * setter for map details
	 * @param mapDetails Object of Map class
	 */
	public void setMapDetails(Map mapDetails) {
		this.mapDetails = mapDetails;
		updateChanges();
	}

	/**
	 * Update changes
	 */
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
