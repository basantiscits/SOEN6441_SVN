package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.proj.controllers.FortificationController;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

/**
 * Fortification View class
 * @author Kirti
 * @since 1 Feb 2019
 * @version 1.0
 */
public class FortificationView extends JFrame implements ActionListener, Serializable {
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
	private Player[] player;
	private int currentPlayer;
	private GameModelCreation gameModel;
	private FortificationController fortificationController;

	private static final long serialVersionUID = 45443434343L;


	
	/**
	 * getter for game model
	 * @return gameModel
	 */
	public GameModelCreation getGameModel() {
		return gameModel;
	}

	/**
	 * setter for game model
	 * @param gameModel Object of GameModelCreation class
	 */
	public void setGameModel(GameModelCreation gameModel) {
		this.gameModel = gameModel;
	}

	/**
	 * getter for source country
	 * @return source country
	 */
	public JLabel getSource() {
		return source;
	}
	
	/**
	 * setter for source country
	 * @param source name of source country
	 */
	public void setSource(JLabel source) {
		this.source = source;
	}
	
	/**
	 * getter for destination country
	 * @return destination country
	 */
	public JLabel getDestination() {
		return destination;
	}
	
	/**
	 * setter for destination country
	 * @param destination destination country
	 */
	public void setDestination(JLabel destination) {
		this.destination = destination;
	}
	
	/**
	 * getter for number of armies
	 * @return number of armies
	 */
	public JLabel getNoOfArmies() {
		return noOfArmies;
	}
	
	/**
	 * setter for number of armies
	 * @param noOfArmies number of armies
	 */
	public void setNoOfArmies(JLabel noOfArmies) {
		this.noOfArmies = noOfArmies;
	}
	
	/**
	 * getter for player name
	 * @return name of player
	 */
	public JLabel getPlayerName() {
		return playerName;
	}
	
	/**
	 * setter for player name
	 * @param playerName name of player
	 */
	public void setPlayerName(JLabel playerName) {
		this.playerName = playerName;
	}
	
	/**
	 * getter for source country
	 * @return source country
	 */
	public JComboBox getSourceCountry() {
		return sourceCountry;
	}
	
	/**
	 * setter for source country
	 * @param sourceCountry name of source country
	 */
	public void setSourceCountry(JComboBox sourceCountry) {
		this.sourceCountry = sourceCountry;
	}
	
	/**
	 * getter for destination country
	 * @return name of destination country
	 */
	public JComboBox getDestinationCountry() {
		return destinationCountry;
	}
	
	/**
	 * setter for destination country
	 * @param destinationCountry name of destination country
	 */
	public void setDestinationCountry(JComboBox destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	
	/**
	 * getter for number of armies
	 * @return number of armies selected
	 */
	public JComboBox getSelectNoOfArmies() {
		return selectNoOfArmies;
	}
	
	/**
	 * setter for number of armies
	 * @param selectNoOfArmies number of armies selected
	 */
	public void setSelectNoOfArmies(JComboBox selectNoOfArmies) {
		this.selectNoOfArmies = selectNoOfArmies;
	}
	
	/**
	 * getter for send
	 * @return send
	 */
	public JButton getSend() {
		return send;
	}
	
	/**
	 * setter for send
	 * @param send name of button
	 */
	public void setSend(JButton send) {
		this.send = send;
	}
	
	/**
	 * getter for finish
	 * @return finish of type JButton
	 */
	public JButton getFinish() {
		return finish;
	}
	
	/**
	 * setter for finish
	 * @param finish object of type JButton
	 */
	public void setFinish(JButton finish) {
		this.finish = finish;
	}
	
	/**
	 * getter for map
	 * @return map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * setter for map
	 * @param map object of type Map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * getter for Player
	 * @return player
	 */
	public Player[] getPlayer() {
		return player;
	}
	
	/**
	 * setter for Player
	 * @param player array object of type Player[]
	 */
	public void setPlayer(Player[] player) {
		this.player = player;
	}
	
	/**
	 * getter for current player
	 * @return current player number
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * setter for current player
	 * @param currentPlayer current player number
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * getter for Fortification Controller
	 * @return Fortification Controller
	 */
	public FortificationController getFortificationController() {
		return fortificationController;
	}
	
	/**
	 * setter for Fortification Controller
	 * @param fortificationController object of type FortificationController
	 */
	public void setFortificationController(FortificationController fortificationController) {
		this.fortificationController = fortificationController;
	}
	
	/**
	 * Fortification View constructor
	 * @param gameModel Object of GameModelCreation class
	 */
	public FortificationView(GameModelCreation gameModel) {
		this.map = gameModel.getMapDetails();
		this.player = gameModel.getPlayer();
		this.gameModel = gameModel;
		setTitle("Fortification Phase");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		fortificationController = new FortificationController(this);
		playerName = new JLabel(gameModel.getCurrPlayer().getPlayerName());
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
		add(finish);
	}
	
	/**
	 * Adds country to combo box
	 * @param country name of combo box
	 */
	public void addCountryToBox(JComboBox country) {
		country.removeAllItems();
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			country.addItem(c.getCountryName());
		}
	}
	
	/**
	 * Adds destination countries to list of destination countries
	 * @param sourCountry name of source country
	 */
	public void addDestCountries(Country sourCountry) {
		destinationCountry.removeAllItems();
		for(Country c : gameModel.getCurrPlayer().getCountriesOwned()) {
			if(sourCountry.getListOfNeighbours().contains(c.getCountryName())) {
				destinationCountry.addItem(c.getCountryName());
			}
		}
	}
	
	/**
	 * Adds armies to selected number of armies
	 */
	public void AddArmies() {
		selectNoOfArmies.removeAllItems();
		for(int i=1;i < Integer.valueOf(armiesInSource.getText());i++) {
			selectNoOfArmies.addItem(String.valueOf(i));
		}
	}
	
	/**
	 * getter for armies in source country
	 * @return number of armies in source country
	 */
	public JLabel getArmiesInSource() {
		return armiesInSource;
	}
	
	/**
	 * setter for armies in source country
	 * @param armiesInSource number of armies in source country
	 */
	public void setArmiesInSource(JLabel armiesInSource) {
		this.armiesInSource = armiesInSource;
	}

	/**
	 * getter for armies in destination country
	 * @return number of armies in destination country
	 */
	public JLabel getArmiesInDestination() {
		return armiesInDestination;
	}
	
	/**
	 * setter for armies in destination country
	 * @param armiesInDestination number of armies in destination country
	 */
	public void setArmiesInDestination(JLabel armiesInDestination) {
		this.armiesInDestination = armiesInDestination;
	}
	
	/**
	 * getter for dispose message
	 */
	public void getDisposeMsg() {
		JOptionPane.showMessageDialog(null,"Player has no country with armies more than one!!!\n Click Ok to finish Fortification Phase");
		gameModel.incrementTurn();
		gameModel.changePlayer();
		if(gameModel.getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
			System.out.println("No turn for neutral Player");
			gameModel.incrementTurn();
			gameModel.changePlayer();
		}
		gameModel.getGameScreen().getArmyAllocation().setEnabled(true);
		gameModel.getGameScreen().getArmyAllocation().setText("Phase Change");
		gameModel.getGameScreen().getArmyAllocation().doClick();
		setVisible(false);
		gameModel.getGameScreen().getStartPhaseDefinedLabel().setText("Reinforcement Phase");
		gameModel.getGameScreen().displayPlayer();
		gameModel.getGameScreen().getArmyAllocation().setEnabled(true);
		dispose();
		
	}
	
	/**
	 * check draw
	 * @return true if all players have 1 army left else false
	 */
	public boolean checkDraw() {
		for(Player p : gameModel.getPlayer()) {
			if(!(p.getCountriesOwned().size()==gameModel.armiesAllocated(p))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * action performed method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
}
