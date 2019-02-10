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



public class MainMenuScreen extends JFrame {

	private JButton btnLoadExisitingMaps;

	public MainMenuScreen() {
		CreateMenuBar();
		setTitle("Simple menu");
		setSize(900, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
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

		JMenuItem CreateEditor = new JMenuItem("Create Map Editor");
		CreateEditor.setToolTipText("Create Map Editor");

		JMenuItem LoadExisitingMap = new JMenuItem(new AbstractAction("Load Existing Map") {
			public void actionPerformed(ActionEvent e) {
				// Button pressed logic goes here
				//String name = JOptionPane.showInputDialog("What is your name?", null);
				LoadMapEditor LoadMapEditor = new LoadMapEditor();
				LoadMapEditor.setVisible(true);
			}
		});
		LoadExisitingMap.setToolTipText("Load Existing Map");

		JMenuItem RulesOfGames = new JMenuItem("Rules of Games");
		RulesOfGames.setToolTipText("Rules of Games");

		menubar.add(helpMenu);
		exitMenuItem.addActionListener((event) -> System.exit(0));

		CreateEditor.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Map map = new Map();
				CreateMapEditor createMap = new CreateMapEditor(map);
				createMap.setVisible(true);
				dispose();
				
			}
		});

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
		fileMenu2.add(CreateEditor);
		fileMenu2.addSeparator();
		fileMenu2.add(LoadExisitingMap);
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
