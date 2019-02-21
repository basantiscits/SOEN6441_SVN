package com.proj.models;

public class GameModelCreation {
	private Player[] player;
	private Player currPlayer;
	private Map ExisitingMapDetails;


	public GameModelCreation(Map ExisitingMapDetails, Player[] player) {
		this.player = player;
		this.ExisitingMapDetails = ExisitingMapDetails;
		this.currPlayer = player[0];
	
	}

}
