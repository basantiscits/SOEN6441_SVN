package com.proj.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;

public class RandomPlayer implements BehaviorStrategies {

	private Random random = new Random();
	
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		int limit = random.nextInt(gameModel.getCurrPlayer().getCountriesOwned().size());
		if (limit == gameModel.getCurrPlayer().getCountriesOwned().size()) {
			limit = limit - 1;
		}

		Country country = gameModel.getCurrPlayer().getCountriesOwned().get(limit);

		if (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		while (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			int limit = random.nextInt(gameModel.getCurrPlayer().getCountriesOwned().size());
			if (limit == gameModel.getCurrPlayer().getCountriesOwned().size())
				limit = limit - 1;
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
		// TODO Auto-generated method stub
		Player attacker=gameModel.getCurrPlayer();
		Random randomCountry=new Random();
		//Country attackingCountry = attacker.getCountriesOwned().get(randomCountry.nextInt(attacker.getCountriesOwned().size()-1));
		Random randomAttacks=new Random();
		Country randomDefendingCountry=null;
		
		Country attackingCountry=attacker.getCountriesOwned().get(randomCountry.nextInt(attacker.getCountriesOwned().size()));
		int noOfAttacks=randomAttacks.nextInt(attackingCountry.getNoOfArmiesPresent()-1);
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
		if(flag==1 && defendingCountries.size()>0){
			AttackController attack=new AttackController(gameModel);
			randomDefendingCountry=defendingCountries.get(randomCountry.nextInt(defendingCountries.size()-1));
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
		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
			JOptionPane.showMessageDialog(null, attacker.getPlayerName()+" won the game!!! CONGRATULATION!!!");
			//gameModel.getGameScreen()attacker;
			gameModel.getGameScreen().dispose();
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
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		ArrayList<Country> countryList = new ArrayList<Country>();
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			if(c.getNoOfArmiesPresent() > 1) {
				countryList.add(c);
			}
		}
		
		int limit1 = random.nextInt(countryList.size());
		if (limit1 == countryList.size()) {
			limit1 = limit1 - 1;
		}
		
		Country sourceCountry = countryList.get(limit1);
		
		ArrayList<String> neighbourList = new ArrayList<String>();
		neighbourList.addAll(sourceCountry.getListOfNeighbours());
		
		int limit2 = random.nextInt(neighbourList.size());
		if (limit2 == neighbourList.size()) {
			limit2 = limit2 - 1;
		}
		
		String destinationCountryName = neighbourList.get(limit2);
		Country destinationCountry = gameModel.getMapDetails().searchCountry(destinationCountryName);
		
		int armies = random.nextInt(sourceCountry.getNoOfArmiesPresent()-1);
		for(int i = 0; i < armies; i++) {
			sourceCountry.removeNoOfArmiesCountry();
			destinationCountry.addNoOfArmiesCountry();
		}
		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
		//gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
	}

}
