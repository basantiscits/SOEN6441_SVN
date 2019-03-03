package com.proj.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
	
	
	Map map;
	Country country,country1,country2,country3,country4,country5,country6;
	List<Continent> continentList;
	@Before
	public void before() {
		
		map = new Map();
		Continent continent1 = new Continent();
		continent1.setContinentName("Asia");
		country  = new Country("India",continent1);
		country1 = new Country("Pakistan",continent1);
		country2 = new Country("China",continent1);
		country3 = new Country("Srilanka",continent1);
		country4 = new Country("Bangladesh",continent1);
		country5 = new Country("Nepal",continent1);
		country6 = new Country("Bhutan",continent1);
		
		continent1.addCountry(country);
		continent1.addCountry(country1);
		continent1.addCountry(country2);
		continent1.addCountry(country3);
		continent1.addCountry(country4);
		continent1.addCountry(country5);
		continent1.addCountry(country6);

		Continent continent2 = new Continent();
		continent2.setContinentName("North America");
		Country country11  = new Country("Alaska",continent2);
		Country country22 = new Country("Ontario",continent2);
		Country country33 = new Country("Quebec",continent2);
		Country country44 = new Country("Alberta",continent2);
		Country country55 = new Country("Iceland",continent2);
		Country country66 = new Country("Eastern United States",continent2);
		Country country77 = new Country("Western United States",continent2);
		
		continent2.addCountry(country11);
		continent2.addCountry(country22);
		continent2.addCountry(country33);
		continent2.addCountry(country44);
		continent2.addCountry(country55);
		continent2.addCountry(country66);
		continent2.addCountry(country77);
		
		continentList = new ArrayList<Continent>();
		continentList.add(continent1);
		continentList.add(continent2);
		map.setContinents(continentList);
	}
	
	
	@After
	public void after() {
		
	}

	@Test
	public void searchContinentTest() {
		
		assertEquals("Asia", map.searchContinent("Asia"));
		assertEquals("North America", map.searchContinent("North America") );
		
	}

	@Test
	public void searchCountryTest() {
		
		assertEquals(country, map.searchCountry("India"));
		assertEquals(country1, map.searchCountry("Pakistan"));
		assertEquals(country2, map.searchCountry("China"));
		
	}
	
	@Test
	public void getListOfContinentNamesTest() {
		
		ArrayList<String> continentNameList = new ArrayList<String>();
		continentNameList.add("Asia");
		continentNameList.add("North America");
		System.out.println(map.listOfContinentNames());
		System.out.println(continentNameList);
		

		assertEquals(continentNameList, map.listOfContinentNames());
		
	}
	
	@Test
	public void getListOfCountryNamesTest() {
		
		ArrayList<String> countryNameList = new ArrayList<String>();
		countryNameList.add("India");
		countryNameList.add("Pakistan");
		countryNameList.add("China");
		countryNameList.add("Srilanka");
		countryNameList.add("Bangladesh");
		countryNameList.add("Nepal");
		countryNameList.add("Bhutan");
		countryNameList.add("Alaska");
		countryNameList.add("Ontario");
		countryNameList.add("Quebec");
		countryNameList.add("Alberta");
		countryNameList.add("Iceland");
		countryNameList.add("Eastern United States");
		countryNameList.add("Western United States");
		
		
		System.out.println(map.listOfCountryNames());
		System.out.println(countryNameList);
		

		assertEquals(countryNameList, map.listOfCountryNames());
		
	}
	
	

	
}
