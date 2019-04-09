package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;

/**
 * Benevolent class
 * @author Basant 
 * @since 23 Mar 2019
 * @version 1.2
 */
public class Benevolent implements BehaviorStrategies, Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * start up phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		System.out.println("Name in Benevolent startUp: "+gameModel.getCurrPlayer().getPlayerName());
		Country country = gameModel.getCurrPlayer().getCountriesOwned().get(0);
		int countOfArmies = country.getNoOfArmiesPresent();
		for (Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			if (countOfArmies >= c.getNoOfArmiesPresent()) {
				country = c;
				countOfArmies = country.getNoOfArmiesPresent();
			}

		}
		if (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		System.out.println("StartUp phase done for Benevolent");
	}

	/**
	 * Reinforcement Phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		while (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			Country country = gameModel.getCurrPlayer().getCountriesOwned().get(0);
			int noOfArmies = country.getNoOfArmiesPresent();
			for (Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
				if (c.getNoOfArmiesPresent() < noOfArmies) {
					country = c;
					noOfArmies = country.getNoOfArmiesPresent();
				}
			}
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();

		}
		
		gameModel.setGameState(2);
		System.out.println("Reinforcement phase done for benevolent");
		attackPhase(gameModel);
		
	}

	/**
	 * Attack phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void attackPhase(GameModelCreation gameModel) {
		Player attacker=gameModel.getCurrPlayer();
		if(gameModel.getGameScreen()!=null) {
			if(gameModel.getPlayer().length==1){
				System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
				gameModel.getGameScreen().dispose();
				JOptionPane.showMessageDialog(null, attacker.getPlayerName()+" won the game!!! CONGRATULATION!!!");
				return;
			}
		}
		
		if(attacker.getNoOfCardsOwned()>4){
			attacker.setCardsForArmies(attacker.getCardsForArmies() + 5);
			attacker.setNoOfArmiesOwned(attacker.getNoOfArmiesOwned() + gameModel.getCurrPlayer().getCardsForArmies());
			Card initialCard=attacker.getCardsOwned().get(0);
			int count=0;
			List<Card> cards=new ArrayList<Card>();
			for(Card card: attacker.getCardsOwned())
			{
				if(initialCard==card){
					cards.add(card);
					count++;
				}
				if(count==3){
					break;
				}
			}
			for(Card card:cards){
				attacker.getCardsOwned().remove(card);
				attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned()-1);
			}
		}
		fortificationPhase(gameModel);	
	}
	
	/**
	 * Minimum armies in country
	 * @param player Object of Player class
	 * @return name of country
	 */
	private Country minArmiesInCountry(Player player) {
		Country country = player.getCountriesOwned().get(0);
		int noOfArmies = country.getNoOfArmiesPresent();
		for(Country c : player.getCountriesOwned()) {
			if(c.getNoOfArmiesPresent() < noOfArmies) {
				country = c;
				noOfArmies = c.getNoOfArmiesPresent();
			}
		}
		return country;
		
	}

	/**
	 * Fortification phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		System.out.println("armies1 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		Country minCountry = minArmiesInCountry(gameModel.getCurrPlayer());
		Country maxCountry=null;
		int maxCountryCount=0;
		int initialCount=0;
		
		for(Country c: gameModel.getCurrPlayer().getCountriesOwned()) {
			if(c.getListOfNeighbours().contains(minCountry.getCountryName()) && c.getNoOfArmiesPresent() > minCountry.getNoOfArmiesPresent()) {
				initialCount=c.getNoOfArmiesPresent();
				if(maxCountry==null) {
					maxCountry=c;
					maxCountryCount=c.getNoOfArmiesPresent();
				}
				else if(maxCountryCount<initialCount){
					maxCountry=c;
					maxCountryCount=c.getNoOfArmiesPresent();
				}
			}
		}
		
		if(maxCountry !=null && maxCountryCount>1) {
			while(maxCountry.getNoOfArmiesPresent()>=minCountry.getNoOfArmiesPresent()){
				minCountry.addNoOfArmiesCountry();
				maxCountry.removeNoOfArmiesCountry();	
			}
		}
		
		
		
		
//		for(String cName : minCountry.getListOfNeighbours()) {
//			Country c = gameModel.getMapDetails().searchCountry(cName);
//			if(c.getNoOfArmiesPresent() > minCountry.getNoOfArmiesPresent() && gameModel.getCurrPlayer().getCountriesOwned().contains(c)) {
//				minCountry.addNoOfArmiesCountry();
//				c.removeNoOfArmiesCountry();
//			}
//		}

		
		
		
		//System.out.println("armies2 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
/*		System.out.println("Fortification finish");
		for(Player p : gameModel.getPlayer()) {
			System.out.print("Player: "+p.getPlayerName()+" , "+p.getPlayerType()+" , "+p.getNoOfArmiesOwned()+" , ");
			for(Country c : p.getCountriesOwned()) {
				System.out.print(c.getCountryName()+"["+c.getNoOfArmiesPresent()+"]");
			}
			System.out.println();
		}*/

		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
		if(gameModel.getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
			System.out.println("No turn for neutral Player");
			gameModel.incrementTurn();
			gameModel.changePlayer();
			gameModel.setGameState(1);
		}
	}
}
