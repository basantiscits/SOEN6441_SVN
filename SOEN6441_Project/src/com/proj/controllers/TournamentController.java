package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.GameWindowScreen;
import com.proj.views.TournamentView;

public class TournamentController  implements ActionListener, Serializable {
	private TournamentView tournamentView;
	private String noOfMaps;
	private String noOfPlayer;
	private String noOfGames;
	private String noOfTurns;
	private String sPathFileName1 = "";
	private String sPathFileName2 = "";
	private String sPathFileName3 = "";
	private String sPathFileName4 = "";
	private String sPathFileName5 = "";	
	private String sPlayerBehaviour1="";
	private String sPlayerBehaviour2="";
	private String sPlayerBehaviour3="";
	private String sPlayerBehaviour4="";
	private String sPlayerBehaviour5="";
	private String sPlayerBehaviour6="";
	ArrayList<String>addFileName;
	ArrayList<String>addPlayerBehaviourName;
	ArrayList<Map> maps;
	Player players[];
	NewGameController newGameController;
	

	public TournamentController(TournamentView tournamentView) {
		// TODO Auto-generated constructor stub
		this.tournamentView = tournamentView;
		addFileName=new ArrayList<String>();
		addPlayerBehaviourName=new ArrayList<String>();
		maps=new ArrayList<Map>();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
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
				if (sPathFileName3 == null) {
				} else {
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
				if (sPathFileName4 == null) {
				} else {
					isMapValid4 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid4) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName4);
						maps.add(existingMap);
					} else {
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
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName5 = sFunctions.pickMapFile(existingMap);
				if (sPathFileName5 == null) {
				} else {
					isMapValid5 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid5) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
						addFileName.add(sPathFileName5);
						maps.add(existingMap);
					} else {
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
	
	private void intitiateTournament() {
		// TODO Auto-generated method stub
		String strategies[]=addPlayerBehaviourName.toArray(new String[0]);
			players=new Player[Integer.parseInt(noOfPlayer)];
			newGameController=new NewGameController();
			for(Map m: maps){
				for (int i=0;i<Integer.parseInt(noOfGames);i++){
					players=newGameController.initializingPlayerModels(Integer.parseInt(noOfPlayer), m , strategies );
					GameModelCreation newGame=new GameModelCreation(m,players);
					GameWindowScreen g=new GameWindowScreen(newGame);
					//Method to play game
					for(Player p:players){
						System.out.println(p.getStrategy().getClass());
					}
				}
			}
		
	}

	private boolean checkParameters(){
		if (noOfMaps.equals("  --Select--  ")) {
			//System.out.println("Select is pressed :no of Maps");
			JOptionPane.showMessageDialog(null, "Please select no. of Map to play game");
			return false;
		}
		if (noOfPlayer.equals("  --Select--  ")) {
			//System.out.println("Select is pressed no Of Player");
			JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			return false;
		}
		if (noOfGames.equals("  --Select--  ")) {
			//System.out.println("Select is pressed : no of Games");
			JOptionPane.showMessageDialog(null, "Please select no. of games");
			return false;
		}
		if (noOfTurns.equals("  --Select--  ")) {
			//System.out.println("Select is pressed no of turns");
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
	
 
	private void fncCheckPlayerBehaviour() {
		// TODO Auto-generated method stub
		boolean isReturnBehaviour;
		addPlayerBehaviourName.clear();
		if(sPlayerBehaviour1!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour1);
		}
		if(sPlayerBehaviour2!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour2);
		}
		if(sPlayerBehaviour3!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour3);
		}
		if(sPlayerBehaviour4!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour4);
		}
		
		
//		System.out.println("No of File selected in player behaviour : " +addPlayerBehaviourName.size());
//		if(noOfPlayer2.equals(String.valueOf(addPlayerBehaviourName.size())))
//		{
//			isReturnBehaviour=true;
//		}
//		else
//		{
//			isReturnBehaviour=false;
//		}
//		
//		return isReturnBehaviour;
	}

	private boolean fncCheckFileUploaded(String noOfMapsSelected) {
		// TODO Auto-generated method stub
		boolean isReturn=false;
		System.out.println("No of File added to browse button : " +addFileName.size());
		if(noOfMapsSelected.equals(String.valueOf(addFileName.size())))
		{
			isReturn=true;
		}
		else
		{
			isReturn=false;
		}
		
		
		return isReturn;
	}
}

	
