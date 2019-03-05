package com.proj.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import com.proj.controllers.GameController;
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

/**
 * Game Window Screen class
 * @author Kirti
 * @since 10 Feb 2019
 * @version 1.0
 */
/**
 * @author Arpit
 *
 */
public class GameWindowScreen extends JFrame implements ActionListener {
	private int currentPlayer = 0;
	public Player[] player;
	private JLabel countriesLabel;
	private JLabel continentLabel;
	public Map gameMap;
	private JTree mapTree, startUpTree;
	private JTree playerAllocationCountry;
	private JScrollPane treeScrollPane, startUpScrollPane, strengthPane;
	private String userSelTreeNode;
	private JPanel startPhaseViewPanel;
	public JLabel startPhaseDefinedLabel;
	public JLabel playerAllocatedLabel;
	private JPanel dynamicAreastartPhasePanel;

	private JPanel randomPlayerPhaseViewPanel;
	private JPanel dynamicAreaPlayerPhasePanel;

	private JPanel ContientLabelViewPanel;
	private JPanel CountryLabelViewPanel;

	private JPanel tableHeader;
	private JLabel tableHeaderLabel;

	private JPanel allocationPanel;

	private JTextArea textArea;
	private List<String> countries = new ArrayList<String>();

	private JTable playerStrength;

	private String data[][];
	private String countryColumn[];
	private JTable tablematrix;
	private JScrollPane scrollPane;

	private JLabel currentPlayerName;
	private JComboBox countriesComboBox;
	private JButton armyAllocation;
	private JLabel armiesAvailable;
	private GameController gameController;

	/**
	 * Game Window Screen constructor
	 * @param gameMap Object of Map class
	 * @param player Array object of Player class 
	 */
	public GameWindowScreen(Map gameMap, Player[] player) {

		super("Game Window");
		this.gameMap = gameMap;
		this.player = player;

		gameController = new GameController(this, gameMap);
		
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);

		addComponents();
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
			}
		});
	}

	/**
	 * Adding components to Game window screen
	 */
	public void addComponents() {
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);

		Dimension frameSize = this.getSize();

		ContientLabelViewPanel = new JPanel();
		continentLabel = new JLabel("Continent Tree");
		Dimension continentSize = continentLabel.getPreferredSize();
		continentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		continentLabel.setBounds(140, 50, continentSize.width + 500, continentSize.height);
		ContientLabelViewPanel.setBounds(10, 35, frameSize.width - 950, 35);
		add(ContientLabelViewPanel);
		ContientLabelViewPanel.setBackground(Color.lightGray);
		ContientLabelViewPanel.setLayout(new FlowLayout());
		continentLabel.setFont(new Font("dialog", 1, 15));
		ContientLabelViewPanel.setBorder(blackline);
		ContientLabelViewPanel.add(continentLabel);

		treeScrollPane = new JScrollPane(mapTree);
		treeScrollPane.setBounds(10, 70, frameSize.width - 950, frameSize.height - 800);

		CountryLabelViewPanel = new JPanel();
		countriesLabel = new JLabel("Countries Matrix");
		Dimension countriesSize = countriesLabel.getPreferredSize();
		countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));

		countriesLabel.setBounds(240, 50, countriesSize.width + 500, countriesSize.height);
		CountryLabelViewPanel.setBounds(ContientLabelViewPanel.getBounds().x + (int) (ContientLabelViewPanel.getBounds().getWidth()), 35,frameSize.width - 300, 35);
		add(CountryLabelViewPanel);
		CountryLabelViewPanel.setBackground(Color.lightGray);
		CountryLabelViewPanel.setLayout(new FlowLayout());
		countriesLabel.setFont(new Font("dialog", 1, 15));
		CountryLabelViewPanel.setBorder(blackline);
		CountryLabelViewPanel.add(countriesLabel);

		scrollPane = new JScrollPane(tablematrix);

		scrollPane.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()), 70,frameSize.width - 300, frameSize.height - 800);

		startPhaseViewPanel = new JPanel();
		startPhaseViewPanel.setBounds(10,treeScrollPane.getBounds().y + (int) (treeScrollPane.getBounds().getHeight() + 10),frameSize.width - 50, 35);
		add(startPhaseViewPanel);
		startPhaseViewPanel.setBackground(Color.lightGray);
		startPhaseViewPanel.setLayout(new FlowLayout());

		startPhaseDefinedLabel = new JLabel("StartUp Phase");
		startPhaseDefinedLabel.setFont(new Font("dialog", 1, 15));
		startPhaseViewPanel.setBorder(blackline);
		startPhaseViewPanel.add(startPhaseDefinedLabel);

		startUpScrollPane = new JScrollPane(startUpTree);
		startUpScrollPane.setBounds(10,startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight()) + 5,635 + (int) (200), frameSize.height - 900);

		tableHeader = new JPanel();
		tableHeader.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight()) + 5, 320, 25);

		tableHeaderLabel = new JLabel("Total Countries " + gameMap.listOfCountryNames().size());
		tableHeaderLabel.setFont(new Font("dialog", 1, 15));
		tableHeaderLabel.setBorder(blackline);
		tableHeader.add(tableHeaderLabel);

		strengthPane = new JScrollPane(playerStrength);
		strengthPane.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),tableHeader.getBounds().y + (int) (tableHeader.getBounds().getHeight()), (int) (320),frameSize.height - 925);

		currentPlayerName = new JLabel(player[currentPlayer].getPlayerName());
		currentPlayerName.setBounds(150, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,100, 30);
		add(currentPlayerName);

		countriesComboBox = new JComboBox();
		countriesComboBox.setBounds(350, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,150, 30);
		add(countriesComboBox);
		addCountriesToBox(player[currentPlayer]);

		armyAllocation = new JButton("Place Army");
		armyAllocation.setBounds(650, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,100, 30);
		armyAllocation.addActionListener(gameController);
		add(armyAllocation);

		armiesAvailable = new JLabel("Number of Armies Available:" + String.valueOf(player[currentPlayer].getNoOfArmiesOwned()));
		armiesAvailable.setBounds(850, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,200, 30);
		add(armiesAvailable);

		add(scrollPane);
		add(treeScrollPane);
		add(startUpScrollPane);
		add(tableHeader);
		add(strengthPane);

		countriesMatrix();
		createTree();
		createStartUpTree();
		playerStrengthTable();

	}

	/**
	 * Reinforcement phase
	 */
	public void reinforce() {
		if (player[currentPlayer].getNoOfArmiesOwned() == 0 && currentPlayer < player.length) {
			System.out.println("currentPlayer: "+currentPlayer);
			FortificationView FV = new FortificationView(gameMap, player, currentPlayer, this);
			FV.setVisible(true);
			currentPlayer++;
			if (currentPlayer == player.length) {
				currentPlayer--;
			}

		}
	}

	/**
	 * Displays number of armies available
	 */
	public void displayPhase() {
		
		addPlayerName(player[currentPlayer].getPlayerName());
		addCountriesToBox(player[currentPlayer]);
		armiesAvailable.setText("Number of Armies Available:" + String.valueOf(player[currentPlayer].getNoOfArmiesOwned()));

	}

	/**
	 * The countries matrix
	 */
	public void countriesMatrix() {
		System.out.println("inside countriesMAtrix");
		countries = gameMap.listOfCountryNames();

		int noOfCountries = countries.size();

		DefaultTableModel dtm = new DefaultTableModel(noOfCountries, noOfCountries) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		data = new String[noOfCountries][noOfCountries + 1];
		countryColumn = new String[noOfCountries + 1];
		countryColumn[0] = "**";
		int i = 0;
		int j = 0;

		for (String countryName : countries) {

			data[i++][0] = countryName;
			countryColumn[++j] = countryName;
		}
		dtm.setDataVector(data, countryColumn);

		tablematrix = new JTable(dtm) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);

				String value = (String) dtm.getValueAt(row, col);

				if (value.equals("N")) {
					component.setBackground(Color.LIGHT_GRAY);
				}
				else {
					component.setBackground(Color.WHITE);
				}
				return component;
			}
		};
		tablematrix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane.getViewport().removeAll();
		scrollPane.getViewport().add(tablematrix);

		for (Continent currentContinent : gameMap.getContinents()) {
			for (Country currentCountry : currentContinent.getCountriesPresent()) {
				int row_length = data.length;
				int column_length = data[0].length;
				for (i = 0; i < row_length; i++) {
					if (currentCountry.getCountryName().equalsIgnoreCase(data[i][0])) {
						for (j = 1; j < column_length; j++) {
							if (!currentCountry.getListOfNeighbours().contains(countryColumn[j])) {
								tablematrix.setValueAt("N", i, j);
							}
							else {
								tablematrix.setValueAt("Y", i, j);
							}
						}
					}
				}
			}
		}

	}
	
	
	/**
	 * This method create Tree with all the continents and countries
	 */
	public void createTree() {

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - " + gameMap.getName() + "");

		for (Continent continent : gameMap.getContinents()) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(continent.getContinentName());
			for (Country country : continent.getCountriesPresent()) {
				DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(country.getCountryName());
				branch.add(subBranch);
			}
			top.add(branch);
		}
		mapTree = new JTree(top);
		treeScrollPane.getViewport().add(mapTree);

	}

	/**
	 * getter for current player
	 * @return current player
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
	 * getter for player
	 * @return player
	 */
	public Player[] getPlayer() {
		return player;
	}

	/**
	 * getter for player at given index
	 * @param index index for player array object
	 * @return player at given index
	 */
	public Player getPlayerAtIndex(int index) {
		return player[index];
	}

	/**
	 * setter for player
	 * @param player array object of Player class
	 */
	public void setPlayer(Player[] player) {
		this.player = player;
	}

	/**
	 * getter for countries label
	 * @return countries label
	 */
	public JLabel getCountriesLabel() {
		return countriesLabel;
	}

	/**
	 * setter for countries label
	 * @param countriesLabel Object of JLabel class
	 */
	public void setCountriesLabel(JLabel countriesLabel) {
		this.countriesLabel = countriesLabel;
	}

	/**
	 * getter for countries label
	 * @return continent label
	 */
	public JLabel getContinentLabel() {
		return continentLabel;
	}

	/**
	 * setter for continent label
	 * @param continentLabel Object of class JLabel
	 */
	public void setContinentLabel(JLabel continentLabel) {
		this.continentLabel = continentLabel;
	}

	/**
	 * getter for game map
	 * @return
	 */
	public Map getGameMap() {
		return gameMap;
	}

	/**
	 * setter for game map
	 * @param gameMap Object of Map class
	 */
	public void setGameMap(Map gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * getter for map tree
	 * @return map tree
	 */
	public JTree getMapTree() {
		return mapTree;
	}

	/**
	 * setter for map tree
	 * @param mapTree Object of JTree class
	 */
	public void setMapTree(JTree mapTree) {
		this.mapTree = mapTree;
	}

	/**
	 * getter for start up tree
	 * @return start up tree
	 */
	public JTree getStartUpTree() {
		return startUpTree;
	}

	/**
	 * setter for start up tree
	 * @param startUpTree Object of JTree
	 */
	public void setStartUpTree(JTree startUpTree) {
		this.startUpTree = startUpTree;
	}

	/**
	 * getter for player allocation country
	 * @return player allocation country
	 */
	public JTree getPlayerAllocationCountry() {
		return playerAllocationCountry;
	}

	/**
	 * setter for player allocation country
	 * @param playerAllocationCountry Object of JTree class
	 */
	public void setPlayerAllocationCountry(JTree playerAllocationCountry) {
		this.playerAllocationCountry = playerAllocationCountry;
	}

	/**
	 * getter for scroll panel
	 * @return tree scroll panel
	 */
	public JScrollPane getTreeScrollPane() {
		return treeScrollPane;
	}

	/**
	 * setter for scroll panel
	 * @param treeScrollPane Object of JScrollPane class
	 */
	public void setTreeScrollPane(JScrollPane treeScrollPane) {
		this.treeScrollPane = treeScrollPane;
	}

	/**
	 * getter for start up scroll pane
	 * @return start up scroll pane
	 */
	public JScrollPane getStartUpScrollPane() {
		return startUpScrollPane;
	}

	/**
	 * setter for start up scroll pane
	 * @return Object of JScrollPane class
	 */
	public void setStartUpScrollPane(JScrollPane startUpScrollPane) {
		this.startUpScrollPane = startUpScrollPane;
	}

	/**
	 * getter for get strength pane
	 * @return get strength pane
	 */
	public JScrollPane getStrengthPane() {
		return strengthPane;
	}

	/**
	 * setter for strength pane
	 * @return Object of JScrollPane class
	 */
	public void setStrengthPane(JScrollPane strengthPane) {
		this.strengthPane = strengthPane;
	}

	/**
	 * getter for user selected tree node
	 * @return user selected tree node
	 */
	public String getUserSelTreeNode() {
		return userSelTreeNode;
	}

	/**
	 * setter for user selected tree node
	 * @return user selected tree node name
	 */
	public void setUserSelTreeNode(String userSelTreeNode) {
		this.userSelTreeNode = userSelTreeNode;
	}

	/**
	 * getter for start phase view panel
	 * @return start phase view panel
	 */
	public JPanel getStartPhaseViewPanel() {
		return startPhaseViewPanel;
	}

	/**
	 * setter for start phase view panel
	 * @return Object of JPanel class
	 */
	public void setStartPhaseViewPanel(JPanel startPhaseViewPanel) {
		this.startPhaseViewPanel = startPhaseViewPanel;
	}

	/**
	 * getter for start phase defined label
	 * @return start phase defined label
	 */
	public JLabel getStartPhaseDefinedLabel() {
		return startPhaseDefinedLabel;
	}

	/**
	 * setter for start phase defined label
	 * @return Object of JLabel class
	 */
	public void setStartPhaseDefinedLabel(JLabel startPhaseDefinedLabel) {
		this.startPhaseDefinedLabel = startPhaseDefinedLabel;
	}
	

	
	/**
	 * @return the playerAllocatedLabel
	 */
	public JLabel getPlayerAllocatedLabel() {
		return playerAllocatedLabel;
	}

	/**
	 * @param playerAllocatedLabel the playerAllocatedLabel to set
	 */
	public void setPlayerAllocatedLabel(JLabel playerAllocatedLabel) {
		this.playerAllocatedLabel = playerAllocatedLabel;
	}

	/**
	 * @return the dynamicAreastartPhasePanel
	 */
	public JPanel getDynamicAreastartPhasePanel() {
		return dynamicAreastartPhasePanel;
	}

	/**
	 * @param dynamicAreastartPhasePanel the dynamicAreastartPhasePanel to set
	 */
	public void setDynamicAreastartPhasePanel(JPanel dynamicAreastartPhasePanel) {
		this.dynamicAreastartPhasePanel = dynamicAreastartPhasePanel;
	}

	/**
	 * @return the randomPlayerPhaseViewPanel
	 */
	public JPanel getRandomPlayerPhaseViewPanel() {
		return randomPlayerPhaseViewPanel;
	}

	/**
	 * @param randomPlayerPhaseViewPanel the randomPlayerPhaseViewPanel to set
	 */
	public void setRandomPlayerPhaseViewPanel(JPanel randomPlayerPhaseViewPanel) {
		this.randomPlayerPhaseViewPanel = randomPlayerPhaseViewPanel;
	}

	/**
	 * @return the dynamicAreaPlayerPhasePanel
	 */
	public JPanel getDynamicAreaPlayerPhasePanel() {
		return dynamicAreaPlayerPhasePanel;
	}

	/**
	 * @param dynamicAreaPlayerPhasePanel the dynamicAreaPlayerPhasePanel to set
	 */
	public void setDynamicAreaPlayerPhasePanel(JPanel dynamicAreaPlayerPhasePanel) {
		this.dynamicAreaPlayerPhasePanel = dynamicAreaPlayerPhasePanel;
	}

	/**
	 * @return the contientLabelViewPanel
	 */
	public JPanel getContientLabelViewPanel() {
		return ContientLabelViewPanel;
	}

	/**
	 * @param contientLabelViewPanel the contientLabelViewPanel to set
	 */
	public void setContientLabelViewPanel(JPanel contientLabelViewPanel) {
		ContientLabelViewPanel = contientLabelViewPanel;
	}

	/**
	 * @return the countryLabelViewPanel
	 */
	public JPanel getCountryLabelViewPanel() {
		return CountryLabelViewPanel;
	}

	/**
	 * @param countryLabelViewPanel the countryLabelViewPanel to set
	 */
	public void setCountryLabelViewPanel(JPanel countryLabelViewPanel) {
		CountryLabelViewPanel = countryLabelViewPanel;
	}

	/**
	 * @return the tableHeader
	 */
	public JPanel getTableHeader() {
		return tableHeader;
	}

	/**
	 * @param tableHeader the tableHeader to set
	 */
	public void setTableHeader(JPanel tableHeader) {
		this.tableHeader = tableHeader;
	}

	/**
	 * @return the tableHeaderLabel
	 */
	public JLabel getTableHeaderLabel() {
		return tableHeaderLabel;
	}

	/**
	 * @param tableHeaderLabel the tableHeaderLabel to set
	 */
	public void setTableHeaderLabel(JLabel tableHeaderLabel) {
		this.tableHeaderLabel = tableHeaderLabel;
	}

	/**
	 * @return the allocationPanel
	 */
	public JPanel getAllocationPanel() {
		return allocationPanel;
	}

	/**
	 * @param allocationPanel the allocationPanel to set
	 */
	public void setAllocationPanel(JPanel allocationPanel) {
		this.allocationPanel = allocationPanel;
	}

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	/**
	 * @return the playerStrength
	 */
	public JTable getPlayerStrength() {
		return playerStrength;
	}

	/**
	 * @param playerStrength the playerStrength to set
	 */
	public void setPlayerStrength(JTable playerStrength) {
		this.playerStrength = playerStrength;
	}

	/**
	 * @return the data
	 */
	public String[][] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String[][] data) {
		this.data = data;
	}

	/**
	 * @return the countryColumn
	 */
	public String[] getCountryColumn() {
		return countryColumn;
	}

	/**
	 * @param countryColumn the countryColumn to set
	 */
	public void setCountryColumn(String[] countryColumn) {
		this.countryColumn = countryColumn;
	}

	/**
	 * @return the tablematrix
	 */
	public JTable getTablematrix() {
		return tablematrix;
	}

	/**
	 * @param tablematrix the tablematrix to set
	 */
	public void setTablematrix(JTable tablematrix) {
		this.tablematrix = tablematrix;
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * @return the currentPlayerName
	 */
	public JLabel getCurrentPlayerName() {
		return currentPlayerName;
	}

	/**
	 * @param currentPlayerName the currentPlayerName to set
	 */
	public void setCurrentPlayerName(JLabel currentPlayerName) {
		this.currentPlayerName = currentPlayerName;
	}

	/**
	 * @return the countriesComboBox
	 */
	public JComboBox getCountriesComboBox() {
		return countriesComboBox;
	}

	/**
	 * @param countriesComboBox the countriesComboBox to set
	 */
	public void setCountriesComboBox(JComboBox countriesComboBox) {
		this.countriesComboBox = countriesComboBox;
	}

	/**
	 * @return the armyAllocation
	 */
	public JButton getArmyAllocation() {
		return armyAllocation;
	}

	/**
	 * @param armyAllocation the armyAllocation to set
	 */
	public void setArmyAllocation(JButton armyAllocation) {
		this.armyAllocation = armyAllocation;
	}

	/**
	 * @return the armiesAvailable
	 */
	public JLabel getArmiesAvailable() {
		return armiesAvailable;
	}

	/**
	 * @param armiesAvailable the armiesAvailable to set
	 */
	public void setArmiesAvailable(JLabel armiesAvailable) {
		this.armiesAvailable = armiesAvailable;
	}

	/**
	 * @return the gameController
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * @param gameController the gameController to set
	 */
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * This method create tree with players and the countries owned by them and corresponding armies in particular country
	 */
	public void createStartUpTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Initial Allocation");

		for (Player P : player) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(P.getPlayerName());
			for (Country C : P.getCountriesOwned()) {
				DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(
						C.getCountryName() + "[" + C.getNoOfArmiesPresent() + "] current armies");
				branch.add(subBranch);
			}
			top.add(branch);
		}
		startUpTree = new JTree(top);
		startUpScrollPane.getViewport().add(startUpTree);

	}
	
	/**
	 * This method create table which displays the number of countries owned by players 
	 */
	public void playerStrengthTable() {

		String countrySize = "Number of Countries";

		String[] column = { "Players", countrySize };
		String[][] data = new String[player.length][column.length];

		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < column.length; j++) {
				if (j == 0) {
					data[i][j] = player[i].getPlayerName();
				} else {
					data[i][j] = String.valueOf(player[i].getCountriesOwned().size());
				}
			}
		}

		playerStrength = new JTable(data, column);
		strengthPane.getViewport().add(playerStrength);

	}

	public void addPlayerName(String name) {
		currentPlayerName.setText(name);
	}

	/**
	 * This method add countries to JComboBox according to the player
	 * @param p player 
	 */
	public void addCountriesToBox(Player p) {
		countriesComboBox.removeAllItems();
		for (Country c : p.getCountriesOwned()) {
			countriesComboBox.addItem(c.getCountryName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}


}
