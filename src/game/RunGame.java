package game;

import network.Server;

public class RunGame {
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		System.out.println("Server started!");
	}
}
