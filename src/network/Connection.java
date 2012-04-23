package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread {

	private BufferedReader inFromUser;
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;

	public Connection() {
		try{
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
			clientSocket = new Socket("127.0.0.1", 1337); //Cause we can 
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public void run(){
		try{
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

			readUserInput.start();

			while(true){
				String in = inFromServer.readLine();
				System.out.println(in);

				if(in == null){
					System.out.println("Connection lost");
					readUserInput.interrupt();
					return;
				}
			}


		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
