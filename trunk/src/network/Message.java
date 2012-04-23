package network;

public class Message {
	
//	int oldX, int oldY, int x, int y,String playerDirection
	
	private int x, y, point;
	private String direction;
	
	public Message(String direction, int x, int y, int point){
		this.direction = direction;
		this.x = x; 
		this.y = y;
		this.point = point;
	}
	
	
	//TODO: Consider the need of setters
	
	public int getY(){
		return this.y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getPoint(){
		return this.point;
	}
	
	
	
	

}
