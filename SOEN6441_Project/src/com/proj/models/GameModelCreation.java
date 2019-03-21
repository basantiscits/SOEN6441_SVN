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
	private Map exisitingMapDetails;

	/**
	 * constructor of Game Model Creation
	 * @param exisitingMapDetails existing map details
	 * @param player array object of Player class
	 */
	public GameModelCreation(Map exisitingMapDetails, Player[] player) {
		this.player = player;
		this.exisitingMapDetails = exisitingMapDetails;
		this.currPlayer = player[0];
		updateChanges();
	}
	
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
