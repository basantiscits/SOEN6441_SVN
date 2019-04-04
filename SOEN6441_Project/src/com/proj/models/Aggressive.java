package com.proj.models;

import java.util.ArrayList;

import com.proj.controllers.AttackController;

public class Aggressive implements BehaviorStrategies {

	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		Country country = gameModel.getCurrPlayer().getCountriesOwned().get(0);
		if ( gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		
	}
	
	private Country maxArmiesInCountry(Player player) {
		Country country = player.getCountriesOwned().get(0);
		int noOfArmies = country.getNoOfArmiesPresent();
		for(Country c : player.getCountriesOwned()) {
			if(c.getNoOfArmiesPresent() > noOfArmies) {
				country = c;
				noOfArmies = c.getNoOfArmiesPresent();
			}
		}
		return country;
		
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		Country country = maxArmiesInCountry(gameModel.getCurrPlayer());
		while(gameModel.getCurrPlayer().getNoOfArmiesOwned()>0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		gameModel.setGameState(2);
		attackPhase(gameModel);
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		Player attacker=gameModel.getCurrPlayer();
		Country attackingCountry = maxArmiesInCountry(gameModel.getCurrPlayer());
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
			while(defendingCountries.size()>0){
				if(!attack.allOutAttack(attackingCountry.getCountryName(), defendingCountries.get(0).getCountryName())){
					break;
				}
				else{
					attack.numberOfArmiesTransfered(attack.attackerDiceCount, attackingCountry, defendingCountries.get(0));
					defendingCountries.remove(0);	
				}
			}
			if(attack.countryWon){
				attacker.getCardsOwned().add(Card.getNewCard());   
				attacker.setNoOfCardsOwned(gameModel.getCurrPlayer().getNoOfCardsOwned()+1);
			}
		}		
		
		if(gameModel.getPlayer().length==1){
			System.out.println("Game Won by "+attacker.getPlayerName()+" "+attacker.getStrategy().getClass());
			return;
		}
		if(attacker.getNoOfCardsOwned()==5){
			//Cards to implemented
			attacker.setCardsForArmies(gameModel.getCurrPlayer().getCardsForArmies() + 5);
			attacker.setNoOfArmiesOwned(gameModel.getCurrPlayer().getNoOfArmiesOwned() + gameModel.getCurrPlayer().getCardsForArmies());
			Card initialCard=attacker.getCardsOwned().get(0);
			for(Card card: attacker.getCardsOwned())
			{
				if(initialCard==card){
					attacker.getCardsOwned().remove(card);
					attacker.setNoOfCardsOwned(gameModel.getCurrPlayer().getNoOfCardsOwned()-1);
				}
			}
		}
		fortificationPhase(gameModel);
	}

	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		
		Country mainCountry = maxArmiesInCountry(gameModel.getCurrPlayer());
		//List<Country> neighbors = mainCountry.
		
		
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			if (mainCountry.getListOfNeighbours().contains(c.getCountryName())) {
				while(c.getNoOfArmiesPresent()>1) {
					mainCountry.addNoOfArmiesCountry();
					c.removeNoOfArmiesCountry();
				}
			}
		}
		
		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
	}

}
