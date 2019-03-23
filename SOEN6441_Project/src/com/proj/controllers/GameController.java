package com.proj.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.views.GameWindowScreen;

/**
 * Game Controller Class
 * 
 * @author Aman
 * @since 23 Feb 2019
 * @version 1.0
 */
public class GameController implements ActionListener {
	private GameWindowScreen gameWindowScreen;
	private Map gameMap;
	private Player[] player;
	private GameModelCreation gameModel;

	/**
	 * constructor for Game Controller
	 * 
	 * @param gameWindowScreen
	 *            the game window screen
	 * @param gameMap
	 *            the map of continents and countries
	 */
	public GameController(GameWindowScreen gameWindowScreen, Map gameMap, Player[] player) {
		this.gameMap = gameMap;
		this.gameWindowScreen = gameWindowScreen;
		this.player = player;
		this.gameModel= new GameModelCreation(this.gameMap, this.player);
	}

	
	
	public GameWindowScreen getGameWindowScreen() {
		return gameWindowScreen;
	}



	public void setGameWindowScreen(GameWindowScreen gameWindowScreen) {
		this.gameWindowScreen = gameWindowScreen;
	}



	public Map getGameMap() {
		return gameMap;
	}



	public void setGameMap(Map gameMap) {
		this.gameMap = gameMap;
	}



	public Player[] getPlayer() {
		return player;
	}



	public void setPlayer(Player[] player) {
		this.player = player;
	}



	public GameModelCreation getGameModel() {
		return gameModel;
	}



	public void setGameModel(GameModelCreation gameModel) {
		this.gameModel = gameModel;
	}



	/**
	 * action performed when reinforcement phase starts
	 * 
	 * @param e action event performed that triggers the response
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		switch (button) {
		case "Place Army":
			updateGame((String) gameWindowScreen.getCountriesComboBox().getSelectedItem());
			getGameModel().incrementTurn();
			getGameModel().changePlayer();
			gameWindowScreen.displayPlayer();
			if (checkStartUpEnd()) {
				gameWindowScreen.setCurrentPlayer(0);
				gameWindowScreen.getArmyAllocation().setText("Phase Change");
				gameWindowScreen.getArmyAllocation().doClick();
			}
			break;
		case "Reinforcement Phase":
			
			updateGame((String) gameWindowScreen.getCountriesComboBox().getSelectedItem());
			gameWindowScreen.reinforce();
			gameWindowScreen.displayPlayer();
			break;
		case "Phase Change":
			cardExchange();
			
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
			gameWindowScreen.getArmyAllocation().setText("Reinforcement Phase");
			Player.intializeReinforcementArmies(gameWindowScreen, gameMap);
			gameWindowScreen.displayPlayer();
			break;
		
		case "View Available Cards":
			gameWindowScreen.viewAvailableCards();
		}
	}

	/**
	 * start method for game window screen
	 */
/*	public void start() {
		if (gameWindowScreen.getCurrentPlayer() == (gameWindowScreen.getPlayer().length - 1)) {
			gameWindowScreen.setCurrentPlayer(0);
		} 
		else {
			gameWindowScreen.setCurrentPlayer(gameWindowScreen.getCurrentPlayer() + 1);
		}
	}*/

	/**
	 * checks end of start up phase
	 * 
	 * @return true if all armies have been allocated to the player
	 * @return false if more armies can be allocated to the player
	 */
	public boolean checkStartUpEnd() {
		int flag = 0;
		for (Player p : gameWindowScreen.getPlayer()) {
			if (p.getNoOfArmiesOwned() == 0) {
				flag++;
			}
		}
		if (flag == gameWindowScreen.getPlayer().length) {
			return true;
		}
		return false;
	}

	/**
	 * creates the start up phase tree showing country and number of armies
	 * 
	 * @param country
	 *            name of country
	 */
	public void updateGame(String country) {
		
		if(getGameModel().getCurrPlayer().getNoOfArmiesOwned()>0) {
			getGameModel().getCurrPlayer().reduceArmyInPlayer();
			for(Country c : getGameModel().getCurrPlayer().getCountriesOwned()) {
				if(c.getCountryName().equals(country)) {
					c.addNoOfArmiesCountry();
				}
			}
			//gameWindowScreen.createStartUpTree();
		}
	}
	
	
	public void cardExchange()
	
	{
		
		gameWindowScreen.cardExchangeView();
		
	}
	
	
	
	

	/**
	 * updates continents owned by player
	 * 
	 * @param number
	 *            to indicate control value has to be assigned for which player
	 */
/*	public void updateContinentsOwned(int number) {
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
	}*/
}
