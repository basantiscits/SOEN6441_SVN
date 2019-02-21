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
		save.addActionListener(mapEditorController);
		
		setBorder(BorderFactory.createEtchedBorder());
		
		add(addContinent);
		add(addCountries);
		add(save);
		

	
	}
}
