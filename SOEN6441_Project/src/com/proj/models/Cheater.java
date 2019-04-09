 package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Class for Cheater player
 * @author Basant
 * @since 23 Mar 2019
 * @version 1.2
 */
public class Cheater implements BehaviorStrategies, Serializable {

	/**
	 * start up phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void startUpPhase(GameModelCreation gameModel) {
		System.out.println("Name in cheater startUp: "+gameModel.getCurrPlayer().getPlayerName());
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
		System.out.println("StartUp phase done for cheater");
	}

	/**
	 * reinforcement phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void reinforcementPhase(GameModelCreation gameModel) {
		gameModel.getCurrPlayer().setNoOfArmiesOwned(0);
		
		for(Country country : gameModel.getCurrPlayer().getCountriesOwned()) {
			int armies = country.getNoOfArmiesPresent()*2;
			country.setNoOfArmiesPresent(armies);
		}
		gameModel.setGameState(2);
		System.out.println("Reinforcement phase done for cheater");
		attackPhase(gameModel);
		
	}

	/**
	 * attack phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void attackPhase(GameModelCreation gameModel) {
		boolean playerRemoved=false;
		Player newList[] = null;
		Player[] players=gameModel.getPlayer();
		Player attacker=gameModel.getCurrPlayer();
		HashSet<Country> defendingCountries=new HashSet<Country>();
		for(Country c:attacker.getCountriesOwned()){
			for(String n:c.getListOfNeighbours()){
				Country neighbour=gameModel.getMapDetails().searchCountry(n);
				if(!attacker.getCountriesOwned().contains(neighbour)){
					defendingCountries.add(neighbour);
				}
			}
		}
		if(defendingCountries.size()>0){
			for (Country defendingCountry : defendingCountries) {
				Player defender=null;
				for(Player p:gameModel.getPlayer()){
					if(p.getCountriesOwned().contains(defendingCountry)){
						defender=p;
						break;
					}
				}
				attacker.addCountry(defendingCountry);
				defender.removeCountry(defendingCountry);
				int val1 = 0;
				int val2 = 0;
				for(int i=0; i<players.length; i++) {
					if(attacker==players[i]) {
						val1=i;
					}
					if(defender==players[i]) {
						val2=i;
					}
				}
				if (defender.getCountriesOwned().size() == 0) {
					attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned() + defender.getCardsOwned().size());
					attacker.getCardsOwned().addAll(defender.getCardsOwned());
					int i=0;
					newList=new Player[players.length-1];
					for(Player p:players){
						if(defender==p){
							continue;
						}
						newList[i++]=p;
					}
					players=newList;
					gameModel.setPlayer(players);
					
					if(val2<val1) {
						gameModel.setTurn(gameModel.getTurn()-1);
					}
				}
				Continent continentName = gameModel.getMapDetails().searchContinent(defendingCountry);
				if (defender.getContinentsOwned().contains(continentName)) {
					defender.getContinentsOwned().remove(continentName);
				}

				for (Continent cont : gameModel.getMapDetails().getContinents()) {
					if (attacker.getCountriesOwned().containsAll(cont.getCountriesPresent())
							&& !attacker.getContinentsOwned().contains(cont)) {
						attacker.getContinentsOwned().add(cont);
						for (Player p : gameModel.getPlayer()) {
							if (p.getContinentsOwned().contains(cont) && p != attacker) {
								p.getContinentsOwned().remove(cont);
							}
						}
					}
				}
			}
		}
		
		System.out.println(players.length);
		System.out.println(gameModel.getPlayer().length);
		attacker.getCardsOwned().add(Card.getNewCard());   
		attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned()+1);
		
		if(gameModel.getGameScreen()!=null) {
			int over = 0;
			for(Player p : gameModel.getPlayer()) {
				if(p.getPlayerType()==PlayerType.Human) {
					over = 1;
				}
			}
			if(over != 1) {
				JOptionPane.showMessageDialog(null,"All Human Lost!!! \n Game Over");
				gameModel.getGameScreen().dispose();
				System.exit(0);
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
	 * fortification phase
	 * @param gameModel Object of GameModelCreation class
	 */
	@Override
	public void fortificationPhase(GameModelCreation gameModel) {
		for(Country playerCountry : gameModel.getCurrPlayer().getCountriesOwned()) {
			for(String neighborCountry : playerCountry.getListOfNeighbours()) {
				Country c = gameModel.getMapDetails().searchCountry(neighborCountry);
				if(!gameModel.getCurrPlayer().getCountriesOwned().contains(c)) {
					int armies = playerCountry.getNoOfArmiesPresent()*2;
					playerCountry.setNoOfArmiesPresent(armies);
					break;
				}
			}
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
