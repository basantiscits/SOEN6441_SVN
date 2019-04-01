package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.models.Map;
import com.proj.utilites.MapTools;
import com.proj.views.TournamentView;

public class TournamentController  implements ActionListener {
	private TournamentView tournamentView;
	private String noOfMaps;
	private String noOfPlayer;
	private String sPathFileName1 = "";
	private String sPathFileName2 = "";
	private String sPathFileName3 = "";
	private String sPathFileName4 = "";
	private String sPathFileName5 = "";	

	public TournamentController(TournamentView tournamentView) {
		// TODO Auto-generated constructor stub
		this.tournamentView = tournamentView;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		boolean isMapValid1 = true;
		boolean isMapValid2 = true;
		boolean isMapValid3 = true;
		boolean isMapValid4 = true;
		boolean isMapValid5 = true;
		Map existingMap = new Map();
		System.out.println("actionPerformed");
		noOfMaps = (String) tournamentView.getComboBoxSelectMap().getSelectedItem();
		noOfPlayer=(String) tournamentView.getComboBoxSelectNoOfPlayer().getSelectedItem();
		System.out.println("No of Maps Selected : "+noOfMaps);
		System.out.println("Player Selected : "+noOfPlayer);
		if (noOfMaps.trim().equalsIgnoreCase("1")) { 
			tournamentView.getbuttonbrowse1().setVisible(true);
		}
		else if(noOfMaps.trim().equalsIgnoreCase("2")){
			tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
		}
        else if(noOfMaps.trim().equalsIgnoreCase("3")){
        	tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
		}
        else if(noOfMaps.trim().equalsIgnoreCase("4")){
        	tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
			tournamentView.getbuttonbrowse4().setVisible(true);
		}
       else if(noOfMaps.trim().equalsIgnoreCase("5")){
    	    tournamentView.getbuttonbrowse1().setVisible(true);
			tournamentView.getbuttonbrowse2().setVisible(true);
			tournamentView.getbuttonbrowse3().setVisible(true);
			tournamentView.getbuttonbrowse4().setVisible(true);
			tournamentView.getbuttonbrowse5().setVisible(true);
        }
		
		if(noOfPlayer.trim().equalsIgnoreCase("2")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
		}
		else if(noOfPlayer.trim().equalsIgnoreCase("3")){
			tournamentView.getcomboBoxBehaviourplayer1().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer2().setVisible(true);
			tournamentView.getcomboBoxBehaviourplayer3().setVisible(true);
			
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
			tournamentView.getcomboBoxBehaviourplayer5().setVisible(true);
			
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
		}
}
