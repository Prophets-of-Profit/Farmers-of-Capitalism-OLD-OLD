
public class Main {
	
	public static Board mainBoard = new Board(500);

	public static void main(String[] args) {
		for(int i = 0; i < 101; i++){
			for(int j = 0; j < 101; j++){
				System.out.print(mainBoard.Board[j][i].getBiome().name);
				for(int k = 0; k < 15 - mainBoard.Board[j][i].getBiome().name.length(); k++){
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}

}
