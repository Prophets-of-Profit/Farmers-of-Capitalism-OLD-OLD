import java.io.Serializable;

public class Main implements Serializable{
	
	public static Main game;
	
	public Board mainBoard = new Board(500);
	public int numPlayers = 0;
	public Player[] players = null;
	
	public Main(String[] args) throws InterruptedException{
		NewGameSetup.main(this);
		System.out.println("Getting number of players");
		while(numPlayers == 0){
			Thread.sleep(15);
		}
		System.out.println(numPlayers + " players are going to play");
		players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++){
			players[i] = new Player("Trashlord Yang", 0, 0);
		}
	}

	public static void main(String[] args) throws InterruptedException{
		game = new Main(args);
	}

}
