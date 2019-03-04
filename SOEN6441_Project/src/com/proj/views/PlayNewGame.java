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
	private String CopynoOfPlayers;
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
		labelHeading.setText("THE RISK GAME");
		labelHeading.setBounds(Constants.WIDTH / 2 + 120, 0, 100, 30);
		add(labelHeading);

		
		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select no of Players :");
		labelSelectPlayer.setBounds(40, 20, 400, 180);
		add(labelSelectPlayer);

		comboBoxSelectPlayer = new JComboBox<>(playersList);
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

		setTitle("New Game Menu");
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


	public Player[] getPlayer() {
		return player;
	}


	public void setPlayer(Player[] player) {
		this.player = player;
	}


	public String getsLocationWhereFileisKept() {
		return sLocationWhereFileisKept;
	}


	public void setsLocationWhereFileisKept(String sLocationWhereFileisKept) {
		this.sLocationWhereFileisKept = sLocationWhereFileisKept;
	}


	public String getsAppendParam() {
		return sAppendParam;
	}


	public void setsAppendParam(String sAppendParam) {
		this.sAppendParam = sAppendParam;
	}


	public JButton getButtonbrowse() {
		return buttonbrowse;
	}


	public void setButtonbrowse(JButton buttonbrowse) {
		this.buttonbrowse = buttonbrowse;
	}


	public JComboBox<String> getComboBoxSelectPlayer() {
		return comboBoxSelectPlayer;
	}


	public void setComboBoxSelectPlayer(JComboBox<String> comboBoxSelectPlayer) {
		this.comboBoxSelectPlayer = comboBoxSelectPlayer;
	}


	public String[] getPlayersList() {
		return playersList;
	}


	public void setPlayersList(String[] playersList) {
		this.playersList = playersList;
	}


	

	public JTextField getTextFieldMap() {
		return textFieldMap;
	}


	public void setTextFieldMap(JTextField textFieldMap) {
		this.textFieldMap = textFieldMap;
	}


	public JButton getButtonPlayGame() {
		return buttonPlayGame;
	}


	public void setButtonPlayGame(JButton buttonPlayGame) {
		this.buttonPlayGame = buttonPlayGame;
	}


	public String getCopynoOfPlayers() {
		return CopynoOfPlayers;
	}


	public void setCopynoOfPlayers(String copynoOfPlayers) {
		CopynoOfPlayers = copynoOfPlayers;
	}


	public Map getsCarryMapForward() {
		return sCarryMapForward;
	}


	public void setsCarryMapForward(Map sCarryMapForward) {
		this.sCarryMapForward = sCarryMapForward;
	}


	public JTree getMapTree() {
		return mapTree;
	}


	public void setMapTree(JTree mapTree) {
		this.mapTree = mapTree;
	}


	public JScrollPane getTreeScrollPane() {
		return treeScrollPane;
	}


	public void setTreeScrollPane(JScrollPane treeScrollPane) {
		this.treeScrollPane = treeScrollPane;
	}


	public NewGameController getNewGameController() {
		return newGameController;
	}


	public void setNewGameController(NewGameController newGameController) {
		this.newGameController = newGameController;
	}


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
				// get the path of the file.
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				// if the path of the selected file is empty.
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				} else {
					if (ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map")) {

						File f = new File(ImportFileName);
						sFileName = f.getName();
						sLocationWhereFileisKept = ImportFileName;
						sAppendParam = sFileName + "^" + sLocationWhereFileisKept;
						JOptionPane.showMessageDialog(null, "File in Correct format");
					} else {
						JOptionPane.showMessageDialog(null, "File name invalid");
						String sPrint = ImportFileName.trim().substring(ImportFileName.length() - 4);
						System.out.println(sPrint);
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return sLocationWhereFileisKept;

	}


	public void GameModelWindowMade(Map sCarryMapForward, Player[] player) {
		// TODO Auto-generated method stub
		dispose();
		GameModelCreation gameModel = new GameModelCreation(sCarryMapForward, player);
		GameWindowScreen GameWindowScreen = new GameWindowScreen(sCarryMapForward,player);
		GameWindowScreen.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}
