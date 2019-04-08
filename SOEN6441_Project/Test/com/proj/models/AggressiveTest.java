package com.proj.models;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import com.proj.controllers.AttackController;
import com.proj.controllers.GameController;
import com.proj.views.AttackView;
import com.proj.views.GameWindowScreen;

public class AggressiveTest {
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
		player[0] = new Player("Player1",PlayerType.Aggressive);
		player[1] = new Player("Player2",PlayerType.Human);
		player[2] = new Player("Player3",PlayerType.Human);
		player[0].setStrategy(new Aggressive());
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
		India.getListOfNeighbours().add("Bangladesh");
		Bangladesh.getListOfNeighbours().add("India");
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
		India.addNoOfArmiesCountry();
		India.addNoOfArmiesCountry();
		India.addNoOfArmiesCountry();
		India.addNoOfArmiesCountry();
		India.addNoOfArmiesCountry();
		Pakistan.addNoOfArmiesCountry();
		Bangladesh.addNoOfArmiesCountry();
		gameModel.setCurrPlayer(player[0]);
	}
	
	@Test
	public void startUpPhaseTest(){
		player[0].addArmyInPlayer();
		gameModel.getCurrPlayer().getStrategy().startUpPhase(gameModel);
		assertEquals(6,India.getNoOfArmiesPresent());
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		assertEquals(0,player[0].getNoOfArmiesOwned());
	}
	
	@After
	public void afterEachTestMethod() {
		
	}
	
	
	@Test
	public void reinforcementPhase(){
		gameModel.getCurrPlayer().addArmyInPlayer();
		gameModel.getCurrPlayer().getStrategy().reinforcementPhase(gameModel);
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		assertEquals(0,player[0].getNoOfArmiesOwned());
	}

	
	@Test
	public void attackPhaseTest(){
		gameModel.getCurrPlayer().getStrategy().attackPhase(gameModel);
		assertTrue(player[0].getCountriesOwned().contains(Pakistan));
		assertEquals(1,player[0].getNoOfCardsOwned());
	}
	
	

	@Test
	public void fortificationPhaseTest(){
		Bangladesh.addNoOfArmiesCountry();
		assertEquals(2,Bangladesh.getNoOfArmiesPresent());
		gameModel.getCurrPlayer().getStrategy().fortificationPhase(gameModel);
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		assertEquals("Player2",gameModel.getCurrPlayer().getPlayerName());
	}	
}
