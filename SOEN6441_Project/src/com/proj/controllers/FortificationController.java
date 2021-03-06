package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JOptionPane;

import com.proj.models.Country;
import com.proj.views.FortificationView;
import com.proj.views.MainMenuScreen;

/**
 * Fortification Controller Class
 * @author Aman
 * @since 25 Feb 2019
 * @version 1.0
 */
public class FortificationController implements ActionListener, Serializable {

	
	private static final long serialVersionUID = 45443434343L;

	private FortificationView fortifyView;

	/**
	 * getter for source country
	 * @return source country
	 */
	public Country getSourCountry() {
		return sourCountry;
	}

	/**
	 * setter for source country
	 * @param sourCountry source country
	 */
	public void setSourCountry(Country sourCountry) {
		this.sourCountry = sourCountry;
	}

	/**
	 * getter for destination country
	 * @return destCountry Destination Country
	 */
	public Country getDestCountry() {
		return destCountry;
	}

	/**
	 * setter for destination country
	 * 
	 * @param destCountry destination country
	 */
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}

	private Country sourCountry, destCountry;

	/**
	 * constructor for fortification controller
	 * 
	 * @param fortifyView the fortify view
	 */
	public FortificationController(FortificationView fortifyView) {
		this.fortifyView = fortifyView;
	}

	/**
	 * action performed upon fortification
	 * @param e action event that triggers the response
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fortifyView.getSourceCountry()) {
			if (fortifyView.getSourceCountry().getItemCount() != 0) {
				fortifyView.getArmiesInDestination().setText("");
				fortifyView.getSelectNoOfArmies().removeAllItems();
				destCountry = null;
				String countryName = (String) fortifyView.getSourceCountry().getSelectedItem();
				sourCountry = fortifyView.getMap().searchCountry(countryName);
				fortifyView.getArmiesInSource().setText(String.valueOf(sourCountry.getNoOfArmiesPresent()));
				fortifyView.addDestCountries(sourCountry);
				if (sourCountry.getNoOfArmiesPresent() <= 1) {
					JOptionPane.showMessageDialog(null, "Country should contain atleast 2 armies to move 1");
				}
				else {
					fortifyView.getSelectNoOfArmies().removeAllItems();
					fortifyView.AddArmies();
				}
			}
		}
		else if (e.getSource() == fortifyView.getDestinationCountry()) {
			if (fortifyView.getDestinationCountry().getItemCount() != 0) {
				String destinationSelected = (String) fortifyView.getDestinationCountry().getSelectedItem();
				destCountry = fortifyView.getMap().searchCountry(destinationSelected);
				fortifyView.getArmiesInDestination().setText(String.valueOf(destCountry.getNoOfArmiesPresent()));
			}
		}
		else if (e.getSource() == fortifyView.getSend()) {
			if (sourCountry != null && sourCountry.getNoOfArmiesPresent() > 1 && destCountry != null) {
				String arm = (String) fortifyView.getSelectNoOfArmies().getSelectedItem();
				int army = Integer.valueOf(arm);

				transferFortifyArmies(army,sourCountry,destCountry);

				if((fortifyView.getGameModel().getCurrPlayer()==fortifyView.getPlayer()[fortifyView.getPlayer().length-1]) && fortifyView.checkDraw()) {
					JOptionPane.showMessageDialog(null, "No Player is eligible to attack \n MATCH DRAWN!!!");
					fortifyView.getGameModel().getGameScreen().dispose();
				}
				
				fortifyView.getGameModel().incrementTurn();
				fortifyView.getGameModel().changePlayer();
				if(fortifyView.getGameModel().getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
					System.out.println("No turn for neutral Player");
					fortifyView.getGameModel().incrementTurn();
					fortifyView.getGameModel().changePlayer();
					fortifyView.getGameModel().getGameScreen().displayPlayer();
				}
				fortifyView.getGameModel().getGameScreen().displayPlayer();
				fortifyView.getGameModel().getGameScreen().getArmyAllocation().setEnabled(true);
				fortifyView.getGameModel().getGameScreen().getArmyAllocation().setText("Phase Change");
				fortifyView.getGameModel().getGameScreen().getArmyAllocation().doClick();
				fortifyView.setVisible(false);
				fortifyView.getGameModel().setGameState(1);
				fortifyView.dispose();
				fortifyView.getGameModel().getGameScreen().getGameController().cardExchange();
				
			} 
			else if (sourCountry == null) {
				JOptionPane.showMessageDialog(null, "Please select country with armies more than 1");
			} 
			else if (destCountry == null) {
				JOptionPane.showMessageDialog(null, "Please select destination country");
			} 
			else if (sourCountry.getNoOfArmiesPresent() <= 1) {
				JOptionPane.showMessageDialog(null, "Please select country with armies more than 1");
			}
		}
		else if (e.getSource() == fortifyView.getFinish()) {
			
			if(fortifyView.checkDraw()) {
				JOptionPane.showMessageDialog(null, "No Player is eligible to attack \n MATCH DRAWN!!!");
				fortifyView.getGameModel().setGameState(10);
				fortifyView.getGameModel().getGameScreen().dispose();
			}
			
			fortifyView.getGameModel().incrementTurn();
			fortifyView.getGameModel().changePlayer();
			if(fortifyView.getGameModel().getCurrPlayer().getPlayerName().equalsIgnoreCase("Neutral")) {
				System.out.println("No turn for neutral Player");
				fortifyView.getGameModel().incrementTurn();
				fortifyView.getGameModel().changePlayer();
				fortifyView.getGameModel().getGameScreen().displayPlayer();
			}
			fortifyView.getGameModel().getGameScreen().displayPlayer();
			fortifyView.getGameModel().getGameScreen().getArmyAllocation().setEnabled(true);
			fortifyView.getGameModel().getGameScreen().getArmyAllocation().setText("Phase Change");
			fortifyView.getGameModel().getGameScreen().getArmyAllocation().doClick();
			fortifyView.setVisible(false);
			fortifyView.dispose();
			
			System.out.println("Player name after fortification: -"+fortifyView.getGameModel().getGameScreen().getGameController().getGameModel().getCurrPlayer().getPlayerName());
			fortifyView.getGameModel().getGameScreen().getGameController().cardExchange();
		}
	}
	
	/**
	 * Transfer fortified armies
	 * @param army number of armies to be transferred
	 * @param sCountry source country
	 * @param tCountry destination country
	 */
	public void transferFortifyArmies(int army, Country sCountry, Country tCountry) {
		for (int i = 0; i < army; i++) {
			sCountry.removeNoOfArmiesCountry();
			tCountry.addNoOfArmiesCountry();
		}
	}
}
