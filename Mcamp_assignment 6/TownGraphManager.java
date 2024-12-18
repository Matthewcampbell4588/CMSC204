import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//Matthew Campbell

public class TownGraphManager implements TownGraphManagerInterface{

	private Graph g;
	
		public TownGraphManager() {
		g = new Graph();
	}
	
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		g.addEdge(town1o, town2o, weight, roadName);
		
		return true;
	}

	
	@Override
	public String getRoad(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		Road r = g.getEdge(town1o, town2o);
		
		for(Road ro : g.edgesOf(town1o)) {
			if(ro.getDestination().equals(town2o) || ro.getSource().equals(town2o)) {
				return r.getName();
			}
		}
		
		return null;
	}

	
	@Override
	public boolean addTown(String v) {
		Town town1o = new Town(v);
		return g.addVertex(town1o);
	}


	@Override
	public Town getTown(String name) {
		Town town1o = new Town(name);
		for(Town to : g.vertexSet()) {
			if(town1o.equals(to)) {
				return to;
			}
		}
		
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		Town town1o = new Town(v);
		return g.containsVertex(town1o);
	}

	
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);

		return g.containsEdge(town1o, town2o);
	}

	
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();
		for(Road ro : g.edgeSet()) {
			roads.add(ro.getName());
		}
		
		Collections.sort(roads);
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		
		if(g.containsEdge(town1o, town2o)) {
			Road temp = g.getEdge(town1o, town2o);
			Road test = g.removeEdge(town1o, town2o, temp.getWeight() , road);
		
			if(test.equals(temp)) 
				return true;
		}
		
		return false;
	}

	
	@Override
	public boolean deleteTown(String v) {
		Town town1o = new Town(v);
		return g.removeVertex(town1o);
	}

	
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();
		
		for(Town to : g.vertexSet()) {
			towns.add(to.getName());
		}
		
		Collections.sort(towns);
		return towns;
	}

	
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		
		ArrayList<String> thePath = g.shortestPath(town1o, town2o);
		return thePath;
	}
	
	
	public void populateTownGraph(File input) throws FileNotFoundException{
		ArrayList<String> list = new ArrayList<>();
		
		if (!input.exists())
			throw new FileNotFoundException();
		
		Scanner sc = new Scanner(input);
		
		while (sc.hasNextLine()) {
			list.add(sc.nextLine());
		}
		
		for (String line : list) {
			String[] split = line.split(";");
			int delim = split[0].indexOf(",");
			String roadName = split[0].substring(0,delim);
			String weight = split[0].substring(delim+1,split[0].length());
			String source = split[1];
			String destination = split[2];
			
			addTown(source);
			addTown(destination);
			
			addRoad(source, destination, Integer.parseInt(weight), roadName);
		}
		
		sc.close();
	}


}
