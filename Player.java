import java.util.ArrayList;

public class Player {
	
	public String playerName;
	public double amtMoney;
	public int[] amtMoves = new int[2];
	public double[] health = new double[2];
	public int[] location = new int[2];
	public double[] survivableTempRange = new double[2];
	public ArrayList<String> purchasableServices;
	public Inventory charInv;

	public Player(String name, int xCoord, int yCoord) {
		playerName = name;
		amtMoney = 0;
		amtMoves[0] = 25;
		amtMoves[1] = 25;
		health[0] = 100;
		health[1] = 100;
		location[0] = xCoord;
		location[1] = yCoord;
		purchasableServices = new ArrayList<String>();
		charInv = new Inventory(6);
	}

}
