package acsse.csc02b2.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class client 
{
	Socket soc;
	DataInputStream dis;
	PrintWriter out ;
	BufferedReader in ;
	FileOutputStream fos;
	File fileToBeSaved;
	private String command;
	
	public client() 
	{
		try 
		{
			this.soc=new Socket("localhost",1234);
			this.soc.setSoTimeout(5000);
			this.dis=new DataInputStream(soc.getInputStream());
			this.in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			this.out=new PrintWriter(soc.getOutputStream(),true);
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void send(String command) 
	{
		// TODO Auto-generated method stub

		try 
		{
			this.out.println(command);
			this.command=command;
			System.out.println("Command sent is : "+command);
			String serverResponse=this.in.readLine();
			System.out.println("Servers Response Is : "+serverResponse);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recieve()
	{
		try 
		{
			String serverResponse="";

			while((serverResponse=this.in.readLine())!=null)
			{
				if(this.command.equals("GET"))
				{
					if(!serverResponse.startsWith("200"))
					{
						this.fileToBeSaved = new File("data/server/List.txt");
						
						byte[] buffer = new byte[1028*4];
						int byteCount=0;
						int totalBytesRead=0;
						this.fos=new FileOutputStream(fileToBeSaved);
						
						while(totalBytesRead!= byteCount)
						{
							byteCount=this.dis.read(buffer,0,buffer.length);
							this.fos.write(buffer, 0, byteCount);
							this.fos.flush();
							totalBytesRead+=byteCount;
						}
						this.fos.close();
				}

				}else if(serverResponse.startsWith("200"))
				{
					System.out.println("Server Response : "+serverResponse);
					break;
				}else if(serverResponse.startsWith("500"))
				{
					System.err.println("Error : "+serverResponse);
					break;
				}
				
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
