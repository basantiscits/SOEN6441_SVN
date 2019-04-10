package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.GameWindowScreen;
import com.proj.views.TournamentView;

/**
 * Tournament controller class
 * @author Ofreish
 * @since 20 Mar 2019
 * @version 1.2
 */
public class TournamentController  implements ActionListener, Serializable {
	private TournamentView tournamentView;
	public String noOfMaps;
	public String noOfPlayer;
	public String noOfGames;
	public String noOfTurns;
	private String sPathFileName1 = "";
	private String sPathFileName2 = "";
	private String sPathFileName3 = "";
	private String sPathFileName4 = "";
	private String sPathFileName5 = "";	

	
	public String sPlayerBehaviour1="";
	public String sPlayerBehaviour2="";
	public String sPlayerBehaviour3="";
	public String sPlayerBehaviour4="";
	private static final long serialVersionUID = 45443434343L;

	public ArrayList<String>addFileName;
	public ArrayList<String>addPlayerBehaviourName;
	public ArrayList<Map> maps;
	private Player players[];
	public NewGameController newGameController;
	public ArrayList<String> result;

	
	/**
	 * Constructor of Tournament Controller
	 * @param tournamentView Object of TournamentView class
	 */
	public TournamentController(TournamentView tournamentView) {
		this.tournamentView = tournamentView;
		addFileName=new ArrayList<String>();
		addPlayerBehaviourName=new ArrayList<String>();
		maps=new ArrayList<Map>();
		result=new ArrayList<String>();
	}
	
	/**
	 * Constructor of Tournament Controller test case
	 */
	public TournamentController() {
		addFileName=new ArrayList<String>();
		addPlayerBehaviourName=new ArrayList<String>();
		maps=new ArrayList<Map>();
		result = new ArrayList<String>();
	}

	/**
	 * Action performed
	 * @param event Object of ActionEvent class
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		boolean isFileChecked;
		boolean isPlayerAdded;
		boolean isMapValid1 = true;
		boolean isMapValid2 = true;
		boolean isMapValid3 = true;
		boolean isMapValid4 = true;
		boolean isMapValid5 = true;
		Map existingMap = new Map();
		System.out.println("actionPerformed");
		noOfMaps = (String) tournamentView.getComboBoxSelectMap().getSelectedItem();
		noOfPlayer=(String) tournamentView.getComboBoxSelectNoOfPlayer().getSelectedItem();
		noOfGames=(String) tournamentView.getcomboBoxSelectNoOfGames().getSelectedItem();
		noOfTurns=(String) tournamentView.getcomboBoxSelectNoOfTurns().getSelectedItem();
		sPlayerBehaviour1=(String) tournamentView.getcomboBoxBehaviourplayer1().getSelectedItem();
		sPlayerBehaviour2=(String) tournamentView.getcomboBoxBehaviourplayer2().getSelectedItem();
		sPlayerBehaviour3=(String) tournamentView.getcomboBoxBehaviourplayer3().getSelectedItem();
		sPlayerBehaviour4=(String) tournamentView.getcomboBoxBehaviourplayer4().getSelectedItem();
		
		
		System.out.println("No of Maps Selected : "+noOfMaps);
		System.out.println("Player Selected : "+noOfPlayer);
		if (noOfMaps.equalsIgnoreCase("  --Select--  ")) {
			    tournamentView.getbuttonbrowse1().setVisible(false);
				tournamentView.getbuttonbrowse2().setVisible(false);
				tournamentView.getbuttonbrowse3().setVisible(false);
				tournamentView.getbuttonbrowse4().setVisible(false);
				tournamentView.getbuttonbrowse5().setVisible(false);
		}
		else if (noOfMaps.trim().equalsIgnoreCase("1")) { 
			tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(false);
			tournamentView.getbuttonbrowse3().setVisible(false);
			tournamentView.getbuttonbrowse4().setVisible(false);
			tournamentView.getbuttonbrowse5().setVisible(false);
		}
		else if(noOfMaps.trim().equalsIgnoreCase("2")){
			tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(false);
			tournamentView.getbuttonbrowse4().setVisible(false);
			tournamentView.getbuttonbrowse5().setVisible(false);
		}
        else if(noOfMaps.trim().equalsIgnoreCase("3")){
        	tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
			tournamentView.getbuttonbrowse4().setVisible(false);
			tournamentView.getbuttonbrowse5().setVisible(false);
		}
        else if(noOfMaps.trim().equalsIgnoreCase("4")){
        	tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
			tournamentView.getbuttonbrowse4().setVisible(true);
			tournamentView.getbuttonbrowse5().setVisible(false);
			
		}
       else if(noOfMaps.trim().equalsIgnoreCase("5")){
    	    tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
			tournamentView.getbuttonbrowse4().setVisible(true);
			tournamentView.getbuttonbrowse5().setVisible(true);
        }
		
		if(noOfPlayer.equalsIgnoreCase("  --Select--  ")) {
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(false);
			
		}
       else if(noOfPlayer.trim().equalsIgnoreCase("2")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(false);
			
		}
		else if(noOfPlayer.trim().equalsIgnoreCase("3")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(false);
			
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("4")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			
			
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("5")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("6")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			
		}
		if (event.getSource().equals(tournamentView.getbuttonbrowse1())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName1 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName1 == null) {
				} else {
					isMapValid1 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid1) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName1);
						maps.add(existingMap);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}

			    }
			}				
		}

		
		if (event.getSource().equals(tournamentView.getbuttonbrowse2())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName2 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName2 == null) {
				} else {
					isMapValid2 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid2) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName2);
						maps.add(existingMap);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}

			     }
			}				

		}		
		if (event.getSource().equals(tournamentView.getbuttonbrowse3())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName3 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName3 == null) {}
				else {
					isMapValid3 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid3) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName3);
						maps.add(existingMap);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
				}
			}	
		}
		if (event.getSource().equals(tournamentView.getbuttonbrowse4())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName4 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName4 == null) {} 
				else {
					isMapValid4 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid4) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName4);
						maps.add(existingMap);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
				}
			}		
		}
		if (event.getSource().equals(tournamentView.getbuttonbrowse5())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else {
				MapTools sFunctions = new MapTools();
				sPathFileName5 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName5 == null) {}
				else {
					isMapValid5 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid5) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName5);
						maps.add(existingMap);
					} 
					else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
				}
			}	
		}
		if (event.getSource().equals(tournamentView.getbuttonPlayGameTournament())) {
			if(checkParameters()){
				intitiateTournament();
			}
			else{
				JOptionPane.showMessageDialog(null, "Reselect required parameters.");
			}
		}
	}
	
	/**
	 * initiate tournament
	 */
	public void intitiateTournament() {		
		String strategies[]=addPlayerBehaviourName.toArray(new String[0]);
		for(String s : strategies) {
			System.out.println(s);
		}
		System.out.println("No of players: "+noOfPlayer);
		System.out.println("No of Games: "+noOfGames);
		System.out.println("No of Maps: "+noOfMaps);
		System.out.println("No of turns: "+noOfTurns);
		Map m1;
		int j=0;
		ArrayList<Map> duplicateMap = new ArrayList<Map>();
		for(Map m : maps) {
			duplicateMap.add(m);
		}
		for(Map m: maps){
			System.out.println("Map name: "+m.getName()+" started");
			for (int i=0;i<Integer.parseInt(noOfGames);i++){
				System.out.println("Game "+(i+1)+" started for map "+m.getName());
				players=new Player[Integer.parseInt(noOfPlayer)];
				newGameController=new NewGameController();
				players=newGameController.initializingPlayerModels(Integer.parseInt(noOfPlayer), m, strategies);
				GameModelCreation gameModelCreation =new GameModelCreation(m,players);
				providingGameModelToPlayer(players, gameModelCreation);
				gameModelCreation.incrementTurn();
				int army = players[players.length-1].getNoOfArmiesOwned();
				for(int p = 0; p < army; p++) {
					for(Player P : gameModelCreation.getPlayer()) {
						P.initialArmyAllocation(gameModelCreation);
					}
				}
				
				int turns = Integer.parseInt(noOfTurns);
				while(gameModelCreation.getPlayer().length>1 && turns*gameModelCreation.getPlayer().length>0 ) {
					gameModelCreation.getCurrPlayer().intializeReinforcementArmies(gameModelCreation);
					turns--;
					if(turns==0) {
						break;
					}
				}

				if(gameModelCreation.getPlayer().length == 1) {
					result.add("For Map: "+m.getName()+",Game no.: "+(i+1)+" is won by "+gameModelCreation.getPlayer()[0].getPlayerType().toString());
				}
				else {
					result.add("For Map: "+m.getName()+",Game no.: "+(i+1)+" is "+"Draw");
				}
				gameModelCreation.setTurn(0);
				gameModelCreation = null;
				newGameController= null;
				players=null;

				for(Continent continent:m.getContinents()){
					for(Country country:continent.getCountriesPresent()){
						country.setNoOfArmiesPresent(0);
						country.setOwnedBy(null);
					}
				}
			}
		}
		System.out.println("Results: ");
		System.out.print("M: ");
		for(int i = 0; i<maps.size(); i++) {
			if(i == maps.size()-1) {
				System.out.print(maps.get(i).getName());
			}
			else {
				System.out.print(maps.get(i).getName()+", ");
			}
		}
		System.out.println();
		System.out.print("P: ");
		for(int i = 0; i<addPlayerBehaviourName.size(); i++) {
			if(i == addPlayerBehaviourName.size()-1) {
				System.out.print(addPlayerBehaviourName.get(i));
			}
			else {
				System.out.print(addPlayerBehaviourName.get(i)+", ");
			}
		}
		System.out.println();
		System.out.println("G: "+noOfGames);
		System.out.println("D: "+noOfTurns);
		
		for(String s : result) {
			System.out.println(s);
		}
		
		if(tournamentView!=null){
			System.exit(0);
		}
		
	}

	/**
	 * check parameters
	 * @return false if no parameters are selected and true otherwise
	 */
	public boolean checkParameters() {
		if (noOfMaps.equals("  --Select--  ")) {
			JOptionPane.showMessageDialog(null, "Please select no. of Map to play game");
			return false;
		}
		if (noOfPlayer.equals("  --Select--  ")) {
			JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			return false;
		}
		if (noOfGames.equals("  --Select--  ")) {
			JOptionPane.showMessageDialog(null, "Please select no. of games");
			return false;
		}
		if (noOfTurns.equals("  --Select--  ")) {
			JOptionPane.showMessageDialog(null, "Please select no. of turns");
			return false;
		}
		if(addFileName.size()!=Integer.parseInt(noOfMaps)){
			System.out.println(addFileName);
			JOptionPane.showMessageDialog(null, "Not all maps are successfully loaded");
			addFileName.clear();
			maps.clear();
			return false;
		}
		fncCheckPlayerBehaviour();
		if(addPlayerBehaviourName.size()!=Integer.parseInt(noOfPlayer)){
			JOptionPane.showMessageDialog(null, "Not all players are selected.");
			addPlayerBehaviourName.clear();
			return false;
		}
		return true;
	
	}
	
	/**
	 * providing game model to player
	 * @param player Array object of Player class
	 * @param gameModel Object of GameModelCreation class
	 */
	public void providingGameModelToPlayer(Player[] player, GameModelCreation gameModel) {
		for (Player players : player) {
			players.setGameModel(gameModel);
		}

	}
 
	/**
	 * Function to check player behaviour
	 * 
	 */
	private void fncCheckPlayerBehaviour() {
		boolean isReturnBehaviour;
		addPlayerBehaviourName.clear();
		if(sPlayerBehaviour1!=("  --Select--  ")) {
			addPlayerBehaviourName.add(sPlayerBehaviour1);
		}
		if(sPlayerBehaviour2!=("  --Select--  ")) {
			addPlayerBehaviourName.add(sPlayerBehaviour2);
		}
		if(sPlayerBehaviour3!=("  --Select--  ")) {
			addPlayerBehaviourName.add(sPlayerBehaviour3);
		}
		if(sPlayerBehaviour4!=("  --Select--  ")) {
			addPlayerBehaviourName.add(sPlayerBehaviour4);
		}
		
	}

	/**
	 * Function to check the uploaded file
	 * @param noOfMapsSelected number of maps selected
	 * @return true if number of maps selected is equal to number of files chosen else false
	 */
	private boolean fncCheckFileUploaded(String noOfMapsSelected) {
		boolean isReturn=false;
		System.out.println("No of File added to browse button : " +addFileName.size());
		if(noOfMapsSelected.equals(String.valueOf(addFileName.size()))) {
			isReturn=true;
		}
		else {
			isReturn=false;
		}
		return isReturn;
	}
}

	
