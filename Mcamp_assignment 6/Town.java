
//Matthew Campbell
public class Town implements Comparable<Town>{

	private String name;
	

	public Town(String name) {
		this.name = name;
	}

	
	public Town(Town copy) {
		this.name = copy.getName();
	}
	
	
	public String getName() {
		return name;
	}
	

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
	@Override
	public boolean equals(Object o) {
		Town t = (Town)o;
		return this.name.equals(t.getName());
	}
	
	
	public int compareTo(Town T) {
		int status = 1;
		
		if(this.name.equals(T.getName())) {
			status = 0;
		}
	
		return status;
	}

}
