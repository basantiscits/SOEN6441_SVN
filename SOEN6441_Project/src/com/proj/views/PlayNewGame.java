package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.proj.controllers.NewGameController;
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;
import com.proj.utilites.MapTools;

/**
 * Play New Game class
 * @author Kirti
 * @since 1- Feb 2019
 * @version 1.0
 */
public class PlayNewGame extends JFrame implements ActionListener {
	private String sFileName = "";
	private Player[] player ;
	private String sLocationWhereFileisKept = "";
	private String sAppendParam = "";
	private JButton buttonbrowse;
	private JComboBox<String> comboBoxSelectPlayer;
	private String[] playersList = new String[] { "  --Select--  ","3", "4", "5" };
	private JTextField textFieldMap;
	private JButton buttonPlayGame;
	private String copyNoOfPlayers;
	private Map sCarryMapForward = new Map();
	private JTree mapTree;
	private JScrollPane treeScrollPane;
	private NewGameController newGameController;

	/**
	 * Play New Game constructor
	 */
	public PlayNewGame() {
		newGameController = new NewGameController(this);
		JLabel labelHeading = new JLabel();
		labelHeading.setText("THE RISK GAME : START PHASE");
		labelHeading.setBounds(Constants.WIDTH / 2 + 90, 0, 200,30);
		add(labelHeading);
		
		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select no of Players :");
		labelSelectPlayer.setBounds(40, 20, 400, 180);
		add(labelSelectPlayer);

		comboBoxSelectPlayer = new JComboBox<String>(playersList);
		comboBoxSelectPlayer.setBounds(380, 90, 90, 30);
		comboBoxSelectPlayer.addActionListener(newGameController);
		add(comboBoxSelectPlayer);

		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Please select the Map file to upload on browse Button :");
		labelSelectMap.setBounds(40, 120, 400, 180);
		add(labelSelectMap);

		textFieldMap = new JTextField();
		textFieldMap.setBounds(380, 195, 180, 30);
		add(textFieldMap);
		textFieldMap.setEditable(false);

		buttonbrowse = new JButton();
		buttonbrowse.setText("Press button to select file");
		buttonbrowse.setBounds(600, 195, 210, 30);
		buttonbrowse.addActionListener(newGameController);
		add(buttonbrowse);

		buttonPlayGame = new JButton();
		buttonPlayGame.setText("Play Now");
		buttonPlayGame.setBounds(380, 400, 210, 30);
		buttonPlayGame.addActionListener(newGameController);
		add(buttonPlayGame);


		setTitle("******THE RISK GAME******");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT + 300);
		setLayout(null);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
			}
		});
	}

	/**
	 * getter for file name
	 * @return file name
	 */
	public String getsFileName() {
		return sFileName;
	}


	/**
	 * setter for file name
	 * @param sFileName file name
	 */
	public void setsFileName(String sFileName) {
		this.sFileName = sFileName;
	}


	/**
	 * This method takes file from user
	 * @param sReturnFileAndLoc file name
	 * @return sLocationWhereFileisKept file path
	 */
	public String ImportFileName(String sReturnFileAndLoc) {
		try {
			String ImportFileName;
			JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./save"));
			chooser.setDialogTitle("Choose saved game file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.map", "map"));
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				}
				else {
					if (ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map")) {

						File f = new File(ImportFileName);
						sFileName = f.getName();
						sLocationWhereFileisKept = ImportFileName;
						sAppendParam = sFileName + "^" + sLocationWhereFileisKept;
						JOptionPane.showMessageDialog(null, "File in Correct format");
					}
					else {
						JOptionPane.showMessageDialog(null, "File name invalid");
						String sPrint = ImportFileName.trim().substring(ImportFileName.length() - 4);
						System.out.println(sPrint);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {}
		return sLocationWhereFileisKept;
	}

	/**
	 * getter for Player
	 * @return the player
	 */
	public Player[] getPlayer() {
		return player;
	}

	/**
	 * setter for Player
	 * @param player the player to set
	 */
	public void setPlayer(Player[] player) {
		this.player = player;
	}

	/**
	 * getter for Location of File
	 * @return the sLocationWhereFileisKept
	 */
	public String getsLocationWhereFileisKept() {
		return sLocationWhereFileisKept;
	}

	/**
	 * setter for Location of File
	 * @param sLocationWhereFileisKept the sLocationWhereFileisKept to set
	 */
	public void setsLocationWhereFileisKept(String sLocationWhereFileisKept) {
		this.sLocationWhereFileisKept = sLocationWhereFileisKept;
	}

	/**
	 * getter for Append Parameter
	 * @return the sAppendParam
	 */
	public String getsAppendParam() {
		return sAppendParam;
	}

	/**
	 * setter for Append Parameter
	 * @param sAppendParam the sAppendParam to set
	 */
	public void setsAppendParam(String sAppendParam) {
		this.sAppendParam = sAppendParam;
	}

	/**
	 * getter for Browse button 
	 * @return the buttonbrowse
	 */
	public JButton getButtonbrowse() {
		return buttonbrowse;
	}

	/**
	 * setter for Browse Button 
	 * @param buttonbrowse the buttonbrowse to set
	 */
	public void setButtonbrowse(JButton buttonbrowse) {
		this.buttonbrowse = buttonbrowse;
	}

	/**
	 * getter for Combo Box to Select Player
	 * @return the comboBoxSelectPlayer
	 */
	public JComboBox<String> getComboBoxSelectPlayer() {
		return comboBoxSelectPlayer;
	}

	/**
	 * setter for Combo Box to Select Player
	 * @param comboBoxSelectPlayer the comboBoxSelectPlayer to set
	 */
	public void setComboBoxSelectPlayer(JComboBox<String> comboBoxSelectPlayer) {
		this.comboBoxSelectPlayer = comboBoxSelectPlayer;
	}

	/**
	 * getter for Players List
	 * @return the playersList
	 */
	public String[] getPlayersList() {
		return playersList;
	}

	/**
	 * setter for Players List
	 * @param playersList the playersList to set
	 */
	public void setPlayersList(String[] playersList) {
		this.playersList = playersList;
	}

	/**
	 * getter for Text Field Map
	 * @return the textFieldMap
	 */
	public JTextField getTextFieldMap() {
		return textFieldMap;
	}

	/**
	 * setter for Text Field Map 
	 * @param textFieldMap the textFieldMap to set
	 */
	public void setTextFieldMap(JTextField textFieldMap) {
		this.textFieldMap = textFieldMap;
	}

	/**
	 * getter for Play Game Button
	 * @return the buttonPlayGame
	 */
	public JButton getButtonPlayGame() {
		return buttonPlayGame;
	}

	/**
	 * setter for Play Game Button 
	 * @param buttonPlayGame the buttonPlayGame to set
	 */
	public void setButtonPlayGame(JButton buttonPlayGame) {
		this.buttonPlayGame = buttonPlayGame;
	}

	/**
	 * getter for Copy of No of Players
	 * @return the copyNoOfPlayers
	 */
	public String getCopyNoOfPlayers() {
		return copyNoOfPlayers;
	}

	/**
	 * setter for Copy of No of Players
	 * @param copyNoOfPlayers the copyNoOfPlayers to set
	 */
	public void setCopyNoOfPlayers(String copyNoOfPlayers) {
		this.copyNoOfPlayers = copyNoOfPlayers;
	}

	/**
	 * getter for Carry Map Forward
	 * @return the sCarryMapForward
	 */
	public Map getsCarryMapForward() {
		return sCarryMapForward;
	}

	/**
	 * setter for Carry Map Forward
	 * @param sCarryMapForward the sCarryMapForward to set
	 */
	public void setsCarryMapForward(Map sCarryMapForward) {
		this.sCarryMapForward = sCarryMapForward;
	}

	/**
	 * getter for Map Tree
	 * @return the mapTree
	 */
	public JTree getMapTree() {
		return mapTree;
	}

	/**
	 * setter for Map Tree
	 * @param mapTree the mapTree to set
	 */
	public void setMapTree(JTree mapTree) {
		this.mapTree = mapTree;
	}

	/**
	 * getter for Tree Scroll Pane 
	 * @return the treeScrollPane
	 */
	public JScrollPane getTreeScrollPane() {
		return treeScrollPane;
	}

	/**
	 * setter for Tree Scroll Pane
	 * @param treeScrollPane the treeScrollPane to set
	 */
	public void setTreeScrollPane(JScrollPane treeScrollPane) {
		this.treeScrollPane = treeScrollPane;
	}

	/**
	 * getter for New Game Controller
	 * @return the newGameController
	 */
	public NewGameController getNewGameController() {
		return newGameController;
	}

	/**
	 * setter for New Game Controller
	 * @param newGameController the newGameController to set
	 */
	public void setNewGameController(NewGameController newGameController) {
		this.newGameController = newGameController;
	}
	
	/**
	 * This method displays the game model window
	 * @param sCarryMapForward Already edited map
	 * @param player Array of Player objects
	 * @param gameModel Object of GameModelCreation class
	 */
	public void GameModelWindowMade(Map sCarryMapForward, Player[] player, GameModelCreation gameModel) {
		dispose();
		GameWindowScreen GameWindowScreen = new GameWindowScreen(sCarryMapForward,player,gameModel);
		GameWindowScreen.setVisible(true);
	}

	/**
	 * Overridden method listening events not used
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
}
