package com.proj.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

/*
 * @author Ofreish
 */
public class FortificationView extends JFrame implements ActionListener{
	
	private JLabel source;
	
	private JLabel destination;
	
	private JLabel noOfArmies;
	
	private JLabel playerName;
	
	private JLabel armiesInSource;
	
	private JLabel armiesInDestination;
	
	private JComboBox sourceCountry;
	
	private JComboBox destinationCountry;
	
	private JComboBox selectNoOfArmies;
	
	private JButton send;
	
	private JButton finish;
	
	private Map map;
	
	private Country sourCountry,destinationSelected;
	
	public FortificationView(Map map, Player[] player, int currentPlayer){
		
		this.map = map;
		setTitle("Fortification Phase");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		playerName = new JLabel(player[currentPlayer].getPlayerName());
		playerName.setBounds(15, 150, 100, 35);
		add(playerName);
		
		source = new JLabel("Source Country");
		source.setBounds(165, 120, 100, 35);
		add(source);
		
		armiesInSource = new JLabel();
		armiesInSource.setBounds(165, 180, 100, 30);
		add(armiesInSource);
		
		destination = new JLabel("Destination Country");
		destination.setBounds(315, 120, 100, 35);
		add(destination);
		
		armiesInDestination = new JLabel();
		armiesInDestination.setBounds(315, 180, 100, 30);
		add(armiesInDestination);
		
		noOfArmies = new JLabel("Number of Armies");
		noOfArmies.setBounds(465, 120, 100, 35);
		add(noOfArmies);
		
		sourceCountry = new JComboBox();
		sourceCountry.setBounds(165, 150, 120, 35);
		addCountryToBox(player,currentPlayer);
		sourceCountry.setSelectedIndex(-1);
		sourceCountry.addActionListener(this);
		add(sourceCountry);
		
		destinationCountry = new JComboBox();
		destinationCountry.setBounds(315, 150, 100, 35);
		add(destinationCountry);
		destinationCountry.addActionListener(this);
		//destinationCountry.setVisible(true);
		
		selectNoOfArmies = new JComboBox();
		selectNoOfArmies.setBounds(465, 150, 100, 35);
		add(selectNoOfArmies);
		
		send = new JButton("Send");
		send.setBounds(615, 150, 100, 35);
		add(send);
		
		finish = new JButton("Finish");
		finish.setBounds(765, 150, 100, 35);
		add(finish);

	}
	
	public void addCountryToBox(Player[] player, int currentPlayer) {
		sourceCountry.removeAll();
		for(Country c : player[currentPlayer].getCountriesOwned()) {
			sourceCountry.addItem(c.getCountryName());
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sourceCountry) {
			
			if(sourceCountry.getItemCount()!=0) {
				String countryName = (String) sourceCountry.getSelectedItem();
				
				Country sourCountry = map.searchCountry(countryName);
				
				armiesInSource.setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				
				destinationCountry.removeAll();
				for(String str : sourCountry.getListOfNeighbours()) {
					destinationCountry.addItem(str);
				}
				
				selectNoOfArmies.removeAll();
				for(int i=1;i < Integer.valueOf(armiesInSource.getText());i++) {
					selectNoOfArmies.addItem(String.valueOf(i));
				}
			}
		}
		
		else if(e.getSource()==destinationCountry) {
			if(destinationCountry.getItemCount()!=0) {
				String destinationSelected = (String) destinationCountry.getSelectedItem();
				Country destCountry = map.searchCountry(destinationSelected);
				
				armiesInDestination.setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
				
			}
		}
		
	}

}













