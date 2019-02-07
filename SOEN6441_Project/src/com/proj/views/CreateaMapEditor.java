package com.proj.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.proj.controllers.CreateMapContoller;
import com.proj.models.Continent;
import com.proj.models.ContinentArea;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.utilites.Constants;


public class CreateaMapEditor extends JFrame {
	
	private JLabel countriesLabel;
	private JLabel continentLabel;
	private CountriesArea countriesArea;
	private ContinentArea continentArea;
	private ToolBar toolBar;
	public Map map;
	private CreateaMapEditor createaMapEditor;
	private JTree mapTree;
	private JScrollPane treeScrollPane;
	private String userSelTreeNode;
	
	
	public CreateaMapEditor(Map map) {
		// TODO Auto-generated method stub
		
		
		super("Game Window");
		this.map = map;
		
		setSize(Constants.MAP_EDITOR_WIDTH,Constants.MAP_EDITOR_HEIGHT);
		setMinimumSize(new Dimension(Constants.MAP_EDITOR_WIDTH,Constants.MAP_EDITOR_HEIGHT));
		setResizable(false);
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
		//setLayout(new BorderLayout());
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		
		continentLabel = new JLabel("Continent Tree");
	    Dimension continentSize = continentLabel.getPreferredSize();
	    continentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    continentLabel.setBounds(10, 50, continentSize.width+300, continentSize.height);
	    


		treeScrollPane = new JScrollPane(mapTree);
	    treeScrollPane.setBounds(10, 70,frameSize.width-600,frameSize.height-400);
	   
		
		countriesLabel = new JLabel("Countries Matrix");
	    Dimension countriesSize = countriesLabel.getPreferredSize();
	    countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    countriesLabel.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()), 50, countriesSize.width+300, countriesSize.height);

	    
	    countriesArea = new CountriesArea();
	    countriesArea.setBounds(treeScrollPane.getBounds().x + (int) (treeScrollPane.getBounds().getWidth()),70,frameSize.width-350,frameSize.height-400);

		toolBar = new ToolBar(createMapController);
		toolBar.setBounds(0, 0, frameSize.width, 40);
		
		
		add(countriesLabel);
		add(continentLabel);
		add(countriesArea);
		add(treeScrollPane);
		add(toolBar);
		
	}

	public void createTree() {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - "+map.getName()+"");
		
		for(Continent continent : map.getContinents()) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(continent.getContinentName());
			for(Country country : continent.getCountriesPresent()) {
				DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(country.getCountryName()); 
				branch.add(subBranch);
			}
			top.add(branch);
		}
		mapTree = new JTree(top);
		treeScrollPane.getViewport().add(mapTree);
		
		
	}

}

class CountriesArea extends JTextArea {
	
	private JTextArea textArea;
	public CountriesArea() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane,BorderLayout.CENTER);

	}

}




