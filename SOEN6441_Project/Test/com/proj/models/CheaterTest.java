package com.proj.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.proj.controllers.AttackController;
import com.proj.controllers.GameController;
import com.proj.views.AttackView;
import com.proj.views.GameWindowScreen;

/**
 * CheaterTest class
 * @author Aman
 * @since 28/03/2019
 * @version 1.2
 */
public class CheaterTest {
	
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
		player[0] = new Player("Player1",PlayerType.Cheater);
		player[1] = new Player("Player2",PlayerType.Human);
		player[2] = new Player("Player3",PlayerType.Human);
		player[0].setStrategy(new Cheater());
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
	
	/**
	 * start up phase test
	 */
	@Test
	public void startUpPhaseTest(){
		player[0].addArmyInPlayer();
		gameModel.getCurrPlayer().getStrategy().startUpPhase(gameModel);
		assertEquals(1,Italy.getNoOfArmiesPresent());
		assertEquals(5,India.getNoOfArmiesPresent());
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		assertEquals(0,player[0].getNoOfArmiesOwned());
	}
	
	@After
	public void afterEachTestMethod() {
		
	}
	
	/**
	 * reinforcement phase test
	 */
	@Test
	public void reinforcementPhaseTest(){
		gameModel.getCurrPlayer().getStrategy().reinforcementPhase(gameModel);
		assertEquals(2,Bangladesh.getNoOfArmiesPresent());
		assertEquals(10,India.getNoOfArmiesPresent());
	}
	
	/**
	 * attack phase test
	 */
	@Test
	public void attackPhaseTest(){
		assertTrue(player[1].getCountriesOwned().contains(Pakistan));
		assertTrue(player[2].getCountriesOwned().contains(Nepal));
		India.getListOfNeighbours().add("Nepal");
		assertEquals(5,India.getNoOfArmiesPresent());
		assertFalse(gameModel.getCurrPlayer().getCountriesOwned().contains(Pakistan));
		assertFalse(gameModel.getCurrPlayer().getCountriesOwned().contains(Nepal));
		gameModel.getCurrPlayer().getStrategy().attackPhase(gameModel);
		assertEquals(5,India.getNoOfArmiesPresent());
		assertTrue(player[0].getCountriesOwned().contains(Pakistan));
		assertTrue(player[0].getCountriesOwned().contains(Nepal));
	}
	
	/**
	 * fortification phase test
	 */
	@Test
	public void fortificationPhaseTest(){
		assertEquals(5,India.getNoOfArmiesPresent());
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		gameModel.getCurrPlayer().getStrategy().fortificationPhase(gameModel);
		assertEquals(10,India.getNoOfArmiesPresent());
		assertEquals(1,Bangladesh.getNoOfArmiesPresent());
		assertEquals(player[1],gameModel.getCurrPlayer());
	}
}
