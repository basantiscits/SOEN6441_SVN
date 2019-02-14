package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.proj.models.Continent;
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;

public class CreateMapContoller implements ActionListener {

	private Continent continent;

//	private CreateMapEditor createMapEditor;

	private Map map;

	private ContinentArea continentArea;

	private ArrayList<Country> addCountry = new ArrayList<Country>();

//	public CreateMapContoller(CreateMapEditor createMapEditor) {
//		this.createMapEditor = createMapEditor;
//	}

	public void addMap(Map map) {
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		switch (button) {
		case "Add Continent":
			addContinent();
			break;
		case "Add Countries":
			addCountries();
			break;
		default:
			break;
		}

	}

	public void addContinent() {
		boolean loop = true;
		continent = new Continent();
		while (loop) {
			String continentName = JOptionPane.showInputDialog(null, "Enter the Continent name: ", "Add Continent",
					JOptionPane.OK_CANCEL_OPTION | JOptionPane.QUESTION_MESSAGE);
			if (continentName != null) {
				if (continentName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please specify the name!");
				}

				else if (continentName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Name cannot be empty!");
				}

				else if (map.searchContinent(continentName).equalsIgnoreCase(continentName)) {
					JOptionPane.showMessageDialog(null, "Continent name already exists!");
				}

				else if (map.searchContinent(continentName.trim()).equalsIgnoreCase(continentName.trim())) {
					JOptionPane.showMessageDialog(null, "Continent name already exists!");
				}

//				else if ((continentName != null) && (createMapEditor.getMap().searchContinent(continentName) == "")) {
//					continentArea = new ContinentArea(map);
//					continent.setContinentName(continentName);
////					createMapEditor.getMap().addContinent(continent);
////					createMapEditor.createTree();
//					loop = false;
//				}
			} else {
				loop = false;
			}

		}

	}

	public void addCountries() {
		JTextField inputCountry = new JTextField();
		String lastContinent = "";
		if(map.getContinents().size()==0) {
			JOptionPane.showMessageDialog(null, "Please specify continent first!");
		}
		String continents[] = new String[map.getContinents().size()];
		int count = 0;
		
		for(Continent name : map.getContinents()) {
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
				
				else if(map.getContinents().size()==0) {
					JOptionPane.showMessageDialog(null, "Please enter continent first");
				}
				
				else if((map.searchCountry(inputCountry.getText(), (String) continentBox.getSelectedItem()).equalsIgnoreCase(inputCountry.getText()))){
					JOptionPane.showMessageDialog(null, "Country already exists!");
				}
				
				else {
					Continent tempContinent = null;
					String selectedContinent = (String) continentBox.getItemAt(continentBox.getSelectedIndex());
					for(Continent name : map.getContinents()) {
						if(name.getContinentName().equalsIgnoreCase(selectedContinent)) {
							tempContinent = name;
							map.removeContinent(name);
							break;
						}
					}
					
					Country newCountry = new Country();
					newCountry.setCountryName(inputCountry.getText());
					tempContinent.addCountry(newCountry);
					map.addContinent(tempContinent);
					
					addCountry.add(newCountry);
					map.setAllExistingCountries(addCountry);
//a					createMapEditor.createTree();
//					createMapEditor.countriesMatrix();
					
					ArrayList<String> listOfNeighbour = new ArrayList<String>(); 
					
					
					for (Country country : map.getAllExistingCountries()) {
						if (!country.getCountryName().trim().equalsIgnoreCase(newCountry.getCountryName()) ){
							country.getListOfNeighbours().add(newCountry.getCountryName());
							listOfNeighbour.add(country.getCountryName());
						}
					}
					newCountry.setListOfNeighbours(listOfNeighbour);
					//listOfNeighbour = null;
					
					for(Country country:map.getAllExistingCountries()){
						System.out.println(country.getListOfNeighbours().toString());
						System.out.println();
					}
					
					
					loop = false;
				}
			}
			else {
				loop = false;
			}
		}
		
	}

}
