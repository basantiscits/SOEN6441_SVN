package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.views.AttackView;
import com.proj.views.FortificationView;
import com.proj.models.Country;


public class AttackController implements ActionListener{
	//private Country sourCountry, destCountry;
	private AttackView AttackView;
	public String attacker;
	public String defender;
	public Random diceRoll;
	public ArrayList<Integer> attackerDiceValues;
	public ArrayList<Integer> defenderDiceValues;
	
	
	public AttackController(){
		diceRoll=new Random();
	}
	
	public boolean normalAttack(String attacker,String defender,int noOfArmiesSelected){
		if(noOfArmiesSelected==2){
			
		}
		else if(noOfArmiesSelected==3){
			
		}
		else{
			
		}
		return true;
	}
	
	public boolean allOutAttack(String attacker, String defender){
		
		return false;
		
	}
	private Country sourCountry, destCountry;

	public Country getSourCountry() {
		return sourCountry;
	}
	
	public void setSourCountry(Country sourCountry) {
		this.sourCountry = sourCountry;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


	public Country getDestCountry() {
		return destCountry;
	}
	
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}
	public AttackController(AttackView AttackView) {
		this.AttackView = AttackView;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == AttackView.getDestinationCountry()) {
			if (AttackView.getDestinationCountry().getItemCount() != 0) {
				String destinationSelected = (String) AttackView.getDestinationCountry().getSelectedItem();
				destCountry = AttackView.getMap().searchCountry(destinationSelected);
				AttackView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
			}
		
		}
		else if (e.getSource() == AttackView.getSourceCountry()) {
			if (AttackView.getSourceCountry().getItemCount() != 0) {
				AttackView.getArmiesInDestination().setText("");
				AttackView.getNoOfDice().removeAllItems();
				destCountry = null;
				String countryName = (String) AttackView.getSourceCountry().getSelectedItem();
				sourCountry = AttackView.getMap().searchCountry(countryName);
				AttackView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				AttackView.addDestCountries(sourCountry);
				AttackView.selectDices();
			}
		}
		
	}



}