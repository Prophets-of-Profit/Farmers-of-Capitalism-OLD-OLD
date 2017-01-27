import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.filechooser.FileSystemView;

public class Main implements Serializable{

	private static final long serialVersionUID = 1L;

	public static Main game;
	
	public Board mainBoard = new Board(500);
	public String mode = "multi-device || local";
	public int numPlayers = 0;
	public Player[] players = null;
	public String startTime;
	
	public Main(String[] args) throws InterruptedException{
		startTime = new SimpleDateFormat("dd,MM,yy;HH,mm,ss").format(Calendar.getInstance().getTime());
		for(int i = 0; i < args.length; i++){
			log(args[i]);
		}
		log("Constructing new game");
		NewGameSetup.main(this);
		log("Getting number of players and mode");
		while(numPlayers == 0){
			Thread.sleep(15);
		}
		players = new Player[numPlayers];
		log(numPlayers + " players are going to play");
		log(mode + " was the selected mode");
		if(mode.equals("local")){
			for(int i = 0; i < numPlayers; i++){
				log("Making Player " + (i + 1));
				MakePlayer.main(i, this);
				while(players[i] == null){
					Thread.sleep(15);
				}
				log("Player " + (i + 1) + " has chosen the name \"" + players[i].playerName + "\"");
			}
		
		}
	}

	public static void main(String[] args) throws InterruptedException{
		game = new Main(args); //TODO find out if they want to use a saved game or to start a new game, and make strings for that, and then pass that on as the argument to main for main to log
	}
	
	public void log(String messageToLog){
		messageToLog = "[" + new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(Calendar.getInstance().getTime()) + "] : " + messageToLog;
		System.out.println(messageToLog);
		try{
			File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\My Games\\Farmers of Capitalism\\Logs\\gameOf[" + startTime + "];log.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(messageToLog);
			bufferWriter.newLine();
			bufferWriter.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
