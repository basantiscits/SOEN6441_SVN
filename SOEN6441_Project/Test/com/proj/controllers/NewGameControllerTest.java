package com.proj.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.PlayNewGame;

/**
 * NewGameControllerTest class
 * @author Ofreish
 * @since 28/02/2019
 * @version 1.0
 */
public class NewGameControllerTest {
	static Map GAME_MAP;
	static String ABSOULTE_PATH;
	static PlayNewGame PLAY_NEW_GAME;
	static NewGameController NEW_GAME_CONTROL;
	String[] comboSelectedPlayers;
	
	/**
	 * This method initializes all the required data to complete the test
	 */
	@BeforeClass
	public static void setup() {
		GAME_MAP = new Map();
		ABSOULTE_PATH = new String();
		for (int i = 0; i < 6; i++) {
			GAME_MAP = new Map();
			ABSOULTE_PATH = new String();

		}
		ABSOULTE_PATH = "MapFiles/World.map";
		GAME_MAP.setName("World.map");
		GAME_MAP.setPath(ABSOULTE_PATH.substring(0, ABSOULTE_PATH.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		sFunctions.parseAndValidateMap(GAME_MAP, 3);
		PLAY_NEW_GAME = new PlayNewGame();
		NEW_GAME_CONTROL = new NewGameController(PLAY_NEW_GAME);
	}

	@After
	public void after() {}
	
	/**
	 * This test method checks number of armies each players has if there are three players
	 */
	@Test
	public void threePlayerArmiesTest() {
		comboSelectedPlayers = new String[3];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(3, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(21, player[0].getNoOfArmiesOwned());
		assertEquals(21, player[1].getNoOfArmiesOwned());
		assertEquals(21, player[2].getNoOfArmiesOwned());
	}
	
	/**
	 * This test method checks number of armies each players has if there are four players
	 */
	@Test
	public void fourPlayerArmiesTest() {
		comboSelectedPlayers = new String[4];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		comboSelectedPlayers[3] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(4, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(19, player[0].getNoOfArmiesOwned());
		assertEquals(19, player[1].getNoOfArmiesOwned());
		assertEquals(20, player[2].getNoOfArmiesOwned());
		assertEquals(20, player[3].getNoOfArmiesOwned());
	}
	
	/**
	 * This test method checks number of armies each players has if there are five players
	 */
	@Test
	public void fivePlayerArmiesTest() {
		comboSelectedPlayers = new String[5];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		comboSelectedPlayers[3] = "Human";
		comboSelectedPlayers[4] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(5, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getNoOfArmiesOwned());
		assertEquals(16, player[0].getNoOfArmiesOwned());
		assertEquals(16, player[1].getNoOfArmiesOwned());
		assertEquals(17, player[2].getNoOfArmiesOwned());
		assertEquals(17, player[3].getNoOfArmiesOwned());
		assertEquals(17, player[4].getNoOfArmiesOwned());
	}

	/**
	 * This test method checks number of countries each players has if there are three players
	 */
	@Test
	public void threePlayerCountriesTest() {
		comboSelectedPlayers = new String[3];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(3, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(14, player[0].getCountriesOwned().size());
		assertEquals(14, player[1].getCountriesOwned().size());
		assertEquals(14, player[2].getCountriesOwned().size());
	}

	/**
	 * This test method checks number of countries each players has if there are four players
	 */
	@Test
	public void fourPlayerCountriesTest() {
		comboSelectedPlayers = new String[4];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		comboSelectedPlayers[3] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(4, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(11, player[0].getCountriesOwned().size());
		assertEquals(11, player[1].getCountriesOwned().size());
		assertEquals(10, player[2].getCountriesOwned().size());
		assertEquals(10, player[3].getCountriesOwned().size());
	}
	
	/**
	 * This test method checks number of countries each players has if there are five players
	 */
	@Test
	public void fivePlayerCountriesTest() {
		comboSelectedPlayers = new String[5];
		comboSelectedPlayers[0] = "Human";
		comboSelectedPlayers[1] = "Human";
		comboSelectedPlayers[2] = "Human";
		comboSelectedPlayers[3] = "Human";
		comboSelectedPlayers[4] = "Human";
		Player player[] = NEW_GAME_CONTROL.initializingPlayerModels(5, GAME_MAP, comboSelectedPlayers);
		System.out.println(player[0].getCountriesOwned().size());
		assertEquals(9, player[0].getCountriesOwned().size());
		assertEquals(9, player[1].getCountriesOwned().size());
		assertEquals(8, player[2].getCountriesOwned().size());
		assertEquals(8, player[3].getCountriesOwned().size());
		assertEquals(8, player[4].getCountriesOwned().size());
	}
}