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
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.views.AttackView;
import com.proj.views.GameWindowScreen;

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

	@Before
	public void before() {
		player = new Player[3];
		player[0] = new Player("Player1");
		player[1] = new Player("Player2");
		player[2] = new Player("Player3");
		gameMap = new Map();
		GameModelCreation gameModel = new GameModelCreation(gameMap,player);
		screen = new GameWindowScreen(gameMap, player, gameModel);
		controller = new GameController(screen, gameMap, player);
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
	
	@Test
	public void method1Test() {
		
		attackView = new AttackView(gameMap, player, currentPlayer, screen);
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
	

	

}