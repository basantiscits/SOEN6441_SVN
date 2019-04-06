package com.proj.models;

import java.util.ArrayList;
import java.util.List;

import com.proj.controllers.AttackController;

public class Benevolent implements BehaviorStrategies {

	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
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
		
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
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

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		Player attacker=gameModel.getCurrPlayer();
		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
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
		
		Country minCountry = minArmiesInCountry(gameModel.getCurrPlayer());
		
		for(String cName : minCountry.getListOfNeighbours()) {
			Country c = gameModel.getMapDetails().searchCountry(cName);
			if(c.getNoOfArmiesPresent() > minCountry.getNoOfArmiesPresent() ) {
				minCountry.addNoOfArmiesCountry();
				c.removeNoOfArmiesCountry();
			}
		}

		
		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
		//gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
	}

}
