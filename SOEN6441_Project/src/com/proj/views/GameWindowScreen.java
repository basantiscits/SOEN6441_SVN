package com.proj.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

public class GameWindowScreen extends JFrame implements ActionListener{
	private int currentPlayer = 0;
	public Player[] player;
	private JLabel countriesLabel;
	private JLabel continentLabel;
	private ContinentArea continentArea;
	// private ToolBar toolBar;
	public Map gameMap;
	// public com.proj.controllers.MapEditor mapEditorController;
	private JTree mapTree,startUpTree;
	private JTree playerAllocationCountry;
	private JScrollPane treeScrollPane,startUpScrollPane,strengthPane;
	private String userSelTreeNode;
	private JPanel startPhaseViewPanel;
	public JLabel StartPhaseDefinedLabel;
	public JLabel PlayerAllocatedLabel;
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
	
	
	public GameWindowScreen(Map gameMap, Player[] player) {
		// TODO Auto-generated method stub

		super("Game Window");
		this.gameMap = gameMap;
		this.player = player;
		
		gameController = new GameController(this, gameMap);

		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		//setMinimumSize(new Dimension(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT));
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// mapEditorController= new com.proj.controllers.MapEditor(this);

		addComponents();
	}
	
	
	
	

	public void addComponents() {
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);

		Dimension frameSize = this.getSize();
		// ---------------------------------------------------------------

		// toolBar = new ToolBar(mapEditorController);
		// toolBar.setBounds(0, 0, frameSize.width, 40);
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
		// ---------------------------------------------------------------
		CountryLabelViewPanel = new JPanel();
		countriesLabel = new JLabel("Countries Matrix");
		Dimension countriesSize = countriesLabel.getPreferredSize();
		countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		// System.out.println("yes : "+treeScrollPane.getBounds().x );
		countriesLabel.setBounds(240, 50, countriesSize.width + 500, countriesSize.height);
		CountryLabelViewPanel.setBounds(
				ContientLabelViewPanel.getBounds().x + (int) (ContientLabelViewPanel.getBounds().getWidth()), 35,
				frameSize.width - 300, 35);
		add(CountryLabelViewPanel);
		CountryLabelViewPanel.setBackground(Color.lightGray);
		CountryLabelViewPanel.setLayout(new FlowLayout());
		countriesLabel.setFont(new Font("dialog", 1, 15));
		CountryLabelViewPanel.setBorder(blackline);
		CountryLabelViewPanel.add(countriesLabel);

		scrollPane = new JScrollPane(tablematrix);

		scrollPane.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()), 70,
				frameSize.width - 300, frameSize.height - 800);

		// ---------------------------------------------------------------

		startPhaseViewPanel = new JPanel();
		startPhaseViewPanel.setBounds(10,treeScrollPane.getBounds().y + (int) (treeScrollPane.getBounds().getHeight()+10), frameSize.width-50,35);
		add(startPhaseViewPanel);
		startPhaseViewPanel.setBackground(Color.lightGray);
		startPhaseViewPanel.setLayout(new FlowLayout());

		StartPhaseDefinedLabel = new JLabel("StartUp Phase");
		StartPhaseDefinedLabel.setFont(new Font("dialog", 1, 15));
		startPhaseViewPanel.setBorder(blackline);
		startPhaseViewPanel.add(StartPhaseDefinedLabel);


		// ---------------------------------------------------------------

		startUpScrollPane = new JScrollPane(startUpTree);
		startUpScrollPane.setBounds(10, startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight())+5, 635 + (int) (200), frameSize.height - 900);
		

		// ---------------------------------------------------------------
		tableHeader = new JPanel();
		tableHeader.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()), startPhaseViewPanel.getBounds().y + (int) (startPhaseViewPanel.getBounds().getHeight())+5, 320, 25);
		
		tableHeaderLabel = new JLabel("Total Countries "+gameMap.listOfCountryNames().size());
		tableHeaderLabel.setFont(new Font("dialog", 1, 15));
		tableHeaderLabel.setBorder(blackline);
		tableHeader.add(tableHeaderLabel);
		// -----------------------------------------------------------------
		strengthPane = new JScrollPane(playerStrength);
		strengthPane.setBounds(startUpScrollPane.getBounds().x + (int) (startUpScrollPane.getBounds().getWidth()),tableHeader.getBounds().y + (int) (tableHeader.getBounds().getHeight()) ,(int) (320), frameSize.height - 925);

		
		
		// ---------------------------------------------------------------
		
		currentPlayerName = new JLabel(player[currentPlayer].getPlayerName());
		currentPlayerName.setBounds(150, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight())+20, 100, 30);
		add(currentPlayerName);
		
		countriesComboBox = new JComboBox();
		countriesComboBox.setBounds(350, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight())+20, 150, 30);
		add(countriesComboBox);
		addCountriesToBox(player[currentPlayer]);
		
		armyAllocation = new JButton("Place Army");
		armyAllocation.setBounds(650, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight())+20, 100, 30);
		armyAllocation.addActionListener(gameController);
		add(armyAllocation);
		
		armiesAvailable = new JLabel("Number of Armies Available:"+String.valueOf(player[currentPlayer].getNoOfArmiesOwned()));
		armiesAvailable.setBounds(850, strengthPane.getBounds().y + (int) (strengthPane.getBounds().getHeight())+20, 200, 30);
		add(armiesAvailable);
		
		
		// ---------------------------------------------------------------


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
	
	/*
	 * @author Ofreish
	 */
//	public void start() {
//		if(currentPlayer==(player.length-1)) {
//			currentPlayer = 0;
//		}
//		else {
//			currentPlayer++;
//		}
//	}
//	
	/*
	 * @author Ofreish
	 */
	public void reinforce() {
		if(player[currentPlayer].getNoOfArmiesOwned()==0) {
			FortificationView FV = new FortificationView(gameMap, player,currentPlayer,this);
			FV.setVisible(true);
			currentPlayer++;
			if(currentPlayer == player.length) {
				currentPlayer--;
				armyAllocation.setEnabled(false);
			}
			
		}
	}
	
	/*
	 * @author Ofreish
	 */
	public void displayPhase() {
		
		addPlayerName(player[currentPlayer].getPlayerName());
		addCountriesToBox(player[currentPlayer]);
		armiesAvailable.setText("Number of Armies Available:"+String.valueOf(player[currentPlayer].getNoOfArmiesOwned()));

	}
	
	
	/*
	 * @author Ofreish
	 */
//	public void startReinforcementPhase() {
//		if(player[currentPlayer].getNoOfArmiesOwned()==0) {
//			StartPhaseDefinedLabel.setText("Reinforcement Phase");
//			intializeReinforcementArmies();
//			
//			armyAllocation.setText("Reinforcement Phase");
//		}
//	}
//	
	/*
	 * @author Ofreish
	 */
//	public void intializeReinforcementArmies() {
//		
//		for(int i=0;i<player.length;i++) {
//			long armies =  Math.round(Math.floor(player[i].getCountriesOwned().size()/3));
//			if(armies>3) {
//				player[i].incrementNoOfArmiesOwned((int)armies);
//			}
//			else {
//				player[i].incrementNoOfArmiesOwned(3);
//			}
//			
//			updateContinentsOwned(i);
//		}
//	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @author Ofreish
	 */
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		String button = e.getActionCommand();
//		switch(button) {
//			case "Place Army":
//				updateGame((String)countriesComboBox.getSelectedItem());
//				start();
//				displayPhase();
//				if(checkStartUpEnd()) {
//					currentPlayer = 0;
//					armyAllocation.setText("Phase Change");
//					armyAllocation.doClick();
//				}
//				break;
//			case "Reinforcement Phase":
//				updateGame((String)countriesComboBox.getSelectedItem());
//				reinforce();
//				displayPhase();
//				break;
//			case "Phase Change":
//				StartPhaseDefinedLabel.setText("Reinforcement Phase");
//				armyAllocation.setText("Reinforcement Phase");
//				intializeReinforcementArmies();
//				displayPhase();
//				break;
//		}
//
//	}
	
//	public boolean checkStartUpEnd() {
//		int flag = 0 ;
//		for(Player p: player) {
//			if(p.getNoOfArmiesOwned()==0) {
//				flag++;
//			}
//		}
//		if(flag==player.length) {
//			return true;
//		}
//		return false;
//	}

	public void countriesMatrix() {
		System.out.println("inside countriesMAtrix");
		countries = gameMap.listOfCountryNames();
		// Country country = new Country();

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
			// Set color of cell to Grey If the value is "N"
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
			        Component component = super.prepareRenderer(renderer, row, col);
			       
			        String value = (String) dtm.getValueAt(row, col);
			       
			            if (value.equals("N")) {
			                component.setBackground(Color.LIGHT_GRAY);
			            }
			            else
			            {
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
							String neighbour = countryColumn[j];
							if (!currentCountry.getListOfNeighbours().contains(countryColumn[j])) {
								tablematrix.setValueAt("N", i, j);
							} else {
								tablematrix.setValueAt("Y", i, j);
							}
						}
					}
				}
			}
		}

	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}





	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}





	public Player[] getPlayer() {
		return player;
	}
	
	public Player getPlayerAtIndex(int index) {
	    return player[index];
	}




	public void setPlayer(Player[] player) {
		this.player = player;
	}





	public JLabel getCountriesLabel() {
		return countriesLabel;
	}





	public void setCountriesLabel(JLabel countriesLabel) {
		this.countriesLabel = countriesLabel;
	}





	public JLabel getContinentLabel() {
		return continentLabel;
	}





	public void setContinentLabel(JLabel continentLabel) {
		this.continentLabel = continentLabel;
	}





	public ContinentArea getContinentArea() {
		return continentArea;
	}





	public void setContinentArea(ContinentArea continentArea) {
		this.continentArea = continentArea;
	}





	public Map getGameMap() {
		return gameMap;
	}





	public void setGameMap(Map gameMap) {
		this.gameMap = gameMap;
	}





	public JTree getMapTree() {
		return mapTree;
	}





	public void setMapTree(JTree mapTree) {
		this.mapTree = mapTree;
	}





	public JTree getStartUpTree() {
		return startUpTree;
	}





	public void setStartUpTree(JTree startUpTree) {
		this.startUpTree = startUpTree;
	}





	public JTree getPlayerAllocationCountry() {
		return playerAllocationCountry;
	}





	public void setPlayerAllocationCountry(JTree playerAllocationCountry) {
		this.playerAllocationCountry = playerAllocationCountry;
	}





	public JScrollPane getTreeScrollPane() {
		return treeScrollPane;
	}





	public void setTreeScrollPane(JScrollPane treeScrollPane) {
		this.treeScrollPane = treeScrollPane;
	}





	public JScrollPane getStartUpScrollPane() {
		return startUpScrollPane;
	}





	public void setStartUpScrollPane(JScrollPane startUpScrollPane) {
		this.startUpScrollPane = startUpScrollPane;
	}





	public JScrollPane getStrengthPane() {
		return strengthPane;
	}





	public void setStrengthPane(JScrollPane strengthPane) {
		this.strengthPane = strengthPane;
	}





	public String getUserSelTreeNode() {
		return userSelTreeNode;
	}





	public void setUserSelTreeNode(String userSelTreeNode) {
		this.userSelTreeNode = userSelTreeNode;
	}





	public JPanel getStartPhaseViewPanel() {
		return startPhaseViewPanel;
	}





	public void setStartPhaseViewPanel(JPanel startPhaseViewPanel) {
		this.startPhaseViewPanel = startPhaseViewPanel;
	}





	public JLabel getStartPhaseDefinedLabel() {
		return StartPhaseDefinedLabel;
	}





	public void setStartPhaseDefinedLabel(JLabel startPhaseDefinedLabel) {
		StartPhaseDefinedLabel = startPhaseDefinedLabel;
	}





	public JLabel getPlayerAllocatedLabel() {
		return PlayerAllocatedLabel;
	}





	public void setPlayerAllocatedLabel(JLabel playerAllocatedLabel) {
		PlayerAllocatedLabel = playerAllocatedLabel;
	}





	public JPanel getDynamicAreastartPhasePanel() {
		return dynamicAreastartPhasePanel;
	}





	public void setDynamicAreastartPhasePanel(JPanel dynamicAreastartPhasePanel) {
		this.dynamicAreastartPhasePanel = dynamicAreastartPhasePanel;
	}





	public JPanel getRandomPlayerPhaseViewPanel() {
		return randomPlayerPhaseViewPanel;
	}





	public void setRandomPlayerPhaseViewPanel(JPanel randomPlayerPhaseViewPanel) {
		this.randomPlayerPhaseViewPanel = randomPlayerPhaseViewPanel;
	}





	public JPanel getDynamicAreaPlayerPhasePanel() {
		return dynamicAreaPlayerPhasePanel;
	}





	public void setDynamicAreaPlayerPhasePanel(JPanel dynamicAreaPlayerPhasePanel) {
		this.dynamicAreaPlayerPhasePanel = dynamicAreaPlayerPhasePanel;
	}





	public JPanel getContientLabelViewPanel() {
		return ContientLabelViewPanel;
	}





	public void setContientLabelViewPanel(JPanel contientLabelViewPanel) {
		ContientLabelViewPanel = contientLabelViewPanel;
	}





	public JPanel getCountryLabelViewPanel() {
		return CountryLabelViewPanel;
	}





	public void setCountryLabelViewPanel(JPanel countryLabelViewPanel) {
		CountryLabelViewPanel = countryLabelViewPanel;
	}





	public JPanel getTableHeader() {
		return tableHeader;
	}





	public void setTableHeader(JPanel tableHeader) {
		this.tableHeader = tableHeader;
	}





	public JLabel getTableHeaderLabel() {
		return tableHeaderLabel;
	}





	public void setTableHeaderLabel(JLabel tableHeaderLabel) {
		this.tableHeaderLabel = tableHeaderLabel;
	}





	public JPanel getAllocationPanel() {
		return allocationPanel;
	}





	public void setAllocationPanel(JPanel allocationPanel) {
		this.allocationPanel = allocationPanel;
	}





	public JTextArea getTextArea() {
		return textArea;
	}





	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}





	public List<String> getCountries() {
		return countries;
	}





	public void setCountries(List<String> countries) {
		this.countries = countries;
	}





	public JTable getPlayerStrength() {
		return playerStrength;
	}





	public void setPlayerStrength(JTable playerStrength) {
		this.playerStrength = playerStrength;
	}





	public String[][] getData() {
		return data;
	}





	public void setData(String[][] data) {
		this.data = data;
	}





	public String[] getCountryColumn() {
		return countryColumn;
	}





	public void setCountryColumn(String[] countryColumn) {
		this.countryColumn = countryColumn;
	}





	public JTable getTablematrix() {
		return tablematrix;
	}





	public void setTablematrix(JTable tablematrix) {
		this.tablematrix = tablematrix;
	}





	public JScrollPane getScrollPane() {
		return scrollPane;
	}





	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}





	public JLabel getCurrentPlayerName() {
		return currentPlayerName;
	}





	public void setCurrentPlayerName(JLabel currentPlayerName) {
		this.currentPlayerName = currentPlayerName;
	}





	public JComboBox getCountriesComboBox() {
		return countriesComboBox;
	}





	public void setCountriesComboBox(JComboBox countriesComboBox) {
		this.countriesComboBox = countriesComboBox;
	}





	public JButton getArmyAllocation() {
		return armyAllocation;
	}





	public void setArmyAllocation(JButton armyAllocation) {
		this.armyAllocation = armyAllocation;
	}





	public JLabel getArmiesAvailable() {
		return armiesAvailable;
	}





	public void setArmiesAvailable(JLabel armiesAvailable) {
		this.armiesAvailable = armiesAvailable;
	}





	public GameController getGameController() {
		return gameController;
	}





	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}





	/*
	 * @author Ofreish 
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
	
	/*
	 * @author Ofreish
	 */
	public void createStartUpTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Initial Allocation");
		
		for(Player P : player) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(P.getPlayerName());
			for(Country C : P.getCountriesOwned()) {
				DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(C.getCountryName()+"["+C.getNoOfArmiesPresent()+"] current armies");
				branch.add(subBranch);
			}
			top.add(branch);
		}
		startUpTree = new JTree(top);
		startUpScrollPane.getViewport().add(startUpTree);
		
		
	}
	
	/*
	 * @author Ofreish
	 */
	public void playerStrengthTable() {
		
		String countrySize = "Number of Countries";
		
		String[] column = {"Players",countrySize};
		String[][] data = new String[player.length][column.length];
		
		
		for(int i=0;i<player.length;i++) {
			for(int j=0;j<column.length;j++) {
				if(j==0) {
					data[i][j] = player[i].getPlayerName();
				}
				else {
					data[i][j] =String.valueOf(player[i].getCountriesOwned().size());
				}
			}
		}
		
		playerStrength = new JTable(data,column);
		strengthPane.getViewport().add(playerStrength);
	
	}
	
	/*
	 * @author Ofreish
	 */
	public void addPlayerName(String name) {
		currentPlayerName.setText(name);
	}
	
	/*
	 * @author Ofreish
	 */
	public void addCountriesToBox(Player p) {
		countriesComboBox.removeAllItems();
		for(Country c : p.getCountriesOwned()) {
			countriesComboBox.addItem(c.getCountryName());
		}
	}





	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * @author Ofreish
	 */
//	public void updateGame(String country) {
//		
//		if(player[currentPlayer].getNoOfArmiesOwned()>0) {
//			player[currentPlayer].reduceArmyInPlayer();
//			for(Country c : player[currentPlayer].getCountriesOwned()) {
//				if(c.getCountryName().equals(country)) {
//					c.addNoOfArmiesCountry();
//				}
//			}
//			createStartUpTree();
//		}
//	}
	
	/*
	 * @author Ofreish
	 */
//	public void updateContinentsOwned(int number) {
//		boolean flag = false;
//		for(Continent continent : gameMap.getContinents()) {
//			for(Country country : continent.getCountriesPresent()) {
//				System.out.println("CNT: "+country.getCountryName());
//				if(player[number].getCountriesOwned().contains(country)) {
//					flag = true;
//				}
//				else {
//					flag = false;
//					break;
//				}
//			}
//			if(flag) {
//				System.out.println("UCO: "+player[currentPlayer].getNoOfArmiesOwned());
//				player[currentPlayer].incrementNoOfArmiesOwned(continent.getControlValue());
//			}
//		}
//		
//	}

}
