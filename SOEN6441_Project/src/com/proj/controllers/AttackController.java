package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import com.proj.views.AttackView;


public class AttackController {
	//private Country sourCountry, destCountry;
	private AttackView AttackView;
	public String attacker;
	public String defender;
	public Random diceRoll;
	public ArrayList<Integer> attackerDiceValues;
	public ArrayList<Integer> defenderDiceValues;
	
	
	AttackController(){
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
}
