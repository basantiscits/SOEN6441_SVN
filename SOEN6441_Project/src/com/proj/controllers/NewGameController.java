package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

import javax.swing.JOptionPane;


import com.proj.models.Aggressive;
import com.proj.models.Benevolent;
import com.proj.models.Cheater;
import com.proj.models.RandomPlayer;
import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Human;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.models.PlayerType;
import com.proj.utilites.MapTools;
import com.proj.views.GameWindowScreen;
import com.proj.views.PlayNewGame;

/**
 * New Game Controller Class
 * 
 * @author Aman
 * @since 10 Feb 2019
 * @version 1.0
 */
public class NewGameController implements ActionListener {
	private PlayNewGame playNewGame;
	private GameModelCreation gameModel;
	private String noOfPlayers;
	private String sPathFileName = "";
	private Map sCarryMapForward = new Map();
	private Player[] player;
	boolean sCopyisMapValid = true;

	/**
	 * game model creation
	 * 
	 * @return game model
	 */
	public GameModelCreation getGameModel() {
		return gameModel;
	}

	/**
	 * setter for game model
	 * 
	 * @param gameModel
	 *            Object of GameModelCreation class
	 */
	public void setGameModel(GameModelCreation gameModel) {
		this.gameModel = gameModel;
	}

	/**
	 * constructor for new game controller
	 * 
	 * @param playNewGame
	 *            play new game object
	 */
	public NewGameController(PlayNewGame playNewGame) {
		this.playNewGame = playNewGame;
	}
	public NewGameController(){}

	/**
	 * action performed to successfully load map, select number of players
	 * 
	 * @param event
	 *            action event that triggers the response
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		boolean isMapValid = true;
		Map existingMap = new Map();
		System.out.println("actionPerformed");
		noOfPlayers = (String) playNewGame.getComboBoxSelectPlayer().getSelectedItem();
		if(noOfPlayers.equalsIgnoreCase("  --Select--  ")) {
			playNewGame.getPlayerLable1().setVisible(false);
			playNewGame.getPlayerLable2().setVisible(false);
			playNewGame.getPlayerLable3().setVisible(false);
			playNewGame.getPlayerLable4().setVisible(false);
			playNewGame.getPlayerLable5().setVisible(false);
			playNewGame.getPlayerLable6().setVisible(false);
			playNewGame.getComboBoxSelectPlayer1().setVisible(false);
			playNewGame.getComboBoxSelectPlayer2().setVisible(false);
			playNewGame.getComboBoxSelectPlayer3().setVisible(false);
			playNewGame.getComboBoxSelectPlayer4().setVisible(false);
			playNewGame.getComboBoxSelectPlayer5().setVisible(false);
			playNewGame.getComboBoxSelectPlayer6().setVisible(false);
		}
		else if (noOfPlayers.trim().equalsIgnoreCase("2")) {
			playNewGame.getPlayerLable1().setVisible(true);
			playNewGame.getPlayerLable2().setVisible(true);
			playNewGame.getPlayerLable3().setVisible(false);
			playNewGame.getPlayerLable4().setVisible(false);
			playNewGame.getPlayerLable5().setVisible(false);
			playNewGame.getPlayerLable6().setVisible(false);
			playNewGame.getComboBoxSelectPlayer1().setVisible(true);
			playNewGame.getComboBoxSelectPlayer2().setVisible(true);
			playNewGame.getComboBoxSelectPlayer3().setVisible(false);
			playNewGame.getComboBoxSelectPlayer4().setVisible(false);
			playNewGame.getComboBoxSelectPlayer5().setVisible(false);
			playNewGame.getComboBoxSelectPlayer6().setVisible(false);
		}
		else if(noOfPlayers.trim().equalsIgnoreCase("3"))
		{
			playNewGame.getPlayerLable1().setVisible(true);
			playNewGame.getPlayerLable2().setVisible(true);
			playNewGame.getPlayerLable3().setVisible(true);
			playNewGame.getPlayerLable4().setVisible(false);
			playNewGame.getPlayerLable5().setVisible(false);
			playNewGame.getPlayerLable6().setVisible(false);
			playNewGame.getComboBoxSelectPlayer1().setVisible(true);
			playNewGame.getComboBoxSelectPlayer2().setVisible(true);
			playNewGame.getComboBoxSelectPlayer3().setVisible(true);
			playNewGame.getComboBoxSelectPlayer4().setVisible(false);
			playNewGame.getComboBoxSelectPlayer5().setVisible(false);
			playNewGame.getComboBoxSelectPlayer6().setVisible(false);
		}
		else if(noOfPlayers.trim().equalsIgnoreCase("4"))
		{
			playNewGame.getPlayerLable1().setVisible(true);
			playNewGame.getPlayerLable2().setVisible(true);
			playNewGame.getPlayerLable3().setVisible(true);
			playNewGame.getPlayerLable4().setVisible(true);
			playNewGame.getPlayerLable5().setVisible(false);
			playNewGame.getPlayerLable6().setVisible(false);
			playNewGame.getComboBoxSelectPlayer1().setVisible(true);
			playNewGame.getComboBoxSelectPlayer2().setVisible(true);
			playNewGame.getComboBoxSelectPlayer3().setVisible(true);
			playNewGame.getComboBoxSelectPlayer4().setVisible(true);
			playNewGame.getComboBoxSelectPlayer5().setVisible(false);
			playNewGame.getComboBoxSelectPlayer6().setVisible(false);
			
			
		}
		else if(noOfPlayers.trim().equalsIgnoreCase("5"))
		{
			playNewGame.getPlayerLable1().setVisible(true);
			playNewGame.getPlayerLable2().setVisible(true);
			playNewGame.getPlayerLable3().setVisible(true);
			playNewGame.getPlayerLable4().setVisible(true);
			playNewGame.getPlayerLable5().setVisible(true);
			playNewGame.getPlayerLable6().setVisible(false);
			playNewGame.getComboBoxSelectPlayer1().setVisible(true);
			playNewGame.getComboBoxSelectPlayer2().setVisible(true);
			playNewGame.getComboBoxSelectPlayer3().setVisible(true);
			playNewGame.getComboBoxSelectPlayer4().setVisible(true);
			playNewGame.getComboBoxSelectPlayer5().setVisible(true);
			playNewGame.getComboBoxSelectPlayer6().setVisible(false);
			
		}
		else if(noOfPlayers.trim().equalsIgnoreCase("6"))
		{
			playNewGame.getPlayerLable1().setVisible(true);
			playNewGame.getPlayerLable2().setVisible(true);
			playNewGame.getPlayerLable3().setVisible(true);
			playNewGame.getPlayerLable4().setVisible(true);
			playNewGame.getPlayerLable5().setVisible(true);
			playNewGame.getPlayerLable6().setVisible(true);
			playNewGame.getComboBoxSelectPlayer1().setVisible(true);
			playNewGame.getComboBoxSelectPlayer2().setVisible(true);
			playNewGame.getComboBoxSelectPlayer3().setVisible(true);
			playNewGame.getComboBoxSelectPlayer4().setVisible(true);
			playNewGame.getComboBoxSelectPlayer5().setVisible(true);
			playNewGame.getComboBoxSelectPlayer6().setVisible(true);
			
		}

		
			

		if (event.getSource().equals(playNewGame.getButtonbrowse())) {
			if (noOfPlayers.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			} else {
				PlayNewGame objPlayNewGame = new PlayNewGame();
				MapTools sFunctions = new MapTools();
				sPathFileName = sFunctions.pickMapFile(existingMap);
				if (sPathFileName == null) {
				} else {
					isMapValid = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayers));
					sCopyisMapValid = isMapValid;
					if (isMapValid) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
					sCarryMapForward = existingMap;
					playNewGame.getTextFieldMap().setText(sPathFileName);
				}
			}
		} else if ((event.getSource()).equals(playNewGame.getButtonPlayGame())) {
			System.out.println("Play Game Button");
			noOfPlayers = (String) playNewGame.getComboBoxSelectPlayer().getSelectedItem();
			if (sCopyisMapValid) {
				if (noOfPlayers.equals("  --Select--  ")) {
					System.out.println("Select is pressed");
					JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
				} else {
					if (sPathFileName.equals(null) || (sPathFileName).equals("")) {
						JOptionPane.showMessageDialog(null, "Please upload the . map file");
					} else {
						String[] comboSelectedPlayers = new String[Integer.parseInt(noOfPlayers)];
						if (Integer.parseInt(noOfPlayers) >= 2) {
							comboSelectedPlayers[0] = (String) playNewGame.getComboBoxSelectPlayer1().getSelectedItem();
							comboSelectedPlayers[1] = (String) playNewGame.getComboBoxSelectPlayer2().getSelectedItem();
						}
						if (Integer.parseInt(noOfPlayers) >= 3) {
							comboSelectedPlayers[2] = (String) playNewGame.getComboBoxSelectPlayer3().getSelectedItem();
						}
						if (Integer.parseInt(noOfPlayers) >= 4) {
							comboSelectedPlayers[3] = (String) playNewGame.getComboBoxSelectPlayer4().getSelectedItem();
						}
						if (Integer.parseInt(noOfPlayers) >= 5) {
							comboSelectedPlayers[5] = (String) playNewGame.getComboBoxSelectPlayer5().getSelectedItem();
						}
						if (Integer.parseInt(noOfPlayers) == 6) {
							comboSelectedPlayers[6] = (String) playNewGame.getComboBoxSelectPlayer6().getSelectedItem();
						}
						player = initializingPlayerModels(Integer.parseInt(noOfPlayers), sCarryMapForward, comboSelectedPlayers);
						this.gameModel = new GameModelCreation(sCarryMapForward, player);
						providingGameModelToPlayer();
						playNewGame.GameModelWindowMade(sCarryMapForward, player, gameModel);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Map selected");
				System.out.println(existingMap.getErrorMessage());
			}
		}

	}

	/**
	 * Providing game model to player
	 */
	public void providingGameModelToPlayer() {
		for (Player players : player) {
			players.setGameModel(gameModel);
		}

	}

	/**
	 * initialize number of armies
	 * 
	 * @param players
	 *            Array object of Player class
	 * @param noOfPlayers
	 *            number of players
	 */
	public void initializeNumberOfArmies(PlayerType[] playerTypes, Player[] players, int noOfPlayers) {
			for (int j = 0; j < noOfPlayers; j++) {
				int value = j + 1;
				players[j] = new Player("Player" + String.valueOf(value), playerTypes[j]);
				
				if (playerTypes[j] == PlayerType.Human) {
					players[j].setStrategy(new Human());
				}
				else if (playerTypes[j] == PlayerType.Aggressive) {
					players[j].setStrategy(new Aggressive());
				}
				else if (playerTypes[j] == PlayerType.Benevolent) {
					players[j].setStrategy(new Benevolent());
				}
				else if (playerTypes[j] == PlayerType.Random) {
					players[j].setStrategy(new RandomPlayer());
				}
				else if (playerTypes[j] == PlayerType.Cheater) {
					players[j].setStrategy(new Cheater());
				}
				
				
				if (noOfPlayers == 3) {
					players[j].setNoOfArmiesOwned(35);
				}
				else if (noOfPlayers == 4) {
					players[j].setNoOfArmiesOwned(30);
				}
				else if (noOfPlayers == 5) {
					players[j].setNoOfArmiesOwned(25);
				}
				else if (noOfPlayers == 6) {
					players[j].setNoOfArmiesOwned(20);
				}
			}
	}

	/**
	 * initializes player models and sets total number of armies based on number
	 * of players
	 * 
	 * 
	 * @param noOfPlayers
	 *            total number of players in the game
	 * @param sCarryMapForward
	 *            map constructed so far
	 * @param comboSelectedPlayers
	 *            number of players selected
	 * @return players array object of type Player
	 */
	public Player[] initializingPlayerModels(int noOfPlayers, Map sCarryMapForward, String[] comboSelectedPlayers) {
		Player[] players = new Player[noOfPlayers];
		int pickedNumber = 0;
		Continent[] continents = new Continent[sCarryMapForward.getContinents().size()];
		PlayerType[] playerTypes = getPlayerTypes(noOfPlayers, comboSelectedPlayers);
		if(noOfPlayers == 2) {
			System.out.println("length: "+players.length);
			Player []newList=new Player[noOfPlayers+1];
			for (int j = 0; j < 2; j++) {
				int value = j + 1;
				newList[j] = new Player("Player" + String.valueOf(value),playerTypes[j]);
				if (playerTypes[j] == PlayerType.Human) {
					newList[j].setStrategy(new Human());
				}
				else if (playerTypes[j] == PlayerType.Aggressive) {
					newList[j].setStrategy(new Aggressive());
				}
				else if (playerTypes[j] == PlayerType.Benevolent) {
					newList[j].setStrategy(new Benevolent());
				}
				else if (playerTypes[j] == PlayerType.Random) {
					newList[j].setStrategy(new RandomPlayer());
				}
				else if (playerTypes[j] == PlayerType.Cheater) {
					newList[j].setStrategy(new Cheater());
				}
				newList[j].setNoOfArmiesOwned(40);

			}
			newList[2] = new Player("Neutral",PlayerType.Aggressive);
			newList[2].setStrategy(new Aggressive());
			newList[2].setNoOfArmiesOwned(40);
			for(Player p : newList) {
				System.out.println("p1: "+p.getPlayerName());
			}
			players = newList;
			for(Player p : players) {
				System.out.println("p2: "+p.getPlayerName());
			}
		}
		else {
			initializeNumberOfArmies(playerTypes, players, noOfPlayers);
		}
		Random RandomAllocationCountries = new Random();
		List<Country> countryModelList = new ArrayList<Country>();
		List<Continent> continentModelList = new ArrayList<Continent>();
		continentModelList.addAll(sCarryMapForward.getContinents());
		for (Continent continent : continentModelList) {
			countryModelList.addAll(continent.getCountriesPresent());
		}
		if(players[0]==null) {
			System.out.println("Yes Null");
		}
		while (!(countryModelList.isEmpty())) {
			for (int count1 = 0; count1 < players.length; count1++) {
				if (!(countryModelList.isEmpty())) {
					pickedNumber = RandomAllocationCountries.nextInt(countryModelList.size());
					Country countryModelTest = countryModelList.get(pickedNumber);
					if (countryModelTest != null) {
						players[count1].addCountry(countryModelTest);
						countryModelTest.setOwnedBy(players[count1]);
						countryModelTest.addNoOfArmiesCountry();
						players[count1].reduceArmyInPlayer();
						System.out.println("Random Allocated to Players " + count1
								+ countryModelList.get(pickedNumber).getCountryName() + " ->"
								+ countryModelTest.getNoOfArmiesPresent());
					}
					countryModelList.remove(pickedNumber);
				}
			}
		}

		for (Continent cont : sCarryMapForward.getContinents()) {
			for (Player p : players) {
				if (p.getCountriesOwned().containsAll(cont.getCountriesPresent())) {
					p.getContinentsOwned().add(cont);
					break;
				}
			}
		}

		System.out.println(players);
		return players;
	}
	
	public PlayerType[] getPlayerTypes(int noOfPlayers, String[] comboSelectedPlayers) {
		
		PlayerType[] playerTypes = new PlayerType[noOfPlayers];
		if (noOfPlayers >= 2) {
			playerTypes[0] = getPlayerType(comboSelectedPlayers[0]);
			playerTypes[1] = getPlayerType(comboSelectedPlayers[1]);
		}
		if (noOfPlayers >= 3) {
			playerTypes[2] = getPlayerType(comboSelectedPlayers[2]);
		}
		if (noOfPlayers >= 4) {
			playerTypes[3] = getPlayerType(comboSelectedPlayers[3]);
		}
		if (noOfPlayers >= 5) {
			playerTypes[4] = getPlayerType(comboSelectedPlayers[4]);
		}
		if(noOfPlayers == 6) {
			playerTypes[5] = getPlayerType(comboSelectedPlayers[4]);
		}

		return playerTypes;
		
	}
	
	public PlayerType getPlayerType(String type) {
		PlayerType playerType = PlayerType.Human;
		if (type.trim().equalsIgnoreCase("Human"))
			playerType = PlayerType.Human;
		if (type.trim().equalsIgnoreCase("Aggressive"))
			playerType = PlayerType.Aggressive;
		if (type.trim().equalsIgnoreCase("Benevolent"))
			playerType = PlayerType.Benevolent;
		if (type.trim().equalsIgnoreCase("Random"))
			playerType = PlayerType.Random;
		if (type.trim().equalsIgnoreCase("Cheater"))
			playerType = PlayerType.Cheater;
		return playerType;
	}
}
