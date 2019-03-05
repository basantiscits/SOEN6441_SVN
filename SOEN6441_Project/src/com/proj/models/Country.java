package com.proj.models;

import java.util.ArrayList;
import java.util.List;

/**
 * the country class
 * @author Ofreish
 * @since 12 Feb 2019
 * @version 1.0
 */
public class Country {
	private String countryName;
	private String ownedBy;
	private int noOfArmiesPresent;
	private List <String> listOfNeighbours;
	private double longitude;
	private double latitude;
	private boolean isVisited;
	
	/**
	 * constructor of Country class
	 */
	public Country(){
		listOfNeighbours=new ArrayList<String>();
	}
	
	/**
	 * parameterized constructor of Country class
	 * @param countryName name of country
	 * @param continent name of continent
	 */
	public Country(String countryName, Continent continent) {
		this.setCountryName(countryName);
		this.listOfNeighbours = new ArrayList<String>();
		isVisited = false;

	}
	/**
	 * getter for country name
	 * @return name of country
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * setter for country name
	 * @param countryName name of country
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * getter for owned by
	 * @return owned by
	 */
	public String getOwnedBy() {
		return ownedBy;
	}
	
	/**
	 * setter for owned by
	 * @param ownedBy owned by
	 */
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	
	/**
	 * getter for number of armies present
	 * @return number of armies present
	 */
	public int getNoOfArmiesPresent() {
		return this.noOfArmiesPresent;
	}
	
	/**
	 * setter for number of armies present
	 * @param noOfArmiesPresent number of armies present
	 */
	public void setNoOfArmiesPresent(int noOfArmiesPresent) {
		this.noOfArmiesPresent = noOfArmiesPresent;
	}
	
	/**
	 * getter for list of neighbors
	 * @return list of neighbors
	 */
	public List<String> getListOfNeighbours() {
		return listOfNeighbours;
	}
	
	/**
	 * setter for list of neighbors
	 * @param listOfNeighbours list of neighbors
	 */
	public void setListOfNeighbours(List<String> listOfNeighbours) {
		this.listOfNeighbours = listOfNeighbours;
	}
	
	/**
	 * getter for longitude
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * setter for longitude
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		longitude = longitude;
	}
	
	/**
	 * getter for latitude
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * setter for latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		latitude = latitude;
	}

	/**
	 * setter for owned by
	 * @param owner object of Player class
	 */
	public void setOwnedBy(Player owner) {
		this.ownedBy = ownedBy;
	}

	/**
	 * incrementing number of armies present
	 */
	public void addNoOfArmiesCountry() {
		this.noOfArmiesPresent++;	
	}
	
	/**
	 * decrement number of armies present
	 */
	public void removeNoOfArmiesCountry() {
		this.noOfArmiesPresent--;	
	}

	

}
