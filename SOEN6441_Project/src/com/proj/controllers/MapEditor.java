package com.proj.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;

public class MapEditor {
	
	private Map gameMap;
	void validatePreExistingMap(){	
	}
	void validateNewMap(){
		
	}
	void validateAndParseDataFromMap(){
		try{
			// TODO Auto-generated method stub
			//FileReader mapFile=new FileReader(gameMap.getPath()+"\\"+gameMap.getName();
			gameMap=new Map();
			FileReader mapFile;
			String line=null;
				mapFile = new FileReader("F:\\Master's\\Advance_Programming\\SOENPRoject\\SOENPRoject\\TheConquest\\resources\\maps\\World\\World.map");
			String Data="";
			
			BufferedReader mapReader=new BufferedReader(mapFile);
			 while((line=mapReader.readLine())!=null){
				 if(line!="\n"){
					 Data+=line+"\n"; 
				 }
			 }
			 String authorData=Data.substring(Data.toLowerCase().indexOf("[map]"),Data.toLowerCase().indexOf("[continents]"));
			 String continentData=Data.substring(Data.toLowerCase().indexOf("[continents]"),Data.toLowerCase().indexOf("[territories]"));
			 String countryData=Data.substring(Data.toLowerCase().indexOf("[territories]"));
			 
			 String []countryDataArray=countryData.split("\n");
			 String []continentDataArray=continentData.split("\n");
			 
			 for(String stringManipulation:continentDataArray){
				 if(!stringManipulation.equalsIgnoreCase("[continents]") && stringManipulation.length()>5){
					 Continent newContinent=new Continent();
					
					 
					 newContinent.setContinentName(stringManipulation.substring(0,stringManipulation.indexOf("=")));
					 newContinent.setControlValue(Integer.parseInt(stringManipulation.substring(stringManipulation.indexOf("=")+1)));
					 gameMap.getContinents().add(newContinent);
				 }
			 }
			 for(String stringManipulation:countryDataArray){
				 if((!stringManipulation.equalsIgnoreCase("[territories]") && stringManipulation.length()>5)){
					 if(stringManipulation.replaceAll("[^,]","").length()>=4){
						 Country newCountry=new Country();
						 String[] stringManipulationArray=stringManipulation.split(",");
						 newCountry.setCountryName(stringManipulationArray[0]);
						 newCountry.setLatitude(Integer.parseInt(stringManipulationArray[1].trim()));
						 newCountry.setLongitude(Integer.parseInt(stringManipulationArray[2].trim()));
						 
						 for(int i=4;i<stringManipulationArray.length;i++){
							 newCountry.getListOfNeighbours().add(stringManipulationArray[i]);
						 }
						 for(Continent currentContinent:gameMap.getContinents()){
							 if(currentContinent.getContinentName().toLowerCase().indexOf(stringManipulationArray[3].trim().toLowerCase())>=0){
//								 System.out.println(newCountry.getCountryName());
//								 System.out.println(C.getContinentName());
								 currentContinent.getCountriesPresent().add(newCountry);
							 }
						 }
					 }
					 else{
						 	break;
					 }
				 } 
			 }
			 
			 for(Continent currentContinent:gameMap.getContinents()){
				 System.out.println(currentContinent.getContinentName());
				 System.out.println(currentContinent.getControlValue());
				 for(Country currentCountry:currentContinent.getCountriesPresent()){
					 System.out.println(currentCountry.getCountryName());
					 System.out.println(currentCountry.getLatitude());
					 System.out.println(currentCountry.getLongitude());
					 for(String Neighbours:currentCountry.getListOfNeighbours()){
						 System.out.print(Neighbours+",");
					 }
					 System.out.println();
				 }
				 System.out.println();
			}
		}catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		MapEditor m=new MapEditor();
		m.validateAndParseDataFromMap();
	}
}


