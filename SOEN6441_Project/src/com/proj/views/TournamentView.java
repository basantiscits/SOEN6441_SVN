package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.proj.controllers.NewGameController;
import com.proj.controllers.TournamentController;
import com.proj.utilites.Constants;

/**
 * TournamentView class
 * @author Ofreish
 * @since 28 Mar 2019
 * @version 1.2
 */
public class TournamentView  extends JFrame implements ActionListener {
	private JComboBox<String> comboBoxSelectMap;
	private JComboBox<String> comboBoxSelectplayer;
	private JComboBox<String> comboBoxBehaviourplayer1;
	private JComboBox<String> comboBoxBehaviourplayer2;
	private JComboBox<String> comboBoxBehaviourplayer3;
	private JComboBox<String> comboBoxBehaviourplayer4;
	private static final long serialVersionUID = 45443434343L;

	
	private JComboBox<String> comboBoxSelectNoOfGames;
	private JComboBox<String> comboBoxSelectNoOfTurns;
	
	private JButton buttonbrowse1;
	private JButton buttonbrowse2;
	private JButton buttonbrowse3;
	private JButton buttonbrowse4;
	private JButton buttonbrowse5;
	private JButton buttonPlayGameTournament;
	private TournamentController tournamentController;
	private String[] noOfMaps = new String[] {"  --Select--  ","1","2","3","4","5" };
	private String[] playersList = new String[] {"  --Select--  ","2","3","4" };
	private String [] playerTypesBehaviour = new String[] {"  --Select--  ", "Aggressive", "Benevolent", "Random", "Cheater" };
	private String[] noOfTurnsArray = new String[] {"  --Select--  ", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
			"39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" };
	private String[] noOfGame = new String[] {"  --Select--  ", "1", "2", "3", "4", "5" };
	
	/**
	 * TournamentView constructor
	 */
	public  TournamentView(){
		tournamentController = new TournamentController(this);
		JLabel labelHeading = new JLabel();
		labelHeading.setText("THE RISK GAME : TOURANAMENT");
		labelHeading.setBounds(Constants.WIDTH / 2 + 90, 0, 200,30);
		add(labelHeading);
		
		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Select the No. of Map :");
		labelSelectMap.setBounds(40, 20, 400, 180);
		add(labelSelectMap);

		comboBoxSelectMap = new JComboBox<String>(noOfMaps);
		comboBoxSelectMap.setBounds(380, 90, 90, 30);
		comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxSelectMap);
		
		buttonbrowse1 = new JButton();
		buttonbrowse1.setText("Browse Map 1");
		buttonbrowse1.setBounds(40, 180, 120, 30);
		buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse1);
		buttonbrowse1.setVisible(false);
		
		buttonbrowse2 = new JButton();
		buttonbrowse2.setText("Browse Map 2");
		buttonbrowse2.setBounds(200, 180, 120, 30);
		buttonbrowse2.addActionListener(tournamentController);
		add(buttonbrowse2);
		buttonbrowse2.setVisible(false);
		
		buttonbrowse3 = new JButton();
		buttonbrowse3.setText("Browse Map 3");
		buttonbrowse3.setBounds(360, 180, 120, 30);
		buttonbrowse3.addActionListener(tournamentController);
		add(buttonbrowse3);
		buttonbrowse3.setVisible(false);
		
		buttonbrowse4 = new JButton();
		buttonbrowse4.setText("Browse Map 4");
		buttonbrowse4.setBounds(520, 180, 120, 30);
		buttonbrowse4.addActionListener(tournamentController);
		add(buttonbrowse4);
		buttonbrowse4.setVisible(false);
		
		
		buttonbrowse5 = new JButton();
		buttonbrowse5.setText("Browse Map 5");
		buttonbrowse5.setBounds(680, 180, 120, 30);
		buttonbrowse5.addActionListener(tournamentController);
		add(buttonbrowse5);
		buttonbrowse5.setVisible(false);
		
		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select the No. of Player to play :");
		labelSelectPlayer.setBounds(40, 200, 400, 180);
		add(labelSelectPlayer);
		
		comboBoxSelectplayer = new JComboBox<String>(playersList);
		comboBoxSelectplayer.setBounds(380, 270, 90, 30);
		comboBoxSelectplayer.addActionListener(tournamentController);
		add(comboBoxSelectplayer);
		
		comboBoxBehaviourplayer1 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer1.setBounds(10, 350, 120, 30);
		comboBoxBehaviourplayer1.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer1);
		comboBoxBehaviourplayer1.setVisible(false);
		
		comboBoxBehaviourplayer2 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer2.setBounds(170, 350, 120, 30);
		comboBoxBehaviourplayer2.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer2);
		comboBoxBehaviourplayer2.setVisible(false);
		
		comboBoxBehaviourplayer3 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer3.setBounds(330, 350, 120, 30);
		comboBoxBehaviourplayer3.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer3);
		comboBoxBehaviourplayer3.setVisible(false);
		
		comboBoxBehaviourplayer4 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer4.setBounds(490, 350, 120, 30);
		comboBoxBehaviourplayer4.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer4);
		comboBoxBehaviourplayer4.setVisible(false);
		
		
		comboBoxBehaviourplayer4.addActionListener(tournamentController);
		
		
		JLabel labelSelectNoOfGames = new JLabel();
		labelSelectNoOfGames.setText("Select  No. of games :");
		labelSelectNoOfGames.setBounds(40, 370, 400, 180);
		add(labelSelectNoOfGames);
		
		comboBoxSelectNoOfGames = new JComboBox<String>(noOfGame);
		comboBoxSelectNoOfGames.setBounds(380, 440, 90, 30);
		comboBoxSelectNoOfGames.addActionListener(tournamentController);
		add(comboBoxSelectNoOfGames);
		
		JLabel labelSelectNoOfTurns = new JLabel();
		labelSelectNoOfTurns.setText("Select  No. of Turns :");
		labelSelectNoOfTurns.setBounds(40, 430, 400, 180);
		add(labelSelectNoOfTurns);
		
		
		comboBoxSelectNoOfTurns = new JComboBox<String>(noOfTurnsArray);
		comboBoxSelectNoOfTurns.setBounds(380, 500, 90, 30);
		comboBoxSelectNoOfTurns.addActionListener(tournamentController);
		add(comboBoxSelectNoOfTurns);
		
		
		buttonPlayGameTournament = new JButton();
		buttonPlayGameTournament.setText("Play Tournament Now");
		buttonPlayGameTournament.setBounds(380, 600, 210, 30);
		buttonPlayGameTournament.addActionListener(tournamentController);
		add(buttonPlayGameTournament);
		

		setTitle("******  THE RISK GAME ---> TOURANAMENT  ******");
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
	 * setter for tournament controller
	 * @param TournamentController Object of TournamentController class
	 */
	public void setTournamentController(TournamentController TournamentController) {
		this.tournamentController = TournamentController;
	}
	
	/**
	 * getter for tournament controller
	 * @return tournamentController
	 */
	public TournamentController getTournamentController() {
		return tournamentController;
	}
	
	/**
	 * getter for combo box of selected map
	 * @return comboBoxSelectMap
	 */
	public JComboBox<String> getComboBoxSelectMap() {
		return comboBoxSelectMap;
	}

	/**
	 * setter for combo box of selected map
	 * @param comboBoxSelectMap Object of JComboBox class
	 */
	public void setComboBoxSelectMap(JComboBox<String> comboBoxSelectMap) {
		this.comboBoxSelectMap = comboBoxSelectMap;
	}
	
	/**
	 * getter for combo box of select number of players
	 * @return comboBoxSelectplayer
	 */
	public JComboBox<String> getComboBoxSelectNoOfPlayer() {
		return comboBoxSelectplayer;
	}

	/**
	 * setter for combo box of select number of players
	 * @param comboBoxSelectplayer Object of JComboBox class
	 */
	public void setComboBoxSelectNoOfPlayer(JComboBox<String> comboBoxSelectplayer) {
		this.comboBoxSelectplayer = comboBoxSelectplayer;
	}
	
	/**
	 * getter for button browse1
	 * @return buttonbrowse1
	 */
	public JButton getbuttonbrowse1() {
		return buttonbrowse1;
	}
	
	/**
	 * setter for button browse1
	 * @param buttonbrowse1 Object of JButton class 
	 */
	public void setbuttonbrowse1(JButton buttonbrowse1) {
		this.buttonbrowse1 = buttonbrowse1;
	}
	
	/**
	 * getter for button browse2
	 * @return buttonbrowse2
	 */
	public JButton getbuttonbrowse2() {
		return buttonbrowse2;
	}

	/**
	 * setter for button browse2
	 * @param buttonbrowse2 Object of JButton class 
	 */
	public void setbuttonbrowse2(JButton buttonbrowse2) {
		this.buttonbrowse2 = buttonbrowse2;
	}
	
	/**
	 * getter for button browse3
	 * @return buttonbrowse3
	 */
	public JButton getbuttonbrowse3() {
		return buttonbrowse3;
	}

	/**
	 * setter for button browse3
	 * @param buttonbrowse3 Object of JButton class 
	 */
	public void setbuttonbrowse3(JButton buttonbrowse3) {
		this.buttonbrowse3 = buttonbrowse3;
	}
	
	/**
	 * getter for button browse4
	 * @return buttonbrowse4
	 */
	public JButton getbuttonbrowse4() {
		return buttonbrowse4;
	}

	/**
	 * setter for button browse4
	 * @param buttonbrowse4 Object of JButton class 
	 */
	public void setbuttonbrowse4(JButton buttonbrowse4) {
		this.buttonbrowse4 = buttonbrowse4;
	}
	
	/**
	 * getter for button browse5
	 * @return buttonbrowse5
	 */
	public JButton getbuttonbrowse5() {
		return buttonbrowse5;
	}

	/**
	 * setter for button browse5
	 * @param buttonbrowse5 Object of JButton class 
	 */
	public void setbuttonbrowse5(JButton buttonbrowse5) {
		this.buttonbrowse5 = buttonbrowse5;
	}
	
	/**
	 * getter for combo box behaviour of player 1
	 * @return comboBoxBehaviourplayer1
	 */
	public JComboBox<String> getcomboBoxBehaviourplayer1() {
		return comboBoxBehaviourplayer1;
	}

	/**
	 * setter for combo box behaviour of player 1
	 * @param comboBoxBehaviourplayer1 Object of JComboBox class
	 */
	public void setcomboBoxBehaviourplayer1(JComboBox<String> comboBoxBehaviourplayer1) {
		this.comboBoxBehaviourplayer1 = comboBoxBehaviourplayer1;
	}
	
	/**
	 * getter for combo box behaviour of player 2
	 * @return comboBoxBehaviourplayer2
	 */
	public JComboBox<String> getcomboBoxBehaviourplayer2() {
		return comboBoxBehaviourplayer2;
	}

	/**
	 * setter for combo box behaviour of player 2
	 * @param comboBoxBehaviourplayer2 Object of JComboBox class
	 */
	public void setcomboBoxBehaviourplayer2(JComboBox<String> comboBoxBehaviourplayer2) {
		this.comboBoxBehaviourplayer2 = comboBoxBehaviourplayer2;
	}
	
	/**
	 * getter for combo box behaviour of player 3
	 * @return comboBoxBehaviourplayer3
	 */
	public JComboBox<String> getcomboBoxBehaviourplayer3() {
		return comboBoxBehaviourplayer3;
	}

	/**
	 * setter for combo box behaviour of player 3
	 * @param comboBoxBehaviourplayer3 Object of JComboBox class
	 */
	public void setcomboBoxBehaviourplayer3(JComboBox<String> comboBoxBehaviourplayer3) {
		this.comboBoxBehaviourplayer3 = comboBoxBehaviourplayer3;
	}
	
	/**
	 * getter for combo box behaviour of player 4
	 * @return comboBoxBehaviourplayer4
	 */
	public JComboBox<String> getcomboBoxBehaviourplayer4() {
		return comboBoxBehaviourplayer4;
	}

	/**
	 * setter for combo box behaviour of player 4
	 * @param comboBoxBehaviourplayer4 Object of JComboBox class
	 */
	public void setcomboBoxBehaviourplayer4(JComboBox<String> comboBoxBehaviourplayer4) {
		this.comboBoxBehaviourplayer4 = comboBoxBehaviourplayer4;
	}
	
	
	/**
	 * getter for combo box of select number of turns
	 * @return comboBoxSelectNoOfTurns
	 */
	public JComboBox<String> getcomboBoxSelectNoOfTurns() {
		return comboBoxSelectNoOfTurns;
	}

	/**
	 * setter for combo box of select number of turns
	 * @param comboBoxSelectNoOfTurns Object of JComboBox class
	 */
	public void setcomboBoxSelectNoOfTurns(JComboBox<String> comboBoxSelectNoOfTurns) {
		this.comboBoxSelectNoOfTurns = comboBoxSelectNoOfTurns;
	}
	
	/**
	 * getter for combo box of select number of games
	 * @return comboBoxSelectNoOfGames
	 */
	public JComboBox<String> getcomboBoxSelectNoOfGames() {
		return comboBoxSelectNoOfGames;
	}

	/**
	 * setter for combo box of select number of games
	 * @param comboBoxSelectNoOfGames Object of JComboBox class
	 */
	public void setcomboBoxSelectNoOfGames(JComboBox<String> comboBoxSelectNoOfGames) {
		this.comboBoxSelectNoOfGames = comboBoxSelectNoOfGames;
	}
	
	/**
	 * setter for combo box of play game tournament button
	 * @param buttonPlayGameTournament Object of JComboBox class
	 */
	public void setbuttonPlayGameTournament(JButton buttonPlayGameTournament) {
		this.buttonPlayGameTournament = buttonPlayGameTournament;
	}
	
	/**
	 * getter for play game tournament button
	 * @return buttonPlayGameTournament
	 */
	public JButton getbuttonPlayGameTournament() {
		return buttonPlayGameTournament;
	}

	/**
	 * action performed
	 * @param arg0 Object of ActionEvent class
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
