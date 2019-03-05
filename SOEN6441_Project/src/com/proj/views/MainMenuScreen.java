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


/**
 * Main Menu Screen class
 * @author Kirti
 * @since 7 Feb 2019
 * @version 1.0
 */
public class MainMenuScreen extends JFrame {
	private MapTools mapTool;
	private JButton btnLoadExisitingMaps;
	private JLabel riskImage;
	private MapEditor mapEditorView;
	public MainMenuScreen() {
		CreateMenuBar();
		setTitle("Simple menu");
		setSize(900, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mapTool=new MapTools();
		
		ImageIcon RiskIcon = new ImageIcon("Images/Risk.jpg");
		riskImage = new JLabel("");
		riskImage.setIcon(RiskIcon);
		riskImage.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(riskImage);

	
	}
	
	/**
	 * create menu bar method
	 */
	private void CreateMenuBar() {

		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu1 = new JMenu("File");
		JMenu fileMenu2 = new JMenu("Map Editor");
		JMenu helpMenu = new JMenu("Help");
		JMenu SubSubMenu = new JMenu("Play Game");
		
		JMenuItem PlayGame =  new JMenuItem(new AbstractAction("New Game") {
			public void actionPerformed(ActionEvent e) {
				PlayNewGame PlayGame = new PlayNewGame();
				PlayGame.setVisible(true);
				dispose();
			}
		});

		PlayGame.setToolTipText("New Game");
		JMenuItem singlePlayer = new JMenuItem("Play Game");
		singlePlayer.setToolTipText("Play Game");

		JMenuItem exitMenuItem = new JMenuItem(new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitMenuItem.setToolTipText("Exit application");

		SubSubMenu.add(PlayGame);

		JMenuItem newMap = new JMenuItem(new AbstractAction("Create New Map") {
			public void actionPerformed(ActionEvent e) {
				Map newMap=new Map();
				mapEditorView=new MapEditor(newMap);
				mapEditorView.setVisible(true);
				dispose();
			
			}
		});
		newMap.setToolTipText("Create New Map");

		JMenuItem editExistingMap = new JMenuItem(new AbstractAction("Edit Existing Map") {
			public void actionPerformed(ActionEvent e) {
			
			Map existingMap=new Map();
			
			String sFinal=mapTool.pickMapFile(existingMap);
			System.out.println(sFinal);
			if(sFinal == null || (sFinal.isEmpty())){
				
			}
			else{
				boolean isMapValid=mapTool.parseAndValidateMap(existingMap,3);
				
					if(isMapValid){
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						MapEditor mapEditorView=new MapEditor(existingMap);
						mapEditorView.setVisible(true);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
					
				}
				
			}
			
		});
		
		editExistingMap.setToolTipText("Edit Existing Map");

		JMenuItem RulesOfGames = new JMenuItem("Rules of Games");
		RulesOfGames.setToolTipText("Rules of Games");

		menubar.add(helpMenu);

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


}
