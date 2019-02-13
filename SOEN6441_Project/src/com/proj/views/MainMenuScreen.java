package com.proj.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.proj.models.Map;
import com.proj.utilites.Constants;
import com.proj.utilites.MapTools;



public class MainMenuScreen extends JFrame {
	private MapTools mapTool;
	private JButton btnLoadExisitingMaps;

	public MainMenuScreen() {
		CreateMenuBar();
		setTitle("Simple menu");
		setSize(900, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mapTool=new MapTools();
	
		JLabel jLabelObject = new JLabel();
		jLabelObject
				.setIcon(new ImageIcon("E:\\SOEN_6441\\SOEN6441_Project\\Images\\Risk.jpg"));
	
		
		jLabelObject.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(jLabelObject);
	}

	private void CreateMenuBar() {
		// TODO Auto-generated method stub

		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu1 = new JMenu("File");
		JMenu fileMenu2 = new JMenu("Map Editor");
		JMenu helpMenu = new JMenu("Help");
		JMenu SubSubMenu = new JMenu("Play Game");

		JMenuItem singlePlayer = new JMenuItem("Single Player");
		singlePlayer.setToolTipText("Single Player");

		JMenuItem MultiPlayer = new JMenuItem("Multi-Player");
		MultiPlayer.setToolTipText("Multi-Player");

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setToolTipText("Exit application");

		SubSubMenu.add(singlePlayer);
		SubSubMenu.add(MultiPlayer);

		JMenuItem newMap = new JMenuItem(new AbstractAction("Create New Map") {
			public void actionPerformed(ActionEvent e) {
				Map newMap=new Map();
				MapEditor mapEditorView=new MapEditor(newMap);
				mapEditorView.setVisible(true);
				// Button pressed logic goes here
				//String name = JOptionPane.showInputDialog("What is your name?", null);
				//LoadMapEditor LoadMapEditor = new LoadMapEditor();
				//LoadMapEditor.setVisible(true);
			}
		});
		newMap.setToolTipText("Create New Map");

		JMenuItem editExistingMap = new JMenuItem(new AbstractAction("Edit Existing Map") {
			public void actionPerformed(ActionEvent e) {
				Map existingMap=new Map();
				
				mapTool.pickMapFile(existingMap);
				mapTool.parseAndValidateMap(existingMap);
				
				MapEditor mapEditorView=new MapEditor(existingMap);
				mapEditorView.setVisible(true);
				// Button pressed logic goes here
				//String name = JOptionPane.showInputDialog("What is your name?", null);
				//LoadMapEditor LoadMapEditor = new LoadMapEditor();
				//LoadMapEditor.setVisible(true);
			}
		});
		editExistingMap.setToolTipText("Edit Existing Map");

		JMenuItem RulesOfGames = new JMenuItem("Rules of Games");
		RulesOfGames.setToolTipText("Rules of Games");

		menubar.add(helpMenu);
		exitMenuItem.addActionListener((event) -> System.exit(0));

//		CreateEditor.addActionListener(new java.awt.event.ActionListener() {
//			@Override
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				Map map = new Map();
//				CreateMapEditor createMap = new CreateMapEditor(map);
//				createMap.setVisible(true);
//				dispose();
//				
//			}
//		});

		SubSubMenu.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SubSubMenu.addActionListener(this);
				add(btnLoadExisitingMaps);
			}

		});

		fileMenu1.add(SubSubMenu);
		fileMenu1.addSeparator();
		fileMenu1.add(exitMenuItem);
		fileMenu2.add(newMap);
		fileMenu2.addSeparator();
		fileMenu2.add(editExistingMap);
		helpMenu.add(RulesOfGames);

		menubar.add(fileMenu1);
		menubar.add(fileMenu2);
		menubar.add(fileMenu2);
		menubar.add(Box.createHorizontalGlue());
		menubar.add(helpMenu);
		menubar.add(helpMenu);

		setJMenuBar(menubar);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			MainMenuScreen ex = new MainMenuScreen();
			ex.setVisible(true);

		});

	}

/*	public void SubSubMenu(ActionEvent e) {
		if (e.getSource() == btnLoadExisitingMaps) {
			CreateaMapEditor LoadExisitingMaps = new CreateaMapEditor();
			LoadExisitingMaps.setVisible(true);
			dispose();
		}

	}*/
}
