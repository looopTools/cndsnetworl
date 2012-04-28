package network;

import game.test.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {

	private Game game;
	private ArrayList<Player> players;
	private ServerSocket server;
	private String[][] level;
	
	public Server() throws UnknownHostException, IOException{
		game = new Game();
		//level = game.getGamePlayer().getLevel();
		players = new ArrayList<Player>();
	}
	
	public void waitForPalyers() throws IOException{
		int port = 1337; //The port which the players connect so
		int index = 0; //To keep track on the number of players and their index
		
		server = new ServerSocket(port);
		
		while(true){
			System.out.println("Waiting for a new beaver to the pond - Number of beavers in the pond right now: " + index);
			Socket acceptSocket = server.accept(); //Used to accpet players
			if(players.get(index).equals(null) && index <= 3){
				String name = "Beaver" + index+1;
				Player p; 
				if(index == 0){
					p = new Player(acceptSocket, 1, 1, 1, name);
					players.add(p);
					p.start();
				}
				else if(index == 1){
					p = new Player(acceptSocket, 10, 1, 2, name);
					players.add(p);
					p.start();
				}
				else if(index == 2){
					p = new Player(acceptSocket, 10, 5, 3, name);
					players.add(p);
					p.start();
				}
				else if(index == 3){
					p = new Player(acceptSocket, 10, 5, 4, name);
					players.add(p);
					p.start();
				}
				System.out.println("Forbindelse opnŒet fra " + acceptSocket.getRemoteSocketAddress());
				server.accept();
				
			}
		}
		
	}

	private class Player extends Thread {
		private int xPos, yPos, score, number;
		private String name;
		private ObjectOutputStream out;
		private ObjectInputStream in;

		public Player(Socket socket, int xPos, int yPos, int number,
				String name) throws IOException {
			this.xPos = xPos;
			this.yPos = yPos;
			this.number = number;
			this.score = 0;
			this.name = name;

			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		}

		public synchronized void checkCollision(int[] update)
				throws IOException {
			update[1] = 1;
			update[5] = xPos;
			update[6] = yPos;
			if (number != 1 && players.get(1) != null) {
				if (xPos == players.get(1).xPos && yPos == players.get(1).yPos) {
					score += 50;
					players.get(1).score -= 50;
					update[2] = score;
					int[] otherUpdate = {1, 1, players.get(1).score, players.get(1).xPos,
							players.get(1).yPos, 1, 1};
					players.get(1).xPos = 1;
					players.get(1).yPos = 1;
					fireUpdate(otherUpdate);
				}
			} else if (number != 2 && players.get(2) != null) {
				if (xPos == players.get(2).xPos && yPos == players.get(2).yPos) {
					score += 50;
					players.get(2).score -= 50;
					update[2] = score;
					int[] otherUpdate = {1, 1, players.get(2).score, players.get(2).xPos,
							players.get(2).yPos, 10, 1};
					players.get(2).xPos = 10;
					players.get(2).yPos = 1;
					fireUpdate(otherUpdate);
				}
			} else if (number != 3 && players.get(3) != null) {
				if (xPos == players.get(3).xPos && yPos == players.get(3).yPos) {
					score += 50;
					players.get(3).score -= 50;
					update[2] = score;
					int[] otherUpdate = {1, 1, players.get(3).score, players.get(3).xPos,
							players.get(3).yPos, 10, 5};
					players.get(3).xPos = 10;
					players.get(3).yPos = 5;
					fireUpdate(otherUpdate);
				}
			} else if (number != 4 && players.get(3) != null) {
				if (xPos == players.get(4).xPos && yPos == players.get(4).yPos) {
					score += 50;
					players.get(4).score -= 50;
					update[2] = score;
					int[] otherUpdate = {1, 1, players.get(4).score, players.get(4).xPos,
							players.get(4).yPos, 1, 5};
					players.get(2).xPos = 1;
					players.get(2).yPos = 5;
					fireUpdate(otherUpdate);
				}
			}
		}
		public void run() {

			try {
				int[] startUpdate = { number, 1, score, xPos, yPos, xPos, yPos };
				fireUpdate(startUpdate);
				while (true) {
					String message = (String) in.readObject();
					int[] update = { number, 0, score, xPos, yPos, xPos, yPos };
					score++;
					if (message.equals("up")) {
						yPos--;
						if (level[xPos][yPos].equals("w")) {
							score = score - 2;
							yPos++;
//							update[6] = yPos;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("right")) {
						xPos++;
						if (level[xPos][yPos].equals("w")) {
							score = score - 2;
							xPos--;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("down")) {
						yPos++;
						if (level[xPos][yPos].equals("w")) {
							score = score - 2;
							yPos--;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("left")) {
						xPos--;
						if (level[xPos][yPos].equals("w")) {
							score = score - 2;
							xPos++;
						} else {
							checkCollision(update);
						}
					}
					update[2] = score;
					fireUpdate(update);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public void fireUpdate(int[] update) throws IOException {

		if (players.get(1) != null) {
			players.get(1).out.writeObject(update);
			players.get(1).out.flush();
		}
		if (players.get(2) != null) {
			players.get(2).out.writeObject(update);
			players.get(2).out.flush();
		}
		if (players.get(3) != null) {
			players.get(3).out.writeObject(update);
			players.get(3).out.flush();
		}
		if (players.get(4) != null) {
			players.get(4).out.writeObject(update);
			players.get(4).out.flush();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		new Server();
	}
	
	


}
