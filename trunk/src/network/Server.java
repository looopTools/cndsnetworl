package network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	
	private ArrayList<TCPThread> threads;
	private ServerSocket welcomeSocket;
	
	public Server(){
		
	}
	
	public void run(){
//		ArrayList<E>
		try{
			welcomeSocket = new ServerSocket(3000);
			while(true){
				Socket connectionSocket = welcomeSocket.accept();
				TCPThread tserv = new TCPThread(connectionSocket);
				threads.add(tserv);
				tserv.start();
			}
		}
		catch (Exception e){
			
		}
	}
}
