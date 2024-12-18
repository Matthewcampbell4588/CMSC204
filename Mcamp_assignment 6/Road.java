
public class Road implements Comparable<Road>{
	//Matthew Campbell
	private String name;
	private int weight;
	private Town source;
	private Town destination;
	
	
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.weight = weight; 
	}

	
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.weight = 1;
	}

	
	public String getName() {
		return name;
	}
	
	
	public Town getSource() {
		return source;
	}
	
	
	public int getWeight() {
		return weight;
	}
	
	
	public Town getDestination() {
		return destination;
	}
	
	
	public boolean contains(Town town){	
		if(town == null) {
			return false;
		}
		return town.equals(source) || town.equals(destination);
	}
	
	
	@Override
	public boolean equals(Object o) {
		boolean flag = false;
		Road r = (Road)o;
		if((this.destination.equals(r.getDestination()) || this.source.equals(r.getDestination()))
			 && (this.destination.equals(r.getSource()) || this.source.equals(r.getSource()))) {
			flag = true;
		}

		return flag;
	}
	
	
	@Override
	public String toString() {
		return "The road " + this.name + " is " + this.weight + " miles long and starts at " + this.source + " and it ends at " + this.destination;
	}	
	
	
	@Override
	public int compareTo(Road o) {
		return this.weight - o.getWeight();
	}

}
