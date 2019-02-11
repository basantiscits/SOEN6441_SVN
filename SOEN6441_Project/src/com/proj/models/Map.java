package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private String Name;
	private String authorName;
	private String Path;
	private List<Continent> continents;	//o
	private List<Country> allExistingCountries;
	public Map(){
		continents=new ArrayList<Continent>();	//o
		//countries=new ArrayList<Country>();	//o
		Path="";
		Name="";
	}
	
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public List<Continent> getContinents() {
		return continents;
	}
	public void setContinents(List<Continent> continents) {
		this.continents = continents;
	}
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	/*
	 * @author Ofreish
	 */
/*	public List<Country> getCountries(){
		return countries;
	}*/
	
	/**
	 * @author ofreish
	 */
	public String searchContinent(String continentName) {
		
		for(Continent name : getContinents()) {
			if(name.getContinentName().equalsIgnoreCase(continentName)) {
				return name.getContinentName();
			}
		}
		return "";
		
	}
	
	/**
	 * @author arpit
	 */
	
	public void removeContinent(Continent continent)
	{
		getContinents().remove(continent);
		
	}
	
	/**
	 * @author ofreish
	 */
	public void addContinent(Continent continent) {
		
		if(searchContinent(continent.getContinentName())=="") {
			getContinents().add(continent);
		}
		
	}
	
	/*
	 * @author ofreish
	 */
	public String searchCountry(String countryName, String continentName) {
		
		System.out.println("1");
		for(Continent name : getContinents()) {
			if(name.getContinentName().equalsIgnoreCase(continentName)) {
				for(Country cName : name.getCountriesPresent() ) {
					if(cName.getCountryName().equalsIgnoreCase(countryName)) {
						System.out.println("2");
						return countryName;
					}
				}
			}
		}
		return "";
		
	}

	/**
	 * @return the allExistingCountries Getter Method
	 */
	public List<Country> getAllExistingCountries() {
		return allExistingCountries;
	}

	/**
	 * @param allExistingCountries Setter Method
	 */
	public void setAllExistingCountries(List<Country> allExistingCountries) {
		this.allExistingCountries = allExistingCountries;
	}
	
	
}








