package client;

import game.Game;
import game.KeyClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("serial")
public class PlayerClient {
	
	private Game game;
	private String[][] level;
	
	private KeyClass keyob;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public PlayerClient() throws UnknownHostException, IOException{
		game = new Game();
		level = game.getGamePlayer().getLevel();
		Socket socket = new Socket("localhost", 1337);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
		keyob = new KeyClass(game.getGamePlayer());
		game.getGamePlayer().getScreen().addKeyListener(keyob);
	}
	
	private class PlayerThread extends Thread{
		
		@SuppressWarnings("unused")
		public void run(){
			Socket socket;
			try{
				socket = new Socket("localhost", 1337);
				while(true){
					int[] update = (int[]) in.readObject();
					performUpdate(update);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void performUpdate(int[] update) {

	}
}
