package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.views.AttackView;
import com.proj.views.FortificationView;
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
	
	public boolean normalAttack(Player selectedPlayer,String attackingCountryName,String defendingCountryName,int noOfArmiesSelected){
		
		attacker=selectedPlayer;
		noOfAttackingArmies=noOfArmiesSelected;
		
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
		if(noOfDefendingArmies==0){
			warWon();
			return true;
		}
		for(Country c:attacker.getCountriesOwned()){
			if(attackingCountryName.equals(c.getCountryName())){
				countryAttacking=c;
			}
		}
		if(countryAttacking.getNoOfArmiesPresent()<=1){
			return false;
		}
		
		
		if(noOfArmiesSelected==1){
			
			rollDice(attackerDiceValues);
			
			int i=noOfDefendingArmies;
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				storeDiceValues();
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
			}
			if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
				battleWon();
				if(countryDefending.getNoOfArmiesPresent()<1){
					warWon();
				}
			}
			else{
				battleLost();
			}
			
			return true;
		}
		
		
		else if(noOfArmiesSelected==2){
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
					battleWon();
					if(countryDefending.getNoOfArmiesPresent()<1){
						warWon();
					}
				}
				else{
					battleLost();
				}
				return true;
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
					battleWon();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)){
						battleWon();
					}
					else{
						battleLost();
					}
				}
				else{
					battleLost();		
				}
				return true;
			}
		}
		else{
			
		}
		return true;
	}
	
	public boolean allOutAttack(Player attacker,String attackingCountryName,String defendingCountryName){
		
		return false;
		
	}
	
	private void battleWon(){
		countryDefending.removeNoOfArmiesCountry(); 
		initiateDice();
	}
	private void battleLost(){
		initiateDice();
	}
	
	private void warWon(){
		attacker.addCountry(countryDefending);
		defender.removeCountry(countryDefending);
		initiateDice();
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