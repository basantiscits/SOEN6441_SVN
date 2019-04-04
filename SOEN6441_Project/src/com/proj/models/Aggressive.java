package com.proj.models;


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
		Country country = maxArmiesInCountry(gameModel.getCurrPlayer());
		
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
