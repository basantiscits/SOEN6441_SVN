package com.proj.views;

import java.awt.FlowLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


import com.proj.models.Continent;
import com.proj.models.Country;

public class ToolBar extends JPanel{
	private JButton addContinent;
	private JButton addCountries;
	private JButton save;
	private Continent continent;
	private Country newCountry;
	
	
	public ToolBar(com.proj.controllers.MapEditor mapEditorController) {
		
		
		setLayout(new FlowLayout());

		continent =  new Continent();
		newCountry = new Country();
		addContinent = new JButton("Add Continent");
		addContinent.addActionListener(mapEditorController);
		addCountries = new JButton("Add Countries");
		addCountries.addActionListener(mapEditorController);
		save = new JButton("Save");
		
		setBorder(BorderFactory.createEtchedBorder());
		
		add(addContinent);
		add(addCountries);
		add(save);
		
/*		addContinent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean loop = true;
				while(loop) {
					String continentName = JOptionPane.showInputDialog(new CreateaMapEditor(), "Enter the Continent name: ","Add Continent",JOptionPane.OK_CANCEL_OPTION|JOptionPane.QUESTION_MESSAGE);
					if(continentName !=null) {
						if(continentName.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please specify the name!");
						}
						
						else if(continentName.trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Name cannot be empty!");
						}
						
						else if(map.searchContinent(continentName).equalsIgnoreCase(continentName)) {
							JOptionPane.showMessageDialog(null, "Continent name already exists!");
						}
						
						else if ((continentName != null) && (map.searchContinent(continentName)=="")) {
							continent.setContinentName(continentName);
							map.addContinent(continent);
							System.out.println(map.getContinents().size());
							loop = false;
						}
					}
					else {
						loop = false;
					}

				}
				
				
			}
			
		});
		
		addCountries.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField inputCountry = new JTextField();
				String lastContinent = "";
				if(map.getContinents().size()==0) {
					JOptionPane.showMessageDialog(null, "Please specify continent first!");
				}
				String continents[] = new String[map.getContinents().size()];
				int count = 0;
				int index = 0;
				
				for(Continent name : map.getContinents()) {
					continents[count++] = name.getContinentName();
					System.out.println(name.getContinentName());
					if (name.getContinentName().equals(lastContinent))
						index = count - 1;
				}
				JComboBox<Object> continentBox = new JComboBox<Object>(continents);
				Object[] message = {"Select continent name : ", continentBox, "Enter the Country name : ",inputCountry}; 
				continentBox.setSelectedIndex(index);	
				boolean loop = true;
				
				while(loop) {
					
					int countryName = JOptionPane.showConfirmDialog(new CreateaMapEditor(), message, "Country Name", JOptionPane.OK_CANCEL_OPTION);
					
					if(countryName == JOptionPane.OK_OPTION) {
						if(inputCountry.getText()==null||inputCountry.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please specify the name!");
						}
						
						else if(map.getContinents().size()==0) {
							JOptionPane.showMessageDialog(null, "Please enter continent first");
						}
						else {
							Continent tempContinent = null;
							String selectedContinent = (String) continentBox.getItemAt(continentBox.getSelectedIndex());
							for(Continent name : map.getContinents()) {
								if(name.getContinentName().equalsIgnoreCase(selectedContinent)) {
									tempContinent = name;
								}
							}
							Country newCountry = new Country();
							newCountry.setCountryName(inputCountry.getText());
							tempContinent.addCountry(newCountry);
							for(Country country : tempContinent.getCountriesPresent()) {
								country.getListOfNeighbours().add(inputCountry.getText());
							}
							
							loop = false;
						}
					}
					else {
						loop = false;
					}
				}
				
			}
			
		});*/
	
	}
}
