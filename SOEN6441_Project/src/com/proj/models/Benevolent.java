package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;

public class Benevolent implements BehaviorStrategies, Serializable {

	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
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

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		//System.out.println("armies1 in reinforce: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		//int flag = 0;
		while (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			Country country = gameModel.getCurrPlayer().getCountriesOwned().get(0);
			int noOfArmies = country.getNoOfArmiesPresent();
			for (Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
				if (c.getNoOfArmiesPresent() < noOfArmies) {
					country = c;
					noOfArmies = country.getNoOfArmiesPresent();
				}
			}
			//flag++;
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();

		}
		
		gameModel.setGameState(2);
/*		System.out.println("flag in reinforce: "+flag);
		System.out.println("armies2 in reinforce: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());*/
		System.out.println("Reinforcement phase done for benevolent");
		attackPhase(gameModel);
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		//System.out.println("armies1 in attack: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		Player attacker=gameModel.getCurrPlayer();
/*		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
			return;
		}*/
		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
			gameModel.getGameScreen().dispose();
			JOptionPane.showMessageDialog(null, attacker.getPlayerName()+" won the game!!! CONGRATULATION!!!");
			//gameModel.getGameScreen()attacker;
			return;
		}
		
		if(attacker.getNoOfCardsOwned()>4){
			//Cards to implemented
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
		//System.out.println("armies2 in attack: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		fortificationPhase(gameModel);	
	}
	
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

	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("armies1 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		Country minCountry = minArmiesInCountry(gameModel.getCurrPlayer());
/*		ArrayList<Country> countryList = new ArrayList<Country>();
		for(String cName : minCountry.getListOfNeighbours()) {
			Country c = gameModel.getMapDetails().searchCountry(cName);
			if(c.getOwnedBy().) {
				
			}
		}*/
		for(String cName : minCountry.getListOfNeighbours()) {
			Country c = gameModel.getMapDetails().searchCountry(cName);
			if(c.getNoOfArmiesPresent() > minCountry.getNoOfArmiesPresent() && gameModel.getCurrPlayer().getCountriesOwned().contains(c)) {
/*				System.out.println("minCountry: "+minCountry.getCountryName());
				System.out.println("c: "+c.getCountryName());*/
				minCountry.addNoOfArmiesCountry();
				c.removeNoOfArmiesCountry();
			}
		}
		//System.out.println("armies2 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		
		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
		if(gameModel.getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
			System.out.println("No turn for neutral Player");
			gameModel.incrementTurn();
			gameModel.changePlayer();
			gameModel.setGameState(1);
		//	fortifyView.getGameModel().setGameState(1);
			//fortifyView.getGameModel().getGameScreen().displayPlayer();
		}
		//gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
	}

}
