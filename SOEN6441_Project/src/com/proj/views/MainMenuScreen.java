package com.proj.views;

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
	private static final long serialVersionUID = 45443434343L;

	private MapTools mapTool;
	private JButton btnLoadExisitingMaps;
	private JLabel RiskImage;
	private MapEditor mapEditorView;
	
	/**
	 * Constructor of MainMenuScreen class
	 */
	public MainMenuScreen() {
		CreateMenuBar();
		setTitle("********THE RISK GAME********");
		setSize(900, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mapTool=new MapTools();
		ImageIcon RiskIcon = new ImageIcon("Images/Risk.jpg");
		RiskImage = new JLabel("");
		RiskImage.setIcon(RiskIcon);
		RiskImage.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(RiskImage);
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
		JMenu subSubMenuTournament = new JMenu("Tournament");
		
		JMenuItem PlayGame =  new JMenuItem(new AbstractAction("New Game") {
			public void actionPerformed(ActionEvent e) {
				PlayNewGame PlayGame = new PlayNewGame();
				PlayGame.setVisible(true);
				dispose();
			}
		});
		PlayGame.setToolTipText("New Game");
		JMenuItem singlePlayer = new JMenuItem("Play Game");
		JMenuItem MultiPlayer =  new JMenuItem(new AbstractAction("Tournament") {
			public void actionPerformed(ActionEvent e) {
				TournamentView tournamentMultiPlayer = new TournamentView();
				tournamentMultiPlayer.setVisible(true);
				dispose();
			}
		});
		
		singlePlayer.setToolTipText("Play Game");
		JMenuItem UploadDeserializeMap =  new JMenuItem(new AbstractAction("Upload Exisiting Game") {
			public void actionPerformed(ActionEvent e) {
				UploadExistingGame UploadExistingGame = new UploadExistingGame();
				UploadExistingGame.setVisible(true);
				dispose();
			}
		});
		UploadDeserializeMap.setToolTipText("Upload Exisiting Game");
		
		MultiPlayer.setToolTipText("Tournament");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setToolTipText("Exit application");
		
		SubSubMenu.add(PlayGame);
		SubSubMenu.add(MultiPlayer);
		SubSubMenu.add(UploadDeserializeMap);	
		
		JMenuItem newMap = new JMenuItem(new AbstractAction("Create New Map") {
			public void actionPerformed(ActionEvent e) {
				Map newMap=new Map();
				mapEditorView=new MapEditor(newMap);
				mapEditorView.setVisible(true);
			}
		});
		newMap.setToolTipText("Create New Map");
		JMenuItem editExistingMap = new JMenuItem(new AbstractAction("Edit Existing Map") {
			public void actionPerformed(ActionEvent e) {
			Map existingMap=new Map();
			String sFinal=mapTool.pickMapFile(existingMap);
			System.out.println(sFinal);
			if(sFinal == null || (sFinal.isEmpty())) {}
			else {
				boolean isMapValid=mapTool.parseAndValidateMap(existingMap,3);
					if(isMapValid) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						MapEditor mapEditorView=new MapEditor(existingMap);
						mapEditorView.setVisible(true);
					}
					else {
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
		exitMenuItem.addActionListener((event) -> System.exit(0));
		SubSubMenu.addActionListener(new java.awt.event.ActionListener() {
			@Override
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println(evt);
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
}
