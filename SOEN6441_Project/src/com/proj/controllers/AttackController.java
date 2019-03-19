package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.views.AttackView;
import com.proj.views.FortificationView;
import com.proj.models.Card;
import com.proj.models.Country;
import com.proj.models.Player;


public class AttackController implements ActionListener{
	//private Country sourCountry, destCountry;
	private AttackView AttackView;
	public Random diceRoll;
	public ArrayList<Integer> attackerDiceValues;
	public ArrayList<Integer> defenderDiceValues;
	public Country countryAttacking;
	public Country countryDefending;
	public Player defender;
	public Player attacker;
	public int noOfAttackingArmies;
	public int noOfDefendingArmies;
	public Player[] players;
	public String diceValues;
	
	
	
	AttackController(Player[] players){
		diceRoll=new Random();
		defender=null;
		attacker=null;
		countryAttacking=null;
		countryDefending=null;
		noOfAttackingArmies=0;
		noOfDefendingArmies=0;
		this.players=players;
	}
	
	public boolean normalAttack(Player selectedPlayer,String attackingCountryName,String defendingCountryName,int noOfDicesSelected){
		
		attacker=selectedPlayer;
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName()==defendingCountryName){
					countryDefending=c;
					defender=p;
					break;
				}
			}
		}
		
		if(attacker==defender){
			return false;
		}
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		if(checkWarWon()){
			return true;
		}
		for(Country c:attacker.getCountriesOwned()){
			if(attackingCountryName.equals(c.getCountryName())){
				countryAttacking=c;
			}
		}
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent();
		if(countryAttacking.getNoOfArmiesPresent()<=1){
			return false;
		}
		if(noOfDicesSelected==1){
			rollDice(attackerDiceValues);
			int i=noOfDefendingArmies;
			if(noOfDefendingArmies==1)
				rollDice(defenderDiceValues);
			
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
			}
			if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
				battleWon();
			else
				battleLost();
			storeDiceValues();
		}
		
		
		else if(noOfDicesSelected==2){
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
					battleWon();
				else
					battleLost();
				
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
					battleWon();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
						battleWon();
					else
						battleLost();
				}
				else{
					battleLost();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
						battleWon();
					else
						battleLost();
				}
			}
		}
		else if (noOfDicesSelected==3){
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
					battleWon();
				else
					battleLost();
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
					battleWon();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
						battleWon();
					else
						battleLost();
				}
				else{
					battleLost();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
						battleWon();
					else
						battleLost();
				}
			}	
		}
		initiateDice();
		return checkWarWon();
	}
	
	public boolean allOutAttack(Player selectedPlayer,String attackingCountryName,String defendingCountryName){
		
		attacker=selectedPlayer;
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName()==defendingCountryName){
					countryDefending=c;
					defender=p;
					break;
				}
			}
		}
		for(Country c:attacker.getCountriesOwned()){
			if(attackingCountryName.equals(c.getCountryName())){
				countryAttacking=c;
			}
		}
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		if(noOfDefendingArmies>0){
			return true;
		}
		noOfAttackingArmies = countryAttacking.getNoOfArmiesPresent()-1;
		boolean won=false;
		
		do{
			if(noOfAttackingArmies==1)
				won=normalAttack(selectedPlayer, attackingCountryName, defendingCountryName,1);
			else if(noOfAttackingArmies==2)
				won=normalAttack(selectedPlayer, attackingCountryName, defendingCountryName,2);
			else
				won=normalAttack(selectedPlayer, attackingCountryName, defendingCountryName,3);
			noOfAttackingArmies = countryAttacking.getNoOfArmiesPresent()-1;
		}while(noOfAttackingArmies>0 && noOfDefendingArmies>0);
		
		return won;
	}
	
	
	private void battleWon(){
		countryDefending.removeNoOfArmiesCountry();
	}
	
	private void battleLost(){
		countryAttacking.removeNoOfArmiesCountry();
	}
	
	private boolean checkWarWon(){
		
		if(noOfDefendingArmies==0){
			attacker.addCountry(countryDefending);
			defender.removeCountry(countryDefending);
			attacker.getCardsOwned().add(Card.getNewCard());
			if(defender.getCountriesOwned().size()==0){
				attacker.getCardsOwned().addAll(defender.getCardsOwned());
	//removeplayer			for(Player p:players){
	//				if(p==defender){
	//					
	//				}
	//			}
		}
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private void storeDiceValues(){
		diceValues=attackerDiceValues.toString();
		diceValues=diceValues+"\n"+defenderDiceValues.toString();
	}
	
	private void rollDice(ArrayList <Integer> setDiceValues){
		setDiceValues.add(diceRoll.nextInt(6)+1);
	}
	
	private void initiateDice(){
		defenderDiceValues.clear();
		attackerDiceValues.clear();
	}
	private Country sourCountry, destCountry;

	public Country getSourCountry() {
		return sourCountry;
	}
	
	public void setSourCountry(Country sourCountry) {
		this.sourCountry = sourCountry;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


	public Country getDestCountry() {
		return destCountry;
	}
	
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}
	public AttackController(AttackView AttackView) {
		this.AttackView = AttackView;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == AttackView.getDestinationCountry()) {
			if (AttackView.getDestinationCountry().getItemCount() != 0) {
				String destinationSelected = (String) AttackView.getDestinationCountry().getSelectedItem();
				destCountry = AttackView.getMap().searchCountry(destinationSelected);
				AttackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
			}
		
		}
		else if (e.getSource() == AttackView.getSourceCountry()) {
			if (AttackView.getSourceCountry().getItemCount() != 0) {
				AttackView.getArmiesInDestination().setText("");
				AttackView.getNoOfDice().removeAllItems();
				destCountry = null;
				String countryName = (String) AttackView.getSourceCountry().getSelectedItem();
				sourCountry = AttackView.getMap().searchCountry(countryName);
				AttackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				AttackView.addDestCountries(sourCountry);
				AttackView.selectDices();
			}
		}
		
	}



}