package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private String[][] level = {
			{ "p1Score", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"p3Score" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "w", "e", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "w", "w", "w", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "w", "w", "w", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "p4Score", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"p2Score" }, };

	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;

	private ServerSocket server;

	private class Player extends Thread {
		private int xPos;
		private int yPos;
		private int score;
		private int number;
		private ObjectOutputStream out;
		private ObjectInputStream in;

		public Player(Socket socket, int xPos, int yPos, int number) throws IOException {
			this.xPos = xPos;
			this.yPos = yPos;
			this.number = number;
			score = 0;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		}

		public synchronized void checkCollision(int[] update) throws IOException {
			update[1] = 1;
			update[5] = xPos;
			update[6] = yPos;
			if (number != 1 && player1 != null) {
				if (xPos == player1.xPos && yPos == player1.yPos) {
					score += 50;
					player1.score -= 50;
					update[2] = score;
					int[] otherUpdate = { 1, 1, player1.score, player1.xPos, player1.yPos, 1, 1 };
					player1.xPos = 1;
					player1.yPos = 1;
					fireUpdate(otherUpdate);
				}
			} else if (number != 2 && player2 != null) {
				if (xPos == player2.xPos && yPos == player2.yPos) {
					score += 50;
					player2.score -= 50;
					update[2] = score;
					int[] otherUpdate = { 1, 1, player2.score, player2.xPos, player2.yPos, 10, 1 };
					player2.xPos = 10;
					player2.yPos = 1;
					fireUpdate(otherUpdate);
				}
			} else if (number != 3 && player3 != null) {
				if (xPos == player3.xPos && yPos == player3.yPos) {
					score += 50;
					player3.score -= 50;
					update[2] = score;
					int[] otherUpdate = { 1, 1, player3.score, player3.xPos, player3.yPos, 10, 5 };
					player3.xPos = 10;
					player3.yPos = 5;
					fireUpdate(otherUpdate);
				}
			} else if (number != 4 && player3 != null) {
				if (xPos == player4.xPos && yPos == player4.yPos) {
					score += 50;
					player4.score -= 50;
					update[2] = score;
					int[] otherUpdate = { 1, 1, player4.score, player4.xPos, player4.yPos, 1, 5 };
					player2.xPos = 1;
					player2.yPos = 5;
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

		if (player1 != null) {
			player1.out.writeObject(update);
			player1.out.flush();
		}
		if (player2 != null) {
			player2.out.writeObject(update);
			player2.out.flush();
		}
		if (player3 != null) {
			player3.out.writeObject(update);
			player3.out.flush();
		}
		if (player4 != null) {
			player4.out.writeObject(update);
			player4.out.flush();
		}
	}

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		try {
			waitForClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForClients() throws IOException {
		int port = 6855;
		server = new ServerSocket(port);
		while (true) {
			System.out.println("Venter spændt på forbindelse..");
			Socket acceptSocket = server.accept();
			if (player1 == null) {
				player1 = new Player(acceptSocket, 1, 1, 1);
				player1.start();
			} else if (player2 == null) {
				player2 = new Player(acceptSocket, 10, 1, 2);
				player2.start();
			} else if (player3 == null) {
				player3 = new Player(acceptSocket, 10, 5, 3);
				player3.start();
			} else if (player4 == null) {
				player4 = new Player(acceptSocket, 1, 5, 4);
				player4.start();	
			}
			System.out.println("Forbindelse opnået fra " + acceptSocket.getRemoteSocketAddress());
			server.accept();
		}

	}

}
