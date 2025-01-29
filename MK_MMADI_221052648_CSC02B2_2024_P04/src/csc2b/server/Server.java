package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket SS ;
	private boolean ContinueRunning;
	
	public Server(int Port)
	{
		try {
			SS=new ServerSocket(Port);
			ContinueRunning=true;
			runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	private void runServer()
	{
		System.out.println("Server Starting");
		
		while(ContinueRunning)
		{
			try
			{
				//setting up socket
				Socket socket = SS.accept();
				System.out.println("Connection Established");
				FileHandler  HandleFiles = new FileHandler(socket);
				Thread thread = new Thread(HandleFiles);
				thread.start();
				
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server s = new Server(5432);
	}

}
