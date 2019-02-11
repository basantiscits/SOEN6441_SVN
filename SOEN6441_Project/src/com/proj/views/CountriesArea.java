package com.proj.views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.proj.models.Country;
import com.proj.models.Map;

public class CountriesArea extends JTextArea {

	private JTextArea textArea;
	List<Country> countries = new ArrayList<Country>();
	Map map = new Map();
	String data[][];
	String countryColumn[];
	JTable tablematrix;
	JScrollPane scrollPane;
	public CountriesArea() {
		
		
		setLayout(new BorderLayout());

		scrollPane = new JScrollPane(tablematrix);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane, BorderLayout.CENTER);


	}
}

