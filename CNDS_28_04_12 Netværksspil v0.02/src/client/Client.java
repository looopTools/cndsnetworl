package client;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Client extends JFrame {

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

	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private KeyClass ko;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private JLabel p1Score, p2Score, p3Score, p4Score;

	public Client() throws UnknownHostException, IOException {
		super("Le Beaver game");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 0);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.setVisible(true);
		Socket socket = new Socket("localhost", 6855);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
		ko = new KeyClass(out);
		this.addKeyListener(ko);
		draw();
		new ClientThread().start();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client g = new Client();
		g.setVisible(true);

	}

	private class ClientThread extends Thread {

		@SuppressWarnings("unused")
		public void run() {
			Socket socket;
			try {
				socket = new Socket("localhost", 1337);
				while (true) {
					int[] update = (int[]) in.readObject();
					performUpdate(update);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
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
				} else if (level[i][j].equalsIgnoreCase("p1Score")) {
					p1Score = new JLabel("0");
					p1Score.setSize(50, 50);
					this.add(p1Score);
					labels.add(p1Score);
				} else if (level[i][j].equalsIgnoreCase("p2Score")) {
					p2Score = new JLabel("0");
					p2Score.setSize(50, 50);
					this.add(p2Score);
					labels.add(p2Score);
				} else if (level[i][j].equalsIgnoreCase("p3Score")) {
					p3Score = new JLabel("0");
					p3Score.setSize(50, 50);
					this.add(p3Score);
					labels.add(p3Score);
				} else if (level[i][j].equalsIgnoreCase("p4Score")) {
					p4Score = new JLabel("0");
					p4Score.setSize(50, 50);
					this.add(p4Score);
					labels.add(p4Score);
				}

			}

		}
		this.validate();
	}
}
