package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.models.Map;
import com.proj.utilites.MapTools;
import com.proj.views.PlayNewGame;
import com.proj.views.UploadExistingGame;

public class UploadExistingGameController implements ActionListener {
	//private UploadExistingGameController UploadExistingGameController;
	UploadExistingGame uploadExistingGame;
	private String sPathFileName = "";

	public UploadExistingGameController(UploadExistingGame uploadExistingGame) {
		// TODO Auto-generated constructor stub
		this.uploadExistingGame = uploadExistingGame;
	}
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if ((e.getSource()).equals(uploadExistingGame.getButtonbrowse())) {
            System.out.println("Browse Pressed");
            UploadExistingGame objPlayNewGame = new UploadExistingGame();
			MapTools sFunctions = new MapTools();
			Map existingMap = new Map();
			sPathFileName = sFunctions.pickMapFile(existingMap);
			if (sPathFileName == null) {
			} else {
				uploadExistingGame.getTextFieldMap().setText(sPathFileName);

			}
		}
		else if ((e.getSource()).equals(uploadExistingGame.getButtonPlayGame()))
		{
			 System.out.println("Play Game Pressed");
			// playNewGame.GameModelWindowMade(sCarryMapForward, player, gameModel);
		}
	
		
	}
	
	
	

}
