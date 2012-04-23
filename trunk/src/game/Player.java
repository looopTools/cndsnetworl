package game;

public class Player {
String name;
int xpos;
int ypos;
int point;
String direction;

public Player (String name) {
	this.name = name;
	xpos = 9;
	ypos = 7;
	point = 0;
	direction = "up";
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

public String ToString () {
	return name + "   " + point;
}
public void addOnePoint() {
	
	point ++;
}

public void subOnePoint() {
	point --;
}

public void playerKilled(){
	point += 50;
}

public void killedByPlayer(){
	point -= 50;
}

}

