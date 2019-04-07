package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.proj.controllers.NewGameController;
import com.proj.controllers.UploadExistingGameController;
import com.proj.utilites.Constants;

public class UploadExistingGame extends JFrame implements ActionListener {
	private JTextField textFieldMap;
	private JButton buttonPlayGame;
	private JButton buttonbrowse;
	private UploadExistingGameController uploadExistingGameController;
	
	public UploadExistingGame()
	{

		uploadExistingGameController = new UploadExistingGameController(this);
		JLabel labelHeading = new JLabel();
		labelHeading.setText("THE RISK GAME : Upload Existing Game");
		labelHeading.setBounds(Constants.WIDTH / 2 + 90, 0, 200,30);
		add(labelHeading);
	
		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Please select the Map file to upload on browse Button :");
		labelSelectMap.setBounds(40, 120, 400, 180);
		add(labelSelectMap);

		textFieldMap = new JTextField();
		textFieldMap.setBounds(380, 195, 180, 30);
		add(textFieldMap);
		textFieldMap.setEditable(false);

		buttonbrowse = new JButton();
		buttonbrowse.setText("Press button to select file");
		buttonbrowse.setBounds(600, 195, 210, 30);
		buttonbrowse.addActionListener(uploadExistingGameController);
		add(buttonbrowse);

		buttonPlayGame = new JButton();
		buttonPlayGame.setText("Play Now");
		buttonPlayGame.setBounds(380, 600, 210, 30);
		buttonPlayGame.addActionListener(uploadExistingGameController);
		add(buttonPlayGame);
		
		

		setTitle("******THE RISK GAME******");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT + 300);
		setLayout(null);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
			}
		});
	
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public UploadExistingGameController getUploadExistingGameController() {
		return uploadExistingGameController;
	}


	public void setUploadExistingGameController(UploadExistingGameController uploadExistingGameController) {
		this.uploadExistingGameController = uploadExistingGameController;
	}

	public JButton getButtonbrowse() {
		return buttonbrowse;
	}

	
	public void setButtonbrowse(JButton buttonbrowse) {
		this.buttonbrowse = buttonbrowse;
	}
	
	public JButton getButtonPlayGame() {
		return buttonPlayGame;
	}

	
	public void setButtonPlayGame(JButton buttonPlayGame) {
		this.buttonPlayGame = buttonPlayGame;
	}
	
	public JTextField getTextFieldMap() {
		return textFieldMap;
	}

	
	public void setTextFieldMap(JTextField textFieldMap) {
		this.textFieldMap = textFieldMap;
	}
}
