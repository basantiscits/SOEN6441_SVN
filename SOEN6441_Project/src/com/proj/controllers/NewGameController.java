package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.PlayNewGame;

/**
 * New Game Controller Class
 * @author Aman
 * @since 10 Feb 2019
 * @version 1.0
 */
public class NewGameController implements ActionListener {
	
	private PlayNewGame playNewGame;
	private String noOfPlayers;
	private String sPathFileName = "";
	private Map sCarryMapForward = new Map();
	private Player[] player ;
	boolean sCopyisMapValid=true;
	
	/**
	 * constructor for new game controller
	 * @param playNewGame play new game object
	 */
	public NewGameController(PlayNewGame playNewGame) {
		
		this.playNewGame = playNewGame;
	}
	
	/**
	 * action performed to successfully load map & select number of players
	 * @param event action event that triggers the response
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		boolean isMapValid=true;
		noOfPlayers = (String) playNewGame.getComboBoxSelectPlayer().getSelectedItem();
		
		if (event.getSource().equals(playNewGame.getButtonbrowse())) {
			if(noOfPlayers.equals("  --Select--  "))
			{
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else
			{
				Map existingMap = new Map();
				
				PlayNewGame objPlayNewGame= new PlayNewGame();
				MapTools sFunctions= new MapTools();
				sPathFileName = sFunctions.pickMapFile(existingMap);
				//sPathFileName=objPlayNewGame.ImportFileName("");
				if (sPathFileName == null) {
					

				} else {
					isMapValid = sFunctions.parseAndValidateMap(existingMap,Integer.parseInt(noOfPlayers));
					sCopyisMapValid=isMapValid;

					if(isMapValid)
					{
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
				
					}
					sCarryMapForward = existingMap;
					playNewGame.getTextFieldMap().setText(sPathFileName);
				}
				
				
			}

		

		} else if ((event.getSource()).equals(playNewGame.getButtonPlayGame())) {
			System.out.println("Play Game Button");
			noOfPlayers = (String) playNewGame.getComboBoxSelectPlayer().getSelectedItem();
			if(sCopyisMapValid)
			{
				JOptionPane.showMessageDialog(null, "Map successfully loaded");
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
					
						
						playNewGame.GameModelWindowMade(sCarryMapForward,player);
					}
					
					
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Map selected");
		
			}
			
			
			

		}

	}

	/**
	 * initializes player models and sets total number of armies based on number of players 
	 * @param noOfPlayers total number of players in the game
	 * @param sCarryMapForward map constructed so far 
	 * @param comboSelectedPlayers number of players selected
	 * @return players array object of type Player
	 */
	public Player[] initializingPlayerModels(int noOfPlayers, Map sCarryMapForward, String[] comboSelectedPlayers) {
		Player[] players = new Player[noOfPlayers];
		int pickedNumber = 0;
		Continent[] continents = new Continent[sCarryMapForward.getContinents().size()];
		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new Player("Player" + String.valueOf(value));
			
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
					}
				   
					countryModelList.remove(pickedNumber);


				}
			}
		}
		
		System.out.println(players);
		return players;

	}
	
	

}
