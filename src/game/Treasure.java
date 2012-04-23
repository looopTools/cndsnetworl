package game;

import java.util.Random;

public class Treasure {
	
	//priceSize is the amount of points the player gets, when "catching" the treasure 
	private int priceSize, x, y;
	//TODO ADD TILE and images
	
	public Treasure(int x, int y){
		
		Random r = new Random();
		this.priceSize = r.nextInt(1002); //'cause of the arabian night
		this.x = x;
		this.y = y;
		
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getPriceSize(){
		return this.priceSize;
	}
}
