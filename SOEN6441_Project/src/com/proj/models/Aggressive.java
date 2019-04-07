package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;

public class Aggressive implements BehaviorStrategies, Serializable {

	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		//Country country = gameModel.getCurrPlayer().getCountriesOwned().get(0);
		System.out.println("StartUp Turn1: "+gameModel.getTurn());
		Country country = maxArmiesInCountry(gameModel.getCurrPlayer());
		System.out.println("Name in Aggressive startUp: "+gameModel.getCurrPlayer().getPlayerName());
		if ( gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		System.out.println("StartUp phase done for aggressive");
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
		System.out.println("Reinforcement Turn1: "+gameModel.getTurn());
		// TODO Auto-generated method stub
		Country country = maxArmiesInCountry(gameModel.getCurrPlayer());
		System.out.println("OFREISH COUNTRY Reinforcement: "+country.getCountryName() );
		while(gameModel.getCurrPlayer().getNoOfArmiesOwned()>0) {
			country.addNoOfArmiesCountry();
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		gameModel.setGameState(2);
		System.out.println("Reinforcement phase done for aggressive");
		attackPhase(gameModel);
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("Attack Turn1: "+gameModel.getTurn());
		Player attacker=gameModel.getCurrPlayer();
		Country attackingCountry = maxArmiesInCountry(attacker);
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
				attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned()+1);
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
		
		
		System.out.println("Attack phase done for aggressive");
		gameModel.setGameState(3);
		fortificationPhase(gameModel);
		
	}

	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("Fortification Turn1: "+gameModel.getTurn());
		Country mainCountry = maxArmiesInCountry(gameModel.getCurrPlayer());
		System.out.println("Player name1: "+gameModel.getCurrPlayer().getPlayerName());
		//List<Country> neighbors = mainCountry.
		System.out.println("OFRESIH COUNTRY IN FORTIFICATION: "+mainCountry.getCountryName());
		
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
		//gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
	}

}
