package com.proj.utilites;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.proj.models.Map;


public class MapToolsTest {
	
	
	
	Map[] gameMap;
	
	String absolute_path[];
	@Before
	public void setup() {
		gameMap = new Map[6];
		absolute_path = new String[6];
		for(int i = 0;i<6;i++)
		{
			gameMap[i] = new Map();
			absolute_path[i] = new String();
			
		}
		
		absolute_path[0] = "MapFiles/World.map";
		gameMap[0].setName("World.map");
		gameMap[0].setPath(absolute_path[0].substring(0, absolute_path[0].lastIndexOf("/")));
		
		absolute_path[1] = "MapFiles/3D Cliff.map";
		gameMap[1].setName("3D Cliff.map");
		gameMap[1].setPath(absolute_path[1].substring(0, absolute_path[1].lastIndexOf("/")));
		
		absolute_path[2] = "MapFiles/Twin_Volcano.map";
		gameMap[2].setName("Twin_Volcano.map");
		gameMap[2].setPath(absolute_path[2].substring(0, absolute_path[2].lastIndexOf("/")));
		
		absolute_path[3] = "MapFiles/noneighbours.map";
		gameMap[3].setName("noneighbours.map");
		gameMap[3].setPath(absolute_path[3].substring(0, absolute_path[3].lastIndexOf("/")));
		
		absolute_path[4] = "MapFiles/Asia.map";
		gameMap[4].setName("Asia.map");
		gameMap[4].setPath(absolute_path[4].substring(0, absolute_path[4].lastIndexOf("/")));
		
		absolute_path[5] = "MapFiles/three.map";
		gameMap[5].setName("three.map");
		gameMap[5].setPath(absolute_path[5].substring(0, absolute_path[5].lastIndexOf("/")));
	}
	
	

	@Test
	public void parseAndValidateMapCorrectnessTest() {
		boolean isMapValid[];
		isMapValid = new boolean[3];
		boolean[] isMapValidExpected = new boolean[]{true,true,true};
		MapTools sFunctions = new MapTools();
		for(int i=0;i<3;i++)
		{
		isMapValid[i] = sFunctions.parseAndValidateMap(gameMap[i],3);
		System.out.println(isMapValid[i]);
		}
		assertArrayEquals(isMapValidExpected, isMapValid);

	}
	
	@Test
	public void ValidateMapNoNeighboursTest() {
		
		MapTools sFunctions = new MapTools();
		boolean isMapValid = sFunctions.parseAndValidateMap(gameMap[3],3);
		assertEquals(false, isMapValid);
		
	}
	
	@Test
	public void ValidateMapInformationMissing() {
		
		MapTools sFunctions = new MapTools();
		boolean isMapValid = sFunctions.parseAndValidateMap(gameMap[4],3);
		assertEquals(false, isMapValid);
		
	}
	
	
	//checking If the map is connected, return true if map is connected
	@Test
	public void checkMapConnectivityTest(){
		
		MapTools sFunctions = new MapTools();
		
		sFunctions.parseAndValidateMap(gameMap[0],3);
		boolean isMapConnected = sFunctions.checkMapConnectivity(gameMap[0]);
		System.out.println("result:-"+ isMapConnected);
		assertEquals(true, isMapConnected);

	}
	
	//checking If duplicate Neighbours Exist in a map, return true If yes otherwise false
	
	@Test
	public void checkIfNeigbourExistTest() {
		
		
		Map map = new Map();
		String absolutePath = "MapFiles/WorldMaptwosameCountries.map";
		map.setName("WorldMaptwosameCountries.map");
		map.setPath(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		sFunctions.parseAndValidateMap(map,3);
		boolean isMapValid = sFunctions.checkDuplicateNeighbours(map);
		System.out.println("Result is: "+isMapValid);
		assertEquals(true, isMapValid);
	}
	
	//returns true If the map has duplicate countries otherwise false
	public void checkDuplicateCountriesTest() {
		
		
		Map map = new Map();
		String absolutePath = "MapFiles/WorldWithDuplicateCountries.map";
		map.setName("WorldWithDuplicateCountries.map");
		map.setPath(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
		MapTools sFunctions = new MapTools();
		boolean is=sFunctions.parseAndValidateMap(map,3);
		
		boolean isMapValid = sFunctions.checkDuplicateNeighbours(map);
		System.out.println("Result is: "+isMapValid);
		assertEquals(true, isMapValid);
	}
}
