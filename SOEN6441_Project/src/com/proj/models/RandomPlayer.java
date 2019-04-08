package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;

public class RandomPlayer implements BehaviorStrategies, Serializable {

	private Random random = new Random();
	
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		System.out.println("Name in Random startUp: "+gameModel.getCurrPlayer().getPlayerName());
		int limit = random.nextInt(gameModel.getCurrPlayer().getCountriesOwned().size());
		if (limit == gameModel.getCurrPlayer().getCountriesOwned().size()) {
			limit = limit - 1;
		}

		Country country = gameModel.getCurrPlayer().getCountriesOwned().get(limit);

		if (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		System.out.println("StartUp phase done for Random");
		 
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		while (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			int limit = random.nextInt(gameModel.getCurrPlayer().getCountriesOwned().size());
			if (limit == gameModel.getCurrPlayer().getCountriesOwned().size()) {
				limit = limit - 1;
			}
			Country country = gameModel.getCurrPlayer().getCountriesOwned().get(limit);
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}

		gameModel.setGameState(2);
		System.out.println("Reinforcement phase done for Random");
		attackPhase(gameModel);
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		Player attacker=gameModel.getCurrPlayer();
		Random randomCountry=new Random();
		Random randomAttacks=new Random();
		Country randomDefendingCountry=null;
		ArrayList<Country> availableAttackingCountries = new ArrayList<Country>();
		
		for(Country c : attacker.getCountriesOwned()) {
			if(c.getNoOfArmiesPresent()>1) {
				availableAttackingCountries.add(c);
			}
		}
		
		if(availableAttackingCountries.size()>0) {
			int limit = randomCountry.nextInt(availableAttackingCountries.size());
			if (limit == availableAttackingCountries.size()) {
				limit = limit - 1;
			}
			
			Country attackingCountry = availableAttackingCountries.get(limit);
			System.out.println("attackingCountry: "+attackingCountry.getCountryName());
			int noOfAttacks = randomAttacks.nextInt(attackingCountry.getNoOfArmiesPresent()-1);
			ArrayList<Country> defendingCountries=new ArrayList<Country>();
			int flag =0;
			Country countryToBeChecked = null;
			for(String n:attackingCountry.getListOfNeighbours()){
				for(Continent continent:gameModel.getMapDetails().getContinents()){
					for(Country neighbour:continent.getCountriesPresent()){
						if( neighbour.getCountryName().equalsIgnoreCase(n)){
							for(Player p:gameModel.getPlayer()){
								if(p.getCountriesOwned().contains(neighbour) && p!=attacker){
									flag=1;
									defendingCountries.add(neighbour);
								}
							}
						}
					}
				}
			}
			for(Country c : defendingCountries) {
				System.out.println(c.getCountryName());
			}
			if(flag==1 && defendingCountries.size()>0){
				AttackController attack=new AttackController(gameModel);
				if((defendingCountries.size()-1)<1) {
					randomDefendingCountry=defendingCountries.get(randomCountry.nextInt(1));
				}
				else {
					randomDefendingCountry=defendingCountries.get(randomCountry.nextInt(defendingCountries.size()-1));
				}
				
				int noOfAttackingArmies=attackingCountry.getNoOfArmiesPresent()-1;
				int noOfDefendingArmies=randomDefendingCountry.getNoOfArmiesPresent();
				while(noOfAttacks>0 && noOfAttackingArmies>0 && noOfDefendingArmies>0){
					int attackerDiceCount=0;
					int defenderDiceCount=0;
					Random randomDiceCount=new Random();
					if(noOfAttackingArmies==1 && noOfDefendingArmies==1){
						attackerDiceCount=1;
						defenderDiceCount=1;
					}
					else if(noOfAttackingArmies==1 && noOfDefendingArmies>1){
						attackerDiceCount=1;
						defenderDiceCount=randomDiceCount.nextInt(2)+1;
					}	
					else if(noOfAttackingArmies==2 && noOfDefendingArmies==1){
						attackerDiceCount=randomDiceCount.nextInt(2)+1;
						defenderDiceCount=1;
					}
					else if(noOfAttackingArmies==2 && noOfDefendingArmies>1){
						attackerDiceCount=randomDiceCount.nextInt(2)+1;
						defenderDiceCount=randomDiceCount.nextInt(2)+1;
					}	
					else if(noOfAttackingArmies>2 && noOfDefendingArmies==1){
						attackerDiceCount=randomDiceCount.nextInt(3)+1;
						defenderDiceCount=1;
					}
					else if(noOfAttackingArmies>2 && noOfDefendingArmies>1){
						attackerDiceCount=randomDiceCount.nextInt(3)+1;
						defenderDiceCount=randomDiceCount.nextInt(2)+1;
					}
					System.out.println("attackingCountry: "+attackingCountry.getCountryName());
					System.out.println("randomDefendingCountry: "+randomDefendingCountry.getCountryName());
					if(attack.normalAttack(attackingCountry.getCountryName(), randomDefendingCountry.getCountryName(), attackerDiceCount, defenderDiceCount)){
						attack.numberOfArmiesTransfered(attack.attackerDiceCount, attackingCountry, randomDefendingCountry);
						defendingCountries.remove(0);	
						attacker.getCardsOwned().add(Card.getNewCard());   
						attacker.setNoOfCardsOwned(gameModel.getCurrPlayer().getNoOfCardsOwned()+1);
						break;
					}
					noOfAttackingArmies=attackingCountry.getNoOfArmiesPresent()-1;
					noOfDefendingArmies=randomDefendingCountry.getNoOfArmiesPresent();
					noOfAttacks--;
				}
			}
		}
		
		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
			gameModel.getGameScreen().dispose();
			JOptionPane.showMessageDialog(null, attacker.getPlayerName()+" won the game!!! CONGRATULATION!!!");
			return;
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
		System.out.println("Inside Random attack");
		for(Player p : gameModel.getPlayer()) {
			System.out.print("Player: "+p.getPlayerName()+" , "+p.getPlayerType()+" , "+p.getNoOfArmiesOwned()+" , ");
			for(Country c : p.getCountriesOwned()) {
				System.out.print(c.getCountryName()+"["+c.getNoOfArmiesPresent()+"]");
			}
			System.out.println();
		}
		
		fortificationPhase(gameModel);
	}
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		ArrayList<Country> countryList = new ArrayList<Country>();
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			if(c.getNoOfArmiesPresent() > 1) {
				countryList.add(c);
			}
		}
		if(countryList.size()>0) {
			int limit1 = random.nextInt(countryList.size());
			if (limit1 == countryList.size()) {
				limit1 = limit1 - 1;
			}
			for(Country c : countryList) {
				System.out.println("String name as country source: "+c.getCountryName());
			}
			Country sourceCountry = countryList.get(limit1);
			System.out.println("Fort random source : "+sourceCountry.getCountryName()+" , "+sourceCountry.getNoOfArmiesPresent());
			ArrayList<Country> neighbourList = new ArrayList<Country>();
			//neighbourList.addAll(sourceCountry.getListOfNeighbours());
			
			for(String c : sourceCountry.getListOfNeighbours()) {
				Country count = gameModel.getMapDetails().searchCountry(c);
				if(gameModel.getCurrPlayer().getCountriesOwned().contains(count)) {
					neighbourList.add(count);
				}
			}
			
			if(neighbourList.size()>0) {
				int limit2 = random.nextInt(neighbourList.size());
				if (limit2 == neighbourList.size()) {
					limit2 = limit2 - 1;
				}
				for(Country c : neighbourList) {
					System.out.println("Country name as country neihbor: "+c.getCountryName());
				}
				Country destinationCountry = neighbourList.get(limit2);
				//Country destinationCountry = gameModel.getMapDetails().searchCountry(destinationCountryName);
				System.out.println("Fort random target : "+destinationCountry.getCountryName()+" , "+destinationCountry.getNoOfArmiesPresent());
				if(sourceCountry.getNoOfArmiesPresent()>1) {
					int armies = random.nextInt(sourceCountry.getNoOfArmiesPresent()-1);
					System.out.println("Transfer army: "+armies);
					for(int i = 0; i < armies; i++) {
						sourceCountry.removeNoOfArmiesCountry();
						destinationCountry.addNoOfArmiesCountry();
					}
				}
			}
			}
		System.out.println("Fortification finish");
		for(Player p : gameModel.getPlayer()) {
			System.out.print("Player: "+p.getPlayerName()+" , "+p.getPlayerType()+" , "+p.getNoOfArmiesOwned()+" , ");
			for(Country c : p.getCountriesOwned()) {
				System.out.print(c.getCountryName()+"["+c.getNoOfArmiesPresent()+"]");
			}
			System.out.println();
		}
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
