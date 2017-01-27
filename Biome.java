import java.io.Serializable;

public class Biome implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	static Biome[] naturalBiomes = {new Biome("lake", 25, true), new Biome("rainforest", 35, false), new Biome("desert", 26, false), new Biome("plains", 24, false), new Biome("taiga", 10, false), new Biome("frozen lake", 0.01, true), new Biome("mountains", -7, false), new Biome("tundra", 5, false), new Biome("swamp", 30, true), new Biome("forest", 10, false), new Biome("savanna", 15, false), new Biome("alpine", 8, false), new Biome("pond", 10, true), new Biome("hills", 20, false, 3)};
	static Biome[] manualBiomes = {new Biome("river", 30, true), new Biome("fallout", 50, false), new Biome("battleground", 35, false), new Biome("e-waste", 0, false), new Biome("alien war zone", -50, false), new Biome("town", 20, false), new Biome("alien compound", -30, false), new Biome("robot outpost", 30, false), new Biome("molten cobalt ruins", 1500, true), new Biome("liquid nitrogen dump", -50, true), new Biome("landfill", 20, false), new Biome("etheral cemetery", -200, false), new Biome("the Sun King's wrathland", 1000, false, 10), new Biome("the Night Queen's eternal chamber", -250, false, 10)};
	static Biome[] allBiomes = combineLists(naturalBiomes, manualBiomes);
	
	public String name;
	public double avgTemperature;
	public boolean isLiquid;
	public double movementChange;
	public double additionalPlayerDamage;

	public Biome(String name, double avgTemperature, boolean isWater) {
		this.name = name;
		this.avgTemperature = avgTemperature;
		isLiquid = isWater;
		movementChange = 0;
		additionalPlayerDamage = 0;
	}
	
	public Biome(String name, double avgTemperature, boolean isWater, int changeAndDamage) {
		this.name = name;
		this.avgTemperature = avgTemperature;
		isLiquid = isWater;
		movementChange = changeAndDamage;
		additionalPlayerDamage = changeAndDamage;
	}
	
	public static boolean areSimilarBiomes(Biome first, Biome second){
		if(first == null || second == null || first.isLiquid != second.isLiquid){
			return false;
		}
		return first.avgTemperature - 10 <= second.avgTemperature && first.avgTemperature + 10 >= second.avgTemperature || second.avgTemperature - 10 < first.avgTemperature && second.avgTemperature + 10 > first.avgTemperature;
	}
	
	public static Biome generateSimilarBiome(Biome toBeSimilarTo, Biome[] toChooseFrom){
		Biome biome = null;
		while(!areSimilarBiomes(biome, toBeSimilarTo)){
			biome = toChooseFrom[(int) (Math.random() * toChooseFrom.length)];
		}
		return biome;
	}
	
	public static Biome generateSimilarBiome(double temperatureToBeSimilar, Biome[] toChooseFrom){
		Biome biome;
		do{
			biome = toChooseFrom[(int) (Math.random() * toChooseFrom.length)];
		}while(!(temperatureToBeSimilar + 10 >= biome.avgTemperature && temperatureToBeSimilar - 10 <= biome.avgTemperature || biome.avgTemperature - 10 <= temperatureToBeSimilar && biome.avgTemperature + 10 >= temperatureToBeSimilar));
		return biome;
	}
	
	private static Biome[] combineLists(Biome[] first, Biome[] second){
		Biome[] toReturn = new Biome[first.length + second.length];
		for(int i = 0; i < first.length; i++){
			toReturn[i] = first[i];
		}
		for(int i = first.length; i < first.length + second.length; i++){
			toReturn[i] = second[i - first.length];
		}
		return toReturn;
	}

}
