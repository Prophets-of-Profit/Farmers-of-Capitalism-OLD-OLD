import java.awt.Color;
import java.io.Serializable;

public class Market implements Serializable{
	
	public Color[] preferredColorOrder = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
	public String[] plantBiases = Plant.allPlantTypes.clone();
	private double quality;
	
	public Market(double value) {
		shuffleBiases();
		quality = value;
	}
	
	public double getPrice(Plant toSell){
		int indexOfColor = 0;
		int indexOfName = 0;
		for(int i = 0; i < preferredColorOrder.length; i++){
			if(toSell.color.equals(preferredColorOrder[i])){
				indexOfColor = i;
			}
		}
		for(int i = 0; i < plantBiases.length; i++){
			if(plantBiases[i].equals(toSell.plantType)){
				indexOfName = i;
			}
		}
		double price = quality * toSell.quality * (1 + indexOfColor / (100 * preferredColorOrder.length)) * (1 + indexOfName / (100 * plantBiases.length));
		return price;
	}
	
	public void updateBiases(double weight){
		for(int i = 0; i < preferredColorOrder.length - 1; i++){
			Color tempolor;
			String templant;
			if(Math.random() * weight > 0.9){
				tempolor = preferredColorOrder[i];
				preferredColorOrder[i] = preferredColorOrder[i + 1];
				preferredColorOrder[i + 1] = tempolor;
			}
			if(Math.random() * weight > 0.9){
				templant = plantBiases[i];
				plantBiases[i] = plantBiases[i + 1];
				plantBiases[i + 1] = templant;
			}
		}
	}
	
	public void shuffleBiases(){
		int randLoc;
		Color tempolor;
		String templant;
		for(int i = preferredColorOrder.length; i > 0; i--){
			randLoc = (int) (Math.random() * i);
			tempolor = preferredColorOrder[i];
			templant = plantBiases[i];
			preferredColorOrder[i] = preferredColorOrder[randLoc];
			plantBiases[i] = plantBiases[randLoc];
			preferredColorOrder[randLoc] = tempolor;
			plantBiases[randLoc] = templant;
		}
	}

}
