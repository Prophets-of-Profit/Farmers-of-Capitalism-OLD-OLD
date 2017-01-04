import java.io.Serializable;

public class Tile implements Serializable{
	
	public double soilQuality;
	public double environmentQuality;
	private double temperature;
	private Biome biome;
	public Plant plant;
	public Improvement improvement;
	public Player owner;

	public Tile(Biome biome) {
		this.biome = biome;
		this.soilQuality = 5 + Math.random() * 5;
		this.environmentQuality = 5 + Math.random() * 5;
		this.temperature = biome.avgTemperature + Math.random() * 0.50 * biome.avgTemperature - 0.25 * biome.avgTemperature;
		this.plant = null;
		this.improvement = null;
		this.owner = null;
	}
	
	public double getTemperature(){
		return this.temperature;
	}
	
	public void setTemperature(double temperature){
		Biome newBiome = this.biome;
		if(this.biome.name.equals("mountains") || this.biome.name.equals("hills")){
			do{
				newBiome = Biome.generateSimilarBiome(temperature, Biome.naturalBiomes);
			}while(!(newBiome.name.equals("mountains") || newBiome.name.equals("hills")));
		}else if(temperature < this.biome.avgTemperature - 20 || temperature > this.biome.avgTemperature + 20 ){
			do{
				newBiome = Biome.generateSimilarBiome(temperature, Biome.naturalBiomes);
			}while(this.biome.isLiquid != newBiome.isLiquid);
		}
		this.biome = newBiome;
		this.temperature = temperature;
	}
	
	public void terraformTile(double temperature){
		this.biome = Biome.generateSimilarBiome(temperature, Biome.allBiomes);
		this.temperature = temperature;
	}
	
	public Biome getBiome(){
		return this.biome;
	}
	
	public double getPlayerDamage(double[] tempRange){
		double dmg = 0;
		dmg += biome.additionalPlayerDamage;
		if(improvement != null){
			dmg += improvement.playerDamage;
		}
		if(temperature < tempRange[0]){
			dmg += tempRange[0] - temperature;
		}else if(temperature > tempRange[1]){
			dmg += temperature - tempRange[1];
		}
		return dmg;
	}
	
	public double getPlayerMovementCost(){
		double moveCost = 0;
		moveCost += biome.movementChange;
		if(improvement != null){
			moveCost += improvement.movementChange;
		}
		return moveCost;
	}
	
	@Override
	public String toString(){
		return this.biome.name + ": (Temperature: " + this.temperature + ", Environment: " + this.environmentQuality + ", Soil: " + this.soilQuality + ")";
	}

}
