package com.proj.utilites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;

public class MapTools {
	
	public void pickMapFile(Map gameMap){
		
		try {
			
			String ImportFileName;
			JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./save"));
			chooser.setDialogTitle("Choose Map file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.map", "map"));

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				// get the path of the file.
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				// if the path of the selected file is empty.
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				} else {
					if(ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map"))
					{
						// hand over to Aman
						File f = new File(ImportFileName);
						gameMap.setName(f.getName());
						gameMap.setPath(ImportFileName.substring(0,ImportFileName.lastIndexOf("\\")));
						JOptionPane.showMessageDialog(null, "File in Correct format");
						System.out.println(gameMap.getPath());
						System.out.println(gameMap.getName());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "File name invalid");
						String sPrint=ImportFileName.trim().substring(ImportFileName.length() - 4);
						System.out.println(sPrint);
					}
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

		
	
	public void parseAndValidateMap(Map gameMap){
		try{
			// TODO Auto-generated method stub
			//FileReader mapFile=new FileReader(gameMap.getPath()+"\\"+gameMap.getName();
			
			FileReader mapFile;
			String line=null;
				mapFile = new FileReader(gameMap.getPath()+"\\"+gameMap.getName());
			String Data="";
			
			BufferedReader mapReader=new BufferedReader(mapFile);
			 while((line=mapReader.readLine())!=null){
				 if(line!="\n"){
					 Data+=line+"\n"; 
				 }
			 }
			 
			 if(Data.toLowerCase().indexOf("continents")>=0 && Data.toLowerCase().indexOf("territories")>=0 && Data.toLowerCase().indexOf("author")>=0
					 && Data.toLowerCase().indexOf("map")>=0){
				 gameMap.setErrorOccurred(false);
				 gameMap.setErrorMessage("No Errors");
			 }
			 else{
				 gameMap.setErrorOccurred(true);
				 gameMap.setErrorMessage("Information missing");
			 }
			 
			 
			 String authorData=Data.substring(Data.toLowerCase().indexOf("[map]"),Data.toLowerCase().indexOf("[continents]"));
			 String continentData=Data.substring(Data.toLowerCase().indexOf("[continents]"),Data.toLowerCase().indexOf("[territories]"));
			 String countryData=Data.substring(Data.toLowerCase().indexOf("[territories]"));
			 
			 String []countryDataArray=countryData.split("\n");
			 String []continentDataArray=continentData.split("\n");
			 
			 for(String stringManipulation:continentDataArray){
				 if(!stringManipulation.equalsIgnoreCase("[continents]") && stringManipulation.length()>=3){
					 Continent newContinent=new Continent();
					 newContinent.setContinentName(stringManipulation.substring(0,stringManipulation.indexOf("=")));
					 newContinent.setControlValue(Integer.parseInt(stringManipulation.substring(stringManipulation.indexOf("=")+1)));
					 gameMap.getContinents().add(newContinent);
				 }
			 }
			 for(String stringManipulation:countryDataArray){
				 if((!stringManipulation.equalsIgnoreCase("[territories]") && stringManipulation.length()>3)){
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
						 	gameMap.setErrorOccurred(true);
						 	gameMap.setErrorMessage("Information missing");
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
	}


