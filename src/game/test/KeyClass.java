package game.test;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class KeyClass implements KeyListener {
	private ObjectOutputStream out;

	public KeyClass(ObjectOutputStream out){
		this.out = out;
	}

	@SuppressWarnings("static-access")
	public void keyPressed(KeyEvent ke) {
		try{
		if (ke.getKeyCode() == ke.VK_UP) {
			out.writeObject("up");
			out.flush();
		}

		if (ke.getKeyCode() == ke.VK_DOWN) {
			out.writeObject("down");
			out.flush();
		}
		if (ke.getKeyCode() == ke.VK_LEFT) {
			out.writeObject("left");
			out.flush();
		}
		if (ke.getKeyCode() == ke.VK_RIGHT) {
			out.writeObject("right");
			out.flush();
		}
		if (ke.getKeyCode() == ke.VK_ESCAPE){
			out.writeObject("esc");
			out.flush();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void keyReleased(KeyEvent ke) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}