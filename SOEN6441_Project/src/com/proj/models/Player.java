package com.proj.models;

import java.io.Serializable;
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
public class Player extends Observable implements Serializable{
	private String playerName;
	private int status;	
	private List<Country> countriesOwned;
	private List<Continent> continentsOwned;
	private int noOfArmiesOwned;
	private int noOfCardsOwned = 0;
	private List<Card> cardsOwned = new ArrayList<Card>();
	private int cardsForArmies = 0;
	private BehaviorStrategies strategy;
	private PlayerType playerType;
	private GameModelCreation gameModel;
	private GameWindowScreen gameScreen;
	public Country randomCountry;
	
	
	
	/**
	 * constructor for Player class
	 * @param string name of player
	 */
	public Player(String string, PlayerType playerType) {
		this.playerName = string;
		this.playerType = playerType;
		countriesOwned = new ArrayList<Country>();
		cardsOwned=new ArrayList<Card>();
		continentsOwned=new ArrayList<Continent>();
		updateChanges();
	}
	
	
	
	public BehaviorStrategies getStrategy() {
		return strategy;
	}



	public void setStrategy(BehaviorStrategies strategy) {
		this.strategy = strategy;
	}



	public PlayerType getPlayerType() {
		return playerType;
	}



	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}



	/**
	 * getter for status
	 * @return status whether won 
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * setter for status
	 * @param status 0 if won and 1 if lost
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * getter for game screen
	 * @return game screen
	 */
	public GameWindowScreen getGameScreen() {
		return gameScreen;
	}

	/**
	 * setter for game screen
	 * @param gameScreen Object of GameWindowScreen class
	 */
	public void setGameScreen(GameWindowScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	/**
	 * getter for game model
	 * @return game model
	 */
	public GameModelCreation getGameModel() {
		return gameModel;
	}

	/**
	 * setter for game model
	 * @param gameModel Object of GameModelCreation class
	 */
	public void setGameModel(GameModelCreation gameModel) {
		this.gameModel = gameModel;
	}

	/**
	 * getter for cards for armies
	 * @return the cardsForArmies
	 */
	public int getCardsForArmies() {
		return cardsForArmies;
	}

	/**
	 * setter for cards for armies
	 * @param cardsForArmies the cardsForArmies to set
	 */
	public void setCardsForArmies(int cardsForArmies) {
		this.cardsForArmies = cardsForArmies;
	}
	
	/**
	 * getter for cards owned
	 * @return the listOfCardsOwned
	 */
	public List<Card> getCardsOwned() {
		return cardsOwned;
	}

	/**
	 * setter for cards owned
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
	 * @param gameMap Object of Map class
	 * @param gameWindowScreen Object of GameWindowScreen class
	 */
	public void intializeReinforcementArmies(GameModelCreation gameModel) {
		
		long armies = Math.round(Math.floor(getCountriesOwned().size() / 3));
		if(armies > 3) {
			incrementNoOfArmiesOwned((int) armies);
		}
		else {
			incrementNoOfArmiesOwned(3);
		}
		updateContinentsOwned(gameModel);
		System.out.println("Armies after reinforcement1: "+gameModel.getCurrPlayer().getNoOfArmiesOwned()+" "+gameModel.getCurrPlayer().getPlayerName());
		System.out.println("Armies after reinforcement2: "+ getNoOfArmiesOwned() + " "+getPlayerName());
		getStrategy().reinforcementPhase(gameModel);
		
	}
	
	/**
	 * updates continents owned by player
	 * 
	 * @param gameWindowScreen Object of GameWindowScreen class
	 * @param gameMap Object of Map class
	 */
	public void updateContinentsOwned(GameModelCreation gameModel) {
		
		for(Continent cont:continentsOwned) {
			incrementNoOfArmiesOwned(cont.getControlValue());
		}
	}
	
	/**
	 * Attack phase implementation
	 * @param gameMap Object of Map class
	 * @param playersArray Array object of Player class
	 * @param gameWindowScreen Object of GameWindowScreen class
	 */
	public void attackPhaseImplementation(GameModelCreation gameModel, GameWindowScreen gameWindowScreen) {
		this.gameScreen = gameWindowScreen;
		gameWindowScreen.getArmyAllocation().setEnabled(false);
		int index = 0 ;
		for(int i=0; i < gameModel.getPlayer().length;i++) {
			if(this == gameModel.getPlayer()[i]) {
				index = i;
			}
		}
		int possibility = 0;
		for(Player p : gameModel.getPlayer()) {
			if(p.getStatus()==1) {
				possibility++;
			}
		}
		
		if(!attackPossible()) {
			this.setStatus(1);
			JOptionPane.showMessageDialog(null, "Player is not eligible for Attack and fortification phase");
			gameWindowScreen.getGameController().getGameModel().incrementTurn();
			gameWindowScreen.getGameController().getGameModel().changePlayer();
			if(gameWindowScreen.getGameController().getGameModel().getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
				System.out.println("No turn for neutral Player");
				gameWindowScreen.getGameController().getGameModel().incrementTurn();
				gameWindowScreen.getGameController().getGameModel().changePlayer();
				gameWindowScreen.displayPlayer();
			}
			gameWindowScreen.getArmyAllocation().setText("Phase Change");
			gameWindowScreen.getArmyAllocation().doClick();
			gameWindowScreen.displayPlayer();
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
			gameWindowScreen.getArmyAllocation().setEnabled(true);

		}
		else {
			gameModel.setGameState(2);
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Attack Phase");
			AttackView attackPhase = new AttackView(gameModel);
			attackPhase.setVisible(true);
		}
	}
	
	
	
	public void initialArmyAllocation(GameModelCreation gameModel) {
		getStrategy().startUpPhase(gameModel);
		gameModel.incrementTurn();
		gameModel.changePlayer();
	}
	
	/**
	 * Fortification phase Implementation
	 * @param map Object of Map class
	 * @param player Array Object of Player class
	 * @param gameScreen Object of GameWindowScreen class
	 * @param flag set to 1 if only 1 army present in country
	 */
	public void fortificationPhaseImplementation(GameModelCreation gameModel, int flag) {
		int index = 0 ;
		for(int i=0; i < gameModel.getPlayer().length;i++) {
			if(this == gameModel.getPlayer()[i]) {
				index = i;
			}
		}

		gameModel.setGameState(3);
		FortificationView FV = new FortificationView(gameModel);
		FV.setVisible(true);
		if(flag == 0) {
			FV.getDisposeMsg();
		}
	}
	
	/**
	 * Attack possible
	 * @return true if attack possible else false
	 */
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
	
	/**
	 * Update changes
	 */
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
	
}
