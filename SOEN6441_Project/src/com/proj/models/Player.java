package com.proj.models;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class
 * @author Ofreish
 * @since 7 Feb 2019
 * @version 1.0
 */
public class Player {
	
	private String playerName;
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned;
	
	/**
	 * constructor for Player class
	 * @param string name of player
	 */
	public Player(String string) {
		this.playerName = string;	
		countriesOwned = new ArrayList<Country>();
	}
	
	/**
	 * getter for list of countries owned
	 * @return countries owned
	 */
	public List<Country> getCountriesOwned() {
		return countriesOwned;
	}
	
	/**
	 * setter for list of countries owned
	 * @param countriesOwned countries owned
	 */
	public void setCountriesOwned(List<Country> countriesOwned) {
		this.countriesOwned = countriesOwned;
	}
	
	/**
	 * getter for list of continents owned
	 * @return list of continents owned
	 */
	public List<Continent> getContinentsOwned() {
		return continentsOwned;
	}
	
	/**
	 * setter for list of continents owned
	 * @param continentsOwned list of continents owned
	 */
	public void setContinentsOwned(List<Continent> continentsOwned) {
		this.continentsOwned = continentsOwned;
	}
	
	/**
	 * getter for number of armies owned
	 * @return number of armies owned
	 */
	public int getNoOfArmiesOwned() {
		return noOfArmiesOwned;
	}
	
	/**
	 * setter for number of armies owned
	 * @param noOfArmiesOwned number of armies owned
	 */
	public void setNoOfArmiesOwned(int noOfArmiesOwned) {
		this.noOfArmiesOwned = noOfArmiesOwned;
	}
	
	/**
	 * update number of armies owned
	 * @param noOfnewArmies number of armies owned plus number of new armies
	 */
	public void incrementNoOfArmiesOwned(int noOfnewArmies) {
		this.noOfArmiesOwned = this.noOfArmiesOwned + noOfnewArmies;
	}
	
	/**
	 * getter for number of cards owned
	 * @return number of cards owned
	 */
	public int getNoOfCardsOwned() {
		return noOfCardsOwned;
	}
	
	/**
	 * setter for number of cards owned
	 * @param noOfCardsOwned number of cards owned
	 */
	public void setNoOfCardsOwned(int noOfCardsOwned) {
		this.noOfCardsOwned = noOfCardsOwned;
	}
	
	/**
	 * setter for player name
	 * @param playerName name of player
	 */
	public void setPlayerName(String playerName){
		this.playerName=playerName;
	}
	
	/**
	 * getter for player name
	 * @return name of player
	 */
	public String getPlayerName(){
		return playerName;
	}
	
	/**
	 * add country in list of countries owned
	 * @param countryName name of country to be added
	 */
	public void addCountry(Country countryName) {
		countriesOwned.add(countryName);
		
	}
	
	/**
	 * decrement number of armies owned
	 */
	public void reduceArmyInPlayer() {
		this.noOfArmiesOwned--;
	
	}
	
	/**
	 * increment number of armies owned
	 */
	public void addArmyInPlayer() {
		this.noOfArmiesOwned++;
	
	}

}
