package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.proj.controllers.NewGameController;
import com.proj.controllers.UploadExistingGameController;
import com.proj.utilites.Constants;


/**
 * UploadExistingGame class
 * @author Ofreish
 * @since 28 Mar 2019
 * @version 1.2
 */
public class UploadExistingGame extends JFrame implements ActionListener , Serializable {
	
	private JTextField textFieldMap;
	private JButton buttonPlayGame;
	private JButton buttonbrowse;
	private UploadExistingGameController uploadExistingGameController;
	private static final long serialVersionUID = 45443434343L;

	
	/**
	 * UploadExistingGame constructor
	 */
	public UploadExistingGame() {

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
	
	
	/**
	 * action performed
	 * @param e Object of ActionEvent class
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	/**
	 * getter for upload existing game controller
	 * @return uploadExistingGameController
	 */
	public UploadExistingGameController getUploadExistingGameController() {
		return uploadExistingGameController;
	}

	/**
	 * setter for upload existing game controller
	 * @param uploadExistingGameController Object of UploadExistingGameController class
	 */
	public void setUploadExistingGameController(UploadExistingGameController uploadExistingGameController) {
		this.uploadExistingGameController = uploadExistingGameController;
	}

	/**
	 * getter for button browse
	 * @return buttonbrowse
	 */
	public JButton getButtonbrowse() {
		return buttonbrowse;
	}

	/**
	 * setter for button browse
	 * @param buttonbrowse Object of JButton class
	 */
	public void setButtonbrowse(JButton buttonbrowse) {
		this.buttonbrowse = buttonbrowse;
	}
	
	/**
	 * getter for button play game
	 * @return buttonPlayGame
	 */
	public JButton getButtonPlayGame() {
		return buttonPlayGame;
	}

	/**
	 * setter for button play game
	 * @param buttonPlayGame Object of JButton class
	 */
	public void setButtonPlayGame(JButton buttonPlayGame) {
		this.buttonPlayGame = buttonPlayGame;
	}
	
	/**
	 * getter for text field map
	 * @return textFieldMap
	 */
	public JTextField getTextFieldMap() {
		return textFieldMap;
	}

	/**
	 * setter for text field map
	 * @param textFieldMap Object of JTextField class
	 */
	public void setTextFieldMap(JTextField textFieldMap) {
		this.textFieldMap = textFieldMap;
	}
}
