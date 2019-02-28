package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.views.GameWindowScreen;

public class GameController implements ActionListener{
	
	
	
	private GameWindowScreen gameWindowScreen;
	private Map gameMap;
	

	public GameController(GameWindowScreen gameWindowScreen, Map gameMap) {
		
		this.gameMap = gameMap;
		this.gameWindowScreen = gameWindowScreen;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String button = e.getActionCommand();
		switch(button) {
			case "Place Army":
				updateGame((String)gameWindowScreen.getCountriesComboBox().getSelectedItem());
				start();
				gameWindowScreen.displayPhase();
				if(checkStartUpEnd()) {
					gameWindowScreen.setCurrentPlayer(0); 
					gameWindowScreen.getArmyAllocation().setText("Phase Change");
					gameWindowScreen.getArmyAllocation().doClick();
				}
				break;
			case "Reinforcement Phase":
				updateGame((String)gameWindowScreen.getCountriesComboBox().getSelectedItem());
				gameWindowScreen.reinforce();
				gameWindowScreen.displayPhase();
				break;
			case "Phase Change":
				gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
				gameWindowScreen.getArmyAllocation().setText("Reinforcement Phase");
				intializeReinforcementArmies();
				gameWindowScreen.displayPhase();
				break;
		}
	}

		public void start() {
			if(gameWindowScreen.getCurrentPlayer()==(gameWindowScreen.getPlayer().length-1)) {
				gameWindowScreen.setCurrentPlayer(0);
			}
			else {
				//currentPlayer++; 
				gameWindowScreen.setCurrentPlayer(gameWindowScreen.getCurrentPlayer()+1);;
			}
		}
	
		public void intializeReinforcementArmies() {
			
			for(int i=0;i<gameWindowScreen.getPlayer().length;i++) {
				long armies =  Math.round(Math.floor(gameWindowScreen.getPlayerAtIndex(i).getCountriesOwned().size()/3));
				if(armies>3) {
					gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned((int)armies);
				}
				else {
					gameWindowScreen.getPlayerAtIndex(i).incrementNoOfArmiesOwned(3);
				}
				
				updateContinentsOwned(i);
			}
		}
		
		public void startReinforcementPhase() {
			if(gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).getNoOfArmiesOwned()==0) {
				gameWindowScreen.getStartPhaseDefinedLabel().setText("Reinforcement Phase");
				intializeReinforcementArmies();
				
				gameWindowScreen.getArmyAllocation().setText("Reinforcement Phase");
			}
		}
		
		public boolean checkStartUpEnd() {
			int flag = 0 ;
			for(Player p: gameWindowScreen.getPlayer()) {
				if(p.getNoOfArmiesOwned()==0) {
					flag++;
				}
			}
			if(flag==gameWindowScreen.getPlayer().length) {
				return true;
			}
			return false;
		}
		
		public void updateGame(String country) {
			
			if(gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).getNoOfArmiesOwned()>0) {
				gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).reduceArmyInPlayer();
				for(Country c : gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).getCountriesOwned()) {
					if(c.getCountryName().equals(country)) {
						c.addNoOfArmiesCountry();
					}
				}
				gameWindowScreen.createStartUpTree();
			}
		}
		
		public void updateContinentsOwned(int number) {
			boolean flag = false;
			for(Continent continent : gameMap.getContinents()) {
				for(Country country : continent.getCountriesPresent()) {
					System.out.println("CNT: "+country.getCountryName());
					if(gameWindowScreen.getPlayerAtIndex(number).getCountriesOwned().contains(country)) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
				if(flag) {
					System.out.println("UCO: "+gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).getNoOfArmiesOwned());
					gameWindowScreen.getPlayerAtIndex(gameWindowScreen.getCurrentPlayer()).incrementNoOfArmiesOwned(continent.getControlValue());
				}
			}
			
		}
		
		
	}
	
	
	
	
	
	
	

	
	
	
	
	
	

