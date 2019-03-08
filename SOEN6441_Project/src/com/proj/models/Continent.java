package com.proj.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Continent class
 * @author Ofreish
 * @since 10 Feb 2019
 * @version 1.0
 */
public class Continent {
	private String continentName;
	private List<Country> countriesPresent;
	private int controlValue;

	/**
	 * Continent constructor
	 */
	public Continent() {
		controlValue = 0;
		countriesPresent = new ArrayList<Country>();
	}

	/**
	 * getter for continent name
	 * 
	 * @return name of continent
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * setter for continent name
	 * 
	 * @param continentName name of continent
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getter for countries present
	 * 
	 * @return countries present
	 */
	public List<Country> getCountriesPresent() {
		return countriesPresent;
	}

	/**
	 * setter for countries present
	 * 
	 * @param countriesPresent list of countries present
	 */
	public void setCountriesPresent(List<Country> countriesPresent) {
		this.countriesPresent = countriesPresent;
	}

	/**
	 * getter for control value
	 * 
	 * @return control value of continent
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * setter for control value
	 * 
	 * @param controlValue control value of continent
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * add country name to list of countries
	 * 
	 * @param name name of country
	 */
	public void addCountry(Country name) {
		countriesPresent.add(name);
	}

	/**
	 * removes country from list of countries
	 * 
	 * @param name name of country
	 */
	public void removeCountry(Country name) {
		countriesPresent.remove(name);
	}

}
