 package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Cheater implements BehaviorStrategies {

	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		if (gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
			for (Country country : gameModel.getCurrPlayer().getCountriesOwned()) {
				country.addNoOfArmiesCountry();
			}
			gameModel.getCurrPlayer().reduceArmyInPlayer();
		}
		
		
	}

	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("armies1 in reinforce: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		gameModel.getCurrPlayer().setNoOfArmiesOwned(0);
		
		for(Country country : gameModel.getCurrPlayer().getCountriesOwned()) {
			int armies = country.getNoOfArmiesPresent()*2;
			country.setNoOfArmiesPresent(armies);
			System.out.println("armies1 in reinforceinside: "+country.getCountryName()+" : "+country.getNoOfArmiesPresent());
		}
		System.out.println("armies2 in reinforce: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		gameModel.setGameState(2);
		System.out.println("Reinforcement phase done for cheater");
		attackPhase(gameModel);
		
	}

	@Override
	public void attackPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("armies1 in Attack: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		boolean playerRemoved=false;
		Player newList[] = null;
		ArrayList<Player> newPlayerList=new ArrayList<Player>();
		Player attacker=gameModel.getCurrPlayer();
		ArrayList<Country> defendingCountries=new ArrayList<Country>();
		int flag =0;
		Country countryToBeChecked = null;
		for(Country c:attacker.getCountriesOwned()){
			for(String n:c.getListOfNeighbours()){
				for(Continent continent:gameModel.getMapDetails().getContinents()){
					for(Country neighbour:continent.getCountriesPresent()){
						if(neighbour.getCountryName().equalsIgnoreCase(n)){
							countryToBeChecked=neighbour;
						}
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
			for(Country defendingCountry:defendingCountries){
				for(Player defender:gameModel.getPlayer()) {
					for(Country c:defender.getCountriesOwned()) {
						if(c==defendingCountry) {
							attacker.addCountry(defendingCountry);
							defender.removeCountry(defendingCountry);
							if(defender.getCountriesOwned().size()==0) {
								attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned() + defender.getCardsOwned().size());
								attacker.getCardsOwned().addAll(defender.getCardsOwned());
								int k=0;
								playerRemoved=true;
								for(Player p:gameModel.getPlayer()) {
									if(p==defender) {
										continue;
									}
									newPlayerList.add(p);
								}
							}
							Continent continentName = gameModel.getMapDetails().searchContinent(defendingCountry);
								if(defender.getContinentsOwned().contains(continentName)) {
									defender.getContinentsOwned().remove(continentName);
								}
							
							
							for(Continent cont:gameModel.getMapDetails().getContinents()) {
								if(attacker.getCountriesOwned().containsAll(cont.getCountriesPresent()) && !attacker.getContinentsOwned().contains(c)) {
									attacker.getContinentsOwned().add(cont);
									for(Player p :gameModel.getPlayer()) {
										if(p.getContinentsOwned().contains(c) && p!=attacker) {
											p.getContinentsOwned().remove(c);
										}
									}
								}
							}
							
							
							break;
						}
					}
				}
			}
		}
		
		
		attacker.getCardsOwned().add(Card.getNewCard());   
		attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned()+1);
		if(playerRemoved){
			newList=new Player[newPlayerList.size()];
			int i=0;
			for(Player p:newPlayerList){
				newList[i++]=p;
			}
			gameModel.setPlayer(newList);
		}
		
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
		System.out.println("armies2 in attack: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		fortificationPhase(gameModel);
	}

	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		// TODO Auto-generated method stub
		System.out.println("armies1 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			System.out.println("Armies before: "+c.getCountryName()+" : "+c.getNoOfArmiesPresent());
		}
		for(Country playerCountry : gameModel.getCurrPlayer().getCountriesOwned()) {
			for(String neighborCountry : playerCountry.getListOfNeighbours()) {
				Country c = gameModel.getMapDetails().searchCountry(neighborCountry);
				if(!gameModel.getCurrPlayer().getPlayerName().equals(c.getOwnedBy().getPlayerName())) {
					int armies = playerCountry.getNoOfArmiesPresent()*2;
					playerCountry.setNoOfArmiesPresent(armies);
					break;
				}
			}
		}
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			System.out.println("Armies after: "+c.getCountryName()+" : "+c.getNoOfArmiesPresent());
		}
		System.out.println("armies2 in fortify: "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
		gameModel.incrementTurn();
		gameModel.changePlayer();
		gameModel.setGameState(1);
		//gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
		
	}

}
