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
		Random randomAttacks=new Random();
		Country attackingCountry = attacker.getCountriesOwned().get(randomCountry.nextInt(attacker.getCountriesOwned().size()-1));
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
			while(defendingCountries.size()>0 && noOfAttacks>0){
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
		
	}

}
