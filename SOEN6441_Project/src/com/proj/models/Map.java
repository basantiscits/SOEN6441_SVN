package com.proj.models;

import java.util.ArrayList;
import java.util.List;
/**
 * The Map class
 * @author Ofreish
 * @since 5 Feb 2019
 * @version 1.0
 */
public class Map {
	private String name;
	private String authorName;
	private String path;
	private boolean errorOccurred;
	private String errorMessage;
	private List<Continent> continents;	
	
	/**
	 * constructor of Map class
	 */
	public Map(){
		continents=new ArrayList<Continent>();
		path="";
		name="";
		errorOccurred=false;
	}
	
	/**
	 * getter for path
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * setter for path
	 * @param path path where map is saved
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * getter for name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter for name
	 * @param name name of map
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for list of continents
	 * @return continents
	 */
	public List<Continent> getContinents() {
		return continents;
	}
	
	/**
	 * setter for list of continents
	 * @param continents list of continents
	 */
	public void setContinents(List<Continent> continents) {
		this.continents = continents;
	}
	
	/**
	 * getter for author name
	 * @return author name
	 */
	public String getAuthorName() {
		return authorName;
	}
	
	/**
	 * setter for author name
	 * @param authorName name of author
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	/**
	 * get error occured
	 * @return error occured
	 */
	public boolean getErrorOccurred() {
		return errorOccurred;
	}
	
	/**
	 * setter for error occured
	 * @param errorOccurred error occured 
	 */
	public void setErrorOccurred(boolean errorOccurred) {
		this.errorOccurred = errorOccurred;
	}
	
	/**
	 * getter for error message
	 * @return error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * setter for error message
	 * @param errorMessage error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * search for continent
	 * @param continentName name of continent
	 * @return name of continent
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
	 * remove continent
	 * @param continent name of continent to be removed
	 */
	public void removeContinent(Continent continent) {
		getContinents().remove(continent);
	}
	
	/**
	 * add continent
	 * @param continent name of continent to be added
	 */
	public void addContinent(Continent continent) {
		if(searchContinent(continent.getContinentName())=="") {
			getContinents().add(continent);
		}
	}
	
	/**
	 * list of country names
	 * @return country names
	 */
	public List<String> listOfCountryNames(){
		List<String> countryNames=new ArrayList<String>();
		for(Continent continent:getContinents()){
			for(Country country:continent.getCountriesPresent()){
				countryNames.add(country.getCountryName());
			}
		}
		return countryNames;
	}
	
	/**
	 * list of continent names
	 * @return continent names
	 */
	public List<String> listOfContinentNames(){
		List<String> continentNames=new ArrayList<String>();
		for(Continent continent:getContinents()){
			continentNames.add(continent.getContinentName());
		}
		return continentNames;
	}
	
	/**
	 * search for country in one continent
	 * @param countryName name of country
	 * @param continentName name of continent
	 * @return country name
	 */
	public String searchCountry(String countryName, String continentName) {
		for(Continent name : getContinents()) {
			if(name.getContinentName().equalsIgnoreCase(continentName)) {
				for(Country cName : name.getCountriesPresent() ) {
					if(cName.getCountryName().equalsIgnoreCase(countryName)) {
						return countryName;
					}
				}
			}
		}
		return "";
	}
	
	/**
	 * search for country in all continents
	 * @param countryName name of country
	 * @return name of country
	 */
	public Country searchCountry(String countryName) {
		for(Continent name : getContinents()) {
			for(Country cName : name.getCountriesPresent() ) {
				if(cName.getCountryName().equalsIgnoreCase(countryName)) {
					return cName;
				}
			}
		}
		return null;
	}
}
