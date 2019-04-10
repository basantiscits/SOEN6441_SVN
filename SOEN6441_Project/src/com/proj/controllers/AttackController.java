package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;


import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.models.PlayerType;
import com.proj.views.AttackView;
import com.proj.models.Card;

/**
 * Attack Controller Class
 * @author Basant
 * @since 9 Mar 2019
 * @version 1.1
 */
public class AttackController implements ActionListener, Serializable{
	private AttackView attackView;
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
	boolean boolAttackAllOut;
	boolean boolAttack;
	public int attackerDiceCount;
	private Map map;
	private Country sourCountry, destCountry;
	public boolean countryWon;
	private GameModelCreation gameModel;
	private static final long serialVersionUID = 45443434343L;
	/** 
	 * attackView class constructor
	 * @param attackView attack view object
	 */
	public AttackController(AttackView attackView) {
		gameModel = attackView.getGameModel();
		this.attackView=attackView;
		diceRoll=new Random();
		defender=null;
		attacker=null;
		countryAttacking=null;
		countryDefending=null;
		noOfAttackingArmies=0;
		noOfDefendingArmies=0;
		countryWon=false;
		attackerDiceValues=new ArrayList<Integer>();
		defenderDiceValues=new ArrayList<Integer>();
		this.players=attackView.getGameModel().getPlayer();
		this.map=attackView.getGameModel().getMapDetails();
		attackerDiceCount=0;
	}
	
	public AttackController(GameModelCreation gameModel){
		diceRoll=new Random();
		defender=null;
		attacker=null;
		countryAttacking=null;
		countryDefending=null;
		noOfAttackingArmies=0;
		noOfDefendingArmies=0;
		countryWon=false;
		attackerDiceValues=new ArrayList<Integer>();
		defenderDiceValues=new ArrayList<Integer>();
		this.gameModel = gameModel;
		this.players=gameModel.getPlayer();
		this.map=gameModel.getMapDetails();
		attackerDiceCount=0;
	}
	
	/**
	 * Normal Attack
	 * @param attackingCountryName name of attacking country
	 * @param defendingCountryName name of defending country
	 * @param noOfAttackingDicesSelected number of attacking dices selected
	 * @param noOfDefendingDicesSelected number of defending dices selected
	 * @return true if war won else false
	 * 
	 */
	public boolean normalAttack(String attackingCountryName,String defendingCountryName,int noOfAttackingDicesSelected,int noOfDefendingDicesSelected) {	
		for(Player p:players) {
			for(Country c:p.getCountriesOwned()) {
				if(c.getCountryName().equals(attackingCountryName)) {
					countryAttacking=c;
					attacker=p;
					break;
				}
			}
		}
		for(Player p:players) {
			for(Country c:p.getCountriesOwned()) {
				if(c.getCountryName().equals(defendingCountryName)) {
					countryDefending=c;
					defender=p;
					break;
				}
			}
		}
		
		if(attacker==defender) {
			return false;
		}
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent()-1;
		if(checkWarWon()) {
			return true;
		}
	
		if(noOfAttackingArmies<=0 || noOfDefendingArmies<=0) {
			return false;
		}
		if (noOfAttackingDicesSelected==3) {
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			if(noOfDefendingDicesSelected==2) {
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
					battleWon();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
						battleWon();
					}
					else {
						battleLost();
					}
				}
				else {
					battleLost();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
						battleWon();
					}
					else {
						battleLost();
					}
				}
			}	
			
			else {
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
					battleWon();
				}
				else {
					battleLost();
				}
			}
		}
		else if(noOfAttackingDicesSelected==2) {
			rollDice(attackerDiceValues);
			rollDice(attackerDiceValues);
			if(noOfDefendingDicesSelected==2) {
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
					battleWon();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
						battleWon();
					}
					else {
						battleLost();
					}
				}
				else {
					battleLost();
					attackerDiceValues.remove(Collections.max(attackerDiceValues));
					defenderDiceValues.remove(Collections.max(defenderDiceValues));
					if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
						battleWon();
					}
					else {
						battleLost();
					}
				}
			}
			else {
				rollDice(defenderDiceValues);
				storeDiceValues();
				if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
					battleWon();
				}
				else {
					battleLost();
				}
			}
		}
		else if(noOfAttackingDicesSelected==1) {
			rollDice(attackerDiceValues);
			
			if(noOfDefendingDicesSelected ==2) {
				rollDice(defenderDiceValues);
				rollDice(defenderDiceValues);
			}
			else {
				rollDice(defenderDiceValues);
			}
			if(Collections.max(attackerDiceValues) > Collections.max(defenderDiceValues)) {
				battleWon();
			}
			else {
				battleLost();
			}
			storeDiceValues();
		}
		initiateDice();
		return checkWarWon();
	}
	
	/** 
	 * All Out Attack
	 * @param attackingCountryName name of attacking country
	 * @param defendingCountryName name of defending country
	 * @return true if battle won by attacker else false
	 */
	public boolean allOutAttack(String attackingCountryName,String defendingCountryName) {
		for(Player p:players) {
			for(Country c:p.getCountriesOwned()) {
				if(c.getCountryName().equals(attackingCountryName)) {
					countryAttacking=c;
					attacker=p;
					break;
				}
			}
		}
		for(Player p:players) {
			for(Country c:p.getCountriesOwned()) {
				if(c.getCountryName().equals(defendingCountryName)) {
					countryDefending=c;
					defender=p;
					break;
				}
			}
		}
		if(attacker==defender) {
			return false;
		}
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		noOfAttackingArmies = countryAttacking.getNoOfArmiesPresent()-1;
		boolean won=false;
		while(noOfAttackingArmies>0 && noOfDefendingArmies>0) {
			if(noOfAttackingArmies==1 && noOfDefendingArmies==1)
				won=normalAttack(attackingCountryName, defendingCountryName,1,1);
			else if(noOfAttackingArmies==1 && noOfDefendingArmies>1)
				won=normalAttack(attackingCountryName, defendingCountryName,1,2);
			else if(noOfAttackingArmies==2 && noOfDefendingArmies==1)
				won=normalAttack(attackingCountryName, defendingCountryName,2,1);
			else if(noOfAttackingArmies==2 && noOfDefendingArmies>1)
				won=normalAttack(attackingCountryName, defendingCountryName,2,2);
			else if(noOfAttackingArmies>2 && noOfDefendingArmies==1)
				won=normalAttack(attackingCountryName, defendingCountryName,3,1);
			else if(noOfAttackingArmies>2 && noOfDefendingArmies>1)
				won=normalAttack(attackingCountryName, defendingCountryName,3,2);
		}
		return won;
	}
	
	/** 
	 * Battle won so removes armies from defending country
	 * 
	 */
	public void battleWon(){
		System.out.println("BattleWon");
		countryDefending.removeNoOfArmiesCountry();
		noOfDefendingArmies=countryDefending.getNoOfArmiesPresent();
		
	}
	
	/** 
	 * Battle lost so removes armies from attacking country
	 * 
	 */
	public void battleLost() {
		System.out.println("BattleLost");
		countryAttacking.removeNoOfArmiesCountry();
		noOfAttackingArmies=countryAttacking.getNoOfArmiesPresent()-1;
		
	}
	
	/**
	 * Check war won
	 * @return true if war won else false
	 * 
	 */
	boolean checkWarWon() {
		if(noOfDefendingArmies==0) {
			System.out.println("WarWon");
			attacker.addCountry(countryDefending);
			defender.removeCountry(countryDefending);
			countryWon=true;
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
			if(defender.getCountriesOwned().size()==0) {
				attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned() + defender.getCardsOwned().size());
				attacker.getCardsOwned().addAll(defender.getCardsOwned());
				Player []newList=new Player[players.length-1];
				int k=0;
				for(Player p:players) {
					if(p==defender) {
						continue;
					}
					newList[k++]=p;
				}
				players=newList;
				if(newList!=null) {
					for(Player p:newList) {
						System.out.println("Player in new List: "+p);
					}
					for(Player p:players) {
						System.out.println("Player in players: "+p);
					}
				}
				if(players!=null) {
					gameModel.setPlayer(players);
				}
				else {
					System.out.println("Null");
				}
				if(val2<val1) {
					gameModel.setTurn(gameModel.getTurn()-1);
				}
			}
			Continent continentName = map.searchContinent(countryDefending);
			if(defender.getContinentsOwned().contains(continentName)) {
				defender.getContinentsOwned().remove(continentName);
			}
			
			for(Continent c : map.getContinents()) {
				if(attacker.getCountriesOwned().containsAll(c.getCountriesPresent()) && !attacker.getContinentsOwned().contains(c)) {
					attacker.getContinentsOwned().add(c);
					for(Player p : players) {
						if(p.getContinentsOwned().contains(c) && p!=attacker) {
							p.getContinentsOwned().remove(c);
						}
					}
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * stores attacker and defender dice values in a list
	 */
	private void storeDiceValues() {
		diceValues = attackerDiceValues.toString();
		diceValues = diceValues+"   "+defenderDiceValues.toString();
		attackerDiceCount = attackerDiceValues.size();
		System.out.println("Attacker: "+attackerDiceValues.toString()+"\nDefender: "+defenderDiceValues);
		System.out.println();
	}
	
	/**
	 * roll dice
	 * @param setDiceValues List of dice values
	 */
	private void rollDice(ArrayList <Integer> setDiceValues) {
		setDiceValues.add(diceRoll.nextInt(6)+1);
	}
	
	/**
	 * initiate dice values to null
	 */
	private void initiateDice() {
		defenderDiceValues.clear();
		attackerDiceValues.clear();
	}

	/**
	 * Moves armies from attacking to defending country that was won
	 * @param noOfArmiesToMove
	 */
	private void moveArmies(int noOfArmiesToMove) {
		countryAttacking.setNoOfArmiesPresent(countryAttacking.getNoOfArmiesPresent()-noOfArmiesToMove);
		countryDefending.setNoOfArmiesPresent(noOfArmiesToMove);
	}
	
	/**
	 * getter for destination country
	 * @return destCountry name of destination country
	 */
	public Country getDestCountry() {
		return destCountry;
	}
	
	/**
	 * setter for destination country
	 * @param destCountry name of destination country
	 */
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}
	
	
	/**
	 * Action performed when attack is initiated
	 * @param e Object of type ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == attackView.getSourceCountry()) {
			if (attackView.getSourceCountry().getItemCount() != 0) {
				attackView.getArmiesInDestination().setText("");
				attackView.getNoOfDice().removeAllItems();
				attackView.getNoOfDiceDefender().removeAllItems();
				destCountry = null;
				String countryName = (String) attackView.getSourceCountry().getSelectedItem();
				sourCountry = attackView.getGameModel().getMapDetails().searchCountry(countryName);
				attackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				boolean check = attackView.addDestCountries(sourCountry);
				if(check) {
					attackView.getDestinationCountry().setSelectedIndex(0);

					String destinationSelected = (String) attackView.getDestinationCountry().getSelectedItem();
					destCountry = attackView.getGameModel().getMapDetails().searchCountry(destinationSelected);
					System.out.println("AGGGGG :"+destCountry.getCountryName()+" String: "+destinationSelected);
					attackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
					attackView.selectDices();
					attackView.selectDefenderDice();
				}
				else {
					attackView.selectDices();
				}
			}
		}
		else if(e.getSource() == attackView.getDestinationCountry()) {
			if (attackView.getDestinationCountry().getItemCount() != 0) {
				attackView.getNoOfDiceDefender().removeAllItems();
				String destinationSelected = (String) attackView.getDestinationCountry().getSelectedItem();
				destCountry = attackView.getGameModel().getMapDetails().searchCountry(destinationSelected);
				attackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
				attackView.selectDefenderDice();
			}
		
		}
		else if(e.getSource() == attackView.getAttack()) {
			
			if(attackView.getSourceCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select Source country");
			}
			else if(attackView.getDestinationCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select target country");
			}
			else if(attackView.getNoOfDice().getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Please select number of dices for attacker");
			}
			else if(attackView.getNoOfDiceDefender().getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(null, "Please select number of dices for defender");
			}
			else {
				System.out.println("Attack Button Pressed...");

				String sSourceCountry=(String) attackView.getSourceCountry().getSelectedItem();
				String sDestinationCountry=(String) attackView.getDestinationCountry().getSelectedItem();

				int attackerDiceSelection=  Integer.parseInt((String)(attackView.getNoOfDice().getSelectedItem()));
				int defenderDiceSelection = Integer.parseInt((String)(attackView.getNoOfDiceDefender().getSelectedItem()));
				System.out.println("Player Name : "+attackView.getGameModel().getCurrPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry+"\n"+ "No of Dices of Attacker : "+attackerDiceSelection);
				
				boolAttack=normalAttack(sSourceCountry,sDestinationCountry,attackerDiceSelection, defenderDiceSelection);
				attackView.getAttackDice1().setText(diceValues);
				if (boolAttack== true) {
					System.out.println("Normal Attack "+"terroitry won");
					attackView.getSourceCountry().removeAllItems();
					attackView.getArmiesInSource().setText("");
					attackView.getDestinationCountry().removeAllItems();
					attackView.getArmiesInDestination().setText("");
					attackView.getNoOfDice().removeAllItems();
					attackView.getNoOfDiceDefender().removeAllItems();
					transferArmy(attackerDiceSelection);	
					attackView.addCountryToBox(attackView.getSourceCountry());
					int over = 0;
					for(Player p : attackView.getGameModel().getPlayer()) {
						if(p.getPlayerType()==PlayerType.Human) {
							over = 1;
						}
					}
					if(over != 1) {
						JOptionPane.showMessageDialog(null,"All Human Lost!!! \n Game Over");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
						System.exit(0);
					}
					if(attackView.getGameModel().getCurrPlayer().getCountriesOwned().size()==attackView.getGameModel().getMapDetails().listOfCountryNames().size()) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getCurrPlayer().getPlayerName()+" has won the game\n CONGRATULATIONS");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
						return;
					}

					if(attackView.getGameModel().getPlayer()[1].getPlayerName().equals("Neutral")) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getPlayer()[0].getPlayerName()+" has won the game\n CONGRATULATIONS");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
					}
					if(attackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getCurrPlayer().getPlayerName()+" has no country with armies more than one!!!");
						attackView.dispose();
						int flag=0;
						attackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(attackView.getGameModel(),flag);
					}
				}
				else {
					System.out.println("Normal Attack "+"terroitry is not won");
					attackView.selectDices();
					attackView.selectDefenderDice();
					attackView.getNoOfDice().setSelectedIndex(-1);
					attackView.getNoOfDiceDefender().setSelectedIndex(-1);
					attackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
					attackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
					if(sourCountry.getNoOfArmiesPresent()<=1) {
						JOptionPane.showMessageDialog(null, "Source country contains only 1 army \n Player should select other country to attack!!!");
						attackView.addCountryToBox(attackView.getSourceCountry());
						attackView.getDestinationCountry().removeAllItems();
						attackView.getArmiesInDestination().setText("");
					}
					if(attackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null,attackView.getGameModel().getCurrPlayer().getPlayerName()+" has no country with armies more than one!!!");
						attackView.dispose();
						int flag = 0;
						attackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(attackView.getGameModel(),flag);
					}
				}
			}		
		}
		else if(e.getSource() == attackView.getallOutAttackBtn()) {
			if(attackView.getSourceCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select Source country");
			}
			else if(attackView.getDestinationCountry().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Please select target country");
			}

			else {
				System.out.println("All out attack button pressed");
				String sSourceCountry=(String) attackView.getSourceCountry().getSelectedItem();
				String sDestinationCountry=(String) attackView.getDestinationCountry().getSelectedItem();
				System.out.println("Player Name : "+attackView.getGameModel().getCurrPlayer()+"\n"+ "Source Country :"+ sSourceCountry+"\n"+ "Defender Country :" +sDestinationCountry);
				boolAttackAllOut=allOutAttack(sSourceCountry,sDestinationCountry);
				attackView.getAttackDice1().setText(diceValues);
				if (boolAttackAllOut== true) {
					System.out.println("All out Attack "+" terriotry  won");
					attackView.getSourceCountry().removeAllItems();
					attackView.getArmiesInSource().setText("");
					attackView.getDestinationCountry().removeAllItems();
					attackView.getArmiesInDestination().setText("");
					attackView.getNoOfDice().removeAllItems();
					attackView.getNoOfDiceDefender().removeAllItems();
					transferArmy(attackerDiceCount);	
					attackView.addCountryToBox(attackView.getSourceCountry());
					System.out.println("chl pya");
					int over = 0;
					for(Player p : attackView.getGameModel().getPlayer()) {
						if(p.getPlayerType()==PlayerType.Human) {
							over = 1;
						}
					}
					if(over != 1) {
						JOptionPane.showMessageDialog(null,"All Human Lost!!! \n Game Over");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
						System.exit(0);
					}
					if(attackView.getGameModel().getCurrPlayer().getCountriesOwned().size()==attackView.getGameModel().getMapDetails().listOfCountryNames().size()) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getCurrPlayer().getPlayerName()+" has won the game\n CONGRATULATIONS");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
						return;
					}
					if(attackView.getGameModel().getPlayer()[1].getPlayerName().equals("Neutral")) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getPlayer()[0].getPlayerName()+" has won the game\n CONGRATULATIONS");
						attackView.dispose();
						attackView.getGameModel().getGameScreen().dispose();
					}
					if(attackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getCurrPlayer().getPlayerName()+" has no country with armies more than one!!!");
						attackView.dispose();
						int flag = 0;
						attackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(attackView.getGameModel(),flag);
					}
				}
				else {
					System.out.println("All out Attack "+" terriotry not won");
					attackView.selectDices();
					attackView.selectDefenderDice();
					attackView.getNoOfDice().setSelectedIndex(-1);
					attackView.getNoOfDiceDefender().setSelectedIndex(-1);
					attackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
					attackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
					if(sourCountry.getNoOfArmiesPresent()<=1) {
						attackView.addCountryToBox(attackView.getSourceCountry());
						attackView.getDestinationCountry().removeAllItems();
						attackView.getArmiesInDestination().setText("");
					}
					if(attackView.getSourceCountry().getSelectedItem()==null) {
						JOptionPane.showMessageDialog(null, attackView.getGameModel().getCurrPlayer().getPlayerName()+" has no country with armies more than one!!!");
						attackView.dispose();
						int flag = 0;
						attackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(attackView.getGameModel(),flag);	
					}
				}
			}
			
		}
		else if(e.getSource() == attackView.getFinish()) {
			
			if(countryWon){
			attacker.getCardsOwned().add(Card.getNewCard());   
			attacker.setNoOfCardsOwned(attacker.getNoOfCardsOwned()+1); 
			}
			countryWon=false;
			attackView.dispose();
			int flag = 1;
			attackView.getGameModel().getCurrPlayer().fortificationPhaseImplementation(attackView.getGameModel(),flag);
		}
	}
	
	/**
	 * Transfers armies from attacking to defending country won
	 * @param dice number of dice of attacker
	 */
	public void transferArmy(int dice) {
		int attackArmy = countryAttacking.getNoOfArmiesPresent()-1;
		Integer[] options = new Integer[attackArmy-(dice-1)];
		for (int i = dice,j = 1; i <= attackArmy; i++,j++) {
			int index = j - 1;
			options[index] = i;
		}
		int n = 0;
		try {
			n = (Integer) JOptionPane.showInputDialog(null, "Select no of armies to be transfered", "Move Armies",
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} catch(Exception ex) {
			System.out.println("Exception encountered");
			n = dice;
			System.out.println("n: "+n+"dice: "+dice);
		} finally {
			System.out.println("finally set up");
		}

		System.out.println("Armies transfer: " + n);
		for (int j = 0; j < n; j++) {
			countryAttacking.removeNoOfArmiesCountry();
			countryDefending.addNoOfArmiesCountry();
		}
	}
	
	/**
	 * Checks dice values
	 * @param attackerDice list of attacker dice values
	 * @param defenderDice list of defender dice values
	 * @return true if best of attacker dice values is greater than best of defender dice values else false
	 */
	public boolean checkDiceValues( ArrayList<Integer>  attackerDice, ArrayList<Integer> defenderDice) {
		if(Collections.max(attackerDice) > Collections.max(defenderDice)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Number of armies transferred
	 * @param n number of armies to be transferred
	 * @param attack attacking country
	 * @param defender defending country
	 */
	public void numberOfArmiesTransfered(int n, Country attack, Country defender) {
		for (int j = 0; j < n; j++) {
			attack.removeNoOfArmiesCountry();
			defender.addNoOfArmiesCountry();
		}
	}
	
	/**
	 * Checks if attacker is valid
	 * @param p Object of Player class
	 * @return true if attacker is valid else false
	 */
	public boolean validAttacker(Player p) {
		for(Country c:p.getCountriesOwned()) {
			if(c.getNoOfArmiesPresent()>1) {
				return true;
			}
		}
		return false;
	}
}


	