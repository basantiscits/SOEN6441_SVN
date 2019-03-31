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


public class TournamentView  extends JFrame implements ActionListener {
	private JComboBox<String> comboBoxSelectMap;
	private JComboBox<String> comboBoxSelectplayer;
	private JComboBox<String> comboBoxBehaviourplayer1;
	private JComboBox<String> comboBoxBehaviourplayer2;
	private JComboBox<String> comboBoxBehaviourplayer3;
	private JComboBox<String> comboBoxBehaviourplayer4;
	private JComboBox<String> comboBoxBehaviourplayer5;
	private JComboBox<String> comboBoxBehaviourplayer6;
	
	private JComboBox<String> comboBoxSelectNoOfGames;
	private JComboBox<String> comboBoxSelectNoOfTurns;
	
	private JButton buttonbrowse1;
	private JButton buttonbrowse2;
	private JButton buttonbrowse3;
	private JButton buttonbrowse4;
	private JButton buttonbrowse5;
	private JButton buttonPlayGameTournament;
	private TournamentController tournamentController;
	private String[] noOfMaps = new String[] {"1","2","3","4","5" };
	private String[] playersList = new String[] {"2","3","4","5","6" };
	private String [] playerTypesBehaviour = new String[] { "Human", "Aggresive", "Benevolent", "Random", "Cheater" };
	private String[] noOfTurnsArray = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
			"39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" };
	private String[] noOfGame = new String[] { "1", "2", "3", "4", "5" };
	
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
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxSelectMap);
		
		buttonbrowse1 = new JButton();
		buttonbrowse1.setText("Browse Map 1");
		buttonbrowse1.setBounds(40, 180, 120, 30);
		//buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse1);
		
		buttonbrowse2 = new JButton();
		buttonbrowse2.setText("Browse Map 2");
		buttonbrowse2.setBounds(200, 180, 120, 30);
		//buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse2);
		
		buttonbrowse3 = new JButton();
		buttonbrowse3.setText("Browse Map 3");
		buttonbrowse3.setBounds(360, 180, 120, 30);
		//buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse3);
		
		buttonbrowse4 = new JButton();
		buttonbrowse4.setText("Browse Map 4");
		buttonbrowse4.setBounds(520, 180, 120, 30);
		//buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse4);
		
		buttonbrowse5 = new JButton();
		buttonbrowse5.setText("Browse Map 5");
		buttonbrowse5.setBounds(680, 180, 120, 30);
		//buttonbrowse1.addActionListener(tournamentController);
		add(buttonbrowse5);
		
		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select the No. of Player to play :");
		labelSelectPlayer.setBounds(40, 200, 400, 180);
		add(labelSelectPlayer);
		
		comboBoxSelectplayer = new JComboBox<String>(playersList);
		comboBoxSelectplayer.setBounds(380, 270, 90, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxSelectplayer);
		
		comboBoxBehaviourplayer1 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer1.setBounds(10, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer1);
		
		comboBoxBehaviourplayer2 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer2.setBounds(170, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer2);
		
		comboBoxBehaviourplayer3 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer3.setBounds(330, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer3);
		
		comboBoxBehaviourplayer4 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer4.setBounds(490, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer4);
		
		comboBoxBehaviourplayer5 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer5.setBounds(650, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer5);
		
		comboBoxBehaviourplayer6 = new JComboBox<String>(playerTypesBehaviour);
		comboBoxBehaviourplayer6.setBounds(810, 350, 120, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxBehaviourplayer6);
		
		JLabel labelSelectNoOfGames = new JLabel();
		labelSelectNoOfGames.setText("Select  No. of games :");
		labelSelectNoOfGames.setBounds(40, 370, 400, 180);
		add(labelSelectNoOfGames);
		
		comboBoxSelectNoOfGames = new JComboBox<String>(noOfGame);
		comboBoxSelectNoOfGames.setBounds(380, 440, 90, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxSelectNoOfGames);
		
		JLabel labelSelectNoOfTurns = new JLabel();
		labelSelectNoOfTurns.setText("Select  No. of Turns :");
		labelSelectNoOfTurns.setBounds(40, 430, 400, 180);
		add(labelSelectNoOfTurns);
		
		
		comboBoxSelectNoOfTurns = new JComboBox<String>(noOfTurnsArray);
		comboBoxSelectNoOfTurns.setBounds(380, 500, 90, 30);
		//comboBoxSelectMap.addActionListener(tournamentController);
		add(comboBoxSelectNoOfTurns);
		
		
		buttonPlayGameTournament = new JButton();
		buttonPlayGameTournament.setText("Play Tournament Now");
		buttonPlayGameTournament.setBounds(380, 600, 210, 30);
		//buttonPlayGameTournament.addActionListener(tournamentController);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
