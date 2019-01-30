package com.proj.models;

import java.util.List;

public class Country {
	private String countryName;
	private String ownedBy;
	private String liesInContinent;
	private int noOfArmiesPresent;
	private List<Country> listOfNeighbours;
	
	
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
	public String getLiesInContinent() {
		return liesInContinent;
	}
	public void setLiesInContinent(String liesInContinent) {
		this.liesInContinent = liesInContinent;
	}
	public int getNoOfArmiesPresent() {
		return noOfArmiesPresent;
	}
	public void setNoOfArmiesPresent(int noOfArmiesPresent) {
		this.noOfArmiesPresent = noOfArmiesPresent;
	}
	public List<Country> getListOfNeighbours() {
		return listOfNeighbours;
	}
	public void setListOfNeighbours(List<Country> listOfNeighbours) {
		this.listOfNeighbours = listOfNeighbours;
	}

}
