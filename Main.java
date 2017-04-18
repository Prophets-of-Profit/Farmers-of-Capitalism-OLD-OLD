import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.filechooser.FileSystemView;

public class Main implements Serializable{

	private static final long serialVersionUID = 1L;

	public static Main game;
	public static int turnLimit = 500;
	public static int boardSize = 500;
	
	public Board mainBoard = new Board(boardSize);
	public String mode = "multi-device || local";
	public int numPlayers = 0;
	public Player[] players = null;
	public String startTime;
	public int turnCount = 0;
	public boolean allowMove = false;
	
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
			allowMove = true;
		}else if(mode.equals("multi-device")){
			//TODO
		}
	}

	public static void main(String[] args) throws InterruptedException{
		InitGame.main(args);
		while(InitGame.gameAction.equals("none")){
			Thread.sleep(15);
		}
		args = InitGame.logs;
		if(InitGame.gameAction.equals("new game")){
			InitGame.window.die();
			game = new Main(args);
		}else{
			try{
				FileInputStream fileIn = new FileInputStream(InitGame.gameAction);
		        ObjectInputStream in = new ObjectInputStream(fileIn);
		        game = (Main) in.readObject();
		        in.close();
		        fileIn.close();
		        for(int i = 0; i < args.length; i++){
		        	game.log(args[i]);
		        }
		        game.log("Deserialized and successfully loaded game");
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
			InitGame.window.die();
		}
		//Send up GUI
		while(game.turnCount < turnLimit){
			if(game.allowMove){
				//TODO
				if(game.mode.equals("multi-device")){
					//TODO send game object over socket
				}
			}else if(game.mode.equals("multi-device")){
				//TODO receive game object over socket and display changes
			}
		}
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

	public void save(){
		log("Saving game");
		try{
			FileOutputStream fileOut = new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\My Games\\Farmers of Capitalism\\Saves\\gameOf[" + startTime + "].ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this);
	        log("Successfully and gracefully saved game");
	        out.writeObject(this);
	        out.close();
	        fileOut.close();
		}catch (Exception e){
			log(e.getMessage());
		}
	}
	
	public void writeGameToSocket(){
		//TODO
	}

}
