package network;

public class Message {
	
//	int oldX, int oldY, int x, int y,String playerDirection
	
	private int x, y, point;
	
	public Message(int x, int y){
		this.x = x; 
		this.y = y;
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
