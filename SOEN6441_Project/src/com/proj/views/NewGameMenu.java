package com.proj.views;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class NewGameMenu extends Applet implements ActionListener, ItemListener{
	TextField mapping;
	Button select_file;
	Choice players; 
	String msg=" ";
	Choice playerss[];
	public void init() {
		Label select_map=new Label("Select Map ");
		mapping=new TextField(12);
		select_file=new Button("Select File");
		System.out.println();
		Label select_players=new Label("Select Players ");
		players=new Choice();
		players.add("3");
		players.add("4");
		players.add("5");
		add(select_map);
		add(mapping);
		add(select_file);
		add(select_players);
		add(players);
		//Choice[] playerss=new Choice[parseInt(players.getSelectedItem())];
		
		mapping.addActionListener(this);
		select_file.addActionListener(this);
		players.addItemListener(this);
	}
//	public int parseInt(String selectedItem) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	public void actionPerformed(ActionEvent ae) {
		String str=ae.getActionCommand();
		if(str.equals("Select File")){
			msg="You pressed yes";
		}
		repaint();
	}
	public void itemStateChanged(ItemEvent ie) {
		playerss=new Choice[Integer.parseInt(players.getSelectedItem())];
		int iCount=Integer.parseInt(players.getSelectedItem());
		//for(int i=0;i<Integer.parseInt(players.getSelectedItem());i++) {
			for(int i=0;i<iCount;i++) {
			playerss[i]=new Choice();
			Label player=new Label("Player "+i);
			playerss[i]=new Choice();
			playerss[i].add("A");
			playerss[i].add("B");
			playerss[i].add("C");
			playerss[i].add("D");
			add(player);
			add(playerss[i]);
			playerss[i].addItemListener(this);
		}
		repaint();
	}
	public void paint(Graphics g) {
		g.drawString("Map: "+ mapping.getText(), 6, 60);
		g.drawString(msg, 6, 100);
		g.drawString("Number of players: " + players.getSelectedItem(), 6, 120);
//		for(int i=1;i<=parseInt(players.getSelectedItem());i++) {
//			g.drawString("Player 1: " + playerss[i].getSelectedItem(), 6, 120);
//		}
//		for(int i=1;i<=parseInt(players.getSelectedItem());i++) {
//			g.drawString("Player " + i + playerss[i].getSelectedItem(), 6, 140);
			//playerss[i].addItemListener(this);
//		}
	}
}