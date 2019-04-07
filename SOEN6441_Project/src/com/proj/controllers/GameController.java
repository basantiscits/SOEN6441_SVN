package com.proj.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
public class GameController implements ActionListener, Serializable {
	private GameWindowScreen gameWindowScreen;
	private GameModelCreation gameModel;
	private String sSaveFileName="";


	/**
	 * constructor for Game Controller
	 * 
	 * @param gameWindowScreen the game window screen
	 * @param player Array object of Player class
	 * @param gameMap the map of continents and countries
	 */
	public GameController(GameWindowScreen gameWindowScreen, GameModelCreation gameModel) {
		this.gameWindowScreen = gameWindowScreen;
		this.gameModel= gameModel;
		gameModel.setGameScreen(gameWindowScreen);
	}

	
	/**
	 * getter for game window screen
	 * @return Object of GameWindowScreen
	 */
	public GameWindowScreen getGameWindowScreen() {
		return gameWindowScreen;
	}


	/**
	 * setter for Game window screen
	 * @param gameWindowScreen Object of GameWindowScreen
	 */
	public void setGameWindowScreen(GameWindowScreen gameWindowScreen) {
		this.gameWindowScreen = gameWindowScreen;
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
	 * @param gameModel Object of GameModelCreation
	 */
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
			if (checkStartUpEnd()) {
				gameModel.setCurrPlayer(gameModel.getPlayer()[0]);
				gameWindowScreen.getArmyAllocation().setText("Phase Change");
				gameWindowScreen.getArmyAllocation().doClick();
			}
			break;
			
		case "Reinforcement Phase":
			updateGame((String) gameWindowScreen.getCountriesComboBox().getSelectedItem());
			gameWindowScreen.displayPlayer();
			gameWindowScreen.reinforce();
			break;
			
		case "Phase Change":
			gameModel.setGameState(1);
			gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
			gameWindowScreen.getArmyAllocation().setText("Reinforcement Phase");
			gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
			gameWindowScreen.displayPlayer();
			break;
		
		case "Cards":
			gameWindowScreen.viewAvailableCards();
			break;
		case "Save Button":
			sSaveFileName=FncSaveFileName();
			try {
				gameWindowScreen.saveExistingGame(gameModel,sSaveFileName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
	}

	private String FncSaveFileName() {
		String sFilename="";
		sFilename = JOptionPane.showInputDialog(null,"Enter the File name you want to save:",JOptionPane.OK_CANCEL_OPTION | JOptionPane.QUESTION_MESSAGE);
		return sFilename;
	}


	/**
	 * checks end of start up phase
	 * 
	 * @return true if all armies have been allocated to the player
	 * @return false if more armies can be allocated to the player
	 */
	public boolean checkStartUpEnd() {
		int flag = 0;
		for (Player p : gameModel.getPlayer()) {
			if (p.getNoOfArmiesOwned() == 0) {
				flag++;
			}
		}
		if (flag == gameModel.getPlayer().length) {
			return true;
		}
		return false;
	}

	/**
	 * creates the start up phase tree showing country and number of armies
	 * 
	 * @param country name of country
	 */
	public void updateGame(String country) {
		if(getGameModel().getCurrPlayer().getNoOfArmiesOwned()>0) {
			getGameModel().getCurrPlayer().reduceArmyInPlayer();
			for(Country c : getGameModel().getCurrPlayer().getCountriesOwned()) {
				if(c.getCountryName().equals(country)) {
					c.addNoOfArmiesCountry();
				}
			}
		}
	}
	
	/**
	 * exchange cards
	 */
	public void cardExchange(){
		gameWindowScreen.cardExchangeView();
	}
}
