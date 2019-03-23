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
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
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
	private int attackerDiceCount;
	private Map map;
	private Country sourCountry, destCountry;
	
	
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
		this.map=AttackView.getMap();
		attackerDiceCount=0;
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
			AttackView.getAttackDice1().setText(String.valueOf(attackerDiceValues.get(0)));
			AttackView.getAttackDice2().setText("");
			AttackView.getAttackDice3().setText("");
			if(noOfDefendingArmies==1) {
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText("");
			}
			else if(noOfDefendingArmies>1) {
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText(String.valueOf(defenderDiceValues.get(1)));
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
			AttackView.getAttackDice1().setText(String.valueOf(attackerDiceValues.get(0)));
			AttackView.getAttackDice2().setText(String.valueOf(attackerDiceValues.get(1)));
			AttackView.getAttackDice3().setText("");
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText("");
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
					battleWon();
				}
				else {
					battleLost();
				}
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText(String.valueOf(defenderDiceValues.get(1)));
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
			AttackView.getAttackDice1().setText(String.valueOf(attackerDiceValues.get(0)));
			AttackView.getAttackDice2().setText(String.valueOf(attackerDiceValues.get(1)));
			AttackView.getAttackDice3().setText(String.valueOf(attackerDiceValues.get(2)));
			if(noOfDefendingArmies==1){
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText("");
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues))
					battleWon();
				else
					battleLost();
			}
			else if(noOfDefendingArmies>1){
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				AttackView.getDefendDice1().setText(String.valueOf(defenderDiceValues.get(0)));
				AttackView.getDefendDice2().setText(String.valueOf(defenderDiceValues.get(1)));
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
		//// Check again
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
		System.out.println("BattleWon");
		countryDefending.removeNoOfArmiesCountry();
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		
	}
	
	private void battleLost(){
		System.out.println("BattleLost");
		countryAttacking.removeNoOfArmiesCountry();
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent()-1; // ???
		
	}
	
	private boolean checkWarWon(){
		if(noOfDefendingArmies==0){
			System.out.println("WarWon");
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
			Continent continentName = map.searchContinent(countryDefending);
			for(Continent c : map.getContinents()) {
				if(defender.getContinentsOwned().contains(continentName)) {
					defender.getContinentsOwned().remove(continentName);
				}
			}
			
			for(Continent c:map.getContinents()){
				if(attacker.getCountriesOwned().containsAll(c.getCountriesPresent()) && !attacker.getContinentsOwned().contains(c)){
					attacker.getContinentsOwned().add(c);
					for(Player p :AttackView.getPlayer()) {
						if(p.getContinentsOwned().contains(c) && p!=attacker) {
							p.getContinentsOwned().remove(c);
						}
					}
				}
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
		attackerDiceCount=attackerDiceValues.size();
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

	
	private void moveArmies(int noOfArmiesToMove){
		countryAttacking.setNoOfArmiesPresent(countryAttacking.getNoOfArmiesPresent()-noOfArmiesToMove);
		countryDefending.setNoOfArmiesPresent(noOfArmiesToMove);
	}
	
	
	public Country getDestCountry() {
		return destCountry;
	}
	
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == AttackView.getSourceCountry()) {
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
		else if(e.getSource() == AttackView.getDestinationCountry()) {
			if (AttackView.getDestinationCountry().getItemCount() != 0) {
				String destinationSelected = (String) AttackView.getDestinationCountry().getSelectedItem();
				destCountry = AttackView.getMap().searchCountry(destinationSelected);
				AttackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
			}
		
		}
		else if(e.getSource() == AttackView.getAttack()){
			
			if(AttackView.getSourceCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select Source country");
			}
			else if(AttackView.getDestinationCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select target country");
			}
			else if(AttackView.getNoOfDice().getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Please select number of dices");
			}
			else {
				System.out.println("Attack Button Pressed...");

				//Attacking Country
				String sSourceCountry=(String) AttackView.getSourceCountry().getSelectedItem();
				//Defender Country
				String sDestinationCountry=(String) AttackView.getDestinationCountry().getSelectedItem();

				int DiceSelection=  Integer.parseInt((String)(AttackView.getNoOfDice().getSelectedItem()));
				System.out.println("Player Name : "+AttackView.getPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry+"\n"+ "No of Dices of Attacker : "+DiceSelection);
				
				boolAttack=normalAttack(sSourceCountry,sDestinationCountry,DiceSelection);
				if (boolAttack== true){
					System.out.println("Normal Attack "+"terroitry won");
					AttackView.getSourceCountry().removeAllItems();
					AttackView.getArmiesInSource().setText("");
					AttackView.getDestinationCountry().removeAllItems();
					AttackView.getArmiesInDestination().setText("");
					AttackView.getNoOfDice().removeAllItems();
					transferArmy();	
					AttackView.addCountryToBox(AttackView.getSourceCountry());
					if(AttackView.getGameModel().getCurrPlayer().getCountriesOwned().size()==AttackView.getGameModel().getMapDetails().listOfCountryNames().size()) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has won the game\n CONGRATULATIONS");
						AttackView.dispose();
						AttackView.getGameWindow().dispose();
					}
					if(AttackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has no country with armies more than one!!!");
						AttackView.dispose();
						int flag=0;
						AttackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(AttackView.getMap(), AttackView.getPlayer(), AttackView.getGameWindow(),flag);

					}
				}
				else{
					System.out.println("Normal Attack "+"terroitry is not won");
					AttackView.selectDices();
					AttackView.getNoOfDice().setSelectedIndex(-1);
					AttackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
					AttackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
					if(sourCountry.getNoOfArmiesPresent()<=1) {
						JOptionPane.showMessageDialog(null, "Source country contains only 1 army \n Player should select other country to attack!!!");
						AttackView.addCountryToBox(AttackView.getSourceCountry());
						AttackView.getDestinationCountry().removeAllItems();
						AttackView.getArmiesInDestination().setText("");
					}
					if(AttackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has no country with armies more than one!!!");
						AttackView.dispose();
						int flag = 0;
						AttackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(AttackView.getMap(), AttackView.getPlayer(), AttackView.getGameWindow(),flag);
					}
				}
				
			}		
		}
		else if(e.getSource() == AttackView.getAllOutAttackbtn()){
			if(AttackView.getSourceCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select Source country");
			}
			else if(AttackView.getDestinationCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select target country");
			}
			else if(AttackView.getNoOfDice().getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Please select number of dices");
			}
			
			else {
				System.out.println("All out attack button pressed");
				//Attacking Country
				String sSourceCountry=(String) AttackView.getSourceCountry().getSelectedItem();
				//Defender Country
				String sDestinationCountry=(String) AttackView.getDestinationCountry().getSelectedItem();
				System.out.println("Player Name : "+AttackView.getPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry);
				boolAttackAllout=allOutAttack(sSourceCountry,sDestinationCountry);

				if (boolAttackAllout== true){
					System.out.println("All out Attack "+" terriotry  won");
					AttackView.getSourceCountry().removeAllItems();
					AttackView.getArmiesInSource().setText("");
					AttackView.getDestinationCountry().removeAllItems();
					AttackView.getArmiesInDestination().setText("");
					AttackView.getNoOfDice().removeAllItems();
					transferArmy();	
					AttackView.addCountryToBox(AttackView.getSourceCountry());
					System.out.println("chl pya");
					if(AttackView.getGameModel().getCurrPlayer().getCountriesOwned().size()==AttackView.getGameModel().getMapDetails().listOfCountryNames().size()) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has won the game\n CONGRATULATIONS");
						AttackView.dispose();
						AttackView.getGameWindow().dispose();
					}
					if(AttackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has no country with armies more than one!!!");
						AttackView.dispose();
						int flag = 0;
						AttackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(AttackView.getMap(), AttackView.getPlayer(), AttackView.getGameWindow(),flag);

					}
				}
				else{
					System.out.println("All out Attack "+" terriotry not won");
					AttackView.selectDices();
					AttackView.getNoOfDice().setSelectedIndex(-1);
					AttackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
					AttackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
					if(sourCountry.getNoOfArmiesPresent()<=1) {
						//JOptionPane.showMessageDialog(null, "Source country contains only 1 army \n Player should select other country to attack!!!");
						AttackView.addCountryToBox(AttackView.getSourceCountry());
						AttackView.getDestinationCountry().removeAllItems();
						AttackView.getArmiesInDestination().setText("");
					}
					if(AttackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, AttackView.getPlayer()[AttackView.getCurrentPlayer()].getPlayerName()+" has no country with armies more than one!!!");
						AttackView.dispose();
						int flag = 0;
						AttackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(AttackView.getMap(), AttackView.getPlayer(), AttackView.getGameWindow(),flag);
						
					}
				}
			}
			

		}
		else if(e.getSource() == AttackView.getFinish()) {
			AttackView.dispose();
			int flag = 1;
			AttackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(AttackView.getMap(), AttackView.getPlayer(), AttackView.getGameWindow(),flag);
		}
		
	}
	
	public void transferArmy() {
		System.out.println("a gya");
		int attackArmy = countryAttacking.getNoOfArmiesPresent()-1;
		Integer[] options = new Integer[attackArmy];
		for (int i = 1; i <= attackArmy; i++) {
			int index = i - 1;
			options[index] = i;
		}
		int n = 0;
		try {
			n = (Integer) JOptionPane.showInputDialog(null, "Select no of armies to be transfered", "Move Armies",
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} catch(Exception ex){
			System.out.println("Exception encountered");
			n = 1;
		} finally {
			System.out.println("finally set up");
		}

		System.out.println("Armies transfer: " + n);
		for (int j = 0; j < n; j++) {
			countryAttacking.removeNoOfArmiesCountry();
			countryDefending.addNoOfArmiesCountry();
		}
	}
}


	