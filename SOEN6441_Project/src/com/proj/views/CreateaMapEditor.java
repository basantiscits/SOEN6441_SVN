package com.proj.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.proj.utilites.Constants;


public class CreateaMapEditor extends JFrame {
	
	private JLabel countriesLabel;
	private JLabel continentLabel;
	private CountriesArea countriesArea;
	private ContinentArea continentArea;
	private ToolBar toolBar;
	
	public CreateaMapEditor() {
		// TODO Auto-generated method stub
		
		super("Map Editor");
		
		setSize(Constants.MAP_EDITOR_HEIGHT, Constants.MAP_EDITOR_WIDTH);
		setMinimumSize(new Dimension(Constants.MAP_EDITOR_HEIGHT, Constants.MAP_EDITOR_WIDTH));
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents();

	}
	
	public void addComponents() {
		//setLayout(new BorderLayout());
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		
		continentLabel = new JLabel("Continent Tree");
	    Dimension continentSize = continentLabel.getPreferredSize();
	    continentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    continentLabel.setBounds(10, 50, continentSize.width+300, continentSize.height);
	    
	    continentArea = new ContinentArea();
		continentArea.setBounds(10, 70,frameSize.width-600,frameSize.height-700);
		//add(continentArea);
	   
		
		countriesLabel = new JLabel("Countries Matrix");
	    Dimension countriesSize = countriesLabel.getPreferredSize();
	    countriesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
	    countriesLabel.setBounds(continentArea.getBounds().x + (int) (continentArea.getBounds().getWidth()), 50, countriesSize.width+300, countriesSize.height);

	    
	    countriesArea = new CountriesArea();
	    countriesArea.setBounds(continentArea.getBounds().x + (int) (continentArea.getBounds().getWidth()),70,frameSize.width-350,frameSize.height-700);
		
		toolBar = new ToolBar();
		toolBar.setBounds(0, 0, frameSize.width, 40);
		
		add(countriesLabel);
		add(continentLabel);
		add(countriesArea);
		add(continentArea);
		add(toolBar);
		
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

class ContinentArea extends JTextArea {
	
	private JTextArea textArea;
	public ContinentArea() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane,BorderLayout.CENTER);

	}
}


class ToolBar extends JPanel{
	
	private JButton addContinent;
	private JButton addCountries;
	private JButton save;
	
	public ToolBar() {
		
		setLayout(new FlowLayout());
		addContinent = new JButton("Add Continent");
		addCountries = new JButton("Add Countries");
		save = new JButton("Save");
		
		setBorder(BorderFactory.createEtchedBorder());
		
		add(addContinent);
		add(addCountries);
		add(save);
		
		addContinent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showInputDialog(new CreateaMapEditor(), "Enter the Continent name: ","Add Continent",JOptionPane.OK_CANCEL_OPTION|JOptionPane.QUESTION_MESSAGE);
			}
			
		});
		
	
	}
}