package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;

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
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;

/**
 * Play New Game class
 * @author Kirti
 * @since 1- Feb 2019
 * @version 1.0
 */
public class PlayNewGame extends JFrame implements ActionListener, Serializable {
	private PlayNewGame playNewGame;
	private String sFileName = "";
	private String sLocationWhereFileisKept = "";
	private String sAppendParam = "";
	private JButton buttonbrowse;
	private JComboBox<String> comboBoxSelectPlayer;
	private String[] playersList = new String[] { "  --Select--  ", "2","3","4","5","6" };
	private JTextField textFieldMap;
	private JButton buttonPlayGame;
	private String copyNoOfPlayers;
	private Map sCarryMapForward = new Map();
	private JTree mapTree;
	private JScrollPane treeScrollPane;
	private NewGameController newGameController;
	private String [] playerTypesBehaviour = new String[] { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
	private JLabel labelPlayer1, labelPlayer2,labelPlayer3,labelPlayer4,labelPlayer5,labelPlayer6;
	private JComboBox<String> comboBoxPlayer1;
	private JComboBox<String> comboBoxPlayer2;
	private JComboBox<String> comboBoxPlayer3;
	private JComboBox<String> comboBoxPlayer4;
	private JComboBox<String> comboBoxPlayer5;
	private JComboBox<String> comboBoxPlayer6;
	private static final long serialVersionUID = 45443434343L;

	
	

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
		buttonPlayGame.setBounds(380, 600, 210, 30);
		buttonPlayGame.addActionListener(newGameController);
		add(buttonPlayGame);
		
		labelPlayer1 = new JLabel("Player1 : ");
		labelPlayer1.setBounds(350, 220, 400, 180);
		add(labelPlayer1);
		labelPlayer1.setVisible(false);
		
		comboBoxPlayer1 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer1.setBounds(490, 295, 90, 30);
		comboBoxPlayer1.addActionListener(newGameController);
		add(comboBoxPlayer1);
		comboBoxPlayer1.setVisible(false);
		
		labelPlayer2 = new JLabel("Player 2 :  ");
		labelPlayer2.setBounds(350, 270, 400, 180);
		add(labelPlayer2);
		labelPlayer2.setVisible(false);
		
		comboBoxPlayer2 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer2.setBounds(490, 345, 90, 30);
		comboBoxPlayer2.addActionListener(newGameController);
		add(comboBoxPlayer2);
		comboBoxPlayer2.setVisible(false);
		
		labelPlayer3 = new JLabel("Player 3 :  ");
		labelPlayer3.setBounds(350, 320, 400, 180);
		add(labelPlayer3);
		labelPlayer3.setVisible(false);
		
		comboBoxPlayer3 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer3.setBounds(490, 395, 90, 30);
		comboBoxPlayer3.addActionListener(newGameController);
		add(comboBoxPlayer3);
		comboBoxPlayer3.setVisible(false);
		
		labelPlayer4 = new JLabel("Player 4 :  ");
		labelPlayer4.setBounds(350, 370, 400, 180);
		add(labelPlayer4);
		labelPlayer4.setVisible(false);
		
		comboBoxPlayer4 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer4.setBounds(490, 445, 90, 30);
		comboBoxPlayer4.addActionListener(newGameController);
		add(comboBoxPlayer4);
		comboBoxPlayer4.setVisible(false);
		
		labelPlayer5 = new JLabel("Player 5 :  ");
		labelPlayer5.setBounds(350, 420, 400, 180);
		add(labelPlayer5);
		labelPlayer5.setVisible(false);
		
		comboBoxPlayer5 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer5.setBounds(490, 495, 90, 30);
		comboBoxPlayer5.addActionListener(newGameController);
		add(comboBoxPlayer5);
		comboBoxPlayer5.setVisible(false);
		
		labelPlayer6 = new JLabel("Player 6 :  ");
	    labelPlayer6.setBounds(350, 470, 400, 180);
	    add(labelPlayer6);
	    labelPlayer6.setVisible(false);
			
		comboBoxPlayer6 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxPlayer6.setBounds(490, 545, 90, 30);
		comboBoxPlayer6.addActionListener(newGameController);
		add(comboBoxPlayer6);
		comboBoxPlayer6.setVisible(false);


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
		GameWindowScreen GameWindowScreen = new GameWindowScreen(gameModel);
		GameWindowScreen.setVisible(true);
	}
	

	/**
	 * getter for player label
	 * @return labelPlayer1
	 */
	public JLabel getPlayerLable1() {
		return labelPlayer1;
	}
	
	/**
	 * setter for player label 1
	 * @param labelPlayer1 player label 1
	 */
	public void setPlayerLable1(JLabel labelPlayer1) {
		this.labelPlayer1 = labelPlayer1;
	}
	
	/**
	 * getter for player label 2
	 * @return labelPlayer2
	 */
	public JLabel getPlayerLable2() {
		return labelPlayer2;
	}
	
	/**
	 * setter for player label 2
	 * @param labelPlayer2 player label 2
	 */
	public void setPlayerLable2(JLabel labelPlayer2) {
		this.labelPlayer2 = labelPlayer2;
	}
	
	/**
	 * getter for player label 3
	 * @return labelPlayer3
	 */
	public JLabel getPlayerLable3() {
		return labelPlayer3;
	}
	
	/**
	 * setter for player label 3
	 * @param labelPlayer3 player label 3
	 */
	public void setPlayerLable3(JLabel labelPlayer3) {
		this.labelPlayer3 = labelPlayer3;
	}
	
	/**
	 * getter for player label 4
	 * @return labelPlayer4
	 */
	public JLabel getPlayerLable4() {
		return labelPlayer4;
	}
	
	/**
	 * setter for player label 4
	 * @param labelPlayer4 player label 4
	 */
	public void setPlayerLable4(JLabel labelPlayer4) {
		this.labelPlayer4 = labelPlayer4;
	}
	
	/**
	 * getter for player label 5
	 * @return labelPlayer5
	 */
	public JLabel getPlayerLable5() {
		return labelPlayer5;
	}
	
	/**
	 * setter for player label 5
	 * @param labelPlayer5 player label 5
	 */
	public void setPlayerLable5(JLabel labelPlayer5) {
		this.labelPlayer5 = labelPlayer5;
	}
	
	/**
	 * getter for player label 6
	 * @return labelPlayer6
	 */
	public JLabel getPlayerLable6() {
		return labelPlayer6;
	}
	
	/**
	 * setter for player label 6
	 * @param labelPlayer6 player label 6
	 */
	public void setPlayerLable6(JLabel labelPlayer6) {
		this.labelPlayer6 = labelPlayer6;
	}

	/**
	 * getter for combo box of select player 1
	 * @return comboBoxPlayer1
	 */
	public JComboBox<String> getComboBoxSelectPlayer1() {
		return comboBoxPlayer1;
	}

	/**
	 * setter for combo box of select player 1
	 * @param comboBoxPlayer1 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer1(JComboBox<String> comboBoxPlayer1) {
		this.comboBoxPlayer1 = comboBoxPlayer1;
	}
	
	/**
	 * getter for combo box of select player 2
	 * @return comboBoxPlayer2
	 */
	public JComboBox<String> getComboBoxSelectPlayer2() {
		return comboBoxPlayer2;
	}

	/**
	 * setter for combo box of select player 2
	 * @param comboBoxPlayer2 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer2(JComboBox<String> comboBoxPlayer2) {
		this.comboBoxPlayer2 = comboBoxPlayer2;
	}
	
	/**
	 * getter for combo box of select player 3
	 * @return comboBoxPlayer3
	 */
	public JComboBox<String> getComboBoxSelectPlayer3() {
		return comboBoxPlayer3;
	}

	/**
	 * setter for combo box of select player 3
	 * @param comboBoxPlayer3 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer3(JComboBox<String> comboBoxPlayer3) {
		this.comboBoxPlayer3 = comboBoxPlayer3;
	}
	
	/**
	 * getter for combo box of select player 4
	 * @return comboBoxPlayer4
	 */
	public JComboBox<String> getComboBoxSelectPlayer4() {
		return comboBoxPlayer4;
	}

	/**
	 * setter for combo box of select player 4
	 * @param comboBoxPlayer4 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer4(JComboBox<String> comboBoxPlayer4) {
		this.comboBoxPlayer4 = comboBoxPlayer4;
	}
	
	/**
	 * getter for combo box of select player 5
	 * @return comboBoxPlayer5
	 */
	public JComboBox<String> getComboBoxSelectPlayer5() {
		return comboBoxPlayer5;
	}

	/**
	 * setter for combo box of select player 5
	 * @param comboBoxPlayer5 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer5(JComboBox<String> comboBoxPlayer5) {
		this.comboBoxPlayer5 = comboBoxPlayer5;
	}
	
	/**
	 * getter for combo box of select player 6
	 * @return comboBoxPlayer6
	 */
	public JComboBox<String> getComboBoxSelectPlayer6() {
		return comboBoxPlayer6;
	}

	/**
	 * setter for combo box of select player 6
	 * @param comboBoxPlayer6 Object of JComboBox class
	 */
	public void setComboBoxSelectPlayer6(JComboBox<String> comboBoxPlayer6) {
		this.comboBoxPlayer6 = comboBoxPlayer6;
	}
	
	/**
	 * Overridden method listening events not used
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
