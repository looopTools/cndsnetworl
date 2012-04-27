package game;

import java.util.Random;

import network.Server;

public class Player {
	
	private String name;
	private int xpos;
	private int ypos;
	private int points;
	private Game game = null;


	private String direction;

	public Player (String name) {
		this.name = name;
//		this.generateXAndY();
//		int[] coordinates = Server.getGame().getGamePlayer().spawn();
//		this.xpos = coordinates[0];
//		this.ypos = coordinates[1];
		xpos = ypos = 1;
		this.points = 0;
		this.direction = "up";
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String ToString () {
		return name + "   " + points;
	}
	public void addOnePoint() {

		points ++;
	}

	public void subOnePoint() {
		points --;
	}

	public void playerKilled(){
		points += 50;
	}

	public void killedByPlayer(){
		points -= 50;
	}
	

	public void generateXAndY(){
		Random r = new Random();
		
		String[][] temp = game.getGamePlayer().getLevel();
		
		this.setXpos(r.nextInt(20));
		this.setYpos(r.nextInt(20));
		
		while(temp[xpos][ypos].equals("w")){
			this.setXpos(r.nextInt(20));
			this.setYpos(r.nextInt(20));
		}
	}
	//TODO: Respawn;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}