package network;

import game.Game;
import game.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	
	private ArrayList<TCPThread> threads;
	private ServerSocket welcomeSocket;
	private static Game game;
	
	public Server(){
		game = new Game();
		game.start();
		
	}
	
	public void run(){
//		ArrayList<E>
		try{
			welcomeSocket = new ServerSocket(3000);
			while(true){
				Socket connectionSocket = welcomeSocket.accept();
				// TODO : Fix player in TCPThread constructor.
				TCPThread tserv = new TCPThread(new Player("Erling"), connectionSocket);
				threads.add(tserv);
				tserv.start();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static Game getGame(){
		return game;
	}
}
