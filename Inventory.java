import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Plant> seeds;
	private ArrayList<Improvement> improvements;
	public int maxSize;

	public Inventory(int size) {
		seeds = new ArrayList<Plant>();
		improvements = new ArrayList<Improvement>();
		maxSize = size;
	}
	
	public boolean add(Plant itemToAdd){
		if(seeds.size() + improvements.size() < maxSize){
			seeds.add(itemToAdd);
			return true;
		}
		return false;
	}
	
	public boolean add(Improvement itemToAdd){
		if(seeds.size() + improvements.size() < maxSize){
			improvements.add(itemToAdd);
			return true;
		}
		return false;
	}
	
	public void remove(Plant itemToRemove){
		seeds.remove(itemToRemove);
	}
	
	public void remove(Improvement itemToRemove){
		improvements.remove(itemToRemove);
	}

}
