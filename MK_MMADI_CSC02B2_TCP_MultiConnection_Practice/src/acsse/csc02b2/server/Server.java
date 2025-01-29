package acsse.csc02b2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	private ServerSocket ss;
	
	public Server( int port)
	{
		try 
		{
			this.ss=new ServerSocket(port);
			
			while(true)
			{
				System.out.println("Waiting for connections...");
				Socket soc = ss.accept();
				System.out.println("Connected !");
				Thread thread = new Thread(new ServerHandler(soc));
				thread.start();
			}	
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}finally
		{
			if(ss!=null)
			{
				try 
				{
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args)
	{
		new Server(1234);
	}

}
