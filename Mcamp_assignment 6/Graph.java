import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//Matthew Campbell
public class Graph implements GraphInterface<Town, Road>{
	
	//Matrix was just for experimentation not used in actual graph
	private int adjMatrix[][];
	private Map<Town, Town> prev;
	private Map<Town, Integer> distance;
	private Set<Town> visited;
	private Set<Town> unvisited;
	private HashMap<Town,Integer> vertexList = new HashMap<>();
	private Set<Town> vertexSet = new HashSet<>();
	private Set<Road> edgeSet = new HashSet<>();
	private int vertexCount;
	
	
	public Graph() {
		this.vertexCount = 0;
		this.adjMatrix = new int[15][15];
		this.distance = new HashMap<>();
		this.prev = new HashMap<>();
		this.visited = new HashSet<>();
		this.unvisited = new HashSet<>();
	}
	

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
			for(Road r:edgeSet) {
				if(r != null) {
					if((r.contains(sourceVertex) && r.contains(destinationVertex))) {
						return r;
					}
				}
			}
			
		
		return null;
	}

	
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		edgeSet.add(newRoad);
	
		if(containsVertex(sourceVertex) && containsVertex(destinationVertex)) {
			int from = vertexList.get(sourceVertex);
			int to = vertexList.get(destinationVertex);
			
			if(adjMatrix[to][from] == 0 && adjMatrix[from][to] == 0) {
				adjMatrix[to][from] = weight;
				adjMatrix[from][to] = weight;
				return newRoad;
			}
			else {
				return getEdge(sourceVertex, destinationVertex);
			}
		}
		else {
			throw new IllegalArgumentException();
		}	
	}

    
	@Override
	public boolean addVertex(Town v) {
		if(!containsVertex(v)) {
			vertexSet.add(v);
			vertexList.put(v,vertexCount);
			vertexCount++;
		}
		return true;
	}

	
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		for(Road r: edgeSet) {
			if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		
		return false;
		
	}

	
	@Override
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		if(vertexSet.contains(v))
			return true;
		for(Town t:vertexSet) {
			if(t.equals(v)) {
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public Set<Road> edgeSet() {
		return edgeSet;
	}
	
	
	@Override
	public Set<Town> vertexSet() {
		return vertexSet;
	}

	
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> temp = new HashSet<>();
		for(Road r:edgeSet) {
			if(r.contains(vertex)){
				temp.add(r);
			}
		}
		
		return temp;
	}
	
	
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		for(Road r:edgeSet) {
			if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
				if(r.getWeight() == weight && r.getName().equals(description)) {
					int from = vertexList.get(sourceVertex);
					int to = vertexList.get(destinationVertex);
					Road copy = r;
					edgeSet.remove(r);
					adjMatrix[from][to] = 0;
					adjMatrix[to][from] = 0;
					
					return copy;
				}
			}
		}
		return null;
	}

	
	@Override
	public boolean removeVertex(Town v) {
		if(containsVertex(v)) {
			Set<Road> temp = edgesOf(v);
			for(Road r: temp) {
				removeEdge(r.getSource(), r.getDestination(), r.getWeight(), r.getName());
			}
			vertexSet.remove(v);
			vertexList.remove(v);
			return true;
		}
		
		return false;
	}

	
	public void printMatrix() {
		
		for(int i = 0; i < adjMatrix.length; i++) {
			for(int j = 0; j < adjMatrix[i].length; j++) {
				System.out.print(adjMatrix[i][j]+ "|");
			}
			System.out.println();
		}
		
	}

	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> shortestPath = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		
		Town previous = destinationVertex;
		
		while(previous != null) {
			Town current = previous;
			previous = prev.get(previous);
			Road path = getEdge(current, previous);
			
			if(previous != null){
				shortestPath.add(previous.getName() + " via " + path.getName() + " to " + current.getName() + " " + path.getWeight() + " mi");
			}
		}
		
		Collections.reverse(shortestPath);
		
		return shortestPath;
	}

	 
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		for (Town t : vertexSet) {
			distance.put(t, Integer.MAX_VALUE);
			prev.put(t, null);
			unvisited.add(t);
		}
		
		distance.put(sourceVertex, 0);
		
		
		while (!unvisited.isEmpty()) {
			Town nearest = getNearestUnvisited();
			unvisited.remove(nearest);
			Set<Town> neighbors = getUnvisitedNeighbors(nearest);
			
			for (Town t : neighbors) {
				if(t != null) {
					int netWeight = distance.get(nearest) + getEdge(nearest, t).getWeight();
					
					if(netWeight < distance.get(t)) {
						distance.put(t, netWeight);
						prev.put(t, nearest);
					}
				}	
			}
		}
	}
	
	
	public Set<Town> getUnvisitedNeighbors(Town v) {
		Set<Town> unvisitedNeighbors = new HashSet<>();
		
		for (Road r : edgesOf(v)) {
			Town neighbor = r.getSource();
			
			if(r.getSource() == v) {
				neighbor = r.getDestination();
			}
			if (unvisited.contains(neighbor) && !visited.contains(neighbor))
				unvisitedNeighbors.add(neighbor);
		}
		return unvisitedNeighbors;
	}
	
	
	public Town getNearestUnvisited() {
		int minWeight = Integer.MAX_VALUE;
		Town nearest = null;
		
		for (Town town : unvisited) {
			if (distance.get(town) <= minWeight) {
				minWeight = distance.get(town);
				nearest = town;
			}
		}
		
		return nearest;
	}
	

}