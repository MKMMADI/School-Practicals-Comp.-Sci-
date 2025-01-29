package csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private ServerSocket ss;
	
	public Server(int port){
		try 
		{
			ss=new ServerSocket(port);
			System.out.println("Listening for client conncections");
			Socket socket = ss.accept();
			System.out.println("Connection established");
			
			Thread task = new Thread(new BUKAHandler(socket));
			 
			task.start();
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
	}
	
	
    public static void main(String[] argv)
    {
    	new Server(2018);
    }
}
