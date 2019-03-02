package com.proj.models;

/**
 * Game model creation class
 * @author Ofreish
 * @since 8 Feb 2019
 * @version 1.0
 */
public class GameModelCreation {
	private Player[] player;
	private Player currPlayer;
	private Map ExisitingMapDetails;

	/**
	 * constructor of Game Model Creation
	 * @param ExisitingMapDetails existing map details
	 * @param player array object of Player class
	 */
	public GameModelCreation(Map ExisitingMapDetails, Player[] player) {
		this.player = player;
		this.ExisitingMapDetails = ExisitingMapDetails;
		this.currPlayer = player[0];
	
	}

}
