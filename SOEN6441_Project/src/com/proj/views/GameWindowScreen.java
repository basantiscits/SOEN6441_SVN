package com.proj.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import com.proj.controllers.GameController;
import com.proj.models.Card;
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.models.PlayerType;
import com.proj.utilites.Constants;

/**
 * Game Window Screen class
 * @author Kirti
 * @since 10 Feb 2019
 * @version 1.0
 */
public class GameWindowScreen extends JFrame implements ActionListener,Observer, WindowListener {
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
	private JPanel contientLabelViewPanel;
	private JPanel countryLabelViewPanel;
	private JPanel playerDominationPanel;
	private JLabel playerDominationLabel;
	private JPanel tableHeader;
	private JLabel tableHeaderLabel;
	private JPanel allocationPanel;
	private JTextArea textArea;
	private List<String> countries = new ArrayList<String>();
	private JTable playerStrength;
	private String data[][];
	private String countryColumn[];
	private JTable tableMatrix;
	private JScrollPane scrollPane;
	private JLabel currentPlayerName;
	private JComboBox countriesComboBox;
	private JButton armyAllocation;
	private JLabel armiesAvailable;
	private GameController gameController;
	private JPanel cardExchangePanel;
	private JFrame cardExchangeFrame;
	private JLabel exchangeLabel;
	private JButton exchangeButton;
	private DefaultListModel<String> list;
	private String cardType;
	
	private List<String> cardsSelected;
	private JList<String> listOfCards;
	private JLabel cardViewLabel;
	
	private JPanel progressBarPanel;
	private JProgressBar progressBar;
	
	
	private JOptionPane exchangePane;
	private GameModelCreation gameModel;
	private JButton exchangeButt;
	private JFrame viewCardFrame;
	private JLabel noOfCardsLabel;
	/**
	 * Game Window Screen constructor
	 * @param gameMap Object of Map class
	 * @param player Array object of Player class 
	 * @param gameModel Object of GameModelCreation class
	 */
	public GameWindowScreen(GameModelCreation gameModel) {
		super("Game Window");
		this.gameModel = gameModel;
		for (Player p : gameModel.getPlayer()) {
			p.addObserver(this);
			for(Country c :p.getCountriesOwned()) {
				c.addObserver(this);
			}
		}
		
		gameController = new GameController(this, gameModel);
		gameController.getGameModel().addObserver(this);
		
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
		contientLabelViewPanel = new JPanel();
		continentLabel = new JLabel("Continent Tree");
		Dimension continentSize = continentLabel.getPreferredSize();
		continentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		continentLabel.setBounds(140, 50, continentSize.width + 500, continentSize.height);
		contientLabelViewPanel.setBounds(10, 35, frameSize.width - 950, 35);
		add(contientLabelViewPanel);
		contientLabelViewPanel.setBackground(Color.lightGray);
		contientLabelViewPanel.setLayout(new FlowLayout());
		continentLabel.setFont(new Font("dialog", 1, 15));
		contientLabelViewPanel.setBorder(blackline);
		contientLabelViewPanel.add(continentLabel);
		treeScrollPane = new JScrollPane(mapTree);
		treeScrollPane.setBounds(10, 70, frameSize.width - 950, frameSize.height - 800);
		countryLabelViewPanel = new JPanel();
		countriesLabel = new JLabel("Countries Matrix");
		Dimension countriesSize = countriesLabel.getPreferredSize();
		countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		countriesLabel.setBounds(240, 50, countriesSize.width + 500, countriesSize.height);
		countryLabelViewPanel.setBounds(contientLabelViewPanel.getBounds().x + (int) (contientLabelViewPanel.getBounds().getWidth()), 35,frameSize.width - 300, 35);
		add(countryLabelViewPanel);
		countryLabelViewPanel.setBackground(Color.lightGray);
		countryLabelViewPanel.setLayout(new FlowLayout());
		countriesLabel.setFont(new Font("dialog", 1, 15));
		countryLabelViewPanel.setBorder(blackline);
		countryLabelViewPanel.add(countriesLabel);
		scrollPane = new JScrollPane(tableMatrix);
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
		startUpScrollPane.setBounds(10,startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight()) + 5,400, frameSize.height - 900);
		
		playerDominationPanel = new JPanel();
		playerDominationPanel.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight()) + 5, frameSize.width - 450, 25);
		add(playerDominationPanel);
		playerDominationPanel.setBackground(Color.lightGray);
		playerDominationPanel.setLayout(new FlowLayout());
		
		playerDominationLabel = new JLabel("Players "+gameModel.getMapDetails().getName()+ " Domination View");
		playerDominationLabel.setFont(new Font("dialog", 1, 11));
		playerDominationPanel.setBorder(blackline);
		playerDominationPanel.add(playerDominationLabel);
		
		
		tableHeader = new JPanel();
		tableHeader.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),playerDominationPanel.getBounds().y + (int) (playerDominationPanel.getBounds().getHeight())+5, 320, 25);

		tableHeaderLabel = new JLabel("Map contains " + gameModel.getMapDetails().listOfContinentNames().size()+" Continents and  "+ gameModel.getMapDetails().listOfCountryNames().size()+" Countries");
		tableHeaderLabel.setFont(new Font("dialog", 1, 15));
		tableHeader.add(tableHeaderLabel);
		
		
		tableHeaderLabel = new JLabel("Total Countries " + gameModel.getMapDetails().listOfCountryNames().size());
		tableHeaderLabel.setFont(new Font("dialog", 1, 15));
		tableHeaderLabel.setBorder(blackline);
		tableHeader.add(tableHeaderLabel);

		strengthPane = new JScrollPane(playerStrength);
		strengthPane.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),tableHeader.getBounds().y + (int) (tableHeader.getBounds().getHeight()), (int) (500),frameSize.height - 955);
		
		progressBarPanel = new JPanel();
		progressBarPanel.setBounds((strengthPane.getBounds().x + (int) (strengthPane.getBounds().getWidth())),tableHeader.getBounds().y + (int) (tableHeader.getBounds().getHeight()), (int) (200),frameSize.height - 500);
		
		currentPlayerName = new JLabel(gameModel.getCurrPlayer().getPlayerName());
		currentPlayerName.setBounds(50, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,100, 30);
		add(currentPlayerName);

		countriesComboBox = new JComboBox();
		countriesComboBox.setBounds(150, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,150, 30);
		add(countriesComboBox);
		addCountriesToBox(gameModel.getCurrPlayer());
		
		
		cardExchangePanel = new JPanel();
		add(cardExchangePanel);
		cardExchangeFrame = new JFrame("Card Exchange View");

		listOfCards = new JList<String>();
		
		cardExchangeFrame.add(cardExchangePanel);
		
		cardExchangeFrame.setTitle("Exchange Of Cards");
		cardExchangeFrame.setResizable(false);
		cardExchangeFrame.setSize(Constants.WIDTH-250, Constants.HEIGHT);
		cardExchangeFrame.setLayout(null);
		cardExchangeFrame.setLocationRelativeTo(listOfCards);
		cardExchangeFrame.addWindowListener(this);
		
		exchangePane = new JOptionPane();
		
		exchangeButton = new JButton("Exchange");
		exchangeButton.setBounds(200,150,100,50);
		exchangeButt = new JButton("Cards");
		exchangeButt.setBounds(400, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 80,100, 30);
		exchangeButt.addActionListener(gameController);
		add(exchangeButt);
		
		
		
		viewCardFrame = new JFrame();
		viewCardFrame.setResizable(false);
		viewCardFrame.setSize(Constants.WIDTH-400, Constants.HEIGHT);
		viewCardFrame.setLayout(null);
		viewCardFrame.setLocationRelativeTo(listOfCards);

		
		exchangeButton.addActionListener(this);
		

		armyAllocation = new JButton("Place Army");
		armyAllocation.setBounds(400, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,100, 30);
		armyAllocation.addActionListener(gameController);
		add(armyAllocation);

		armiesAvailable = new JLabel("Number of Armies Available:" + String.valueOf(gameModel.getCurrPlayer().getNoOfArmiesOwned()));
		armiesAvailable.setBounds(600, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 20,200, 30);
		
		noOfCardsLabel = new JLabel("No of Cards Available: " + gameModel.getCurrPlayer().getNoOfCardsOwned() );
		noOfCardsLabel.setBounds(600, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight()) + 80,200, 30);
		add(noOfCardsLabel);
		
		add(armiesAvailable);
		add(scrollPane);
		add(treeScrollPane);
		add(startUpScrollPane);
		add(tableHeader);
		add(strengthPane);
		add(progressBarPanel);
		countriesMatrix();
		createTree();
		createStartUpTree();
		playerStrengthTable(gameController.getGameModel());
		addProgressBar(gameController.getGameModel());
		
		gameModel.incrementTurn();
		displayPlayer();
	}

	/**
	 * Reinforcement phase
	 */
	public void reinforce() {
		if (gameModel.getCurrPlayer().getNoOfArmiesOwned() == 0 && !gameModel.getCurrPlayer().getPlayerName().equals("Neutral")) {
			displayPlayer();
			gameModel.getCurrPlayer().attackPhaseImplementation(gameModel,this);	
		}
	}

	/**
	 * Displays number of armies available
	 */
	public void displayPlayer() {
		
		if(gameModel.getGameState() == 10) {
			JOptionPane.showMessageDialog(null, gameModel.getCurrPlayer().getPlayerName() + " has Won the Game !! Congratulations !!!");
			dispose();
		}
		
		else if ((gameModel.getGameState() == 0) && gameModel.getCurrPlayer().getPlayerType() != PlayerType.Human) {
			if ( gameModel.getCurrPlayer().getNoOfArmiesOwned() > 0) {
				gameModel.getCurrPlayer().initialArmyAllocation(gameModel);
			}

		}
		
		else if((gameModel.getGameState()==1) && (gameModel.getCurrPlayer().getPlayerType()!=PlayerType.Human)) {
			gameModel.getCurrPlayer().intializeReinforcementArmies(gameModel);
		}
		
		
		if(gameModel.getCurrPlayer().getPlayerType() == PlayerType.Human) {
			addPlayerName(gameModel.getCurrPlayer().getPlayerName());
			addCountriesToBox(gameModel.getCurrPlayer());
			armiesAvailable.setText("Number of Armies Available:" + String.valueOf(gameModel.getCurrPlayer().getNoOfArmiesOwned()));
			noOfCardsLabel.setText("No of Cards Available: " + gameModel.getCurrPlayer().getNoOfCardsOwned());
		}
		
		if(gameModel.getGameState()==0) {
			getStartPhaseDefinedLabel().setText("StartUp Phase");
		}
		else if(gameModel.getGameState()==1) {
			getStartPhaseDefinedLabel().setText("Reinforcement Phase");
		}
		else if(gameModel.getGameState()==2) {
			getStartPhaseDefinedLabel().setText("Attack Phase");
		}
		else if(gameModel.getGameState()==3) {
			getStartPhaseDefinedLabel().setText("Fortification Phase");
		}

	}

	/**
	 * The countries matrix
	 */
	public void countriesMatrix() {
		System.out.println("inside countriesMAtrix");
		countries = gameModel.getMapDetails().listOfCountryNames();
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

		tableMatrix = new JTable(dtm) {
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
		tableMatrix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.getViewport().removeAll();
		scrollPane.getViewport().add(tableMatrix);
		for (Continent currentContinent : gameModel.getMapDetails().getContinents()) {
			for (Country currentCountry : currentContinent.getCountriesPresent()) {
				int row_length = data.length;
				int column_length = data[0].length;
				for (i = 0; i < row_length; i++) {
					if (currentCountry.getCountryName().equalsIgnoreCase(data[i][0])) {
						for (j = 1; j < column_length; j++) {
							if (!currentCountry.getListOfNeighbours().contains(countryColumn[j])) {
								tableMatrix.setValueAt("N", i, j);
							}
							else {
								tableMatrix.setValueAt("Y", i, j);
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
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - " + gameModel.getMapDetails().getName() + "");
		for (Continent continent : gameModel.getMapDetails().getContinents()) {
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
	 * @return game map
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
	 * @param startUpScrollPane Object of JScrollPane class
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
	 * @param strengthPane Object of JScrollPane class
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
	 * @param userSelTreeNode User selected tree node
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
	 * @param startPhaseViewPanel Object of JPanel class
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
	 * @param startPhaseDefinedLabel Object of JLabel class
	 */
	public void setStartPhaseDefinedLabel(JLabel startPhaseDefinedLabel) {
		this.startPhaseDefinedLabel = startPhaseDefinedLabel;
	}
	
	/**
	 * getter for Player Allocated Label
	 * @return the playerAllocatedLabel
	 */
	public JLabel getPlayerAllocatedLabel() {
		return playerAllocatedLabel;
	}

	/**
	 * setter for Player Allocated Label
	 * @param playerAllocatedLabel the playerAllocatedLabel to set
	 */
	public void setPlayerAllocatedLabel(JLabel playerAllocatedLabel) {
		this.playerAllocatedLabel = playerAllocatedLabel;
	}

	/**
	 * getter for Dynamic Area start Phase Panel
	 * @return the dynamicAreastartPhasePanel
	 */
	public JPanel getDynamicAreastartPhasePanel() {
		return dynamicAreastartPhasePanel;
	}

	/**
	 * setter for Dynamic Area start Phase Panel
	 * @param dynamicAreastartPhasePanel the dynamicAreastartPhasePanel to set
	 */
	public void setDynamicAreastartPhasePanel(JPanel dynamicAreastartPhasePanel) {
		this.dynamicAreastartPhasePanel = dynamicAreastartPhasePanel;
	}

	/**
	 * getter for Random Player Phase View Panel
	 * @return the randomPlayerPhaseViewPanel
	 */
	public JPanel getRandomPlayerPhaseViewPanel() {
		return randomPlayerPhaseViewPanel;
	}

	/**
	 * setter for Random Player Phase View Panel
	 * @param randomPlayerPhaseViewPanel the randomPlayerPhaseViewPanel to set
	 */
	public void setRandomPlayerPhaseViewPanel(JPanel randomPlayerPhaseViewPanel) {
		this.randomPlayerPhaseViewPanel = randomPlayerPhaseViewPanel;
	}

	/**
	 * getter for Dynamic Area Player Phase Panel
	 * @return the dynamicAreaPlayerPhasePanel
	 */
	public JPanel getDynamicAreaPlayerPhasePanel() {
		return dynamicAreaPlayerPhasePanel;
	}

	/**
	 * setter for Dynamic Area Player Phase Panel
	 * @param dynamicAreaPlayerPhasePanel the dynamicAreaPlayerPhasePanel to set
	 */
	public void setDynamicAreaPlayerPhasePanel(JPanel dynamicAreaPlayerPhasePanel) {
		this.dynamicAreaPlayerPhasePanel = dynamicAreaPlayerPhasePanel;
	}

	/**
	 * getter for Dynamic Area Player Phase Panel
	 * @return the contientLabelViewPanel
	 */
	public JPanel getContientLabelViewPanel() {
		return contientLabelViewPanel;
	}

	/**
	 * setter for Continent Label View Panel
	 * @param contientLabelViewPanel the contientLabelViewPanel to set
	 */
	public void setContientLabelViewPanel(JPanel contientLabelViewPanel) {
		contientLabelViewPanel = contientLabelViewPanel;
	}

	/**
	 * getter for Country Label View Panel
	 * @return the countryLabelViewPanel
	 */
	public JPanel getCountryLabelViewPanel() {
		return countryLabelViewPanel;
	}

	/**
	 * setter for Country Label View Panel
	 * @param countryLabelViewPanel the countryLabelViewPanel to set
	 */
	public void setCountryLabelViewPanel(JPanel countryLabelViewPanel) {
		countryLabelViewPanel = countryLabelViewPanel;
	}

	/**
	 * getter for Table Header
	 * @return the tableHeader
	 */
	public JPanel getTableHeader() {
		return tableHeader;
	}

	/**
	 * setter for Table Header
	 * @param tableHeader the tableHeader to set
	 */
	public void setTableHeader(JPanel tableHeader) {
		this.tableHeader = tableHeader;
	}

	/**
	 * getter for Table Header Label
	 * @return the tableHeaderLabel
	 */
	public JLabel getTableHeaderLabel() {
		return tableHeaderLabel;
	}

	/**
	 * setter for Table Header Label
	 * @param tableHeaderLabel the tableHeaderLabel to set
	 */
	public void setTableHeaderLabel(JLabel tableHeaderLabel) {
		this.tableHeaderLabel = tableHeaderLabel;
	}

	/**
	 * getter Allocation Panel
	 * @return the allocationPanel
	 */
	public JPanel getAllocationPanel() {
		return allocationPanel;
	}

	/**
	 * setter for Allocation Panel
	 * @param allocationPanel the allocationPanel to set
	 */
	public void setAllocationPanel(JPanel allocationPanel) {
		this.allocationPanel = allocationPanel;
	}

	/**
	 * getter for TextArea
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * setter for TextArea
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * getter for countries
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}

	/**
	 * setter for countries
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	/**
	 * getter for Player Strength
	 * @return the playerStrength
	 */
	public JTable getPlayerStrength() {
		return playerStrength;
	}

	/**
	 * setter for Player Strength
	 * @param playerStrength the playerStrength to set
	 */
	public void setPlayerStrength(JTable playerStrength) {
		this.playerStrength = playerStrength;
	}

	/**
	 * getter for Data matrix
	 * @return the data
	 */
	public String[][] getData() {
		return data;
	}

	/**
	 * setter for data matrix
	 * @param data the data to set
	 */
	public void setData(String[][] data) {
		this.data = data;
	}

	/**
	 * getter for country column
	 * @return the countryColumn
	 */
	public String[] getCountryColumn() {
		return countryColumn;
	}

	/**
	 * setter for country column
	 * @param countryColumn the countryColumn to set
	 */
	public void setCountryColumn(String[] countryColumn) {
		this.countryColumn = countryColumn;
	}

	/**
	 * getter for table matrix
	 * @return the tableMatrix
	 */
	public JTable getTableMatrix() {
		return tableMatrix;
	}

	/**
	 * setter for table matrix
	 * @param tableMatrix the table matrix to set
	 */
	public void setTablematrix(JTable tableMatrix) {
		this.tableMatrix = tableMatrix;
	}

	/**
	 * getter for scroll pane
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * setter for scroll pane
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * getter for current player name
	 * @return the currentPlayerName
	 */
	public JLabel getCurrentPlayerName() {
		return currentPlayerName;
	}

	/**
	 * setter for current player name
	 * @param currentPlayerName the currentPlayerName to set
	 */
	public void setCurrentPlayerName(JLabel currentPlayerName) {
		this.currentPlayerName = currentPlayerName;
	}

	/**
	 * getter for Countries ComboBox
	 * @return the countriesComboBox
	 */
	public JComboBox getCountriesComboBox() {
		return countriesComboBox;
	}

	/**
	 * setter for Countries ComboBox
	 * @param countriesComboBox the countriesComboBox to set
	 */
	public void setCountriesComboBox(JComboBox countriesComboBox) {
		this.countriesComboBox = countriesComboBox;
	}

	/**
	 * getter for Army Allocation 
	 * @return the armyAllocation
	 */
	public JButton getArmyAllocation() {
		return armyAllocation;
	}

	/**
	 * setter for Army Allocation
	 * @param armyAllocation the armyAllocation to set
	 */
	public void setArmyAllocation(JButton armyAllocation) {
		this.armyAllocation = armyAllocation;
	}

	/**
	 * getter for Armies Available
	 * @return the armiesAvailable
	 */
	public JLabel getArmiesAvailable() {
		return armiesAvailable;
	}

	/**
	 * setter for Armies Available
	 * @param armiesAvailable the armiesAvailable to set
	 */
	public void setArmiesAvailable(JLabel armiesAvailable) {
		this.armiesAvailable = armiesAvailable;
	}

	/**
	 * getter for Game Controller
	 * @return the gameController
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * setter Game Controller  
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
		for (Player P : gameModel.getPlayer()) {
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
	 * @param gameModel Object of GameModelCreation class
	 */
	public void playerStrengthTable(GameModelCreation gameModel) {
		String countrySize = "Countries";
		String continentSize = "Continents";
		String availableArmiesSize = "Available Armies";
		String allocatedArmiesSize = "Allocated Armies";
		String[] column = { "Players", countrySize, continentSize, availableArmiesSize, allocatedArmiesSize };
		String[][] data = new String[gameModel.getPlayer().length][column.length];
		for (int i = 0; i < gameModel.getPlayer().length; i++) {
			for (int j = 0; j < column.length; j++) {
				if (j == 0) {
					data[i][j] = gameModel.getPlayer()[i].getPlayerName();
				} 
				else if(j==1){
					data[i][j] = String.valueOf(gameModel.getPlayer()[i].getCountriesOwned().size()+"  ("+ (int) (((double) gameModel.getPlayer()[i].getCountriesOwned().size()/gameModel.getMapDetails().listOfCountryNames().size()) * 100)+"%)");
				}
				else if(j==2){
					data[i][j] = String.valueOf(gameModel.getPlayer()[i].getContinentsOwned().size());
				}
				else if(j==3){
					data[i][j] = String.valueOf(gameModel.getPlayer()[i].getNoOfArmiesOwned());
				}
				else if(j==4){
					data[i][j] = String.valueOf(gameModel.armiesAllocated(gameModel.getPlayer()[i]));
				}
			}
		}
		playerStrength = new JTable(data, column);
		strengthPane.getViewport().add(playerStrength);
	}
	
	/**
	 * This method controls whether to close card exchange window when user clicks on close
	 */
	public void doOnClose() {
		
		String[] button = new String[] {"Yes", "No", "Cancel"};
		String defaultOption = button[0];
			
		int i = JOptionPane.showOptionDialog(cardExchangeFrame,
				"\n" +
						"Are you sure you want to Close?",
						"Warning",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, button, defaultOption);
		
		if(i==JOptionPane.YES_OPTION) {
			
			if(gameModel.getCurrPlayer().getNoOfCardsOwned()<5) {
				
				cardExchangeFrame.dispose();
			}
			else if(gameModel.getCurrPlayer().getNoOfCardsOwned()>=5){
				JOptionPane.showMessageDialog(cardExchangeFrame, "Available Cards more than 5. Please exchange !");
				
			}	
		}
		else if(i==JOptionPane.NO_OPTION) {
			
			
		
			
		}
			
	}
	
	/**
	 * This method asks user If a user wants to exchange cards and displays card exchange frame
	 */
	public void cardExchangeView() {	
		
		cardExchangeFrame.setTitle(gameModel.getCurrPlayer().getPlayerName());
		
		
		if(gameModel.getCurrPlayer().getNoOfCardsOwned()< 5 && gameModel.getCurrPlayer().getNoOfCardsOwned() >=3) {
			int r=JOptionPane.showConfirmDialog(this,"Do you want to exchange Cards"); 
			if(r==JOptionPane.YES_OPTION) {  
			     cardExchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			     exchangeButton.setEnabled(true);
			 	 
			 	 doExchangeCardsNow();
			 } 
		}
		else if(gameModel.getCurrPlayer().getNoOfCardsOwned()<3) {}

		else {
			cardExchangeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
			exchangeButton.setEnabled(true);
		    doExchangeCardsNow();
		    
			if(gameModel.getCurrPlayer().getNoOfCardsOwned()<5) cardExchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    
		    
		}
	}
	
	
	/**
	 * This method controls whether user has selected cards of same or different type or not
	 */
	public void doExchangeCardsNow() {
		
		if(gameModel.getCurrPlayer().getNoOfCardsOwned()<5)
			cardExchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		cardsSelected = new ArrayList<String>();
	
		exchangeLabel = new JLabel();
		exchangeLabel.setText("Please Exchange 3 cards of same or different Type");
		exchangeLabel.setSize(500,100);
		displayCards();
		listOfCards.setBounds(100,100, 75,150); 
		
		 cardExchangeFrame.add(listOfCards);
		 cardExchangeFrame.add(exchangeLabel);
		 cardExchangeFrame.add(exchangeButton);
		 cardExchangeFrame.setVisible(true);
		 System.out.println("Again outside action performed");
		 
		 
		
	}
	
	
	/**
	 * This method displays all the available cards as to a particular user
	 */
	public void viewAvailableCards()
	{
		
		cardViewLabel = new JLabel();
		cardViewLabel.setText("Cards Available :");
		cardViewLabel.setLocation(70,10);
		cardViewLabel.setSize(500,100);
		displayCards();
		listOfCards.setBounds(80,100, 75,150); 
		if(gameModel.getCurrPlayer().getNoOfCardsOwned()<5) cardExchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewCardFrame.setTitle("View Cards");
		
		viewCardFrame.add(listOfCards);
		 viewCardFrame.add(cardViewLabel);
		 viewCardFrame.setVisible(true);
		
		
		
	}
	
	/**
	 * This method listens to exchange button events
	 * @param e Event corresponding to exchange button
	 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Again action performed");
				 
				if(gameModel.getCurrPlayer().getNoOfCardsOwned()<5) cardExchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				
				System.out.println("Before Cards:- "+ gameModel.getCurrPlayer().getNoOfCardsOwned());
				System.out.println("Before Armies:- "+ gameModel.getCurrPlayer().getNoOfArmiesOwned());
			
				if(listOfCards.getSelectedIndex()!=-1 && listOfCards.getSelectedIndices().length==3)
				{
					cardsSelected = listOfCards.getSelectedValuesList();
					
					Set<String> setOfCards = new HashSet<String>(cardsSelected);
					if(setOfCards.size()==1 || setOfCards.size()==cardsSelected.size())
					{
						gameModel.getCurrPlayer().setCardsForArmies(gameModel.getCurrPlayer().getCardsForArmies() + 5);
						gameModel.getCurrPlayer().setNoOfArmiesOwned(gameModel.getCurrPlayer().getNoOfArmiesOwned() + gameModel.getCurrPlayer().getCardsForArmies());
						
						for(String cardType: cardsSelected) {
							
							for(Card card: gameModel.getCurrPlayer().getCardsOwned())
							{
								if(card.getTypeOfCard().toString().equals(cardType)) {
									gameModel.getCurrPlayer().getCardsOwned().remove(card);
									gameModel.getCurrPlayer().setNoOfCardsOwned(gameModel.getCurrPlayer().getNoOfCardsOwned()-1);
									break;
								}
							}
							
						}
						System.out.println("After Cards:- "+ gameModel.getCurrPlayer().getNoOfCardsOwned());
						System.out.println("After Armies:- "+gameModel.getCurrPlayer().getNoOfArmiesOwned());
						JOptionPane.showMessageDialog(cardExchangeFrame,"Successfully Exchanged."); 
						displayCards();
				
					}
					else {
						
							JOptionPane.showMessageDialog(cardExchangeFrame,"Cards should of same or different type","Alert", JOptionPane.WARNING_MESSAGE); 
					}
					
				}
				else {
					JOptionPane.showMessageDialog(cardExchangeFrame,"Please select exactly three cards","Alert",JOptionPane.WARNING_MESSAGE); 
				}
				
			}
			
			
			/**
			 * This method displays all cards in the card exchange frame as a JList
			 */
	public void displayCards()
	{	
		list = null;
		
		
		list = new DefaultListModel<String>();  
		System.out.println("Total cards of player 1 is " + gameModel.getCurrPlayer().getNoOfCardsOwned());
		for(Card cards: gameModel.getCurrPlayer().getCardsOwned())
		{
			
			cardType = cards.getTypeOfCard().toString();
			if(cardType.equals("INFANTRY")) {			
				list.addElement("INFANTRY");			
			}
			else if(cardType.equals("CAVALRY")) {
				list.addElement("CAVALRY");				
			}
			else {
				list.addElement("ARTILLERY");				
			}
			
		}
		System.out.println("List is: " + list.size());
		listOfCards.setModel(list);
		

	}

	/**
	 * This method assign name to player
	 * @param name name of player 
	 */
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


	/**
	 * Update method
	 * @param type Object of Observable
	 * @param object calling observer
	 */
	@Override
	public void update(Observable type, Object object) {
		if(object instanceof GameModelCreation) {
			playerStrengthTable(gameController.getGameModel());
			addProgressBar(gameController.getGameModel());
			displayPlayer();
			createStartUpTree();
		}
		else {
			playerStrengthTable(gameController.getGameModel());
			addProgressBar(gameController.getGameModel());
			createStartUpTree();
		}
		
	}
	
	/**
	 * add progress bar
	 * @param gameModel Object of GameModelCreation class 
	 */
	private void addProgressBar(GameModelCreation gameModel) {

		noOfCardsLabel.setText("No of Cards Available: " + gameModel.getCurrPlayer().getNoOfCardsOwned());
		progressBarPanel.removeAll();
		Color color1 = new Color(23, 54, 135);
		Color color2 = new Color(32, 198, 42);
		Color color3 = new Color(88, 43, 97);
		Color color4 = new Color(67, 89, 67);
		Color color5 = new Color(11, 78, 80);
		
		Player[] players = gameModel.getPlayer();
		for (int i = 0; i < players.length; i++) {
			progressBar = new JProgressBar();
			int value = (int) (((double) players[i].getCountriesOwned().size()/gameModel.getMapDetails().listOfCountryNames().size()) * 100);

			progressBar.setValue(value);

			progressBar.setStringPainted(true);
			if (i == 0) {
				progressBar.setForeground(color1);
			}
			else if (i == 1) {
				progressBar.setForeground(color2);
			}
			else if (i == 2) {
				progressBar.setForeground(color3);
			}
			else if (i == 3) {
				progressBar.setForeground(color4);
			}
			else if (i == 4) {
				progressBar.setForeground(color5);
			}
			Border border = BorderFactory.createTitledBorder(players[i].getPlayerName());
			progressBar.setBorder(border);
			progressBarPanel.add(progressBar, BorderLayout.NORTH);
		
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	
		doOnClose();
 
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
		
		
}
