package com.proj.models;

import java.util.List;

public class Player {
	
	private String playerName;
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned;
	private List<Card> cardsOwned;
	
	
	public List<Country> getCountriesOwned() {
		return countriesOwned;
	}

	public void setCountriesOwned(List<Country> countriesOwned) {
		this.countriesOwned = countriesOwned;
	}

	public List<Continent> getContinentsOwned() {
		return continentsOwned;
	}

	public void setContinentsOwned(List<Continent> continentsOwned) {
		this.continentsOwned = continentsOwned;
	}

	public int getNoOfArmiesOwned() {
		return noOfArmiesOwned;
	}

	public void setNoOfArmiesOwned(int noOfArmiesOwned) {
		this.noOfArmiesOwned = noOfArmiesOwned;
	}

	public int getNoOfCardsOwned() {
		return noOfCardsOwned;
	}

	public void setNoOfCardsOwned(int noOfCardsOwned) {
		this.noOfCardsOwned = noOfCardsOwned;
	}

	void setPlayerName(String playerName){
		this.playerName=playerName;
	}
	
	String getPlayerName(){
		return playerName;
	}

}
