package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private String countryName;
	private String ownedBy;
	private int noOfArmiesPresent;
	private List <String> listOfNeighbours;
	private float Longitude;
	private float Latitude;
	private boolean isVisited;
	public Country(){
		listOfNeighbours=new ArrayList<String>();
	}
	public Country(String countryName, Continent continent) {
		this.setCountryName(countryName);
		this.listOfNeighbours = new ArrayList<>();
		isVisited = false;

	}
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	public int getNoOfArmiesPresent() {
		return this.noOfArmiesPresent;
	}
	public void setNoOfArmiesPresent(int noOfArmiesPresent) {
		this.noOfArmiesPresent = noOfArmiesPresent;
	}
	public List<String> getListOfNeighbours() {
		return listOfNeighbours;
	}
	public void setListOfNeighbours(List<String> listOfNeighbours) {
		this.listOfNeighbours = listOfNeighbours;
	}
	public float getLongitude() {
		return Longitude;
	}
	public void setLongitude(int longitude) {
		Longitude = longitude;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(int latitude) {
		Latitude = latitude;
	}

	public void setOwnedBy(Player owner) {
		this.ownedBy = ownedBy;
	}

	public void addNoOfArmiesCountry() {
		this.noOfArmiesPresent++;	
	}
	
	public void removeNoOfArmiesCountry() {
		this.noOfArmiesPresent--;	
	}

	

}
