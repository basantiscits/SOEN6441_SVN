package com.proj.models;

import java.util.ArrayList;
import java.util.Random;

import com.proj.controllers.AttackController;

public class RandomPlayer implements BehaviorStrategies {

	
	
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		Player attacker=gameModel.getCurrPlayer();
		Random randomCountry=new Random();
		//Country attackingCountry = attacker.getCountriesOwned().get(randomCountry.nextInt(attacker.getCountriesOwned().size()-1));
		Random randomAttacks=new Random();
		Country randomDefendingCountry=null;
		
		Country attackingCountry=attacker.randomCountry;
		int noOfAttacks=randomAttacks.nextInt(attackingCountry.getNoOfArmiesPresent()-1);
		ArrayList<Country> defendingCountries=new ArrayList<Country>();
		int flag =0;
		Country countryToBeChecked = null;
		for(String c : attackingCountry.getListOfNeighbours()) {
			for(Continent continent:gameModel.getMapDetails().getContinents()){
				for(Country country:continent.getCountriesPresent()) {
					if(country.getCountryName().equalsIgnoreCase(c)) {
						countryToBeChecked = country;
					}
				}
			}
			if(!attacker.getCountriesOwned().contains(countryToBeChecked)) {
				defendingCountries.add(countryToBeChecked);
				flag=1;
				System.out.println(c);
			}
		}
		if(flag==1){
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
					attack.numberOfArmiesTransfered(attack.attackerDiceCount, attackingCountry, defendingCountries.get(0));
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
			return;
		}
		if(attacker.getNoOfCardsOwned()>4){
			//Cards to implemented
			attacker.setCardsForArmies(gameModel.getCurrPlayer().getCardsForArmies() + 5);
			attacker.setNoOfArmiesOwned(gameModel.getCurrPlayer().getNoOfArmiesOwned() + gameModel.getCurrPlayer().getCardsForArmies());
			Card initialCard=attacker.getCardsOwned().get(0);
			int count=0;
			for(Card card: attacker.getCardsOwned())
			{
				if(initialCard==card){
					attacker.getCardsOwned().remove(card);
					attacker.setNoOfCardsOwned(gameModel.getCurrPlayer().getNoOfCardsOwned()-1);
					count++;
				}
				if(count==3){
					break;
				}
			}
		}
		fortificationPhase(gameModel);
	}
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		
	}

}
