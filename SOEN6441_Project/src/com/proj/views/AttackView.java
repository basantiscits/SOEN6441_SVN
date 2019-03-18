package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextField;

import com.proj.controllers.AttackController;
import com.proj.controllers.FortificationController;
//import com.proj.controllers.FortificationController;
//import com.proj.controllers.NewGameController;
import com.proj.models.Country;
import com.proj.models.Map;
import com.proj.models.Player;
import com.proj.utilites.Constants;


public class AttackView extends JFrame implements ActionListener {
	private JLabel  Player;
	private JLabel noOfArmies;
	private JLabel PlayerDicestrlbl;
	private JLabel DefendersDicestrlbl;
	private JLabel source;
	private JLabel destination;
	private JLabel noOfDices;
	private String[] noOfDicesList = new String[] { "  --Select--  ","1", "2", "3" };
	private JLabel playerName;
	private JLabel armiesInSource;
	private JLabel armiesInDestination;
	private JComboBox sourceCountry;
	private JComboBox destinationCountry;
	private JComboBox selectNoOfArmies;
	private JButton Attackbtn;
	private JButton AllOutAttackbtn;
	private JLabel PlayerDicelbl;
	private JLabel DefenderDicelbl;
	private JLabel PlayerDiceRandomAllocated;
	private JLabel DefenderDiceRandomAllocated;
	private Map map;
	private Player[] player;
	private int currentPlayer;
	private GameWindowScreen gameWindow;
	private AttackController AttackController;
	


	public AttackView(Map gameMap, Player[] playersArry, int currentPlayer, GameWindowScreen gameWindowScreen) {
		this.map = map;
		this.player = player;
		this.currentPlayer = currentPlayer;
		this.gameWindow = gameWindow;

		setTitle("******ATTACK PHASE******");
		setResizable(false);
		setSize(Constants.WIDTH + 300, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		
		Player = new JLabel("Player 1");
		Player.setBounds(55, 155, 100, 35);
		add(Player);
		
		PlayerDicestrlbl = new JLabel("Player Dice");
		PlayerDicestrlbl.setBounds(70, -10, 100, 35);
		add(PlayerDicestrlbl);
		
		DefendersDicestrlbl = new JLabel("Defender Dice");
		DefendersDicestrlbl.setBounds(785, -10, 100, 35);
		add(DefendersDicestrlbl);
		
		PlayerDiceRandomAllocated= new JLabel("Dice Count Player");
		PlayerDiceRandomAllocated.setBounds(180, -40, 200, 200);
		add(PlayerDiceRandomAllocated);
		
		DefenderDiceRandomAllocated= new JLabel("Dice Count Defender");
		DefenderDiceRandomAllocated.setBounds(650, -40, 200, 200);
		add(DefenderDiceRandomAllocated);
		
		
		source = new JLabel("Source Country");
		source.setBounds(165, 120, 100, 30);
		add(source);
		
		armiesInSource = new JLabel();
		armiesInSource.setBounds(165, 180, 100, 30);
		add(armiesInSource);
		
		destination = new JLabel("Destination Country");
		destination.setBounds(315, 120, 120, 35);
		add(destination);
		
		armiesInDestination = new JLabel();
		armiesInDestination.setBounds(315, 180, 100, 30);
		add(armiesInDestination);
		
		
		noOfDices = new JLabel("No. of Dice to be rolled");
		noOfDices.setBounds(465, 120, 135, 35);
		add(noOfDices);
		
		
		
		
		
		sourceCountry = new JComboBox();
		sourceCountry.setBounds(165, 150, 120, 35);
		//addCountryToBox(sourceCountry);
		sourceCountry.setSelectedIndex(-1);
		//sourceCountry.addActionListener(fortificationController);
		add(sourceCountry);
		
		destinationCountry = new JComboBox();
		destinationCountry.setBounds(315, 150, 100, 35);
		add(destinationCountry);
		//destinationCountry.addActionListener(fortificationController);
		
	
		selectNoOfArmies = new JComboBox<String>(noOfDicesList);
		selectNoOfArmies.setBounds(465, 150, 100, 35);
		add(selectNoOfArmies);
		
		Attackbtn = new JButton("Attack");
		Attackbtn.setBounds(615, 150, 100, 35);
		//send.addActionListener(fortificationController);
		add(Attackbtn);
		
		AllOutAttackbtn = new JButton("All out Attack");
		AllOutAttackbtn.setBounds(770, 150, 130, 35);
		//finish.addActionListener(fortificationController);
		add(AllOutAttackbtn);
		
		ImageIcon PlayerDice = new ImageIcon("Images/Dice.jpg");
		ImageIcon DefenderDice = new ImageIcon("Images/Dice.jpg");
		
		PlayerDicelbl=new JLabel("");
		PlayerDicelbl.setIcon(PlayerDice);
		PlayerDicelbl.setBounds(60, -40, 200, 200);
		add(PlayerDicelbl);
		
		DefenderDicelbl=new JLabel("");
		DefenderDicelbl.setIcon(DefenderDice);
		DefenderDicelbl.setBounds(780, -40, 200, 200);
		add(DefenderDicelbl);
		
	

		
	}
		
	

	


	
	
	@Override
	public void actionPerformed(ActionEvent e) {}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	

}
