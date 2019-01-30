package com.proj.models;

import java.util.List;

public class Map {
	
	private String Name;
	private String Path;
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	private List<Continent> Continents;
	private List<Country> Countries;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public List<Continent> getContinents() {
		return Continents;
	}
	public void setContinents(List<Continent> continents) {
		Continents = continents;
	}
	public List<Country> getCountries() {
		return Countries;
	}
	public void setCountries(List<Country> countries) {
		Countries = countries;
	}
	
	
	

}
