package game.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("Serial")
public class Game extends Thread {
	private String[][] level = {
			{"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"w", "w", "w", "w", "w", "w", "w"},
			{"w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e",
					"e", "e", "e", "e", "e", "e", "w"},
			{"w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
					"e", "e", "e", "e", "e", "e", "w"},
			{"w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w",
					"e", "e", "w", "e", "e", "e", "w"},
			{"w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w",
					"e", "e", "w", "e", "e", "e", "w"},
			{"w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e",
					"e", "e", "w", "e", "e", "w", "w"},
			{"w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e",
					"e", "e", "e", "e", "e", "w", "w"},
			{"w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w",
					"e", "e", "w", "w", "e", "w", "w"},
			{"w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w",
					"e", "e", "e", "e", "e", "w", "w"},
			{"w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w",
					"e", "e", "e", "e", "e", "e", "w"},
			{"w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w",
					"w", "w", "w", "w", "w", "w", "w"},};

	private ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();
	private ArrayList<Player> splayers = new ArrayList<Game.Player>();
	private int index;
	private ServerSocket server;

	private class Player extends Thread {

		private int name;
		private int xpos;
		private int ypos;
		private int points;
		private ObjectOutputStream out;
		private ObjectInputStream in;

		private String direction;

		public Player(Socket socket, int xpos, int ypos, int name)
				throws IOException {
			this.name = name;
			this.xpos = xpos;
			this.ypos = ypos;
			this.points = 0;
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}

		public synchronized void checkCollision(int[] update) throws IOException {

			update[1] = 1;
			update[5] = xpos;
			update[6] = ypos;
			
			//String currentName = splayers.get(0).getName();
			
			for(int i = 0; i < splayers.size(); i++){
				if(xpos == splayers.get(i).xpos && ypos == splayers.get(i).ypos){
					points += 50;
					splayers.get(i).points -= 50;
					update[2] = points;
					int[] otherUpdate = { 1, 1, splayers.get(i).points, splayers.get(i).xpos, splayers.get(i).ypos, 1, 1 };
					splayers.get(i).xpos = 1;
					splayers.get(i).ypos = 1;
					fireUpdate(otherUpdate);
				}
			}
			
		}

		public void run() {
			try {
				int[] startUpdate = { name, 1, points, xpos, ypos, xpos, ypos };
				fireUpdate(startUpdate);
				while (true) {
					String message = (String) in.readObject();
					int[] update = { name, 0, points, xpos, ypos, xpos, ypos };
					points++;
					if (message.equals("up")) {
						ypos--;
						if (level[xpos][ypos].equals("w")) {
							points = points - 2;
							ypos++;
//							update[6] = yPos;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("right")) {
						xpos++;
						if (level[xpos][ypos].equals("w")) {
							points = points - 2;
							xpos--;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("down")) {
						ypos++;
						if (level[xpos][ypos].equals("w")) {
							points = points - 2;
							ypos--;
						} else {
							checkCollision(update);
						}
					} else if (message.equals("left")) {
						xpos--;
						if (level[xpos][ypos].equals("w")) {
							points = points - 2;
							xpos++;
						} else {
							checkCollision(update);
						}
					}
					update[2] = points;
					fireUpdate(update);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


		}

	}

	public void fireUpdate(int[] update) throws IOException {
		for(Player p : splayers){
			p.out.writeObject(update);
			p.out.flush();
		}
	}

	public Game() {
		index = 0;

		try {
			waitForPlayers();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void waitForPlayers() throws IOException {
		int port = 1337;
		server = new ServerSocket(port);

		while (true) {
			System.out.println("Waiting for new beavers to the nest");

			Socket acceptSocket = server.accept();

			if (index <= 4) {

				int[] info = generatePlayerData();
				Player p = new Player(acceptSocket, info[0], info[1], info[2]);
				splayers.add(p);
				//GamePlayer gp = new GamePlayer(acceptSocket, players);
				index++;
				System.out.println("Beaver: "
						+ acceptSocket.getRemoteSocketAddress()
						+ " has been add to the nest.");
				server.accept();
			} else {
				System.out.println("To many beaver in the nest");
			}

		}
	}

	public int[] generatePlayerData() {
		Random r = new Random();
		int[] result = {r.nextInt(20), r.nextInt(20), index + 1};

		return result;
	}

	public static void main(String[] args) {
		new Game();
	}

}
