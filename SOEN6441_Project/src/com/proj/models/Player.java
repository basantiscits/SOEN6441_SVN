package com.proj.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import com.proj.views.AttackView;
import com.proj.views.FortificationView;
import com.proj.views.GameWindowScreen;

/**
 * The Player class
 * @author Ofreish
 * @since 7 Feb 2019
 * @version 1.0
 */
public class Player extends Observable{
	private String playerName;
	private int status;	// 1 cannot win 0 can win
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned = 0;
	private List<Card> cardsOwned = new ArrayList<Card>();
	private int cardsForArmies = 0;
	private GameModelCreation gameModel;
	private GameWindowScreen gameScreen;
	
	
	
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
	
	
	
	
	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public GameWindowScreen getGameScreen() {
		return gameScreen;
	}




	public void setGameScreen(GameWindowScreen gameScreen) {
		this.gameScreen = gameScreen;
	}




	public GameModelCreation getGameModel() {
		return gameModel;
	}


	public void setGameModel(GameModelCreation gameModel) {
		this.gameModel = gameModel;
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
		updateChanges();
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
	public void intializeReinforcementArmies(GameWindowScreen gameWindowScreen, Map gameMap) {
/*		for (int i = 0; i < gameWindowScreen.getPlayer().length; i++) {
			long armies = Math.round(Math.floor(gameWindowScreen.getPlayerAtIndex(i).getCountriesOwned().size() / 3));
			if (armies > 3) {
				gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned((int) armies);
			} 
			else {
				gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned(3);
			}
			updateContinentsOwned(gameMap, gameWindowScreen);
		}
		*/
		
		long armies = Math.round(Math.floor(getCountriesOwned().size() / 3));
		if(armies > 3) {
			incrementNoOfArmiesOwned((int) armies);
		}
		else {
			incrementNoOfArmiesOwned(3);
		}
		updateContinentsOwned(gameMap, gameWindowScreen);
		
	}
	
	/**
	 * updates continents owned by player
	 * 
	 * @param number
	 *            to indicate control value has to be assigned for which player
	 * @param gameWindowScreen 
	 * @param gameMap 
	 */
	public void updateContinentsOwned( Map gameMap, GameWindowScreen gameWindowScreen) {
		
		for(Continent cont:continentsOwned) {
			incrementNoOfArmiesOwned(cont.getControlValue());
		}
		
/*		boolean flag = false;
		for (Continent continent : gameMap.getContinents()) {
			for (Country country : continent.getCountriesPresent()) {
				if (getCountriesOwned().contains(country)) {
					flag = true;
				} 
				else {
					flag = false;
					break;
				}
			}
			if (flag) {
				incrementNoOfArmiesOwned(continent.getControlValue());
			}
		}*/
	}
	
	
	public void attackPhaseImplementation(Map gameMap, Player[] playersArray, GameWindowScreen gameWindowScreen) {
		this.gameScreen = gameWindowScreen;
		gameWindowScreen.getArmyAllocation().setEnabled(false);
		int index = 0 ;
		for(int i=0; i < playersArray.length;i++) {
			if(this == playersArray[i]) {
				index = i;
			}
		}
		int possibility = 0;
		for(Player p : gameScreen.getGameController().getGameModel().getPlayer()) {
			if(p.getStatus()==1) {
				possibility++;
			}
		}
		
		if(possibility==gameScreen.getGameController().getGameModel().getPlayer().length) {
			JOptionPane.showMessageDialog(null, "No Player is eligible for further Attack \n Match Drawn!!");
			gameWindowScreen.dispose();
		}
		
		if(!attackPossible()) {
			this.setStatus(1);
			JOptionPane.showMessageDialog(null, "Player is not eligible for Attack and fortification phase");
			gameWindowScreen.getGameController().getGameModel().incrementTurn();
			gameWindowScreen.getGameController().getGameModel().changePlayer();
			gameWindowScreen.getArmyAllocation().setText("Phase Change");
			gameWindowScreen.getArmyAllocation().doClick();
			gameWindowScreen.displayPlayer();
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
			gameWindowScreen.getArmyAllocation().setEnabled(true);

		}
		else {
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Attack Phase");
			AttackView attackPhase = new AttackView(gameMap,playersArray, index ,gameWindowScreen);
			attackPhase.setVisible(true);
		}
	}
	

	public void fortificationPhaseImplementation(Map map, Player[] player, GameWindowScreen gameScreen, int flag) {
		int index = 0 ;
		for(int i=0; i < player.length;i++) {
			if(this == player[i]) {
				index = i;
			}
		}
		
		gameScreen.getStartPhaseDefinedLabel().setText("Fortification Phase");
		FortificationView FV = new FortificationView(map, player, index, gameScreen);
		FV.setVisible(true);
		if(flag == 0) {
			FV.getDisposeMsg();
		}
	}
	
/*	public boolean checkDraw() {
		for(Player p : gameScreen.getGameController().getGameModel().getPlayer()) {
			if(!(p.getCountriesOwned().size()==gameScreen.getGameController().getGameModel().armiesAllocated(p))) {
				return false;
			}
		}
		return true;
	}*/
	
	public boolean attackPossible() {
		boolean hasArmy = false;
		for (Country country : this.getCountriesOwned()) {
			if (country.getNoOfArmiesPresent() > 1) {
				hasArmy = true;
				break;
			}
		}
		return hasArmy;
	}
	
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
	
}
