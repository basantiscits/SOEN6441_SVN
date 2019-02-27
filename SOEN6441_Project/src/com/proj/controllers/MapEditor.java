package com.proj.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.proj.models.Continent;
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.utilites.MapTools;



public class MapEditor implements ActionListener {
	
	private Continent continent;
	
	//private CreateMapEditor createMapEditor;
	private com.proj.views.MapEditor mapEditorView;
	
	private Map gameMap;
	
	public MapEditor(com.proj.views.MapEditor mapEditorView){
		this.gameMap=mapEditorView.gameMap;
		this.mapEditorView= mapEditorView;
	}
	
	private ContinentArea continentArea;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		
		
		switch(button) {
			case "Add Continent":
				addContinent();
				break;
			case "Add Countries": 
				addCountries();
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
							continentArea = new ContinentArea(gameMap);
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








