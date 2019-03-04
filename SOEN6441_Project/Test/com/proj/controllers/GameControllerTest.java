package com.proj.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.views.GameWindowScreen;


public class GameControllerTest {
	
	private Player[] player;
	private Map gameMap;
	private GameWindowScreen screen ;
	private GameController controller;
	private Continent continent1,continent2,continent3;
	private Country country1,country2,country3,country4,country5,country6,country7,country8,country9,country10;
	private ArrayList<Continent> continentList;
	
	@Before
	public void before() {
		player = new Player[3];
		player[0] = new Player("Player1");
		player[1] = new Player("Player2");
		player[2] = new Player("Player3");
		gameMap = new Map();
		screen = new GameWindowScreen(gameMap, player);
		controller = new GameController(screen, gameMap);
		continent1 = new Continent();
		continent1.setContinentName("Asia");
		country1 = new Country("India",continent1);
		country2 = new Country("Pakistan",continent1);
		country3 = new Country("Nepal",continent1);
		country4 = new Country("Bangladesh",continent1);
		
		continent1.addCountry(country1);
		continent1.addCountry(country2);
		continent1.addCountry(country3);
		continent1.addCountry(country4);
		
		continent2= new Continent();
		continent2.setContinentName("Europe");
		country5 = new Country("Spain",continent2);
		country6 = new Country("France",continent2);
		country7 = new Country("Italy",continent2);
		country8 = new Country("England",continent2);
		
		continent2.addCountry(country5);
		continent2.addCountry(country6);
		continent2.addCountry(country7);
		continent2.addCountry(country8);
		
		continent3 = new Continent();
		continent3.setContinentName("Africa");
		country9 = new Country("South Africa",continent3);
		country10 = new Country("Nigeria",continent3);
		
		continent3.addCountry(country9);
		continent3.addCountry(country10);
		
		continentList = new ArrayList<Continent>();
		continentList.add(continent1);
		continentList.add(continent2);
		continentList.add(continent3);
		gameMap.setContinents(continentList);
		
		continent1.setControlValue(4);
		continent2.setControlValue(4);
		continent3.setControlValue(2);
		
		player[0].addCountry(country1);
		player[1].addCountry(country2);
		player[2].addCountry(country3);
		player[0].addCountry(country4);
		player[1].addCountry(country5);
		player[2].addCountry(country6);
		player[0].addCountry(country7);
		player[1].addCountry(country8);
		player[2].addCountry(country9);
		player[2].addCountry(country10);
	}
	
	@After
	public void after() {
		
	}

	@Test
	public void intializeReinforcementArmiesTest() {
		controller.intializeReinforcementArmies();
		System.out.println(player[0].getNoOfArmiesOwned()+" : "+player[1].getNoOfArmiesOwned()+" : "+player[2].getNoOfArmiesOwned());
		assertEquals(3,player[0].getNoOfArmiesOwned());
		assertEquals(3,player[1].getNoOfArmiesOwned());
		assertEquals(5,player[2].getNoOfArmiesOwned());
	}

}
