package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game extends Thread {

	/**
	 * @param args
	 *
	 */
	public ArrayList<Player> players = new ArrayList<Player>();
	public Player me;
	public Gameplayer g;

	public void run() {

		System.out.println("Indtast dit spillernavn");
		BufferedReader b = new BufferedReader (new InputStreamReader(System.in));
		String in;
		try {
			in = b.readLine();
			
			me = new Player(in);
//			me.setGame(this);
			players.add(me);
			ScoreList s = new ScoreList(players);
			s.setVisible(true);
			g = new Gameplayer(me,s);

			
			
			
			// players.add(new Player("FUP"));

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Gameplayer getGamePlayer(){
		return this.g;
	}
	
	

}
