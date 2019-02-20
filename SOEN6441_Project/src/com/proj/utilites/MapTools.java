package com.proj.utilites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

public class MapTools {

	MapTools(Map gameMap) {
		gameMap.setPath("C:\\Users\\Aman\\Desktop\\SOEN6441_Project\\SOEN6441_SVN\\Maps");
		gameMap.setName("UnConnectedMap.map");
	}

	public void pickMapFile(Map gameMap) {

		try {

			String ImportFileName;
			JFileChooser chooser;
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("./save"));
			chooser.setDialogTitle("Choose Map file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.map", "map"));

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				// get the path of the file.
				ImportFileName = chooser.getSelectedFile().getAbsolutePath();
				// if the path of the selected file is empty.
				if (ImportFileName.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "File name invalid");
				} else {
					if (ImportFileName.trim().substring(ImportFileName.length() - 4).equals(".map")) {
						// hand over to Aman
						File f = new File(ImportFileName);
						gameMap.setName(f.getName());
						gameMap.setPath(ImportFileName.substring(0, ImportFileName.lastIndexOf("\\")));
						JOptionPane.showMessageDialog(null, "File in Correct format");
						System.out.println(gameMap.getPath());
						System.out.println(gameMap.getName());
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
	}

	public boolean parseAndValidateMap(Map gameMap) {
		boolean isMapValid = false;
		try {

			// TODO Auto-generated method stub
			// FileReader mapFile=newu
			// FileReader(gameMap.getPath()+"\\"+gameMap.getName();

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
						newCountry.setLatitude(Integer.parseInt(stringManipulationArray[1].trim()));
						newCountry.setLongitude(Integer.parseInt(stringManipulationArray[2].trim()));
						for (int i = 4; i < stringManipulationArray.length; i++) {
							newCountry.getListOfNeighbours().add(stringManipulationArray[i]);
						}
						for (Continent currentContinent : gameMap.getContinents()) {
							if (currentContinent.getContinentName().toLowerCase()
									.indexOf(stringManipulationArray[3].trim().toLowerCase()) >= 0) {
								// System.out.println(newCountry.getCountryName());
								// System.out.println(C.getContinentName());
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

			if (checkDuplicateContinents(gameMap))
				if (checkDuplicateCountries(gameMap))
					if (checkIfNeigbourExist(gameMap))
						if (checkMapConnectivity(gameMap))
							isMapValid = true;
						else
							return false;
					else
						return false;
				else
					return false;
			else
				return false;

			for (Continent currentContinent : gameMap.getContinents()) {
				System.out.println(currentContinent.getContinentName());
				System.out.println(currentContinent.getControlValue());
				for (Country currentCountry : currentContinent.getCountriesPresent()) {
					System.out.println(currentCountry.getCountryName());
					System.out.println(currentCountry.getLatitude());
					System.out.println(currentCountry.getLongitude());
					for (String Neighbours : currentCountry.getListOfNeighbours()) {
						System.out.print(Neighbours + ",");
					}
					System.out.println();
				}
				System.out.println();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isMapValid;
	}

	public boolean checkDuplicateContinents(Map gameMap) {

		List<String> continentNames = gameMap.listOfContinentNames();
		HashSet<String> set = new HashSet<>(continentNames);
		ArrayList<String> result = new ArrayList<>(set);
		if (!(result.size() == continentNames.size())) {
			gameMap.setErrorMessage("Duplicate Continents present");
			return false;
		}

		return true;

	}

	public boolean checkDuplicateCountries(Map gameMap) {

		List<String> countryNames = gameMap.listOfCountryNames();
		HashSet<String> set = new HashSet<>(countryNames);
		ArrayList<String> result = new ArrayList<>(set);
		if (!(result.size() == countryNames.size())) {
			gameMap.setErrorMessage("Duplicate Countries present");
			return false;
		}
		return true;
	}

	public boolean checkDuplicateNeighbours(Map gameMap) {

		for (Continent continent : gameMap.getContinents()) {
			for (Country country : continent.getCountriesPresent()) {
				List<String> neighbours = country.getListOfNeighbours();
				HashSet<String> set = new HashSet<>(neighbours);
				ArrayList<String> result = new ArrayList<>(set);
				if (!(result.size() == neighbours.size())) {
					gameMap.setErrorMessage("Duplicate Neighbours present");
					return false;
				}
			}
		}

		return true;
	}

	public boolean checkIfNeigbourExist(Map gameMap) {

		List<String> listOfCountries = gameMap.listOfCountryNames().stream().map(String::toLowerCase)
				.collect(Collectors.toList());

		for (Continent c : gameMap.getContinents()) {
			for (Country country : c.getCountriesPresent()) {
				for (String neigbhour : country.getListOfNeighbours()) {
					if (!listOfCountries.contains(neigbhour.toLowerCase())) {
						gameMap.setErrorMessage("Neighbour not part of countries list");
						return false;
					}
				}
			}
		}
		return true;

	}

	// This class represents a directed graph using adjacency
	// list representation
	class Graph {
		private int V; // No. of vertices
		private ArrayList<Integer> adj[]; // Adjacency List

		// Constructor
		Graph(int v) {
			V = v;
			adj = new ArrayList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new ArrayList();
		}

		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w);
		}

		// A recursive function to print DFS starting from v
		void DFSUtil(int v, Boolean visited[]) {
			// Mark the current node as visited and print it
			visited[v] = true;

			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> i = adj[v].listIterator();
			while (i.hasNext()) {
				int n;
				n = i.next();
				if (!visited[n])
					DFSUtil(n, visited);
			}
		}

		// Function that returns transpose of this graph
		Graph getTranspose() {
			Graph g = new Graph(V);
			for (int v = 0; v < V; v++) {
				// Recur for all the vertices adjacent to this vertex
				Iterator<Integer> i = adj[v].listIterator();
				while (i.hasNext())
					g.adj[i.next()].add(v);
			}
			return g;
		}

		// The main function that returns true if graph is strongly
		// connected
		Boolean isSC() {
			// Step 1: Mark all the vertices as not visited
			// (For first DFS)
			Boolean visited[] = new Boolean[V];
			for (int i = 0; i < V; i++)
				visited[i] = false;

			// Step 2: Do DFS traversal starting from first vertex.
			DFSUtil(0, visited);

			// If DFS traversal doesn't visit all vertices, then
			// return false.
			for (int i = 0; i < V; i++)
				if (visited[i] == false)
					return false;

			// Step 3: Create a reversed graph
			Graph gr = getTranspose();

			// Step 4: Mark all the vertices as not visited (For
			// second DFS)
			for (int i = 0; i < V; i++)
				visited[i] = false;

			// Step 5: Do DFS for reversed graph starting from
			// first vertex. Staring Vertex must be same starting
			// point of first DFS
			gr.DFSUtil(0, visited);

			// If all vertices are not visited in second DFS, then
			// return false
			for (int i = 0; i < V; i++)
				if (visited[i] == false)
					return false;

			return true;
		}
	}

	private boolean checkMapConnectivity(Map gameMap) {
		// TODO Auto-generated method stub
		// Create graphs given in the above diagrams

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

	private void saveDataIntoFile(Map gameMap, String name, String path) {
		// TODO Auto-generated method stub
		String data = "[Map]\nauthor=Anonymous\n[Continents]\n";
		for (Continent c : gameMap.getContinents()) {
			data = data + c.getContinentName();
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
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("E:\\NewMap.map"));
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		Map gameMap = new Map();
		MapTools M = new MapTools(gameMap);
		M.parseAndValidateMap(gameMap);
		System.out.println(gameMap.getErrorMessage());
		// M.saveDataIntoFile(gameMap, "", "");

	}

}
