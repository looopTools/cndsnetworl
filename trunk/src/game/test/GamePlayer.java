package game.test;

import java.awt.GridLayout;
//import java.awt.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePlayer extends JFrame{
	
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
	
//	private JLabel[][] labels = new JLabel[20][20];
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private KeyClass ko;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private ArrayList<GamePlayer> players;
	
	
	
	
	public GamePlayer() throws UnknownHostException, IOException{
		super("Le Beaver nest");
//		ScoreList score = new ScoreList(players);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		Socket socket = new Socket("localhost", 1337);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
		ko = new KeyClass(out);
		draw();
		this.setAlwaysOnTop(true);
		this.repaint();
		this.setVisible(true);
		new PlayerThread().start();
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
	
	public void updatePlayers(ArrayList<GamePlayer> players){
		this.players = players;
	}
	
	public void performUpdate(int[] update) {
		if (update[1] == 1) { 
			labels.get((update[4] * 20) + update[3]).setIcon(new ImageIcon("./Image/gulv2.png"));
			labels.get((update[6] * 20) + update[5]).setIcon(new ImageIcon("./Image/HeltNed.png"));
		}
		if (update[0] == 1) {
			labels.get(0).setText(Integer.toString(update[2]));
		} else if (update[0] == 2) {
			labels.get(19).setText(Integer.toString(update[2]));
		} else if (update[0] == 3) {
			labels.get(380).setText(Integer.toString(update[2]));
		} else if (update[0] == 4) {
			labels.get(399).setText(Integer.toString(update[2]));
		}
	}

	public void close() {
		dispose();
	}
	
	public void draw() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 20; i++) {
				if (level[i][j].equalsIgnoreCase("w")) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur2(1).png"));
					l.setSize(50, 50);
					this.add(l);
					labels.add(l);
				} else if (level[i][j].equalsIgnoreCase("e")) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels.add(l);
				}

			}

		}
		this.validate();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		GamePlayer gp = new GamePlayer();
		gp.setVisible(true);
		
	}

}
