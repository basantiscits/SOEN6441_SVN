package com.proj.utilites;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.proj.models.Map;

/**
 * MapTest class
 * @author Basant
 * @since 02/03/2019
 * @version 1.0
 */
public class MapToolsTest {
	private Map[] gameMap;
	private String absolutePath[];

	/**
	 * This method initializes all the required data to complete the test
	 */
	@Before
	public void before() {
		gameMap = new Map[6];
		absolutePath = new String[6];
		for (int i = 0; i < 6; i++) {
			gameMap[i] = new Map();
			absolutePath[i] = new String();

		}
		absolutePath[0] = "MapFiles/World.map";
		gameMap[0].setName("World.map");
		gameMap[0].setPath(absolutePath[0].substring(0, absolutePath[0].lastIndexOf("/")));

		absolutePath[1] = "MapFiles/3D Cliff.map";
		gameMap[1].setName("3D Cliff.map");
		gameMap[1].setPath(absolutePath[1].substring(0, absolutePath[1].lastIndexOf("/")));

		absolutePath[2] = "MapFiles/Twin_Volcano.map";
		gameMap[2].setName("Twin_Volcano.map");
		gameMap[2].setPath(absolutePath[2].substring(0, absolutePath[2].lastIndexOf("/")));

		absolutePath[3] = "MapFiles/noneighbours.map";
		gameMap[3].setName("noneighbours.map");
		gameMap[3].setPath(absolutePath[3].substring(0, absolutePath[3].lastIndexOf("/")));

		absolutePath[4] = "MapFiles/Asia.map";
		gameMap[4].setName("Asia.map");
		gameMap[4].setPath(absolutePath[4].substring(0, absolutePath[4].lastIndexOf("/")));

		absolutePath[5] = "MapFiles/three.map";
		gameMap[5].setName("three.map");
		gameMap[5].setPath(absolutePath[5].substring(0, absolutePath[5].lastIndexOf("/")));
	}

	/**
	 * This test method checks parseAndValidateMap method to test map is valid or not
	 */
	@Test
	public void parseAndValidateMapCorrectnessTest() {
		boolean isMapValid[];
		isMapValid = new boolean[3];
		boolean[] isMapValidExpected = new boolean[] { true, true, true };
		MapTools sFunctions = new MapTools();
		for (int i = 0; i < 3; i++) {
			isMapValid[i] = sFunctions.parseAndValidateMap(gameMap[i], 3);
			System.out.println(isMapValid[i]);
		}
		assertArrayEquals(isMapValidExpected, isMapValid);
	}

	/**
	 * This test method checks map with no neighbours present for particular country
	 */
	@Test
	public void validateMapNoNeighboursTest() {
		MapTools sFunctions = new MapTools();
		boolean isMapValid = sFunctions.parseAndValidateMap(gameMap[3], 3);
		assertEquals(false, isMapValid);
	}

	/**
	 * This test method checks map if it provides valid map information
	 */
	@Test
	public void validateMapInformationMissing() {
		MapTools sFunctions = new MapTools();
		boolean isMapValid = sFunctions.parseAndValidateMap(gameMap[4], 3);
		assertEquals(false, isMapValid);
	}

	/**
	 * This test method checks weather map is connected
	 */
	@Test
	public void checkMapConnectivityTest() {
		MapTools sFunctions = new MapTools();
		sFunctions.parseAndValidateMap(gameMap[0], 3);
		boolean isMapConnected = sFunctions.checkMapConnectivity(gameMap[0]);
		System.out.println("result:-" + isMapConnected);
		assertEquals(true, isMapConnected);
	}

	/**
	 * This test method checks If author exists
	 */
	@Test
	public void checkMapAuthorTest() {
		MapTools sFunctions = new MapTools();
		boolean isAuthorExists = sFunctions.parseAndValidateMap(gameMap[5], 3);
		System.out.println("result:-" + isAuthorExists);
		assertEquals(false, isAuthorExists);
	}

	/**
	 * This test method checks If duplicate neighbors exist
	 */
	@Test
	public void checkIfNeigbourExistTest() {
		Map map = new Map();
		String absolutePath = "MapFiles/WorldMaptwosameCountries.map";
		map.setName("WorldMaptwosameCountries.map");
		map.setPath(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		sFunctions.parseAndValidateMap(map, 3);
		boolean isMapValid = sFunctions.checkDuplicateNeighbours(map);
		System.out.println("Result is: " + isMapValid);
		assertEquals(true, isMapValid);
	}

	/**
	 * This test method checks if map contains duplicate countries
	 */
	@Test
	public void checkDuplicateCountriesTest() {
		Map map = new Map();
		String absolutePath = "MapFiles/WorldWithDuplicateCountries.map";
		map.setName("WorldWithDuplicateCountries.map");
		map.setPath(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		boolean is = sFunctions.parseAndValidateMap(map, 3);
		boolean isMapValid = sFunctions.checkDuplicateNeighbours(map);
		System.out.println("Result is: " + isMapValid);
		assertEquals(false, isMapValid);
	}
}
