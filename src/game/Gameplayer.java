package game;
import java.util.Random;


public class Gameplayer {
	
	// Players start values
	//private String playerDirection = "up";

	Player me;
	
	private String wall = "w";
	private KeyClass ko;
	ScoreList slist;

	
// level is defined column by column
	private String[][] level = {
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w", "e", "e", "w", "w", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
			};
	// level is defined column by column
	Screen screen; 

	public String[][] getLevel(){
		return level;
	}
	
	public Gameplayer(Player me, ScoreList s) {
	
		this.me = me;
		this.slist = s;
		screen = new Screen(level,me.getXpos(),me.getYpos());
		screen.setVisible(true);
		ko = new KeyClass(this);
		screen.addKeyListener(ko); 
	}

	public void PlayerMoved(String direction) {
		me.setDirection(direction);
		int x = me.getXpos(),y = me.getYpos();
		if (direction.equals("right")) {
			x = me.getXpos() + 1;
		};
		if (direction.equals("left")) {
			x = me.getXpos() - 1;
		};
		if (direction.equals("up")) {
			y = me.getYpos() - 1;
		};
		if (direction.equals("down")) {
			y = me.getYpos() + 1;
		};
		if (level[x][y].equals(wall)) {
			me.subOnePoint();
			slist.updateScoreOnScreen(me);
		} 
		else {
			me.addOnePoint();
			slist.updateScoreOnScreen(me);
			screen.movePlayerOnScreen(me.getXpos(), me.getYpos(), x, y, me.getDirection());
			me.setXpos(x);
			me.setYpos(y);
		}
	
	}
	
	
	//We have add following
	public int[] spawn(){
		int[] coordinats = new int[2];
		Random r = new Random();
		
		int x = r.nextInt(20);
		int y = r.nextInt(20);
		
		if((!level[x][y].equals("w"))){
			coordinats[0] = x;
			coordinats[1] = y;
		}
		else{
			spawn();
		}
		return coordinats;
	}
	
	public Screen getScreen(){
		return this.screen;
	}
	
	
}

