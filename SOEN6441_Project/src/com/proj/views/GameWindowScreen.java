package com.proj.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.proj.models.Continent;
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

public class GameWindowScreen extends JFrame {
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

	private JTextArea textArea;
	private List<String> countries = new ArrayList<String>();
	
	private JTable playerStrength;
	
	private String data[][];
	private String countryColumn[];
	private JTable tablematrix;
	private JScrollPane scrollPane;

	public GameWindowScreen(Map gameMap, Player[] player) {
		// TODO Auto-generated method stub

		super("Game Window");
		this.gameMap = gameMap;
		this.player = player;

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

/*		add(dynamicAreaPlayerPhasePanel);
		dynamicAreaPlayerPhasePanel.setBackground(Color.lightGray);
		dynamicAreaPlayerPhasePanel.setBorder(blackline);
		dynamicAreaPlayerPhasePanel.setLayout(new FlowLayout());*/

		// ---------------------------------------------------------------

		// add(countriesLabel);
		// add(countriesArea);
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
		tablematrix = new JTable(dtm);
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

		/*
		 * 
		 * int row_length = data.length; int column_length = data[0].length;
		 * for(i = 0;i<row_length;i++){ String source = data[i][0]; for(j =
		 * 1;j<column_length;j++){ String neighbour = countryColumn[j];
		 * if(source.equalsIgnoreCase(neighbour)){ tablematrix.setValueAt("N",
		 * i, j); } else { tablematrix.setValueAt("Y", i, j); }
		 * 
		 * 
		 * } }
		 * 
		 */

		/*
		 * tablematrix.addMouseListener(new java.awt.event.MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(java.awt.event.MouseEvent ev) {
		 * int row = tablematrix.rowAtPoint(ev.getPoint()); int col =
		 * tablematrix.columnAtPoint(ev.getPoint()); String source =
		 * data[row][0]; String neighbour = countryColumn[col];
		 * 
		 * if(tablematrix.getValueAt(row, col) == "Y"){ for(Continent
		 * continent:gameMap.getContinents()){ for(Country
		 * country:continent.getCountriesPresent()){
		 * if(country.getCountryName().trim().equalsIgnoreCase(source.trim())){
		 * country.getListOfNeighbours().remove(neighbour);
		 * tablematrix.setValueAt("N", row, col); for(String
		 * s:country.getListOfNeighbours()){ System.out.println("country " + s);
		 * } System.out.println(); System.out.println(); } } }
		 * 
		 * } else{ if(!source.trim().equalsIgnoreCase(neighbour)){
		 * if(tablematrix.getValueAt(row, col) == "N"){ for(Continent
		 * continent:gameMap.getContinents()){ for(Country
		 * country:continent.getCountriesPresent()){
		 * if(country.getCountryName().trim().equalsIgnoreCase(source.trim())){
		 * country.getListOfNeighbours().add(neighbour);
		 * tablematrix.setValueAt("Y", row, col); for(String
		 * s:country.getListOfNeighbours()) { System.out.println("country" + s);
		 * } System.out.println(); System.out.println(); } } }
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * }
		 * 
		 * } });
		 */
	}

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
}
