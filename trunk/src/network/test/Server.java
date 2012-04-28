package network.test;

import game.test.Game;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private ArrayList<Game> games = new ArrayList<Game>();

	private ServerSocket server;
	
	private int index;

	public Server() {
		index = 0;
		try{
			waitForPlayers();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public void waitForPlayers() throws IOException {
		int port = 1337; // Cause we know da 1337;

		server = new ServerSocket(port);

		while (true) {
			System.out.println("Waiting for new beaves to the nest");
			Socket accpetSocket = server.accept();
			if(index <= 4){
				Game g = new Game(); //accpetSocket, InetAddress.getLocalHost().toString()
				games.add(g);
				//g.start();
				index++;
				System.out.println("Connection from beaver: " + accpetSocket.getRemoteSocketAddress());
				server.accept();
			}else{
				System.out.println("To many beavers in the nest");
			}

		}
	}
	
	public void fireUpdate(int[] update) throws IOException{
		for(Game g : games){
			//g.getOut().writeObject(update);
		}
	}
	
	public static void main(String[] args){
		new Server();
	}
}
