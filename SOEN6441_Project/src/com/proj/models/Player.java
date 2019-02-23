package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String playerName;
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned;
	private List<Card> cardsOwned;
	//private int noOfArmyInPlayer;	// Why ?
	
	public Player(String string) {
		this.playerName = string;	//o
		countriesOwned = new ArrayList<>();
	}

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
	
	public void incrementNoOfArmiesOwned(int noOfnewArmies) {
		this.noOfArmiesOwned = this.noOfArmiesOwned + noOfnewArmies;
	}

	public int getNoOfCardsOwned() {
		return noOfCardsOwned;
	}

	public void setNoOfCardsOwned(int noOfCardsOwned) {
		this.noOfCardsOwned = noOfCardsOwned;
	}

	public void setPlayerName(String playerName){
		this.playerName=playerName;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	public void addCountry(Country countryName) {
		countriesOwned.add(countryName);
		
	}
	public void reduceArmyInPlayer() {
		this.noOfArmiesOwned--;
	
	}
	public void addArmyInPlayer() {
		this.noOfArmiesOwned++;
	
	}

}
