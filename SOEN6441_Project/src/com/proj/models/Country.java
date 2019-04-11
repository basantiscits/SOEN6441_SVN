package com.proj.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * the country class
 * @author Ofreish
 * @since 12 Feb 2019
 * @version 1.0
 */
public class Country extends Observable implements Serializable{
	private String countryName;
	private Player ownedBy;
	private int noOfArmiesPresent;
	private List <String> listOfNeighbours;
	private double longitude;
	private double latitude;
	private boolean isVisited;
	private static final long serialVersionUID = 45443434343L;

	
	/**
	 * constructor of Country class
	 */
	public Country(){
		listOfNeighbours=new ArrayList<String>();
		updateChanges();
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
		updateChanges();

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
		updateChanges();
	}
	
	/**
	 * getter for owned by
	 * @return owned by
	 */
	public Player getOwnedBy() {
		return ownedBy;
	}
	
	/**
	 * setter for owned by
	 * @param ownedBy owned by
	 */
	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
		updateChanges();
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
		updateChanges();
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
		updateChanges();
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
	 * @param longitude longitude of country
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
	 * @param latitude latitude of country
	 */
	public void setLatitude(double latitude) {
		latitude = latitude;
	}

	/**
	 * incrementing number of armies present
	 */
	public void addNoOfArmiesCountry() {
		this.noOfArmiesPresent++;
		updateChanges();
	}
	
	/**
	 * decrement number of armies present
	 */
	public void removeNoOfArmiesCountry() {
		this.noOfArmiesPresent--;
		updateChanges();
	}
	
	/**
	 * Update changes
	 */
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
