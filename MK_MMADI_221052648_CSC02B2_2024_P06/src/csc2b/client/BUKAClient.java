package csc2b.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

public class BUKAClient 
{
	private Socket soc;
	private PrintWriter out;
	private BufferedReader in;
	private String command;
	private ArrayList<String> files;
	
	public BUKAClient(int port) 
	{
		try 
		{				
			this.soc=new Socket("localhost",port);
			this.soc.setSoTimeout(5000);
			this.out = new PrintWriter(soc.getOutputStream(),true);
			this.in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
				
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public String sendComand(String Command)
	{
		this.command=Command;
		String responseToUser="";
		this.out.println(Command);
		System.out.println("User command : "+command);
		return responseToUser;
	}
	
	
	public String recieve()
	{
		String serverResponse ="";
	
			try 
			{
				while((serverResponse=this.in.readLine())!=null)
				{
					
					if(serverResponse.startsWith("200"))
					{
						System.out.println("Server Response : "+serverResponse);
						break;
					}else
					{
						System.err.println("Error : "+serverResponse);
						break;
					}	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return serverResponse;
	}	
	
	
	public ArrayList<String> recieveList()
	{
		files = new ArrayList<String>();
		String serverResponse ="";
		
			try 
			{
				while((serverResponse=this.in.readLine())!=null)
				{
						if(!serverResponse.startsWith("200"))
						{
							files.add(serverResponse);	
						}if(serverResponse.startsWith("200"))
						{
							System.out.println("Server Response : "+serverResponse);
							break;
						}if(serverResponse.startsWith("500"))
						{
							System.err.println("Error : "+serverResponse);
							break;
						}	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return files;
	}

	public File recieveFile(String text) {
		File fileRequested = null;
		
		for(String file : this.files)
		{
			if(file.startsWith(text))
			{
				fileRequested = new File("data/client/"+file.replace(text+" ", ""));
			}	
		}
		
		try 
		{
			int fileSize = Integer.parseInt(in.readLine());

			FileOutputStream fos = new FileOutputStream(fileRequested);
			byte[] buffer =new byte[1028*4];			
			int byteCount=0;
			int totalbytesReadIn=0;
			DataInputStream dis = new DataInputStream(this.soc.getInputStream());

			
			while(totalbytesReadIn!= fileSize)
			{
				byteCount=dis.read(buffer,0,buffer.length);
				fos.write(buffer, 0, byteCount);
				fos.flush();
				totalbytesReadIn+=byteCount;
			}
			
			fos.close();
			dis.close();
			
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return fileRequested;
	}
	
	
	
}
	
