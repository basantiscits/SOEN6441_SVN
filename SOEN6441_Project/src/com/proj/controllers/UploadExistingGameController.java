package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.proj.models.GameModelCreation;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.MapTools;
import com.proj.views.PlayNewGame;
import com.proj.views.UploadExistingGame;

/**
 * Uploads Existing Game Controller
 * @author Aman
 * @since 20 Mar 2019
 * @version 1.2
 */
public class UploadExistingGameController implements ActionListener, Serializable {
	UploadExistingGame uploadExistingGame;
	private String sPathFileName = "";
	private static final long serialVersionUID = 45443434343L;

	/**
	 * UploadExistingGameController constructor
	 * @param uploadExistingGame Object of UploadExistingGame class
	 */
	public UploadExistingGameController(UploadExistingGame uploadExistingGame) {
		this.uploadExistingGame = uploadExistingGame;
	}
	
	/**
	 * UploadExistingGameController constructor
	 * @param sPathFileName File path name
	 */
	public UploadExistingGameController(String sPathFileName) {
		this.sPathFileName = sPathFileName;
	}
	
	/**
	 * Action performed
	 * @param e Object of ActionEvent class
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ((e.getSource()).equals(uploadExistingGame.getButtonbrowse())) {
            System.out.println("Browse Pressed");
            UploadExistingGame objPlayNewGame = new UploadExistingGame();
			MapTools sFunctions = new MapTools();
			Map existingMap = new Map();
			sPathFileName = sFunctions.pickMapFile(existingMap);
			System.out.println(existingMap.getName());
			sPathFileName = sPathFileName +"\\"+ existingMap.getName();
			if (sPathFileName == null) {
			} else {
				uploadExistingGame.getTextFieldMap().setText(sPathFileName);
				
			}
		}
		else if ((e.getSource()).equals(uploadExistingGame.getButtonPlayGame()))
		{
			 System.out.println("Play Game Pressed");
			 try {
					loadSavedGame();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
		}		
	}

	/**
	 * Load saved game
	 * @throws IOException input output exception
	 * @throws ClassNotFoundException class not found
	 */
	private void loadSavedGame() throws IOException, ClassNotFoundException {
		
		System.out.println(sPathFileName);
		FileInputStream fs = new FileInputStream(sPathFileName);
		ObjectInputStream os = new ObjectInputStream(fs);
		GameModelCreation gameModel = (GameModelCreation)os.readObject();
		gameModel.getGameScreen().addObserverWhenLoading();
		gameModel.getGameScreen().setVisible(true);
		
		System.out.println("Game Model State: " + gameModel.getGameState());
	}
	
	/**
	 * 
	 * @param sName file name
	 * @return game model
	 * @throws IOException input output exception
	 * @throws ClassNotFoundException class not found
	 */
	public GameModelCreation loadSavedGame(String sName) throws IOException, ClassNotFoundException {
		FileInputStream fs = new FileInputStream(sName);
		ObjectInputStream os = new ObjectInputStream(fs);
		GameModelCreation gameModel = (GameModelCreation)os.readObject();
		return gameModel;
	}
}
