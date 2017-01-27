import java.awt.Color;
import java.io.Serializable;

public class Plant implements Serializable{

	private static final long serialVersionUID = 1L;

	public static String[] allPlantTypes = {};
	
	public String plantType;
	public double resilience;
	public double quality;
	public Color color;

	public Plant() {
		// TODO Auto-generated constructor stub
	}

}
