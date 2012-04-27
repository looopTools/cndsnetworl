package right;

import java.net.ServerSocket;

public class TCPServer{

	public static void main(String[] args){
		ArrayList<TCPThread> threads = new ArrayList<TCPThread>();
		
		try{
			ServerSocket connection = new ServerSocket(1337);
			
			while(true){
				Socket connectioSocket = connection.accept();
				TCPThread playerConnection = new TCPThread(connectioSocket, threads);
				threads.add(playerConnection);
				playerConnection.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
