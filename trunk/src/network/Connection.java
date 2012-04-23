package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread {

	public Connection() {

	}

	public void run(){
		try{
			final BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
			Socket clientSocket = new Socket("127.0.0.1", 1337); //Cause we can 
			final DataOutputStream outToServer = new DataOutputStream(
					clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			Thread readUserInput = new Thread(){
				public void run(){
					while(!interrupted()){
						String sentence;
						try {
							sentence = inFromUser.readLine(); // change to right input
							outToServer.writeBytes(sentence);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						
					}
				}
			};
				
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
