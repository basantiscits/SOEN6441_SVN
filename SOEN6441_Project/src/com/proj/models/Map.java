package com.proj.models;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private String Name;
	private String authorName;
	private String Path;
	private List<Continent> Continents;
	public Map(){
		Continents=new ArrayList<Continent>();
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
		return Continents;
	}
	public void setContinents(List<Continent> continents) {
		Continents = continents;
	}
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
