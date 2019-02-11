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

import com.proj.controllers.CreateMapContoller;
import com.proj.models.Continent;
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.utilites.Constants;

public class CreateMapEditor extends JFrame {

	private JLabel countriesLabel;
	private JLabel continentLabel;
	private CountriesArea countriesArea;
	private ContinentArea continentArea;
	private ToolBar toolBar;
	public Map map;
	private CreateMapEditor createaMapEditor;
	private JTree mapTree;
	private JScrollPane treeScrollPane;
	private String userSelTreeNode;
	private JPanel startPhaseViewPanel;
	public JLabel StartPhaseDefinedLabel;
	public JLabel PlayerAllocatedLabel;
	private JPanel dynamicAreastartPhasePanel;

	private JPanel randomPlayerPhaseViewPanel;
	private JPanel dynamicAreaPlayerPhasePanel;
	
	private JPanel ContientLabelViewPanel;
	private JPanel CountryLabelViewPanel;
	
	
	// countries area
	private JTextArea textArea;
	List<Country> countries = new ArrayList<Country>();
	
	String data[][];
	String countryColumn[];
	JTable tablematrix;
	JScrollPane scrollPane;
	
	
	

	public CreateMapEditor(Map map) {
		// TODO Auto-generated method stub

		super("Game Window");
		this.map = map;

		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		setMinimumSize(new Dimension(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT));
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		CreateMapContoller createMapController = new CreateMapContoller(this);

		createMapController.addMap(map);

		addComponents(createMapController);

	}

	public Map getMap() {
		return map;
	}

	public void addComponents(CreateMapContoller createMapController) {
		// setLayout(new BorderLayout());
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);

		Dimension frameSize = this.getSize();
		//---------------------------------------------------------------
		ContientLabelViewPanel = new JPanel();
		continentLabel = new JLabel("Continent Tree");
		Dimension continentSize = continentLabel.getPreferredSize();
		continentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		continentLabel.setBounds(140, 50, continentSize.width + 500, continentSize.height);
		ContientLabelViewPanel.setBounds(10, 35, 400, 35);
		add(ContientLabelViewPanel);
		ContientLabelViewPanel.setBackground(Color.lightGray);
		ContientLabelViewPanel.setLayout(new FlowLayout());
		continentLabel.setFont(new Font("dialog", 1, 15));
		ContientLabelViewPanel.setBorder(blackline);
		ContientLabelViewPanel.add(continentLabel);
		

		treeScrollPane = new JScrollPane(mapTree);
		treeScrollPane.setBounds(10, 70, frameSize.width - 800, frameSize.height - 600);
		//---------------------------------------------------------------
		CountryLabelViewPanel = new JPanel();
		countriesLabel = new JLabel("Countries Matrix");
		Dimension countriesSize = countriesLabel.getPreferredSize();
		countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		countriesLabel.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()+285), 50,
				countriesSize.width + 300, countriesSize.height);
		CountryLabelViewPanel.setBounds(410, 35, 750, 35);
		add(CountryLabelViewPanel);
		CountryLabelViewPanel.setBackground(Color.lightGray);
		CountryLabelViewPanel.setLayout(new FlowLayout());
		countriesLabel.setFont(new Font("dialog", 1, 15));
		CountryLabelViewPanel.setBorder(blackline);
		CountryLabelViewPanel.add(countriesLabel);

		countriesArea = new CountriesArea();
		
		countriesArea.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()), 70,
				frameSize.width - 450, frameSize.height - 600);
		setLayout(new BorderLayout());

		scrollPane = new JScrollPane(tablematrix);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		countriesArea.add(scrollPane);
		
		
		
		
		
		
		

		toolBar = new ToolBar(createMapController);
		toolBar.setBounds(0, 0, frameSize.width, 40);

		// ---------------------------------------------------------------

		startPhaseViewPanel = new JPanel();
		startPhaseViewPanel.setBounds(15, 275 + (int) (300), 635 + (int) (200), 35);
		add(startPhaseViewPanel);
		startPhaseViewPanel.setBackground(Color.lightGray);
		startPhaseViewPanel.setLayout(new FlowLayout());

		StartPhaseDefinedLabel = new JLabel("StartUp Phase");
		StartPhaseDefinedLabel.setFont(new Font("dialog", 1, 15));
		startPhaseViewPanel.setBorder(blackline);
		startPhaseViewPanel.add(StartPhaseDefinedLabel);

		// ---------------------------------------------------------------

		dynamicAreastartPhasePanel = new JPanel();
		dynamicAreastartPhasePanel.setBounds(15, 275 + (int) (300), 635 + (int) (200), 380);
		add(dynamicAreastartPhasePanel);
		dynamicAreastartPhasePanel.setBackground(Color.white);
		dynamicAreastartPhasePanel.setBorder(blackline);
		dynamicAreastartPhasePanel.setLayout(new FlowLayout());

		// ---------------------------------------------------------------
		randomPlayerPhaseViewPanel = new JPanel();
		randomPlayerPhaseViewPanel.setBounds(850, 575, (305), 35);

		add(randomPlayerPhaseViewPanel);
		randomPlayerPhaseViewPanel.setBackground(Color.lightGray);
		randomPlayerPhaseViewPanel.setBorder(blackline);
		randomPlayerPhaseViewPanel.setLayout(new FlowLayout());

		PlayerAllocatedLabel = new JLabel("Players - Country Percantage Allocated");
		PlayerAllocatedLabel.setFont(new Font("dialog", 1, 15));
		randomPlayerPhaseViewPanel.add(PlayerAllocatedLabel);

		// ---------------------------------------------------------------
		dynamicAreaPlayerPhasePanel = new JPanel();
		dynamicAreaPlayerPhasePanel.setBounds(595, 10 + (int) (580), 360 + (int) (200), 365);

		add(dynamicAreaPlayerPhasePanel);
		dynamicAreaPlayerPhasePanel.setBackground(Color.white);
		dynamicAreaPlayerPhasePanel.setBorder(blackline);
		dynamicAreaPlayerPhasePanel.setLayout(new FlowLayout());

		// ---------------------------------------------------------------

		//add(countriesLabel);
		add(countriesArea);
		add(treeScrollPane);
		add(toolBar);
		
		
		//countries area
		
		

		//add(scrollPane, BorderLayout.CENTER);

	}
	
	public void countriesMatrix()
	{
		System.out.println("inside countriesMAtrix");
		countries = map.getAllExistingCountries();
		// Country country = new Country();
		
		
		int noOfCountries = countries.size();
		
		DefaultTableModel dtm = new DefaultTableModel(noOfCountries,noOfCountries);
		
		data = new String[noOfCountries][noOfCountries + 1];
		countryColumn = new String[noOfCountries + 1];
		countryColumn[0] = "**";
		int i = 0;int j = 0;
		
		
		for(Country country:countries){
			
			data[i++][0] = country.getCountryName();
			countryColumn[++j] =  country.getCountryName();	
		}
		dtm.setDataVector(data, countryColumn);
		tablematrix = new JTable(dtm);
		
		scrollPane.getViewport().removeAll();
		scrollPane.getViewport().add(tablematrix);
		
		int row_length = data.length;
		int column_length = data[0].length;
		for(i = 0;i<row_length;i++){
			String source = data[i][0];
			for(j = 1;j<column_length;j++){
				String neighbour = countryColumn[j];
				if(source.equalsIgnoreCase(neighbour)){	
				tablematrix.setValueAt("N", i, j);
				}
				else {
				tablematrix.setValueAt("Y", i, j);	
				}
				
				
			}
		}
		
		
		
		
	
	tablematrix.addMouseListener(new java.awt.event.MouseAdapter() {
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent ev) {
	        int row = tablematrix.rowAtPoint(ev.getPoint());
	        int col = tablematrix.columnAtPoint(ev.getPoint());
	        String source = data[row][0];
	        String neighbour = countryColumn[col];
	        
	       if(tablematrix.getValueAt(row, col) == "Y"){
	    	   for(Continent continent:map.getContinents()){
	    		   for(Country country:continent.getCountriesPresent()){
	    			   if(country.getCountryName().trim().equalsIgnoreCase(source.trim())){
	    				   country.getListOfNeighbours().remove(neighbour);
	    				   tablematrix.setValueAt("N", row, col);
	    				   for(String s:country.getListOfNeighbours()){
	    					   System.out.println("country " + s);
	    				   }
	    				   System.out.println();
	    				   System.out.println();
	    			   }
	    		   }
	    	   }
	    	   
	       }
	       else{
	    	   if(!source.trim().equalsIgnoreCase(neighbour)){
	    		   if(tablematrix.getValueAt(row, col) == "N"){
	    			   for(Continent continent:map.getContinents()){
	    	    		   for(Country country:continent.getCountriesPresent()){
	    	    			   if(country.getCountryName().trim().equalsIgnoreCase(source.trim())){
	    	    				   country.getListOfNeighbours().add(neighbour);
	    	    				   tablematrix.setValueAt("Y", row, col);
	    	    				   for(String s:country.getListOfNeighbours())
	    	    				   {
	    	    					   System.out.println("country" + s);
	    	    				   }
	    	    				   System.out.println();
	    	    				   System.out.println();
	    	    			   }
	    	    		   }
	    	    	   }
	    			   
	    			   
	    			   
	    		   }
	    	   }
	    	   
	       }
	        
	    }
	});
	}
	
	public void createTree() {

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - " + map.getName() + "");

		for (Continent continent : map.getContinents()) {
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
	

	}
	




