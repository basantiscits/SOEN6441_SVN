package com.proj.models;

import java.awt.BorderLayout;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;


import com.proj.models.*;
import com.proj.utilites.Constants;

public class ContinentArea extends JTextArea implements MouseListener{
	private JTree tree;
	private JScrollPane treeScrollPane;
	private JTextArea textArea;
	public ContinentArea(Map map) {
		textArea = new JTextArea();
		tree = new JTree(createTree(map));
		
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - "+map.getName()+"");
				
				for(Continent continent : map.getContinents()) {
					DefaultMutableTreeNode branch = new DefaultMutableTreeNode(continent.getContinentName());
					for(Country country : continent.getCountriesPresent()) {
						DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(country.getCountryName()); 
						branch.add(subBranch);
					}
					top.add(branch);
				}
				tree = new JTree(top);
				tree.setShowsRootHandles(true);
				treeScrollPane.getViewport().removeAll();
				treeScrollPane.getViewport().add(tree);
			}
		});
		setLayout(new BorderLayout());
		
		//Stree = new JTree(createTree());
		
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane,BorderLayout.CENTER);

	}
	public DefaultMutableTreeNode createTree(Map map) {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Map - "+map.getName()+"");
		
		for(Continent continent : map.getContinents()) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(continent.getContinentName());
			for(Country country : continent.getCountriesPresent()) {
				DefaultMutableTreeNode subBranch = new DefaultMutableTreeNode(country.getCountryName()); 
				branch.add(subBranch);
			}
			top.add(branch);
		}
		
		return top;	
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
