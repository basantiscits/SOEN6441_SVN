package com.proj.models;

import java.util.ArrayList;

import com.proj.controllers.AttackController;

public class Benevolent implements BehaviorStrategies {

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
