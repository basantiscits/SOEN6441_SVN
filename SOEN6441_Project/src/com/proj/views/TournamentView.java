package com.proj.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import com.proj.utilites.Constants;


public class TournamentView  extends JFrame implements ActionListener {
	private JButton chooseMapOne;
	
	public  TournamentView(){
		
		chooseMapOne = new JButton("Select");
		chooseMapOne.setBounds(70, 100, 70, 30);
		chooseMapOne.addActionListener(this);
		chooseMapOne.setVisible(true);
		add(chooseMapOne);
		
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
