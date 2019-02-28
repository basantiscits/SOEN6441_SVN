package com.proj.views;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import com.proj.controllers.MapEditorController;
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.utilites.Constants;

public class MapEditor extends JFrame {

	private JLabel countriesLabel;
	private JLabel continentLabel;
	private ToolBar toolBar;
	public Map gameMap;
	private MapEditorController mapEditorController;
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
	private List<String> countries = new ArrayList<String>();
	
	private String data[][];
	private String countryColumn[];
	private JTable tablematrix;
	private JScrollPane scrollPane;
	
	
	

	public MapEditor(Map gameMap) {
		// TODO Auto-generated method stub

		super("Game Window");
		this.gameMap = gameMap;

		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		setMinimumSize(new Dimension(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		mapEditorController= new MapEditorController(this);


		addComponents(mapEditorController);
	}
	
	public void addComponents(MapEditorController mapEditorController) {
		setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);

		Dimension frameSize = this.getSize();
		//---------------------------------------------------------------
		
		toolBar = new ToolBar(mapEditorController);
		toolBar.setBounds(0, 0, frameSize.width, 40);
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
		treeScrollPane.setBounds(10, 70, frameSize.width - 950, frameSize.height - 600);
		//---------------------------------------------------------------
		CountryLabelViewPanel = new JPanel();
		countriesLabel = new JLabel("Countries Matrix");
		Dimension countriesSize = countriesLabel.getPreferredSize();
		countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		//System.out.println("yes : "+treeScrollPane.getBounds().x );
		countriesLabel.setBounds(240, 50, countriesSize.width + 500, countriesSize.height);
		CountryLabelViewPanel.setBounds(ContientLabelViewPanel.getBounds().x + (int) (ContientLabelViewPanel.getBounds().getWidth()), 35,
				frameSize.width - 300, 35);
		add(CountryLabelViewPanel);
		CountryLabelViewPanel.setBackground(Color.lightGray);
		CountryLabelViewPanel.setLayout(new FlowLayout());
		countriesLabel.setFont(new Font("dialog", 1, 15));
		CountryLabelViewPanel.setBorder(blackline);
		CountryLabelViewPanel.add(countriesLabel);


		scrollPane = new JScrollPane(tablematrix);
		
		scrollPane.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()), 70,
				frameSize.width - 300, frameSize.height - 600);
	

		// ---------------------------------------------------------------

		add(scrollPane);
		add(treeScrollPane);
		add(toolBar);
		
		countriesMatrix();
		createTree();

	}
	
	public void countriesMatrix()
	{
		System.out.println("inside countriesMAtrix");
		countries = gameMap.listOfCountryNames();
		Color color = new Color(200,240,240);
		
		int noOfCountries = countries.size();
		
		DefaultTableModel dtm = new DefaultTableModel(noOfCountries,noOfCountries) {
			
			 
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
		
		
		for(String countryName:countries){
			
			data[i++][0] = countryName;
			countryColumn[++j] =  countryName;	
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
		
		
		for(Continent currentContinent:gameMap.getContinents()){
			for(Country currentCountry:currentContinent.getCountriesPresent()){
				int row_length = data.length;
				int column_length = data[0].length;
				for(i = 0;i<row_length;i++){
					if(currentCountry.getCountryName().equalsIgnoreCase(data[i][0])){
						for(j = 1;j<column_length;j++){
							String neighbour = countryColumn[j];
							if(!currentCountry.getListOfNeighbours().contains(countryColumn[j])){	
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
		
		tablematrix.addMouseListener(new java.awt.event.MouseAdapter() {
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent ev) {
	        int row = tablematrix.rowAtPoint(ev.getPoint());
	        int col = tablematrix.columnAtPoint(ev.getPoint());
	        String source = data[row][0];
	        String neighbour = countryColumn[col];
	        
	       if(tablematrix.getValueAt(row, col) == "Y"){
	    	   for(Continent continent:gameMap.getContinents()){
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
	    			   for(Continent continent:gameMap.getContinents()){
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

}
	
