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
	boolean boolAttackAllout;
	boolean boolAttack;
	
	
	public AttackController(AttackView AttackView){
		this.AttackView=AttackView;
		diceRoll=new Random();
		defender=null;
		attacker=null;
		countryAttacking=null;
		countryDefending=null;
		noOfAttackingArmies=0;
		noOfDefendingArmies=0;
		attackerDiceValues=new ArrayList<Integer>();
		defenderDiceValues=new ArrayList<Integer>();
		this.players=AttackView.getPlayer();
	}
	
	public boolean normalAttack(String attackingCountryName,String defendingCountryName,int noOfDicesSelected){
		
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName().equals(attackingCountryName)){
					countryAttacking=c;
					attacker=p;
					break;
				}
			}
		}
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName().equals(defendingCountryName)){
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
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent()-1;
		if(checkWarWon()){
			return true;
		}
	
		if(noOfAttackingArmies<=0){
			return false;
		}
		if(noOfDicesSelected==1){
			rollDice(attackerDiceValues);
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
	
	public boolean allOutAttack(String attackingCountryName,String defendingCountryName){
		
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName().equals(attackingCountryName)){
					countryAttacking=c;
					attacker=p;
					break;
				}
			}
		}
		for(Player p:players){
			for(Country c:p.getCountriesOwned()){
				if(c.getCountryName().equals(defendingCountryName)){
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
		noOfAttackingArmies = countryAttacking.getNoOfArmiesPresent()-1;
		boolean won=false;
		while(noOfAttackingArmies>0 && noOfDefendingArmies>0){
			if(noOfAttackingArmies==1)
				won=normalAttack(attackingCountryName, defendingCountryName,1);
			else if(noOfAttackingArmies==2)
				won=normalAttack(attackingCountryName, defendingCountryName,2);
			else
				won=normalAttack(attackingCountryName, defendingCountryName,3);
			//noOfAttackingArmies = countryAttacking.getNoOfArmiesPresent()-1;
			//noOfDefendingArmies = countryDefending.getNoOfArmiesPresent();
		}
		return won;
	}
	
	
	private void battleWon(){
		countryDefending.removeNoOfArmiesCountry();
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
	}
	
	private void battleLost(){
		countryAttacking.removeNoOfArmiesCountry();
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent()-1;
	}
	
	private boolean checkWarWon(){
		
		if(noOfDefendingArmies==0){
			attacker.addCountry(countryDefending);
			defender.removeCountry(countryDefending);
			attacker.getCardsOwned().add(Card.getNewCard());
			if(defender.getCountriesOwned().size()==0){
				attacker.getCardsOwned().addAll(defender.getCardsOwned());
				Player []newList=new Player[players.length-1];
				int k=0;
				for(Player p:players){
					if(p==defender){
						continue;
					}
					newList[k++]=p;
				}
				players=newList;
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
		System.out.println(diceValues);
		System.out.println();
	}
	
	private void rollDice(ArrayList <Integer> setDiceValues){
		setDiceValues.add(diceRoll.nextInt(6)+1);
	}
	
	private void initiateDice(){
		defenderDiceValues.clear();
		attackerDiceValues.clear();
	}
	private Country sourCountry, destCountry;

	
	
	
	public Country getDestCountry() {
		return destCountry;
	}
	
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
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
		else if(e.getSource() == AttackView.getAttack())
		{
			System.out.println("Attack Button Pressed...");
			//Attacking Country
			String sSourceCountry=(String) AttackView.getSourceCountry().getSelectedItem();
			//Defender Country
			String sDestinationCountry=(String) AttackView.getDestinationCountry().getSelectedItem();
//			int iNoOfArmiesOfAttacker=Integer.parseInt((String.valueOf(sourCountry.getNoOfArmiesPresent())));
			int DiceSelection=  Integer.parseInt((String)(AttackView.getNoOfDice().getSelectedItem()));
			System.out.println("Player Name : "+AttackView.getPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry+"\n"+ "No of Dices of Attacker : "+DiceSelection);
			
			boolAttack=normalAttack(sSourceCountry,sDestinationCountry,DiceSelection);
			if (boolAttack== true)
		{
			System.out.println("Normal Attack "+"terroitry won");
		}
		else
		{
			System.out.println("Normal Attack "+"terroitry is not won");
		}
			
		}
		else if(e.getSource() == AttackView.getAllOutAttackbtn())
		{
			System.out.println("All out attack button pressed");
			//Attacking Country
			String sSourceCountry=(String) AttackView.getSourceCountry().getSelectedItem();
			//Defender Country
			String sDestinationCountry=(String) AttackView.getDestinationCountry().getSelectedItem();
			System.out.println("Player Name : "+AttackView.getPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry);
			
			boolAttackAllout=allOutAttack(sSourceCountry,sDestinationCountry);
			if (boolAttackAllout== true)
			{
				System.out.println("All out Attack "+" terriotry  won");
			}
			else
			{
				System.out.println("All out Attack "+" terriotry  won");
			}
			
		}
		
	}
	
	



}


	