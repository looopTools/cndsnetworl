package game;

public class Player {
	
	private String name;
	private int xpos;
	private int ypos;
	private int points;


	private String direction;

	public Player (String name) {
		this.name = name;
		this.xpos = 9;
		this.ypos = 7;
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

}

