import java.io.Serializable;

public class Main implements Serializable{
	
	public static Board mainBoard = new Board(500);
	public static int numPlayers = 2;
	
	public Main(){
		
	}

	public static void main(String[] args) {
		GetSetup.main(args);
	}

}
