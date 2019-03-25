package com.proj.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.views.AttackView;
import com.proj.views.GameWindowScreen;

public class AttackControllerTest {
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
	public String diceValues;
	boolean boolAttackAllout;
	boolean boolAttack;
	private int attackerDiceCount;
	private Country sourCountry, destCountry;
	private Player[] player;
	private Map gameMap;
	private int currentPlayer;
	private GameWindowScreen screen ;
	private AttackView obj;
	private AttackController obj_controller;
//	private GameController controller;
//	private Continent continent1,continent2,continent3;
//	private Country country1,country2,country3,country4,country5,country6,country7,country8,country9,country10;
//	private ArrayList<Continent> continentList;
	@Before
	public void before() {
		player = new Player[3];
		player[0] = new Player("Player1");
		player[1] = new Player("Player2");
		player[2] = new Player("Player3");
		gameMap = new Map();
		currentPlayer=1;
		GameModelCreation gameModel = new GameModelCreation(gameMap,player);
		screen = new GameWindowScreen(gameMap, player, gameModel);
		obj=new AttackView( gameMap, player,  currentPlayer,  screen);
		obj_controller=new AttackController(obj);
		//
	}
	
	@After
	public void afterEachTestMethod() {
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
		this.player=AttackView.getPlayer();
		this.gameMap=AttackView.getMap();
		attackerDiceCount=0;
	}
	
	@Test
	public void method1Test() {
		
		Country attacking=new Country();
		Country defending=new Country();
		attacking.setCountryName("India");
		defending.setCountryName("Pakistan");
		int numberOfAttackingArmies=attacking.getNoOfArmiesPresent();
		int numberOfDefendingArmies=defending.getNoOfArmiesPresent();
		ArrayList<Integer> attackerDiceValues=new ArrayList<>();
		ArrayList<Integer> defenderDiceValues=new ArrayList<>();
		attackerDiceValues.add(6);
		attackerDiceValues.add(5);
		attackerDiceValues.add(2);
		defenderDiceValues.add(3);
		if(obj_controller.checkDiceValues(attackerDiceValues,defenderDiceValues)) {
			obj_controller.battleWon();
		}
		else {
			obj_controller.battleLost();
		}
		assertEquals(0,numberOfDefendingArmies);
	}
	

	

}
