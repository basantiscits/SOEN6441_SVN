package com.proj.views;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.proj.controllers.MapEditorController;
import com.proj.models.Continent;
import com.proj.models.Country;
/**
 * ToolBar class
 * @author Kirti
 * @since 5 Feb 2019
 * @version 1.0
 */
public class ToolBar extends JPanel{
	private JButton addContinent;
	private JButton removeContinent;
	private JButton addCountries;
	private JButton removeCountry;
	private JButton save;
	private Continent continent;
	private Country newCountry;
	
	/**
	 * constructor of tool bar class
	 * @param mapEditorController object of MapEditorController class
	 */
	public ToolBar(MapEditorController mapEditorController) {
		
		
		setLayout(new FlowLayout());

		continent =  new Continent();
		newCountry = new Country();
		addContinent = new JButton("Add Continent");
		addContinent.addActionListener(mapEditorController);
		removeContinent = new JButton("Remove Continent");
		removeContinent.addActionListener(mapEditorController);
		addCountries = new JButton("Add Countries");
		addCountries.addActionListener(mapEditorController);
		removeCountry = new JButton("Remove Country");
		removeCountry.addActionListener(mapEditorController);
		save = new JButton("Save");
		save.addActionListener(mapEditorController);
		
		setBorder(BorderFactory.createEtchedBorder());
		
		add(addContinent);
		add(addCountries);
		add(removeContinent);
		add(removeCountry);
		add(save);
		

	
	}
}