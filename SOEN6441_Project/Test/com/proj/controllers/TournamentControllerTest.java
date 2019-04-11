package com.proj.controllers;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.utilites.MapTools;
import com.proj.controllers.TournamentController;

/**
 * NewGameControllerTest class
 * @author Aman
 * @since 28/03/2019
 * @version 1.2
 */
public class TournamentControllerTest {
	
	private String noOfMaps;
	private String noOfPlayer;
	private String noOfGames;
	private String noOfTurns;
	ArrayList<Map> maps;
	ArrayList<String>addFileName;
	ArrayList<String>addPlayerBehaviourName;
	NewGameController newGameController;
	TournamentController tournamentController;
	GameModelCreation gameModel;
	
	/**
	 * This method initializes all the required data to complete the test
	 */
	@Before
	public void before() {
		
		addFileName=new ArrayList<String>();
		addPlayerBehaviourName=new ArrayList<String>();
		maps=new ArrayList<Map>();
		tournamentController=new TournamentController();
		newGameController=new NewGameController();
		noOfMaps="5";
		noOfTurns="2";
		noOfPlayer="4";
		noOfGames="2";
		for (int i = 0; i < Integer.parseInt(noOfMaps); i++) {
			maps.add(new Map());
			addFileName.add("MapFiles/World.map");
			maps.get(i).setName("World.map");
			maps.get(i).setPath(addFileName.get(i).substring(0,addFileName.get(i).lastIndexOf("/")));
		}
		
		for(Map gameMap:maps){
			MapTools m=new MapTools();
			m.parseAndValidateMap(gameMap,Integer.parseInt(noOfPlayer));
		}
		
		for (int i = 0; i < Integer.parseInt(noOfPlayer); i++) {
			addPlayerBehaviourName.add("Cheater");
		}
		
		tournamentController.addFileName=addFileName;
		tournamentController.addPlayerBehaviourName=addPlayerBehaviourName;
		tournamentController.newGameController=newGameController;
		tournamentController.maps=maps;
		tournamentController.noOfGames=noOfGames;
		tournamentController.noOfMaps=noOfMaps;
		tournamentController.noOfPlayer=noOfPlayer;
		tournamentController.noOfTurns=noOfTurns;
		tournamentController.sPlayerBehaviour1=addPlayerBehaviourName.get(0);
		tournamentController.sPlayerBehaviour2=addPlayerBehaviourName.get(1);
		tournamentController.sPlayerBehaviour3=addPlayerBehaviourName.get(2);
		tournamentController.sPlayerBehaviour4=addPlayerBehaviourName.get(3);
	}
	
	/**
	 * This method checks whether all the parameters entered to play the tournament are correct or not
	 */
	@Test
	public void checkParametersTest(){
		assertTrue(tournamentController.checkParameters());
	}
	
	/**
	 * This method checks whether all the games in the tournament are correctly player or not and then verifies
	 * the no of results generated are correct.
	 */
	@Test
	public void initiateTournamentTest(){
		tournamentController.intitiateTournament();
		int a=Integer.parseInt(noOfGames)*Integer.parseInt(noOfMaps);
		assertEquals(a,tournamentController.result.size());
	}

	@After
	public void afterEachTestMethod() {

	}
	
}
