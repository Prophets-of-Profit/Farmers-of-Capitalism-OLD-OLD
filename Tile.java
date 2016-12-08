
public class Tile {
	
	public double soilQuality;
	public double environmentQuality;
	private double temperature;
	private Biome biome;
	public Plant plant;
	public Improvement improvement;
	public Player owner;

	public Tile(Biome biome) {
		this.biome = biome;
		//soilQuality and environmentQuality have minimum values of 5, but they can be better
		this.soilQuality = 5 + Math.random() * 5;
		this.environmentQuality = 5 + Math.random() * 5;
		//The temperature will always be around the average temperature of the biome, but it can vary by + or - 0.25 of the average temperature
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
	
	public double getPlayerDamage(int[] tempRange){
		double dmg = 0;
		dmg += biome.additionalPlayerDamage;
		if(improvement != null){
			dmg += improvement.playerDamage;
		}
		//TODO add damage based on if temperature is not in temp range, and make it proportional to how far it is away from the temprange
		return dmg;
	}
	
	public double getPlayerMovementCost(){
		double moveCost = 1;
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
