 package com.proj.utilites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.proj.models.Continent;
import com.proj.models.Country;
import com.proj.models.Map;

/**
 * Map Tools class
 * @author Arpit
 * @since 25 Feb 2019
 * @version 1.0
 */
public class MapTools {
	/**
	 * Choose map file
	 * @param gameMap object of Map class
	 * @return name of map file
	 */
	public String pickMapFile(Map gameMap) {
		String sAppendFileName = null;
		
		try {
			String ImportFileName;
			JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setDialogTitle("Choose Map file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.map", "map"));

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				} else {
					if (ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map")) {
						File f = new File(ImportFileName);
						gameMap.setName(f.getName());
						gameMap.setPath(ImportFileName.substring(0, ImportFileName.lastIndexOf("\\")));
						JOptionPane.showMessageDialog(null, "File in Correct format");
						System.out.println(gameMap.getPath());
						System.out.println(gameMap.getName());
						sAppendFileName=gameMap.getPath();
					} else {
						JOptionPane.showMessageDialog(null, "File name invalid");
						String sPrint = ImportFileName.trim().substring(ImportFileName.length() - 4);
						System.out.println(sPrint);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sAppendFileName;
	}
	
	/**
	 * Display reason for invalid map
	 * @param gameMap object of Map class
	 * @param size size of list if country names
	 * @return true or false depending on whether map is valid or invalid
	 */
	public boolean parseAndValidateMap(Map gameMap, int size) {
		boolean isMapValid = false;
		try {

			FileReader mapFile;
			String line = null;
			mapFile = new FileReader(gameMap.getPath() + "\\" + gameMap.getName());
			
			String Data = "";

			BufferedReader mapReader = new BufferedReader(mapFile);
			while ((line = mapReader.readLine()) != null) {
				if (line != "\n") {
					Data += line + "\n";
				}
			}

			if (Data.toLowerCase().indexOf("[continents]") >= 0 && Data.toLowerCase().indexOf("[territories]") >= 0
					&& Data.toLowerCase().indexOf("author") >= 0 && Data.toLowerCase().indexOf("[map]") >= 0) {
				isMapValid = true;
				gameMap.setErrorOccurred(false);
				gameMap.setErrorMessage("No Errors");
			} else {
				isMapValid = false;
				gameMap.setErrorOccurred(true);
				gameMap.setErrorMessage("Information missing");
				return isMapValid;
			}

			String authorData = Data.substring(Data.toLowerCase().indexOf("[map]"),
					Data.toLowerCase().indexOf("[continents]"));
			String continentData = Data.substring(Data.toLowerCase().indexOf("[continents]"),
					Data.toLowerCase().indexOf("[territories]"));
			String countryData = Data.substring(Data.toLowerCase().indexOf("[territories]"));

			String[] countryDataArray = countryData.split("\n");
			String[] continentDataArray = continentData.split("\n");

			for (String stringManipulation : continentDataArray) {
				if (!stringManipulation.equalsIgnoreCase("[continents]") && stringManipulation.length() >= 3) {
					Continent newContinent = new Continent();
					newContinent.setContinentName(
							stringManipulation.substring(0, stringManipulation.indexOf("=")).toUpperCase());
					newContinent.setControlValue(
							Integer.parseInt(stringManipulation.substring(stringManipulation.indexOf("=") + 1)));
					gameMap.getContinents().add(newContinent);
				}
			}
			for (String stringManipulation : countryDataArray) {
				if ((!stringManipulation.equalsIgnoreCase("[territories]") && stringManipulation.length() > 3)) {
					if (stringManipulation.replaceAll("[^,]", "").length() >= 4) {
						Country newCountry = new Country();
						String[] stringManipulationArray = stringManipulation.split(",");
						newCountry.setCountryName(stringManipulationArray[0]);
						newCountry.setLatitude(Double.parseDouble(stringManipulationArray[1].trim()));
						newCountry.setLongitude(Double.parseDouble(stringManipulationArray[2].trim()));
						for (int i = 4; i < stringManipulationArray.length; i++) {
							newCountry.getListOfNeighbours().add(stringManipulationArray[i]);
						}
						for (Continent currentContinent : gameMap.getContinents()) {
							if (currentContinent.getContinentName().toLowerCase()
									.indexOf(stringManipulationArray[3].trim().toLowerCase()) >= 0) {
								currentContinent.getCountriesPresent().add(newCountry);
							}
						}
					} else {
						gameMap.setErrorOccurred(true);
						gameMap.setErrorMessage("Information missing");
						break;
					}
				}
			}
			
			if (!checkDuplicateContinents(gameMap))
				if (!checkDuplicateCountries(gameMap))
					if (!checkEmptyContinent(gameMap))
						if (checkIfNeigbourExist(gameMap))
							if (checkMapConnectivity(gameMap))
								if (checkCountryCount(gameMap, size))
									return true;
								else
									return false;
							else
								return false;
						else
							return false;
					else
						return false;
				else
					return false;
			else
				return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return isMapValid;
	}
	
	/**
	 * Validate map
	 * @param gameMap object of Map class
	 * @param size size of list of countries
	 * @return true or false depending upon whether map is valid or invalid
	 */
	public boolean validateMap(Map gameMap,int size){
		
		if (!checkDuplicateContinents(gameMap))
			if (!checkDuplicateCountries(gameMap))
				if (!checkEmptyContinent(gameMap))
					if (checkIfNeigbourExist(gameMap))
						if (checkMapConnectivity(gameMap))
							if (checkCountryCount(gameMap, size))
								return true;
							else
								return false;
						else
							return false;
					else
						return false;
				else
					return false;
			else
				return false;
		else
			return false;
	}
	
	/**
	 * Check empty continent
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether the continent is empty or not
	 */
	public boolean checkEmptyContinent(Map gameMap){
		for(Continent c:gameMap.getContinents()){
			if(c.getCountriesPresent().isEmpty()){
				gameMap.setErrorMessage("Continent "+c.getContinentName()+" has no country.");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param gameMap object of Map class
	 * @param size size of list of countries
	 * @return true or false depending upon whether number of countries is less than required size
	 */
	public boolean checkCountryCount(Map gameMap,int size){
		
		if(gameMap.listOfCountryNames().size()>=size){
			return true;
		}
		else{ 
			gameMap.setErrorMessage("No of countries less than "+size+" hence invalid.");
			return false;
		}
	}
	
	/**
	 * check duplicate continents
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether duplicate continents are present or not
	 */
	public boolean checkDuplicateContinents(Map gameMap) {

		List<String> continentNames = gameMap.listOfContinentNames();
		HashSet<String> set = new HashSet<>(continentNames);
		ArrayList<String> result = new ArrayList<>(set);
		if (!(result.size() == continentNames.size())) {
			gameMap.setErrorMessage("Duplicate Continents present");
			return true;
		}

		return false;

	}
	/**
	 * check duplicate countries
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether duplicate countries are present or not
	 */
	public boolean checkDuplicateCountries(Map gameMap) {

		List<String> countryNames = gameMap.listOfCountryNames();
		HashSet<String> set = new HashSet<>(countryNames);
		ArrayList<String> result = new ArrayList<>(set);
		if (!(result.size() == countryNames.size())) {
			gameMap.setErrorMessage("Duplicate Countries present");
			return true;
		}
		return false;
	}
	
	/**
	 * check duplicate neighbors
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether duplicate neighbors are present or not
	 */
	public boolean checkDuplicateNeighbours(Map gameMap) {

		for (Continent continent : gameMap.getContinents()) {
			for (Country country : continent.getCountriesPresent()) {
				List<String> neighbours = country.getListOfNeighbours();
				HashSet<String> set = new HashSet<>(neighbours);
				ArrayList<String> result = new ArrayList<>(set);
				if (!(result.size() == neighbours.size())) {
					gameMap.setErrorMessage("Duplicate Neighbours present");
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * check if neighbor exists
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether duplicate neighbor exists or not
	 */
	public boolean checkIfNeigbourExist(Map gameMap) {

		List<String> listOfCountries = gameMap.listOfCountryNames().stream().map(String::toLowerCase)
				.collect(Collectors.toList());

		for (Continent c : gameMap.getContinents()) {
			for (Country country : c.getCountriesPresent()) {
				for (String neigbhour : country.getListOfNeighbours()) {
					if (!listOfCountries.contains(neigbhour.toLowerCase())) {
						gameMap.setErrorMessage("Neighbour not part of countries list "+" neighbour");
						return false;
					}
				}
			}
		}
		return true;

	}
	
	/**
	 * Graph class
	 * @author Arpit
	 * @since 26 Feb 2019
	 * @version 1.0
	 */
	class Graph {
		private int V; 
		private ArrayList<Integer> adj[]; 
		
		/**
		 * constructor of Graph class
		 * @param v size of array list
		 */
		Graph(int v) {
			V = v;
			adj = new ArrayList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new ArrayList();
		}
		
		/**
		 * Add edge between countries
		 * @param v index of original country
		 * @param w index of country to be joined with
		 */
		void addEdge(int v, int w) {
			adj[v].add(w);
		}
		
		/**
		 * Depth First Search
		 * @param v index of visited array
		 * @param visited boolean array of whether country has been visited or not
		 */
		void DFSUtil(int v, Boolean visited[]) {
			visited[v] = true;

			Iterator<Integer> i = adj[v].listIterator();
			while (i.hasNext()) {
				int n;
				n = i.next();
				if (!visited[n])
					DFSUtil(n, visited);
			}
		}
		
		/**
		 * get transpose of graph
		 * @return Graph object
		 */
		Graph getTranspose() {
			Graph g = new Graph(V);
			for (int v = 0; v < V; v++) {
				Iterator<Integer> i = adj[v].listIterator();
				while (i.hasNext())
					g.adj[i.next()].add(v);
			}
			return g;
		}
		
		/**
		 * Is strongly connected or not
		 * @return true or false depending upon whether graph is strongly connected or not
		 */
		Boolean isSC() {
			Boolean visited[] = new Boolean[V];
			for (int i = 0; i < V; i++)
				visited[i] = false;

			DFSUtil(0, visited);

			for (int i = 0; i < V; i++)
				if (visited[i] == false)
					return false;

			Graph gr = getTranspose();

			for (int i = 0; i < V; i++)
				visited[i] = false;

			gr.DFSUtil(0, visited);

			for (int i = 0; i < V; i++)
				if (visited[i] == false)
					return false;

			return true;
		}
	}
	
	/**
	 * check map connectivity & print it
	 * @param gameMap object of Map class
	 * @return true or false depending upon whether graph is strongly connected or not
	 */
	private boolean checkMapConnectivity(Map gameMap) {

		List<String> listOfCountries = gameMap.listOfCountryNames().stream().map(String::toLowerCase)
				.collect(Collectors.toList());
		int noOfVertices = listOfCountries.size();
		Graph g1 = new Graph(noOfVertices);
		for (int i = 0; i < noOfVertices; i++) {
			for (Continent c : gameMap.getContinents()) {
				for (Country country : c.getCountriesPresent()) {
					if (country.getCountryName().equals(gameMap.listOfCountryNames().get(i))) {
						List<String> neighbours = country.getListOfNeighbours();
						for (String current : neighbours) {
							int index = listOfCountries.indexOf(current.toLowerCase());
							g1.addEdge(i, index);
						}

					}
				}
			}
		}

		if (g1.isSC()) {
			System.out.println("Yes, Map is strongly connected");
			return true;
		} else {
			System.out.println("No, Map is not strongly connected");
			gameMap.setErrorMessage("Map is not strongly connected");
			return false;
		}
	}
	
	/**
	 * save map data into map file
	 * @param gameMap object of Map class
	 * @param name name of map file
	 * @return true or false depending upon whether the map can be saved or not
	 */
	public boolean saveDataIntoFile(Map gameMap, String name) {
				String data = "[Map]\nauthor=Anonymous\n[Continents]\n";
				for (Continent c : gameMap.getContinents()) {
					data = data + c.getContinentName();
					if(c.getControlValue()==0){
					c.setControlValue(c.getCountriesPresent().size());	
					}
					data = data + "=" + c.getControlValue();
					data += "\n";
				}
				data += "[Territories]\n";
				for (Continent c : gameMap.getContinents()) {
					for (Country country : c.getCountriesPresent()) {
						data += country.getCountryName() + "," + country.getLatitude() + "," + country.getLongitude() + ","
								+ c.getContinentName() + "," + String.join(",", country.getListOfNeighbours()) + "\n";
					}
				}
				PrintWriter writeData = null;
				try {
					writeData = new PrintWriter(name+".map");
					writeData.println(data);
					return true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}finally{
					writeData.close();
				}
		}


}

