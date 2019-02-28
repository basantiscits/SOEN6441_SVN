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
import javax.swing.tree.DefaultMutableTreeNode;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;
import com.proj.utilites.MapTools;

public class PlayNewGame extends JFrame implements ActionListener {
	String sFileName = "";
	Player[] player ;
	String sLocationWhereFileisKept = "";
	String sAppendParam = "";
	private JButton buttonbrowse;
	private JComboBox<String> comboBoxSelectPlayer;
	private String[] playersList = new String[] { "  --Select--  ","3", "4", "5" };
	String sPathFileName = "";
	private JTextField textFieldMap;
	private JButton buttonPlayGame;
	String noOfPlayers;
	String CopynoOfPlayers;
	Map sCarryMapForward = new Map();
	private JTree mapTree;
	private JScrollPane treeScrollPane;

	public PlayNewGame() {

		JLabel labelHeading = new JLabel();
		labelHeading.setText("THE RISK GAME");
		labelHeading.setBounds(Constants.WIDTH / 2 + 120, 0, 100, 30);
		add(labelHeading);

		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select no of Players :");
		// labelSelectPlayer.setBounds(40, 80, 400, 180);
		labelSelectPlayer.setBounds(40, 20, 400, 180);
		add(labelSelectPlayer);

		comboBoxSelectPlayer = new JComboBox<>(playersList);
		comboBoxSelectPlayer.setBounds(380, 90, 90, 30);
		comboBoxSelectPlayer.addActionListener(this);
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
		buttonbrowse.addActionListener(this);
		add(buttonbrowse);

		buttonPlayGame = new JButton();
		buttonPlayGame.setText("Play Now");
		buttonPlayGame.setBounds(380, 400, 210, 30);
		buttonPlayGame.addActionListener(this);
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


	// TODO Auto-generated method stub
	/*
	 * sPathFileName = ImportFileName(""); textFieldMap.setText(sPathFileName);
	 */

	private String ImportFileName(String sReturnFileAndLoc) {

		
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
			// System.out.println(sAppendParam);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return sLocationWhereFileisKept;

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		boolean isMapValid=true;
		noOfPlayers = (String) comboBoxSelectPlayer.getSelectedItem();
		
		if (event.getSource().equals(buttonbrowse)) {
			if(noOfPlayers.equals("  --Select--  "))
			{
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else
			{
				Map existingMap = new Map();
				MapTools sFunctions= new MapTools();
				sPathFileName = sFunctions.pickMapFile(existingMap);
				if (sPathFileName == null) {
					

				} else {
					isMapValid = sFunctions.parseAndValidateMap(existingMap,Integer.parseInt(noOfPlayers));
					

					if(isMapValid)
					{
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
				
					}
					sCarryMapForward = existingMap;
					textFieldMap.setText(sPathFileName);
				}
				//isMapValid=sFunctions.parseAndValidateMap(existingMap,Integer.parseInt(noOfPlayers));
				
				
			}

		

		} else if ((event.getSource()).equals(buttonPlayGame)) {
			System.out.println("Play Game Button");
			noOfPlayers = (String) comboBoxSelectPlayer.getSelectedItem();
			// System.out.println(noOfPlayers);
			if(noOfPlayers.equals("  --Select--  "))
			{
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else
			{
				if (sPathFileName.equals(null) || (sPathFileName).equals("")) {
					JOptionPane.showMessageDialog(null, "Please upload the . map file");
				}
				else
				{
					String[] comboSelectedPlayers = new String[Integer.parseInt(noOfPlayers)];
					player = initializingPlayerModels(Integer.parseInt(noOfPlayers), sCarryMapForward,
							comboSelectedPlayers);
				
					
					GameModelWindowMade(sCarryMapForward,player);
				}
				
				
			}
			

		}

	}

	private void GameModelWindowMade(Map sCarryMapForward, Player[] player) {
		// TODO Auto-generated method stub
		dispose();
		GameModelCreation gameModel = new GameModelCreation(sCarryMapForward, player);
		GameWindowScreen GameWindowScreen = new GameWindowScreen(sCarryMapForward,player);
		GameWindowScreen.setVisible(true);
	}

	// Assigning Player model Player No. with setting owner of Counties with 1
	// Army
	public Player[] initializingPlayerModels(int noOfPlayers, Map sCarryMapForward, String[] comboSelectedPlayers) {
		Player[] players = new Player[noOfPlayers];
		int pickedNumber = 0;
		Continent[] continents = new Continent[sCarryMapForward.getContinents().size()];
		// PlayerType[] playerTypes =
		// Utility.getPlayerTypeFromDropDown(noOfPlayers, comboSelectedPlayers);
		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new Player("Player" + String.valueOf(value));
			
			// By ofreish for adding initial armies per player
			if(noOfPlayers==3) {
				players[j].setNoOfArmiesOwned(25);
			}
			else if(noOfPlayers==4) {
				players[j].setNoOfArmiesOwned(20);
			}
			else if(noOfPlayers==5) {
				players[j].setNoOfArmiesOwned(15);
			}
		}
		Random RandomAllocationCountries = new Random();
		List<Country> countryModelList = new ArrayList<>();
		List<Continent> continentModelList = new ArrayList<>();
		continentModelList.addAll(sCarryMapForward.getContinents());
		for(Continent continent:continentModelList) {
			countryModelList.addAll(continent.getCountriesPresent());
		}
		
		while (!(countryModelList.isEmpty())) {
			for (int count1 = 0; count1 < noOfPlayers; count1++) {
				if (!(countryModelList.isEmpty())) {
					pickedNumber = RandomAllocationCountries.nextInt(countryModelList.size());
					Country countryModelTest = countryModelList.get(pickedNumber);
					if (countryModelTest != null) {
						players[count1].addCountry(countryModelTest);
						countryModelTest.setOwnedBy(players[count1]);
						countryModelTest.addNoOfArmiesCountry();
						
						players[count1].reduceArmyInPlayer();
						System.out.println("Random Allocated to Players "+ count1 +countryModelList.get(pickedNumber).getCountryName()+" ->"+countryModelTest.getNoOfArmiesPresent());
						//System.out.println();
					}
				   
					countryModelList.remove(pickedNumber);


				}
			}
		}
		
		System.out.println(players);
		return players;

	}
	

}
