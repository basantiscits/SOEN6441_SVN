package com.proj.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.proj.models.Country;
import com.proj.views.FortificationView;

/**
 * Fortification Controller Class
 * 
 * @author Aman
 * @since 25 Feb 2019
 * @version 1.0
 */
public class FortificationController implements ActionListener {

	private FortificationView fortifyView;

	/**
	 * getter for source country
	 */
	public Country getSourCountry() {
		return sourCountry;
	}

	/**
	 * setter for source country
	 */
	public void setSourCountry(Country sourCountry) {
		this.sourCountry = sourCountry;
	}

	/**
	 * getter for destination country
	 * 
	 * @return destCountry Destination Country
	 */
	public Country getDestCountry() {
		return destCountry;
	}

	/**
	 * setter for destination country
	 */
	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}

	private Country sourCountry, destCountry;

	/**
	 * constructor for fortification controller
	 * @param fortifyView the fortify view
	 */
	public FortificationController(FortificationView fortifyView) {

		this.fortifyView = fortifyView;

	}

	/**
	 * action performed upon fortification
	 * 
	 * @param event action event that triggers the response
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

				for (int i = 0; i < army; i++) {
					sourCountry.removeNoOfArmiesCountry();
					destCountry.addNoOfArmiesCountry();
				}
				fortifyView.getGameWindow().createStartUpTree();
				if (fortifyView.getCurrentPlayer() == (fortifyView.getPlayer().length - 1)) {
					fortifyView.setVisible(false);
					fortifyView.getGameWindow().getArmyAllocation().setEnabled(false);
					fortifyView.dispose();
				} 
				else {
					fortifyView.setVisible(false);
					fortifyView.getGameWindow().getArmyAllocation().setEnabled(true);
					fortifyView.dispose();
				}
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
			if (fortifyView.getCurrentPlayer() == (fortifyView.getPlayer().length - 1)) {
				fortifyView.setVisible(false);
				fortifyView.getGameWindow().getArmyAllocation().setEnabled(false);
				fortifyView.dispose();
			}
			else {
				fortifyView.setVisible(false);
				fortifyView.getGameWindow().getArmyAllocation().setEnabled(true);
				fortifyView.dispose();
			}
		}

	}

}
