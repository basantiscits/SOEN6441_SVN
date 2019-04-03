package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.proj.models.Map;
import com.proj.utilites.MapTools;
import com.proj.views.TournamentView;

public class TournamentController  implements ActionListener {
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
	ArrayList<String>addFileName= new ArrayList<String>();
	ArrayList<String>addPlayerBehaviourName= new ArrayList<String>();

	public TournamentController(TournamentView tournamentView) {
		// TODO Auto-generated constructor stub
		this.tournamentView = tournamentView;
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
		sPlayerBehaviour5=(String) tournamentView.getcomboBoxBehaviourplayer5().getSelectedItem();
		sPlayerBehaviour6=(String) tournamentView.getcomboBoxBehaviourplayer6().getSelectedItem();
		
		
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
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(false);
		}
       else if(noOfPlayer.trim().equalsIgnoreCase("2")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(false);
		}
		else if(noOfPlayer.trim().equalsIgnoreCase("3")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(false);
			
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("4")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(false);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(false);
			
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("5")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(false);
		}
        else if(noOfPlayer.trim().equalsIgnoreCase("6")){
        	tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer4().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer6().setVisible(true);
		}
		if (event.getSource().equals(tournamentView.getbuttonbrowse1())) {
			if (noOfPlayer.equals("  --Select--  ")) {
				System.out.println("Select is pressed");
				JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
			}
			else{
				MapTools sFunctions = new MapTools();
				sPathFileName1 = sFunctions.pickMapFile(existingMap);
				addFileName.add(sPathFileName1);
				if (sPathFileName1 == null) {
				} else {
					isMapValid1 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid1) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
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
				addFileName.add(sPathFileName2);
				if (sPathFileName2 == null) {
				} else {
					isMapValid2 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid2) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
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
				addFileName.add(sPathFileName3);
				if (sPathFileName3 == null) {
				} else {
					isMapValid3 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid3) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
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
				addFileName.add(sPathFileName4);
				if (sPathFileName4 == null) {
				} else {
					isMapValid4 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid4) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
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
				addFileName.add(sPathFileName5);
				if (sPathFileName5 == null) {
				} else {
					isMapValid5 = sFunctions.parseAndValidateMap(existingMap, Integer.parseInt(noOfPlayer));
					if (isMapValid5) {
						JOptionPane.showMessageDialog(null, "Map successfully loaded");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Map selected");
						System.out.println(existingMap.getErrorMessage());
					}
			}
			}
			
		
				
	}
		if (event.getSource().equals(tournamentView.getbuttonPlayGameTournament())) {
			if (noOfMaps.equals("  --Select--  ")) {
				//System.out.println("Select is pressed :no of Maps");
				JOptionPane.showMessageDialog(null, "Please select no. of Map to play game");
			}
			else{
				if (noOfPlayer.equals("  --Select--  ")) {
					//System.out.println("Select is pressed no Of Player");
					JOptionPane.showMessageDialog(null, "Please select no. of Players to play game");
				}
				else
				{
					if (noOfGames.equals("  --Select--  ")) {
						//System.out.println("Select is pressed : no of Games");
						JOptionPane.showMessageDialog(null, "Please select no. of games");
					}
					else
					{
						if (noOfTurns.equals("  --Select--  ")) {
							//System.out.println("Select is pressed no of turns");
							JOptionPane.showMessageDialog(null, "Please select no. of turns");
						}
					}
					
				}
				
			}
			//validation for no of maps selected == browse button file uploaded array count
			isFileChecked=fncCheckFileUploaded(noOfMaps);
			if(isFileChecked==false){
				JOptionPane.showMessageDialog(null, "Please upload all the Map file in the window");
			}
			
			
			//validation for no of player == no of player in drop down string array count
		isPlayerAdded=fncCheckPlayerBehaviour(noOfPlayer);
			if(isPlayerAdded==false){
				JOptionPane.showMessageDialog(null, "Please select all the player combo box in the window");
			}
			
			
			
			
		
			
			
		}
		}

	private boolean fncCheckPlayerBehaviour(String noOfPlayer2) {
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
		if(sPlayerBehaviour5!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour5);
		}
		if(sPlayerBehaviour6!=("  --Select--  "))
		{
			addPlayerBehaviourName.add(sPlayerBehaviour6);
		}
		
		System.out.println("No of File selected in player behaviour : " +addPlayerBehaviourName.size());
		if(noOfPlayer2.equals(String.valueOf(addPlayerBehaviourName.size())))
		{
			isReturnBehaviour=true;
		}
		else
		{
			isReturnBehaviour=false;
		}
		
		return isReturnBehaviour;
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
