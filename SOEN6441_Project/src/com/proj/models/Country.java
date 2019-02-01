package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private String countryName;
	private String ownedBy;
	private int noOfArmiesPresent;
	private List <String> listOfNeighbours;
	private int Longitude;
	private int Latitude;
	public Country(){
		listOfNeighbours=new ArrayList<String>();
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
		return noOfArmiesPresent;
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
	public int getLongitude() {
		return Longitude;
	}
	public void setLongitude(int longitude) {
		Longitude = longitude;
	}
	public int getLatitude() {
		return Latitude;
	}
	public void setLatitude(int latitude) {
		Latitude = latitude;
	}
	

}
