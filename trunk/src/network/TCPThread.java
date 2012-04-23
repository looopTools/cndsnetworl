package network;

import game.Player;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TCPThread extends Thread{
	
	private Player player;
	private Socket connectionSocket;
	private DataOutputStream dos;
	
	public TCPThread(Player player, Socket connectionSocket){
		
		this.player = player;
		
		try {
			this.connectionSocket = connectionSocket;
			dos = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Fail");
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		try{
			while(true){
				ObjectInputStream input = new ObjectInputStream(connectionSocket.getInputStream());
				Message m = (Message) input.readObject();
				
				if(m != null) {
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
