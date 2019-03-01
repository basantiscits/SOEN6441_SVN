package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.utilites.MapTools;
import com.proj.views.MapEditor;



public class MapEditorController implements ActionListener {
	
	private Continent continent;
	
	private com.proj.views.MapEditor mapEditorView;
	
	private Map gameMap;
	
	public MapEditorController(MapEditor mapEditorView){
		this.gameMap=mapEditorView.gameMap;
		this.mapEditorView= mapEditorView;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		
		switch(button) {
			case "Add Continent":
				addContinent();
				break;
			case "Add Countries": 
				addCountries();
				break;
			case "Remove Continent":
				removeContinent();
				break;
			case "Remove Country": 
				removeCountry();
				break;
			case "Save":
				save();
				break;
			default:
				break;
		}
		
	}
	
	public void addContinent() {
				boolean loop = true;
				continent = new Continent();
				while(loop) {
					String continentName = JOptionPane.showInputDialog(null, "Enter the Continent name: ","Add Continent",JOptionPane.OK_CANCEL_OPTION|JOptionPane.QUESTION_MESSAGE);
					if(continentName !=null) {
						if(continentName.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please specify the name!");
						}
						
						
						else if(continentName.trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Name cannot be empty!");
						}
						
						else if(gameMap.searchContinent(continentName).equalsIgnoreCase(continentName)) {
							JOptionPane.showMessageDialog(null, "Continent name already exists!");
						}
						
						else if(gameMap.searchContinent(continentName.trim()).equalsIgnoreCase(continentName.trim())) {
							JOptionPane.showMessageDialog(null, "Continent name already exists!");
						}
						
						else if ((continentName != null) && (gameMap.searchContinent(continentName)=="")) {
							continent.setContinentName(continentName);
							gameMap.addContinent(continent);
							mapEditorView.createTree();
							loop = false;
						}
					}
					else {
						loop = false;
					}

				}
				
	}
	
	public void addCountries() {
		JTextField inputCountry = new JTextField();
		String lastContinent = "";
		if(gameMap.getContinents().size()==0) {
			JOptionPane.showMessageDialog(null, "Please specify continent first!");
		}
		
		else {
			String continents[] = new String[gameMap.getContinents().size()];
			int count = 0;
			
			for(Continent name : gameMap.getContinents()) {
				continents[count++] = name.getContinentName();
			}
			
			JComboBox<Object> continentBox = new JComboBox<Object>(continents);
			Object[] message = {"Select continent name : ", continentBox, "Enter the Country name : ",inputCountry}; 
			continentBox.setSelectedIndex(0);	
			boolean loop = true;
			
			while(loop) {
				
				int countryName = JOptionPane.showConfirmDialog(null, message, "Country Name", JOptionPane.OK_CANCEL_OPTION);
				
				if(countryName == JOptionPane.OK_OPTION) {
					if(inputCountry.getText()==null||inputCountry.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please specify the name!");
					}
					
					else if(gameMap.getContinents().size()==0) {
						JOptionPane.showMessageDialog(null, "Please enter continent first");
					}
					
					else if((gameMap.searchCountry(inputCountry.getText(), (String) continentBox.getSelectedItem()).equalsIgnoreCase(inputCountry.getText()))){
						JOptionPane.showMessageDialog(null, "Country already exists!");
					}
					
					else {
						Continent tempContinent = null;
						String selectedContinent = (String) continentBox.getItemAt(continentBox.getSelectedIndex());
						for(Continent name : gameMap.getContinents()) {
							if(name.getContinentName().equalsIgnoreCase(selectedContinent)) {
								tempContinent = name;
							}
						}
						List<String> countryNames=new ArrayList<String>();
						countryNames=gameMap.listOfCountryNames();
						
						Country newCountry = new Country();
						newCountry.setCountryName(inputCountry.getText());
						if(countryNames.size()>0){
							newCountry.setListOfNeighbours(countryNames);
						}
						for(Country country : tempContinent.getCountriesPresent()) {
							country.getListOfNeighbours().add(inputCountry.getText());
						}
						
						
						tempContinent.addCountry(newCountry);
						tempContinent.setControlValue(tempContinent.getCountriesPresent().size());
						mapEditorView.createTree();
						mapEditorView.countriesMatrix();
						loop = false;
					}
				}
				else {
					loop = false;
				}
			}
		}
		
	}
	
	
	public void removeContinent() {
		System.out.println("Aya 1");
		if(gameMap.getContinents().size()==0) {
			JOptionPane.showMessageDialog(null, "Map Contains Zero Continent!");
		}
		
		else {
			String continents[] = new String[gameMap.getContinents().size()];
			int count = 0;
			
			for(Continent name : gameMap.getContinents()) {
				continents[count++] = name.getContinentName();
			}
			
			
			JComboBox<Object> continentBox = new JComboBox<Object>(continents);
			continentBox.setSelectedIndex(-1);
			
			JOptionPane.showConfirmDialog(null, continentBox, "Select continent name : ",JOptionPane.OK_CANCEL_OPTION);
			
			if(continentBox.getSelectedIndex()>-1) {
				
				int result = JOptionPane.showConfirmDialog(null, "Deleting Continent will delete all the countries! \n Do you want to Continue ?");
				
				if(result == JOptionPane.YES_OPTION) {
					Continent tempContinent = null;
					String selectedContinent = (String) continentBox.getItemAt(continentBox.getSelectedIndex());
					for(Continent name : gameMap.getContinents()) {
						if(name.getContinentName().equalsIgnoreCase(selectedContinent)) {
							tempContinent = name;
						}
					}
					
					gameMap.removeContinent(tempContinent);
					gameMap.listOfContinentNames().remove(selectedContinent);
					mapEditorView.createTree();
					mapEditorView.countriesMatrix();
				}
				
				
			}
		}
		
	}
	
	public void removeCountry() {
		if(gameMap.getContinents().size()==0) {
			JOptionPane.showMessageDialog(null, "Map Contains Zero Continent, So no country to remove!");
		}
		
		else if(gameMap.listOfCountryNames().size()==0) {
			JOptionPane.showMessageDialog(null, "All Continents are empty, No country to remove!");
		}
		
		else {
			ArrayList<String> continentsList = new ArrayList<String>();
			int count = 0;
			
			for(Continent contName : gameMap.getContinents()) {
				if(contName.getCountriesPresent().size()>0) {
					continentsList.add(contName.getContinentName());
				}
			}
			String[] continents = new String[continentsList.size()];
			for(String value : continentsList) {
				continents[count++] = value;
			}
			
			JComboBox<Object> continentBox = new JComboBox<Object>(continents);
			continentBox.setSelectedIndex(-1);
			
			int result = JOptionPane.showConfirmDialog(null, continentBox, "Select continent name : ",JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION) {
				if(continentBox.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Please select continent");
				}
				else {
					String continentNameString = (String) continentBox.getSelectedItem();
					Continent continentName = null;
					for(Continent name : gameMap.getContinents()) {
						if(name.getContinentName().equalsIgnoreCase(continentNameString)) {
							continentName = name;
						}
					}
					if(continentName.getCountriesPresent().size()==0) {
						JOptionPane.showMessageDialog(null, continentName.getContinentName()+" has no country to remove");
					}
					else {
						String countries[] = new String[continentName.getCountriesPresent().size()];
						int  val = 0;
						for(Country countryName : continentName.getCountriesPresent() ) {
							countries[val++] = countryName.getCountryName();
						}
						
						JComboBox<Object> countryBox = new JComboBox<Object>(countries);
						countryBox.setSelectedIndex(-1);
						
						int CountryResult = JOptionPane.showConfirmDialog(null, countryBox, "Select Country name : ",JOptionPane.OK_CANCEL_OPTION);
						
						if(CountryResult == JOptionPane.OK_OPTION) {
							
							if(countryBox.getSelectedItem()==null) {
								JOptionPane.showMessageDialog(null, "Please select country");
							}
							else {
								Country tempCountry = null;
								String selectedCountry = (String) countryBox.getItemAt(countryBox.getSelectedIndex());
								for(Country name : continentName.getCountriesPresent()) {
									if(name.getCountryName().equalsIgnoreCase(selectedCountry)) {
										tempCountry = name;
										break;
									}
								}
								continentName.removeCountry(tempCountry);
								gameMap.listOfCountryNames().remove(selectedCountry);
								mapEditorView.createTree();
								mapEditorView.countriesMatrix();
							}
							
						}
				}

				}
			}
			else {
				System.out.println("Continent name not selected");
			}
		}
	}
	
	public void save() {
		MapTools mapTool = new MapTools();
		if(mapTool.validateMap(gameMap,3)){
			System.out.println("Done");
			boolean bool = true;
			while(bool) {
				String mapName = JOptionPane.showInputDialog(null,"Please enter the map name to save");
				if(mapName!=null) {
					if(mapName.trim().isEmpty()) {
						JOptionPane.showConfirmDialog(null, "Please enter some name!");
					}
					else {
						gameMap.setName(mapName);
						if(mapTool.saveDataIntoFile(gameMap,mapName))
						{
							JOptionPane.showMessageDialog(null, "Map has been saved");
						}
						else{
							JOptionPane.showMessageDialog(null, "Not able to save map file, enter different map name.");
						}
						bool = false;
					}
				}
				else {
					bool = false;
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Invalid Map, can not save.");
		}
	}
}








