package com.proj.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.PlayNewGame;


public class NewGameControllerTest {

	static Map gameMap;

	static String absolute_path;
	static PlayNewGame playNewGame;
	static NewGameController newGameControl;

	@BeforeClass
	public static void setup() {
		gameMap = new Map();
		absolute_path = new String();
		String[] comboSelectedPlayer;
		for (int i = 0; i < 6; i++) {
			gameMap = new Map();
			absolute_path = new String();

		}
		absolute_path = "MapFiles/World.map";
		gameMap.setName("World.map");
		gameMap.setPath(absolute_path.substring(0, absolute_path.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		sFunctions.parseAndValidateMap(gameMap, 3);
		playNewGame = new PlayNewGame();
		newGameControl = new NewGameController(playNewGame);

	}

	@After
	public void after() {

	}

	@Test
	public void threePlayerArmiestest() {

		Player player[] = newGameControl.initializingPlayerModels(3, gameMap, null);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(11, player[0].getNoOfArmiesOwned());
		assertEquals(11, player[1].getNoOfArmiesOwned());
		assertEquals(11, player[2].getNoOfArmiesOwned());
	}

	@Test
	public void fourPlayerArmiestest() {

		Player player[] = newGameControl.initializingPlayerModels(4, gameMap, null);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(9, player[0].getNoOfArmiesOwned());
		assertEquals(9, player[1].getNoOfArmiesOwned());
		assertEquals(10, player[2].getNoOfArmiesOwned());
		assertEquals(10, player[3].getNoOfArmiesOwned());
	}

	@Test
	public void fivePlayerArmiestest() {

		Player player[] = newGameControl.initializingPlayerModels(5, gameMap, null);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(6, player[0].getNoOfArmiesOwned());
		assertEquals(6, player[1].getNoOfArmiesOwned());
		assertEquals(7, player[2].getNoOfArmiesOwned());
		assertEquals(7, player[3].getNoOfArmiesOwned());
		assertEquals(7, player[4].getNoOfArmiesOwned());
	}

	// checking no of countries

	@Test
	public void threePlayerCountriesTest() {

		Player player[] = newGameControl.initializingPlayerModels(3, gameMap, null);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(14, player[0].getCountriesOwned().size());
		assertEquals(14, player[1].getCountriesOwned().size());
		assertEquals(14, player[2].getCountriesOwned().size());
	}

	
	@Test
	public void fourPlayerCountriesTest() {
		Player player[] = newGameControl.initializingPlayerModels(4, gameMap, null);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(11, player[0].getCountriesOwned().size());
		assertEquals(11, player[1].getCountriesOwned().size());
		assertEquals(10, player[2].getCountriesOwned().size());
		assertEquals(10, player[3].getCountriesOwned().size());

	}

	
	@Test
	public void fivePlayerCountriesTest() {
		Player player[] = newGameControl.initializingPlayerModels(5, gameMap, null);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(9, player[0].getCountriesOwned().size());
		assertEquals(9, player[1].getCountriesOwned().size());
		assertEquals(8, player[2].getCountriesOwned().size());
		assertEquals(8, player[3].getCountriesOwned().size());
		assertEquals(8, player[4].getCountriesOwned().size());

	}

}
