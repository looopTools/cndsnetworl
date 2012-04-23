package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class TCPThread extends Thread{
	
	private String playerName;
	private Socket connectionSocket;
	private DataOutputStream dos;
	
	public TCPThread(Socket connectionSocket){
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
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
