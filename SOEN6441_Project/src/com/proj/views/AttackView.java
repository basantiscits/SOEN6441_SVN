package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.proj.controllers.AttackController;
import com.proj.models.Continent;
import com.proj.controllers.FortificationController;
import com.proj.controllers.NewGameController;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

/**
 * The Player class
 * @author Ofreish
 * @since 12 Mar 2019
 * @version 1.1
 */
public class AttackView extends JFrame implements ActionListener {
	private JLabel noOfArmies;
	private JLabel PlayerDicestrlbl;
	private JLabel DefendersDicestrlbl;
	private JLabel source;
	private JLabel destination;
	private JLabel noOfDices;
	private JComboBox selectNoOfDice;
	private JLabel playerName;
	private JLabel armiesInSource;
	private JLabel armiesInDestination;
	private JComboBox sourceCountry;
	private JComboBox destinationCountry;
	
	private JComboBox sourceCountryArmiesddl;
	private JComboBox destinationCountryArmiesddl;
	
	private JComboBox selectNoOfDices;
	private JButton Attackbtn;
	private JButton AllOutAttackbtn;
	private JButton finish;
	private JLabel PlayerDicelbl;
	private JLabel DefenderDicelbl;
	private JLabel PlayerDiceRandomAllocated;
	private JLabel DefenderDiceRandomAllocated;
	private Map map;
	private Player[] player;
	private GameModelCreation gameModel;
	private int currentPlayer;
	private GameWindowScreen gameWindow;
	private AttackController AttackController;
	
	private JLabel attackDice1;
	private JLabel attackDice2;
	private JLabel attackDice3;
	private JLabel defendDice1;
	private JLabel defendDice2;
	
	/**
	 * getter for attack dice
	 * @return attackDice1
	 */
	public JLabel getAttackDice1() {
		return attackDice1;
	}
	
	/**
	 * setter for attack dice
	 * @param attackDice1 1st dice value of attacker
	 */
	public void setAttackDice1(JLabel attackDice1) {
		this.attackDice1 = attackDice1;
	}
	
	/**
	 * getter for attack dice
	 * @return attackDice2
	 */
	public JLabel getAttackDice2() {
		return attackDice2;
	}
	
	/**
	 * setter for attack dice
	 * @param attackDice2 2nd dice value of attacker
	 */
	public void setAttackDice2(JLabel attackDice2) {
		this.attackDice2 = attackDice2;
	}
	
	/**
	 * getter for attack dice
	 * @return attackDice3
	 */
	public JLabel getAttackDice3() {
		return attackDice3;
	}
	
	/**
	 * setter for attack dice
	 * @param attackDice2 3rd dice value of attacker
	 */
	public void setAttackDice3(JLabel attackDice3) {
		this.attackDice3 = attackDice3;
	}
	
	/**
	 * getter for defend dice
	 * @return defendDice1
	 */
	public JLabel getDefendDice1() {
		return defendDice1;
	}
	
	/**
	 * setter for defend dice
	 * @param defendDice1 1st dice value of defender
	 */
	public void setDefendDice1(JLabel defendDice1) {
		this.defendDice1 = defendDice1;
	}
	
	/**
	 * getter for defend dice
	 * @return defendDice2
	 */
	public JLabel getDefendDice2() {
		return defendDice2;
	}
	
	/**
	 * setter for defend dice
	 * @param defendDice1 1st dice value of defender
	 */
	public void setDefendDice2(JLabel defendDice2) {
		this.defendDice2 = defendDice2;
	}

	/**
	 * getter for finish
	 * @return finish
	 */
	public JButton getFinish() {
		return finish;
	}
	
	/**
	 * setter for finish
	 * @param finish name of button
	 */
	public void setFinish(JButton finish) {
		this.finish = finish;
	}
	
	/**
	 * getter for AlloutAttack
	 * @return AllOutAttackbtn
	 */
	public JButton getAllOutAttackbtn() {
		return AllOutAttackbtn;
	}
	
	/**
	 * setter for AllOutAttackbtn
	 * @param AllOutAttackbtn name of button
	 */
	public void setAllOutAttackbtn(JButton AllOutAttackbtn) {
		this.AllOutAttackbtn = AllOutAttackbtn;
	}
	
	/**
	 * getter for Attack
	 * @return Attackbtn
	 */
	public JButton getAttack() {
		return Attackbtn;
	}
	
	/**
	 * setter for Attack
	 * @param Attackbtn name of button
	 */
	public void setAttack(JButton Attackbtn) {
		this.Attackbtn = Attackbtn;
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
	
	/**
	 * getter for attack controller
	 * @return attack controller
	 */
	public AttackController getAttackController() {
		return AttackController;
	}
	
	/**
	 * setter for attack controller
	 * @param AttackController Object of AttackController class
	 */
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
	
	/**
	 * getter for game model creation
	 * @return game model
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
	 * Constructor of AttackView class
	 * @param gameMap Object of Map class
	 * @param playersArry Array object of Player class
	 * @param currentPlayer current player
	 * @param gameWindowScreen Object of GameWindowScreen class
	 */
	public AttackView(Map gameMap, Player[] playersArry, int currentPlayer, GameWindowScreen gameWindowScreen) {
		this.map = gameMap;
		this.player = playersArry;
		this.currentPlayer = currentPlayer;
		this.gameWindow = gameWindowScreen;
		this.gameModel = gameWindowScreen.getGameController().getGameModel();
		AttackController = new AttackController(this);
		setTitle("******ATTACK PHASE******");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		playerName = new JLabel(player[currentPlayer].getPlayerName());
		playerName.setBounds(55, 155, 100, 35);
		add(playerName);
		
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
		Attackbtn.addActionListener(AttackController);
		Attackbtn.addActionListener(this);
		add(Attackbtn);
		
		AllOutAttackbtn = new JButton("All out Attack");
		AllOutAttackbtn.setBounds(770, 150, 130, 35);
		AllOutAttackbtn.addActionListener(AttackController);
		AllOutAttackbtn.addActionListener(this);
		add(AllOutAttackbtn);
		
		finish = new JButton("Finish");
		finish.setBounds(500, 250, 130, 35);
		finish.addActionListener(AttackController);
		finish.addActionListener(this);
		add(finish);
		
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
		
		attackDice1 = new JLabel();
		attackDice1.setBounds(400,40,50,20);
		add(attackDice1);

		attackDice2 = new JLabel();
		attackDice2.setBounds(400,60,50,20);
		add(attackDice2);
		
		attackDice3 = new JLabel();
		attackDice3.setBounds(400,80,50,20);
		add(attackDice3);

		defendDice1 = new JLabel();
		defendDice1.setBounds(450,40,50,20);
		add(defendDice1);
		
		defendDice2 = new JLabel();
		defendDice2.setBounds(450,60,50,20);
		add(defendDice2);

		
	}
	public void addCountryToBox(JComboBox country) {
		country.removeAllItems();
		for(Country c : player[currentPlayer].getCountriesOwned()) {
			if(c.getNoOfArmiesPresent()>1) {
				country.addItem(c.getCountryName());
			}
		}
	}
	
	/**
	 * Add destination countries
	 * @param sourCountry source country
	 */
	public void addDestCountries(Country sourCountry) {
		destinationCountry.removeAllItems();
		Country countryToBeChechked = null;
		for(String c : sourCountry.getListOfNeighbours()) {
			for(Continent continent:map.getContinents()){
				for(Country country:continent.getCountriesPresent()){
					if(country.getCountryName().equalsIgnoreCase(c)) {
						countryToBeChechked = country;
					}
				}
			}
			if(!(player[currentPlayer].getCountriesOwned().contains(countryToBeChechked))) {
				destinationCountry.addItem(c);
				System.out.println(c);
			}
		}
	}
		
	/**
	 * select dices
	 */
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
	
	/**
	 * Action performed
	 * @param e Object of ActionEvent class
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}


	/**
	 * Main method
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	

}