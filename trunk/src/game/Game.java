package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Game extends Thread {

	/**
	 * @param args
	 *
	 */
	
	private Player me;
	private Gameplayer g;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	
	//Our adds
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	//
	public Game(){
		
		
	}
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
	
	//---our changes--
	
	public void setUpPlayer(){
		
	}
	public Gameplayer getGamePlayer(){
		return this.g;
	}
	
	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}
	
	

}
