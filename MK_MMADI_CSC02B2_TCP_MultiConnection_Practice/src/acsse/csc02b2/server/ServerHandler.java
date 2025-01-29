package acsse.csc02b2.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerHandler implements Runnable 
{
	private Socket incommingConnection;
	private BufferedReader in;
	private PrintWriter out;
	private DataOutputStream dos;
	
	public ServerHandler(Socket soc) 
	{
		// TODO Auto-generated constructor stub
		
		try 
		{
			this.incommingConnection=soc;
			this.in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			this.out=new PrintWriter(soc.getOutputStream(),true);
			this.dos=new DataOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() 
	{
		boolean Continue=true;
		while(Continue)
		{
			try 
			{
				String command="";
				command= this.in.readLine();
				System.out.println("User Command Is : " + command);
				Continue = processCommand(command);
				
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}

	private boolean processCommand(String command) 
	{
		String ServerResponse = "";
		File fileTobeSent = null;
		switch(command)
		{
			case "HELLO":
			{
				this.out.println("200 <Nice To Meet You>");
				break;
			}
			
			case "GET":
			{
				fileTobeSent=new File("data/server/List.txt");
				
				if(fileTobeSent.exists())
				{

					long fileSize = fileTobeSent.length();
					this.out.println(fileSize);
					
					try 
					{
						
						FileInputStream fis = new FileInputStream(fileTobeSent);
						
						byte buffer[] = new byte[1028*4];
						int byteCount=0;
						
						while((byteCount=fis.read(buffer))>0)
						{
							this.dos.write(buffer,0,byteCount);
							this.dos.flush();
						}
						this.out.println("200 <File Sent Successfully.>");
						fis.close();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				break;
				
			}
			
			case "LOGOFF":
			{
				this.out.println("200 <GoodBye>");
				return false;
			}
		}
		return true;
		
	}
	
		
		

	

}
 