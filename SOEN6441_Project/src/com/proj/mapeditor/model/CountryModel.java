/**
 * 
 */
package com.proj.mapeditor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import com.conquest.model.PlayerModel;

/**
 * CountryModel Class Initializes country name String, a ContinentModel
 * {@link ContinentModel} as belongsTo and a ArrayList {@link ArrayList} as
 * listofNeighbours.
 *
 * @author Nancy Goyal
 */
public class CountryModel extends Observable implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 3L;

	/** The country name. */
	private String countryName;

	/** The belongs to. */
	private ContinentModel belongsTo;

	/** The no of armies country. */
	private int noOfArmiesCountry;

	/** The list of neighbours. */
	private ArrayList<String> listOfNeighbours;

	/** player who owns this country. */
	private PlayerModel owner;

	/** The is visited. */
	private boolean isVisited;

	/**
	 * ContryModel Constructor Create a new empty ArrayList.
	 */
	public CountryModel() {
		this.listOfNeighbours = new ArrayList<>();
		isVisited = false;
		updateChanges();

	}

	/**
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName Name of country
	 */
	public CountryModel(String countryName) {
		this.setCountryName(countryName);
		this.listOfNeighbours = new ArrayList<>();
		isVisited = false;
		updateChanges();

	}

	/**
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName name of the new country
	 * @param continent   Continent object which contains this country
	 */
	public CountryModel(String countryName, ContinentModel continent) {
		this.setCountryName(countryName);
		this.setBelongsTo(continent);
		this.listOfNeighbours = new ArrayList<>();
		isVisited = false;
		updateChanges();

	}

	/**
	 * CountryModel Parameterized Constructor.
	 *
	 * @param countryName      Name of country
	 * @param continentModel   Continent object which contains this country
	 * @param listOfNeighbours List of all the neighbours country names
	 */

	public CountryModel(String countryName, ContinentModel continentModel, ArrayList<String> listOfNeighbours) {
		this.countryName = countryName;
		this.belongsTo = continentModel;
		this.listOfNeighbours = listOfNeighbours;
		isVisited = false;
		updateChanges();

	}

	/**
	 * getCountryName Method Getter Function to get String name.
	 *
	 * @return countryName Name of country
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * setCountryName Method Setter Function to set name of country.
	 *
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
		updateChanges();
	}

	/**
	 * getBelongsTo Method of type ContinentModel.
	 *
	 * @return belongsTo the continent the country belong to
	 */
	public ContinentModel getBelongsTo() {
		return belongsTo;
	}

	/**
	 * setBelongsTo Method.
	 *
	 * @param belongsTo the belongsTo to set
	 */
	public void setBelongsTo(ContinentModel belongsTo) {
		this.belongsTo = belongsTo;
		updateChanges();
	}

	/**
	 * addNeighbour Method Adds a new neighbour to a country countryModel a
	 * CountryModel{@link CountryModel} object.
	 *
	 * @param countryName adds a neighbour country
	 * @return isAdded 
	 */

	public boolean addNeighbour(String countryName) {
		boolean isAdded = listOfNeighbours.add(countryName);
		updateChanges();
		return isAdded;
	}

	/**
	 * getListOfNeighbours Method.
	 *
	 * @return listOfNeighbours of ArrayList type{@link ArrayList}
	 */
	public ArrayList<String> getListOfNeighbours() {
		return listOfNeighbours;
	}

	/**
	 * getNoOfArmiesCountry Method Getter Function to get noOfArmiesCountry.
	 *
	 * @return noOfArmiesCountry Integer
	 */
	public int getNoOfArmiesCountry() {
		return noOfArmiesCountry;
	}

	/**
	 * setNoOfArmiesCountry Method.
	 *
	 * @param noOfArmiesCountry the noOfArmiesCountry to set
	 */
	public void setNoOfArmiesCountry(int noOfArmiesCountry) {
		this.noOfArmiesCountry = noOfArmiesCountry;
		updateChanges();
	}

	/**
	 * Adds the no of armies country.
	 */
	public void addNoOfArmiesCountry() {
		noOfArmiesCountry++;
		updateChanges();
	}

	/**
	 * Removes the no of armies country.
	 */
	public void removeNoOfArmiesCountry() {
		noOfArmiesCountry--;
		updateChanges();
	}

	/**
	 * Method to get country's owner.
	 * 
	 * @return owner country's current owner
	 */
	public PlayerModel getOwner() {
		return owner;
	}

	/**
	 * Method to set country's owner, is used at the beginning of a game or during
	 * an attack phase.
	 * 
	 * @param owner country's new owner
	 */
	public void setOwner(PlayerModel owner) {
		this.owner = owner;
		updateChanges();
	}

	/**
	 * searchNeighboursCountry Method A function to find neighbour country to a
	 * country.
	 *
	 * @param countryName name of country to search
	 * @return country CountryModel object
	 */

	public String searchNeighboursCountry(String countryName) {
		countryName = countryName.toLowerCase();
		for (String country : listOfNeighbours) {
			if (country.equalsIgnoreCase(countryName)) {
				return country;
			}
		}
		return null;
	}

	/**
	 * Sets the visited.
	 *
	 * @param isVisited the isVisited to set
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	/**
	 * Checks if is visited.
	 *
	 * @return the isVisited
	 */
	public boolean isVisited() {
		return isVisited;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryModel [countryName=" + countryName + ", belongsTo=" + belongsTo + ", noOfArmiesCountry="
				+ noOfArmiesCountry + ", listOfNeighbours=" + listOfNeighbours + "]";
	}

	/**
	 * Update changes.
	 */
	public void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
