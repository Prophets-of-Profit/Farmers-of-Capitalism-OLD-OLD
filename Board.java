import java.util.ArrayList;

public class Board {
	
	public int boardLength;
	public Tile[][] Board;

	public Board(int length) {
		this.boardLength = length;
		Board = new Tile[length][length];
		for(int i = 0; i < length; i++){
			for(int j = 0; j < length; j++){
				Board[i][j] = null;
			}
		}
		int xCoord;
		int yCoord;
		int squareLength;
		Biome biome = Biome.naturalBiomes[(int) (Math.random() * Biome.naturalBiomes.length)];
		ArrayList<Biome> touching = new ArrayList<Biome>();
		long amtNulls = length * length;
		do{
			xCoord = (int) (Math.random() * length);
			yCoord = (int) (Math.random() * length);
			squareLength = (int) (0.01 * (length + 1) * Math.random());
			touching.clear();
			for(int i = -1; i <= squareLength; i++){
				for(int j = -1; j <= squareLength; j++){
					if(xCoord + i >= 0 && yCoord + j >= 0 && xCoord + i < length && yCoord + j < length && Board[xCoord + i][yCoord + j] != null){
						touching.add(Board[xCoord + i][yCoord + j].getBiome());
					}
				}
			}
			if(touching.size() == 0){
				biome = Biome.naturalBiomes[(int) (Math.random() * Biome.naturalBiomes.length)];
			}else{
				biome = Biome.generateSimilarBiome(touching.get((int) (Math.random() * touching.size())), Biome.naturalBiomes);
			}
			for(int i = 0; i < squareLength; i++){
				for(int j = 0; j < squareLength; j++){
					if(xCoord + i < length && yCoord + j < length && Board[xCoord + i][yCoord + j] == null){
						Board[xCoord + i][yCoord + j] = new Tile(biome);
						amtNulls--;
					}
				}
			}
			//System.out.println(amtNulls);
		}while(amtNulls > 0);
	}

}
