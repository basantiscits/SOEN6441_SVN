package com.proj.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Human;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.models.PlayerType;
import com.proj.views.AttackView;
import com.proj.views.GameWindowScreen;

/**
 * GameControllerTest class
 * @author Ofreish
 * @since 16/03/2019
 * @version 1.1
 */
public class AttackControllerTest {
	public Player defender;
	public Player attacker;
	public int noOfAttackingArmies;
	public int noOfDefendingArmies;
	public String diceValues;
	boolean boolAttackAllout;
	boolean boolAttack;
	private int attackerDiceCount;
	private AttackView attackView;
	private AttackController attackController;
	private Player[] player;
	private Map gameMap;
	private GameWindowScreen screen;
	private int currentPlayer;
	private GameController controller;
	private Continent Asia,Europe,Africa;
	private Country India,Pakistan,Nepal,Bangladesh,Spain,France,Italy,England,SouthAfrica,Nigeria;
	private ArrayList<Continent> continentList;
	GameModelCreation gameModel;

	/**
	 * This method initializes all the required data to complete the test
	 */
	@Before
	public void before() {
		player = new Player[3];
		player[0] = new Player("Player1",PlayerType.Human);
		player[1] = new Player("Player2",PlayerType.Human);
		player[2] = new Player("Player3",PlayerType.Human);
		player[0].setStrategy(new Human());
		player[1].setStrategy(new Human());
		player[2].setStrategy(new Human());
		gameMap = new Map();
		gameModel = new GameModelCreation(gameMap,player);
		screen = new GameWindowScreen(gameModel);
		controller = new GameController(screen, gameModel);
		Asia = new Continent();
		Asia.setContinentName("Asia");
		India = new Country("India",Asia);
		Pakistan = new Country("Pakistan",Asia);
		Nepal = new Country("Nepal",Asia);
		Bangladesh = new Country("Bangladesh",Asia);
		
		Asia.addCountry(India);
		Asia.addCountry(Pakistan);
		Asia.addCountry(Nepal);
		Asia.addCountry(Bangladesh);
		
		Europe= new Continent();
		Europe.setContinentName("Europe");
		Spain = new Country("Spain",Europe);
		France = new Country("France",Europe);
		Italy = new Country("Italy",Europe);
		England = new Country("England",Europe);
		
		Europe.addCountry(Spain);
		Europe.addCountry(France);
		Europe.addCountry(Italy);
		Europe.addCountry(England);
		
		Africa = new Continent();
		Africa.setContinentName("Africa");
		SouthAfrica = new Country("South Africa",Africa);
		Nigeria = new Country("Nigeria",Africa);
		
		Africa.addCountry(SouthAfrica);
		Africa.addCountry(Nigeria);
		
		continentList = new ArrayList<Continent>();
		continentList.add(Asia);
		continentList.add(Europe);
		continentList.add(Africa);
		gameMap.setContinents(continentList);
		
		Asia.setControlValue(4);
		Europe.setControlValue(4);
		Africa.setControlValue(2);
		
		India.getListOfNeighbours().add("Pakistan");
		
		player[0].addCountry(India);
		player[1].addCountry(Pakistan);
		player[2].addCountry(Nepal);
		player[0].addCountry(Bangladesh);
		player[1].addCountry(Spain);
		player[2].addCountry(France);
		player[0].addCountry(Italy);
		player[1].addCountry(England);
		player[2].addCountry(SouthAfrica);
		player[2].addCountry(Nigeria);
		player[2].getContinentsOwned().add(Africa);
				
		
	}
	
	@After
	public void afterEachTestMethod() {

	}
	
	/**
	 * This test method checks battle won or lost depending upon the dice values
	 */
	@Test
	public void checkDiceValuesTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.countryAttacking = India;
		attackController.countryDefending = Pakistan;
		
		attackController.countryAttacking.setNoOfArmiesPresent(5);
		attackController.countryDefending.setNoOfArmiesPresent(1);
		int numberOfAttackingArmies=attackController.countryAttacking.getNoOfArmiesPresent();
		int numberOfDefendingArmies=attackController.countryDefending.getNoOfArmiesPresent();
		
		ArrayList<Integer> attackerDiceValues=new ArrayList<>();
		ArrayList<Integer> defenderDiceValues=new ArrayList<>();
		
		attackerDiceValues.add(6);
		attackerDiceValues.add(5);
		attackerDiceValues.add(2);
		defenderDiceValues.add(3);

		if(attackController.checkDiceValues(attackerDiceValues,defenderDiceValues)) {
			attackController.battleWon();
		}
		else {
			attackController.battleLost();
		}
		assertEquals(0,attackController.countryDefending.getNoOfArmiesPresent());
	}
	
	/**
	 * This test method checks the number of armies transferred
	 */
	@Test
	public void numberOfArmiesTransferedTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.countryAttacking = India;
		attackController.countryDefending = Pakistan;
		
		attackController.countryAttacking.setNoOfArmiesPresent(5);
		attackController.countryDefending.setNoOfArmiesPresent(1);
		int numberOfAttackingArmies=attackController.countryAttacking.getNoOfArmiesPresent();
		int numberOfDefendingArmies=attackController.countryDefending.getNoOfArmiesPresent();
		
		attackController.numberOfArmiesTransfered(2,India,Pakistan);
		assertEquals(3,attackController.countryAttacking.getNoOfArmiesPresent());
		assertEquals(3,attackController.countryDefending.getNoOfArmiesPresent());
	}
	
	/**
	 * This test method checks the war won method
	 */
	@Test
	public void warWonTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.countryAttacking = India;
		attackController.countryDefending = Pakistan;
		
		attackController.countryAttacking.setNoOfArmiesPresent(5);
		attackController.countryDefending.setNoOfArmiesPresent(1);
		int numberOfAttackingArmies=attackController.countryAttacking.getNoOfArmiesPresent();
		int numberOfDefendingArmies=attackController.countryDefending.getNoOfArmiesPresent();
		
		ArrayList<Integer> attackerDiceValues=new ArrayList<>();
		ArrayList<Integer> defenderDiceValues=new ArrayList<>();
		
		
		attackController.attacker = player[0];
		attackController.defender = player[1];
		attackerDiceValues.add(6);
		attackerDiceValues.add(5);
		attackerDiceValues.add(2);
		defenderDiceValues.add(3);
		attackController.battleWon();
		
		boolean check=attackController.checkWarWon();
		System.out.println(check);
		assertEquals(true,check);
	}
	
	/**
	 * This test method checks if the attacker is valid
	 */
	@Test
	public void attackerValidTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.attacker=player[0];
		attackController.defender=player[1];
		India.setNoOfArmiesPresent(3);
		Bangladesh.setNoOfArmiesPresent(2);
		Italy.setNoOfArmiesPresent(1);
		boolean status=attackController.validAttacker(player[0]);
		
		assertEquals(true,status);

	}
	
	/**
	 * This test method checks if the defender is valid
	 */
	@Test
	public void defenderValidTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.attacker=player[0];
		attackController.defender=player[1];
		India.setNoOfArmiesPresent(3);
		Bangladesh.setNoOfArmiesPresent(2);
		Italy.setNoOfArmiesPresent(1);
		ArrayList<Country> cList = attackView.targetCountries(India, gameMap);
		
		assertEquals(Pakistan,cList.get(0));

	}
	
	/**
	 * This test method checks if one player has all the countries of map
	 */
	@Test
	public void endGameTest() {
		attackView = new AttackView(gameModel);
		attackController = new AttackController(attackView);
		attackController.attacker=player[2];
		attackController.defender=player[1];

		player[2].addCountry(Bangladesh);
		player[2].addCountry(India);
		player[2].addCountry(Pakistan);
		player[2].addCountry(Spain);
		player[2].addCountry(Italy);
		player[2].addCountry(England);

		boolean status = attackView.endGame(player[2], gameMap);
		
		assertEquals(true,status);

	}
	
}