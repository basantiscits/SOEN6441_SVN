package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextField;

import com.proj.controllers.AttackController;
import com.proj.controllers.FortificationController;
//import com.proj.controllers.FortificationController;
//import com.proj.controllers.NewGameController;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;


public class AttackView extends JFrame implements ActionListener {
	private JLabel noOfArmies;
	private JLabel PlayerDicestrlbl;
	private JLabel DefendersDicestrlbl;
	private JLabel source;
	private JLabel destination;
	private JLabel noOfDices;
	private JComboBox selectNoOfDice;
	private String[] noOfDicesList = new String[] { "  --Select--  ","1", "2", "3" };
	private JLabel playerName;
	private JLabel armiesInSource;
	private JLabel armiesInDestination;
	private JComboBox sourceCountry;
	private JComboBox destinationCountry;
	
	private JComboBox sourceCountryArmiesddl;
	private JComboBox destinationCountryArmiesddl;
	
	//private JComboBox selectNoOfArmies;
	private JComboBox selectNoOfDices;
	private JButton Attackbtn;
	private JButton AllOutAttackbtn;
	private JLabel PlayerDicelbl;
	private JLabel DefenderDicelbl;
	private JLabel PlayerDiceRandomAllocated;
	private JLabel DefenderDiceRandomAllocated;
	private Map map;
	private Player[] player;
	private int currentPlayer;
	private GameWindowScreen gameWindow;
	private AttackController AttackController;
	
	
	
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
	 * getter for number of dice
	 * @return combo box for number of dices
	 */
	public JComboBox getNoOfDice() {
		return selectNoOfDice;
	}
	
	/**
	 * setter for numer of dice
	 * @param dice combo box for number of dices
	 */
	public void setNoOfDice(JComboBox dice) {
		this.selectNoOfDice = dice;
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
	 * getter for Game Window
	 * @return game window
	 */
	public GameWindowScreen getGameWindow() {
		return gameWindow;
	}

	/**
	 * setter for Game Window
	 * @param gameWindow object of type GameWindowScreen
	 */
	public void setGameWindow(GameWindowScreen gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	public AttackController getAttackController() {
		return AttackController;
	}
	public void setAttackController(AttackController AttackController) {
		this.AttackController = AttackController;
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

	public AttackView(Map gameMap, Player[] playersArry, int currentPlayer, GameWindowScreen gameWindowScreen) {
		this.map = gameMap;
		this.player = playersArry;
		this.currentPlayer = currentPlayer;
		this.gameWindow = gameWindowScreen;
		AttackController = new AttackController(this);
		setTitle("******ATTACK PHASE******");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		playerName = new JLabel(player[currentPlayer].getPlayerName());
		playerName.setBounds(55, 155, 100, 35);
		add(playerName);
		
		
		PlayerDicestrlbl = new JLabel("Player Dice");
		PlayerDicestrlbl.setBounds(70, -10, 100, 35);
		add(PlayerDicestrlbl);
		
		DefendersDicestrlbl = new JLabel("Defender Dice");
		DefendersDicestrlbl.setBounds(785, -10, 100, 35);
		add(DefendersDicestrlbl);
		
		//Player1 or 2 or 3 Dice
		PlayerDiceRandomAllocated= new JLabel("Dice Count Player");
		PlayerDiceRandomAllocated.setBounds(180, -40, 200, 200);
		add(PlayerDiceRandomAllocated);
		
		// Defender Dice 
		DefenderDiceRandomAllocated= new JLabel("Dice Count Defender");
		DefenderDiceRandomAllocated.setBounds(650, -40, 200, 200);
		add(DefenderDiceRandomAllocated);
		
		
		source = new JLabel("Source Country");
		source.setBounds(165, 120, 100, 30);
		add(source);
		
		armiesInSource = new JLabel();
		armiesInSource.setBounds(165, 180, 100, 30);
		add(armiesInSource);
		
		destination = new JLabel("Destination Country");
		destination.setBounds(315, 120, 120, 35);
		add(destination);
		
		armiesInDestination = new JLabel();
		armiesInDestination.setBounds(315, 180, 100, 30);
		add(armiesInDestination);
		
		
		noOfDices = new JLabel("No. of Dice to be rolled");
		noOfDices.setBounds(465, 120, 135, 35);
		add(noOfDices);
		
		sourceCountry = new JComboBox();
		sourceCountry.setBounds(165, 150, 120, 35);
		addCountryToBox(sourceCountry);
		sourceCountry.setSelectedIndex(-1);
		sourceCountry.addActionListener(AttackController);
		add(sourceCountry);
		
		destinationCountry = new JComboBox();
		destinationCountry.setBounds(315, 150, 100, 35);
		add(destinationCountry);
		destinationCountry.addActionListener(AttackController);
		

		selectNoOfDice = new JComboBox();
		selectNoOfDice.setBounds(465, 150, 100, 35);
		add(selectNoOfDice);
		
		Attackbtn = new JButton("Attack");
		Attackbtn.setBounds(615, 150, 100, 35);
		//send.addActionListener(fortificationController);
		Attackbtn.addActionListener(this);
		add(Attackbtn);
		
		AllOutAttackbtn = new JButton("All out Attack");
		AllOutAttackbtn.setBounds(770, 150, 130, 35);
		//finish.addActionListener(fortificationController);
		AllOutAttackbtn.addActionListener(this);
		add(AllOutAttackbtn);
		
		ImageIcon PlayerDice = new ImageIcon("Images/Dice.jpg");
		ImageIcon DefenderDice = new ImageIcon("Images/Dice.jpg");
		
		PlayerDicelbl=new JLabel("");
		PlayerDicelbl.setIcon(PlayerDice);
		PlayerDicelbl.setBounds(60, -40, 200, 200);
		add(PlayerDicelbl);
		
		DefenderDicelbl=new JLabel("");
		DefenderDicelbl.setIcon(DefenderDice);
		DefenderDicelbl.setBounds(780, -40, 200, 200);
		add(DefenderDicelbl);
		
	

		
	}
	public void addCountryToBox(JComboBox country) {
		country.removeAllItems();
		for(Country c : player[currentPlayer].getCountriesOwned()) {
			if(c.getNoOfArmiesPresent()>1) {
				country.addItem(c.getCountryName());
			}
		}
	}
	
	public void addDestCountries(Country sourCountry) {
		destinationCountry.removeAllItems();
		for(String c : sourCountry.getListOfNeighbours()) {
			if(!(player[currentPlayer].getCountriesOwned().contains(c))) {
				destinationCountry.addItem(c);
			}
		}
	}
		
	
	public void selectDices() {
		selectNoOfDice.removeAllItems();
		for(int i=1; i<Integer.valueOf(armiesInSource.getText()); i++) {
			if(i<4) {
				selectNoOfDice.addItem(String.valueOf(i));
			}
			else {
				break;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Player[] sPlayerName;
	    String sourceCountry;
		String sAttackingCountry;
		String sDefenderCountry;
		//int iNoOfArmiesOfAttacker;
		Country sourCountry;
		if (e.getSource() == Attackbtn)
		{
			System.out.println("\n");
			System.out.println("Attack Param");
			//sPlayerName=player[currentPlayer].getPlayerName();
			sPlayerName=this.player;
			sAttackingCountry=(String) getSourceCountry().getSelectedItem();
			sourCountry = getMap().searchCountry(sAttackingCountry);
			sDefenderCountry=(String) getDestinationCountry().getSelectedItem();
			 String iNoOfArmiesOfAttacker=(String.valueOf(sourCountry.getNoOfArmiesPresent()));
			System.out.println("Player Name : "+sPlayerName+"\n"+ "Source Country :"+ sAttackingCountry+"\n"+ "Defender Country :" +sDefenderCountry+"\n"+ "No of Armies of Attacker : "+iNoOfArmiesOfAttacker);
			//+ sPlayerName + "Source Country :" +sAttackingCountry "Defender Country :" +sDefenderCountry+ "No of Armies of Attacker"+iNoOfArmiesOfAttacker,iNoOfArmiesOfAttacker);
			//Handover to Aman
			AttackController objAttackController= new AttackController();
			//objAttackController.fncNamegivenByAman(sPlayerName,sAttackingCountry,sDefenderCountry,);
		}
		else if(e.getSource() == AllOutAttackbtn)
		{
			System.out.println("\n");
			System.out.println("All out Attack Param");
			//sPlayerName=player[currentPlayer].getPlayerName();
			sPlayerName=this.player;
			sAttackingCountry=(String) getSourceCountry().getSelectedItem();
			sDefenderCountry=(String) getDestinationCountry().getSelectedItem();;
			System.out.println("Player Name : "+sPlayerName+"\n"+ "Source Country :"+ sAttackingCountry+"\n"+ "Defender Country :" +sDefenderCountry);
			//Handover to Aman
			AttackController objAttackController= new AttackController();
			//objAttackController.fncNameGiveByAman(sPlayerName,sAttackingCountry,sDefenderCountry);
		}
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	

}