import java.io.Serializable;

public class Improvement implements Serializable{
	
	public String name;
	public double movementChange;
	public double playerDamage;
	public Inventory storage = null;
	public double helpsSurrounding;
	public int radius;
	public String whatHelped;
	
	public Improvement(){
		name = "generic";
		movementChange = 0;
		playerDamage = 0;
		storage = null;
		helpsSurrounding = 0;
		radius = 0;
		whatHelped = "";
	}
	
	public Improvement(Inventory inv){
		name = "generic";
		movementChange = 0;
		playerDamage = 0;
		storage = inv;
		helpsSurrounding = 0;
		radius = 0;
		whatHelped = "";
	}
	
	public Improvement(double moveChange, double damage){
		name = "generic";
		movementChange = moveChange;
		playerDamage = damage;
		storage = null;
		helpsSurrounding = 0;
		radius = 0;
		whatHelped = "";
	}
	
	public Improvement(String toHelp, int range, double helpings) {
		name = "generic";
		movementChange = 0;
		playerDamage = 0;
		storage = null;
		helpsSurrounding = helpings;
		radius = range;
		whatHelped = toHelp;
	}
	
	public void main(Player stepper){}
	
	public class Road extends Improvement{
		public Road(double moveChange) {
			super(moveChange, 0);
			Improvement.this.name = "road";
		}	
	}
	
	public class Silo extends Improvement{
		public Silo(int invSize){
			super(new Inventory(invSize));
			Improvement.this.name = "silo";
		}
		
		@Override
		public void main(Player stepper){}
	}
	
	public class Trap extends Improvement{
		public Trap(double moveInhibit, double playerDmg){
			super(moveInhibit, playerDmg);
			Improvement.this.name = "none";
		}
		
		@Override
		public void main(Player stepper){}
	}
	
	public class IrrigationChannel extends Improvement{
		public IrrigationChannel(int range, double howMuchHelped){
			super("soil quality", range, howMuchHelped);
			Improvement.this.movementChange = (int) -0.1 * howMuchHelped;
			Improvement.this.name = "irrigation channel";
		}
	}
	
	public class Sprinkler extends Improvement{
		public Sprinkler(int range, double howMuchHelped){
			super("soil quality", range, howMuchHelped);
			Improvement.this.name = "sprinkler";
		}
	}
	
	public class Tunnel extends Improvement{
		
		int[] locations = new int[4];
		
		public Tunnel(int[] firstLocation, int[] secondLocation){
			super("soil quality", (int) (Math.sqrt(Math.pow(firstLocation[0] - secondLocation[0], 2) + Math.pow(firstLocation[1] - secondLocation[1], 2)) / 15), (int) (-1 * Math.sqrt(Math.pow(firstLocation[0] - secondLocation[0], 2) + Math.pow(firstLocation[1] - secondLocation[1], 2)) / 10));
			locations[0] = firstLocation[0];
			locations[1] = firstLocation[1];
			locations[2] = secondLocation[0];
			locations[3] = secondLocation[1];
			Improvement.this.name = "tunnel";
		}
		
		@Override
		public void main(Player stepper){}
	}
	
	public class Teleporter extends Improvement{
		
		int[] locations = new int[4];
		
		public Teleporter(int[] firstLocation, int[] secondLocation){
			super();
			locations[0] = firstLocation[0];
			locations[1] = firstLocation[1];
			locations[2] = secondLocation[0];
			locations[3] = secondLocation[1];
			Improvement.this.name = "teleporter";
		}
		
		@Override
		public void main(Player stepper){}
	}
	
	public class VotingMachine extends Improvement{
		public VotingMachine(){
			super();
			Improvement.this.name = "voting machine";
		}
		
		@Override
		public void main(Player stepper){}
	}

}
