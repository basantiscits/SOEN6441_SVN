package com.proj.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	
	private Country sourCountry,destCountry;
	
	private Player[] player;
	
	private int currentPlayer;
	
	private GameWindowScreen gameWindow;
	
	public FortificationView(Map map, Player[] player, int currentPlayer, GameWindowScreen gameWindow){
		
		this.map = map;
		this.player = player;
		this.currentPlayer = currentPlayer;
		this.gameWindow = gameWindow;
		gameWindow.setEnabled(false);
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
		addCountryToBox(sourceCountry);
		sourceCountry.setSelectedIndex(-1);
		sourceCountry.addActionListener(this);
		add(sourceCountry);
		
		destinationCountry = new JComboBox();
		destinationCountry.setBounds(315, 150, 100, 35);
		add(destinationCountry);
		destinationCountry.addActionListener(this);
		
		selectNoOfArmies = new JComboBox();
		selectNoOfArmies.setBounds(465, 150, 100, 35);
		add(selectNoOfArmies);
		
		send = new JButton("Send");
		send.setBounds(615, 150, 100, 35);
		send.addActionListener(this);
		add(send);
		
		finish = new JButton("Finish");
		finish.setBounds(765, 150, 100, 35);
		finish.addActionListener(this);
		add(finish);

	}
	
	public void addCountryToBox(JComboBox country) {
		country.removeAllItems();
		for(Country c : player[currentPlayer].getCountriesOwned()) {
			country.addItem(c.getCountryName());
		}
	}
	
	public void addDestCountries(Country sourCountry) {
		destinationCountry.removeAllItems();
		for(Country c : player[currentPlayer].getCountriesOwned()) {
			if(sourCountry.getListOfNeighbours().contains(c.getCountryName())) {
				destinationCountry.addItem(c.getCountryName());
			}
		}
	}
	
	public void AddArmies() {
		selectNoOfArmies.removeAllItems();
		for(int i=1;i < Integer.valueOf(armiesInSource.getText());i++) {
			selectNoOfArmies.addItem(String.valueOf(i));
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sourceCountry) {
			
			if(sourceCountry.getItemCount()!=0) {
				armiesInDestination.setText("");
				selectNoOfArmies.removeAllItems();
				
				destCountry = null;
				
				String countryName = (String) sourceCountry.getSelectedItem();
				
				sourCountry = map.searchCountry(countryName);
				
				armiesInSource.setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				
				addDestCountries(sourCountry);
				
				if(sourCountry.getNoOfArmiesPresent()<=1) {
					JOptionPane.showMessageDialog(null, "Country should contain atleast 2 armies to move 1");
				}
				else {
					selectNoOfArmies.removeAllItems();
					AddArmies();
				}
			}
		}
		
		else if(e.getSource()==destinationCountry) {

			if(destinationCountry.getItemCount()!=0) {
				String destinationSelected = (String) destinationCountry.getSelectedItem();
				destCountry = map.searchCountry(destinationSelected);
				
				armiesInDestination.setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
				
			}
		}
		
		else if(e.getSource()==send) {
			if(sourCountry.getNoOfArmiesPresent()>1 && destCountry!=null) {
				String arm = (String)  selectNoOfArmies.getSelectedItem();
				int army = Integer.valueOf(arm);
				
				for(int i=0;i<army;i++) {
					sourCountry.removeNoOfArmiesCountry();
					destCountry.addNoOfArmiesCountry();
				}
				
				gameWindow.createStartUpTree();
				setVisible(false);
				gameWindow.setEnabled(true);
				dispose();
			}
			else if(sourCountry.getNoOfArmiesPresent()<=1 && destCountry==null) {
				JOptionPane.showMessageDialog(null, "Please select destination country and country with armies more than 1 and ");
			}
			else if(sourCountry.getNoOfArmiesPresent()<=1){
				JOptionPane.showMessageDialog(null, "Please select country with armies more than 1");
			}
			else if(destCountry==null) {
				JOptionPane.showMessageDialog(null, "Please select destination country");
			}
		}
		
		else if(e.getSource()==finish) {
			setVisible(false);
			gameWindow.setEnabled(true);
			dispose();
		}
		
	}

}













