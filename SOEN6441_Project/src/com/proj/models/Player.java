package com.proj.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.proj.views.GameWindowScreen;

/**
 * The Player class
 * @author Ofreish
 * @since 7 Feb 2019
 * @version 1.0
 */
public class Player extends Observable{
	private String playerName;
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned = 0;
	private List<Card> cardsOwned = new ArrayList<Card>();
	private int cardsForArmies = 0;
	
	
	/**
	 * constructor for Player class
	 * @param string name of player
	 */
	public Player(String string) {
		this.playerName = string;	
		countriesOwned = new ArrayList<Country>();
		cardsOwned=new ArrayList<Card>();
		continentsOwned=new ArrayList<Continent>();
		updateChanges();
	}
	
	/**
	 * @return the cardsForArmies
	 */
	public int getCardsForArmies() {
		return cardsForArmies;
	}

	/**
	 * @param cardsForArmies the cardsForArmies to set
	 */
	public void setCardsForArmies(int cardsForArmies) {
		this.cardsForArmies = cardsForArmies;
	}
	
	/**
	 * @return the listOfCardsOwned
	 */
	public List<Card> getCardsOwned() {
		return cardsOwned;
	}

	/**
	 * @param listOfCardsOwned the listOfCardsOwned to set
	 */
	public void setCardsOwned(List<Card> listOfCardsOwned) {
		this.cardsOwned = listOfCardsOwned;
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
		updateChanges();
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
		updateChanges();
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
		updateChanges();
	}
	
	/**
	 * update number of armies owned
	 * @param noOfnewArmies number of armies owned plus number of new armies
	 */
	public void incrementNoOfArmiesOwned(int noOfnewArmies) {
		this.noOfArmiesOwned = this.noOfArmiesOwned + noOfnewArmies;
		updateChanges();
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
		updateChanges();
	}
	
	/**
	 * setter for player name
	 * @param playerName name of player
	 */
	public void setPlayerName(String playerName){
		this.playerName=playerName;
		updateChanges();
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
		updateChanges();
	}
	
	/**
	 * add country in list of countries owned
	 * @param countryName name of country to be added
	 */
	public void removeCountry(Country countryName) {
		countriesOwned.remove(countryName);	
		updateChanges();
	}

	/**
	 * decrement number of armies owned
	 */
	public void reduceArmyInPlayer() {
		this.noOfArmiesOwned--;
		updateChanges();
	}
	
	/**
	 * increment number of armies owned
	 */
	public void addArmyInPlayer() {
		this.noOfArmiesOwned++;
		updateChanges();
	}
	

	/**
	 * initializes reinforcement armies
	 * @param gameMap 
	 * @param gameWindowScreen 
	 */
	public static void intializeReinforcementArmies(GameWindowScreen gameWindowScreen, Map gameMap) {
		for (int i = 0; i < gameWindowScreen.getPlayer().length; i++) {
			long armies = Math.round(Math.floor(gameWindowScreen.getPlayerAtIndex(i).getCountriesOwned().size() / 3));
			if (armies > 3) {
				gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned((int) armies);
			} 
			else {
				gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned(3);
			}
			updateContinentsOwned(i,gameMap, gameWindowScreen);
		}
	}
	
	/**
	 * updates continents owned by player
	 * 
	 * @param number
	 *            to indicate control value has to be assigned for which player
	 * @param gameWindowScreen 
	 * @param gameMap 
	 */
	public static void updateContinentsOwned(int number, Map gameMap, GameWindowScreen gameWindowScreen) {
		boolean flag = false;
		for (Continent continent : gameMap.getContinents()) {
			for (Country country : continent.getCountriesPresent()) {
				if (gameWindowScreen.getPlayerAtIndex(number).getCountriesOwned().contains(country)) {
					flag = true;
				} 
				else {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("ControlValue: " + continent.getControlValue() + " number: " + number);
				gameWindowScreen.getPlayerAtIndex(number).incrementNoOfArmiesOwned(continent.getControlValue());
			}
		}
		System.out.println("Current player : " + gameWindowScreen.getCurrentPlayer() + " number: " + number);
	}
	
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
	
}
