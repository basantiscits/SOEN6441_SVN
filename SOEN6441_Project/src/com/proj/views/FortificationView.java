package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.proj.controllers.FortificationController;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

public class FortificationView extends JFrame implements ActionListener{
	
	private JLabel source;
	
	private JLabel destination;
	
	private JLabel noOfArmies;
	
	private JLabel playerName;
	
	public JLabel getSource() {
		return source;
	}

	public void setSource(JLabel source) {
		this.source = source;
	}

	public JLabel getDestination() {
		return destination;
	}

	public void setDestination(JLabel destination) {
		this.destination = destination;
	}

	public JLabel getNoOfArmies() {
		return noOfArmies;
	}

	public void setNoOfArmies(JLabel noOfArmies) {
		this.noOfArmies = noOfArmies;
	}

	public JLabel getPlayerName() {
		return playerName;
	}

	public void setPlayerName(JLabel playerName) {
		this.playerName = playerName;
	}

	public JComboBox getSourceCountry() {
		return sourceCountry;
	}

	public void setSourceCountry(JComboBox sourceCountry) {
		this.sourceCountry = sourceCountry;
	}

	public JComboBox getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(JComboBox destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public JComboBox getSelectNoOfArmies() {
		return selectNoOfArmies;
	}

	public void setSelectNoOfArmies(JComboBox selectNoOfArmies) {
		this.selectNoOfArmies = selectNoOfArmies;
	}

	public JButton getSend() {
		return send;
	}

	public void setSend(JButton send) {
		this.send = send;
	}

	public JButton getFinish() {
		return finish;
	}

	public void setFinish(JButton finish) {
		this.finish = finish;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}



	public Player[] getPlayer() {
		return player;
	}

	public void setPlayer(Player[] player) {
		this.player = player;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public GameWindowScreen getGameWindow() {
		return gameWindow;
	}

	public void setGameWindow(GameWindowScreen gameWindow) {
		this.gameWindow = gameWindow;
	}

	public FortificationController getFortificationController() {
		return fortificationController;
	}

	public void setFortificationController(FortificationController fortificationController) {
		this.fortificationController = fortificationController;
	}


	private JLabel armiesInSource;
	
	private JLabel armiesInDestination;
	
	private JComboBox sourceCountry;
	
	private JComboBox destinationCountry;
	
	private JComboBox selectNoOfArmies;
	
	private JButton send;
	
	private JButton finish;
	
	private Map map;
	
	
	
	private Player[] player;
	
	private int currentPlayer;
	
	private GameWindowScreen gameWindow;
	
	private FortificationController fortificationController;
	
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
		
		fortificationController = new FortificationController(this);
		
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
		sourceCountry.addActionListener(fortificationController);
		add(sourceCountry);
		
		destinationCountry = new JComboBox();
		destinationCountry.setBounds(315, 150, 100, 35);
		add(destinationCountry);
		destinationCountry.addActionListener(fortificationController);
		
		selectNoOfArmies = new JComboBox();
		selectNoOfArmies.setBounds(465, 150, 100, 35);
		add(selectNoOfArmies);
		
		send = new JButton("Send");
		send.setBounds(615, 150, 100, 35);
		send.addActionListener(fortificationController);
		add(send);
		
		finish = new JButton("Finish");
		finish.setBounds(765, 150, 100, 35);
		finish.addActionListener(fortificationController);
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
	
	public JLabel getArmiesInSource() {
		return armiesInSource;
	}

	public void setArmiesInSource(JLabel armiesInSource) {
		this.armiesInSource = armiesInSource;
	}

	public JLabel getArmiesInDestination() {
		return armiesInDestination;
	}

	public void setArmiesInDestination(JLabel armiesInDestination) {
		this.armiesInDestination = armiesInDestination;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}













